package com.aktie.services;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.aktie.domain.entities.dto.UserDTO;
import com.aktie.domain.useCases.CreateUser;
import com.aktie.infra.database.panache.repositories.PanacheUserRepository;

@ApplicationScoped
public class UserService {
    
    @Transactional
    public UserDTO handle(UserDTO userDTO) throws Exception {

        PanacheUserRepository panacheUserRepository = new PanacheUserRepository();
        CreateUser createUser = new CreateUser(panacheUserRepository);

        return createUser.execute(userDTO);

    }

}
