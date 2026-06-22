package com.weng.system.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 雪花算法工具类
 * <p>
 * 雪花算法生成64位Long型ID，结构如下：
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 * <p>
 * 1位符号位（始终为0）
 * 41位时间戳（毫秒级）
 * 10位工作机器ID（0-1023）
 * 12位序列号（0-4095）
 * <p>
 * 时间范围：约69年
 *
 * @author weng.yifeng
 * @date 2026-04-10
 */
public class SnowflakeIdUtil {

    private static final Logger logger = LoggerFactory.getLogger(SnowflakeIdUtil.class);

    /**
     * 起始时间戳 (2025-01-01 00:00:00)
     */
    private static final long EPOCH = 1704067200000L;

    /**
     * 机器ID所占的位数
     */
    private static final long WORKER_ID_BITS = 10L;

    /**
     * 数据中心ID所占的位数
     */
    private static final long DATACENTER_ID_BITS = 2L;

    /**
     * 序列号所占的位数
     */
    private static final long SEQUENCE_BITS = 12L;

    /**
     * 机器ID最大值
     */
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    /**
     * 数据中心ID最大值
     */
    private static final long MAX_DATACENTER_ID = ~(-1L << DATACENTER_ID_BITS);

    /**
     * 机器ID左移位数
     */
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;

    /**
     * 数据中心ID左移位数
     */
    private static final long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    /**
     * 时间戳左移位数
     */
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;

    /**
     * 序列号最大值
     */
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    /**
     * 上一毫秒序列号
     */
    private long lastTimestamp = -1L;

    /**
     * 当前毫秒内序列号
     */
    private long sequence = 0L;

    /**
     * 工作机器ID
     */
    private final long workerId;

    /**
     * 数据中心ID
     */
    private final long datacenterId;

    /**
     * 雪花算法实例
     */
    private static volatile SnowflakeIdUtil instance;

