package com.aktie.domain.entities.mappers;

import com.aktie.domain.entities.UserBO;
import com.aktie.domain.entities.dto.UserDTO;
import com.aktie.domain.entities.vo.CreatedAtVO;
import com.aktie.domain.entities.vo.UuidVO;

/**
 *
 * @author SRamos
 */
public class UserMapper {

    public static UserDTO toDTO(UserBO bo) {
        var userDTO = new UserDTO();

        userDTO.setDocument(bo.getDocument());
        userDTO.setName(bo.getName());
        userDTO.setId(bo.getId().getValue().toString());
        userDTO.setDisabledAt(bo.getDisabledAt());
        userDTO.setCreatedAt(bo.getCreatedAt().getValue());

        return userDTO;
    }

    public static UserBO toBO(UserDTO dto) {
        return new UserBO(
                new UuidVO(dto.getId()),
                dto.getName(),
                dto.getDocument(),
                new CreatedAtVO(dto.getCreatedAt()),
                dto.getDisabledAt());
    }

}
