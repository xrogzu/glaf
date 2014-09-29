<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="html"%>
<%@ page import="java.util.*"%>
<%@ page import="com.glaf.base.modules.*"%>
<%@ page import="com.glaf.base.modules.sys.*"%>
<%@ page import="com.glaf.base.modules.sys.model.*"%>
<%@ page import="com.glaf.base.utils.*"%>
<%@ page import="org.apache.commons.lang3.StringUtils"%>
<%
	String context = request.getContextPath();
	SysDepartment department = (SysDepartment)request.getAttribute("department");
	List list = (List)request.getAttribute("list");
	Set roleId=new HashSet();
	Iterator roles = department.getRoles().iterator();
	while(roles.hasNext()){
	  SysDeptRole deptRole=(SysDeptRole)roles.next();    
	  roleId.add(deptRole.getRole().getId());  
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门角色列表</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/<%=com.glaf.core.util.RequestUtils.getTheme(request)%>/site.css">
<script language="javascript" src='<%=context%>/scripts/verify.js'></script>
<script language="javascript" src='<%=context%>/scripts/main.js'></script>
<script language="javascript">
 
function roleUsers(roleId){
  var url="user.do?method=showRoleUser&deptId=<%=department.getId()%>&roleId=" + roleId;
  var width=600;
  var height=350;
  var scroll="no";
  openWindow(url, width, height, scroll);
}
</script>
</head>
<body>
<div class="nav-title"><span class="Title">部门管理</span>&gt;&gt;<%=department.getName()%>-角色列表</div>
<html:form   method="post" target="_self" onsubmit="return confirm('确认要重新设置吗？')"> 
<input type="hidden" name="deptId" value="<%=department.getId()%>"> 
<input type="hidden" name="id" value="0"> 
<div style="width:100%; height:350px;overflow-x:auto; overflow-y:auto;">
<table width="95%" border="0" align="center" cellspacing="1" cellpadding="0" class="list-box">
  <tr class="list-title" style="position:relative; top:expression(this.offsetParent.scrollTop-2);"> 
    <td width="5%" align="center"> <input type="checkbox" name="chkall" value="checkbox" onClick="checkAll(this.form, this);">    </td>
    <td width="5%" align="center">序号</td>
    <td width="35%" align="center">角色名称</td>
    <td width="40%" align="center">角色用户</td>
  </tr>
  <%
int i=0;
if(list!=null){
  Iterator iter=list.iterator();   
  while(iter.hasNext()){
    SysRole bean=(SysRole)iter.next();
	if (StringUtils.isNotEmpty(bean.getCode())
		&& ( StringUtils.startsWithIgnoreCase(bean.getCode(), SysConstants.BRANCH_PREFIX) || StringUtils.equals(bean.getIsUseBranch(), "Y"))) {			 
%>  
  <tr <%=i%2==0?"":"class='list-back'"%>> 
    <td class="td-cb"> 
	<input type="checkbox" name="id" value="<%=bean.getId()%>" <%=roleId.contains(new Long(bean.getId()))?"checked":""%>>    
	</td>
    <td class="td-no"><%=i+1%></td>
    <td class="td-text"> <%=bean.getName()%>&nbsp; </td>
    <td class="td-text"> 
	<a href="javascript:roleUsers(<%=bean.getId()%>)">用户列表</a>
	</td>
  </tr>
  <%
    i++;
	}
  }
}
for(; i<10; i++){
%>
  <tr <%=i%2==0?"":"class='list-back'"%>> 
    <td height="20">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp; </td>
    <td>&nbsp;</td>
  </tr>
<%
}
%>
</table>
</div> 
</html:form> 
</body>
</html>
