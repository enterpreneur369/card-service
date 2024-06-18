package com.bankinc.jpa.repo;

import com.bankinc.jpa.model.CardEntity;
import com.bankinc.jpa.model.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPAClientRepository extends CrudRepository<ClientEntity, Integer>, QueryByExampleExecutor<ClientEntity> {
}
