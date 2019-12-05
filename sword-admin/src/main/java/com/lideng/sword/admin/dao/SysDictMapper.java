package com.lideng.sword.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lideng.sword.admin.model.entity.SysDict;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface SysDictMapper {

    @Update("update sys_dict set del_flag=0,version=version+1 where id = #{id}")
    int deleteByPrimaryKey(String id);

    int insert(SysDict record);

    SysDict selectByPrimaryKey(String id);

    int updateByPrimaryKey(SysDict record);
    
    List<SysDict> findPage();
    
    List<SysDict> findPageByLabel(@Param(value = "label") String label);

    List<SysDict> findByLabel(@Param(value = "label") String label);
}