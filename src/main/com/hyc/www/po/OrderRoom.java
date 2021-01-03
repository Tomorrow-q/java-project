

package com.hyc.www.po;

import com.hyc.www.po.abs.Order;

/**
 * @program XHotel
 * @description 用于存储房间订单信息
 */
public class OrderRoom extends Order {
    private String roomId;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }


}
