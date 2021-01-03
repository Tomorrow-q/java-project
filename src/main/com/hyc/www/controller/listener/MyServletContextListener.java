
package com.hyc.www.controller.listener;

import com.hyc.www.service.inter.OrderRoomService;
import com.hyc.www.service.inter.RemarkService;
import com.hyc.www.service.inter.RoomService;
import com.hyc.www.service.inter.UserService;
import com.hyc.www.util.BeanFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @program XHotel
 * @description 负责监听servlet的初始化和销毁事件
 */

@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /**
         * 注册服务单例
         */
        ServletContext sc = sce.getServletContext();
        UserService userService = (UserService) BeanFactory.getBean(BeanFactory.ServiceType.UserService);
        sc.setAttribute("userService",userService);
        RoomService roomService = (RoomService) BeanFactory.getBean(BeanFactory.ServiceType.RoomService);
        sc.setAttribute("roomService",roomService);
        OrderRoomService orderRoomService = (OrderRoomService)BeanFactory.getBean(BeanFactory.ServiceType.OrderRoomService);
        sc.setAttribute("orderRoomService",orderRoomService);
        RemarkService remarkService = (RemarkService) BeanFactory.getBean((BeanFactory.ServiceType.RemarkService));
        sc.setAttribute("remarkService",remarkService);
        System.out.println("该对象被创建了。。。。。。。");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("对象被销毁了。。。。");
    }
}
