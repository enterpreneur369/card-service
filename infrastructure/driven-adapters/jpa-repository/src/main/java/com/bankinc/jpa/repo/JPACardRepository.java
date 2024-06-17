package com.bankinc.jpa.repo;

import com.bankinc.jpa.model.CardEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPACardRepository extends CrudRepository<CardEntity, Integer>, QueryByExampleExecutor<CardEntity> {
}
