package com.github.cundream.springbootbuilding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Lison
 */
@EnableCaching
@MapperScan("com.github.cundream.springbootbuilding.mapper")
@SpringBootApplication
public class SpringbootbuildingApplication  extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringbootbuildingApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringbootbuildingApplication.class, args);
    }

}
