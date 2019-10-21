package com.github.cundream.springbootbuilding.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;



/**
 * @author : Lison
 * @Date: 2019/10/16 16:12
 * @Description:异常消息工具类
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionUtil {

    /**
     * 获取友好的错误信息
     *
     * @param e
     * @param exceptionType
     * @return
     */
    public static String getFriendlyErrorMsg(Exception e, String exceptionType, String applicationName, String profileActive) {
        String message = e.getMessage();
        boolean isContainsKeyWord = !StringUtils.isEmpty(message) && message.contains("错误类型:") && message.contains("错误信息:");

        if (isContainsKeyWord) {
            return message;

        } else {
            if (profileActive.toUpperCase().startsWith("PROD")) {

                StackTraceElement[] stackTraceElements = e.getStackTrace();

                StringBuilder sb = new StringBuilder();
                sb.append("错误类型: [").append(exceptionType);
                sb.append("], 错误信息: [").append(e.getMessage());

                for(StackTraceElement stackTraceElement : stackTraceElements){
                    sb.append(stackTraceElement.toString()).append("\n");
                }

                sb.append("]");
                return sb.toString();

            } else {

                StackTraceElement[] stackTraceElements = e.getStackTrace();
                StringBuilder sb = new StringBuilder();
                sb.append("报错应用: [").append(applicationName);
                sb.append("], 当前配置: [").append(profileActive);
                sb.append("], 错误类型: [").append(exceptionType);
                sb.append("], 错误信息: [").append(e.getMessage());

                for(StackTraceElement stackTraceElement : stackTraceElements){
                    sb.append(stackTraceElement.toString()).append("\n");
                }
                sb.append("]");
                return sb.toString();
            }
        }

    }

    /**
     * 获取简单异常信息
     *
     * @param e
     * @return
     */
    public static String getSimpleExceptionMsg(Exception e) {
        return String.valueOf(getExceptionMsg(e, false));
    }

    /**
     * 获取完整异常信息
     *
     * @param e
     * @return
     */
    public static String getFullExceptionMsg(Exception e) {
        return String.valueOf(getExceptionMsg(e, true));
    }

    /**
     * 获取异常信息
     *
     * @param e
     * @param isFull
     * @return
     */
    private static Object getExceptionMsg(Exception e, boolean isFull) {
        if (isFull) {
            return e;
        }

        String message = e.getMessage();
        boolean isContainsKeyWord = !StringUtils.isEmpty(message) && message.contains("异常类型:") && message.contains("异常信息:");

        if (isContainsKeyWord) {
            return message;

        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("异常类型: {{").append(e.getClass().getName());
            sb.append("}}, 异常信息: {{").append(e.getMessage());
            sb.append("}}, 异常类名: {{").append(e.getStackTrace()[0].getClassName());
            sb.append("}}, 异常方法: {{").append(e.getStackTrace()[0].getMethodName());
            sb.append("}}, 异常行数: {{").append(e.getStackTrace()[0].getLineNumber());
            sb.append("}}");
            return sb.toString();
        }
    }

}
