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

import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.glaf.core.base.BlobItem;
import com.glaf.core.base.DataFile;

import com.glaf.core.util.DateUtils;

@Entity
@Table(name = "MX_LOB")
public class BlobItemEntity implements DataFile, Serializable, BlobItem {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_", length = 50, nullable = false)
	protected String id;

	@Column(name = "RESOURCEID_", length = 50)
	protected String resourceId;

	@Column(name = "FILEID_", length = 50)
	protected String fileId;

	@Column(name = "SERVICEKEY_", length = 50)
	protected String serviceKey;

	@Column(name = "DEVICEID_", length = 20)
	protected String deviceId;

	@Column(name = "NAME_", length = 50)
	protected String name;

	@Column(name = "TYPE_", length = 50)
	protected String type;

	@Column(name = "FILENAME_", length = 500)
	protected String filename;

	@Column(name = "CONTENTTYPE_", length = 50)
	protected String contentType;

	@Column(name = "OBJECTID_")
	protected String objectId;

	@Column(name = "OBJECTVALUE_")
	protected String objectValue;

	@Column(name = "SIZE_")
	protected long size = -1;

	@Column(name = "LASTMODIFIED_")
	protected long lastModified = -1;

	@Column(name = "LOCKED_")
	protected int locked = 0;

	@Column(name = "STATUS_")
	protected int status = 0;

	@Column(name = "DELETEFLAG_")
	protected int deleteFlag = 0;

	@Column(name = "CREATEBY_", length = 50)
	protected String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE_")
	protected Date createDate;

	@Lob
	@Column(name = "DATA_")
	protected byte[] data = null;

	@Transient
	protected String path = null;

	@Transient
	protected transient InputStream inputStream = null;

