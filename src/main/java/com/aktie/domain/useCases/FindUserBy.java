package com.aktie.domain.useCases;

import java.util.List;
import java.util.stream.Collectors;

import com.aktie.domain.entities.dto.UserDTO;
import com.aktie.domain.entities.enums.EnumErrorCode;
import com.aktie.domain.entities.mappers.UserMapper;
import com.aktie.domain.entities.vo.QueryFieldInfoVO;
import com.aktie.domain.repositories.UserRepository;
import com.aktie.domain.utils.ListUtil;
import com.aktie.domain.utils.exception.AktieException;

/**
 *
 * @author SRamos
 */
public class FindUserBy {

    private UserRepository userRepository;

    public FindUserBy(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO execute(List<QueryFieldInfoVO> queryFields, boolean throwsException) {
        var userBO = userRepository.findFirstBy(queryFields);

        if (userBO == null && throwsException) {
            var fields = ListUtil.stream(queryFields)
                    .map(QueryFieldInfoVO::getFieldName)
                    .collect(Collectors.joining(", "));

            throw new AktieException(EnumErrorCode.USUARIO_NAO_ENCONTRADO_FILTROS, fields);
        }

        return userBO != null ? UserMapper.toDTO(userBO) : null;
    }

}
