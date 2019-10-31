package com.github.cundream.springbootbuilding.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.github.cundream.springbootbuilding.common.controller.ResponseWrapperMapper;
import com.github.cundream.springbootbuilding.common.enums.ErrorCodeEnum;
import com.github.cundream.springbootbuilding.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : Lison
 * @Date: 2019/10/28 16:30
 * @Description: 通用工具类
 */
@Slf4j
public class ResponseUtil {


    /**
     * 往 response 写出 json
     *
     * @param response 响应
     * @param codeEnum   状态
     * @param data     返回数据
     */
    public static void renderJson(HttpServletResponse response, ErrorCodeEnum codeEnum, Object data) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(200);


            //  将JSON转为String的时候，忽略null值的时候转成的String存在错误
            response.getWriter()
                    .write(JSONUtil.toJsonStr(new JSONObject(ResponseWrapperMapper.wrap(codeEnum.code(),codeEnum.msg(), data), false)));
        } catch (IOException e) {
            log.error("Response写出JSON异常，", e);
        }
    }

    /**
     * 往 response 写出 json
     *
     * @param response  响应
     * @param exception 异常
     */
    public static void renderJson(HttpServletResponse response, BusinessException exception) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(200);


            //  将JSON转为String的时候，忽略null值的时候转成的String存在错误
            response.getWriter()
                    .write(JSONUtil.toJsonStr(new JSONObject(ResponseWrapperMapper.wrap(exception), false)));
        } catch (IOException e) {
            log.error("Response写出JSON异常，", e);
        }
    }
}
