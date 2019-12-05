package com.lideng.sword.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lideng.sword.admin.model.entity.SysMenu;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * @author lideng
 * @date July 30, 2019
 *
 */
@Component
public interface SysMenuMapper {

	/**
	 * 根据主键更新del_flag 字段
	 * @param id
	 * @return
	 */
	@Update("update sys_menu set del_flag=0,version=version+1 where id = #{id}")
    int deleteByPrimaryKey(String id);

    int insert(SysMenu record);

    SysMenu selectByPrimaryKey(String id);

    int updateByPrimaryKey(SysMenu record);
    
	List<SysMenu> findPage();

	List<SysMenu> findPageByName(@Param(value = "name") String name);
	
	List<SysMenu> findAll();

	List<SysMenu> findByUserName(@Param(value = "userName") String userName);

	List<SysMenu> findRoleMenus(@Param(value = "roleId") String roleId);
}