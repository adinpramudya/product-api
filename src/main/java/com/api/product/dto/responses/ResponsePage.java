package com.api.product.dto.responses;

import lombok.*;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePage<T> {
    private T[] dataList;
    private Page<T> dataPage;


}