package com.aktie.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.aktie.domain.entities.dto.UserDTO;
import com.aktie.domain.entities.enums.EnumErrorCode;
import com.aktie.domain.entities.vo.QueryFieldInfoVO;
import com.aktie.domain.useCases.CreateUser;
import com.aktie.domain.useCases.DisableUser;
import com.aktie.domain.useCases.FindUserBy;
import com.aktie.domain.useCases.ListUsersBy;
import com.aktie.domain.useCases.UpdateUserInfo;
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
    public UserDTO create(UserDTO dto) {
        var createUser = new CreateUser(panacheUserRepository);

        return createUser.execute(dto);
    }

    @Transactional
    public UserDTO updateInfo(UserDTO dto, String userId) {
        var updateUserInfo = new UpdateUserInfo(panacheUserRepository);

        return updateUserInfo.execute(dto, userId);
    }

    @Transactional
    public UserDTO disable(String userId) {
        var disableUser = new DisableUser(panacheUserRepository);

        return disableUser.execute(userId);
    }

    public UserDTO findBy(String userId) {
        if (StringUtil.isNullOrEmpty(userId)) {
            throw new AktieException(EnumErrorCode.CAMPO_OBRIGATORIO, "userId");
        }

        var findUserBy = new FindUserBy(panacheUserRepository);
        var queryFieldUserId = new QueryFieldInfoVO("id", Utils.getUUIDfromStr(userId));

        return findUserBy.execute(List.of(queryFieldUserId), true);
    }

    public List<UserDTO> listBy(String document) {
        if (StringUtil.isNullOrEmpty(document)) {
            throw new AktieException(EnumErrorCode.CAMPO_OBRIGATORIO, "document");
        }

        var findAllBy = new ListUsersBy(panacheUserRepository);
        var queryFieldUserId = new QueryFieldInfoVO("document", document);

        return findAllBy.execute(List.of(queryFieldUserId), true);
    }

}
