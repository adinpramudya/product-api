package com.api.product.services.Impl;

import com.api.product.converter.TransactionConverter;
import com.api.product.dto.TransactionDto;
import com.api.product.dto.responses.ResponsePage;
import com.api.product.dto.responses.TransactionResponse;
import com.api.product.entities.Transaction;
import com.api.product.exceptions.ClientException;
import com.api.product.exceptions.NotFoundException;
import com.api.product.repositories.TransactionRepository;
import com.api.product.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionConverter transactionConverter;


    @Override
    public List<TransactionResponse> findAllTransaction(Timestamp startDate, Timestamp endDate) throws ClientException {
        List<Transaction> transactions = new ArrayList<>();
        if(startDate != null && endDate != null){
            transactions =   transactionRepository.findAllTransactionsByTimestamp(startDate,endDate);
        }else{
            transactions =   transactionRepository.findAll();
        }

        return transactionConverter.toDtoResponses(transactions);
    }

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) throws ClientException {
        Transaction transaction = transactionConverter.toEntity(transactionDto);
        transactionRepository.save(transaction);
        return transactionConverter.toDto(transaction);
    }

    @Override
    public TransactionDto updateTransaction(Long id, TransactionDto transactionDto) throws ClientException, NotFoundException {
        if (!transactionRepository.existsById(transactionDto.getId())) {
            throw new NotFoundException("Cannot find Transaction with id " + transactionDto.getId());
        }
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        transactionRepository.save(transactionConverter.toEntityUpdate(transactionDto, transaction));
        return transactionConverter.toDto(transaction);

    }

    @Override
    public TransactionDto findById(Long id) throws ClientException, NotFoundException {
        // validation
        if(id == null){
            throw new ClientException("ID Kosong Mohon Masukkan ID");
        }

        // process
        Transaction transaction = transactionRepository.findById(id).orElse(null);
       if(transaction == null){
           throw new NotFoundException("Tidak Dapat Menemukan Transaksi");
       }

        return transactionConverter.toDto(transaction);
    }

    @Override
    public TransactionDto deleteTransaction(Long id) throws ClientException, NotFoundException {
        if(id == null){
            throw new ClientException("ID Kosong Mohon Masukkan ID");
        }

        if (!transactionRepository.existsById(id)) {
            throw new NotFoundException("Cannot find Transaction with id :" + id);
        }
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if(transaction == null){
            throw new NotFoundException("Tidak Dapat Menemukan Transaksi");
        }

        transactionRepository.delete(transaction);
        return transactionConverter.toDto(transaction);
    }


}
