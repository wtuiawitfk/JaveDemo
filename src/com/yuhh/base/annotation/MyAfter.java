package com.yuhh.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)     //自定义标签存在的范围，RUNTIME(运行时)
@Target(ElementType.METHOD)             //自定义标签可以作用的范围，METHOD（方法上）
public @interface MyAfter {
}
