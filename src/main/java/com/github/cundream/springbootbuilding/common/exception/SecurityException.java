package com.github.cundream.springbootbuilding.common.exception;

import com.github.cundream.springbootbuilding.common.enums.ErrorCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class SecurityException extends BusinessException {
    public SecurityException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum);
    }

    public SecurityException(ErrorCodeEnum errorCodeEnum, Object data) {
        super(errorCodeEnum, data);
    }

    public SecurityException(Integer code, String message) {
        super(code, message);
    }

    public SecurityException(Integer code, String message, Object data) {
        super(code, message, data);
    }
}
