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

package com.glaf.base.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.glaf.base.modules.sys.model.SysUser;
import com.glaf.base.modules.sys.model.SysUserRole;
import com.glaf.base.utils.PageResult;

public interface SysUserRoleService {

	/**
	 * 授权
	 * 
	 * @param fromUser
	 *            SysUser 授权人
	 * @param toUser
	 *            SysUser 被授权人
	 * @param startDate
	 *            String
	 * @param endDate
	 *            String
	 */
	boolean addRole(long fromUserId, long toUserId, String startDate,
			String endDate, int mark, String processNames,
			String processDescriptions);

	/**
	 * 保存
	 * 
	 * @param bean
	 *            SysUserRole
	 * @return boolean
	 */
	boolean create(SysUserRole bean);

	/**
	 * 删除
	 * 
	 * @param id
	 *            int
	 * @return boolean
	 */
	boolean delete(long id);

	/**
	 * 删除
	 * 
	 * @param bean
	 *            SysUserRole
	 * @return boolean
	 */
	boolean delete(SysUserRole bean);

	/**
	 * 获取对象
	 * 
	 * @param id
	 *            long
	 * @return
	 */
	SysUserRole findById(long id);

	/**
	 * 
	 * @param filter
	 * @return
	 */
	PageResult getAllAuthorizedUser(Map filter);

	/**
	 * 某人已授权的用户列表
	 * 
	 * @param user
	 *            SysUser
	 * @return
	 */
	List getAuthorizedUser(SysUser user);

	/**
	 * 获取用户所有的审批工作流
	 */
	List getProcessByUser(SysUser user);

	/**
	 * 取本部门下的未授权用户列表（除了自己、已授权用户）
	 * 
	 * @param user
	 *            SysUser
	 * @return
	 */
	List getUnAuthorizedUser(SysUser user);

	/**
	 * 工作流授权
	 * 
	 * @param fromUser
	 * @param toUser
	 */
	void insertAgent(SysUser fromUser, SysUser toUser, String startDate,
			String endDate, int mark, String processNames);

	/**
	 * 判断是否已经授权了
	 * 
	 * @param fromUserId
	 *            long
	 * @param toUserId
	 *            long
	 * @return
	 */
	boolean isAuthorized(long fromUserId, long toUserId);

	void removeAgent(SysUser fromUser, SysUser toUser);

	/**
	 * 取消授权
	 * 
	 * @param fromUser
	 *            SysUser 授权人
	 * @param toUser
	 *            SysUser 被授权人
	 */
	boolean removeRole(long fromUserId, long toUserId);

	/**
	 * 定时批量删除过期代理的权限
	 * 
	 * @return
	 */
	public boolean removeRoles();

	/**
	 * 更新
	 * 
	 * @param bean
	 *            SysUserRole
	 * @return boolean
	 */
	boolean update(SysUserRole bean);
}