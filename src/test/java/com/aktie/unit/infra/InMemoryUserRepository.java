package com.aktie.unit.infra;

import java.util.ArrayList;
import java.util.List;

import com.aktie.domain.entities.UserBO;
import com.aktie.domain.entities.vo.QueryFieldInfoVO;
import com.aktie.domain.repositories.IUserRepository;
import com.aktie.domain.utils.ListUtil;

public class InMemoryUserRepository implements IUserRepository {

    List<UserBO> users = new ArrayList<>();

    @Override
    public UserBO create(UserBO bo) {
        users.add(bo);

        return bo;
    }

    @Override
    public UserBO merge(UserBO bo) {
        return bo;
    }

    @Override
    public List<UserBO> findAllBy(List<QueryFieldInfoVO> queryFieldInfo) {
        List<UserBO> findedUsers = new ArrayList<>();

        for (QueryFieldInfoVO queryFieldInfoVO : queryFieldInfo) {
            try {
                for (UserBO userBO : users) {
                    var field = userBO.getClass().getDeclaredField(queryFieldInfoVO.getFieldName());
                    field.setAccessible(true);

                    if (field.get(userBO).equals(queryFieldInfoVO.getFieldValue())) {
                        findedUsers.add(userBO);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return findedUsers;
    }

    @Override
    public UserBO findFirstBy(List<QueryFieldInfoVO> queryFieldInfo) {
        return ListUtil.first(findAllBy(queryFieldInfo));
    }

}
