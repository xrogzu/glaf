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

public class SysFunction implements Serializable {
	private static final long serialVersionUID = -4669036487930746301L;
	private long id;
	private SysApplication app;
	private long appId;
	private String name;
	private String funcDesc;
	private String funcMethod;
	private int sort;

	public SysFunction() {

	}

	public SysApplication getApp() {
		return app;
	}

	public long getAppId() {
		return appId;
	}

	public String getFuncDesc() {
		return funcDesc;
	}

	public String getFuncMethod() {
		return funcMethod;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getSort() {
		return sort;
	}

	public void setApp(SysApplication app) {
		this.app = app;
	}

	public void setAppId(long appId) {
		this.appId = appId;
	}

	public void setFuncDesc(String funcDesc) {
		this.funcDesc = funcDesc;
	}

	public void setFuncMethod(String funcMethod) {
		this.funcMethod = funcMethod;
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

}