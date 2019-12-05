package com.lideng.sword.admin.controller;

import java.util.List;
import com.lideng.sword.admin.model.request.SysRoleCreateDTO;
import com.lideng.sword.admin.model.request.SysRoleMenuCreateDTO;
import com.lideng.sword.admin.model.request.SysRoleUpdateDTO;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.lideng.sword.admin.service.SysRoleService;
import com.lideng.sword.core.http.HttpResult;


import javax.servlet.http.HttpServletRequest;

/**
 * 角色控制器
 * @author lideng
 * @date July 30, 2019
 */
@RestController
@RequestMapping("role")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;

	@PreAuthorize("hasAuthority('sys:role:add') AND hasAuthority('sys:role:edit')")
	@RequestMapping(value="/create",method = RequestMethod.POST)
	public HttpResult create(@RequestBody SysRoleCreateDTO record, HttpServletRequest request) {
		return HttpResult.ok(sysRoleService.create(record,request));
	}

	@PreAuthorize("hasAuthority('sys:config:add') AND hasAuthority('sys:config:edit')")
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public HttpResult update(@RequestBody SysRoleUpdateDTO record, HttpServletRequest request) {
		return HttpResult.ok(sysRoleService.update(record,request));
	}

	@PreAuthorize("hasAuthority('sys:role:delete')")
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public HttpResult delete(@ApiParam(value = "只用传ID")@RequestBody List<String> ids) {
		return HttpResult.ok(sysRoleService.delete(ids));
	}

	@PreAuthorize("hasAuthority('sys:role:view')")
	@RequestMapping(value="/findAll",method = RequestMethod.GET)
	public HttpResult findAll() {
		return HttpResult.ok(sysRoleService.findAll(),"查询成功");
	}
	
	@PreAuthorize("hasAuthority('sys:role:view')")
	@RequestMapping(value="/findRoleMenus",method = RequestMethod.GET)
	public HttpResult findRoleMenus(@RequestParam String roleId) {
		return HttpResult.ok(sysRoleService.findRoleMenus(roleId));
	}

	@RequestMapping(value="/findRoleAll",method = RequestMethod.GET)
	public HttpResult findRoleAll() {
		return HttpResult.ok(sysRoleService.findA(),"测试复杂查询，返回一个map");
	}

	@PreAuthorize("hasAuthority('sys:role:view')")
	@RequestMapping(value="/saveRoleMenus",method = RequestMethod.POST)
	public HttpResult saveRoleMenus(@RequestBody List<SysRoleMenuCreateDTO> records,HttpServletRequest request) {
		return HttpResult.ok(sysRoleService.saveRoleMenus(records,request),"保存成功");
	}
}
