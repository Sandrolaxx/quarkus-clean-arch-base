package com.aktie.infra.database.panache.repositories;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import com.aktie.domain.entities.UserBO;
import com.aktie.domain.entities.vo.QueryFieldInfoVO;
import com.aktie.domain.repositories.IUserRepository;
import com.aktie.domain.utils.ListUtil;
import com.aktie.domain.utils.StringUtil;
import com.aktie.infra.database.panache.mappers.PanacheUserMapper;
import com.aktie.infra.database.panache.model.PanacheUser;

/**
 *
 * @author SRamos
 */
@ApplicationScoped
public class PanacheUserRepository implements IUserRepository {

    @Override
    public UserBO create(UserBO bo) {
        var panacheUser = PanacheUserMapper.toEntity(bo);

        panacheUser.persist();

        return PanacheUserMapper.toDomain(panacheUser);
    }

    @Override
    public UserBO merge(UserBO bo) {
        var entity = PanacheUserMapper.toEntity(bo);

        PanacheUser.getEntityManager().merge(entity);

        return bo;
    }

    @Override
    public UserBO findFirstBy(List<QueryFieldInfoVO> queryFieldsInfoVO) {
        return ListUtil.first(findAllBy(queryFieldsInfoVO));
    }

    @Override
    public List<UserBO> findAllBy(List<QueryFieldInfoVO> queryFieldsInfoVO) {
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

        return ListUtil.stream(PanacheUser.list(query.toString(), params))
                .map(value -> PanacheUserMapper.toDomain(((PanacheUser) value)))
                .collect(Collectors.toList());
    }

}
