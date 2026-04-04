package com.weng.system.common.utils;

import sun.net.util.IPAddressUtil;

import javax.servlet.http.HttpServletRequest;

public class IPUtils {

    private static final String UNKNOWN = "unknown";

    protected IPUtils() {

    }

    /**
     * 获取IP地址
     * 使用 Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非 unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    /**
     * 判断IP是否为局域网ip
     *
     * @param ip
     * @return
     */
    public static boolean internalIp(String ip) {
        byte[] addr = IPAddressUtil.textToNumericFormatV4(ip);
        return internalIp(addr);
    }

    public static boolean internalIp(byte[] addr) {
        final byte b0 = addr[0];
        final byte b1 = addr[1];
        //10.x.x.x/8
        final byte SECTION_1 = 0x0A;
        //172.16.x.x/12
        final byte SECTION_2 = (byte) 0xAC;
        final byte SECTION_3 = (byte) 0x10;
        final byte SECTION_4 = (byte) 0x1F;
        //192.168.x.x/16
        final byte SECTION_5 = (byte) 0xC0;
        final byte SECTION_6 = (byte) 0xA8;
        switch (b0) {
            case SECTION_1:
                return true;
            case SECTION_2:
                if (b1 >= SECTION_3 && b1 <= SECTION_4) {
                    return true;
                }
            case SECTION_5:
                switch (b1) {
                    case SECTION_6:
                        return true;
                }
            default:
                return false;

        }
    }

    /**
     * IP转换成long
     *
     * @param ip
     * @return
     */
    public static Long ipToLong(String ip) {
        Long ips = 0L;
        String[] numbers = ip.trim().split("\\.");
        for (int i = 0; i < 4; ++i) {
            ips = ips << 8 | Integer.parseInt(numbers[i]);
        }
        return ips;
    }

    /**
     * long转换成IP
     *
     * @param number
     * @return
     */
    public static String longToIp(Long number) {
        StringBuffer ipStr = new StringBuffer();
        ipStr.append(number >> 24);
        ipStr.append(".");
        ipStr.append((number & 0xFFFFFF) >> 16);
        ipStr.append(".");
        ipStr.append((number & 0xFFFF) >> 8);
        ipStr.append(".");
        ipStr.append(number & 0xFF);
        return ipStr.toString();
    }

    public static void main(String[] args) {
		String ip="192.168.1.194";
//        String ip = "60.190.99.3";
        System.out.println(internalIp(ip));
        System.out.println(ipToLong(ip));
        System.out.println(longToIp(3232235970L));
    }

}
