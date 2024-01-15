package com.aktie.infra.database.mongo.repositories;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import com.aktie.domain.entities.UserBO;
import com.aktie.domain.entities.vo.QueryFieldInfoVO;
import com.aktie.domain.repositories.IUserRepository;
import com.aktie.domain.utils.ListUtil;
import com.aktie.domain.utils.StringUtil;
import com.aktie.infra.database.mongo.mappers.MongoUserMapper;
import com.aktie.infra.database.mongo.model.MongoUser;

/**
 *
 * @author SRamos
 */
@ApplicationScoped
public class MongoUserRepository implements IUserRepository {

    @Override
    public UserBO create(UserBO bo) {
        var mongoUser = MongoUserMapper.toEntity(bo);

        mongoUser.persist();

        return MongoUserMapper.toDomain(mongoUser);
    }

    @Override
    public UserBO merge(UserBO bo) {
        var entity = MongoUserMapper.toEntity(bo);

        entity.update();

        return bo;
    }

    @Override
    public UserBO findFirstBy(List<QueryFieldInfoVO> queryFieldsInfoVO) {
        return ListUtil.first(findAllBy(queryFieldsInfoVO));
    }

    @Override
    public List<UserBO> findAllBy(List<QueryFieldInfoVO> queryFieldsInfoVO) {
        var qfUUID = queryFieldsInfoVO.stream()
                .filter(item -> item.getFieldValue() instanceof UUID)
                .findFirst();

        if (qfUUID.isPresent()) {
            queryFieldsInfoVO = queryFieldsInfoVO.stream()
                    .filter(item ->  item.getFieldValue() instanceof UUID == false)
                    .collect(Collectors.toList());
            var newQf = new QueryFieldInfoVO("_id", qfUUID.get().getFieldValue().toString());

            queryFieldsInfoVO.add(newQf);
        }

        var params = ListUtil.stream(queryFieldsInfoVO)
                .filter(item -> item.getFieldValue() != null)
                .collect(Collectors.toMap(
                        item -> StringUtil.replaceDot(item.getFieldName()),
                        QueryFieldInfoVO::getFieldValue));

        var query = new StringBuilder();

        queryFieldsInfoVO.stream().forEach(val -> {
            var formatedFieldName = val.getFieldValue() != null
                    ? " = :".concat(StringUtil.replaceDot(val.getFieldName()))
                    : " is NULL";

            if (StringUtil.isNullOrEmpty(query.toString())) {
                query.append(val.getFieldName().concat(formatedFieldName));
            } else {
                query.append(" AND ".concat(val.getFieldName().concat(formatedFieldName)));
            }
        });

        return ListUtil.stream(MongoUser.list(query.toString(), params))
                .map(value -> MongoUserMapper.toDomain(((MongoUser) value)))
                .collect(Collectors.toList());
    }

}
