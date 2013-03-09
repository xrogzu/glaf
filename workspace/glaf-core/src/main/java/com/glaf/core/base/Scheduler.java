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

package com.glaf.core.base;

import java.util.*;

public interface Scheduler {

	String getAttribute();

	String getContent();

	String getCreateBy();

	Date getCreateDate();

	Date getEndDate();

	String getExpression();

	String getId();

	String getJobClass();

	Map<String, Parameter> getJobDataMap();

	int getLocked();

	int getPriority();

	int getRepeatCount();

	int getRepeatInterval();

	Date getStartDate();

	int getStartDelay();

	String getTaskId();

	String getTaskName();

	String getTaskType();

	int getThreadSize();

	String getTitle();
	
	int getStartup();
	
	int getAutoStartup();

	boolean isSchedulerAutoStartup();

	boolean isSchedulerStartup();

	boolean isValid();

	void setAttribute(String attribute);

	void setAutoStartup(int autoStartup);

	void setContent(String content);

	void setCreateBy(String createBy);

	void setCreateDate(Date createDate);

	void setEndDate(Date endDate);

	void setExpression(String expression);

	void setId(String id);

	void setJobClass(String jobClass);

	void setJobDataMap(Map<String, Parameter> jobDataMap);

	void setLocked(int locked);

	void setPriority(int priority);

	void setRepeatCount(int repeatCount);

	void setRepeatInterval(int repeatInterval);

	void setStartDate(Date startDate);

	void setStartDelay(int startDelay);

	void setStartup(int startup);

	void setTaskId(String taskId);

	void setTaskName(String taskName);

	void setTaskType(String taskType);

	void setThreadSize(int threadSize);

	void setTitle(String title);

}