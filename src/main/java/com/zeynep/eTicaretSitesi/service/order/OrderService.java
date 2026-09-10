package com.zeynep.eTicaretSitesi.service.order;

import com.zeynep.eTicaretSitesi.core.dao.BaseService;
import com.zeynep.eTicaretSitesi.core.entity.*;
import com.zeynep.eTicaretSitesi.dto.order.OrderInput;
import com.zeynep.eTicaretSitesi.dto.order.OrderResponse;
import com.zeynep.eTicaretSitesi.logic.order.OrderLogic;
import com.zeynep.eTicaretSitesi.mapper.order.OrderMapper;
import com.zeynep.eTicaretSitesi.repo.address.AddressRepository;
import com.zeynep.eTicaretSitesi.repo.cart.CartRepository;
import com.zeynep.eTicaretSitesi.repo.cartItem.CartItemRepository;
import com.zeynep.eTicaretSitesi.repo.order.OrderRepository;
import com.zeynep.eTicaretSitesi.repo.orderItem.OrderItemRepository;
import com.zeynep.eTicaretSitesi.repo.paymentMethod.PaymentMethodRepository;
import com.zeynep.eTicaretSitesi.repo.productVariant.ProductVariantRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zeynep.eTicaretSitesi.service.mail.OrderCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import com.zeynep.eTicaretSitesi.service.mail.OrderMailItem;
import com.zeynep.eTicaretSitesi.service.analytics.AnalyticsEventService;

import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Map;

@Service
public class OrderService extends BaseService<Order, OrderInput, Long, OrderLogic, OrderMapper, OrderRepository, OrderResponse> {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductVariantRepository productVariantRepository;
    private final AddressRepository addressRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final AnalyticsEventService analyticsEventService;

    public OrderService(OrderRepository repository, OrderLogic logic, OrderMapper mapper, CartRepository cartRepository, CartItemRepository cartItemRepository, OrderItemRepository orderItemRepository,
             ProductVariantRepository productVariantRepository, AddressRepository addressRepository, PaymentMethodRepository paymentMethodRepository, ApplicationEventPublisher eventPublisher,
                        AnalyticsEventService analyticsEventService) {

        super(repository, logic, mapper);

        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderItemRepository = orderItemRepository;
        this.productVariantRepository = productVariantRepository;
        this.addressRepository = addressRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.eventPublisher = eventPublisher;
        this.analyticsEventService = analyticsEventService;
    }

