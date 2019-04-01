<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.im731.servmgr.ServerStatus" %><%--
  Created by IntelliJ IDEA.
  User: zzh
  Date: 2019/3/13
  Time: 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>ServerManager</title>
</head>
<body>
<h1>服务器管理系统</h1>
用户：zzh，现在时间：<%= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())%>
<br>
<form action="query.do" method="post">
  <input type="submit" value="刷新">
</form>

<table border="1" cellspacing="0" cellpadding="10">

  <tr>
    <th>ID</th>
    <th>描述</th>
    <th>IP</th>
    <th>上次更新</th>
    <th>状态</th>
    <th>管理</th>
  </tr>
  <%
    List<ServerStatus> serverStatuses = (List<ServerStatus>) request.getAttribute("status");
    if (serverStatuses != null) {
      for (ServerStatus status : serverStatuses) {
        out.print("<tr>");
        out.print("<td>");
        out.print(status.getId());
        out.print("</td>");
        out.print("<td>");
        out.print(status.getDescrb());
        out.print("</td>");
        out.print("<td>");
        out.print(status.getNowIP());
        out.print("</td>");
        out.print("<td>");
        out.print(status.getLastReportTime());
        out.print("</td>");
        out.print("<td>");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowTime = new Date();
        Date lastReportTime = simpleDateFormat.parse(status.getLastReportTime());
        long betweenSeconds = (nowTime.getTime() - lastReportTime.getTime()) / 1000;//get seconds
        if (betweenSeconds > 30) {
          out.print("离线");
        } else {
          out.print("在线");
        }
        if (!status.getNowIP().equals(status.getLastIP())) {
          out.print("<br>IP地址有变化");
        }
        out.print("</td>");
        out.print("<td>");
        out.print("<a href=\"detail.do?id="+status.getId()+"\">查看详情</a>");
        out.print("</td>");
        out.print("</tr>");
      }
    }
  %>
</table>

<h4>测试用表格：</h4>
<form action="report.do" method="post">
  <table border="1" cellspacing="0" cellpadding="10">
    <tr>
      <td>
        id
      </td>
      <td>
        <input type="text" name="id">
      </td>
    </tr>
    <tr>
      <td>
        描述
      </td>
      <td>
        <input type="text" name="descrb">
      </td>
    </tr>
    <tr>
      <td>
        ip
      </td>
      <td>
        <input type="text" name="ip">
      </td>
    </tr>
    <tr>
      <td>
        frp配置
      </td>
      <td>
        <input type="text" name="frp_config">
      </td>
    </tr>
    <tr>
      <td>
        其它
      </td>
      <td>
        <input type="text" name="others">
      </td>
    </tr>
  </table>
  <input type="submit" value="提交">
</form>

</body>
</html>
