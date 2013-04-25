package ${packageName}.web.springmvc;

import java.io.IOException;
import java.util.*;
 
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import com.alibaba.fastjson.*;
 
import com.glaf.core.config.ViewProperties;
import com.glaf.core.identity.*;
import com.glaf.core.security.*;
import com.glaf.core.util.*;
 
import ${packageName}.model.*;
import ${packageName}.query.*;
import ${packageName}.service.*;


public class ${entityName}BaseController {
	protected static final Log logger = LogFactory.getLog(${entityName}BaseController.class);

	protected ${entityName}Service ${modelName}Service;

	public ${entityName}BaseController() {

	}

        @javax.annotation.Resource
	public void set${entityName}Service(${entityName}Service ${modelName}Service) {
		this.${modelName}Service = ${modelName}Service;
	}


	@RequestMapping(params = "method=save")
	public ModelAndView save(HttpServletRequest request, ModelMap modelMap) {
		User user = RequestUtils.getUser(request);
		String actorId =  user.getActorId();
		Map<String, Object> params = RequestUtils.getParameterMap(request);
                params.remove("status");
		params.remove("wfStatus");

		${entityName} ${modelName} = new ${entityName}();
		//Tools.populate(${modelName}, params);

 <#if pojo_fields?exists>
    <#list  pojo_fields as field>	
      <#if field.type?exists && ( field.type== 'Integer')>
                ${modelName}.set${field.firstUpperName}(RequestUtils.getInt(request, "${field.name}"));
      <#elseif field.type?exists && ( field.type== 'Long')>
                ${modelName}.set${field.firstUpperName}(RequestUtils.getLong(request, "${field.name}"));
      <#elseif field.type?exists && ( field.type== 'Double')>
                ${modelName}.set${field.firstUpperName}(RequestUtils.getDouble(request, "${field.name}"));
      <#elseif field.type?exists && ( field.type== 'Date')>
                ${modelName}.set${field.firstUpperName}(RequestUtils.getDate(request, "${field.name}"));
      <#elseif field.type?exists && ( field.type== 'String')>
                ${modelName}.set${field.firstUpperName}(request.getParameter("${field.name}"));
      </#if>
    </#list>
</#if>
		
		${modelName}.setCreateBy(actorId);
         
		${modelName}Service.save(${modelName});   

		return this.list(request, modelMap);
	}

        @ResponseBody
	@RequestMapping(params = "method=save${entityName}")
	public byte[] save${entityName}(HttpServletRequest request ) { 
	        User user = RequestUtils.getUser(request);
		String actorId =  user.getActorId();
	        Map<String, Object> params = RequestUtils.getParameterMap(request);
		${entityName} ${modelName} = new ${entityName}();
		try {
		    Tools.populate(${modelName}, params);
 <#if pojo_fields?exists>
    <#list  pojo_fields as field>	
      <#if field.type?exists && ( field.type== 'Integer')>
                    ${modelName}.set${field.firstUpperName}(RequestUtils.getInt(request, "${field.name}"));
      <#elseif field.type?exists && ( field.type== 'Long')>
                    ${modelName}.set${field.firstUpperName}(RequestUtils.getLong(request, "${field.name}"));
      <#elseif field.type?exists && ( field.type== 'Double')>
                    ${modelName}.set${field.firstUpperName}(RequestUtils.getDouble(request, "${field.name}"));
      <#elseif field.type?exists && ( field.type== 'Date')>
                    ${modelName}.set${field.firstUpperName}(RequestUtils.getDate(request, "${field.name}"));
      <#elseif field.type?exists && ( field.type== 'String')>
                    ${modelName}.set${field.firstUpperName}(request.getParameter("${field.name}"));
      </#if>
    </#list>
</#if>
		    ${modelName}.setCreateBy(actorId);
		    this.${modelName}Service.save(${modelName});

		    return ResponseUtils.responseJsonResult(true);
		} catch (Exception ex) {
		    ex.printStackTrace();
		    logger.error(ex);
		}
		return ResponseUtils.responseJsonResult(false);
	}

