

package com.hyc.www.annotation;

import java.lang.annotation.*;

/**
 * @program XHotel
 * @description 用于标注PrepareStatement的参数索引

 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface SQLParam {
    int index()default 1;
}
