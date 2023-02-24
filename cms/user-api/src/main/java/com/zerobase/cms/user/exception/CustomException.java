package com.zerobase.cms.user.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getContent());
        this.errorCode = errorCode;
    }
}
