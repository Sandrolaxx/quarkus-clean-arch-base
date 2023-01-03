package com.aktie.domain.repositories;

import com.aktie.domain.entities.UserBO;

/**
 *
 * @author SRamos
 */
public interface UserRepository {
    UserBO createUser(UserBO userBO);

    UserBO findByDocument(String document);
}
