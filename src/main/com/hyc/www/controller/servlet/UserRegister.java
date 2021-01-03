
package com.hyc.www.controller.servlet;

import com.google.gson.Gson;
import com.hyc.www.controller.constant.Methods;
import com.hyc.www.controller.constant.Pages;
import com.hyc.www.dao.inter.UserDao;
import com.hyc.www.po.User;
import com.hyc.www.service.Result;
import com.hyc.www.service.constant.Status;
import com.hyc.www.service.inter.OrderRoomService;
import com.hyc.www.service.inter.UserService;
import com.hyc.www.util.BeanFactory;
import com.hyc.www.util.BeanUtils;
import com.hyc.www.util.ServiceUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.hyc.www.service.constant.Status.*;
import static com.hyc.www.service.constant.Status.ERROR;
import static com.hyc.www.util.ControllerUtils.forward;
import static com.hyc.www.util.ControllerUtils.getMethod;
import static com.hyc.www.util.Md5Utils.getDigest;
import static com.hyc.www.util.ServiceUtils.isValidRegist;
import static com.hyc.www.util.ServiceUtils.setResult;
import static com.hyc.www.util.UUIDUtils.getUUID;

@WebServlet("/useregister")
public class UserRegister extends HttpServlet {
    private UserDao dao = (UserDao) BeanFactory.getBean(BeanFactory.DaoType.UserDao);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json;charset=UTF-8");
//        System.out.println("进来了");
        UserService serv = (UserService) getServletContext().getAttribute("userService");
        String username=req.getParameter("username");
//        System.out.println("打印名字："+username);
       /*
       ajax判断
        */
        Result result = serv.regist(req, resp);
        Result result1=serv.isExsitUser(req,resp);
        //判断用户名
//        boolean result1=ServiceUtils.isValidUserName(username);
        Status status = result1.getStatus();
//        Map<String,String> map=new HashMap<String, String>();
//        System.out.println("测试："+status);

        switch (status) {
            case ACCOUNT_ALREADY_EXIST:
//                map.put()
                resp.getWriter().write("0");
                break;
            case DATA_ILLEGAL:
                resp.getWriter().write("1");
//                forward(req, resp, result.getData(), "输入不合法！(6-20位英文字母，数字或下划线)", Pages.REGIST_JSP);
//                return;
                break;
            case SUCCESS:
                resp.getWriter().write("2");
//                forward(req,resp,result.getData(),"注册成功！请使用用户名登陆！",Pages.LOGIN_JSP);
            default:
//                resp.getWriter().print("2");
        }


    }

}