	public BlobItemEntity() {

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlobItemEntity other = (BlobItemEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getContentType() {
		return contentType;
	}

	public String getCreateBy() {
		return createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public byte[] getData() {
		return data;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public String getFileId() {
		return fileId;
	}

	public String getFilename() {
		return filename;
	}

	public String getId() {
		return id;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public long getLastModified() {
		return lastModified;
	}

	public int getLocked() {
		return locked;
	}

	public String getName() {
		return name;
	}

	public String getObjectId() {
		return objectId;
	}

	public String getObjectValue() {
		return objectValue;
	}

	public String getPath() {
		return path;
	}

	public String getResourceId() {
		return resourceId;
	}

	public String getServiceKey() {
		return serviceKey;
	}

	public long getSize() {
		return size;
	}

	public int getStatus() {
		return status;
	}

	public String getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public BlobItemEntity jsonToObject(JSONObject jsonObject) {
		BlobItemEntity model = new BlobItemEntity();
		if (jsonObject.containsKey("resourceId")) {
			model.setResourceId(jsonObject.getString("resourceId"));
		}
		if (jsonObject.containsKey("fileId")) {
			model.setFileId(jsonObject.getString("fileId"));
		}
		if (jsonObject.containsKey("serviceKey")) {
			model.setServiceKey(jsonObject.getString("serviceKey"));
		}
		if (jsonObject.containsKey("deviceId")) {
			model.setDeviceId(jsonObject.getString("deviceId"));
		}
		if (jsonObject.containsKey("name")) {
			model.setName(jsonObject.getString("name"));
		}
		if (jsonObject.containsKey("type")) {
			model.setType(jsonObject.getString("type"));
		}
		if (jsonObject.containsKey("filename")) {
			model.setFilename(jsonObject.getString("filename"));
		}
		if (jsonObject.containsKey("contentType")) {
			model.setContentType(jsonObject.getString("contentType"));
		}
		if (jsonObject.containsKey("objectId")) {
			model.setObjectId(jsonObject.getString("objectId"));
		}
		if (jsonObject.containsKey("objectValue")) {
			model.setObjectValue(jsonObject.getString("objectValue"));
		}
		if (jsonObject.containsKey("size")) {
			model.setSize(jsonObject.getLong("size"));
		}
		if (jsonObject.containsKey("lastModified")) {
			model.setLastModified(jsonObject.getLong("lastModified"));
		}
		if (jsonObject.containsKey("locked")) {
			model.setLocked(jsonObject.getInteger("locked"));
		}
		if (jsonObject.containsKey("status")) {
			model.setStatus(jsonObject.getInteger("status"));
		}
		if (jsonObject.containsKey("deleteFlag")) {
			model.setDeleteFlag(jsonObject.getInteger("deleteFlag"));
		}
		if (jsonObject.containsKey("createBy")) {
			model.setCreateBy(jsonObject.getString("createBy"));
		}
		if (jsonObject.containsKey("createDate")) {
			model.setCreateDate(jsonObject.getDate("createDate"));
		}
		return model;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public void setObjectValue(String objectValue) {
		this.objectValue = objectValue;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setType(String type) {
		this.type = type;
	}

	public JSONObject toJsonObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", id);
		if (resourceId != null) {
			jsonObject.put("resourceId", resourceId);
		}
		if (fileId != null) {
			jsonObject.put("fileId", fileId);
		}
		if (serviceKey != null) {
			jsonObject.put("serviceKey", serviceKey);
		}
		if (deviceId != null) {
			jsonObject.put("deviceId", deviceId);
		}
		if (name != null) {
			jsonObject.put("name", name);
		}
		if (type != null) {
			jsonObject.put("type", type);
		}
		if (filename != null) {
			jsonObject.put("filename", filename);
		}
		if (contentType != null) {
			jsonObject.put("contentType", contentType);
		}
		if (objectId != null) {
			jsonObject.put("objectId", objectId);
		}
		if (objectValue != null) {
			jsonObject.put("objectValue", objectValue);
		}
		jsonObject.put("size", size);
		jsonObject.put("lastModified", lastModified);
		jsonObject.put("locked", locked);
		jsonObject.put("status", status);
		jsonObject.put("deleteFlag", deleteFlag);
		if (createBy != null) {
			jsonObject.put("createBy", createBy);
		}
		if (createDate != null) {
			jsonObject.put("createDate", DateUtils.getDate(createDate));
			jsonObject.put("createDate_date", DateUtils.getDate(createDate));
			jsonObject.put("createDate_datetime",
					DateUtils.getDateTime(createDate));
		}
		return jsonObject;
	}

	public ObjectNode toObjectNode() {
		ObjectNode objectNode = new ObjectMapper().createObjectNode();
		objectNode.put("id", id);
		if (resourceId != null) {
			objectNode.put("resourceId", resourceId);
		}
		if (fileId != null) {
			objectNode.put("fileId", fileId);
		}
		if (serviceKey != null) {
			objectNode.put("serviceKey", serviceKey);
		}
		if (deviceId != null) {
			objectNode.put("deviceId", deviceId);
		}
		if (name != null) {
			objectNode.put("name", name);
		}
		if (type != null) {
			objectNode.put("type", type);
		}
		if (filename != null) {
			objectNode.put("filename", filename);
		}
		if (contentType != null) {
			objectNode.put("contentType", contentType);
		}
		if (objectId != null) {
			objectNode.put("objectId", objectId);
		}
		if (objectValue != null) {
			objectNode.put("objectValue", objectValue);
		}
		objectNode.put("size", size);
		objectNode.put("lastModified", lastModified);
		objectNode.put("locked", locked);
		objectNode.put("status", status);
		objectNode.put("deleteFlag", deleteFlag);
		if (createBy != null) {
			objectNode.put("createBy", createBy);
		}
		if (createDate != null) {
			objectNode.put("createDate", DateUtils.getDate(createDate));
			objectNode.put("createDate_date", DateUtils.getDate(createDate));
			objectNode.put("createDate_datetime",
					DateUtils.getDateTime(createDate));
		}
		return objectNode;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}