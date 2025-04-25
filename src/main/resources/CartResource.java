package com.itesm.resource;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@DeclareRoles("admin")
@Path("/cart")
@RolesAllowed("admin")
public class CartResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCart() {
        return Response.ok("{\"message\": \"Carrito cargado correctamente\"}").build();
    }
}
