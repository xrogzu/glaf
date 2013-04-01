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

package com.glaf.base.modules.sys.springmvc;

import java.util.ArrayList;

import java.util.Collections;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.glaf.core.res.MessageUtils;
import com.glaf.core.res.ViewMessage;
import com.glaf.core.res.ViewMessages;
import com.glaf.core.util.PageResult;
import com.glaf.core.util.ParamUtils;
import com.glaf.core.util.RequestUtils;
import com.glaf.core.util.Tools;

import com.glaf.base.modules.BaseDataManager;
import com.glaf.base.modules.Constants;

import com.glaf.base.modules.sys.model.Dictory;
import com.glaf.base.modules.sys.model.DictoryDefinition;

import com.glaf.base.modules.sys.model.SysTree;
import com.glaf.base.modules.sys.service.DictoryDefinitionService;
import com.glaf.base.modules.sys.service.DictoryService;
import com.glaf.base.modules.sys.service.SysTreeService;
import com.glaf.base.utils.ParamUtil;
import com.glaf.base.utils.RequestUtil;

@Controller("/sys/dictory")
@RequestMapping("/sys/dictory.do")
public class SysDictoryController {
	private static final Log logger = LogFactory
			.getLog(SysDictoryController.class);

	@javax.annotation.Resource
	private DictoryService dictoryService;

	@javax.annotation.Resource
	private SysTreeService sysTreeService;

	@javax.annotation.Resource
	protected DictoryDefinitionService dictoryDefinitionService;

	public void setDictoryService(DictoryService dictoryService) {
		this.dictoryService = dictoryService;
		logger.info("setDictoryService");
	}

	public void setSysTreeService(SysTreeService sysTreeService) {
		this.sysTreeService = sysTreeService;
		logger.info("setSysTreeService");
	}

	public void setDictoryDefinitionService(
			DictoryDefinitionService dictoryDefinitionService) {
		this.dictoryDefinitionService = dictoryDefinitionService;
		logger.info("setDictoryDefinitionService");
	}

