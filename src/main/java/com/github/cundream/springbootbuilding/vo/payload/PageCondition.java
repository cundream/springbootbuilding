package com.github.cundream.springbootbuilding.vo.payload;

import lombok.Data;

/**
 * @author : Lison
 * @Date: 2019/10/28 15:47
 * @Description: 分页请求参数
 */
@Data
public class PageCondition {

    /**
     * 当前页码
     */
    private Integer currentPage;

    /**
     * 每页条数
     */
    private Integer pageSize;



}
