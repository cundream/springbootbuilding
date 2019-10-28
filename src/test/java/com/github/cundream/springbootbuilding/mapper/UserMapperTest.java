package com.github.cundream.springbootbuilding.mapper;

import cn.hutool.core.collection.CollUtil;
import com.github.cundream.springbootbuilding.SpringbootbuildingApplicationTests;
import com.github.cundream.springbootbuilding.entity.User;
import com.github.cundream.springbootbuilding.utils.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Lison
 * @Date: 2019/10/28 11:28
 * @Description:
 */
@Slf4j
public class UserMapperTest extends SpringbootbuildingApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        Long id = IdUtil.newInstance().nextId();
        User testSave3 = User.builder().id(id).userName("testSave3").passWord("123456").build();
        userMapper.insertUseGeneratedKeys(testSave3);
        Assert.assertNotNull(testSave3.getId());
        log.debug("【测试主键回写#testSave3.getId()】= {}", testSave3.getId());
    }


    @Test
    public void testInsertList() {
        List<User> userList = Lists.newArrayList();
        for (long i = 5; i < 14; i++) {
            Long salt = IdUtil.newInstance().nextId();
            User user = User.builder().id(i).userName("testSave"+i).passWord("123456").realName("test"+i).build();
            userList.add(user);
        }
        int i = userMapper.insertList(userList);
        Assert.assertEquals(userList.size(), i);
        List<Long> ids = userList.stream().map(User::getId).collect(Collectors.toList());
        log.debug("【测试主键回写#userList.ids】= {}", ids);
    }

    /**
     * 测试通用Mapper - 删除
     */
    @Test
    public void testDelete() {
        Long primaryKey = 1L;
        int i = userMapper.deleteByPrimaryKey(primaryKey);
        Assert.assertEquals(1, i);
        User user = userMapper.selectByPrimaryKey(primaryKey);
        Assert.assertNull(user);
    }

    /**
     * 测试通用Mapper - 更新
     */
    @Test
    public void testUpdate() {
        Long primaryKey = 2L;
        User user = userMapper.selectByPrimaryKey(primaryKey);
        user.setUserName("通用Mapper名字更新");
        int i = userMapper.updateByPrimaryKeySelective(user);
        Assert.assertEquals(1, i);
        User update = userMapper.selectByPrimaryKey(primaryKey);
        Assert.assertNotNull(update);
        Assert.assertEquals("通用Mapper名字更新", update.getUserName());
        log.debug("【update】= {}", update);
    }

    /**
     * 测试通用Mapper - 查询单个
     */
    @Test
    public void testQueryOne(){
        User user = userMapper.selectByPrimaryKey(2L);
        Assert.assertNotNull(user);
        log.debug("【user】= {}", user);
    }

    /**
     * 测试通用Mapper - 查询全部
     */
    @Test
    public void testQueryAll() {
        List<User> users = userMapper.selectAll();
        Assert.assertTrue(CollUtil.isNotEmpty(users));
        log.debug("【users】= {}", users);
    }

    /**
     * 测试分页助手 - 分页排序查询
     */
    @Test
    public void testQueryByPageAndSort() {
        initData();
        int currentPage = 1;
        int pageSize = 5;
        String orderBy = "id desc";
        int count = userMapper.selectCount(null);
        PageHelper.startPage(currentPage, pageSize, orderBy);
        List<User> users = userMapper.selectAll();
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        Assert.assertEquals(5, userPageInfo.getSize());
        Assert.assertEquals(count, userPageInfo.getTotal());
        log.debug("【userPageInfo】= {}", userPageInfo);
    }

    /**
     * 测试通用Mapper - 条件查询
     */
    @Test
    public void testQueryByCondition() {
        initData();
        Example example = new Example(User.class);
        // 过滤
        example.createCriteria().andLike("name", "%Save1%").orEqualTo("phoneNumber", "17300000001");
        // 排序
        example.setOrderByClause("id desc");
        int count = userMapper.selectCountByExample(example);
        // 分页
        PageHelper.startPage(1, 3);
        // 查询
        List<User> userList = userMapper.selectByExample(example);
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        Assert.assertEquals(3, userPageInfo.getSize());
        Assert.assertEquals(count, userPageInfo.getTotal());
        log.debug("【userPageInfo】= {}", userPageInfo);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        testInsertList();
    }

}
