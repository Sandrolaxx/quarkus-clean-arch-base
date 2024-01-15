package com.aktie.infra.database.mongo.mappers;

import com.aktie.domain.entities.UserBO;
import com.aktie.domain.entities.vo.CreatedAtVO;
import com.aktie.domain.entities.vo.UuidVO;
import com.aktie.infra.database.mongo.model.MongoUser;

/**
 *
 * @author SRamos
 */
public class MongoUserMapper {

    public static UserBO toDomain(MongoUser entity) {
        var userBO = new UserBO(
                new UuidVO(entity.getId()),
                entity.getName(),
                entity.getDocument(),
                new CreatedAtVO(entity.getCreatedAt()),
                entity.getUpdatedAt(),
                entity.getDisabledAt());

        return userBO;
    }

    public static MongoUser toEntity(UserBO bo) {
        var mongouser = new MongoUser();

        mongouser.setId(bo.getId().getValue().toString());
        mongouser.setName(bo.getName());
        mongouser.setDocument(bo.getDocument());
        mongouser.setDisabledAt(bo.getDisabledAt());
        mongouser.setUpdatedAt(bo.getUpdatedAt());
        mongouser.setCreatedAt(bo.getCreatedAt().getValue());

        return mongouser;
    }

}
