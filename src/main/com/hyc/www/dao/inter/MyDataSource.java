

package com.hyc.www.dao.inter;

import com.hyc.www.exception.DaoException;

import java.sql.Connection;

/**

 * @program XHotel
 * @description 负责提供数据库连接池，向dao实现类提供数据库连接

 */
public interface MyDataSource {


    /**
     * 负责从数据库连接池中获取数据库连接
     *
     * @return java.sql.Connection
     * @throws DaoException 如果数据库连接已经达到最大值时仍然调用此方法，则抛出此异常
     * @name getConnection
     * @notice 数据库连接的数量受到配置文件中最大值的限制

     */
    Connection getConnection() throws DaoException;


    /**
     * 用于将数据库连接放回连接池中
     *
     * @param conn 数据库连接
     * @name freeConnection
     */
    void freeConnection(Connection conn);


    /**
     * 负责返回当前已经创建的连接数
     *
     * @return int 当前已经创建的连接数
     * @name getCurrentCount
     */
    int getCurrentCount();

    /**
     * 负责返回当前池中剩余的空闲连接数
     *
     * @return int 当前空闲连接数
     * @name getfreeCount
     */
    int getfreeCount();

}
