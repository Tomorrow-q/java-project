
package com.hyc.www.test;

import com.hyc.www.po.User;
import com.hyc.www.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.hyc.www.util.StringUtils.toLegalText;
import static com.hyc.www.util.UUIDUtils.getUUID;

/**
 * @program XHotel
 * @description 用于测试String工具类

 */
public class TestStringUtils {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        System.out.println(getUUID());
        System.out.println(toLegalText("<h1>"));
    }
}
