package com.lideng.sword.admin.dao;

import java.util.List;

import com.lideng.sword.admin.model.entity.SysDept;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface SysDeptMapper {

    @Update("update sys_dept set del_flag=0,version=version+1 where id = #{id}")
    int deleteByPrimaryKey(String id);

    int insert(SysDept record);

    SysDept selectByPrimaryKey(String id);

    int updateByPrimaryKey(SysDept record);
    
    List<SysDept> findPage();
    
    List<SysDept> findAll();
}