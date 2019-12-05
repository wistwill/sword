package com.lideng.sword.admin.service.impl;

import java.util.Date;
import java.util.List;

import com.lideng.sword.admin.model.request.SysDictCreateDTO;
import com.lideng.sword.admin.model.request.SysDictUpdateDTO;
import com.lideng.sword.common.utils.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lideng.sword.admin.dao.SysDictMapper;
import com.lideng.sword.admin.model.entity.SysDict;
import com.lideng.sword.admin.service.SysDictService;
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
public class SysDictServiceImpl  implements SysDictService {

	@Autowired
	private SysDictMapper sysDictMapper;

	@Autowired
	private IdWorker idWorker;


	@Override
	public int create(SysDictCreateDTO record, HttpServletRequest request) {

		SysDict sysDict =new SysDict();
		BeanUtils.copyProperties(record,sysDict);
		sysDict.setId(idWorker.nextId() + "");
		sysDict.setCreateBy((String) request.getSession().getAttribute(USERNAME.getValue()));
		sysDict.setCreateTime(new Date());
		sysDict.setVersion(0);
		return sysDictMapper.insert(sysDict);
	}

	@Override
	public int update(SysDictUpdateDTO record, HttpServletRequest request) {

		SysDict sysDict = sysDictMapper.selectByPrimaryKey(record.getId());
		BeanUtils.copyProperties(record,sysDict);
		sysDict.setLastUpdateBy((String) request.getSession().getAttribute(USERNAME.getValue()));
		sysDict.setLastUpdateTime(new Date());
		sysDict.setVersion(sysDict.getVersion()+1);
		return sysDictMapper.updateByPrimaryKey(sysDict);
	}

	@Override
	public int delete(List<String> ids) {

		ids.forEach(id->sysDictMapper.deleteByPrimaryKey(id));
		return ids.size();
	}

	@Override
	public SysDict findById(String id) {
		return sysDictMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SysDict> findByLabel(String label) {
		return sysDictMapper.findByLabel(label);
	}

}
