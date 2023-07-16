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
        var dto = new UserDTO();

        dto.setDocument(bo.getDocument());
        dto.setName(bo.getName());
        dto.setId(bo.getId().getValue().toString());
        dto.setDisabledAt(bo.getDisabledAt());
        dto.setUpdatedAt(bo.getUpdatedAt());
        dto.setCreatedAt(bo.getCreatedAt() != null ? bo.getCreatedAt().getValue() : null);

        return dto;
    }

    public static UserBO toBO(UserDTO dto) {
        return new UserBO(
                new UuidVO(dto.getId()),
                dto.getName(),
                dto.getDocument(),
                new CreatedAtVO(dto.getCreatedAt()),
                dto.getUpdatedAt(),
                dto.getDisabledAt());
    }

}
