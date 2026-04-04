package com.weng.system.test;

import com.weng.system.common.utils.IPUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName IpTest
 * @Description: TODO
 * @Author weng.yifeng
 * @Date 2020/5/8 16:28
 * @Version V1.0
 **/
public class IpTest {
    private static boolean isInternalIp(String ip) {
        String ipConfiguration = "192.168.1.1-192.168.1.10,192.168.1.194,192.168.3.*";
        String[] ipSegments = StringUtils.split(ipConfiguration, ",");
        for (String ipSegment : ipSegments){
            if (ipSegment.contains("*")){
                //如果配置的IP段包含"*"，如192.168.3.*
                String[] ipSegmentNumbers = StringUtils.split(ipSegment, ".");
                String[] ipNumbers = StringUtils.split(ip, ".");
                if(ipNumbers[0].equals(ipSegmentNumbers[0]) && ipNumbers[1].equals(ipSegmentNumbers[1]) && ipNumbers[2].equals(ipSegmentNumbers[2])){
                    return true;
                }
            } else if (ipSegment.contains("-")){
                //如果配置的IP段包含"-"，如192.168.1.1-192.168.1.10
                String[] ips = StringUtils.split(ipSegment, "-");
                Long startIp = IPUtils.ipToLong(ips[0]);
                Long endIp = IPUtils.ipToLong(ips[1]);
                Long longIp = IPUtils.ipToLong(ip);
                if(longIp>=startIp && longIp<=endIp){
                    return true;
                }
            } else {
                //如果配置的IP段是完整的IP，如192.168.1.194
                if (ipSegment.equals(ip)){
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args){
        System.out.println(isInternalIp("192.168.3.11"));
    }
}
