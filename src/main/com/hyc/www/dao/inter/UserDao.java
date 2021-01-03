
package com.hyc.www.dao.inter;

import com.hyc.www.annotation.TableName;
import com.hyc.www.po.User;

import java.math.BigInteger;
import java.util.LinkedList;

/**
 * @program XHotel
 * @description 提供User类的Dao接口
 */
public interface UserDao {

    /**
     * 通过用户名检查一个用户是否已经存在
     *
     * @param userName 用户名
     * @return boolean
     * @name isExist
     * @notice none
     */
    boolean isExist(String userName);

    /**
     * 添加一个用户到数据库
     *
     * @param user 要添加的用户对象
     * @return boolean
     * @name addUser
     * @notice none
     */
    boolean addUser(User user);

    /**
     * 根据id查询一个用户的所有信息
     *
     * @param id 用户id
     * @return com.hyc.www.po.User
     * @name getUser
     * @notice 如果id为空或者没有该用户，则返回null;
     */
    User getUserById(String id);

    /**
     * 根据用户名查询一个用户的所有信息
     *
     * @param userName 用户名
     * @return com.hyc.www.po.User
     * @name getUser
     * @notice none
     */
    User getUser(String userName);

    /**
     * 返回该用户名对应的登陆密码
     *
     * @param userName 需要查询登陆密码的用户名
     * @return java.lang.String
     * @name getPassword
     * @notice none
     */
    String getPassword(String userName);


    /**
     * 返回该用户名对应的id
     *
     * @param userName 用户名
     * @return java.math.String
     * @name getId
     * @notice none
     */
    String getId(String userName);

    /**
     * 获取表中所有用户的信息，并以LinkedList的形式返回
     *
     * @return java.util.LinkedList
     * @name qgetAllUsers
     * @notice none
     */
    LinkedList<User> getAllUsers();

    /**
     * 将该id对应的用户从数据库中删除
     *
     * @param Id 要删除用户的id
     * @return boolean
     * @name deleteById
     * @notice none
     */
    boolean deleteById(String Id);


    /**
     * 将该用户名对应的用户从数据库中删除
     *
     * @param userName 要删除的用户的用户名
     * @return boolean
     * @name deleteByUserName

     */
    boolean deleteByUserName(String userName);


    /**
     * 将一个用户对象从数据库中删除
     *
     * @param user 要删除的用户对象
     * @return boolean
     * @name delete
     * @notice 该用户对象必须至少包含用户的id
     */
    boolean delete(User user);


    /**
     * 更新一个用户的信息，不包括登陆密码和支付密码
     *
     * @param user 要更新的用户对象
     * @return boolean
     * @name update
     * @notice 此方法不会更新用户的密码，需要更新密码请使用updateWithPassword
     */
    boolean update(User user);


    /**
     * 更新一个用户的信息，包括密码
     *
     * @param user 要更新的用户对象
     * @return boolean
     * @name updateAll
     * @notice 此方法会更新用户的密码，请只在用户更新密码时使用，否则md5的摘要将覆盖原来的摘要
     */
    boolean updateAll(User user);


}
