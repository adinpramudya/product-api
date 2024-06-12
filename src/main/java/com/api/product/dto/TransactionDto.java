package com.api.product.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class TransactionDto {
    private Long id;
    private Long productID;
    private String productName;
    private BigDecimal amount;
    private String customerName;
    private String status;
    private String transactionDate;
    private String createBy;
    private Timestamp createOn;
}
