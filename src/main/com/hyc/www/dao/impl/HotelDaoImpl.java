

package com.hyc.www.dao.impl;

import com.hyc.www.dao.inter.HotelDao;
import com.hyc.www.po.Hotel;
import com.hyc.www.util.JdbcUtils;

import java.util.LinkedList;

/**
 * @program XHotel
 * @description 负责酒店的CRUD
 */
public class HotelDaoImpl extends BaseDaoImpl implements HotelDao {
    /**
     * 本类操作的数据库表名
     */
    private final String TABLE_NAME = " " + JdbcUtils.getTableName(Hotel.class) + " ";

    /**
     * 表中所有字段对应的查询语句
     */
    private final String ALL_FIELD_NAME = " id,name,number,site,photo,type,area,"
            + "score,status,gmt_create,gmt_modified ";

    private final String[] ALL_FIELD_ARRAY = new String[]{"id", "name", "number", "site", "photo", "type", "area",
            "score", "status", "gmt_create", "gmt_modified"};


    /**
     * 通过酒店编号检查一个酒店是否已经存在
     *
     * @param hotelNumber 酒店编号
     * @return boolean
     * @name isExist
     * @notice none
     */
    @Override
    public boolean isExist(String hotelNumber) {
        return getHotel(hotelNumber) != null;
    }

    /**
     * 添加一个酒店到数据库
     *
     * @param hotel 要添加的酒店
     * @return boolean
     * @name addHotel
     * @notice 无法添加酒店编号为空的酒店
     */
    @Override
    public boolean addHotel(Hotel hotel) {
        if (hotel == null || hotel.getNumber() == null) {
            return false;
        }
        return super.insert(hotel) == 1;
    }

    /**
     * 根据酒店编号查询一个酒店的所有信息
     *
     * @param hotelNum
     * @return com.hyc.www.po.Hotel
     * @name getHotel
     * @notice 如果酒店为空或者没有该酒店，则返回null;
     */
    @Override
    public Hotel getHotel(String hotelNum) {
        if (hotelNum == null) {
            return null;
        }
        String sql = "select " + ALL_FIELD_NAME + " from " + TABLE_NAME + " where number  = ?";
        return (Hotel) super.queryObject(sql, new Object[]{hotelNum}, Hotel.class);
    }


    /**
     * 返回该酒店编号对应的id
     *
     * @param hotelNum 酒店编号
     * @return java.math.String
     * @name getId
     * @notice none
     */
    @Override
    public String getId(String hotelNum) {
        String sql = "select id from " + TABLE_NAME + " where number = ?";
        return (String) super.queryValue(sql, new Object[]{hotelNum});
    }

    /**
     * 获取表中所有酒店的信息，并以LinkedList的形式返回
     *
     * @return java.util.LinkedList
     * @name getAllHotels
     * @notice none
     */
    @Override
    public LinkedList<Hotel> getAllHotels() {
        String sql = "select " + ALL_FIELD_NAME + " from " + TABLE_NAME;
        LinkedList<Object> list = super.queryList(sql, null, Hotel.class);
        return toHotel(list);
    }

    private LinkedList<Hotel> toHotel(LinkedList<Object> list) {
        LinkedList<Hotel> hotels = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            Hotel hotel = (Hotel) list.get(i);
            hotels.add(hotel);
        }
        return hotels;
    }


    /**
     * 通过酒店名进行模糊查询
     *
     * @param name 酒店名
     * @name findByName
     * @notice none
     */
    @Override
    public LinkedList<Hotel> findByName(String name) {
        Hotel hotel = new Hotel();
        hotel.setName("%" + name + "%");
        LinkedList<Object> list = super.queryWhereLikeAnd(ALL_FIELD_ARRAY, hotel);
        return toHotel(list);
    }


    /**
     * 将该id对应的酒店从数据库中删除
     *
     * @param id 要删除酒店的id
     * @return boolean
     * @name deleteById
     * @notice none
     */
    @Override
    public boolean deleteById(String id) {
        if (id == null) {
            return false;
        }
        Hotel hotel = new Hotel();
        hotel.setId(id);
        return super.delete(hotel) == 1;
    }


    /**
     * 将该酒店编号对应的酒店从数据库中删除
     *
     * @param hotelNumber 要删除的酒店的酒店编号
     * @return boolean
     * @name deleteByNumber
     * @notice none
     */
    @Override
    public boolean deleteByNumber(String hotelNumber) {
        return hotelNumber != null && deleteById(getId(hotelNumber));
    }

    /**
     * 将一个酒店对象对象从数据库中删除
     *
     * @param hotel 要删除的酒店对象
     * @return boolean
     * @name delete
     * @notice 该酒店对象必须至少包含酒店的id
     */
    @Override
    public boolean delete(Hotel hotel) {
        return deleteById(hotel.getId());
    }


    /**
     * 更新一个酒店的信息
     *
     * @param hotel 要更新的酒店对象
     * @return boolean
     * @name update
     * @notice none
     */
    @Override
    public boolean update(Hotel hotel) {
        return hotel != null && super.update(hotel) == 1;
    }
}
