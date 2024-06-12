package com.api.product.converter;

import com.api.product.dto.TransactionDto;
import com.api.product.dto.responses.TransactionResponse;
import com.api.product.entities.Transaction;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionConverter {
    public TransactionDto toDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setProductID(transaction.getProductId());
        transactionDto.setProductName(transaction.getProductName());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setCustomerName(transaction.getCustomerName());
        if (transaction.getStatus().equals("SUCCESS")) {
            transactionDto.setStatus("0");
        } else {
            transactionDto.setStatus("1");
        }
        transactionDto.setTransactionDate(String.valueOf(transaction.getTransactionDate()));
        transactionDto.setCreateBy(transaction.getCreatedBy());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        transactionDto.setCreateOn(Timestamp.valueOf(dateFormat.format(transaction.getCreateOn())));
        return transactionDto;
    }

    public TransactionResponse toDtoResponse(Transaction transaction) {
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setId(transaction.getId());
        transactionResponse.setProductID(transaction.getProductId());
        transactionResponse.setProductName(transaction.getProductName());
        transactionResponse.setAmount(transaction.getAmount());
        transactionResponse.setCustomerName(transaction.getCustomerName());
        if (transaction.getStatus().equals("SUCCESS")) {
            transactionResponse.setStatus("0");
        } else {
            transactionResponse.setStatus("1");
        }
        transactionResponse.setTransactionDate(String.valueOf(transaction.getTransactionDate()));
        transactionResponse.setCreateBy(transaction.getCreatedBy());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        transactionResponse.setCreateOn(dateFormat.format(transaction.getCreateOn()));
        return transactionResponse;
    }

    public List<TransactionDto> toDtos(List<Transaction> transactions) {
        return transactions.stream().map(this::toDto).collect(Collectors.toList());
    } public List<TransactionResponse> toDtoResponses(List<Transaction> transactions) {
        return transactions.stream().map(this::toDtoResponse).collect(Collectors.toList());
    }

    public Transaction toEntity(TransactionDto transactionDto) {
        Transaction entity = new Transaction();
        entity.setProductId(transactionDto.getProductID());
        entity.setProductName(transactionDto.getProductName());
        entity.setAmount(transactionDto.getAmount());
        entity.setCustomerName(transactionDto.getCustomerName());
       entity.setStatus(transactionDto.getStatus().toUpperCase());
        LocalDateTime dateTime = LocalDateTime.parse(transactionDto.getTransactionDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        entity.setTransactionDate(timestamp);
        entity.setCreateOn(new Timestamp(System.currentTimeMillis()));
        entity.setCreatedBy(transactionDto.getCreateBy());
        return entity;
    }

    public Transaction toEntityUpdate(TransactionDto transactionDto, Transaction entity) {
        if (transactionDto.getProductID() != null) {
            entity.setProductId(transactionDto.getProductID());
        }
        if (transactionDto.getProductName() != null) {
            entity.setProductName(transactionDto.getProductName());
        }
        if (transactionDto.getAmount() != null) {
            entity.setAmount(transactionDto.getAmount());
        }
        if (transactionDto.getCustomerName() != null) {
            entity.setCustomerName(transactionDto.getCustomerName());
        }
        if (transactionDto.getStatus() != null) {

            // Atur status ke entity
            entity.setStatus(transactionDto.getStatus().toUpperCase());
        }
        if (transactionDto.getTransactionDate() != null) {
            LocalDateTime dateTime = LocalDateTime.parse(transactionDto.getTransactionDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            Timestamp timestamp = Timestamp.valueOf(dateTime);
            entity.setTransactionDate(timestamp);
        }
        return entity;
    }

}
