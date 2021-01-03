
package com.hyc.www.service.impl;

import com.hyc.www.dao.inter.RemarkDao;
import com.hyc.www.po.Remark;
import com.hyc.www.service.Result;
import com.hyc.www.service.inter.RemarkService;
import com.hyc.www.util.BeanFactory;
import com.hyc.www.util.BeanUtils;
import com.hyc.www.vo.PagesVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;

import static com.hyc.www.service.constant.Status.*;
import static com.hyc.www.util.ServiceUtils.setResult;
import static com.hyc.www.util.StringUtils.toLegalText;
import static com.hyc.www.util.UUIDUtils.getUUID;

/**
 * @program XHotel
 * @description RemarkServiceImpl
 */
public class RemarkServiceImpl implements RemarkService {

    private RemarkDao dao = (RemarkDao) BeanFactory.getBean(BeanFactory.DaoType.RemarkDao);

    /**
     * 添加记录
     *
     * @param req
     * @param resp
     * @name add
     * @notice none
     */
    @Override
    public Result add(HttpServletRequest req, HttpServletResponse resp) {
//        Remark remark = (Remark) BeanUtils.toObject(req.getParameterMap(), Remark.class);
        Remark remark=new Remark();
        remark.setRemark(req.getParameter("remark"));
        remark.setUserName((String) req.getSession().getAttribute("USERNAME"));
        String user = remark.getUserName();
        if (dao.getUserRemarkCount(user) > 20) {
            PagesVo vo = new PagesVo();
            vo.setRemarks(dao.listAll());
            return new Result(DATA_TOO_MUCH, vo);
        }
        remark.setId(getUUID());
        /**
         * 使用toLegalText过滤非法字符
         */
        remark.setRemark(toLegalText(remark.getRemark()));

        if (remark.getRemark().trim().isEmpty()) {
            PagesVo vo = new PagesVo();
            vo.setRemarks(dao.listAll());
            return new Result(DATA_ILLEGAL, vo);
        }
        /**
         * 此处将留言中的用户名字段修改为用户的昵称
         */
        String userName = remark.getUserName();
        remark.setUserName(userName);
        if (dao.add(remark)) {
            PagesVo vo = new PagesVo();
            vo.setRemarks(dao.listAll());
            return new Result(SUCCESS, vo);
        }
        return setResult(ERROR);
    }

    /**
     * 返回所有评论
     *
     * @param req
     * @param resp
     * @name
     * @notice none

     */
    @Override
    public Result listAll(HttpServletRequest req, HttpServletResponse resp) {
        LinkedList<Remark> remarks = dao.listAll();
        if (remarks != null) {
            PagesVo vo = new PagesVo();
            vo.setRemarks(remarks);
            return new Result(SUCCESS, vo);
        }
//        System.out.print("测试：");
//        System.out.println(req.getParameter("remark"));
        return setResult(ERROR);
    }
}
