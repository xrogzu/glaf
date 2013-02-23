package ${packageName}.action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import org.json.*;

import com.glaf.core.util.JsonUtils;
import com.glaf.jbpm.context.ProcessContext;
import com.glaf.jbpm.datafield.DataField;
import com.glaf.jbpm.model.*;
import com.glaf.jbpm.service.*;
import com.glaf.jbpm.container.*; 

import com.glaf.base.config.*;

import com.glaf.base.modules.sys.model.*;
import com.glaf.base.security.*;
import com.glaf.base.utils.*;

import ${packageName}.model.*;
import ${packageName}.query.*;

public class ${entityName}WfAction extends ${entityName}BaseAction {

	protected static final Log logger = LogFactory.getLog(${entityName}WfAction.class);

	public ${entityName}WfAction() {

	}

	public ActionForward startProcess(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SysUser user = RequestUtil.getSysUser(request);
		String actorId = user.getAccount();
		Map<String, Object> params = RequestUtil.getParameterMap(request);
		String rowId = ParamUtil.getString(params, "rowId");
		${entityName} ${modelName} = ${modelName}Service.get${entityName}(rowId);
		if (${modelName} != null) {
			String processName = SystemConfig.getString("${entityName}.processName");
			if (StringUtils.isEmpty(processName)) {
				processName = "${entityName}Process";
				${modelName}.setProcessName(processName);
			}
			ProcessContext ctx = new ProcessContext();
			ctx.setRowId(String.valueOf(${modelName}.getId()));
			ctx.setActorId(actorId);
			ctx.setTitle(SystemConfig.getString("res_rowId") + ${modelName}.getId());
			ctx.setProcessName(${modelName}.getProcessName());
			String processInstanceId = ProcessContainer.getContainer()
					.startProcess(ctx);
			if (processInstanceId == null) {
				ActionMessages messages = new ActionMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"audit.failure"));
				addMessages(request, messages);
				return mapping.findForward("show_msg");
			}
		}

		return this.list(mapping, actionForm, request, response);
	}

	public ActionForward completeTask(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SysUser user = RequestUtil.getSysUser(request);
		String actorId = user.getAccount();
		Map<String, Object> params = RequestUtil.getParameterMap(request);
		String taskInstanceId = ParamUtil.getString(params, "taskInstanceId");
		String rowId = ParamUtil.getString(params, "rowId");
		${entityName} ${modelName} = null;
		if (StringUtils.isNotEmpty(rowId)) {
			${modelName} = ${modelName}Service.get${entityName}(rowId);
			if (${modelName} != null && ${modelName}.getStatus() != 50) {
				TaskItem taskItem = ProcessContainer.getContainer()
						.getMinTaskItem(actorId, ${modelName}.getProcessInstanceId());
				if (taskItem != null
						&& StringUtils.equals(taskItem.getTaskInstanceId(),
								taskInstanceId)) {
					if (StringUtils.isNotEmpty(${modelName}.getProcessInstanceId())) {
						String route = request.getParameter("route");
						String isAgree = request.getParameter("isAgree");
						String opinion = request.getParameter("opinion");
						ProcessContext ctx = new ProcessContext();
						Collection<DataField> datafields = new ArrayList<DataField>();
						if (StringUtils.isNotEmpty(isAgree)) {
							DataField datafield = new DataField();
							datafield.setName("isAgree");
							datafield.setValue(isAgree);
							datafields.add(datafield);
						}
						if (StringUtils.isNotEmpty(route)) {
							DataField datafield = new DataField();
							datafield.setName("route");
							datafield.setValue(route);
							datafields.add(datafield);
						}
						ctx.setActorId(actorId);
						ctx.setOpinion(opinion);
						ctx.setContextMap(params);
						ctx.setDataFields(datafields);
						ctx.setTaskInstanceId(taskInstanceId);
						ctx.setProcessInstanceId(${modelName}.getProcessInstanceId());
						boolean isOK = ProcessContainer.getContainer()
								.completeTask(ctx);
						if (!isOK) {
							ActionMessages messages = new ActionMessages();
							messages.add(ActionMessages.GLOBAL_MESSAGE,
									new ActionMessage("audit.failure"));
							addMessages(request, messages);
							return mapping.findForward("show_msg");
						}
					}
				}
			}
		}

		return this.list(mapping, actionForm, request, response);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser user = RequestUtil.getSysUser(request);
		String actorId = user.getAccount();
		RequestUtil.setRequestParameterToAttribute(request);
		request.removeAttribute("canSubmit");
		Map<String, Object> params = RequestUtil.getParameterMap(request);
		String rowId = ParamUtil.getString(params, "rowId");
		${entityName} ${modelName} = null;
		if (StringUtils.isNotEmpty(rowId)) {
			${modelName} = ${modelName}Service.get${entityName}(rowId);
			request.setAttribute("${modelName}", ${modelName});
			JSONObject rowJSON = ${modelName}.toJsonObject();
			request.setAttribute("x_json", rowJSON.toString('\n'));
		}

		boolean canUpdate = false;
		boolean canSubmit = false;
		String x_method = request.getParameter("x_method");
		if (StringUtils.equals(x_method, "submit")) {
			if (${modelName} != null
					&& StringUtils.isNotEmpty(${modelName}.getProcessInstanceId())) {
				ProcessContainer container = ProcessContainer.getContainer();
				Collection<String> processInstanceIds = container
						.getRunningProcessInstanceIds(actorId);
				if (processInstanceIds.contains(${modelName}.getProcessInstanceId())) {
					canSubmit = true;
				}
				if (${modelName}.getStatus() == 0 || ${modelName}.getStatus() == -1) {
					canUpdate = true;
				}
				TaskItem taskItem = container.getMinTaskItem(actorId,
						${modelName}.getProcessInstanceId());
				if (taskItem != null) {
					request.setAttribute("taskItem", taskItem);
				}
				List<StateInstance> stepInstances = container
						.getStateInstances(${modelName}.getProcessInstanceId());
				request.setAttribute("stepInstances", stepInstances);
				request.setAttribute("stateInstances", stepInstances);
			} else {
				canSubmit = true;
				canUpdate = true;
			}
		}

		if (StringUtils.containsIgnoreCase(x_method, "update")) {
			if (${modelName} != null) {
				if (${modelName}.getStatus() == 0 || ${modelName}.getStatus() == -1) {
					canUpdate = true;
				}
			}
		}

		request.setAttribute("canSubmit", canSubmit);
		request.setAttribute("canUpdate", canUpdate);

		return mapping.findForward("show_modify");
	}

	public ActionForward view(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RequestUtil.setRequestParameterToAttribute(request);
		Map<String, Object> params = RequestUtil.getParameterMap(request);
		String rowId = ParamUtil.getString(params, "rowId");
		${entityName} ${modelName} = null;
		if (StringUtils.isNotEmpty(rowId)) {
			${modelName} = ${modelName}Service.get${entityName}(rowId);
			request.setAttribute("${modelName}", ${modelName});
			Map<String, Object> dataMap = Tools.getDataMap(${modelName});
			String x_json = JSONTools.encode(dataMap);
			x_json = RequestUtil.encodeString(x_json);
			request.setAttribute("x_json", x_json);

			if (StringUtils.isNotEmpty(${modelName}.getProcessInstanceId())) {
				ProcessContainer container = ProcessContainer.getContainer();
				List<StateInstance> stepInstances = container
						.getStateInstances(${modelName}.getProcessInstanceId());
				request.setAttribute("stepInstances", stepInstances);
				request.setAttribute("stateInstances", stepInstances);
			}
		}

		return mapping.findForward("show_view");
	}

	public ActionForward json(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser user = RequestUtil.getSysUser(request);
		String actorId = user.getAccount();
		RequestUtil.setRequestParameterToAttribute(request);

		String processName = SystemConfig.getString("${entityName}.processName");
		if (StringUtils.isEmpty(processName)) {
			processName = "${entityName}Process";
		}

		String taskType = request.getParameter("taskType");
		if (StringUtils.isEmpty(taskType)) {
			taskType = "all";
		}

		Map<String, Object> params = RequestUtil.getParameterMap(request);
		${entityName}Query query = new ${entityName}Query();
		Tools.populate(query, params);

		ProcessContainer container = ProcessContainer.getContainer();
		if (StringUtils.equals(taskType, "running")) {
			List<String> processInstanceIds = container
					.getRunningProcessInstanceIdsByName(processName,
							user.getActorId());
			if (processInstanceIds != null && processInstanceIds.size() > 0) {
				query.processInstanceIds(processInstanceIds);
			} else {
				processInstanceIds = new ArrayList<String>();
				processInstanceIds.add("0");
				query.processInstanceIds(processInstanceIds);
			}
		} else if (StringUtils.equals(taskType, "finished")) {
			List<String> processInstanceIds = container
					.getFinishedProcessInstanceIds(processName,
							user.getActorId());
			if (processInstanceIds != null && processInstanceIds.size() > 0) {
				query.processInstanceIds(processInstanceIds);
			} else {
				processInstanceIds = new ArrayList<String>();
				processInstanceIds.add("0");
				query.processInstanceIds(processInstanceIds);
			}
		} else if (StringUtils.equals(taskType, "fallback")) {
			List<String> processInstanceIds = container
					.getFinishedProcessInstanceIds(processName,
							user.getActorId());
			if (processInstanceIds != null && processInstanceIds.size() > 0) {
				query.processInstanceIds(processInstanceIds);
			} else {
				processInstanceIds = new ArrayList<String>();
				processInstanceIds.add("0");
				query.processInstanceIds(processInstanceIds);
			}
		} else if (StringUtils.equals(taskType, "worked")) {
			List<String> processInstanceIds = container
					.getFinishedProcessInstanceIds(processName,
							user.getActorId());
			if (processInstanceIds != null && processInstanceIds.size() > 0) {
				query.processInstanceIds(processInstanceIds);
			} else {
				processInstanceIds = new ArrayList<String>();
				processInstanceIds.add("0");
				query.processInstanceIds(processInstanceIds);
			}
		}

		String gridType = ParamUtil.getString(params, "gridType");
		if (gridType == null) {
			gridType = "easyui";
		}
		int start = 0;
		int limit = 10;
		String orderName = null;
		String order = null;

		int pageNo = ParamUtil.getInt(params, "page");
		limit = ParamUtil.getInt(params, "rows");
		start = (pageNo - 1) * limit;
		orderName = ParamUtil.getString(params, "sortName");
		order = ParamUtil.getString(params, "sortOrder");

		if (start < 0) {
			start = 0;
		}

		if (limit <= 0) {
			limit = PageResult.DEFAULT_PAGE_SIZE;
		}

		JSONObject result = new JSONObject();
		int total = ${modelName}Service.get${entityName}CountByQueryCriteria(query);
		if (total > 0) {
			result.put("total", total);
			result.put("totalCount", total);
			result.put("totalRecords", total);
			result.put("start", start);
			result.put("startIndex", start);
			result.put("limit", limit);
			result.put("pageSize", limit);

			if (StringUtils.isNotEmpty(orderName)) {
				query.setSortOrder(orderName);
				if (StringUtils.equals(order, "desc")) {
					query.setSortOrder(" desc ");
				}
			}

			Map<String, SysUser> userMap = IdentityFactory.getUserMap();
			List<${entityName}> list = ${modelName}Service.get${entityName}sByQueryCriteria(start, limit,
					query);

			if (list != null && !list.isEmpty()) {
				JSONArray rowsJSON = new JSONArray();

				result.put("rows", rowsJSON);

				for (${entityName} ${modelName} : list) {
					JSONObject rowJSON = ${modelName}.toJsonObject();
					rowJSON.put("id", ${modelName}.getId());
					rowJSON.put("${modelName}Id", ${modelName}.getId());

					rowsJSON.put(rowJSON);
				}

			}
		}

		response.getOutputStream().write(result.toString().getBytes("UTF-8"));
		response.flushBuffer();
		return null;
	}

}