        @RequestMapping(params = "method=update")
	public ModelAndView update(HttpServletRequest request, ModelMap modelMap) {
		User user = RequestUtils.getUser(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
                params.remove("status");
		params.remove("wfStatus");

		<#if idField.type=='Integer' >
                ${entityName} ${modelName} = ${modelName}Service.get${entityName}(RequestUtils.getInt(request, "rowId"));
		<#elseif idField.type== 'Long' >
                ${entityName} ${modelName} = ${modelName}Service.get${entityName}(RequestUtils.getLong(request, "rowId"));
		<#else>
                ${entityName} ${modelName} = ${modelName}Service.get${entityName}(request.getParameter("rowId"));
		</#if>

 <#if pojo_fields?exists>
    <#list  pojo_fields as field>	
      <#if field.type?exists && ( field.type== 'Integer')>
                ${modelName}.set${field.firstUpperName}(RequestUtils.getInt(request, "${field.name}"));
      <#elseif field.type?exists && ( field.type== 'Long')>
                ${modelName}.set${field.firstUpperName}(RequestUtils.getLong(request, "${field.name}"));
      <#elseif field.type?exists && ( field.type== 'Double')>
                ${modelName}.set${field.firstUpperName}(RequestUtils.getDouble(request, "${field.name}"));
      <#elseif field.type?exists && ( field.type== 'Date')>
                ${modelName}.set${field.firstUpperName}(RequestUtils.getDate(request, "${field.name}"));
      <#elseif field.type?exists && ( field.type== 'String')>
                ${modelName}.set${field.firstUpperName}(request.getParameter("${field.name}"));
      </#if>
    </#list>
</#if>
	
		${modelName}Service.save(${modelName});   

		return this.list(request, modelMap);
	}

        @ResponseBody
        @RequestMapping(params = "method=delete")
	public void delete(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		String rowId = ParamUtils.getString(params, "rowId");
		String rowIds = request.getParameter("rowIds");
		if (StringUtils.isNotEmpty(rowIds)) {
			StringTokenizer token = new StringTokenizer(rowIds, ",");
			while (token.hasMoreTokens()) {
				String x = token.nextToken();
				if (StringUtils.isNotEmpty(x)) {
					${entityName} ${modelName} = ${modelName}Service.get${entityName}(x);
					/**
		                         * 此处业务逻辑需自行调整
		                         */
					 //TODO
					if (${modelName} != null && (StringUtils.equals(${modelName}.getCreateBy(), loginContext.getActorId()) || loginContext.isSystemAdministrator())) {
						${modelName}.setDeleteFlag(1);
						${modelName}Service.save(${modelName});
					}
				}
			}
		} else if (StringUtils.isNotEmpty(rowId)) {
			${entityName} ${modelName} = ${modelName}Service
					.get${entityName}(rowId);
			/**
		         * 此处业务逻辑需自行调整
		         */
		        //TODO
			if (${modelName} != null && ( StringUtils.equals(${modelName}.getCreateBy(), loginContext.getActorId()) || loginContext.isSystemAdministrator())) {
				${modelName}.setDeleteFlag(1);
				${modelName}Service.save(${modelName});
			}
		}
	}


        @ResponseBody
	@RequestMapping(params = "method=detail")
	public byte[] detail(HttpServletRequest request) throws IOException{
	    //RequestUtils.setRequestParameterToAttribute(request);
	    //Map<String, Object> params = RequestUtils.getParameterMap(request);
	    <#if idField.type=='Integer' >
            ${entityName} ${modelName} = ${modelName}Service.get${entityName}(RequestUtils.getInt(request, "rowId"));
	    <#elseif idField.type== 'Long' >
            ${entityName} ${modelName} = ${modelName}Service.get${entityName}(RequestUtils.getLong(request, "rowId"));
	    <#else>
            ${entityName} ${modelName} = ${modelName}Service.get${entityName}(request.getParameter("rowId"));
	    </#if>
		 
	    JSONObject rowJSON =  ${modelName}.toJsonObject();
	    return rowJSON.toJSONString().getBytes("UTF-8");
        }
    

        @RequestMapping(params = "method=edit")
	public ModelAndView edit(HttpServletRequest request, ModelMap modelMap) {
		User user = RequestUtils.getUser(request);
		String actorId =  user.getActorId();
		RequestUtils.setRequestParameterToAttribute(request);
		request.removeAttribute("canSubmit");
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		<#if idField.type=='Integer' >
                ${entityName} ${modelName} = ${modelName}Service.get${entityName}(RequestUtils.getInt(request, "rowId"));
		<#elseif idField.type== 'Long' >
                ${entityName} ${modelName} = ${modelName}Service.get${entityName}(RequestUtils.getLong(request, "rowId"));
		<#else>
                ${entityName} ${modelName} = ${modelName}Service.get${entityName}(request.getParameter("rowId"));
		</#if>
		if(${modelName} != null) {
		    request.setAttribute("${modelName}", ${modelName});
		    JSONObject rowJSON =  ${modelName}.toJsonObject();
		    request.setAttribute("x_json", rowJSON.toJSONString());
		}
	

                boolean canUpdate = false;
		String x_method = request.getParameter("x_method");
		if (StringUtils.equals(x_method, "submit")) {
			 
		}

		if (StringUtils.containsIgnoreCase(x_method, "update")) {
			if (${modelName} != null) {
				if (${modelName}.getStatus() == 0
						|| ${modelName}.getStatus() == -1) {
					canUpdate = true;
				}
			}
		}

		 
		request.setAttribute("canUpdate",  canUpdate);

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		String x_view = ViewProperties.getString("${modelName}.edit");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/apps/${modelName}/edit", modelMap);
	}

        @RequestMapping(params = "method=view")
	public ModelAndView view(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		<#if idField.type=='Integer' >
                ${entityName} ${modelName} = ${modelName}Service.get${entityName}(RequestUtils.getInt(request, "rowId"));
		<#elseif idField.type== 'Long' >
                ${entityName} ${modelName} = ${modelName}Service.get${entityName}(RequestUtils.getLong(request, "rowId"));
		<#else>
                ${entityName} ${modelName} = ${modelName}Service.get${entityName}(request.getParameter("rowId"));
		</#if>
		request.setAttribute("${modelName}", ${modelName});
		JSONObject rowJSON =  ${modelName}.toJsonObject();
		request.setAttribute("x_json", rowJSON.toJSONString());


		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view);
		}

