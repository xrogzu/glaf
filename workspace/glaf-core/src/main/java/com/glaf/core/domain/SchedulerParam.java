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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
 
import com.glaf.core.base.Parameter;
import com.glaf.core.util.DateUtils;

@Entity
@Table(name = "MX_SYS_SCHEDULER_PARAMS")
public class SchedulerParam implements Serializable,  Parameter {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", length = 50, nullable = false)
	protected String id;

	/**
	 * taskId
	 */
	@Column(name = "taskId", length = 50)
	protected String taskId;

	/**
	 * type_cd
	 */
	@Column(name = "type_cd", length = 20)
	protected String typeCd;

	/**
	 * key_name
	 */
	@Column(name = "key_name", length = 50)
	protected String keyName;

	@Column(name = "title", length = 200)
	protected String title;

	/**
	 * string_val
	 */
	@Column(name = "string_val", length = 2000)
	protected String stringVal;

	@Lob
	@Column(name = "text_val")
	protected String textVal;

	/**
	 * date_val
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_val")
	protected Date dateVal;

	/**
	 * long_val
	 */
	@Column(name = "int_val")
	protected Integer intVal;

	/**
	 * long_val
	 */
	@Column(name = "long_val")
	protected Long longVal;

	/**
	 * double_val
	 */
	@Column(name = "double_val")
	protected Double doubleVal;

	public SchedulerParam() {

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SchedulerParam other = (SchedulerParam) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Date getDateVal() {
		return this.dateVal;
	}

	public Double getDoubleVal() {
		return this.doubleVal;
	}

	public String getId() {
		return id;
	}

	public Integer getIntVal() {
		return intVal;
	}

	public String getKeyName() {
		return this.keyName;
	}

	public Long getLongVal() {
		return this.longVal;
	}

	public String getStringVal() {
		return this.stringVal;
	}

	public String getTaskId() {
		return this.taskId;
	}

	public String getTextVal() {
		return textVal;
	}

	public String getTitle() {
		return title;
	}

	public String getTypeCd() {
		return this.typeCd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public SchedulerParam jsonToObject(JSONObject jsonObject) {
		SchedulerParam model = new SchedulerParam();
		if (jsonObject.containsKey("taskId")) {
			model.setTaskId(jsonObject.getString("taskId"));
		}
		if (jsonObject.containsKey("typeCd")) {
			model.setTypeCd(jsonObject.getString("typeCd"));
		}
		if (jsonObject.containsKey("keyName")) {
			model.setKeyName(jsonObject.getString("keyName"));
		}
		if (jsonObject.containsKey("title")) {
			model.setTitle(jsonObject.getString("title"));
		}
		if (jsonObject.containsKey("stringVal")) {
			model.setStringVal(jsonObject.getString("stringVal"));
		}
		if (jsonObject.containsKey("textVal")) {
			model.setTextVal(jsonObject.getString("textVal"));
		}
		if (jsonObject.containsKey("dateVal")) {
			model.setDateVal(jsonObject.getDate("dateVal"));
		}
		return model;
	}

	public void setDateVal(Date dateVal) {
		this.dateVal = dateVal;
	}

	public void setDoubleVal(Double doubleVal) {
		this.doubleVal = doubleVal;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIntVal(Integer intVal) {
		this.intVal = intVal;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public void setLongVal(Long longVal) {
		this.longVal = longVal;
	}

	public void setStringVal(String stringVal) {
		this.stringVal = stringVal;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public void setTextVal(String textVal) {
		this.textVal = textVal;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}

	public JSONObject toJsonObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", id);
		if (taskId != null) {
			jsonObject.put("taskId", taskId);
		}
		if (typeCd != null) {
			jsonObject.put("typeCd", typeCd);
		}
		if (keyName != null) {
			jsonObject.put("keyName", keyName);
		}
		if (title != null) {
			jsonObject.put("title", title);
		}
		if (stringVal != null) {
			jsonObject.put("stringVal", stringVal);
		}
		if (textVal != null) {
			jsonObject.put("textVal", textVal);
		}
		if (dateVal != null) {
			jsonObject.put("dateVal", DateUtils.getDate(dateVal));
			jsonObject.put("dateVal_date", DateUtils.getDate(dateVal));
			jsonObject.put("dateVal_datetime", DateUtils.getDateTime(dateVal));
		}
		return jsonObject;
	}

	public ObjectNode toObjectNode() {
		ObjectNode jsonObject = new ObjectMapper().createObjectNode();
		jsonObject.put("id", id);
		if (taskId != null) {
			jsonObject.put("taskId", taskId);
		}
		if (typeCd != null) {
			jsonObject.put("typeCd", typeCd);
		}
		if (keyName != null) {
			jsonObject.put("keyName", keyName);
		}
		if (title != null) {
			jsonObject.put("title", title);
		}
		if (stringVal != null) {
			jsonObject.put("stringVal", stringVal);
		}
		if (textVal != null) {
			jsonObject.put("textVal", textVal);
		}
		if (dateVal != null) {
			jsonObject.put("dateVal", DateUtils.getDate(dateVal));
			jsonObject.put("dateVal_date", DateUtils.getDate(dateVal));
			jsonObject.put("dateVal_datetime", DateUtils.getDateTime(dateVal));
		}
		return jsonObject;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}