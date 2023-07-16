package com.aktie.infra.database.panache.repositories;

import java.util.List;
import java.util.stream.Collectors;

import com.aktie.domain.entities.UserBO;
import com.aktie.domain.entities.vo.QueryFieldInfoVO;
import com.aktie.domain.repositories.UserRepository;
import com.aktie.domain.utils.ListUtil;
import com.aktie.domain.utils.StringUtil;
import com.aktie.infra.database.panache.mappers.PanacheUserMapper;
import com.aktie.infra.database.panache.model.PanacheUser;

/**
 *
 * @author SRamos
 */
public class PanacheUserRepository implements UserRepository {

    @Override
    public UserBO create(UserBO userDTO) {
        var panacheUser = PanacheUserMapper.toEntity(userDTO);

        panacheUser.persist();

        return PanacheUserMapper.toDomain(panacheUser);
    }

    @Override
    public UserBO findFirstBy(List<QueryFieldInfoVO> queryFieldsInfoVO) {
        return ListUtil.first(findAllBy(queryFieldsInfoVO));
    }

    @Override
    public List<UserBO> findAllBy(List<QueryFieldInfoVO> queryFieldsInfoVO) {
        var params = ListUtil.stream(queryFieldsInfoVO)
                .collect(Collectors.toMap(item -> item.getFieldName().replace(".", ""),
                        QueryFieldInfoVO::getFieldValue));

        var query = new StringBuilder();

        queryFieldsInfoVO.stream().forEach(val -> {
            if (StringUtil.isNullOrEmpty(query.toString())) {
                query.append(val.getFieldName().concat(" = :").concat(val.getFieldName().replace(".", "")));
            } else {
                query.append(
                        " AND ".concat(val.getFieldName().concat(" = :").concat(val.getFieldName().replace(".", ""))));
            }
        });

        return ListUtil.stream(PanacheUser.list(query.toString(), params))
                .map(value -> PanacheUserMapper.toDomain(((PanacheUser) value)))
                .collect(Collectors.toList());
    }

}
