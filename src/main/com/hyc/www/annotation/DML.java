

package com.hyc.www.annotation;

import java.lang.annotation.*;

/**
 * @program XHotel
 * @description 用于描述DML语句（update/delete/insert）
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DML {
    String value()default "";
}
