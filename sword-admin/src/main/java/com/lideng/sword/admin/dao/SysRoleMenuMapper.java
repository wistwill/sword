package com.lideng.sword.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lideng.sword.admin.model.entity.SysRoleMenu;
import org.springframework.stereotype.Component;

@Component
public interface SysRoleMenuMapper {

    int deleteByPrimaryKey(String id);

    int insert(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(String id);

    int updateByPrimaryKey(SysRoleMenu record);

	List<SysRoleMenu> findRoleMenus(@Param(value = "roleId") String roleId);
	
	List<SysRoleMenu> findAll();

	int deleteByRoleId(@Param(value = "roleId") String roleId);
}