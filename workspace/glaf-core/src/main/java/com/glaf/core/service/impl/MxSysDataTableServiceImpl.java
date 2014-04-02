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

package com.glaf.core.service.impl;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glaf.core.id.*;
import com.glaf.core.dao.*;
import com.glaf.core.mapper.*;
import com.glaf.core.domain.*;
import com.glaf.core.query.*;
import com.glaf.core.service.ISysDataTableService;

@Service("sysDataTableService")
@Transactional(readOnly = true)
public class MxSysDataTableServiceImpl implements ISysDataTableService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected EntityDAO entityDAO;

	protected IdGenerator idGenerator;

	protected SqlSessionTemplate sqlSessionTemplate;

	protected SysDataTableMapper sysDataTableMapper;

	public MxSysDataTableServiceImpl() {

	}

	@Transactional
	public void deleteById(String id) {
		if (id != null) {
			sysDataTableMapper.deleteSysDataTableById(id);
		}
	}

	@Transactional
	public void deleteByIds(List<String> ids) {
		if (ids != null && !ids.isEmpty()) {
			for (String id : ids) {
				sysDataTableMapper.deleteSysDataTableById(id);
			}
		}
	}

	public int count(SysDataTableQuery query) {
		return sysDataTableMapper.getSysDataTableCount(query);
	}

	public List<SysDataTable> list(SysDataTableQuery query) {
		List<SysDataTable> list = sysDataTableMapper.getSysDataTables(query);
		return list;
	}

	public int getSysDataTableCountByQueryCriteria(SysDataTableQuery query) {
		return sysDataTableMapper.getSysDataTableCount(query);
	}

	public List<SysDataTable> getSysDataTablesByQueryCriteria(int start,
			int pageSize, SysDataTableQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<SysDataTable> rows = sqlSessionTemplate.selectList(
				"getSysDataTables", query, rowBounds);
		return rows;
	}

	public SysDataTable getSysDataTable(String id) {
		if (id == null) {
			return null;
		}
		SysDataTable sysDataTable = sysDataTableMapper.getSysDataTableById(id);
		return sysDataTable;
	}

	/**
	 * 根据服务ID获取一条记录
	 * 
	 * @return
	 */
	public SysDataTable getSysDataTableByServiceKey(String serviceKey) {
		SysDataTableQuery query = new SysDataTableQuery();
		query.serviceKey(serviceKey);
		SysDataTable sysDataTable = null;
		List<SysDataTable> list = sysDataTableMapper.getSysDataTables(query);
		if (list != null && !list.isEmpty()) {
			sysDataTable = list.get(0);
		}
		return sysDataTable;
	}

	@Transactional
	public void save(SysDataTable sysDataTable) {
		if (StringUtils.isEmpty(sysDataTable.getId())) {
			sysDataTable.setId(idGenerator.getNextId());
			sysDataTableMapper.insertSysDataTable(sysDataTable);
		} else {
			sysDataTableMapper.updateSysDataTable(sysDataTable);
		}
	}

	@javax.annotation.Resource
	public void setEntityDAO(EntityDAO entityDAO) {
		this.entityDAO = entityDAO;
	}

	@javax.annotation.Resource
	public void setIdGenerator(IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}

	@javax.annotation.Resource
	public void setSysDataTableMapper(SysDataTableMapper sysDataTableMapper) {
		this.sysDataTableMapper = sysDataTableMapper;
	}

	@javax.annotation.Resource
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
