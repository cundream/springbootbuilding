package com.github.cundream.springbootbuilding.common.exception;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.github.cundream.springbootbuilding.common.controller.ResponseWrapper;
import com.github.cundream.springbootbuilding.common.controller.ResponseWrapperMapper;
import com.github.cundream.springbootbuilding.common.enums.ErrorCodeEnum;
import com.github.cundream.springbootbuilding.utils.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;

/**
 * @author : Lison
 * @Date: 2019/10/16 17:30
 * @Description: 全局的的异常拦截器
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.github.cundream")
public class GlobalExceptionHandler {


    @Value("${spring.application.name}")
    String applicationName;

    @Value("${spring.profiles.active}")
    String profileActive;


    /**
     * 非法参数异常.
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseWrapper illegalArgumentException(IllegalArgumentException e) {
        String message = ExceptionUtil.getFriendlyErrorMsg(e, "非法参数", applicationName, profileActive);
        log.error(message);
        return ResponseWrapperMapper.error("参数异常");
    }

    /**
     * 空指针异常.
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseWrapper nullPointerException(NullPointerException e) {
        String message = ExceptionUtil.getFriendlyErrorMsg(e, "空指针", applicationName, profileActive);
        log.error(message);
        return ResponseWrapperMapper.error("系统空指针异常");
    }

    /**
     * 非法状态异常.
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseWrapper illegalStateException(IllegalStateException e) {
        String message = ExceptionUtil.getFriendlyErrorMsg(e, "非法状态", applicationName, profileActive);
        log.error(message);
        return ResponseWrapperMapper.error("非法状态");
    }

    /**
     * 索引越界异常.
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseWrapper indexOutOfBoundsException(IndexOutOfBoundsException e) {
        String message = ExceptionUtil.getFriendlyErrorMsg(e, "索引越界", applicationName, profileActive);
        log.error(message);
        return ResponseWrapperMapper.error("索引越界异常");
    }

    /**
     * 系统异常.
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(SystemException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseWrapper systemException(SystemException e) {
        String message = ExceptionUtil.getFriendlyErrorMsg(e, "系统", applicationName, profileActive);
        log.error(message);
        return ResponseWrapperMapper.wrap(e.getCode() == 0 ? ResponseWrapper.ERROR_CODE : e.getCode(), "系统未知错误，修复中");
    }

    /**
     * 业务异常.
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseWrapper businessException(BusinessException e) {
        String message = ExceptionUtil.getFriendlyErrorMsg(e, "业务", applicationName, profileActive);
        log.error(message);
        return ResponseWrapperMapper.wrap(e.getCode() == 0 ? ResponseWrapper.ERROR_CODE : e.getCode(), e.getMessage());
    }

    /**
     * 全局异常.
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseWrapper exception(Exception e) {
        String message = ExceptionUtil.getFriendlyErrorMsg(e, "全局", applicationName, profileActive);
        if (e instanceof NoHandlerFoundException) {
            log.error("【全局异常拦截】NoHandlerFoundException: 请求方法 {}, 请求路径 {}", ((NoHandlerFoundException) e).getRequestURL(), ((NoHandlerFoundException) e).getHttpMethod());
            return ofErrorCode(ErrorCodeEnum.REQUEST_NOT_FOUND);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            log.error("【全局异常拦截】HttpRequestMethodNotSupportedException: 当前请求方式 {}, 支持请求方式 {}", ((HttpRequestMethodNotSupportedException) e).getMethod(), JSONUtil.toJsonStr(((HttpRequestMethodNotSupportedException) e).getSupportedHttpMethods()));
            return ofErrorCode(ErrorCodeEnum.HTTP_BAD_METHOD);
        } else if (e instanceof MethodArgumentNotValidException) {
            log.error("【全局异常拦截】MethodArgumentNotValidException", e);
            return ResponseWrapperMapper.wrap(ErrorCodeEnum.BAD_REQUEST.code(), ((MethodArgumentNotValidException) e).getBindingResult()
                    .getAllErrors()
                    .get(0)
                    .getDefaultMessage(), null);
        } else if (e instanceof ConstraintViolationException) {
            log.error("【全局异常拦截】ConstraintViolationException", e);
            return ResponseWrapperMapper.wrap(ErrorCodeEnum.BAD_REQUEST.code(), CollUtil.getFirst(((ConstraintViolationException) e).getConstraintViolations())
                    .getMessage(), null);
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            log.error("【全局异常拦截】MethodArgumentTypeMismatchException: 参数名 {}, 异常信息 {}", ((MethodArgumentTypeMismatchException) e).getName(), ((MethodArgumentTypeMismatchException) e).getMessage());
            return ofErrorCode(ErrorCodeEnum.PARAM_NOT_MATCH);
        } else if (e instanceof HttpMessageNotReadableException) {
            log.error("【全局异常拦截】HttpMessageNotReadableException: 错误信息 {}", ((HttpMessageNotReadableException) e).getMessage());
            return ofErrorCode(ErrorCodeEnum.PARAM_NOT_NULL);
        } else if (e instanceof BadCredentialsException) {
            log.error("【全局异常拦截】BadCredentialsException: 错误信息 {}", e.getMessage());
            return ofErrorCode(ErrorCodeEnum.USERNAME_PASSWORD_ERROR);
        } else if (e instanceof DisabledException) {
            log.error("【全局异常拦截】BadCredentialsException: 错误信息 {}", e.getMessage());
            return ofErrorCode(ErrorCodeEnum.USER_DISABLED);
        }
        log.error(message);
        return ResponseWrapperMapper.error("服务器繁忙.....");
    }

    /**
     * mysql数据库异常.
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseWrapper exception(SQLException e) {
        String message = ExceptionUtil.getFriendlyErrorMsg(e, "数据库异常", applicationName, profileActive);
        log.error(message);
        return ResponseWrapperMapper.error("操作失败，数据异常");
    }

    private static ResponseWrapper ofErrorCode(ErrorCodeEnum errorCodeEnum){
        return ResponseWrapperMapper.wrap(errorCodeEnum.code(),errorCodeEnum.msg());
    }

}
