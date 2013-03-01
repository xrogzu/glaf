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

package com.glaf.base.modules.sys.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.glaf.base.modules.sys.model.SysAccess;

public class SysAccessJsonFactory {

	public static SysAccess jsonToObject(JSONObject jsonObject) {
		SysAccess model = new SysAccess();
		if (jsonObject.containsKey("appId")) {
			model.setAppId(jsonObject.getLong("appId"));
		}
		if (jsonObject.containsKey("roleId")) {
			model.setRoleId(jsonObject.getLong("roleId"));
		}

		return model;
	}

	public static JSONObject toJsonObject(SysAccess model) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("appId", model.getAppId());
		jsonObject.put("_appId_", model.getAppId());
		jsonObject.put("roleId", model.getRoleId());
		return jsonObject;
	}

	public static ObjectNode toObjectNode(SysAccess model) {
		ObjectNode jsonObject = new ObjectMapper().createObjectNode();
		jsonObject.put("appId", model.getAppId());
		jsonObject.put("_appId_", model.getAppId());
		jsonObject.put("roleId", model.getRoleId());
		return jsonObject;
	}

	private SysAccessJsonFactory() {

	}

}
