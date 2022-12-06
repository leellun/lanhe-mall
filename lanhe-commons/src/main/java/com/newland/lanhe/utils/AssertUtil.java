package com.newland.lanhe.utils;

/**
 * assert工具类
 * Author: leell
 * Date: 2022/12/6 17:44:59
 */
public class AssertUtil {
    /**
     * 断言 expression 是否为false
     * 如果true,  则抛出BusinessException异常
     *
     * @param expression
     * @param errorMsgTemplate
     * @param params
     */
    public static void isFalse(boolean expression, String errorMsgTemplate, Object... params) {
        cn.hutool.core.lang.Assert.isFalse(expression, () -> new BusinessException(StrUtil.format(errorMsgTemplate, params)));
    }

    /**
     * 断言 expression 是否为: true
     * 如果为false, 则抛出BusinessException异常
     *
     * @param expression
     * @param errorMsgTemplate
     * @param params
     */
    public static void isTrue(boolean expression, String errorMsgTemplate, Object... params) {
        cn.hutool.core.lang.Assert.isTrue(expression, () -> new BusinessException(StrUtil.format(errorMsgTemplate, params)));
    }

    public static void isNotTrue(boolean expression, String errorMsgTemplate, Object... params) {
        cn.hutool.core.lang.Assert.isTrue(!expression, () -> new BusinessException(StrUtil.format(errorMsgTemplate, params)));
    }


    /**
     * 断言对象必须为 null {@code null} ，如果不为{@code null} 抛出指定类型异常
     *
     * @param object
     * @param errorMsgTemplate
     * @param params
     * @return
     */
    public static <T> void isNull(T object, String errorMsgTemplate, Object... params) {
        cn.hutool.core.lang.Assert.isNull(object, () -> new BusinessException(StrUtil.format(errorMsgTemplate, params)));
    }


    /**
     * 断言对象是否不为{@code null} ，如果为{@code null} 抛出指定类型异常
     *
     * @param object
     * @param errorMsgTemplate
     * @param params
     * @param <T>
     * @return
     */
    public static <T> T notNull(T object, String errorMsgTemplate, Object... params) {
        return cn.hutool.core.lang.Assert.notNull(object, () -> new BusinessException(StrUtil.format(errorMsgTemplate, params)));
    }

    /**
     * 断言字符串不为空字符串 ，如果空字符串抛出指定类型异常
     *
     * @param str
     * @param errorMsgTemplate
     * @param params
     * @return
     */
    public static String notBlank(String str, String errorMsgTemplate, Object... params) {
        return cn.hutool.core.lang.Assert.notBlank(str, () -> new BusinessException(StrUtil.format(errorMsgTemplate, params)));
    }

    /**
     * params是否包含t，如果不包含抛出异常
     *
     * @param t
     * @param errorMsgTemplate
     * @param params
     * @param <T>
     */
    public static <T> void includeItem(T t, String errorMsgTemplate, T... params) {
        isTrue(Arrays.stream(params).collect(Collectors.toList()).contains(t), errorMsgTemplate);
    }

    /**
     * 正则检查
     * @param str 源字符串
     * @param regx 正则表达式
     * @param errorMsgTemplate
     * @param <T>
     */
    public static <T> void checkRegx(String str, String regx, String errorMsgTemplate) {
        Pattern pattern = Pattern.compile(regx);
        isTrue(pattern.matcher(str).find(), errorMsgTemplate);
    }

    /**
     * 断言value是否在min与max之间，如果不在则抛出异常
     *
     * @param value
     * @param min
     * @param max
     * @param errorMsgTemplate
     * @param params
     * @return
     */
    public static int checkBetween(int value, int min, int max, String errorMsgTemplate, Object... params) {
        if (value >= min && value <= max) {
            return value;
        } else {
            throw new BusinessException(StrUtil.format(errorMsgTemplate, params));
        }
    }
}
