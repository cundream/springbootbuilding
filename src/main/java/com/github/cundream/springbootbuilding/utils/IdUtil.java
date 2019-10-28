package com.github.cundream.springbootbuilding.utils;

import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * @author : Lison
 * @Date: 2019/10/28 11:30
 * @Description:
 */
@Slf4j
public class IdUtil {
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    /**
     * 开始时间截 (2015-01-01)
     */
    private static final long START_TIME = 1420041600000L;

    /**
     * 数据中心ID所占的位数
     */
    private static final long DATA_CENTER_ID_BITS = 4L;

    /**
     * 机器ID所占的位数
     */
    private static final long WORKER_ID_BITS = 6L;

    /**
     * 序列在ID中占的位数
     */
    private static final long SEQUENCE_BITS = 12L;

    /**
     * 生成数据中心ID的掩码，同时也是最大数值
     */
    private static final long DATA_CENTER_ID_MASK = -1L ^ (-1L << DATA_CENTER_ID_BITS);

    /**
     * 生成机器ID的掩码，同时也是最大数值
     */
    private static final long WORKER_ID_MASK = -1L ^ (-1L << WORKER_ID_BITS);

    /**
     * 生成序列的掩码，同时也是最大数值
     */
    private static final long SEQUENCE_MASK = -1L ^ (-1L << SEQUENCE_BITS);

    /**
     * 时间截向左移
     */
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;

    /**
     * 数据中心ID向左移
     */
    private static final long DATA_CENTER_ID_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    /**
     * 机器ID向左移
     */
    private static final long WORKER_ID_LEFT_SHIFT = SEQUENCE_BITS;

    /**
     * 数据中心ID
     */
    private long dataCenterId;

    /**
     * 工作机器ID
     */
    private long workerId;

    /**
     * 毫秒内序列
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    /**
     * 构造函数
     */
    private IdUtil() {
        String serverHostName = StringUtils.isEmpty(System.getProperty("server.hostname")) ? "localhost" : System.getProperty("server.hostname");
        String serverPort = StringUtils.isEmpty(System.getProperty("server.port")) ? "8080" : System.getProperty("server.port");

        long serverHostNameHash = Math.abs(Hashing.murmur3_128().hashUnencodedChars(serverHostName).asInt());
        long serverPortHash = Math.abs(Hashing.murmur3_128().hashUnencodedChars(serverPort).asInt());

        this.dataCenterId = serverHostNameHash & DATA_CENTER_ID_MASK;
        this.workerId = serverPortHash & WORKER_ID_MASK;

        log.info("IdWorker Starting. TIMESTAMP_LEFT_SHIFT:{}, DATA_CENTER_ID_BITS:{}, WORKER_ID_BITS:{}, SEQUENCE_BITS:{}, dataCenterId: {}, workerId:{}",
                TIMESTAMP_LEFT_SHIFT, DATA_CENTER_ID_BITS, WORKER_ID_BITS, SEQUENCE_BITS, dataCenterId, workerId);
    }

    public static IdUtil newInstance() {
        IdUtil instance = IdUtilHolder.instance;
        return instance;
    }

    /**
     * 生成ID
     *
     * @return
     */
    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate ID for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果是同一时间生成的，则自增
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0L) {
                //生成下一个毫秒级的序列
                timestamp = tilNextMillis();
                //序列从0开始
                sequence = RANDOM.nextInt(299);
            }
        } else {
            //如果发现是下一个时间单位，则自增序列回0，重新自增
            sequence = RANDOM.nextInt(299);
        }

        lastTimestamp = timestamp;

        //看本文第二部分的结构图，移位并通过或运算拼到一起组成64位的ID

        long id = ((timestamp - START_TIME) << TIMESTAMP_LEFT_SHIFT)
                | (dataCenterId << DATA_CENTER_ID_LEFT_SHIFT)
                | (workerId << WORKER_ID_LEFT_SHIFT)
                | sequence;

        return id;
    }

    protected long tilNextMillis() {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("IdWorker{");
        sb.append("dataCenterId=").append(dataCenterId);
        sb.append(", workerId=").append(workerId);
        sb.append(", sequence=").append(sequence);
        sb.append(", lastTimestamp=").append(lastTimestamp);
        sb.append('}');
        return sb.toString();
    }

    private static class IdUtilHolder {
        public static IdUtil instance = new IdUtil();
    }
}
