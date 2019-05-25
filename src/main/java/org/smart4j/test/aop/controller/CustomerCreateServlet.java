package org.smart4j.test.aop.controller;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 创建客户
 */
@WebServlet("/customer_create")
public class CustomerCreateServlet extends HttpServlet {

    /**
     * 进入创建客户页面
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        //TODO
    }

    /**
     * 处理创建客户请求
     */

    protected void doPost(HttpServletResponse req,HttpServletResponse resp)
        throws ServletException,IOException {
        //TODO
    }
}
