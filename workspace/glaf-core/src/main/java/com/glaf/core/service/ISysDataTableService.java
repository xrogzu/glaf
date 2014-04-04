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

package com.glaf.core.service;

import java.util.*;

import org.springframework.transaction.annotation.Transactional;

import com.glaf.core.domain.*;
import com.glaf.core.query.*;

@Transactional(readOnly = true)
public interface ISysDataTableService {

	/**
	 * ��������ɾ����¼
	 * 
	 * @return
	 */
	@Transactional
	void deleteById(String id);

	/**
	 * ��������ɾ��������¼
	 * 
	 * @return
	 */
	@Transactional
	void deleteByIds(List<String> ids);

	/**
	 * ����������ȡһ����¼
	 * 
	 * @return
	 */
	SysDataTable getDataTable(String id);

	/**
	 * ���ݷ���ID��ȡһ����¼
	 * 
	 * @return
	 */
	SysDataTable getDataTableByServiceKey(String serviceKey);

	/**
	 * ���ݲ�ѯ������ȡ��¼����
	 * 
	 * @return
	 */
	int getDataTableCountByQueryCriteria(SysDataTableQuery query);

	/**
	 * ���ݲ�ѯ������ȡһҳ������
	 * 
	 * @return
	 */
	List<SysDataTable> getDataTablesByQueryCriteria(int start, int pageSize,
			SysDataTableQuery query);

	/**
	 * ���ݲ�ѯ������ȡ��¼�б�
	 * 
	 * @return
	 */
	List<SysDataTable> list(SysDataTableQuery query);

	@Transactional
	void saveData(String serviceKey, Map<String, Object> dataMap);

	/**
	 * �����ֶζ���
	 * 
	 * @param sysDataField
	 */
	@Transactional
	void saveDataField(SysDataField sysDataField);

	/**
	 * �������ֶζ���
	 * 
	 * @param fields
	 */
	@Transactional
	void saveDataFields(List<SysDataField> fields);

	@Transactional
	void saveDataList(String serviceKey, List<Map<String, Object>> dataList);

	/**
	 * ����һ����¼
	 * 
	 * @return
	 */
	@Transactional
	void saveDataTable(SysDataTable sysDataTable);

}