package com.lideng.sword.admin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.lideng.sword.admin.model.request.SysRoleCreateDTO;
import com.lideng.sword.admin.model.request.SysRoleMenuCreateDTO;
import com.lideng.sword.admin.model.request.SysRoleUpdateDTO;
import com.lideng.sword.common.utils.IdWorker;
import com.lideng.sword.core.exception.SwordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lideng.sword.admin.dao.SysMenuMapper;
import com.lideng.sword.admin.dao.SysRoleMapper;
import com.lideng.sword.admin.dao.SysRoleMenuMapper;
import com.lideng.sword.admin.model.entity.SysMenu;
import com.lideng.sword.admin.model.entity.SysRole;
import com.lideng.sword.admin.model.entity.SysRoleMenu;
import com.lideng.sword.admin.service.SysRoleService;
import javax.servlet.http.HttpServletRequest;
import static com.lideng.sword.admin.constant.SysConstants.ADMIN;
import static com.lideng.sword.admin.constant.SysConstants.USERNAME;

@Slf4j
@Service
@Transactional
/**
 * @author lideng
 * @date 2019/10/09
 */
public class SysRoleServiceImpl  implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Autowired
	private IdWorker idWorker;


	@Override
	public int create(SysRoleCreateDTO record, HttpServletRequest request) {

	    //todo 创建角色的时候，还需要指定部门，这里没有绑定部门
		SysRole sysRole =new SysRole();
		if(!sysRoleMapper.findByName(record.getName()).isEmpty()) {
			throw new SwordException("角色名已存在!");
		}
		BeanUtils.copyProperties(record,sysRole);
		sysRole.setId(idWorker.nextId() + "");
		sysRole.setCreateTime(new Date());
		sysRole.setCreateBy((String) request.getSession().getAttribute(USERNAME.getValue()));
		sysRole.setVersion(0);
        log.info(sysRole.toString());
		return sysRoleMapper.insert(sysRole);
	}

	@Override
	public int update(SysRoleUpdateDTO record, HttpServletRequest request) {

		SysRole sysRole = sysRoleMapper.selectByPrimaryKey(record.getId());
		if(ADMIN.getValue().equals(sysRole.getName())) {
			throw new SwordException("超级管理员不允许修改!");
		}
		BeanUtils.copyProperties(record,sysRole);
		log.info(sysRole.toString());
		sysRole.setLastUpdateBy((String) request.getSession().getAttribute(USERNAME.getValue()));
		sysRole.setLastUpdateTime(new Date());
		sysRole.setVersion(sysRole.getVersion()+1);
		return sysRoleMapper.updateByPrimaryKey(sysRole);
	}

	@Override
	public int delete(List<String> ids) {
		ids.forEach(id->sysRoleMapper.deleteByPrimaryKey(id));
		return ids.size();
	}

	@Override
	public List<SysRole> findAll() {
		return sysRoleMapper.findAll();
	}

	@Override
	public List<Map> findA() {
		return  sysRoleMapper.test();
	}

	@Override
	public List<SysMenu> findRoleMenus(String roleId) {
		if(ADMIN.getValue().equalsIgnoreCase(sysRoleMapper.selectByPrimaryKey(roleId).getName())) {
			// 如果是超级管理员，返回全部
			return sysMenuMapper.findAll();
		}
		return sysMenuMapper.findRoleMenus(roleId);
	}


	@Override
	public int saveRoleMenus(List<SysRoleMenuCreateDTO> records, HttpServletRequest request) {

        /**
         * roleId 对应多个menu ID
         */
		String roleId = records.get(0).getRoleId();
		List<String> menuIdList = records.get(0).getMenuId();
		SysRole sysRole = sysRoleMapper.selectByPrimaryKey(roleId);
		if(ADMIN.getValue().equalsIgnoreCase(sysRole.getName())){
			throw new SwordException("超级管理员拥有所有菜单权限，不允许修改！");
		}
		List<SysRoleMenu> collectList = menuIdList.stream().map(menuId -> {
					SysRoleMenu sysRoleMenu = new SysRoleMenu();
					sysRoleMenu.setRoleId(roleId);
					sysRoleMenu.setMenuId(menuId);
					sysRoleMenu.setId(idWorker.nextId() + "");
					sysRoleMenu.setCreateBy((String) request.getSession().getAttribute(USERNAME.getValue()));
					sysRoleMenu.setCreateTime(new Date());
					return sysRoleMenu;
				}
		).collect(Collectors.toList());
		collectList.forEach(sysRoleMenu->sysRoleMenuMapper.insert(sysRoleMenu));
		return menuIdList.size();
	}

}
