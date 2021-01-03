
package com.hyc.www.dao.impl;

import com.hyc.www.dao.inter.OrderRoomDao;
import com.hyc.www.po.OrderRoom;
import com.hyc.www.util.JdbcUtils;

import java.util.LinkedList;

/**
 * @program XHotel
 * @description 负责房间订单的CRUD
 */
public class OrderRoomDaoImpl extends BaseDaoImpl implements OrderRoomDao {
    /**
     * 本类操作的数据库表名
     */
    private final String TABLE_NAME = " " + JdbcUtils.getTableName(OrderRoom.class) + " ";

    /**
     * 表中所有字段对应的查询语句
     */
    private final String ALL_FIELD_NAME = " id,number,user_id,room_id,start_time,end_time,amount,remark,"
            + "status,gmt_create,gmt_modified ";


    /**
     * 通过订单编号检查一个订单是否已经存在
     *
     * @param orderNumber 房间编号
     * @return boolean
     * @name isExist
     * @notice none

     */
    @Override
    public boolean isExist(String orderNumber) {
        return getOrderRoom(orderNumber) != null;
    }

    /**
     * 添加一个订单到数据库
     *
     * @param order 要添加的订单
     * @return boolean
     * @name addOrderRoom
     * @notice 无法添加订单编号为空的订单
     */
    @Override
    public boolean addOrderRoom(OrderRoom order) {
        if (order == null || order.getNumber() == null) {
            return false;
        }
        return super.insert(order) == 1;
    }

    /**
     * 根据订单编号查询一个订单的所有信息
     *
     * @param orderNum
     * @return com.hyc.www.po.OrderRoom
     * @name getOrderRoom
     * @notice 如果订单为空或者没有该订单，则返回null;
     */
    @Override
    public OrderRoom getOrderRoom(String orderNum) {
        if (orderNum == null) {
            return null;
        }
        String sql = "select " + ALL_FIELD_NAME + " from " + TABLE_NAME + " where number  = ?";
        return (OrderRoom) super.queryObject(sql, new Object[]{orderNum}, OrderRoom.class);
    }


    /**
     * 返回该订单编号对应的id
     *
     * @param orderNum 订单编号
     * @return java.math.String
     * @name getId
     * @notice none
     */
    @Override
    public String getId(String orderNum) {
        String sql = "select id from " + TABLE_NAME + " where number = ?";
        return (String) super.queryValue(sql, new Object[]{orderNum});
    }

    /**
     * 获取表中所有订单的信息，并以LinkedList的形式返回
     *
     * @return java.util.LinkedList
     * @name getAllOrderRooms
     * @notice none
     */
    @Override
    public LinkedList<OrderRoom> getAllOrderRooms() {
        String sql = "select " + ALL_FIELD_NAME + " from " + TABLE_NAME;
        return toOrderList(sql, null);
    }


    /**
     * 通过一个用户id返回该用户的所有订单
     *
     * @param userId 查询订单对应的的用户id
     * @return
     * @name listByUserId
     * @notice none
     */
    @Override
    public LinkedList<OrderRoom> listByUserId(String userId) {
        String sql = "select " + ALL_FIELD_NAME + " from " + TABLE_NAME + " where user_id = ?";
        return toOrderList(sql, new String[]{userId});
    }


    private LinkedList<OrderRoom> toOrderList(String sql, Object[] params) {
        LinkedList<Object> list = super.queryList(sql, params, OrderRoom.class);
        LinkedList<OrderRoom> orders = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            OrderRoom order = (OrderRoom) list.get(i);
            orders.add(order);
        }
        return orders;
    }


    /**
     * 将该id对应的订单从数据库中删除
     *
     * @param id 要删除订单的id
     * @return boolean
     * @name deleteById
     * @notice none
     */
    @Override
    public boolean deleteById(String id) {
        if (id == null) {
            return false;
        }
        OrderRoom orderRoom = new OrderRoom();
        orderRoom.setId(id);
        return super.delete(orderRoom) == 1;
    }


    /**
     * 将该订单编号对应的订单从数据库中删除
     *
     * @param orderNumber 要删除的订单的订单编号
     * @return boolean
     * @name deleteByNumber
     * @notice none

     */
    @Override
    public boolean deleteByNumber(String orderNumber) {
        return deleteById(getId(orderNumber));
    }

    /**
     * 将一个订单对象对象从数据库中删除
     *
     * @param order 要删除的订单对象
     * @return boolean
     * @name delete
     * @notice 该订单对象必须至少包含订单的id
     */
    @Override
    public boolean delete(OrderRoom order) {
        return deleteById(order.getId());
    }


    /**
     * 更新一个订单的信息
     *
     * @param order 要更新的订单对象
     * @return boolean
     * @name update
     * @notice none
     */
    @Override
    public boolean update(OrderRoom order) {
        if (order == null) {
            return false;
        }
        return super.update(order) == 1;
    }


}
