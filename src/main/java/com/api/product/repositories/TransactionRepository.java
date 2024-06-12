package com.api.product.repositories;

import com.api.product.dto.responses.TransactionResponse;
import com.api.product.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {
    @Query(value = "SELECT * FROM transactions WHERE transaction_date BETWEEN ? and ?", nativeQuery = true)
    List<Transaction> findAllTransactionsByTimestamp(Timestamp startTimestamp, Timestamp endTimestamp);
}
