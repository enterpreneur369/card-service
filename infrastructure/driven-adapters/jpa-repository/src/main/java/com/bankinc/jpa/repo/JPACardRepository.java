package com.bankinc.jpa.repo;

import com.bankinc.jpa.model.CardEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public interface JPACardRepository extends CrudRepository<CardEntity, Integer>, QueryByExampleExecutor<CardEntity> {
    Optional<CardEntity> findByNumber(String number);
}
