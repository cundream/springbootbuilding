package com.github.cundream.springbootbuilding.common.controller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @author : Lison
 * @Date: 2019/10/16 17:18
 * @Description: 响应映射器
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseWrapperMapper {

    /**
     * Wrap.
     *
     * @param <E>     the element type
     * @param code    the code
     * @param message the message
     * @param o       the o
     * @return the wrapper
     */
    public static <E> ResponseWrapper<E> wrap(int code, String message, E o) {
        return new ResponseWrapper<>(code, message, o);
    }

    /**
     * Wrap.
     *
     * @param <E>     the element type
     * @param code    the code
     * @param message the message
     * @return the wrapper
     */
    public static <E> ResponseWrapper<E> wrap(int code, String message) {
        return wrap(code, message, null);
    }

    /**
     * Wrap.
     *
     * @param <E>  the element type
     * @param code the code
     * @return the wrapper
     */
    public static <E> ResponseWrapper<E> wrap(int code) {
        return wrap(code, null);
    }

    /**
     * Wrap.
     *
     * @param <E> the element type
     * @param e   the e
     * @return the wrapper
     */
    public static <E> ResponseWrapper<E> wrap(Exception e) {
        String logMessage = e.getMessage();
        log.error(logMessage);
        return new ResponseWrapper<>(ResponseWrapper.ERROR_CODE, e.getMessage());
    }

    /**
     * Un ResponseWrapper.
     *
     * @param <E>         the element type
     * @param ResponseWrapper the ResponseWrapper
     * @return the e
     */
    public static <E> E unWrap(ResponseWrapper<E> ResponseWrapper) {
        return ResponseWrapper.getData();
    }

    /**
     * Wrap ERROR. code=500
     *
     * @param <E> the element type
     * @return the wrapper
     */
    public static <E> ResponseWrapper<E> error() {
        String logMessage = ResponseWrapper.ERROR_MESSAGE;
        log.error(logMessage);
        return wrap(ResponseWrapper.ERROR_CODE, logMessage);
    }


    /**
     * Error wrapper.
     *
     * @param <E>     the type parameter
     * @param message the message
     * @return the wrapper
     */
    public static <E> ResponseWrapper<E> error(String message) {
        String logMessage = StringUtils.isEmpty(message) ? ResponseWrapper.ERROR_MESSAGE : message;
        log.error(logMessage);
        return wrap(ResponseWrapper.ERROR_CODE, logMessage);
    }

    /**
     * Wrap SUCCESS. code=200
     *
     * @param <E> the element type
     * @return the wrapper
     */
    public static <E> ResponseWrapper<E> ok() {
        return new ResponseWrapper<>();
    }

    /**
     * Ok wrapper.
     *
     * @param <E> the type parameter
     * @param o   the o
     * @return the wrapper
     */
    public static <E> ResponseWrapper<E> ok(E o) {
        return new ResponseWrapper<>(ResponseWrapper.SUCCESS_CODE, ResponseWrapper.SUCCESS_MESSAGE, o);
    }
}
