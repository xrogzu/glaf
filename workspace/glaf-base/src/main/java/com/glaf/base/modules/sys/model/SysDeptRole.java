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
import java.util.HashSet;
import java.util.Set;
import com.alibaba.fastjson.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.glaf.core.base.JSONable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class SysDeptRole implements Serializable, JSONable {
	private static final long serialVersionUID = 273479478656626289L;
	private long id;
	private SysRole role;
	private SysDepartment dept;
	private long deptId;
	private int grade;
	private String code;
	private int sort;
	private long sysRoleId;

	private Set<SysUser> users = new HashSet<SysUser>();
	private Set<SysFunction> functions = new HashSet<SysFunction>();
	private Set<SysApplication> apps = new HashSet<SysApplication>();

	public SysDeptRole() {

	}

	public Set<SysApplication> getApps() {
		return apps;
	}

	public String getCode() {
		return code;
	}

	public SysDepartment getDept() {
		return dept;
	}

	public long getDeptId() {
		return deptId;
	}

	public Set<SysFunction> getFunctions() {
		return functions;
	}

	public int getGrade() {
		return grade;
	}

	public long getId() {
		return id;
	}

	public SysRole getRole() {
		return role;
	}

	public int getSort() {
		return sort;
	}

	public long getSysRoleId() {
		return sysRoleId;
	}

	public Set<SysUser> getUsers() {
		return users;
	}

	public void setApps(Set<SysApplication> apps) {
		this.apps = apps;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDept(SysDepartment dept) {
		this.dept = dept;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}

	public void setFunctions(Set<SysFunction> functions) {
		this.functions = functions;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setRole(SysRole role) {
		this.role = role;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public void setSysRoleId(long sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	public void setUsers(Set<SysUser> users) {
		this.users = users;
	}

	public SysDeptRole jsonToObject(JSONObject jsonObject) {
		SysDeptRole model = new SysDeptRole();
		if (jsonObject.containsKey("id")) {
			model.setId(jsonObject.getLong("id"));
		}
		if (jsonObject.containsKey("grade")) {
			model.setGrade(jsonObject.getInteger("grade"));
		}
		if (jsonObject.containsKey("code")) {
			model.setCode(jsonObject.getString("code"));
		}
		if (jsonObject.containsKey("sort")) {
			model.setSort(jsonObject.getInteger("sort"));
		}
		if (jsonObject.containsKey("sysRoleId")) {
			model.setSysRoleId(jsonObject.getLong("sysRoleId"));
		}
		if (jsonObject.containsKey("deptId")) {
			model.setDeptId(jsonObject.getLong("deptId"));
		}
		return model;
	}

	public JSONObject toJsonObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", id);
		jsonObject.put("_id_", id);
		jsonObject.put("_oid_", id);
		jsonObject.put("grade", grade);
		if (code != null) {
			jsonObject.put("code", code);
		}
		jsonObject.put("sort", sort);
		jsonObject.put("sysRoleId", sysRoleId);
		jsonObject.put("deptId", deptId);
		return jsonObject;
	}

	public ObjectNode toObjectNode() {
		ObjectNode jsonObject = new ObjectMapper().createObjectNode();
		jsonObject.put("id", id);
		jsonObject.put("_id_", id);
		jsonObject.put("_oid_", id);
		jsonObject.put("grade", grade);
		if (code != null) {
			jsonObject.put("code", code);
		}
		jsonObject.put("sort", sort);
		jsonObject.put("sysRoleId", sysRoleId);
		jsonObject.put("deptId", deptId);
		return jsonObject;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}