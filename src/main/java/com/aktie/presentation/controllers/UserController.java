package com.aktie.presentation.controllers;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

import com.aktie.domain.entities.dto.UserDTO;
import com.aktie.domain.entities.enums.EnumDBImpl;
import com.aktie.services.UserService;

/**
 *
 * @author SRamos
 */
@Path("/api/v1/user")
public class UserController {

    @Inject
    UserService userService;

    @POST
    public Response create(UserDTO dto, @HeaderParam EnumDBImpl dbImpl) {
        var createdUser = userService.create(dto, dbImpl);

        return Response.ok(createdUser).build();
    }

    @GET
    public Response findBy(@HeaderParam String userId, @HeaderParam EnumDBImpl dbImpl) {
        var userFound = userService.findBy(userId, dbImpl);

        return Response.ok(userFound).build();
    }

    @GET
    @Path("/all")
    public Response findAllBy(@HeaderParam String document, @HeaderParam EnumDBImpl dbImpl) {
        var usersFound = userService.listBy(document, dbImpl);

        return Response.ok(usersFound).build();
    }

    @PUT
    public Response find(UserDTO dto, @HeaderParam String userId, @HeaderParam EnumDBImpl dbImpl) {
        var updatedUser = userService.updateInfo(dto, userId, dbImpl);

        return Response.ok(updatedUser).build();
    }

    @DELETE
    public Response disable(@HeaderParam String userId, @HeaderParam EnumDBImpl dbImpl) {
        var desibledUser = userService.disable(userId, dbImpl);

        return Response.ok(desibledUser).build();
    }

}