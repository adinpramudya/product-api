package com.api.product.dto.responses;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStatus {
    private Long id;
    private String name;
}
