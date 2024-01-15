package com.aktie.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import com.aktie.domain.entities.enums.EnumDBImpl;
import com.aktie.domain.entities.enums.EnumErrorCode;
import com.aktie.domain.repositories.IUserRepository;
import com.aktie.domain.utils.exception.AktieException;

import io.quarkus.arc.All;

/**
 *
 * @author SRamos
 */
@ApplicationScoped
public class DbFactory {

    @All
    private List<IUserRepository> implementations;

    private static final Map<EnumDBImpl, IUserRepository> serviceCache = new HashMap<>();

    @PostConstruct
    public void init() {
        for (IUserRepository impl : implementations) {
            serviceCache.put(impl.getType(), impl);
        }
    }

    public IUserRepository getImpl(EnumDBImpl impl) {
        IUserRepository repository = serviceCache.get(impl);

        if (repository == null) {
            throw new AktieException(EnumErrorCode.CAMPO_OBRIGATORIO, "dbImpl");
        }

        return repository;
    }
}
