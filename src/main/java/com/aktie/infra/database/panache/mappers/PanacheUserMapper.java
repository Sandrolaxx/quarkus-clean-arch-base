package com.aktie.infra.database.panache.mappers;

import com.aktie.domain.entities.UserBO;
import com.aktie.infra.database.panache.model.PanacheUser;
import com.aktie.presentation.dto.UserDTO;

/**
 *
 * @author SRamos
 */
public class PanacheUserMapper {
    
    public static UserBO toDomain(PanacheUser panacheUser) {
        var userBO = new UserBO();
        
        userBO.setId(panacheUser.getId());
        userBO.setName(panacheUser.getName());
        userBO.setDocument(panacheUser.getDocument());

        return userBO;
    }
    
    public static PanacheUser toEntity(UserDTO userDTO) {
        var panacheUser = new PanacheUser();

        panacheUser.setName(userDTO.getName());
        panacheUser.setDocument(userDTO.getDocument());
        
        return panacheUser;
    }

}