    /**
     * 私有构造方法
     *
     * @param workerId     工作机器ID (0-1023)
     * @param datacenterId 数据中心ID (0-3)
     */
    private SnowflakeIdUtil(long workerId, long datacenterId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", MAX_WORKER_ID));
        }
        if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", MAX_DATACENTER_ID));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
        logger.info("SnowflakeIdUtil initialized with workerId: {}, datacenterId: {}", workerId, datacenterId);
    }

    /**
     * 获取单例实例（使用默认的workerId和datacenterId）
     * 注意：多实例部署时需要确保workerId唯一
     *
     * @return 雪花算法实例
     */
    public static SnowflakeIdUtil getInstance() {
        if (instance == null) {
            synchronized (SnowflakeIdUtil.class) {
                if (instance == null) {
                    // 默认使用workerId=0, datacenterId=0
                    instance = new SnowflakeIdUtil(0L, 0L);
                }
            }
        }
        return instance;
    }

    /**
     * 获取单例实例（指定workerId和datacenterId）
     *
     * @param workerId     工作机器ID (0-1023)
     * @param datacenterId 数据中心ID (0-3)
     * @return 雪花算法实例
     */
    public static SnowflakeIdUtil getInstance(long workerId, long datacenterId) {
        if (instance == null) {
            synchronized (SnowflakeIdUtil.class) {
                if (instance == null) {
                    instance = new SnowflakeIdUtil(workerId, datacenterId);
                }
            }
        }
        return instance;
    }

    /**
     * 生成下一个ID（线程安全）
     *
     * @return 雪花ID
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        // 如果当前时间小于上次生成ID的时间戳，说明时钟回拨
        if (timestamp < lastTimestamp) {
            long offset = lastTimestamp - timestamp;
            if (offset <= 5) {
                // 如果时钟回拨在5ms以内，等待
                try {
                    wait(offset << 1);
                    timestamp = timeGen();
                    if (timestamp < lastTimestamp) {
                        throw new RuntimeException("Clock moved backwards. Refusing to generate id");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", offset));
            }
        }

        // 如果是同一毫秒生成的
        if (lastTimestamp == timestamp) {
            // 序列号自增
            sequence = (sequence + 1) & SEQUENCE_MASK;
            // 如果序列号溢出，等待下一毫秒
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 不同毫秒，序列号重置
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        // 组合生成ID
        return ((timestamp - EPOCH) << TIMESTAMP_SHIFT)
                | (datacenterId << DATACENTER_ID_SHIFT)
                | (workerId << WORKER_ID_SHIFT)
                | sequence;
    }

    /**
     * 生成下一个ID（非线程安全，适用于性能要求极高的场景）
     *
     * @return 雪花ID
     */
    public long nextIdUnsafe() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - EPOCH) << TIMESTAMP_SHIFT)
                | (datacenterId << DATACENTER_ID_SHIFT)
                | (workerId << WORKER_ID_SHIFT)
                | sequence;
    }

    /**
     * 预生成下一个ID（非线程安全，适用于批量生成场景）
     *
     * @param count 生成数量
     * @return ID数组
     */
    public long[] nextIds(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("count must be positive");
        }

        long[] ids = new long[count];
        for (int i = 0; i < count; i++) {
            ids[i] = nextIdUnsafe();
        }
        return ids;
    }

    /**
     * 等待下一毫秒生成ID
     *
     * @param lastTimestamp 上次生成ID的时间戳
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取当前时间戳
     *
     * @return 当前时间戳（毫秒）
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 解析雪花ID
     *
     * @param id 雪花ID
     * @return ID信息对象
     */
    public static IdInfo parseId(long id) {
        IdInfo info = new IdInfo();
        info.setId(id);

        // 时间戳部分
        long timestamp = (id >> TIMESTAMP_SHIFT) + EPOCH;
        info.setTimestamp(timestamp);
        info.setDate(DateUtil.convertTimeToString(timestamp));

        // 数据中心ID
        info.setDatacenterId((id >> DATACENTER_ID_SHIFT) & MAX_DATACENTER_ID);

        // 工作机器ID
        info.setWorkerId((id >> WORKER_ID_SHIFT) & MAX_WORKER_ID);

        // 序列号
        info.setSequence(id & SEQUENCE_MASK);

        // 生成时间
        info.setGenTime(DateUtil.convertTimeToString(System.currentTimeMillis()));

        return info;
    }

    /**
     * ID信息对象
     */
    public static class IdInfo {
        private long id;
        private long timestamp;
        private String date;
        private long datacenterId;
        private long workerId;
        private long sequence;
        private String genTime;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public long getDatacenterId() {
            return datacenterId;
        }

        public void setDatacenterId(long datacenterId) {
            this.datacenterId = datacenterId;
        }

        public long getWorkerId() {
            return workerId;
        }

        public void setWorkerId(long workerId) {
            this.workerId = workerId;
        }

        public long getSequence() {
            return sequence;
        }

        public void setSequence(long sequence) {
            this.sequence = sequence;
        }

        public String getGenTime() {
            return genTime;
        }

        public void setGenTime(String genTime) {
            this.genTime = genTime;
        }

        @Override
        public String toString() {
            return String.format(
                    "IdInfo{id=%d, timestamp=%d, date=%s, datacenterId=%d, workerId=%d, sequence=%d, genTime=%s}",
                    id, timestamp, date, datacenterId, workerId, sequence, genTime
            );
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        // 创建实例
        SnowflakeIdUtil snowflakeIdUtil = SnowflakeIdUtil.getInstance(1L, 1L);

        // 生成10个ID
        System.out.println("Generating 10 IDs:");
        for (int i = 0; i < 10; i++) {
            long id = snowflakeIdUtil.nextId();
            IdInfo info = parseId(id);
            System.out.println("ID " + (i + 1) + ": " + id + " -> " + info);
        }

        // 批量生成ID
        System.out.println("\nBatch generating 5 IDs:");
        long[] ids = snowflakeIdUtil.nextIds(5);
        for (int i = 0; i < ids.length; i++) {
            System.out.println("ID " + (i + 1) + ": " + ids[i]);
        }

        // 解析ID
        System.out.println("\nParsing last generated ID:");
        long lastId = snowflakeIdUtil.nextId();
        IdInfo info = parseId(lastId);
        System.out.println(info);
    }
}
