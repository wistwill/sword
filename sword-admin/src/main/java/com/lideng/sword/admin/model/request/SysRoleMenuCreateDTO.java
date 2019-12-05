package com.lideng.sword.admin.model.request;

import lombok.Data;

import java.util.List;

/**
 * 基础模型
 * @author lideng
 * @date Sep 13, 2018
 */
@Data
public class SysRoleMenuCreateDTO  {

    private String roleId;

    private List<String> menuId;

}