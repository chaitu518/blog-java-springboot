package com.chaitu.blogappspringboot.common.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String error;
}
