package com.aktie.domain.repositories;

import com.aktie.domain.entities.UserBO;
import com.aktie.presentation.dto.UserDTO;

/**
 *
 * @author SRamos
 */
public interface UserRepository {
    UserBO createUser(UserDTO userDTO);

    UserBO findByDocument(String document);
}
