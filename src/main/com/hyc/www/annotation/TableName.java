
package com.hyc.www.annotation;
import java.lang.annotation.*;
/**
 * @program XHotel
 * @description 用于注解表名
 */
@Documented
//当前这被描述的注解，会被保留到class字节码文件，并被jvm读取
@Retention(value = RetentionPolicy.RUNTIME)
//作用在类上
@Target(value = ElementType.TYPE)
public @interface TableName {
        String value() default"";
}
