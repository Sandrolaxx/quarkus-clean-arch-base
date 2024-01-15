package com.aktie.domain.repositories;

import java.util.List;

import com.aktie.domain.entities.UserBO;
import com.aktie.domain.entities.enums.EnumDBImpl;
import com.aktie.domain.entities.vo.QueryFieldInfoVO;

/**
 *
 * @author SRamos
 */
public interface IUserRepository {

    UserBO create(UserBO bo);

    UserBO merge(UserBO bo);

    List<UserBO> findAllBy(List<QueryFieldInfoVO> queryFieldInfo);

    UserBO findFirstBy(List<QueryFieldInfoVO> queryFieldInfo);

    EnumDBImpl getType();

}
