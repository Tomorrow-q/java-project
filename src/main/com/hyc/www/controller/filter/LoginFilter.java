
package com.hyc.www.controller.filter;

import com.hyc.www.controller.constant.Methods;
import com.hyc.www.controller.constant.Pages;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;

import static com.hyc.www.controller.constant.CacheConst.ADMIN;
import static com.hyc.www.controller.constant.CacheConst.USER;
import static com.hyc.www.util.ControllerUtils.redirect;

/**
 * @program
 * @description 负责过滤需要登陆的页面的请求
 */
//  /*   表示访问所有资源时都会执行此过滤器,还有所有服务器
@WebFilter(
        filterName = "LoginFilter",
        urlPatterns = {"/*"}, servletNames = {"/*"},
        initParams = {
                @WebInitParam(name = "ENCODING", value = "UTF-8")
        })
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String method= req.getParameter("method");
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = uri.substring(contextPath.length());
        String servletPath = req.getServletPath();
        if (!(("/"+Pages.LOGIN_JSP.toString()).equalsIgnoreCase(path)
                || ("/"+Pages.REGIST_JSP.toString()).equalsIgnoreCase(path)
                || (Methods.LOGIN_DO.toString()).equalsIgnoreCase(method)
                || (Methods.REGIST_DO.toString()).equalsIgnoreCase(method)
                ||(servletPath.equals("/useregister"))
                || uri.endsWith("jpg"))) {
            /**
             * 如果session不存在则检查cookie
             */
            HttpSession sess = req.getSession(false);
            if (sess == null) {
                /**
                 * 如果有记住登陆状态的cookie,则给session添加'user'属性
                 */
                Cookie[] cookies = req.getCookies();
                if(cookies!=null){
                    for (Cookie cookie : cookies) {
                        String name = cookie.getName();
                        if (USER.toString().equalsIgnoreCase(name)) {
                            sess = req.getSession(true);
                            sess.setAttribute(USER.toString(), cookie.getValue());
                        }
                        if(ADMIN.toString().equalsIgnoreCase(name)){
                            sess=req.getSession(true);
                            sess.setAttribute(ADMIN.toString(),cookie.getValue());
                        }
                    }
                }
            }
            /**
             * 检查session是否有'user',没有则重定向到登陆界面
             */
            if (sess == null || sess.getAttribute(USER.toString()) == null) {
                redirect(resp, Pages.LOGIN_JSP.toString());
                return;
            }
        }
        //放行,执行过滤器后该页面可以执行
        filterChain.doFilter(req, resp);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}
