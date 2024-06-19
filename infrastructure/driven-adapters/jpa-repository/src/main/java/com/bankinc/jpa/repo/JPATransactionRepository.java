package com.bankinc.jpa.repo;

import com.bankinc.jpa.model.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPATransactionRepository extends CrudRepository<TransactionEntity, String>,
        QueryByExampleExecutor<TransactionEntity> {
}
