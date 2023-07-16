package com.aktie.infra.database.panache.mappers;

import com.aktie.domain.entities.UserBO;
import com.aktie.domain.entities.vo.CreatedAtVO;
import com.aktie.domain.entities.vo.UuidVO;
import com.aktie.infra.database.panache.model.PanacheUser;

/**
 *
 * @author SRamos
 */
public class PanacheUserMapper {

    public static UserBO toDomain(PanacheUser entity) {
        var userBO = new UserBO(
                new UuidVO(entity.getId().toString()),
                entity.getName(),
                entity.getDocument(),
                new CreatedAtVO(entity.getCreatedAt()),
                entity.getUpdatedAt(),
                entity.getDisabledAt());

        return userBO;
    }

    public static PanacheUser toEntity(UserBO bo) {
        var panacheUser = new PanacheUser();

        panacheUser.setId(bo.getId().getValue());
        panacheUser.setName(bo.getName());
        panacheUser.setDocument(bo.getDocument());
        panacheUser.setDisabledAt(bo.getDisabledAt());
        panacheUser.setUpdatedAt(bo.getUpdatedAt());
        panacheUser.setCreatedAt(bo.getCreatedAt().getValue());

        return panacheUser;
    }

}
