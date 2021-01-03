
package com.hyc.www.controller.servlet;

import com.hyc.www.controller.constant.Methods;
import com.hyc.www.controller.constant.Pages;
import com.hyc.www.exception.ServiceException;
import com.hyc.www.service.Result;
import com.hyc.www.service.constant.Status;
import com.hyc.www.service.inter.RoomService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.hyc.www.service.constant.Status.NO_RESULT;
import static com.hyc.www.service.constant.Status.SUCCESS;
import static com.hyc.www.util.ControllerUtils.*;

/**
 * @program XHotel
 * @description 负责房间相关服务和显示请求的转发
 */

@MultipartConfig(location = "F:\\hotelSystem\\src\\main\\webapp\\file\\photo")
@WebServlet(value = "/room")
public class RoomServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Methods method = getMethod(req, resp);
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        switch (method) {
            case ADD_DO:
                add(req, resp);
                return;
            case DELETE_DO:
                delete(req, resp);
                return;
            case UPDATE_DO:
                update(req, resp);
                return;
            case FIND_DO:
                find(req, resp);
                return;
            default:
                redirect(resp, Pages.INDEX_JSP.toString());
        }

    }


    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoomService serv = (RoomService) getServletContext().getAttribute("roomService");
        Result result  = serv.add(req, resp);
       Status status = result.getStatus();
        switch (status) {
            case DATA_ILLEGAL:
                forward(req, resp, result.getData(), "数据不合法！", Pages.ROOM_JSP);
                return;
            case ROOM_ALREADY_EXIST:
                forward(req, resp, result.getData(), "该房间编号已经存在！", Pages.ROOM_JSP);
                return;
            case SUCCESS:
                forward(req, resp, result.getData(), "房间添加成功！", Pages.ROOM_JSP);
                return;
            default:
        }

    }
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoomService serv = (RoomService) getServletContext().getAttribute("roomService");
        Result result  = serv.delete(req, resp);
        Status status = result.getStatus();
        switch (status) {
            case SUCCESS:
                redirect(resp, Pages.INDEX_JSP.toString());
                return;
            case ERROR:
                redirect(resp, Pages.ERROR_JSP.toString());
                return;
            default:

        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoomService serv = (RoomService) getServletContext().getAttribute("roomService");
//        req.setAttribute("room_id");
        String message = null;
        Status status = null;
        Result result  = null;

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        try {
             result  = serv.update(req, resp);
            status = result.getStatus();
        } catch (Exception e) {
            message = "上传图片不成功";
            e.printStackTrace();
        }

        switch (status) {
            case DATA_ILLEGAL:
                forward(req, resp, result.getData(), "数据不合法！", Pages.ROOM_JSP);
                return;
            case SUCCESS:
                redirect(resp, Pages.ROOM_JSP.toString() + "?view=update&message=" + message + "&number=" + req.getParameter("number"));
                return;
            case ERROR:
                redirect(resp, Pages.ERROR_JSP.toString());
                return;
            default:
        }

    }

    /**
     * 需要条件指令，如果没有默认查询为所有房间
     *
     * @param req
     * @param resp
     */
    private void find(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RoomService serv = (RoomService) getServletContext().getAttribute("roomService");
        Status status = null;
        Result result = null;
        findType type = findType.valueOf(req.getParameter("find").toUpperCase());
        switch (type) {
            case NAME:
                result=serv.listByName(req, resp);
                status = result.getStatus();
                if (status == SUCCESS) {
                    forward(req, resp, result.getData(), "查询的结果如下", Pages.INDEX_JSP);
                }else if(status==NO_RESULT){
                    forward(req,resp,result.getData(),"没有相关房间！",Pages.INDEX_JSP);
                }
                return;
            case ALL:
                result=serv.listByName(req, resp);
                status = result.getStatus();
                if (status == SUCCESS && result.getData() != null) {
                    forward(req, resp, result.getData(), req.getParameter("message"), Pages.INDEX_JSP);
                }
                return;
            case THIS:
                result=serv.find(req, resp);
                status = result.getStatus();
                if (status == SUCCESS && result.getData() != null) {
                    forward(req, resp, result.getData(), req.getParameter("message"), Pages.ROOM_JSP);
                    return;
                } else {
                    break;
                }
            default:
        }
        resp.sendRedirect(Pages.ERROR_JSP.toString());
    }

    enum findType {
        /**
         * 通过名称模糊查找
         */
        NAME,
        /**
         * 查找全部
         */
        ALL,
        /**
         * 查找这一个房间
         */
        THIS,
    }


}
