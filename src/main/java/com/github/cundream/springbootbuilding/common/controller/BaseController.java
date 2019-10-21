package com.github.cundream.springbootbuilding.common.controller;

import org.springframework.util.ObjectUtils;

/**
 * @author : Lison
 * @Date: 2019/10/16 17:19
 * @Description:
 */
public class BaseController {
    /**
     * Handle result wrapper.
     *
     * @param <T>    the type parameter
     * @param result the result
     * @return the wrapper
     */
    protected <T> ResponseWrapper<T> handleResult(T result) {
        int state = getState(result);

        switch (state) {
            case -1:
                return ResponseWrapperMapper.wrap(ResponseWrapper.ERROR_CODE, "操作失败", result);
            case 0:
                return ResponseWrapperMapper.wrap(ResponseWrapper.SUCCESS_CODE, "无响应数据", result);
            default:
                return ResponseWrapperMapper.wrap(ResponseWrapper.SUCCESS_CODE, "操作成功", result);
        }
    }


    /**
     *
     * @param result
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    protected <T> ResponseWrapper<T> handleResult(T result,int code,String message) {
        return ResponseWrapperMapper.wrap(code, message, result);
    }


    /**
     * Handle result wrapper.
     *
     * @param <E>      the type parameter
     * @param result   the result
     * @param errorMsg the error msg
     * @return the wrapper
     */
    protected <E> ResponseWrapper<E> handleResult(E result, String errorMsg) {
        int state = getState(result);

        switch (state) {
            case -1:
                return ResponseWrapperMapper.wrap(ResponseWrapper.ERROR_CODE, errorMsg, result);
            case 0:
                return ResponseWrapperMapper.wrap(ResponseWrapper.SUCCESS_CODE, "无响应数据", result);
            default:
                return ResponseWrapperMapper.wrap(ResponseWrapper.SUCCESS_CODE, "操作成功", result);
        }
    }

    /**
     * 获取状态码：-1错误、0无数据、1成功
     *
     * @param result
     * @return
     */
    private int getState(Object result) {
        if (result instanceof Boolean) {
            boolean flag = (Boolean) result;
            return flag == true ? 1 : -1;
        } else {
            boolean flag = !ObjectUtils.isEmpty(result);
            return flag == true ? 1 : 0;
        }
    }
}
