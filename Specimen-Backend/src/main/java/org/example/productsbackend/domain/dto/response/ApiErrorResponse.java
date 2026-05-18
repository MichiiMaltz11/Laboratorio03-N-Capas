package org.example.productsbackend.domain.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiErrorResponse {
    private String message;
    private int status;
    private LocalDateTime time;
    private String uri;
}
