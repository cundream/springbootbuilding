package com.github.cundream.springbootbuilding.common.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.cundream.springbootbuilding.utils.TimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : Lison
 * @Date: 2019/10/16 17:19
 * @Description:
 */
@Data
@ApiModel
public class ResponseWrapper<T> implements Serializable {

    /**
     * 成功码
     */
    public static final int SUCCESS_CODE = 200;
    /**
     * 成功信息
     */
    public static final String SUCCESS_MESSAGE = "操作成功";
    /**
     * 成功码：无内容
     */
    public static final int SUCCESS_NO_CONTENT_CODE = 204;
    /**
     * 成功信息.
     */
    public static final String SUCCESS_NO_CONTENT_MESSAGE = "无响应数据";


    /**
     * 找不到资源码
     */
    public static final int NOT_FOUND_CODE = 404 ;


    /**
     *
     */
    public static final String NOT_FOUND_MESSAGE="找不到数据";

    /**
     * 错误码
     */
    public static final int ERROR_CODE = 500;
    /**
     * 错误信息
     */
    public static final String ERROR_MESSAGE = "内部异常";
    /**
     * 序列化标识
     */
    private static final long serialVersionUID = 4893280118017319089L;
    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码")
    private int code;
    /**
     * 响应信息
     */
    @ApiModelProperty(value = "响应信息")
    private String message;
    /**
     * 结果数据
     */
    @ApiModelProperty(value = "结果数据")
    private T data;
    /**
     * 响应时间戳
     */
    @ApiModelProperty(value = "响应时间戳")
    private String timestamp = TimeUtil.format(TimeUtil.nowLocalDateTime(), "yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * Instantiates a new wrapper. default code=200
     */
    ResponseWrapper() {
        this(SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    /**
     * Instantiates a new wrapper.
     *
     * @param code    the code
     * @param message the message
     */
    ResponseWrapper(int code, String message) {
        this(code, message, null);
    }

    /**
     * Instantiates a new wrapper.
     *
     * @param code    the code
     * @param message the message
     * @param data    the data
     */
    ResponseWrapper(int code, String message, T data) {
        super();
        this.code(code).message(message).data(data);
    }

    /**
     * Sets the 编号 , 返回自身的引用.
     *
     * @param code the new 编号
     * @return the wrapper
     */
    private ResponseWrapper<T> code(int code) {
        this.setCode(code);
        return this;
    }

    /**
     * Sets the 信息 , 返回自身的引用.
     *
     * @param message the new 信息
     * @return the wrapper
     */
    private ResponseWrapper<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    /**
     * Sets the 结果数据 , 返回自身的引用.
     *
     * @param data the new 结果数据
     * @return the wrapper
     */
    public ResponseWrapper<T> data(T data) {
        this.setData(data);
        return this;
    }

    /**
     * 判断是否成功： 依据 ResponseWrapper.SUCCESS_CODE == this.code
     *
     * @return code =200,true;否则 false.
     */
    @JsonIgnore
    public boolean success() {
        return ResponseWrapper.SUCCESS_CODE == this.code;
    }

    /**
     * 判断是否成功： 依据 ResponseWrapper.SUCCESS_CODE != this.code
     *
     * @return code !=200,true;否则 false.
     */
    @JsonIgnore
    public boolean error() {
        return !success();
    }

}
