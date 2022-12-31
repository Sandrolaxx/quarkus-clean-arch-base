package com.aktie.domain.entities.mappers;

import com.aktie.domain.entities.UserBO;
import com.aktie.presentation.dto.UserDTO;

/**
 *
 * @author SRamos
 */
public class UserMapper {

    public static UserDTO toDTO(UserBO userBO) {
        var userDTO = new UserDTO();

        userDTO.setDocument(userBO.getDocument());
        userDTO.setName(userBO.getName());
        userDTO.setId(userBO.getId());

        return userDTO;
    }

    public static UserDTO toBO(UserDTO userDTO) {
        var userBO = new UserDTO();

        userBO.setDocument(userDTO.getDocument());
        userBO.setName(userDTO.getName());

        return userBO;
    }

}
