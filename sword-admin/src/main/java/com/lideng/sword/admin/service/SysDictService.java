package com.lideng.sword.admin.service;

import java.util.List;

import com.lideng.sword.admin.model.entity.SysDict;
import com.lideng.sword.admin.model.request.SysDictCreateDTO;
import com.lideng.sword.admin.model.request.SysDictUpdateDTO;
import javax.servlet.http.HttpServletRequest;


/**
 * 字典管理
 * @author lideng
 * @date July 30, 2019
 */
public interface SysDictService  {

	/**
	 * 根据名称查询
	 * @param label
	 * @return
	 */
	List<SysDict> findByLabel(String label);




	int create(SysDictCreateDTO record, HttpServletRequest request);

	int update(SysDictUpdateDTO record, HttpServletRequest request);


	int delete(List<String> ids);

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	SysDict findById(String id);


}
