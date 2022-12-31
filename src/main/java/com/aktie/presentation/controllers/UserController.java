package com.aktie.presentation.controllers;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.aktie.presentation.dto.UserDTO;
import com.aktie.services.CreateUserService;

@Path("/hello")
public class UserController {

    @Inject
    CreateUserService createUserService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws Exception {
        UserDTO testDto = new UserDTO();

        testDto.setDocument("10564574902");
        testDto.setName("Sandrolax Ramos");

        createUserService.handle(testDto);

        return "Hello RESTEasy";
    }
}