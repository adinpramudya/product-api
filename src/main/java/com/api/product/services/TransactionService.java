package com.api.product.services;

import com.api.product.dto.TransactionDto;
import com.api.product.dto.responses.ResponsePage;
import com.api.product.dto.responses.TransactionResponse;
import com.api.product.exceptions.ClientException;
import com.api.product.exceptions.NotFoundException;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    List<TransactionResponse> findAllTransaction(Timestamp startDate, Timestamp endDate) throws ClientException;

    TransactionDto createTransaction(TransactionDto transactionDto) throws ClientException;

    TransactionDto updateTransaction(Long id, TransactionDto transactionDto) throws ClientException, NotFoundException;

    TransactionDto findById(Long id) throws ClientException, NotFoundException;

    TransactionDto deleteTransaction(Long id) throws ClientException, NotFoundException;

}
