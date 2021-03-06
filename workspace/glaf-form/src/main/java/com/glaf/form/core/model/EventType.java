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
package com.glaf.form.core.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventType", propOrder = { "script", "property" })
public class EventType {

	protected List<ScriptType> script;

	protected List<PropertyType> property;

	@XmlAttribute(name = "type", required = true)
	protected String type;

	public List<PropertyType> getProperty() {
		if (property == null) {
			property = new java.util.ArrayList<PropertyType>();
		}
		return this.property;
	}

	public List<ScriptType> getScript() {
		if (script == null) {
			script = new java.util.ArrayList<ScriptType>();
		}
		return this.script;
	}

	public String getType() {
		return type;
	}

	public void setType(String value) {
		this.type = value;
	}

}