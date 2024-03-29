package com.steafan.cangzhu.api.controller.response;

import com.steafan.cangzhu.api.enums.CZHttpStatus;
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
