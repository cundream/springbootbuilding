package com.github.cundream.springbootbuilding.common.aop;

import com.github.cundream.springbootbuilding.config.datasource.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author : Lison
 * @Date: 2019/10/23 16:59
 * @Description:
 */

@Aspect
@Component
public class DataSourceAop {
    @Pointcut("!@annotation(com.github.cundream.springbootbuilding.common.annotation.Master) " +
            "&& (execution(* com.github.cundream.springbootbuilding.service..*.select*(..)) " +
            "|| execution(* com.github.cundream.springbootbuilding.service..*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.github.cundream.springbootbuilding.common.annotation.Master) " +
            "|| execution(* com.github.cundream.springbootbuilding.service..*.insert*(..)) " +
            "|| execution(* com.github.cundream.springbootbuilding.service..*.add*(..)) " +
            "|| execution(* com.github.cundream.springbootbuilding.service..*.update*(..)) " +
            "|| execution(* com.github.cundream.springbootbuilding.service..*.edit*(..)) " +
            "|| execution(* com.github.cundream.springbootbuilding.service..*.delete*(..)) " +
            "|| execution(* com.github.cundream.springbootbuilding.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }
}
