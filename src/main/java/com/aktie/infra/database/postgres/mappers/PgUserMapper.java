package com.aktie.infra.database.postgres.mappers;

import com.aktie.domain.entities.UserBO;
import com.aktie.domain.entities.vo.CreatedAtVO;
import com.aktie.domain.entities.vo.UuidVO;
import com.aktie.infra.database.postgres.model.PgUser;

/**
 *
 * @author SRamos
 */
public class PgUserMapper {

    public static UserBO toDomain(PgUser entity) {
        var userBO = new UserBO(
                new UuidVO(entity.getId().toString()),
                entity.getName(),
                entity.getDocument(),
                new CreatedAtVO(entity.getCreatedAt()),
                entity.getUpdatedAt(),
                entity.getDisabledAt());

        return userBO;
    }

    public static PgUser toEntity(UserBO bo) {
        var panacheUser = new PgUser();

        panacheUser.setId(bo.getId().getValue());
        panacheUser.setName(bo.getName());
        panacheUser.setDocument(bo.getDocument());
        panacheUser.setDisabledAt(bo.getDisabledAt());
        panacheUser.setUpdatedAt(bo.getUpdatedAt());
        panacheUser.setCreatedAt(bo.getCreatedAt().getValue());

        return panacheUser;
    }

}
