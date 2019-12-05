package com.lideng.sword.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lideng.sword.admin.model.entity.SysConfig;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface SysConfigMapper {

    int deleteByPrimaryKey(String id);

    int insert(SysConfig record);

    SysConfig selectByPrimaryKey(String id);

    int updateByPrimaryKey(SysConfig record);
    
    List<SysConfig> findPage();
    
    List<SysConfig> findPageByLabel(@Param(value = "label") String label);

    List<SysConfig> findByLable(@Param(value = "label") String label);

    @Update("update sys_config set del_flag=0,version=version+1 where id = #{id}")
    int updateDeleteFlagByid( String id);
}