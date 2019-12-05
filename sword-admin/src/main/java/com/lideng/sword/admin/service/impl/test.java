package com.lideng.sword.admin.service.impl;
import java.util.Date;

import com.lideng.sword.admin.dao.SysRoleMapper;
import com.lideng.sword.admin.dao.SysRoleMenuMapper;
import com.lideng.sword.admin.model.entity.SysRoleMenu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @author lideng
 * @date 2019/9/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class test {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Test
    public void getHello()  {

        SysRoleMenu sysRoleMenu =new SysRoleMenu();
        sysRoleMenu.setRoleId("1");
        sysRoleMenu.setMenuId("2");
        sysRoleMenu.setId("3");
        sysRoleMenu.setCreateBy("4");
        sysRoleMenu.setCreateTime(new Date());
        sysRoleMenu.setLastUpdateBy("5");
        sysRoleMenu.setLastUpdateTime(new Date());
        sysRoleMenu.setVersion(0);
        int insert = sysRoleMenuMapper.insert(sysRoleMenu);
    }
}