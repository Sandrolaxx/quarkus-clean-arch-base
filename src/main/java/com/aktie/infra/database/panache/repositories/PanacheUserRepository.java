package com.aktie.infra.database.panache.repositories;

import com.aktie.domain.entities.UserBO;
import com.aktie.domain.repositories.UserRepository;
import com.aktie.infra.database.panache.mappers.PanacheUserMapper;
import com.aktie.infra.database.panache.model.PanacheUser;

/**
 *
 * @author SRamos
 */
public class PanacheUserRepository implements UserRepository {

    @Override
    public UserBO createUser(UserBO userDTO) {
        var panacheUser = PanacheUserMapper.toEntity(userDTO);

        panacheUser.persist();

        return PanacheUserMapper.toDomain(panacheUser);
    }

    @Override
    public UserBO findByDocument(String document) {

        PanacheUser searchedUser = PanacheUser.find("document", document).firstResult();

        if (searchedUser != null) {
            return PanacheUserMapper.toDomain(searchedUser);
        }

        return null;
    }

}