	/**
	 * ��ʾ�����б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=showList")
	public ModelAndView showList(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		RequestUtil.setRequestParameterToAttribute(request);
		int parent = ParamUtil.getIntParameter(request, "parent", 0);
		int pageNo = ParamUtil.getIntParameter(request, "page_no", 1);
		int pageSize = ParamUtil.getIntParameter(request, "page_size", 10);
		PageResult pager = dictoryService.getDictoryList(parent, pageNo,
				pageSize);
		request.setAttribute("pager", pager);
		// ��ʾ�б�ҳ��
		return new ModelAndView("/modules/sys/dictory/dictory_list", modelMap);
	}

	/**
	 * ��ʾ�����ֵ�ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=prepareAdd")
	public ModelAndView prepareAdd(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {
		// ��ʾ�б�ҳ��
		RequestUtil.setRequestParameterToAttribute(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		Long nodeId = ParamUtils.getLong(params, "parent");
		if (nodeId > 0) {
			List<DictoryDefinition> list = dictoryDefinitionService
					.getDictoryDefinitions(nodeId, "sys_dictory");
			if (list != null && !list.isEmpty()) {
				Collections.sort(list);
				request.setAttribute("list", list);
			}
		}

		return new ModelAndView("/modules/sys/dictory/dictory_add", modelMap);
	}

	/**
	 * �ύ�����ֵ���Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=saveAdd")
	public ModelAndView saveAdd(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		logger.debug("params:"+params);
		Dictory bean = new Dictory();
		try {
			Tools.populate(bean, params);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		ViewMessages messages = new ViewMessages();
		if (dictoryService.create(bean)) {// ����ɹ�
			if (bean.getNodeId() == 17) {
				BaseDataManager.getInstance().loadDictInfo();
			}
			messages.add(ViewMessages.GLOBAL_MESSAGE, new ViewMessage(
					"dictory.add_success"));
		} else {// ����ʧ��
			messages.add(ViewMessages.GLOBAL_MESSAGE, new ViewMessage(
					"dictory.add_failure"));
		}
		MessageUtils.addMessages(request, messages);
		request.setAttribute("url", "dictory.do?method=showList");

		return new ModelAndView("show_msg", modelMap);
	}

	/**
	 * ��ʾ�޸�ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=prepareModify")
	public ModelAndView prepareModify(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {
		RequestUtil.setRequestParameterToAttribute(request);
		long id = ParamUtil.getIntParameter(request, "id", 0);
		Dictory bean = dictoryService.find(id);
		request.setAttribute("bean", bean);

		long nodeId = ParamUtil.getLongParameter(request, "parent", 0);
		if (nodeId > 0) {
			List<DictoryDefinition> list = dictoryDefinitionService
					.getDictoryDefinitions(nodeId, "sys_dictory");
			if (list != null && !list.isEmpty()) {
				if (bean != null) {
					Map<String, Object> dataMap = Tools.getDataMap(bean);
					for (DictoryDefinition d : list) {
						Object value = dataMap.get(d.getName());
						d.setValue(value);
					}
				}
				Collections.sort(list);
				request.setAttribute("list", list);
			}
		}

		SysTree parent = sysTreeService
				.getSysTreeByCode(Constants.TREE_DICTORY);
		List<SysTree> list = new ArrayList<SysTree>();
		parent.setDeep(0);
		list.add(parent);
		sysTreeService.getSysTree(list, (int) parent.getId(), 1);
		request.setAttribute("parent", list);

		return new ModelAndView("/modules/sys/dictory/dictory_modify", modelMap);
	}

	/**
	 * �ύ�޸��ֵ���Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=saveModify")
	public ModelAndView saveModify(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {
		RequestUtil.setRequestParameterToAttribute(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		long id = ParamUtil.getIntParameter(request, "id", 0);
		Dictory bean = dictoryService.find(id);
        logger.debug("params:"+params);
		try {
			Tools.populate(bean, params);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		ViewMessages messages = new ViewMessages();
		if (dictoryService.update(bean)) {// ����ɹ�
			if (bean.getNodeId() == 17) {
				BaseDataManager.getInstance().loadDictInfo();
			}
			messages.add(ViewMessages.GLOBAL_MESSAGE, new ViewMessage(
					"dictory.modify_success"));
		} else {// ����ʧ��
			messages.add(ViewMessages.GLOBAL_MESSAGE, new ViewMessage(
					"dictory.modify_failure"));
		}
		MessageUtils.addMessages(request, messages);

		return new ModelAndView("show_msg", modelMap);
	}

	/**
	 * �ύɾ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=batchDelete")
	public ModelAndView batchDelete(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {
		RequestUtil.setRequestParameterToAttribute(request);
		boolean ret = true;
		long[] id = ParamUtil.getLongParameterValues(request, "id");
		ret = dictoryService.deleteAll(id);
		ViewMessages messages = new ViewMessages();
		if (ret) {// ����ɹ�
			messages.add(ViewMessages.GLOBAL_MESSAGE, new ViewMessage(
					"dictory.delete_success"));
		} else { // ɾ��ʧ��
			messages.add(ViewMessages.GLOBAL_MESSAGE, new ViewMessage(
					"dictory.delete_failure"));
		}
		MessageUtils.addMessages(request, messages);
		return new ModelAndView("show_msg2", modelMap);
	}

	/**
	 * ��ʾ���ҳ��
	 * 
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=showFrame")
	public ModelAndView showFrame(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {
		RequestUtil.setRequestParameterToAttribute(request);
		SysTree bean = sysTreeService.getSysTreeByCode(Constants.TREE_DICTORY);
		request.setAttribute("parent", bean.getId() + "");
		return new ModelAndView("/modules/sys/dictory/dictory_frame", modelMap);
	}

	/**
	 * ��ʾ���ҳ��
	 * 
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=loadDictory")
	public ModelAndView loadDictory(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {
		RequestUtil.setRequestParameterToAttribute(request);
		return new ModelAndView("/modules/sys/dictory/dictory_load", modelMap);
	}

	/**
	 * ��ʾ��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=saveLoadDictory")
	public ModelAndView saveLoadDictory(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {
		RequestUtil.setRequestParameterToAttribute(request);
		BaseDataManager.getInstance().refreshBaseData();
		ViewMessages messages = new ViewMessages();
		messages.add(ViewMessages.GLOBAL_MESSAGE, new ViewMessage(
				"dictory.reload_success"));
		MessageUtils.addMessages(request, messages);
		// ��ʾ�б�ҳ��
		return new ModelAndView("/modules/sys/dictory/dictory_load", modelMap);
	}

	/**
	 * ��ʾ�ֵ�����
	 * 
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=showDictData")
	public ModelAndView showDictData(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {
		RequestUtil.setRequestParameterToAttribute(request);
		ModelAndView view = null;
		String code = ParamUtil.getParameter(request, "code");
		Iterator<?> iter = null;
		long parent = ParamUtil.getLongParameter(request, "parent", -1);
		if (!(parent == -1)) {
			// List list =
			// this.goodsCategoryService.getGoodsCategoryList(parent);
			// iter = list.iterator();
			// view = new ModelAndView("show_contract_dictory_select");
		} else {
			iter = BaseDataManager.getInstance().getList(code);
			view = new ModelAndView("/modules/sys/dictory/dictory_select",
					modelMap);
		}
		request.setAttribute("list", iter);

		// ��ʾ�б�ҳ��
		return view;
	}
}