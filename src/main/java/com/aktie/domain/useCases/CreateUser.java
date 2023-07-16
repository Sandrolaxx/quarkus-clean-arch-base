package com.aktie.domain.useCases;

import java.util.List;

import com.aktie.domain.entities.dto.UserDTO;
import com.aktie.domain.entities.enums.EnumErrorCode;
import com.aktie.domain.entities.mappers.UserMapper;
import com.aktie.domain.entities.vo.QueryFieldInfoVO;
import com.aktie.domain.repositories.IUserRepository;
import com.aktie.domain.utils.exception.AktieException;

/**
 *
 * @author SRamos
 */
public class CreateUser {

    private IUserRepository userRepository;

    public CreateUser(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO execute(UserDTO dto) {
        verifyExistingUser(dto.getDocument());

        var userBO = UserMapper.toBO(dto);

        userBO = userRepository.create(userBO);

        return UserMapper.toDTO(userBO);
    }

    private void verifyExistingUser(String document) {
        var queryFieldDoc = new QueryFieldInfoVO("document", document);
        var queryFieldDisabled = new QueryFieldInfoVO("disabledAt", null);

        var existingUserBO = userRepository.findFirstBy(List.of(queryFieldDoc, queryFieldDisabled));

        if (existingUserBO != null) {
            throw new AktieException(EnumErrorCode.USUARIO_CADASTRADO);
        }
    }

}
