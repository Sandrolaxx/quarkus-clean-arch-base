package com.aktie.domain.useCases;

import java.util.List;

import com.aktie.domain.entities.dto.UserDTO;
import com.aktie.domain.entities.enums.EnumErrorCode;
import com.aktie.domain.entities.mappers.UserMapper;
import com.aktie.domain.entities.vo.QueryFieldInfoVO;
import com.aktie.domain.repositories.UserRepository;
import com.aktie.domain.utils.exception.AktieException;

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

        var queryField = new QueryFieldInfoVO("document", userDto.getDocument());
        var existingUserBO = userRepository.findFirstBy(List.of(queryField));

        if (existingUserBO != null) {
            throw new AktieException(EnumErrorCode.USUARIO_CADASTRADO);
        }

        var userBO = UserMapper.toBO(userDto);

        userBO = userRepository.create(userBO);

        return UserMapper.toDTO(userBO);
    }

}
