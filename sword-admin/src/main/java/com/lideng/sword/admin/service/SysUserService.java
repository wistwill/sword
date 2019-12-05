package com.lideng.sword.admin.service;

import java.util.List;
import java.util.Set;

import com.github.pagehelper.PageInfo;
import com.lideng.sword.admin.model.entity.SysUser;
import com.lideng.sword.admin.model.entity.SysUserRole;
import com.lideng.sword.admin.model.request.SysUserCreateDTO;
import com.lideng.sword.admin.model.request.SysUserUpdateDTO;
import javax.servlet.http.HttpServletRequest;


/**
 * 用户管理
 * @author lideng
 * @date July 30, 2019
 */
public interface SysUserService  {

	SysUser findByName(String username);

	/**
	 * 查找用户的菜单权限标识集合
	 * @param userName
	 * @return
	 */
	Set<String> findPermissions(String userName);

	/**
	 * 查找用户的角色集合
	 * @param
	 * @return
	 */
	List<SysUserRole> findUserRoles(String userId);

	String create(SysUserCreateDTO record, HttpServletRequest request);

	String update(SysUserUpdateDTO record, HttpServletRequest request);

	int delete(List<String> ids);

	PageInfo<SysUser> findUserPage(Integer page, Integer size);

}
