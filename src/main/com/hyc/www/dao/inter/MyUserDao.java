
package com.hyc.www.dao.inter;

import com.hyc.www.annotation.DML;
import com.hyc.www.annotation.InsertSQL;
import com.hyc.www.annotation.SQLParam;
import com.hyc.www.po.User;

/**
 * @program XHotel
 * @description
 */
public interface MyUserDao extends BaseDao {

    @InsertSQL("insert into t_user (id,name) values (?,?) ")
    boolean insert(@SQLParam() String id,@SQLParam (index = 2)String name);


}
