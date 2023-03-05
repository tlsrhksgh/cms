package com.zerobase.cms.order.exception;

import com.zerobase.cms.order.exception.CustomException.CustomExceptionResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionAdvice {
    @ExceptionHandler({CustomException.class})
    public ResponseEntity<CustomException.CustomExceptionResponse> exceptionHandler(
        HttpServletRequest req, final CustomException exception) {
        return ResponseEntity
            .status(exception.getStatus())
            .body(CustomException.CustomExceptionResponse.builder()
                .message(exception.getMessage())
                .code(exception.getErrorCode().name())
                .status(exception.getStatus())
                .build());
    }
}
