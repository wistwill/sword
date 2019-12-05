package com.lideng.sword.admin.dao;

import com.lideng.sword.admin.model.entity.SysRoleDept;
import org.springframework.stereotype.Component;

@Component
public interface SysRoleDeptMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRoleDept record);

    SysRoleDept selectByPrimaryKey(String id);

    int updateByPrimaryKey(SysRoleDept record);
}