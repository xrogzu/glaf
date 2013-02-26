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

import com.glaf.core.base.TableModel;
import com.glaf.core.domain.TableDefinition;
import com.glaf.core.util.Paging;

@Transactional
public interface ITableDataService {

	/**
	 * ��ȡһҳ����
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param model
	 * @return
	 */
	Paging getPageData(int pageNo, int pageSize, TableModel model);

	/**
	 * ����������ȡ��¼
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	List<Map<String, Object>> getTableDataByPrimaryKey(Object id);

	/**
	 * ������������
	 * 
	 * @param rows
	 */
	@Transactional
	void insertTableData(List<TableModel> rows);

	/**
	 * ������������
	 * 
	 * @param tableName
	 * @param rows
	 */
	@Transactional
	void insertTableData(String tableName, List<Map<String, Object>> rows);

	/**
	 * ����һ����¼
	 * 
	 * @param model
	 */
	@Transactional
	void insertTableData(TableModel model);

	/**
	 * �����������޸ļ�¼��������ڣ�����ѡ���Ƿ����
	 * 
	 * @param tableName
	 * @param updatable
	 * @param rows
	 */
	@Transactional
	void saveOrUpdate(String tableName, boolean updatable,
			List<Map<String, Object>> rows);

	/**
	 * ���������¼
	 * 
	 * @param tableName
	 * @param rows
	 */
	@Transactional
	void saveAll(String tableName, Collection<TableModel> rows);

	/**
	 * ���������¼
	 * 
	 * @param tableDefinition
	 * @param rows
	 */
	@Transactional
	void saveAll(TableDefinition tableDefinition, Collection<TableModel> rows);

	/**
	 * �������¼�¼
	 * 
	 * @param rows
	 */
	@Transactional
	void updateTableData(List<TableModel> rows);

	/**
	 * �������¼�¼
	 * 
	 * @param tableName
	 * @param rows
	 */
	@Transactional
	void updateTableData(String tableName, List<Map<String, Object>> rows);

	/**
	 * ����һ����¼
	 * 
	 * @param model
	 */
	@Transactional
	void updateTableData(TableModel model);
}