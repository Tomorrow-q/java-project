
package com.hyc.www.dao.inter;

import com.hyc.www.po.OrderRoom;

import java.math.BigInteger;
import java.util.LinkedList;

/**
 * @program XHotel
 * @description 负责房间订单的CRUD
 */
public interface OrderRoomDao {

    /**
     * 通过订单编号检查一个订单是否已经存在
     *
     * @param orderNumber 房间编号
     * @return boolean
     * @name isExist
     * @notice none
     */
    boolean isExist(String orderNumber);

    /**
     * 添加一个订单到数据库
     *
     * @param order 要添加的订单
     * @return boolean
     * @name addOrderRoom
     * @notice 无法添加订单编号为空的订单
     */
    boolean addOrderRoom(OrderRoom order);

    /**
     * 根据订单编号查询一个订单的所有信息
     *
     * @param orderNum
     * @return com.hyc.www.po.OrderRoom
     * @name getOrderRoom
     * @notice 如果订单为空或者没有该订单，则返回null;
     */
    OrderRoom getOrderRoom(String orderNum);


    /**
     * 返回该订单编号对应的id
     *
     * @param orderNum 订单编号
     * @return java.math.String
     * @name getId
     * @notice none
     */
    String getId(String orderNum);

    /**
     * 获取表中所有订单的信息，并以LinkedList的形式返回
     *
     * @return java.util.LinkedList
     * @name getAllOrderRooms
     * @notice none
     */
    LinkedList<OrderRoom> getAllOrderRooms();


    /**
     * 将该id对应的订单从数据库中删除
     *
     * @param Id 要删除订单的id
     * @return boolean
     * @name deleteById
     * @notice none
     */
    boolean deleteById(String Id);


    /**
     * 将该订单编号对应的订单从数据库中删除
     *
     * @param orderNumber 要删除的订单的订单编号
     * @return boolean
     * @name deleteByNumber
     * @notice none
     */
    boolean deleteByNumber(String orderNumber);

    /**
     * 将一个订单对象对象从数据库中删除
     *
     * @param order 要删除的订单对象
     * @return boolean
     * @name delete
     * @notice 该订单对象必须至少包含订单的id
     */
    boolean delete(OrderRoom order);


    /**
     * 更新一个订单的信息
     *
     * @param order 要更新的订单对象
     * @return boolean
     * @name update
     * @notice none
     */
    boolean update(OrderRoom order);


    /**
     * 通过一个用户id返回该用户的所有订单
     *
     * @param userId 查询订单对应的的用户id
     * @return
     * @name listByUserId
     * @notice none
     */
    LinkedList<OrderRoom> listByUserId(String userId);
}
