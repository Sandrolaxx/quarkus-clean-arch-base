package com.aktie.domain.entities.mappers;

import com.aktie.domain.entities.UserBO;
import com.aktie.domain.entities.dto.UserDTO;
import com.aktie.domain.entities.vo.UserNameVO;

/**
 *
 * @author SRamos
 */
public class UserMapper {

    public static UserDTO toDTO(UserBO userBO) {
        var userDTO = new UserDTO();

        userDTO.setDocument(userBO.getDocument());
        userDTO.setName(userBO.getName().getValue());
        userDTO.setId(userBO.getId());

        return userDTO;
    }

    public static UserBO toBO(UserDTO userDTO) {
        return new UserBO(
                userDTO.getId(),
                new UserNameVO(userDTO.getName()),
                userDTO.getDocument(),
                null,
                null);
    }

}
