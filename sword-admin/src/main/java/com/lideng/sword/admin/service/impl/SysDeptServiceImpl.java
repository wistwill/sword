package com.lideng.sword.admin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.lideng.sword.admin.model.request.SysDeptCreateDTO;
import com.lideng.sword.admin.model.request.SysDeptUpdateDTO;
import com.lideng.sword.common.utils.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lideng.sword.admin.dao.SysDeptMapper;
import com.lideng.sword.admin.model.entity.SysDept;
import com.lideng.sword.admin.service.SysDeptService;
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
public class SysDeptServiceImpl implements SysDeptService {

	@Autowired
	SysDeptMapper sysDeptMapper;

	@Autowired
	IdWorker idWorker;

	@Override
	public int create(SysDeptCreateDTO record, HttpServletRequest request) {

		SysDept sysDept =new SysDept();
		BeanUtils.copyProperties(record,sysDept);
		log.info(sysDept.toString());
		sysDept.setId(idWorker.nextId() + "");
		sysDept.setCreateTime(new Date());
		sysDept.setCreateBy((String) request.getSession().getAttribute(USERNAME.getValue()));
		sysDept.setVersion(0);
		return sysDeptMapper.insert(sysDept);
	}

	@Override
	public int update(SysDeptUpdateDTO record, HttpServletRequest request) {

		SysDept sysDept = sysDeptMapper.selectByPrimaryKey(record.getId());
		BeanUtils.copyProperties(record,sysDept);
		log.info(sysDept.toString());
		sysDept.setLastUpdateBy((String) request.getSession().getAttribute(USERNAME.getValue()));
		sysDept.setLastUpdateTime(new Date());
		sysDept.setVersion(sysDept.getVersion()+1);
		log.info(sysDept.toString());
		return sysDeptMapper.updateByPrimaryKey(sysDept);
	}


	@Override
	public int delete(List<String> ids) {

		//todo 删除部门之前是不是应该先判断有没有user
		ids.forEach(id->sysDeptMapper.deleteByPrimaryKey(id));
		return ids.size();
	}

	@Override
	public SysDept findById(String id) {
		return sysDeptMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SysDept> findTree() {
		List<SysDept> sysDepts = new ArrayList<>();
		List<SysDept> depts = sysDeptMapper.findAll();
		for (SysDept dept : depts) {
			if (StringUtils.isBlank(dept.getParentId())) {
				dept.setLevel(0);
				sysDepts.add(dept);
			}
		}
		findChildren(sysDepts, depts);
		return sysDepts;
	}

	private void findChildren(List<SysDept> sysDepts, List<SysDept> depts) {
		for (SysDept sysDept : sysDepts) {
			List<SysDept> children = new ArrayList<>();
			for (SysDept dept : depts) {
				if (sysDept.getId() != null && sysDept.getId().equals(dept.getParentId())) {
					dept.setParentName(dept.getName());
					dept.setLevel(sysDept.getLevel() + 1);
					children.add(dept);
				}
			}
			sysDept.setChildren(children);
			findChildren(children, depts);
		}
	}

}
