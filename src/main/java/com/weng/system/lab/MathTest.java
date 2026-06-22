package com.weng.system.lab;

import com.weng.system.common.utils.PercentageUtil;

/**
 * @ClassName MathTest
 * @Description: TODO
 * @Author weng.yifeng
 * @Date 2020/7/29 13:47
 * @Version V1.0
 **/
public class MathTest {

    public static void main(String[] args){
        /*Integer negativeCount = 1;
        Integer neutralCount = 1;
        Integer positiveCount = 1;
        Integer sum = negativeCount + neutralCount + positiveCount;
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后1位
        numberFormat.setMaximumFractionDigits(0);
        String result = numberFormat.format((double) negativeCount / (double) sum * 100);
        String result1 = numberFormat.format((double) neutralCount / (double) sum * 100);
        String result2 = numberFormat.format((double) positiveCount / (double) sum * 100);
        System.out.println(result + "  " + result1 + "  " + result2);*/

        System.out.println(PercentageUtil.cal(0.29, 0));
    }
}
