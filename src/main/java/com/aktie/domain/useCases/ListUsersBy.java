package com.aktie.domain.useCases;

import java.util.List;
import java.util.stream.Collectors;

import com.aktie.domain.entities.dto.UserDTO;
import com.aktie.domain.entities.enums.EnumErrorCode;
import com.aktie.domain.entities.mappers.UserMapper;
import com.aktie.domain.entities.vo.QueryFieldInfoVO;
import com.aktie.domain.repositories.IUserRepository;
import com.aktie.domain.utils.ListUtil;
import com.aktie.domain.utils.exception.AktieException;

/**
 *
 * @author SRamos
 */
public class ListUsersBy {

    private IUserRepository userRepository;

    public ListUsersBy(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> execute(List<QueryFieldInfoVO> queryFields, boolean throwsException) {
        var usersBO = userRepository.findAllBy(queryFields);

        if (ListUtil.isNullOrEmpty(usersBO) && throwsException) {
            var fields = ListUtil.stream(queryFields)
                    .map(QueryFieldInfoVO::getFieldName)
                    .collect(Collectors.joining(", "));

            throw new AktieException(EnumErrorCode.USUARIO_NAO_ENCONTRADO_FILTROS, fields);
        }

        return ListUtil.stream(usersBO)
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

}
