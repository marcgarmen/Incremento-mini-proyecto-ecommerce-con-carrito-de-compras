package com.itesm.ecommerce.infrastructure.rest;

import com.itesm.ecommerce.application.useCase.cart.*;
import com.itesm.ecommerce.infrastructure.dto.cart.*;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

@Path("/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShoppingCartController {

    private static final String FIREBASE_ID = "vlb5W7GDJEfvy2n4cL7YyuztQSu2";

    @Inject
    AddProductToCartUseCase addProductToCartUseCase;
    @Inject
    FindUserShoppingCartUseCase findUserShoppingCartUseCase;
    @Inject
    UpdateProductInCartUseCase updateProductInCartUseCase;
    @Inject
    RemoveProductFromCartUseCase removeProductFromCartUseCase;
    @Inject
    ClearCartUseCase clearCartUseCase;
    @Inject
    CalculateCartSummaryUseCase calculateCartSummaryUseCase;
    @Inject
    CheckoutCartUseCase checkoutCartUseCase;

    @POST
    @Path("/add")
    public Response addProductToCart(AddProductToCartDTO addProductToCartDTO) {
        addProductToCartUseCase.execute(addProductToCartDTO, FIREBASE_ID);
        Map<String,String> response = new HashMap<>();
        response.put("message", "Product added to cart successfully");
        return Response.ok(response).build();
    }

    @GET
    public Response getShoppingCart() {
        return Response.ok().entity(findUserShoppingCartUseCase.execute(FIREBASE_ID)).build();
    }

    @PUT
    @Path("/update")
    @Transactional
    public Response updateProduct(UpdateProductDTO dto) {
        updateProductInCartUseCase.execute(dto, FIREBASE_ID);
        return Response.ok().entity(Map.of("message", "Product updated successfully")).build();
    }

    @DELETE
    @Path("/remove/{productId}")
    @Transactional
    public Response removeProduct(@PathParam("productId") int productId) {
        removeProductFromCartUseCase.execute(productId, FIREBASE_ID);
        return Response.ok().entity(Map.of("message", "Product removed from cart")).build();
    }

    @DELETE
    @Path("/clear")
    @Transactional
    public Response clearCart() {
        clearCartUseCase.execute(FIREBASE_ID);
        return Response.noContent().build();
    }

    @GET
    @Path("/summary")
    public Response getCartSummary() {
        return Response.ok().entity(calculateCartSummaryUseCase.execute(FIREBASE_ID)).build();
    }

    @POST
    @Path("/checkout")
    public Response checkoutCart() {
        checkoutCartUseCase.execute(FIREBASE_ID);
        return Response.ok().entity(Map.of("message", "Checkout simulated successfully")).build();
    }
}
