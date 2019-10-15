package com.github.cundream.springbootbuilding.common.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : Lison
 * @Date: 2019/10/15 10:47
 * @Description:
 */
@Data
@Component
public class CundreamProperties {

    @Value("${com.cundream.title}")
    private String title;

    @Value("${com.cundream.name}")
    private String name;

    @Value("${com.cundream.desc}")
    private String desc;
}
