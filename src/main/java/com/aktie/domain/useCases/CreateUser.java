package com.aktie.domain.useCases;

import com.aktie.domain.entities.UserBO;
import com.aktie.domain.entities.dto.UserDTO;
import com.aktie.domain.entities.mappers.UserMapper;
import com.aktie.domain.repositories.UserRepository;

/**
 *
 * @author SRamos
 */
public class CreateUser {
    private UserRepository userRepository;

    public CreateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO execute(UserDTO userDto) throws Exception {

        UserBO existingUserBO = userRepository.findByDocument(userDto.getDocument());

        if (existingUserBO != null) {
            throw new Exception("Usuário existente");
        }

        UserBO userBO = UserMapper.toBO(userDto);

        userBO = userRepository.createUser(userBO);

        return UserMapper.toDTO(userBO);
    }

}
