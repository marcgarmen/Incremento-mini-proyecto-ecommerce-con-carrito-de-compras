package com.itesm.ecommerce.infrastructure.dto.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductDTO {
    private int productId;
    private int newQuantity;
}
