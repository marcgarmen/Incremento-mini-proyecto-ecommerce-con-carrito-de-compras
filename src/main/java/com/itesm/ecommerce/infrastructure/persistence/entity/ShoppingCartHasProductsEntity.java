package com.itesm.ecommerce.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shopping_cart_has_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartHasProductsEntity {

    @EmbeddedId
    private ShoppingCartProductId id;

    @Column(nullable = false)
    private int quantity;

    // Relaciones con ShoppingCart y Product (opcional si necesitas acceso a ellos como objetos)
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("shoppingCartId")
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCartEntity shoppingCart;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductEntity product;
}
