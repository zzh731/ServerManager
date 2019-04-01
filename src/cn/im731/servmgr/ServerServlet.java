package cn.im731.servmgr;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//@WebServlet(name = "ServerServlet", urlPatterns = "*.do")
public class ServerServlet extends HttpServlet {

    private ServerStatusDAO serverStatusDAO = new ServerStatusDAOJdbcImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");

        String servletPath = request.getServletPath();// /add.do
        String methodName = servletPath.substring(1, servletPath.length() - 3);// add
        System.out.println("调用了"+methodName);

        response.setContentType("text/html; charset=utf-8");

        try {
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("调用了错误方法！");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ServerStatus> serverStatuses = serverStatusDAO.getAll();
        request.setAttribute("status", serverStatuses);
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }

    private void report(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String ip = request.getParameter("ip");
        String lastReportTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String frpConfigure = request.getParameter("frp_config");
        String others = request.getParameter("others");
        String descrb = request.getParameter("descrb");

        ServerStatus serverStatus = new ServerStatus(id, descrb, ip, ip, frpConfigure, lastReportTime, others);
        serverStatusDAO.report(serverStatus);
        response.sendRedirect("index.jsp");
    }



    private void detail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        ServerStatus serverStatus = serverStatusDAO.getById(id);
        if (serverStatus == null) {
            request.setAttribute("msg", "id"+id+"不存在!");
            request.getRequestDispatcher("/detail.jsp").forward(request,response);
            return;
        }
        request.setAttribute("serverStatus", serverStatus);
        request.getRequestDispatcher("/detail.jsp").forward(request,response);
    }

    private void ackAlert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        serverStatusDAO.ackAlert(id);
        response.sendRedirect("detail.jsp?id="+id);
    }
}
