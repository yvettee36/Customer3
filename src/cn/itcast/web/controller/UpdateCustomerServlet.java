package cn.itcast.web.controller;

import cn.itcast.domain.Customer;
import cn.itcast.service.BusinessService;
import cn.itcast.service.impl.BusinessServiceImpl;
import cn.itcast.utils.Globals;
import cn.itcast.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateCustomerServlet", urlPatterns = "/servlet/UpdateCustomerServlet")
public class UpdateCustomerServlet extends HttpServlet {

    //根据id获取要修改的客户信息（用get方式获取信息修改，post方式提交修改）
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        BusinessService service = new BusinessServiceImpl();
        Customer customer = service.findCustomer(id);

        request.setAttribute("genders", Globals.genders);
        request.setAttribute("preferences", Globals.preferences);
        request.setAttribute("types", Globals.types);

        request.setAttribute("c", customer);
        request.getRequestDispatcher("/WEB-INF/jsp/updateCustomer.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //把填写的表单修改信息封装到customer对象中
        try {
            Customer customer = WebUtils.request2Bean(request, Customer.class);
            BusinessService service = new BusinessServiceImpl();
            service.update(customer);

            request.setAttribute("message", "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "更新失败");
        }
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }
}
