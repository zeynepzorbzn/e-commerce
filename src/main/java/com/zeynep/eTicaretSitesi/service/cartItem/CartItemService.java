package com.zeynep.eTicaretSitesi.service.cartItem;

import com.zeynep.eTicaretSitesi.core.dao.BaseService;
import com.zeynep.eTicaretSitesi.core.entity.Cart;
import com.zeynep.eTicaretSitesi.core.entity.CartItem;
import com.zeynep.eTicaretSitesi.core.entity.ProductVariant;
import com.zeynep.eTicaretSitesi.core.entity.User;
import com.zeynep.eTicaretSitesi.dto.cartItem.CartItemInput;
import com.zeynep.eTicaretSitesi.dto.cartItem.CartItemResponse;
import com.zeynep.eTicaretSitesi.logic.cartItem.CartItemLogic;
import com.zeynep.eTicaretSitesi.logic.productVariant.ProductVariantLogic;
import com.zeynep.eTicaretSitesi.mapper.cartItem.CartItemMapper;
import com.zeynep.eTicaretSitesi.repo.cart.CartRepository;
import com.zeynep.eTicaretSitesi.repo.cartItem.CartItemRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartItemService extends BaseService<CartItem, CartItemInput, Long, CartItemLogic, CartItemMapper, CartItemRepository, CartItemResponse> {

    private final ProductVariantLogic productVariantLogic;
    private final CartRepository cartRepository;

    public CartItemService(
            CartItemRepository repository,
            CartItemLogic logic,
            CartItemMapper mapper,
            ProductVariantLogic productVariantLogic,
            CartRepository cartRepository
    ) {
        super(repository, logic, mapper);
        this.productVariantLogic = productVariantLogic;
        this.cartRepository = cartRepository;
    }

    public CartItemResponse addToCart(CartItemInput input) {

        // 1. JWT'den giriş yapmış kullanıcıyı al
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        // 2. Kullanıcının sepetini bul
        // Sepeti yoksa oluştur
        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    newCart.setTotalPrice(BigDecimal.ZERO);
                    newCart.setProductCount(0);

                    return cartRepository.save(newCart);
                });

        // 3. Gönderilen ProductVariant'ı bul
        ProductVariant variant = productVariantLogic
                .findById(input.getProductVariantId())
                .orElseThrow(() ->
                        new RuntimeException("Ürün varyantı bulunamadı.")
                );

        // 4. Quantity kontrolü
        if (input.getQuantity() == null || input.getQuantity() <= 0) {
            throw new RuntimeException(
                    "Miktar 0'dan büyük olmalıdır."
            );
        }

        // 5. Stok kontrolü
        if (variant.getStock() < input.getQuantity()) {
            throw new RuntimeException(
                    "Yeterli stok bulunmamaktadır."
            );
        }

        // 6. Bu ürün varyantı sepette zaten var mı?
        CartItem item = repository
                .findByCartIdAndProductVariantId(
                        cart.getId(),
                        input.getProductVariantId()
                )
                .orElse(null);

        // 7. Yoksa yeni CartItem oluştur
        if (item == null) {

            item = new CartItem();
            item.setCart(cart);
            item.setProductVariant(variant);
            item.setQuantity(input.getQuantity());

        } else {

            // 8. Varsa mevcut quantity'nin üzerine ekle
            int newQuantity =
                    item.getQuantity() + input.getQuantity();

            if (newQuantity > variant.getStock()) {
                throw new RuntimeException(
                        "Sepetteki toplam miktar stoktan fazla olamaz."
                );
            }

            item.setQuantity(newQuantity);
        }

        // 9. CartItem toplam fiyatını hesapla
        BigDecimal price = variant.getProduct().getPrice();

        BigDecimal totalPrice =
                price.multiply(
                        BigDecimal.valueOf(item.getQuantity())
                );

        item.setTotalPrice(totalPrice);

        // 10. CartItem'i kaydet
        CartItem savedItem = repository.save(item);

        // 11. Sepetin toplamlarını güncelle
        updateCartTotals(cart);

        return logic.toResponse(savedItem);
    }

    public CartItemResponse updateQuantity(Long cartItemId, Integer quantity) {

        // 1. JWT'den giriş yapan kullanıcıyı al
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        // 2. CartItem'ı bul
        CartItem item = repository.findById(cartItemId)
                .orElseThrow(() ->
                        new RuntimeException("Sepet ürünü bulunamadı.")
                );

        // 3. Bu ürün gerçekten giriş yapan kullanıcının sepetinde mi?
        if (!item.getCart().getUser().getId().equals(user.getId())) {
            throw new RuntimeException(
                    "Bu sepete erişim yetkiniz yok."
            );
        }

        // 4. Quantity kontrolü
        if (quantity == null || quantity <= 0) {
            throw new RuntimeException(
                    "Miktar 0'dan büyük olmalıdır."
            );
        }

        // 5. Ürünün stok kontrolü
        ProductVariant variant = item.getProductVariant();

        if (quantity > variant.getStock()) {
            throw new RuntimeException(
                    "Yeterli stok bulunmamaktadır."
            );
        }

        // 6. Yeni quantity
        item.setQuantity(quantity);

        // 7. Yeni CartItem fiyatı
        BigDecimal price = variant.getProduct().getPrice();

        BigDecimal totalPrice =
                price.multiply(BigDecimal.valueOf(quantity));

        item.setTotalPrice(totalPrice);

        // 8. CartItem'ı kaydet
        CartItem savedItem = repository.save(item);

        // 9. Cart toplamlarını tekrar hesapla
        updateCartTotals(item.getCart());

        return logic.toResponse(savedItem);
    }

    public Boolean removeFromCart(Long cartItemId) {

        // 1. JWT'den giriş yapan kullanıcıyı al
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        // 2. CartItem'ı bul
        CartItem item = repository.findById(cartItemId)
                .orElseThrow(() ->
                        new RuntimeException("Sepet ürünü bulunamadı.")
                );

        // 3. Bu ürün gerçekten kullanıcının sepetinde mi?
        if (!item.getCart().getUser().getId().equals(user.getId())) {
            throw new RuntimeException(
                    "Bu sepete erişim yetkiniz yok."
            );
        }

        // 4. Sepeti kaydet
        Cart cart = item.getCart();

        // 5. CartItem'ı sil
        repository.delete(item);

        // 6. Sepetin toplamlarını yeniden hesapla
        updateCartTotals(cart);

        return true;
    }



    private void updateCartTotals(Cart cart) {

        var items = repository.findAll()
                .stream()
                .filter(item ->
                        item.getCart()
                                .getId()
                                .equals(cart.getId())
                )
                .toList();

        BigDecimal total = items.stream()
                .map(CartItem::getTotalPrice)
                .reduce(
                        BigDecimal.ZERO,
                        BigDecimal::add
                );

        int productCount = items.stream()
                .mapToInt(CartItem::getQuantity)
                .sum();

        cart.setTotalPrice(total);
        cart.setProductCount(productCount);

        cartRepository.save(cart);
    }
}