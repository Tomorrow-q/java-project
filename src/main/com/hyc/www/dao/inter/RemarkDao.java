

package com.hyc.www.dao.inter;

import com.hyc.www.po.Remark;

import java.util.LinkedList;

/**
 * @program XHotel
 * @description 负责评论的CRUD
 */
public interface RemarkDao {

    /**
     * 负责将一条评论插入数据库
     *
     * @param remark
     * @return 成功返回true, 否则返回false
     * @name add
     * @notice none
     */
    boolean add(Remark remark);

    /**
     * 返回所有评论
     *
     * @return 评论集合
     * @name listAll
     * @notice none
     */
    LinkedList<Remark> listAll();
    /**
     * 返回该用户名对应留言数量
     *
     * @param userName 用户名
     * @return
     * @name getUserRemarkCount
     * @notice none
     */
    int getUserRemarkCount(String userName);
}
