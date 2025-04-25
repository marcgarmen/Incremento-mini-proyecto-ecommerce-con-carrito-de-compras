package com.itesm.ecommerce.application.service.cart;

import com.itesm.ecommerce.domain.model.Product;
import com.itesm.ecommerce.domain.model.ShoppingCart;
import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.domain.repository.ProductRepository;
import com.itesm.ecommerce.domain.repository.ShoppingCartHasItemRepository;
import com.itesm.ecommerce.domain.repository.ShoppingCartRepository;
import com.itesm.ecommerce.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class CartService {

    @Inject
    ShoppingCartRepository shoppingCartRepository;
    @Inject
    UserRepository userRepository;
    @Inject
    ProductRepository productRepository;
    @Inject
    ShoppingCartHasItemRepository shoppingCartHasItemRepository;

    @Transactional
    public void addProductToCartService(int productId, int quantity, String firebaseId){
        User user = userRepository.findByFirebaseId(firebaseId);
        ShoppingCart sc = shoppingCartRepository.findByUserId(user.getId());
        if(sc == null){
            shoppingCartRepository.createShoppingCart(user.getId());
            sc = shoppingCartRepository.findByUserId(user.getId());
        }
        shoppingCartHasItemRepository.addItemToCart(sc.getId(), productId, quantity);
    }

    public ShoppingCart getShoppingCart(String firebaseId) {
        User user = userRepository.findByFirebaseId(firebaseId);
        return shoppingCartRepository.findByUserId(user.getId());
    }

    @Transactional
    public void updateProductQuantity(int productId, int quantity, String firebaseId) {
        User user = userRepository.findByFirebaseId(firebaseId);
        ShoppingCart cart = shoppingCartRepository.findByUserId(user.getId());
        shoppingCartHasItemRepository.updateItemQuantity(cart.getId(), productId, quantity);
    }

    @Transactional
    public void removeProductFromCart(int productId, String firebaseId) {
        User user = userRepository.findByFirebaseId(firebaseId);
        ShoppingCart cart = shoppingCartRepository.findByUserId(user.getId());
        shoppingCartHasItemRepository.removeItemFromCart(cart.getId(), productId);
    }

    @Transactional
    public void clearCartByFirebaseId(String firebaseId) {
        User user = userRepository.findByFirebaseId(firebaseId);
        ShoppingCart cart = shoppingCartRepository.findByUserId(user.getId());
        shoppingCartHasItemRepository.clearCart(cart.getId());
    }

    public Map<String, Double> calculateSummary(String firebaseId) {
        ShoppingCart cart = getShoppingCart(firebaseId);
        double subtotal = cart.getItems().stream()
                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity())
                .sum();
        double total = subtotal; // Add taxes or discounts if needed

        Map<String, Double> summary = new HashMap<>();
        summary.put("subtotal", subtotal);
        summary.put("total", total);
        return summary;
    }

    public void simulateCheckout(String firebaseId) {
        // Simulate payment confirmation
        // In the future: update order status, notify user, etc.
        System.out.println("Checkout completed for Firebase ID: " + firebaseId);
    }
}
