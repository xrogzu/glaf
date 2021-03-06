<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="html"%>
 
<%@ page import="java.util.*"%>
<%@ page import="com.glaf.base.utils.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link href="../../css/site.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/<%=com.glaf.core.util.RequestUtils.getTheme(request)%>/site.css">
<script language="javascript" src="<%= request.getContextPath() %>/scripts/css.js"></script>
<script language="javascript" src='<%= request.getContextPath() %>/scripts/main.js'></script>
<script type="text/JavaScript">
</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="m-box">
  <tr>
    <td width="49%" class="nav-title">查看审核意见</td>
    <td width="51%" height="27" align="right">&nbsp;&nbsp;&nbsp;</td>
  </tr>
</table> 
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" class="box">
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr class="box">
        <td class="box-lt">&nbsp;</td>
        <td class="box-mt">&nbsp;</td>
        <td class="box-rt">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td class="box-mm"><table width="100%" border="0" cellspacing="8" cellpadding="0">
      <tr>
        <td><jsp:include page="/others/audit.do?method=showList" flush="true">
          <jsp:param name="referId" value="<%= ParamUtil.getLongParameter(request, "referId", 0) %>"/>    
          <jsp:param name="referType" value="<%= ParamUtil.getIntParameter(request, "referType", 0) %>"/>
          <jsp:param name="height" value="auto"/>
          </jsp:include>
        </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr class="box">
        <td class="box-lb">&nbsp;</td>
        <td class="box-mb">&nbsp;</td>
        <td class="box-rb">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" align="center"><input name="close" type="button" class="button" id="close" value="关闭" onClick="javascript:self.close();"></td>
  </tr>
</table>
</body>
</html>
