package com.steafan.cangzhu.controller.response;

import com.steafan.cangzhu.enums.CZHttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CZResultException extends RuntimeException {
    private final int code;
    private final String msg;


    public CZResultException(CZHttpStatus statusCode) {
        this.code = statusCode.code;
        this.msg = statusCode.message;
    }
}
