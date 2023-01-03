package com.aktie.infra.database.panache.mappers;

import com.aktie.domain.entities.UserBO;
import com.aktie.domain.entities.vo.UserNameVO;
import com.aktie.infra.database.panache.model.PanacheUser;

/**
 *
 * @author SRamos
 */
public class PanacheUserMapper {

    public static UserBO toDomain(PanacheUser panacheUser) {
        var userBO = new UserBO(
                panacheUser.getId(),
                new UserNameVO(panacheUser.getName()),
                panacheUser.getDocument(),
                panacheUser.getCreatedAt(),
                panacheUser.getDisabledAt());

        return userBO;
    }

    public static PanacheUser toEntity(UserBO userBO) {
        var panacheUser = new PanacheUser();

        panacheUser.setName(userBO.getName().getValue());
        panacheUser.setDocument(userBO.getDocument());

        return panacheUser;
    }

}
