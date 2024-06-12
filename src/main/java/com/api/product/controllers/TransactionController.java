package com.api.product.controllers;

import com.api.product.constants.ApiConstant;
import com.api.product.dto.TransactionDto;
import com.api.product.dto.responses.ResponseModel;
import com.api.product.dto.responses.TransactionResponse;
import com.api.product.exceptions.ClientException;
import com.api.product.exceptions.NotFoundException;
import com.api.product.models.StatusModel;
import com.api.product.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(ApiConstant.MASTER_API)
@CrossOrigin(origins = "http://localhost:5173")
public class TransactionController {
    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);
    @Autowired
    TransactionService transactionService;

    @GetMapping(value = ApiConstant.TRANSACTION)
    public ResponseEntity<ResponseModel<List<TransactionResponse>>> getAllTransactionController(  @RequestParam(name = "startDate") String startDateString,
                                                                                                  @RequestParam(name = "endDate") String endDateString) {
        List<StatusModel> statuses = new ArrayList<>();
        try {
            statuses.add(new StatusModel(0, "SUCCESS"));
            statuses.add(new StatusModel(1, "FAILED"));
            // Parsing startDateString and endDateString with format yyyy-MM-dd
            LocalDate startDate = LocalDate.parse(startDateString);
            LocalDate endDate = LocalDate.parse(endDateString);

            // Convert LocalDate to LocalDateTime with time set to start of day (00:00:00) and end of day (23:59:59)
            LocalDateTime startDateTime = startDate.atTime(LocalTime.MIN);
            LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);

            // Convert LocalDateTime to Timestamp
            Timestamp startTimestamp = Timestamp.valueOf(startDateTime);
            Timestamp endTimestamp = Timestamp.valueOf(endDateTime);

            // Call service method to retrieve transactions by timestamp
            List<TransactionResponse> responsePage = transactionService.findAllTransaction(startTimestamp, endTimestamp);
            return new ResponseEntity<>(ResponseModel.<List<TransactionResponse>>builder().data(responsePage).status(statuses).build(), HttpStatus.OK);
        } catch (ClientException e) {
            return new ResponseEntity<>(ResponseModel.<List<TransactionResponse>>builder().data(null).status(statuses)

                    .build(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Terjadi kesalahan ", e);
            return new ResponseEntity<>(ResponseModel.<List<TransactionResponse>>builder().data(null).status(statuses).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = ApiConstant.TRANSACTION)
    public ResponseEntity<ResponseModel> createTransactionController(@RequestBody TransactionDto transactionDto) {
        List<StatusModel> statuses = new ArrayList<>();
        try {
            statuses.add(new StatusModel(0, "SUCCESS"));
            statuses.add(new StatusModel(1, "FAILED"));
            TransactionDto transaction = transactionService.createTransaction(transactionDto);
            return new ResponseEntity<>(ResponseModel.<TransactionDto>builder().data(transaction).status(statuses).build(), HttpStatus.OK);
        } catch (ClientException e) {
            return new ResponseEntity<>(ResponseModel.<TransactionDto>builder().data(null).status(statuses).build(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Terjadi kesalahan ", e);
            return new ResponseEntity<>(ResponseModel.<TransactionDto>builder().data(null).status(statuses).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = ApiConstant.TRANSACTION + "/{id}")
    public ResponseEntity<ResponseModel> findByIdTransactionController(@PathVariable Long id) {
        List<StatusModel> statuses = new ArrayList<>();
        try {
            statuses.add(new StatusModel(0, "SUCCESS"));
            statuses.add(new StatusModel(1, "FAILED"));
            TransactionDto transactionDto = transactionService.findById(id);
            return new ResponseEntity<>(ResponseModel.<TransactionDto>builder().data(transactionDto).status(statuses).build(), HttpStatus.OK);
        } catch (ClientException e) {
            return new ResponseEntity<>(ResponseModel.<TransactionDto>builder().data(null).status(statuses).build(), HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(ResponseModel.<TransactionDto>builder().data(null).status(statuses).build(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Terjadi kesalahan ", e);
            return new ResponseEntity<>(ResponseModel.<TransactionDto>builder().data(null).status(statuses).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = ApiConstant.TRANSACTION + "/{id}")
    public ResponseEntity<ResponseModel<?>> updateTransactionController(@PathVariable Long id, @RequestBody TransactionDto transactionDto) {
        List<StatusModel> statuses = new ArrayList<>();
        try {
            statuses.add(new StatusModel(0, "SUCCESS"));
            statuses.add(new StatusModel(1, "FAILED"));
            TransactionDto transaction = transactionService.updateTransaction(id, transactionDto);
            return new ResponseEntity<>(ResponseModel.<TransactionDto>builder().data(transaction).status(statuses).build(), HttpStatus.OK);
        } catch (ClientException e) {
            return new ResponseEntity<>(ResponseModel.<TransactionDto>builder().data(null).status(statuses).build(), HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(ResponseModel.<TransactionDto>builder().data(null).status(statuses).build(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Terjadi kesalahan ", e);
            return new ResponseEntity<>(ResponseModel.<TransactionDto>builder().data(null).status(statuses).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = ApiConstant.TRANSACTION + "/{id}")
    public ResponseEntity<ResponseModel<?>> deleteTransactionController(@PathVariable Long id) {
        List<StatusModel> statuses = new ArrayList<>();
        try {
            statuses.add(new StatusModel(0, "SUCCESS"));
            statuses.add(new StatusModel(1, "FAILED"));
            TransactionDto transactionDto = transactionService.deleteTransaction(id);
            return new ResponseEntity<>(ResponseModel.<TransactionDto>builder().data(transactionDto).status(statuses).build(), HttpStatus.OK);
        } catch (ClientException e) {
            return new ResponseEntity<>(ResponseModel.<TransactionDto>builder().data(null).status(statuses).build(), HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(ResponseModel.<TransactionDto>builder().data(null).status(statuses).build(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Terjadi kesalahan ", e);
            return new ResponseEntity<>(ResponseModel.<TransactionDto>builder().data(null).status(statuses).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
