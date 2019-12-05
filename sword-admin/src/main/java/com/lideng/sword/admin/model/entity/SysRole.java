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
public class SysRole extends BaseModel {

    private String name;

    private String remark;

    private boolean delFlag;

}