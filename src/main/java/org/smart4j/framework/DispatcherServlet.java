package org.smart4j.framework;

/**
 * 请求转发器
 *
 *
 * @author
 * @since 1.0.0
 */

import com.sun.deploy.util.StringUtils;
import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;
import org.smart4j.framework.helper.*;
import org.smart4j.framework.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求转发器
 */
@WebServlet (urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //初始化相关 Helper 类
        HelperLoader.init();
        //获取 ServletContext 对象（用于注册 Servlet）
        ServletContext servletContext = servletConfig.getServletContext();
        //注册处理JSP的Servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAPPJspPath() + "*");
        //注册处理静态资源的默认Servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");

        UploadHelper.init(servletContext);

    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.init(request, response);
        try {
            //获取请求方法与请求路径
            String requestMethod = request.getMethod().toLowerCase();
            String requestPath = request.getPathInfo();

            if (requestPath.equals("/favicon.ico")) {
                return;
            }
            //获取Action处理器
            Handler handler = ControllerHelper.getHandler(requestMethod,requestPath);
            if (handler != null) {
                //获取 Controller 类及其Bean实例
                Class<?> controllerClass = handler.getControllerClass();
                Object controllerBean = BeanHelper.getBean(controllerClass);

                Param param;
                if(UploadHelper.isMultipart(request)) {
                    param = UploadHelper.createParam(request);
                } else {
                    param = RequestHelper.createParam(request);
                }
//            //创建请求参数对象
//            Map<String, Object> paramMap = new HashMap<String, Object>();
//            Enumeration<String> paramNames = request.getParameterNames();
//            while (paramNames.hasMoreElements()) {
//                String paramName = paramNames.nextElement();
//                String paramValue = request.getParameter(paramName);
//                paramMap.put(paramName,paramValue);
//            }
//            String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
//            if(StringUtil.isNotEmpty(body)) {
//                String[] params = StringUtils.splitString(body,"&");
//                if(ArrayUtil.isNotEmpty(params)) {
//                    for (String param : params) {
//                        String[] array = StringUtils.splitString(param,"=");
//                        if(ArrayUtil.isNotEmpty(array) && array.length==2) {
//                            String paramName = array[0];
//                            String paramValue = array[1];
//                            paramMap.put(paramName, paramValue);
//                        }
//                    }
//                }
//            }
//            Param param = new Param(paramMap);
                //调用 Action方法
                Method actionMethod = handler.getActionMethod();
                //Object result = ReflectionUtil.invokeMethod(controllerBean,actionMethod,param);

                Object result;

                if (param.isEmpty()) {
                    result = ReflectionUtil.invokeMethod(controllerBean,actionMethod);
                } else {
                    result = ReflectionUtil.invokeMethod(controllerBean,actionMethod,param);
                }
                //处理 Action方法返回值
                if (result instanceof View) {
                    //返回JSP 页面
                    handleViewResult((View) result, request, response);
                } else if (result instanceof Data) {
                    //返回JSON 数据
                    handleDataResult((Data) result, response);
                }
            }
        } finally {
            ServletHelper.destroy();
        }
    }



    private void handleViewResult(View view, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String path = view.getPath();
        if(StringUtil.isNotEmpty(path)) {
            if (path.startsWith("/")) {
                response.sendRedirect(request.getContextPath() + path);
            } else {
                Map<String, Object> model = view.getModel();
                for (Map.Entry<String, Object> entry : model.entrySet()) {
                    request.setAttribute(entry.getKey(), entry.getValue());
                }
                request.getRequestDispatcher(ConfigHelper.getAPPJspPath() + path).forward(request, response);
            }
        }

    }

    private void handleDataResult(Data data, HttpServletResponse response) throws IOException {
        Object model = data.getModel();
        if(model != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            String json = JsonUtil.toJson(model);
            writer.write(json);
            writer.flush();
            writer.close();
        }
    }

}
