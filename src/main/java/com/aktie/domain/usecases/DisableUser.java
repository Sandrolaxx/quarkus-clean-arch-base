package com.aktie.domain.usecases;

import java.util.List;

import com.aktie.domain.entities.dto.UserDTO;
import com.aktie.domain.entities.enums.EnumErrorCode;
import com.aktie.domain.entities.mappers.UserMapper;
import com.aktie.domain.entities.vo.QueryFieldInfoVO;
import com.aktie.domain.repositories.IUserRepository;
import com.aktie.domain.utils.Utils;
import com.aktie.domain.utils.exception.AktieException;

/**
 *
 * @author SRamos
 */
public class DisableUser {

    private IUserRepository userRepository;

    public DisableUser(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO execute(String userId) {

        var queryFieldDoc = new QueryFieldInfoVO("id", Utils.getUUIDfromStr(userId));

        var existingUserBO = userRepository.findFirstBy(List.of(queryFieldDoc));

        if (existingUserBO == null) {
            throw new AktieException(EnumErrorCode.USUARIO_NAO_ENCONTRADO_FILTROS, "identificador");
        }

        existingUserBO.handleDisable();

        userRepository.merge(existingUserBO);

        return UserMapper.toDTO(existingUserBO);

    }

}