		String x_view = ViewProperties.getString("${modelName}.view");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view);
		}

		return new ModelAndView("/apps/${modelName}/view");
	}

        @RequestMapping(params = "method=query")
	public ModelAndView query(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}
		String x_view = ViewProperties.getString("${modelName}.query");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}
		return new ModelAndView("/apps/${modelName}/query", modelMap);
	}

	@RequestMapping(params = "method=json")
	@ResponseBody
	public byte[] json(HttpServletRequest request, ModelMap modelMap) throws IOException {
	        LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		${entityName}Query query = new ${entityName}Query();
		Tools.populate(query, params);
		query.deleteFlag(0);
		/**
		 * 此处业务逻辑需自行调整
		*/
		if(!loginContext.isSystemAdministrator()){
		  String actorId = loginContext.getActorId();
		  query.createBy(actorId);
		}

                String gridType = ParamUtils.getString(params, "gridType");
		if (gridType == null) {
			gridType = "easyui";
		}
		int start = 0;
		int limit = 10;
		String orderName = null;
		String order = null;
	 
		int pageNo = ParamUtils.getInt(params, "page");
		limit = ParamUtils.getInt(params, "rows");
		start = (pageNo - 1) * limit;
		orderName = ParamUtils.getString(params, "sortName");
		order = ParamUtils.getString(params, "sortOrder");
		 

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

			Map<String, User> userMap = IdentityFactory.getUserMap();
			List<${entityName}> list = ${modelName}Service.get${entityName}sByQueryCriteria(start, limit,
					query);

			if (list != null && !list.isEmpty()) {
				JSONArray rowsJSON = new JSONArray();
				 
				result.put("rows", rowsJSON);
				 
				for (${entityName} ${modelName} : list) {
					JSONObject rowJSON = ${modelName}.toJsonObject();
					rowJSON.put("id", ${modelName}.getId());
					rowJSON.put("${modelName}Id", ${modelName}.getId());
                                        rowJSON.put("startIndex", ++start);
 					rowsJSON.add(rowJSON);
				}

			}
		}
		return result.toJSONString().getBytes("UTF-8");
	}

        @RequestMapping 
	public ModelAndView list(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		String x_query = request.getParameter("x_query");
		if (StringUtils.equals(x_query, "true")) {
			Map<String, Object> paramMap = RequestUtils.getParameterMap(request);
			String x_complex_query = JsonUtils.encode(paramMap);
			x_complex_query = RequestUtils.encodeString(x_complex_query);
			request.setAttribute("x_complex_query", x_complex_query);
		} else {
			request.setAttribute("x_complex_query", "");
		}
		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}
 

		return new ModelAndView("/apps/${modelName}/list", modelMap);
	}

}
