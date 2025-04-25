package com.itesm.ecommerce.application.useCase.cart;

import com.itesm.ecommerce.application.service.cart.CartService;
import com.itesm.ecommerce.infrastructure.dto.cart.UpdateProductDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdateProductInCartUseCase {
    @Inject
    CartService cartService;

    public void execute(UpdateProductDTO dto, String firebaseId) {
        cartService.updateProductQuantity(dto.getProductId(), dto.getNewQuantity(), firebaseId);
    }
}