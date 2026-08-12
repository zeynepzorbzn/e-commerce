package com.zeynep.eTicaretSitesi.service.cartItem;

import com.zeynep.eTicaretSitesi.core.dao.BaseService;
import com.zeynep.eTicaretSitesi.core.entity.Cart;
import com.zeynep.eTicaretSitesi.core.entity.CartItem;
import com.zeynep.eTicaretSitesi.core.entity.ProductVariant;
import com.zeynep.eTicaretSitesi.dto.cartItem.CartItemInput;
import com.zeynep.eTicaretSitesi.dto.cartItem.CartItemResponse;
import com.zeynep.eTicaretSitesi.logic.cart.CartLogic;
import com.zeynep.eTicaretSitesi.logic.cartItem.CartItemLogic;
import com.zeynep.eTicaretSitesi.logic.productVariant.ProductVariantLogic;
import com.zeynep.eTicaretSitesi.mapper.cartItem.CartItemMapper;
import com.zeynep.eTicaretSitesi.repo.cartItem.CartItemRepository;
import org.springframework.stereotype.Service;
import com.zeynep.eTicaretSitesi.repo.cart.CartRepository;

import java.math.BigDecimal;

@Service
public class CartItemService extends BaseService<CartItem, CartItemInput, Long, CartItemLogic, CartItemMapper, CartItemRepository,
        CartItemResponse> {
    private final CartLogic cartLogic;
    private final ProductVariantLogic productVariantLogic;
    private final CartRepository cartRepository;

    public CartItemService(CartItemRepository repository, CartItemLogic logic, CartItemMapper mapper, CartLogic cartLogic,
                           ProductVariantLogic productVariantLogic, CartRepository cartRepository){
        super(repository, logic, mapper);
        this.cartLogic=cartLogic;
        this.productVariantLogic= productVariantLogic;
        this.cartRepository=cartRepository;
    }
    public CartItemResponse addToCart(Long cartId, CartItemInput input) {
        Cart cart = cartLogic.findById(cartId).orElseThrow(() -> new RuntimeException("Sepet Bulunamadı"));
        ProductVariant variant = productVariantLogic.findById(input.getProductVariantId()).orElseThrow(() ->
                new RuntimeException("Ürün bulunamadı."));

    if (input.getQuantity() == null || input.getQuantity() <= 0) {
        throw new RuntimeException("Miktar 0'dan büyük olmalıdır.");
    }
    if (variant.getStock() < input.getQuantity()) {
        throw new RuntimeException("Yeterli stok bulunmamaktadır.");
    }
    CartItem item = repository.findByCartIdAndProductVariantId(cartId, input.getProductVariantId()).orElse(null);

        if (item == null) {
        item = new CartItem();
        item.setCart(cart);
        item.setProductVariant(variant);
        item.setQuantity(input.getQuantity());
        }
        else {
        int newQuantity = item.getQuantity() + input.getQuantity();
        if (newQuantity > variant.getStock()) {
            throw new RuntimeException("Sepetteki toplam miktar stoktan fazla olamaz.");}

        item.setQuantity(newQuantity);
    }
    BigDecimal price = variant.getProduct().getPrice();
    BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(item.getQuantity()));
        item.setTotalPrice(totalPrice);
    CartItem savedItem = repository.save(item);
    updateCartTotals(cart);
        return logic.toResponse(savedItem);
}
    private void updateCartTotals(Cart cart) {

      var items = repository.findAll().stream().filter(item -> item.getCart().getId().equals(cart.getId())).toList();
      BigDecimal total = items.stream().map(CartItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
      int productCount = items.stream().mapToInt(CartItem::getQuantity).sum();
      cart.setTotalPrice(total);
      cart.setProductCount(productCount);
      cartRepository.save(cart);
}


}
