package com.lideng.sword.admin.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * 基础模型
 * @author lideng
 * @date Sep 13, 2018
 */
@Data
public class BaseModel {

	private String id;
	
    private String createBy;

    private Date createTime;

    private String lastUpdateBy;

    private Date lastUpdateTime;

    private Integer version;
}
