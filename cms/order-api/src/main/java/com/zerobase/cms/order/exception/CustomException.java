package com.zerobase.cms.order.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;
    private final int status;
    private static final ObjectMapper mapper = new ObjectMapper();

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getContent());
        this.errorCode = errorCode;
        this.status = errorCode.getHttpStatus().value();
    }
}
