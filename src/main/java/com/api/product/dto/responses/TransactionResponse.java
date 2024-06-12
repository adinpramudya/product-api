package com.api.product.dto.responses;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class TransactionResponse {
    private Long id;
    private Long productID;
    private String productName;
    private BigDecimal amount;
    private String customerName;
    private String status;
    private String transactionDate;
    private String createBy;
    private String createOn;
}
