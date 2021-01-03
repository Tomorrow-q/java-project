

package com.hyc.www.test;

import com.hyc.www.po.Room;
import com.hyc.www.util.ServiceUtils;

import java.math.BigDecimal;

/**
 * @program XHotel
 * @description 用于测试ServiceUtils
 */
public class TestServiceUtils {
    public static void main(String[] args) {
        Room room = new Room();
        room.setPrice(BigDecimal.valueOf(999));
        room.setArea(BigDecimal.valueOf(50));
        room.setBedWidth(BigDecimal.valueOf(3));
        System.out.println(ServiceUtils.isValidRoom(room));
    }
}
