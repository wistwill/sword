package com.lideng.sword.admin.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基础模型
 * @author lideng
 * @date Sep 13, 2018
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SysUserRole extends BaseModel {

    private String userId;

    private String roleId;

}