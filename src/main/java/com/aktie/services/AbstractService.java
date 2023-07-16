package com.aktie.services;

import javax.inject.Inject;

import com.aktie.infra.database.panache.repositories.PanacheUserRepository;

public abstract class AbstractService {

    @Inject
    PanacheUserRepository panacheUserRepository;

}
