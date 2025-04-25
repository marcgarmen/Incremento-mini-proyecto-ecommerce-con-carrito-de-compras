package com.itesm.ecommerce.infrastructure.persistence.repository;

import com.itesm.ecommerce.domain.repository.ShoppingCartHasItemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ShoppingCartHasItemRepositoryImpl implements ShoppingCartHasItemRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void addItemToCart(int cartId, int productId, int quantity) {
        em.createNativeQuery("INSERT INTO shopping_cart_has_products (shopping_cart_id, product_id, quantity) " +
                        "VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE quantity = quantity + ?")
                .setParameter(1, cartId)
                .setParameter(2, productId)
                .setParameter(3, quantity)
                .setParameter(4, quantity)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void updateItemQuantity(int cartId, int productId, int quantity) {
        em.createNativeQuery("UPDATE shopping_cart_has_products SET quantity = ? WHERE shopping_cart_id = ? AND product_id = ?")
                .setParameter(1, quantity)
                .setParameter(2, cartId)
                .setParameter(3, productId)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void removeItemFromCart(int cartId, int productId) {
        em.createNativeQuery("DELETE FROM shopping_cart_has_products WHERE shopping_cart_id = ? AND product_id = ?")
                .setParameter(1, cartId)
                .setParameter(2, productId)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void clearCart(int cartId) {
        em.createNativeQuery("DELETE FROM shopping_cart_has_products WHERE shopping_cart_id = ?")
                .setParameter(1, cartId)
                .executeUpdate();
    }
}
