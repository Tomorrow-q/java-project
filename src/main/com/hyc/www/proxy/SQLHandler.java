

package com.hyc.www.proxy;

import com.hyc.www.annotation.InsertSQL;
import com.hyc.www.annotation.SQLParam;
import com.hyc.www.dao.impl.BaseDaoImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @program XHotel
 * @description
 */
public class SQLHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getAnnotation(InsertSQL.class) != null) {
            BaseDaoImpl baseDao = new BaseDaoImpl();
            /**
             * 默认没有sql语句，使用直接插入对象的方法
             */
            if ("".equalsIgnoreCase(method.getAnnotation(InsertSQL.class).value())) {
                Object obj = args[0];
                return baseDao.insert(obj) ;
            } else {
                String sql = method.getDeclaredAnnotation(InsertSQL.class).value();
                return baseDao.executeUpdate(sql, args);
            }
        }
        return null;
    }
}
