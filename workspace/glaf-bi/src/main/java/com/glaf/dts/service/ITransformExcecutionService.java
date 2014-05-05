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

package com.glaf.dts.service;

import java.util.*;

import com.glaf.dts.domain.*;
import com.glaf.dts.query.*;

public interface ITransformExcecutionService {

	/**
	 * 根据主键删除记录
	 * 
	 * @return
	 */
	void deleteById(String id);

	/**
	 * 根据主键删除多条记录
	 * 
	 * @return
	 */
	void deleteByIds(List<String> rowIds);

	/**
	 * 根据查询参数获取记录列表
	 * 
	 * @return
	 */
	List<TransformExcecution> list(TransformExcecutionQuery query);

	/**
	 * 根据查询参数获取记录总数
	 * 
	 * @return
	 */
	int getTransformExcecutionCountByQueryCriteria(
			TransformExcecutionQuery query);

	/**
	 * 根据查询参数获取一页的数据
	 * 
	 * @return
	 */
	List<TransformExcecution> getTransformExcecutionsByQueryCriteria(int start,
			int pageSize, TransformExcecutionQuery query);

	/**
	 * 根据主键获取一条记录
	 * 
	 * @return
	 */
	TransformExcecution getTransformExcecution(String id);

	/**
	 * 保存一条记录
	 * 
	 * @return
	 */
	void save(TransformExcecution transformExcecution);

}