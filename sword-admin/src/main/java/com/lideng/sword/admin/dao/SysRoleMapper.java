package com.lideng.sword.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lideng.sword.admin.model.entity.SysRole;
import org.springframework.stereotype.Component;

@Component
public interface SysRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRole record);

    SysRole selectByPrimaryKey(String id);

    List <Map> test();

    int updateByPrimaryKey(SysRole record);
    
    List<SysRole> findPage();

	List<SysRole> findAll();
	
	List<SysRole> findPageByName(@Param(value = "name") String name);
	
	List<SysRole> findByName(@Param(value = "name") String name);
}