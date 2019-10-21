package com.github.cundream.springbootbuilding.common.exception;

import com.github.cundream.springbootbuilding.common.controller.ResponseWrapper;
import com.github.cundream.springbootbuilding.common.controller.ResponseWrapperMapper;
import com.github.cundream.springbootbuilding.utils.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
