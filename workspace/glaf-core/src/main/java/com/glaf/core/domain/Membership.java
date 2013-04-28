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

package com.glaf.core.domain;

import java.io.*;
import java.util.*;
import javax.persistence.*;
import com.alibaba.fastjson.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.glaf.core.base.JSONable;
import com.glaf.core.domain.util.*;

@Entity
@Table(name = "SYS_MEMBERSHIP")
public class Membership implements Serializable, JSONable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_", nullable = false)
	protected Long id;

	/**
	 * �û��˺�
	 */
	@Column(name = "ACTORID_")
	protected String actorId;

	/**
	 * ��չ����
	 */
	@Column(name = "ATTRIBUTE_")
	protected String attribute;

	/**
	 * �޸���
	 */
	@Column(name = "MODIFYBY_")
	protected String modifyBy;

	/**
	 * �޸�����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFYDATE_")
	protected Date modifyDate;

	/**
	 * �ڵ���
	 */
	@Column(name = "NODEID_")
	protected Long nodeId;

	/**
	 * Ŀ��Id
	 */
	@Column(name = "OBJECTID_")
	protected String objectId;

	/**
	 * Ŀ��ֵ
	 */
	@Column(name = "OBJECTVALUE_")
	protected String objectValue;

	/**
	 * ��ɫ���
	 */
	@Column(name = "ROLEID_")
	protected Long roleId;

	/**
	 * �ϼ��쵼
	 */
	@Column(name = "SUPERIORID_")
	protected String superiorId;

	/**
	 * ����
	 */
	@Column(name = "TYPE_")
	protected String type;

	public Membership() {

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Membership other = (Membership) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getActorId() {
		return this.actorId;
	}

	public String getAttribute() {
		return this.attribute;
	}

	public Long getId() {
		return this.id;
	}

	public String getModifyBy() {
		return this.modifyBy;
	}

	public Date getModifyDate() {
		return this.modifyDate;
	}

	public Long getNodeId() {
		return this.nodeId;
	}

	public String getObjectId() {
		return this.objectId;
	}

	public String getObjectValue() {
		return this.objectValue;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public String getSuperiorId() {
		return this.superiorId;
	}

	public String getType() {
		return this.type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public Membership jsonToObject(JSONObject jsonObject) {
		return MembershipJsonFactory.jsonToObject(jsonObject);
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public void setObjectValue(String objectValue) {
		this.objectValue = objectValue;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public void setSuperiorId(String superiorId) {
		this.superiorId = superiorId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public JSONObject toJsonObject() {
		return MembershipJsonFactory.toJsonObject(this);
	}

	public ObjectNode toObjectNode() {
		return MembershipJsonFactory.toObjectNode(this);
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}