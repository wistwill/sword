package com.lideng.sword.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lideng.sword.admin.model.entity.SysUserRole;
import org.springframework.stereotype.Component;

@Component
public interface SysUserRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUserRole record);

    SysUserRole selectByPrimaryKey(String id);

    int updateByPrimaryKey(SysUserRole record);

	List<SysUserRole> findUserRoles(@Param(value = "userId") String userId);

	int deleteByUserId(@Param(value = "userId") String userId);
}