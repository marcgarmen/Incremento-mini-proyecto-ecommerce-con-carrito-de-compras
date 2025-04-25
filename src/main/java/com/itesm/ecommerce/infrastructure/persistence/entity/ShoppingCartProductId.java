package com.itesm.ecommerce.infrastructure.persistence.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartProductId implements Serializable {

    private int shoppingCartId;
    private int productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingCartProductId)) return false;
        ShoppingCartProductId that = (ShoppingCartProductId) o;
        return shoppingCartId == that.shoppingCartId && productId == that.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoppingCartId, productId);
    }
}
