
package com.hyc.www.annotation;

import java.lang.annotation.*;

/**
 * @program XHotel
 * @description 用于标注执行插入语句
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface InsertSQL {
    String value()default "";
}
