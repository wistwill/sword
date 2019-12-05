package com.lideng.sword.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lideng.sword.admin.model.entity.SysUser;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface SysUserMapper {

    @Update("update sys_user set del_flag=0 where id = #{id}")
    int deleteByPrimaryKey(String id);

    int insert(SysUser record);


    SysUser selectByPrimaryKey(String id);


    int updateByPrimaryKey(SysUser record);
    
    List<SysUser> findAll();
    
    SysUser findByName(@Param(value = "name") String name);
    
	List<SysUser> findPageByName(@Param(value = "name") String name);
	
	List<SysUser> findPageByNameAndEmail(@Param(value = "name") String name, @Param(value = "email") String email);
}