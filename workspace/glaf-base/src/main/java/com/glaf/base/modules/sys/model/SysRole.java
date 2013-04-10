/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.glaf.base.modules.sys.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import com.alibaba.fastjson.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.glaf.base.modules.sys.util.SysRoleJsonFactory;
import com.glaf.core.base.JSONable;

@Entity
@Table(name = "sys_role")
public class SysRole implements Serializable, JSONable {
	private static final long serialVersionUID = 7738558740111388611L;
	/**
	 * 编码
	 */
	@Column(name = "CODE")
	protected String code;

	/**
	 * 创建人
	 */
	@Column(name = "CREATEBY")
	protected String createBy;

	/**
	 * 创建日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE")
	protected Date createDate;

	/**
	 * 描述
	 */
	@Column(name = "ROLEDESC")
	protected String desc;

	@Id
	@Column(name = "ID", length = 50, nullable = false)
	protected long id;

	/**
	 * 名称
	 */
	@Column(name = "NAME")
	protected String name;

	/**
	 * 序号
	 */
	@Column(name = "SORT")
	protected int sort;

	/**
	 * 修改人
	 */
	@Column(name = "UPDATEBY")
	protected String updateBy;

	/**
	 * 修改日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATEDATE")
	protected Date updateDate;

	public SysRole() {

	}

	public String getCode() {
		return this.code;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public String getDesc() {
		return this.desc;
	}

	public long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public int getSort() {
		return this.sort;
	}

	public String getUpdateBy() {
		return this.updateBy;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public SysRole jsonToObject(JSONObject jsonObject) {
		return SysRoleJsonFactory.jsonToObject(jsonObject);
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public JSONObject toJsonObject() {
		return SysRoleJsonFactory.toJsonObject(this);
	}

	public ObjectNode toObjectNode() {
		return SysRoleJsonFactory.toObjectNode(this);
	}

	public String toString() {
		return toJsonObject().toJSONString();
	}

}