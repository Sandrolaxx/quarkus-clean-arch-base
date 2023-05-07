package com.aktie.domain.entities.mappers;

import com.aktie.domain.entities.UserBO;
import com.aktie.domain.entities.dto.UserDTO;
import com.aktie.domain.entities.vo.UuidVO;

/**
 *
 * @author SRamos
 */
public class UserMapper {

    public static UserDTO toDTO(UserBO userBO) {
        var userDTO = new UserDTO();

        userDTO.setDocument(userBO.getDocument());
        userDTO.setName(userBO.getName());
        userDTO.setId(userBO.getId().getValue().toString());
        userDTO.setDisabledAt(userBO.getDisabledAt());
        userDTO.setCreatedAt(userBO.getCreatedAt().getValue());

        return userDTO;
    }

    public static UserBO toBO(UserDTO userDTO) {
        return new UserBO(
                new UuidVO(userDTO.getId()),
                userDTO.getName(),
                userDTO.getDocument(),
                null,
                null);
    }

}
