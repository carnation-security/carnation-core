package com.violetfreesia.carnation.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.violetfreesia.carnation.exception.ConvertException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author violetfreesia
 * @date 2021-04-27
 */
@Slf4j
public class CarnationUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    /**
     * 获取指定指定Cookie名的值
     *
     * @param request    HTTP请求
     * @param targetName cookie名
     * @return 返回cookie值
     */
    public static Optional<String> getCookie(@NonNull HttpServletRequest request, @NonNull String targetName) {
        final Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return Optional.empty();
        }
        return Arrays.stream(cookies).
                filter(cookie -> cookie.getName().equals(targetName))
                .findFirst()
                .map(Cookie::getValue);
    }

    /**
     * 更新值为null的字段
     *
     * @param source 源对象
     * @param target 目标更新对象
     */
    public static void updateObject(@NonNull Object source, @NonNull Object target) {
        List<Field> fields = Stream.of(source.getClass().getDeclaredFields()).collect(Collectors.toList());
        List<Field> tFields = Stream.of(target.getClass().getDeclaredFields()).collect(Collectors.toList());
        fields.forEach(field -> tFields.stream().filter(tField -> tField.getName().equals(field.getName()) &&
                tField.getType().equals(field.getType()))
                .findFirst()
                .ifPresent(tField -> {
                    field.setAccessible(true);
                    tField.setAccessible(true);
                    try {
                        if (tField.get(target) == null) {
                            tField.set(target, field.get(source));
                        }
                    } catch (IllegalAccessException e) {
                        log.error("更新对象失败, 无法更新字段[{}]的值", tField);
                    }
                }));

    }

    public static String joinKey(@NonNull String prefix, @NonNull String token) {
        return prefix + token;
    }


    /**
     * 将对象转为json字符串
     *
     * @param o 对象
     * @return json字符串
     */
    public static String toJsonString(Object o) {
        try {
            return OBJECT_MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new ConvertException("转换异常", e);
        }
    }

    /**
     * 将json字符串转为对象
     *
     * @param json   json字符串
     * @param tClass 目标类Class
     * @param <T>    目标类型
     * @return 目标对象
     */
    public static <T> T parseObject(String json, Class<T> tClass) {
        try {
            return OBJECT_MAPPER.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ConvertException("转换异常", e);
        }
    }
}
