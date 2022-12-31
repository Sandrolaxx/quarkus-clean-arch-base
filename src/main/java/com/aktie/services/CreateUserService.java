package com.aktie.services;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.aktie.domain.useCases.CreateUser;
import com.aktie.infra.database.panache.repositories.PanacheUserRepository;
import com.aktie.presentation.dto.UserDTO;

@ApplicationScoped
public class CreateUserService {
    
    @Transactional
    public void handle(UserDTO userDTO) throws Exception {

        PanacheUserRepository panacheUserRepository = new PanacheUserRepository();
        CreateUser createUser = new CreateUser(panacheUserRepository);

        createUser.execute(userDTO);

    }

}
