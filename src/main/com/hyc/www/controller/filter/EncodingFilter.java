/*
 * Copyright (c) 2019.  黄钰朝
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hyc.www.controller.filter;

import com.hyc.www.controller.constant.Pages;
import com.hyc.www.exception.ServiceException;
import com.sun.xml.internal.ws.addressing.WsaActionUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program XHotel
 * @description 负责设置编码格式
 */

@WebFilter(
        filterName = "EncodingFilter",
        urlPatterns = {"/*"}, servletNames = {"/*"},
        initParams = {
                @WebInitParam(name = "ENCODING", value = "UTF-8")
        }
)
public class EncodingFilter implements Filter {

    private String ENCODING = null;

    @Override
    public void init(FilterConfig config) {
        this.ENCODING = config.getInitParameter("ENCODING");
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        req.setCharacterEncoding(ENCODING);
        try {
            resp.setContentType("text/html;charset=utf-8");
            resp.setCharacterEncoding(ENCODING);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (IOException | ServiceException e) {
            resp.sendRedirect(Pages.ERROR_JSP.toString());
        }

        //TODO debug
        System.out.println("编码过滤器：" + "method = " + req.getParameter("method") + " view = " + req.getParameter("view") + " find = " + req.getParameter("find") + " name = " + req.getParameter("name"));
        System.out.println("用户名：" + req.getParameter("name"));
        System.out.println("电话号码"+req.getParameter("phoneNumber"));
        System.out.println("昵称"+req.getParameter("nickName"));
        System.out.println("留言"+req.getParameter("remark"));
        System.out.println("请求链接：" + req.getQueryString());
    }
}