    @Transactional
    @Override
    public OrderResponse create(OrderInput input) {

        /*
         * JWT'den giriş yapan kullanıcıyı alıyoruz.
         */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        /*
         * Kullanıcının sepetini bul.
         */
        Cart cart = cartRepository.findById(input.getCartId()).orElseThrow(() -> new RuntimeException("Sepet bulunamadı."));
        /*
         * Cart gerçekten bu kullanıcıya mı ait?
         */
        if (!cart.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Bu sepete erişim yetkiniz yok.");
        }
        /*
         * Sepet boş mu?
         */
        if (cart.getItems() == null || cart.getItems().isEmpty()) {throw new RuntimeException("Sepet boş.");
        }
        /*
         * Seçilen adres gerçekten kullanıcıya mı ait?
         */
        Address address = addressRepository.findByIdAndUserId(input.getAddressId(), user.getId()).orElseThrow(() -> new RuntimeException("Teslimat adresi bulunamadı."));
        /*
         * Seçilen ödeme yöntemi gerçekten kullanıcıya mı ait?
         */
        PaymentMethod paymentMethod = paymentMethodRepository.findByIdAndUserId(input.getPaymentMethodId(), user.getId()).orElseThrow(() ->
                new RuntimeException("Ödeme yöntemi bulunamadı."));

        if (!address.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Bu adres size ait değil.");
        }

        if (!paymentMethod.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Bu ödeme yöntemi size ait değil.");
        }

        /*
         * Order oluştur.
         */
        Order order = new Order();

        order.setCode("ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        order.setUser(user);
        order.setAddress(address);
        order.setPaymentMethod(paymentMethod);

        /*
         * Aşağıdaki değerler tekrar hesaplanacak.
         */
        order.setTotalPrice(BigDecimal.ZERO);
        order.setProductCount(0);
        Order savedOrder = repository.save(order);

        BigDecimal totalPrice = BigDecimal.ZERO;

        int productCount = 0;

        List<OrderItem> orderItems = new ArrayList<>();

        /*
         * CartItem -> OrderItem
         */
        for (CartItem cartItem : cart.getItems()) {
            ProductVariant variant = cartItem.getProductVariant();
            /*
             * Stok kontrolü.
             */
            if (variant.getStock() < cartItem.getQuantity()) {
                throw new RuntimeException("Yeterli stok yok: " + variant.getProduct().getName());
            }
            /*
             * OrderItem oluştur.
             */
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setProductVariant(variant);
            orderItem.setQuantity(cartItem.getQuantity());
            /*
             * Snapshot price:
             *
             * Sipariş oluşturulduğu andaki
             * ürün fiyatını OrderItem'a yazıyoruz.
             *
             * Ürün fiyatı daha sonra değişse bile
             * eski siparişin fiyatı değişmeyecek.
             */
            BigDecimal unitPrice = variant.getProduct().getPrice();
            if (variant.getImages() != null && !variant.getImages().isEmpty()) {
                orderItem.setImageToken(
                        variant.getImages().get(0).getImageToken()
                );
            }

            orderItem.setUnitPrice(unitPrice);
            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);


             //Stok düş.

            variant.setStock(variant.getStock() - cartItem.getQuantity());
            productVariantRepository.save(variant);
            /*
             * Toplam hesapla.
             */
            BigDecimal itemTotal = unitPrice.multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            totalPrice = totalPrice.add(itemTotal);
            productCount += cartItem.getQuantity();
        }
        /*
         * Order toplamlarını kaydet.
         */
        savedOrder.setTotalPrice(totalPrice);
        savedOrder.setProductCount(productCount);
        savedOrder.setOrderItems(orderItems);

        Order finalOrder = repository.save(savedOrder);
        /*
         * Sipariş oluşturulduktan sonra
         * cart temizlenir.
         */
        cartItemRepository.deleteAll(cart.getItems());

        cart.setTotalPrice(BigDecimal.ZERO);

        cart.setProductCount(0);

        cartRepository.save(cart);
        /*
         * Analytics: PURCHASE
         *
         * Her OrderItem için ayrı purchase event oluşturuyoruz.
         * Böylece ürün, mağaza, kategori ve marka bazlı
         * satış analitiği doğru şekilde hesaplanabilir.
         */
        for (OrderItem orderItem : orderItems) {

            try {
                ProductVariant variant = orderItem.getProductVariant();
                Product product = variant.getProduct();

                BigDecimal itemTotal =
                        orderItem.getUnitPrice()
                                .multiply(
                                        BigDecimal.valueOf(orderItem.getQuantity())
                                );

                analyticsEventService.track(
                        com.zeynep.eTicaretSitesi.core.enums.AnalyticsEventType.PURCHASE,
                        user.getId(),
                        product.getStore().getId(),
                        product.getId(),
                        product.getCategory().getId(),
                        product.getBrand().getId(),
                        finalOrder.getId(),
                        null,
                        itemTotal,
                        Map.of(
                                "quantity", orderItem.getQuantity(),
                                "unitPrice", orderItem.getUnitPrice().toString()
                        )
                );

            } catch (Exception analyticsError) {
                System.err.println(
                        "Analytics PURCHASE event kaydedilemedi: "
                                + analyticsError.getMessage()
                );
            }
        }

        /*
         * Mail için sipariş bilgilerini transaction içerisindeyken
         * snapshot olarak hazırlıyoruz.
         *
         * Böylece transaction kapandıktan sonra lazy JPA ilişkilerine
         * tekrar erişmek zorunda kalmıyoruz.
         */
        List<OrderMailItem> mailItems = new ArrayList<>();

        for (OrderItem orderItem : orderItems) {

            ProductVariant variant = orderItem.getProductVariant();

            mailItems.add(
                    new OrderMailItem(
                            variant.getProduct().getName(),
                            variant.getSize(),
                            variant.getColor(),
                            orderItem.getQuantity(),
                            orderItem.getUnitPrice()
                    )
            );
        }

        String customerName =
                user.getFirstName() + " " + user.getLastName();

        String orderDate =
                finalOrder.getCreatedAt()
                        .format(
                                DateTimeFormatter.ofPattern(
                                        "dd.MM.yyyy HH:mm"
                                )
                        );
        /*
         * Analytics: PURCHASE
         *
         * Sipariş başarıyla oluşturulduktan sonra
         * satın alma eventini kaydediyoruz.
         */
//        try {
//            analyticsEventService.track(
//                    com.zeynep.eTicaretSitesi.core.enums.AnalyticsEventType.PURCHASE,
//                    user.getId(),
//                    null,
//                    null,
//                    null,
//                    null,
//                    finalOrder.getId(),
//                    null,
//                    finalOrder.getTotalPrice(),
//                    null
//            );
//        } catch (Exception analyticsError) {
//            System.err.println(
//                    "Analytics PURCHASE event kaydedilemedi: "
//                            + analyticsError.getMessage()
//            );
//        }

        eventPublisher.publishEvent(
                new OrderCreatedEvent(
                        user.getEmail(),
                        customerName,
                        finalOrder.getCode(),
                        orderDate,
                        finalOrder.getTotalPrice().toString(),

                        address.getName(),
                        address.getCity(),
                        address.getDistrict(),
                        address.getStreet(),
                        address.getPostalCode(),

                        mailItems
                )
        );

        return logic.toResponse(finalOrder);
    }

    public List<OrderResponse> getMyOrders() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User user =
                (User) authentication.getPrincipal();

        List<Order> orders =
                repository.findMyOrders(user.getId());

        return mapper.toResponseList(orders);
    }
}