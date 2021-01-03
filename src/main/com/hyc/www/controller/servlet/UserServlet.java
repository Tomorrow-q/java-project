
package com.hyc.www.controller.servlet;

import com.hyc.www.controller.constant.CacheConst;
import com.hyc.www.controller.constant.Methods;
import com.hyc.www.controller.constant.Pages;
import com.hyc.www.po.User;
import com.hyc.www.service.Result;
import com.hyc.www.service.constant.Status;
import com.hyc.www.service.inter.UserService;
import com.hyc.www.util.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.hyc.www.controller.constant.CacheConst.*;
import static com.hyc.www.service.constant.Status.NOT_FOUNT;
import static com.hyc.www.service.constant.Status.SUCCESS;
import static com.hyc.www.util.ControllerUtils.*;


/**
 * @program XHotel
 * @description 负责接收与用户信息相关业务的请求
 * @date
 */
@MultipartConfig(location = "F:\\hotelSystem\\src\\main\\webapp\\file\\photo")
@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        Methods method = getMethod(req, resp);
//        String phoneNumber =  req.getParameter("phoneNumber");
//        String idNumber =  req.getParameter("idNumber");
//        String nickName =  req.getParameter("nickName");
        Map<String, String[]> map = req.getParameterMap();

        switch (method) {
            case REGIST_DO:
                regist(req, resp);
                return;
            case LOGIN_DO:
                login(req, resp);
                return;
            case FIND_DO:
                find(req, resp);
                return;
            case UPDATE_DO:
                update(req, resp);
                return;
            case LOGOUT_DO:
                logout(req, resp);
                return;
            case ADD_DO:
                add(req, resp);
                return;
            default:
                resp.sendRedirect(Pages.INDEX_JSP.toString());
        }

    }


    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService serv = (UserService) getServletContext().getAttribute("userService");
        Result result = serv.add(req, resp);
        Status status = result.getStatus();

        switch (status) {
            case DATA_ILLEGAL:
                forward(req, resp, result.getData(), "数据不合法！", Pages.USER_JSP);
                return;
            case ACCOUNT_ALREADY_EXIST:
                forward(req, resp, result.getData(), "该账户已经存在！", Pages.USER_JSP);
                return;
            case SUCCESS:
                forward(req, resp, result.getData(), "用户添加成功！", Pages.USER_JSP);
                return;
            default:
        }

    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        forward(req, resp, null, "您已成功退出登陆！", Pages.LOGIN_JSP);
    }


    private void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService serv = (UserService) getServletContext().getAttribute("userService");
        Result result = serv.regist(req, resp);
        Status status = result.getStatus();

