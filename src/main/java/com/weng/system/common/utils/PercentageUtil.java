package com.weng.system.common.utils;

import java.math.BigDecimal;

public class PercentageUtil {

    public static double cal(int numerator, int denominator) {
        if (denominator == 0) {
            return 0;
        }
        // 百分比后两位  小数点4位
        BigDecimal bg = new BigDecimal(1.0 * numerator / denominator);
        return bg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double cal(double numerator, int denominator) {
        if (denominator == 0) {
            return 0;
        }
        // 百分比后两位  小数点4位
        BigDecimal bg = new BigDecimal(1.0 * numerator / denominator);
        return bg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
