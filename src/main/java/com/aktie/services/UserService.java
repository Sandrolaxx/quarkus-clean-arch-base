package com.aktie.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.aktie.domain.entities.dto.UserDTO;
import com.aktie.domain.entities.enums.EnumDBImpl;
import com.aktie.domain.entities.enums.EnumErrorCode;
import com.aktie.domain.entities.vo.QueryFieldInfoVO;
import com.aktie.domain.usecases.CreateUser;
import com.aktie.domain.usecases.DisableUser;
import com.aktie.domain.usecases.FindUserBy;
import com.aktie.domain.usecases.ListUsersBy;
import com.aktie.domain.usecases.UpdateUserInfo;
import com.aktie.domain.utils.StringUtil;
import com.aktie.domain.utils.Utils;
import com.aktie.domain.utils.exception.AktieException;

/**
 *
 * @author SRamos
 */
@ApplicationScoped
public class UserService extends AbstractService {

    @Transactional
    public UserDTO create(UserDTO dto, EnumDBImpl dbImpl) {
        var repository = dbFactory.getImpl(dbImpl);
        var createUser = new CreateUser(repository);

        return createUser.execute(dto);
    }

    @Transactional
    public UserDTO updateInfo(UserDTO dto, String userId, EnumDBImpl dbImpl) {
        var repository = dbFactory.getImpl(dbImpl);
        var updateUserInfo = new UpdateUserInfo(repository);

        return updateUserInfo.execute(dto, userId);
    }

    @Transactional
    public UserDTO disable(String userId, EnumDBImpl dbImpl) {
        var repository = dbFactory.getImpl(dbImpl);
        var disableUser = new DisableUser(repository);

        return disableUser.execute(userId);
    }

    public UserDTO findBy(String userId, EnumDBImpl dbImpl) {

        if (StringUtil.isNullOrEmpty(userId)) {
            throw new AktieException(EnumErrorCode.CAMPO_OBRIGATORIO, "userId");
        }

        var repository = dbFactory.getImpl(dbImpl);
        var findUserBy = new FindUserBy(repository);
        var queryFieldUserId = new QueryFieldInfoVO("id", Utils.getUUIDfromStr(userId));

        return findUserBy.execute(List.of(queryFieldUserId), true);
    }

    public List<UserDTO> listBy(String document, EnumDBImpl dbImpl) {

        if (StringUtil.isNullOrEmpty(document)) {
            throw new AktieException(EnumErrorCode.CAMPO_OBRIGATORIO, "document");
        }

        var repository = dbFactory.getImpl(dbImpl);
        var findAllBy = new ListUsersBy(repository);
        var queryFieldUserId = new QueryFieldInfoVO("document", document);

        return findAllBy.execute(List.of(queryFieldUserId), true);
    }

}
