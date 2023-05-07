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

    public static UserBO toDomain(PanacheUser panacheUser) {
        var userBO = new UserBO(
                new UuidVO(panacheUser.getId().toString()),
                panacheUser.getName(),
                panacheUser.getDocument(),
                new CreatedAtVO(panacheUser.getCreatedAt()),
                panacheUser.getDisabledAt());

        return userBO;
    }

    public static PanacheUser toEntity(UserBO userBO) {
        var panacheUser = new PanacheUser();

        panacheUser.setId(userBO.getId().getValue());
        panacheUser.setName(userBO.getName());
        panacheUser.setDocument(userBO.getDocument());
        panacheUser.setDisabledAt(userBO.getDisabledAt());
        panacheUser.setCreatedAt(userBO.getCreatedAt().getValue());

        return panacheUser;
    }

}
