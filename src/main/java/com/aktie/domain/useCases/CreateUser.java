package com.aktie.domain.useCases;

import com.aktie.domain.entities.UserBO;
import com.aktie.domain.entities.mappers.UserMapper;
import com.aktie.domain.repositories.UserRepository;
import com.aktie.presentation.dto.UserDTO;
import com.aktie.utils.ValidatorUtil;

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
        ValidatorUtil.verifyUserDto(userDto);

        UserBO existingUserBO = userRepository.findByDocument(userDto.getDocument());

        if (existingUserBO != null) {
            throw new Exception("Usuário existente");
        }

        UserBO userBO = userRepository.createUser(userDto);

        return UserMapper.toDTO(userBO);
    }

}
