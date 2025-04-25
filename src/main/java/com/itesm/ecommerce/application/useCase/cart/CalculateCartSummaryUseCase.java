package com.itesm.ecommerce.application.useCase.cart;

import com.itesm.ecommerce.application.service.cart.CartService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Map;

@ApplicationScoped
public class CalculateCartSummaryUseCase {
    @Inject
    CartService cartService;

    public Map<String, Double> execute(String firebaseId) {
        return cartService.calculateSummary(firebaseId);
    }
}