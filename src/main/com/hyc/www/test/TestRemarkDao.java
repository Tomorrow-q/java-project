

package com.hyc.www.test;

import com.hyc.www.dao.impl.RemarkDaoImpl;
import com.hyc.www.dao.inter.RemarkDao;

/**
 * @program XHotel
 * @description 测试RemarkDao
 */
public class TestRemarkDao {
    public static void main(String[] args) {
        RemarkDao dao  = new RemarkDaoImpl();
        System.out.printf(dao.listAll().get(0).getRemark());
        System.out.println(dao.getUserRemarkCount("test8888(我的新昵称)"));
    }
}
