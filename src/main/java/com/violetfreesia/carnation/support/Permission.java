package com.violetfreesia.carnation.support;

/**
 * 权限接口
 * <p>
 * 注意: 权限最终会放入{@link java.util.Set}集合中,
 * 所以在自定义权限时请一定要重写{@code equals}和{@code hashCode}函数
 *
 * @param <P> 权限值类型, 框架默认提供的是String类型, 可自定义为枚举
 * @author violetfreesia
 * @date 2021-04-26
 */
public interface Permission<P> {

    /**
     * 获取权限的值
     *
     * @return 返回权限值
     */
    P getValue();
}
