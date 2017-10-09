package cn.itcast.web.controller;

import cn.itcast.service.BusinessService;
import cn.itcast.service.impl.BusinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteCustomerServlet", urlPatterns = "/servlet/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            BusinessService service = new BusinessServiceImpl();
            service.delete(id);
            request.setAttribute("message", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "删除失败");
        }
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
