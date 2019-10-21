package com.github.cundream.springbootbuilding.common.exception;


import lombok.extern.slf4j.Slf4j;


/**
 * @author : Lison
 * @Date: 2019/10/16 17:40
 * @Description: 系统异常
 */
@Slf4j
public class SystemException extends RuntimeException {

    /**
     * 异常码
     */
    protected int code;

    private static final long serialVersionUID = 3160241586346324994L;

    public SystemException() {
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(int code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
