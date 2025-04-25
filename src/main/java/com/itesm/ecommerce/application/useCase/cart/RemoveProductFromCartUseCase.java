package com.itesm.ecommerce.application.useCase.cart;

import com.itesm.ecommerce.application.service.cart.CartService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RemoveProductFromCartUseCase {
    @Inject
    CartService cartService;

    public void execute(int productId, String firebaseId) {
        cartService.removeProductFromCart(productId, firebaseId);
    }
}
