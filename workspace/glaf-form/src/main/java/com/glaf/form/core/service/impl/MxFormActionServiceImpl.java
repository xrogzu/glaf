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
package com.glaf.form.core.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glaf.core.dao.EntityDAO;
import com.glaf.core.id.IdGenerator;
import com.glaf.form.core.domain.FormAction;
import com.glaf.form.core.mapper.FormActionMapper;
import com.glaf.form.core.query.FormActionQuery;
import com.glaf.form.core.service.FormActionService;

@Service("formActionService")
@Transactional(readOnly = true)
public class MxFormActionServiceImpl implements FormActionService {
	protected EntityDAO entityDAO;

	protected FormActionMapper formActionMapper;

	protected IdGenerator idGenerator;

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected SqlSessionTemplate sqlSessionTemplate;

	public MxFormActionServiceImpl() {

	}

	public int count(FormActionQuery query) {
		return formActionMapper.getFormActionCount(query);
	}

	@Transactional
	public void deleteById(String id) {
		if (id != null) {
			formActionMapper.deleteFormActionById(id);
		}
	}

	@Transactional
	public void deleteByIds(List<String> rowIds) {
		if (rowIds != null && !rowIds.isEmpty()) {
			FormActionQuery query = new FormActionQuery();
			query.rowIds(rowIds);
			formActionMapper.deleteFormActions(query);
		}
	}

	public FormAction getFormAction(String id) {
		if (id == null) {
			return null;
		}
		FormAction formAction = formActionMapper.getFormActionById(id);
		return formAction;
	}

	public int getFormActionCountByQueryCriteria(FormActionQuery query) {
		return formActionMapper.getFormActionCount(query);
	}

	public List<FormAction> getFormActionsByQueryCriteria(int start,
			int pageSize, FormActionQuery query) {
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<FormAction> rows = sqlSessionTemplate.selectList("getFormActions",
				query, rowBounds);
		return rows;
	}

	public List<FormAction> list(FormActionQuery query) {
		List<FormAction> list = formActionMapper.getFormActions(query);
		return list;
	}

	@Transactional
	public void save(FormAction formAction) {
		if (StringUtils.isEmpty(formAction.getId())) {
			formAction.setId(idGenerator.getNextId());
			formAction.setCreateDate(new Date());
			formActionMapper.insertFormAction(formAction);
		} else {
			formActionMapper.updateFormAction(formAction);
		}
	}

	@javax.annotation.Resource
	public void setEntityDAO(EntityDAO entityDAO) {
		this.entityDAO = entityDAO;
	}

	@javax.annotation.Resource
	public void setFormActionMapper(FormActionMapper formActionMapper) {
		this.formActionMapper = formActionMapper;
	}

	@javax.annotation.Resource
	public void setIdGenerator(IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}

	@javax.annotation.Resource
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
