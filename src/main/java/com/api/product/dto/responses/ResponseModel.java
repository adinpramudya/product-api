package com.api.product.dto.responses;

import com.api.product.models.StatusModel;
import lombok.*;

import java.util.List;
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel<T> {
    private T data;
    private List<StatusModel> status;
}
