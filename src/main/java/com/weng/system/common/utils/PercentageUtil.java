package com.weng.system.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentageUtil {

    /**
     * 计算百分比（小数表示），保留4位小数
     * 如 cal(1, 3) → 0.3333
     */
    public static double cal(int numerator, int denominator) {
        return cal((double) numerator, denominator);
    }

    /**
     * 计算百分比（小数表示），保留4位小数
     * 如 cal(1.0, 3) → 0.3333
     */
    public static double cal(double numerator, int denominator) {
        if (denominator == 0) {
            return 0;
        }
        return BigDecimal.valueOf(numerator)
                .divide(BigDecimal.valueOf(denominator), 4, RoundingMode.HALF_UP)
                .doubleValue();
    }

    /**
     * 计算百分比并格式化为字符串，保留2位小数
     * 如 formatPercent(1, 3) → "33.33%"
     */
    public static String formatPercent(int numerator, int denominator) {
        return formatPercent((double) numerator, denominator);
    }

    /**
     * 计算百分比并格式化为字符串，保留2位小数
     * 如 formatPercent(1.0, 3) → "33.33%"
     */
    public static String formatPercent(double numerator, int denominator) {
        if (denominator == 0) {
            return "0.00%";
        }
        BigDecimal result = BigDecimal.valueOf(numerator)
                .multiply(BigDecimal.valueOf(100))
                .divide(BigDecimal.valueOf(denominator), 2, RoundingMode.HALF_UP);
        return result.stripTrailingZeros().toPlainString() + "%";
    }
}
