package com.weng.system.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * MD5 摘要工具类
 *
 * <p>基于 {@link MessageDigest} 实现，线程安全，支持字符串、字节数组、文件、目录、输入流的 MD5 计算。
 *
 * <p>文件相关方法返回 {@link Optional}，调用方可自行决定如何处理失败情况。
 *
 * <p>使用示例：
 * <pre>
 *   // 字符串
 *   MD5Util.md5("hello");
 *   MD5Util.md5("hello", StandardCharsets.GBK);
 *
 *   // 字节数组
 *   MD5Util.md5(new byte[]{1, 2, 3});
 *
 *   // InputStream
 *   MD5Util.md5Stream(inputStream);
 *
 *   // 文件（返回 Optional）
 *   MD5Util.md5File(new File("test.txt")).ifPresent(System.out::println);
 *
 *   // 目录（跳过不可读文件）
 *   Map&lt;String, String&gt; map = MD5Util.md5Dir(new File("/data"), true);
 * </pre>
 *
 * @author yifeng
 * @since 2026/6/1
 * @version 1.0
 */
public class MD5Util {

    private static final int BUFFER_SIZE = 8192;

    private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

    private MD5Util() {
    }

    // ==================== 字符串 ====================

    /**
     * 计算字符串的 MD5（UTF-8 编码）
     *
     * @param input 输入字符串
     * @return 32 位小写十六进制
     */
    public static String md5(String input) {
        return md5(input, StandardCharsets.UTF_8);
    }

    /**
     * 计算字符串的 MD5（指定编码）
     *
     * @param input   输入字符串
     * @param charset 字符编码
     * @return 32 位小写十六进制
     */
    public static String md5(String input, Charset charset) {
        if (input == null) {
            throw new IllegalArgumentException("输入字符串不能为 null");
        }
        return md5(input.getBytes(charset));
    }

    // ==================== 字节数组 ====================

    /**
     * 计算字节数组的 MD5
     *
     * @param data 字节数组
     * @return 32 位小写十六进制
     */
    public static String md5(byte[] data) {
        return bytesToHex(digest(data));
    }

    /**
     * 计算字节数组的 MD5，返回原始字节
     *
     * @param data 字节数组
     * @return 16 字节的 MD5 摘要
     */
    public static byte[] digest(byte[] data) {
        MessageDigest md = getMessageDigest();
        md.update(data);
        return md.digest();
    }

    // ==================== InputStream ====================

    /**
     * 从输入流计算 MD5
     *
     * <p>方法内部会读取流中全部数据，但 <b>不会关闭</b> 该流，由调用方负责关闭。
     *
     * @param in 输入流
     * @return 32 位小写十六进制
     * @throws IllegalArgumentException 当 in 为 null 或读取失败时抛出
     */
    public static String md5Stream(InputStream in) {
        if (in == null) {
            throw new IllegalArgumentException("输入流不能为 null");
        }
        try {
            MessageDigest md = getMessageDigest();
            byte[] buffer = new byte[BUFFER_SIZE];
            int len;
            while ((len = in.read(buffer)) != -1) {
                md.update(buffer, 0, len);
            }
            return bytesToHex(md.digest());
        } catch (IOException e) {
            throw new IllegalArgumentException("读取输入流失败", e);
        }
    }

    // ==================== 文件（Optional 返回值） ====================

    /**
     * 计算文件的 MD5
     *
     * @param file 文件对象
     * @return 包含 MD5 值的 Optional；文件不存在、不是文件或读取失败时返回 {@link Optional#empty()}
     */
    public static Optional<String> md5File(File file) {
        if (!isReadableFile(file)) {
            return Optional.empty();
        }
        try (InputStream in = new FileInputStream(file)) {
            return Optional.of(md5Stream(in));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    /**
     * 计算文件的 MD5，读取失败时抛出异常
     *
     * @param file 文件对象
     * @return 32 位小写十六进制
     * @throws IllegalArgumentException 当 file 为 null、不是文件或读取失败时抛出
     */
    public static String md5FileOrThrow(File file) {
        if (!isReadableFile(file)) {
            throw new IllegalArgumentException("文件不存在或不是有效文件: " + file);
        }
        try (InputStream in = new FileInputStream(file)) {
            return md5Stream(in);
        } catch (IOException e) {
            throw new IllegalArgumentException("读取文件失败: " + file.getAbsolutePath(), e);
        }
    }

    // ==================== 目录（跳过不可读文件） ====================

    /**
     * 计算目录下所有文件的 MD5，跳过不可读的文件
     *
     * @param dir       目录对象
     * @param recursive 是否递归子目录
     * @return Map，key 为文件绝对路径，value 为 MD5 值；不可读文件自动跳过
     */
    public static Map<String, String> md5Dir(File dir, boolean recursive) {
        if (dir == null || !dir.isDirectory()) {
            throw new IllegalArgumentException("目录不存在或不是有效目录: " + dir);
        }
        Map<String, String> result = new HashMap<>();
        collectFileMd5(dir, recursive, result);
        return result;
    }

    private static void collectFileMd5(File dir, boolean recursive, Map<String, String> result) {
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File f : files) {
            if (f.isDirectory() && recursive) {
                collectFileMd5(f, true, result);
            } else if (f.isFile()) {
                // 跳过不可读文件，不抛异常
                md5File(f).ifPresent(md5 -> result.put(f.getAbsolutePath(), md5));
            }
        }
    }

    // ==================== 文件校验 ====================

    /**
     * 判断文件是否可读
     *
     * @param file 文件对象
     * @return true 表示文件存在、是文件、且可读
     */
    public static boolean isReadableFile(File file) {
        return file != null && file.isFile() && file.canRead();
    }

    // ==================== 内部方法 ====================

    private static MessageDigest getMessageDigest() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            // MD5 是 JDK 必须支持的算法，理论上不会走到这里
            throw new IllegalStateException("MD5 算法不可用", e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        char[] hex = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            hex[i * 2] = HEX_CHARS[v >>> 4];
            hex[i * 2 + 1] = HEX_CHARS[v & 0x0F];
        }
        return new String(hex);
    }
}
