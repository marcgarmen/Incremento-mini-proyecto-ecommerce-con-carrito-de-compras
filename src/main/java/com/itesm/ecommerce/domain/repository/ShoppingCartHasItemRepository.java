package com.itesm.ecommerce.domain.repository;

public interface ShoppingCartHasItemRepository {
    void addItemToCart(int cartId, int productId, int quantity);
    void updateItemQuantity(int cartId, int productId, int quantity);
    void removeItemFromCart(int cartId, int productId);
    void clearCart(int cartId);
}
