package com.violetfreesia.carnation.util;

import java.util.Collection;

/**
 * @author violetfreesia
 * @date 2021-04-26
 */
public class CarnationAssert {
    /**
     * 判空函数, 若为空抛异常
     *
     * @param o 待判断对象
     * @param e 抛出的异常
     */
    public static void notNull(Object o, RuntimeException e) {
        if (o == null) {
            throw e;
        }
    }

    /**
     * 判断集合中是否存在Null值
     *
     * @param objects 集合
     * @param e       存在时抛出的异常
     */
    public static void notExistNull(Collection<?> objects, RuntimeException e) {
        objects.parallelStream()
                .forEach(o -> notNull(o, e));
    }

    /**
     * 判断字符串是否为空
     *
     * @param s 待判断字符串
     * @param e 抛出的异常
     */
    public static void notBlank(String s, RuntimeException e) {
        notNull(s, new NullPointerException("待检查对象为Null"));
        if (s.isEmpty()) {
            throw e;
        }
    }
}
