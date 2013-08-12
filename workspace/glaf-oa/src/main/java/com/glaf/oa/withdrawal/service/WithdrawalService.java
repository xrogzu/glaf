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
package com.glaf.oa.withdrawal.service;

import java.util.*;
import org.springframework.transaction.annotation.Transactional;

import com.glaf.oa.withdrawal.model.*;
import com.glaf.oa.withdrawal.query.*;

@Transactional(readOnly = true)
public interface WithdrawalService {

	/**
	 * ��������ɾ����¼
	 * 
	 * @return
	 */
	@Transactional
	void deleteById(Long id);

	/**
	 * ��������ɾ��������¼
	 * 
	 * @return
	 */
	@Transactional
	void deleteByIds(List<Long> withdrawalids);

	/**
	 * ���ݲ�ѯ������ȡ��¼�б�
	 * 
	 * @return
	 */
	List<Withdrawal> list(WithdrawalQuery query);

	/**
	 * ���ݲ�ѯ������ȡ��¼����
	 * 
	 * @return
	 */
	int getWithdrawalCountByQueryCriteria(WithdrawalQuery query);

	/**
	 * ���ݲ�ѯ������ȡһҳ������
	 * 
	 * @return
	 */
	List<Withdrawal> getWithdrawalsByQueryCriteria(int start, int pageSize,
			WithdrawalQuery query);

	/**
	 * ����������ȡһ����¼
	 * 
	 * @return
	 */
	Withdrawal getWithdrawal(Long id);

	/**
	 * ����һ����¼
	 * 
	 * @return
	 */
	@Transactional
	void save(Withdrawal withdrawal);

	/**
	 * ���ݲ�ѯ������ȡ������¼����
	 * 
	 * @return
	 */
	int getWithdrawalApproveCountByQueryCriteria(WithdrawalQuery query);

	/**
	 * ���ݲ�ѯ������ȡһҳ����������
	 * 
	 * @return
	 */
	List<Withdrawal> getWithdrawalsApproveByQueryCriteria(int start,
			int pageSize, WithdrawalQuery query);
}