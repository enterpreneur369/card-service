package com.bankinc.jpa.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

public class UUIDGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
        return UUID.randomUUID().toString();
    }
}