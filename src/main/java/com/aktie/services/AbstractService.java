package com.aktie.services;

import javax.inject.Inject;

import com.aktie.infra.database.mongo.repositories.MongoUserRepository;
import com.aktie.infra.database.postgres.repositories.PgUserRepository;

public abstract class AbstractService {

    @Inject
    PgUserRepository pgUserRepository;

    @Inject
    MongoUserRepository mongoUserRepository;

    @Inject
    DbFactory dbFactory;

}
