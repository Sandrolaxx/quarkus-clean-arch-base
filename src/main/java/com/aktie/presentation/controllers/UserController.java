package com.aktie.presentation.controllers;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aktie.domain.entities.dto.UserDTO;
import com.aktie.services.UserService;

@Path("/hello")
public class UserController {

    @Inject
    UserService userService;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello(UserDTO userDTO) throws Exception {
        UserDTO createdUser = userService.handle(userDTO);

        return Response.ok(createdUser).build();
    }
}