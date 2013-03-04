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

package com.glaf.core.query;

import java.util.List;

public class TreeModelQuery extends DataQuery {
	private static final long serialVersionUID = 1L;
	protected String name;
	protected String nameLike;
	protected String code;
	protected String codeLike;
	protected List<String> codes;
	protected List<Integer> nodeIds;
	protected String nodeType;
	protected List<String> nodeTypes;
	protected String projectId;
	protected List<String> projectIds;
	protected String treeType;
	protected List<String> treeTypes;
	protected List<String> objectIds;
	protected List<String> objectValues;
	protected String treeCode;
	protected Integer readAccessLevel;
	protected Integer readAccessLevelGreaterThanOrEqual;
	protected Integer readAccessLevelLessThanOrEqual;
	protected List<Integer> readAccessLevels;
	protected Integer writeAccessLevel;
	protected Integer writeAccessLevelGreaterThanOrEqual;
	protected Integer writeAccessLevelLessThanOrEqual;
	protected List<Integer> writeAccessLevels;
	protected Boolean writeProtected;
	protected String propsFlag;

	public TreeModelQuery() {

	}

	public TreeModelQuery code(String code) {
		if (code == null) {
			throw new RuntimeException("code is null");
		}
		this.code = code;
		return this;
	}

	public TreeModelQuery codeLike(String codeLike) {
		if (codeLike == null) {
			throw new RuntimeException("code is null");
		}
		this.codeLike = codeLike;
		return this;
	}

	public TreeModelQuery codes(List<String> codes) {
		if (codes == null) {
			throw new RuntimeException("codes is empty ");
		}
		this.codes = codes;
		return this;
	}

	public String getCode() {
		return code;
	}

	public String getCodeLike() {
		return codeLike;
	}

	public List<String> getCodes() {
		return codes;
	}

	public String getName() {
		return name;
	}

	public String getNameLike() {
		return nameLike;
	}

	public List<Integer> getNodeIds() {
		return nodeIds;
	}

	public String getNodeType() {
		return nodeType;
	}

	public List<String> getNodeTypes() {
		return nodeTypes;
	}

	public List<String> getObjectIds() {
		return objectIds;
	}

	public List<String> getObjectValues() {
		return objectValues;
	}

	public String getProjectId() {
		return projectId;
	}

	public List<String> getProjectIds() {
		return projectIds;
	}

	public String getPropsFlag() {
		return propsFlag;
	}

	public Integer getReadAccessLevel() {
		return readAccessLevel;
	}

	public Integer getReadAccessLevelGreaterThanOrEqual() {
		return readAccessLevelGreaterThanOrEqual;
	}

	public Integer getReadAccessLevelLessThanOrEqual() {
		return readAccessLevelLessThanOrEqual;
	}

	public List<Integer> getReadAccessLevels() {
		return readAccessLevels;
	}

	public String getTreeCode() {
		return treeCode;
	}

	public String getTreeType() {
		return treeType;
	}

	public List<String> getTreeTypes() {
		return treeTypes;
	}

	public Integer getWriteAccessLevel() {
		return writeAccessLevel;
	}

	public Integer getWriteAccessLevelGreaterThanOrEqual() {
		return writeAccessLevelGreaterThanOrEqual;
	}

	public Integer getWriteAccessLevelLessThanOrEqual() {
		return writeAccessLevelLessThanOrEqual;
	}

	public List<Integer> getWriteAccessLevels() {
		return writeAccessLevels;
	}

	public Boolean getWriteProtected() {
		return writeProtected;
	}

	public TreeModelQuery name(String name) {
		if (name == null) {
			throw new RuntimeException("name is null");
		}
		this.name = name;
		return this;
	}

	public TreeModelQuery nameLike(String nameLike) {
		if (nameLike == null) {
			throw new RuntimeException("name is null");
		}
		this.nameLike = nameLike;
		return this;
	}

	public TreeModelQuery nodeIds(List<Integer> nodeIds) {
		if (nodeIds == null) {
			throw new RuntimeException("nodeIds is empty ");
		}
		this.nodeIds = nodeIds;
		return this;
	}

	public TreeModelQuery nodeType(String nodeType) {
		if (nodeType == null) {
			throw new RuntimeException("nodeType is null");
		}
		this.nodeType = nodeType;
		return this;
	}

	public TreeModelQuery nodeTypes(List<String> nodeTypes) {
		if (nodeTypes == null) {
			throw new RuntimeException("nodeTypes is empty ");
		}
		this.nodeTypes = nodeTypes;
		return this;
	}

	public TreeModelQuery objectIds(List<String> objectIds) {
		if (objectIds == null) {
			throw new RuntimeException("objectIds is empty ");
		}
		this.objectIds = objectIds;
		return this;
	}

	public TreeModelQuery objectValues(List<String> objectValues) {
		if (objectValues == null) {
			throw new RuntimeException("objectValues is empty ");
		}
		this.objectValues = objectValues;
		return this;
	}

	public TreeModelQuery projectId(String projectId) {
		if (projectId == null) {
			throw new RuntimeException("projectId is null");
		}
		this.projectId = projectId;
		return this;
	}

	public TreeModelQuery projectIds(List<String> projectIds) {
		if (projectIds == null) {
			throw new RuntimeException("projectIds is empty ");
		}
		this.projectIds = projectIds;
		return this;
	}

	public TreeModelQuery propsFlag(String propsFlag) {
		if (propsFlag == null) {
			throw new RuntimeException("propsFlag is null");
		}
		this.propsFlag = propsFlag;
		return this;
	}