//        Map<String,Object> map=new HashMap<String, Object>();
        switch (status) {
            case ACCOUNT_ALREADY_EXIST:

                forward(req, resp, result.getData(), "此账户已经存在！", Pages.REGIST_JSP);
                return;
            case DATA_ILLEGAL:
                forward(req, resp, result.getData(), "输入不合法！(6-20位英文字母，数字或下划线)", Pages.REGIST_JSP);
                return;
            case SUCCESS:
                forward(req,resp,result.getData(),"注册成功！请使用用户名登陆！",Pages.LOGIN_JSP);
            default:
        }

    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService serv = (UserService) getServletContext().getAttribute("userService");
        Result result = serv.login(req, resp);
        Status status = result.getStatus();

        switch (status) {
            case NOT_FOUNT:
                forward(req, resp, result.getData(), "找不到该用户！", Pages.LOGIN_JSP);
                return;
            case DATA_ILLEGAL:
                forward(req, resp, result.getData(), "输入不合法！(6-20位英文字母，数字或下划线)", Pages.LOGIN_JSP);
                return;
            case PASSWORD_INCORRECT:
                forward(req, resp, result.getData(), "密码不正确！", Pages.LOGIN_JSP);

                return;
            case SUCCESS:
                String userType = result.getData().getUsers().get(0).getType();
                String userName = result.getData().getUsers().get(0).getName();
                /**
                 * 用户登陆
                 */
                HttpSession session = req.getSession();
//                缓存枚举
                CacheConst type = CacheConst.valueOf(userType);
                switch (type) {
                    case USER:
                        if (TRUE.toString().equalsIgnoreCase(req.getParameter(AUTO_LOGIN.toString().toLowerCase()))) {

                            /**
                             * 设置自动登陆cookie
                             */
                            setCookie(resp, USER, userName);
                        }
                        session.setAttribute(USER.toString(), userName);
                        break;
                    case ADMIN:
                        if (TRUE.toString().equalsIgnoreCase(req.getParameter(AUTO_LOGIN.toString().toLowerCase()))) {
                            setCookie(resp, USER, userName);
                            setCookie(resp, ADMIN, userName);
                        }
                        session.setAttribute(USER.toString(), userName);
                        session.setAttribute(ADMIN.toString(), userName);
                        break;
                    case SUPER:
                        break;
                    default:
                }
                forward(req,resp,null,"登陆成功！欢迎回来！",Pages.INDEX_JSP);
            default:
        }
    }

    private static void setCookie(HttpServletResponse resp, CacheConst userType, String userName) {
        /**
         * 设置自动登陆cookie
         */
        Cookie userCookie = new Cookie(userType.toString(), userName);
        userCookie.setMaxAge(60 * 60 * 24 * 30);
        resp.addCookie(userCookie);
    }


    private void find(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService serv = (UserService) getServletContext().getAttribute("userService");
        FindType type = FindType.valueOf(req.getParameter("find").toUpperCase());
        Result result = null;
        Status status = null;
        switch (type) {
            case THIS:
                result = serv.find(req, resp);
                status = result.getStatus();
                if (status == SUCCESS) {
                    forward(req, resp, result.getData(), null, Pages.USER_JSP);
                } else if (status == NOT_FOUNT) {
                    forward(req, resp, result.getData(), "找不到该用户！", Pages.USER_JSP);
                }
                return;
            case ALL:
                result = serv.listAll(req, resp);
                status = result.getStatus();

                if (status == SUCCESS) {
                    forward(req, resp, result.getData(), null, Pages.INDEX_JSP);
                }
                return;
            default:
                redirect(resp, Pages.ERROR_JSP.toString());
        }

    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        Part part = req.getPart("photo");
//
//        //上传文件名 head1.jpg
//        String fileName=part.getSubmittedFileName();
//
//        //创建保存文件目录
////        E:\Mysoft\TomCat\apache-tomcat-8.5.57\webapps\offers_war_exploded
//        String dir = this.getServletContext().getRealPath("/file/photo");
//        //判断 目录是否存在
//        File imgDir=new File(dir);
//        if(!imgDir.exists()){
//            imgDir.mkdirs();
//        }
        //上传到服务器文件路径 imgDir+'/'+fileName
//        part.write(dir+"/"+fileName);
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String phoneNumber = req.getParameter("phoneNumber");
        String idNumber = req.getParameter("idNumber");
        String nickName = req.getParameter("nickName");

        UserService serv = (UserService) getServletContext().getAttribute("userService");
        UpdateType type = UpdateType.valueOf(req.getParameter("update").toUpperCase());
        Status status = null;
        Result result = null;
        switch (type) {
            case INFO:
                result = serv.updateInfo(req,resp);
                status = result.getStatus();
                break;
            case PWD:
                result = serv.updatePwd(req,resp);
                status = result.getStatus();
                break;
            case PAY_PWD:
                result = serv.updatePayPwd(req,resp);
                status = result.getStatus();
                break;
            default:
                break;
        }
        switch (status) {

            case DATA_ILLEGAL:
                forward(req, resp, result.getData(), "输入不合法!", Pages.USER_JSP);
                return;
            case PASSWORD_INCORRECT:
                forward(req, resp, result.getData(), "密码不正确！", Pages.USER_JSP);
            case SUCCESS:
                String name = result.getData().getUsers().get(0).getName();
                forward(req,resp,result.getData(),"更新数据成功！",Pages.USER_JSP);
                return;
            default:
        }

    }


    enum UpdateType {
        /**
         * 更新个人信息，不包括密码
         */
        INFO,
        /**
         * 更新登陆密码
         */
        PWD,
        /**
         * 更新支付密码
         */
        PAY_PWD,

    }

    enum FindType {
        /**
         * 查找个人信息
         */
        THIS,
        /**
         * 查找所有用户
         */
        ALL,
    }

}
