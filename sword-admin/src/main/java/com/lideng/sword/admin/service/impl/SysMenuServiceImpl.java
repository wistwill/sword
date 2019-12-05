package com.lideng.sword.admin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.lideng.sword.admin.model.request.SysMenuCreateDTO;
import com.lideng.sword.admin.model.request.SysMenuUpdateDTO;
import com.lideng.sword.common.utils.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lideng.sword.admin.constant.SysConstants;
import com.lideng.sword.admin.dao.SysMenuMapper;
import com.lideng.sword.admin.model.entity.SysMenu;
import com.lideng.sword.admin.service.SysMenuService;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import static com.lideng.sword.admin.constant.SysConstants.USERNAME;

@Slf4j
@Service
@Transactional
/**
 * @author lideng
 * @date 2019/10/09
 */
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Autowired
	private IdWorker idWorker;


	@Override
	public int create(SysMenuCreateDTO record, HttpServletRequest request) {
		SysMenu sysMenu =new SysMenu();
		BeanUtils.copyProperties(record,sysMenu);
		sysMenu.setId(idWorker.nextId() + "");
		sysMenu.setCreateTime(new Date());
		sysMenu.setCreateBy((String) request.getSession().getAttribute(USERNAME.getValue()));
		sysMenu.setVersion(0);
		return sysMenuMapper.insert(sysMenu);

	}

	@Override
	public int update(SysMenuUpdateDTO record, HttpServletRequest request) {

		SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(record.getId());
		BeanUtils.copyProperties(record,sysMenu);
		sysMenu.setLastUpdateBy((String) request.getSession().getAttribute(USERNAME.getValue()));
		sysMenu.setVersion(sysMenu.getVersion()+1);
        sysMenu.setLastUpdateTime(new Date());
		log.info(sysMenu.toString());
		return sysMenuMapper.updateByPrimaryKey(sysMenu);
	}


	@Override
	public int delete(List<String> ids) {
		ids.forEach(id->sysMenuMapper.deleteByPrimaryKey(id));
		return ids.size();
	}

	@Override
	public SysMenu findById(String id) {
		return sysMenuMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SysMenu> findTree(String userName, int menuType) {
		List<SysMenu> sysMenus = new ArrayList<>();
		List<SysMenu> menus = findByUser(userName);
		for (SysMenu menu : menus) {
			if ("0".equals(menu.getParentId())) {
				menu.setLevel(0);
				if(!exists(sysMenus, menu)) {
					sysMenus.add(menu);
				}
			}
		}
		sysMenus.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
		findChildren(sysMenus, menus, menuType);
		return sysMenus;
	}

	@Override
	public List<SysMenu> findByUser(String userName) {
		if(userName == null || "".equals(userName) || SysConstants.ADMIN.getValue().equalsIgnoreCase(userName)) {
			return sysMenuMapper.findAll();
		}
		return sysMenuMapper.findByUserName(userName);
	}

	private void findChildren(List<SysMenu> SysMenus, List<SysMenu> menus, int menuType) {
		for (SysMenu SysMenu : SysMenus) {
			List<SysMenu> children = new ArrayList<>();
			for (SysMenu menu : menus) {
				if(menuType == 1 && menu.getType() == 2) {
					// 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
					continue ;
				}
				if (SysMenu.getId() != null && SysMenu.getId().equals(menu.getParentId())) {
					menu.setParentName(SysMenu.getName());
					menu.setLevel(SysMenu.getLevel() + 1);
					if(!exists(children, menu)) {
						children.add(menu);
					}
				}
			}
			SysMenu.setChildren(children);
			children.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
			findChildren(children, menus, menuType);
		}
	}

	private boolean exists(List<SysMenu> sysMenus, SysMenu sysMenu) {
		boolean exist = false;
		for(SysMenu menu:sysMenus) {
			if(menu.getId().equals(sysMenu.getId())) {
				exist = true;
			}
		}
		return exist;
	}
	
}