	public TreeModelQuery readAccessLevel(Integer readAccessLevel) {
		if (readAccessLevel == null) {
			throw new RuntimeException("readAccessLevel is null");
		}
		this.readAccessLevel = readAccessLevel;
		return this;
	}

	public TreeModelQuery readAccessLevelGreaterThanOrEqual(
			Integer readAccessLevelGreaterThanOrEqual) {
		if (readAccessLevelGreaterThanOrEqual == null) {
			throw new RuntimeException("readAccessLevel is null");
		}
		this.readAccessLevelGreaterThanOrEqual = readAccessLevelGreaterThanOrEqual;
		return this;
	}

	public TreeModelQuery readAccessLevelLessThanOrEqual(
			Integer readAccessLevelLessThanOrEqual) {
		if (readAccessLevelLessThanOrEqual == null) {
			throw new RuntimeException("readAccessLevel is null");
		}
		this.readAccessLevelLessThanOrEqual = readAccessLevelLessThanOrEqual;
		return this;
	}

	public TreeModelQuery readAccessLevels(List<Integer> readAccessLevels) {
		if (readAccessLevels == null) {
			throw new RuntimeException("readAccessLevels is empty ");
		}
		this.readAccessLevels = readAccessLevels;
		return this;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCodeLike(String codeLike) {
		this.codeLike = codeLike;
	}

	public void setCodes(List<String> codes) {
		this.codes = codes;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	public void setNodeIds(List<Integer> nodeIds) {
		this.nodeIds = nodeIds;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public void setNodeTypes(List<String> nodeTypes) {
		this.nodeTypes = nodeTypes;
	}

	public void setObjectIds(List<String> objectIds) {
		this.objectIds = objectIds;
	}

	public void setObjectValues(List<String> objectValues) {
		this.objectValues = objectValues;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public void setProjectIds(List<String> projectIds) {
		this.projectIds = projectIds;
	}

	public void setPropsFlag(String propsFlag) {
		this.propsFlag = propsFlag;
	}

	public void setReadAccessLevel(Integer readAccessLevel) {
		this.readAccessLevel = readAccessLevel;
	}

	public void setReadAccessLevelGreaterThanOrEqual(
			Integer readAccessLevelGreaterThanOrEqual) {
		this.readAccessLevelGreaterThanOrEqual = readAccessLevelGreaterThanOrEqual;
	}

	public void setReadAccessLevelLessThanOrEqual(
			Integer readAccessLevelLessThanOrEqual) {
		this.readAccessLevelLessThanOrEqual = readAccessLevelLessThanOrEqual;
	}

	public void setReadAccessLevels(List<Integer> readAccessLevels) {
		this.readAccessLevels = readAccessLevels;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public void setTreeTypes(List<String> treeTypes) {
		this.treeTypes = treeTypes;
	}

	public void setWriteAccessLevel(Integer writeAccessLevel) {
		this.writeAccessLevel = writeAccessLevel;
	}

	public void setWriteAccessLevelGreaterThanOrEqual(
			Integer writeAccessLevelGreaterThanOrEqual) {
		this.writeAccessLevelGreaterThanOrEqual = writeAccessLevelGreaterThanOrEqual;
	}

	public void setWriteAccessLevelLessThanOrEqual(
			Integer writeAccessLevelLessThanOrEqual) {
		this.writeAccessLevelLessThanOrEqual = writeAccessLevelLessThanOrEqual;
	}

	public void setWriteAccessLevels(List<Integer> writeAccessLevels) {
		this.writeAccessLevels = writeAccessLevels;
	}

	public void setWriteProtected(Boolean writeProtected) {
		this.writeProtected = writeProtected;
	}

	public TreeModelQuery treeCode(String treeCode) {
		if (treeCode == null) {
			throw new RuntimeException("treeCode is null");
		}
		this.treeCode = treeCode;
		return this;
	}

	public TreeModelQuery treeType(String treeType) {
		if (treeType == null) {
			throw new RuntimeException("treeType is null");
		}
		this.treeType = treeType;
		return this;
	}

	public TreeModelQuery treeTypes(List<String> treeTypes) {
		if (treeTypes == null) {
			throw new RuntimeException("treeTypes is empty ");
		}
		this.treeTypes = treeTypes;
		return this;
	}

	public TreeModelQuery writeAccessLevel(Integer writeAccessLevel) {
		if (writeAccessLevel == null) {
			throw new RuntimeException("writeAccessLevel is null");
		}
		this.writeAccessLevel = writeAccessLevel;
		return this;
	}

	public TreeModelQuery writeAccessLevelGreaterThanOrEqual(
			Integer writeAccessLevelGreaterThanOrEqual) {
		if (writeAccessLevelGreaterThanOrEqual == null) {
			throw new RuntimeException("writeAccessLevel is null");
		}
		this.writeAccessLevelGreaterThanOrEqual = writeAccessLevelGreaterThanOrEqual;
		return this;
	}

	public TreeModelQuery writeAccessLevelLessThanOrEqual(
			Integer writeAccessLevelLessThanOrEqual) {
		if (writeAccessLevelLessThanOrEqual == null) {
			throw new RuntimeException("writeAccessLevel is null");
		}
		this.writeAccessLevelLessThanOrEqual = writeAccessLevelLessThanOrEqual;
		return this;
	}

	public TreeModelQuery writeAccessLevels(List<Integer> writeAccessLevels) {
		if (writeAccessLevels == null) {
			throw new RuntimeException("writeAccessLevels is empty ");
		}
		this.writeAccessLevels = writeAccessLevels;
		return this;
	}

	public TreeModelQuery writeProtected(Boolean writeProtected) {
		if (writeProtected == null) {
			throw new RuntimeException("writeProtected is null");
		}
		this.writeProtected = writeProtected;
		return this;
	}

}