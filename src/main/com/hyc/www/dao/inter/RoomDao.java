
package com.hyc.www.dao.inter;

import com.hyc.www.po.Room;

import java.util.LinkedList;

/**
 * @program XHotel
 * @description 负责对房间进行CRUD操作
 */
public interface RoomDao {

    /**
     * 通过房间编号检查一个房间是否已经存在
     *
     * @param roomNumber 房间编号
     * @return boolean
     * @name isExist
     * @notice none
     */
    boolean isExist(String roomNumber);

    /**
     * 添加一个房间到数据库
     *
     * @param room 要添加的房间
     * @return boolean
     * @name addRoom
     * @notice 无法添加房间编号为空的用户
     */
    boolean addRoom(Room room);

    /**
     * 根据房间编号查询一个房间的所有信息
     *
     * @param roomNum
     * @return com.hyc.www.po.Room
     * @name getRoom
     * @notice 如果房间为空或者没有该房间，则返回null;
     */
    Room getRoom(String roomNum);

    /**
     * 根据房间id查询一个房间的所有信息
     *
     * @param id
     * @return com.hyc.www.po.Room
     * @name getRoom
     * @notice 如果id为空或者没有该房间，则返回null;
     */
    Room getRoomById(String id);


    /**
     * 返回该房间编号对应的id
     *
     * @param roomNum 房间编号
     * @return java.math.String
     * @name getId
     * @notice none
     */
    String getId(String roomNum);

    /**
     * 获取表中所有房间的信息，并以LinkedList的形式返回
     *
     * @return java.util.LinkedList
     * @name getAllRooms
     * @notice none
     */
    LinkedList<Room> getAllRooms();


    /**
     * 将该id对应的房间从数据库中删除
     *
     * @param Id 要删除房间的id
     * @return boolean
     * @name deleteById
     * @notice none
     */
    boolean deleteById(String Id);


    /**
     * 将该房间编号对应的房间从数据库中删除
     *
     * @param roomNumber 要删除的房间的房间编号
     * @return boolean
     * @name deleteByNumber
     * @notice none
     */
    boolean deleteByNumber(String roomNumber);

    /**
     * 将一个房间对象对象从数据库中删除
     *
     * @param room 要删除的房间对象
     * @return boolean
     * @name delete
     * @notice 该房间对象必须至少包含房间的id
     */
    boolean delete(Room room);


    /**
     * 更新一个房间的信息
     *
     * @param room 要更新的房间对象
     * @return boolean
     * @name update
     * @notice none

     */
    boolean update(Room room);

    /**
     * 通过房间名进行模糊查询
     *
     * @param name 房间名
     * @param page 页数
     * @return java.util.LinkedList
     * @name findByName
     * @notice none
     */
    LinkedList<Room> findByName(String name, int page);


    /**
     * 通过房间名进行模糊查询
     *
     * @param name 房间名
     * @name findByName
     */
    LinkedList<Room> findByName(String name);


    /**
     * 统计通过名称模糊查询的记录数
     *
     * @name
     * @notice none
     */
    int getCountByName(String name);
    /**
     * 统计通过名称模糊查询的记录页数
     *
     * @notice none
     */
    int getMaxPageByName(String name);
}


