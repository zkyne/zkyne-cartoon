package com.zkyne.zkynecartoon.utils;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @ClassName: AjaxResultUtils
 * @Description:
 * @Author: zhangkun01
 * @Date: 2018/7/9 14:48
 */
public class AjaxResultUtils {
    private static final String CODE = "code";
    private static final int SUCCESS_CODE = 200;
    private static final int FILURE_CODE_10000 = 10000;
    private static final String MESSAGE = "message";
    private static final String DATA = "data";

    private AjaxResultUtils() {
    }

    public static Map<String, Object> success(Object obj) {
        Map<String, Object> result = Maps.newHashMap();
        result.put(CODE, SUCCESS_CODE);
        result.put(MESSAGE, "SUCCESS");
        if (obj != null) {
            result.put(DATA, obj);
        }
        return result;
    }

    public static Map<String, Object> success() {
        return success(null);
    }

    public static Map<String, Object> error(int errorCode, String errorMessage) {
        Map<String, Object> result = Maps.newHashMap();
        if (errorCode == SUCCESS_CODE) {
            errorCode = FILURE_CODE_10000;
        }
        result.put(CODE, errorCode);
        result.put(MESSAGE, errorMessage);
        return result;
    }

    public static Map<String, Object> error(String errorMessage) {
        return error(FILURE_CODE_10000, errorMessage);
    }
}
