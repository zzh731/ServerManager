<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="cn.im731.servmgr.ServerStatus" %><%--
  Created by IntelliJ IDEA.
  User: zzh
  Date: 2019/3/13
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>服务器详情</title>
</head>
<body>
<h1>服务器状态详情</h1>
用户：zzh，现在时间：<%= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())%>
<%
    String msg = (String) request.getAttribute("msg");
    if (msg != null) {
        out.print("<h1><"+msg+"/h1>");
    }
    ServerStatus serverStatus = (ServerStatus) request.getAttribute("serverStatus");
    if (serverStatus != null) {
%>
    <table border="1" cellpadding="10" cellspacing="0">
        <tr>
            <th>
                服务器ID
            </th>
            <td>
                <%=serverStatus.getId()%>
            </td>
        </tr>
        <tr>
            <th>
                服务器描述
            </th>
            <td>
                <%=serverStatus.getDescrb()%>
            </td>
        </tr>
        <tr>
            <th>
                最后在线时间
            </th>
            <td>
                <%=serverStatus.getLastReportTime()%>
            </td>
        </tr>
        <tr>
            <th>
                IP地址
            </th>
            <td>
                <%=serverStatus.getNowIP()%>
                <%
                    if (!serverStatus.getNowIP().equals(serverStatus.getLastIP())) {
                        out.print("<br>IP地址有变化<br>上次是"+serverStatus.getLastIP());
                    }
                %>
            </td>
        </tr>
        <tr>
            <th>
                FRP配置
            </th>
            <td>
                <textarea rows="7" cols="20">
                <%=serverStatus.getFrpConfigure()%>
                </textarea>
            </td>
        </tr>
        <tr>
            <th>
                其它信息
            </th>
            <td>
                <%=serverStatus.getOthers()%>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <form action="ackAlert.do" method="post">
                    <input type="hidden" name="id" value="<%=serverStatus.getId()%>">
                    <input type="submit" value="清除提醒">
                </form>
            </td>
        </tr>
    </table>

<%
    }
%>
<a href="index.jsp">回首页</a>
</body>
</html>
