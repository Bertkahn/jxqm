package com.cloud.common.util;
//import com.baomidou.mybatisplus.core.toolkit.Sequence;
import com.baomidou.mybatisplus.core.toolkit.Sequence;
//import com.baomidou.mybatisplus.toolkit.Sequence;
import org.springframework.beans.factory.annotation.Value;

public class IdUtil {
//    @Value("${server.worker-id}")
//    private Long workerId;
//    @Value("${server.datacenter-id}")
//    private Long datacenterId;

    private static Sequence sequence = new Sequence();

    public static Long getId() {
        return sequence.nextId();
    }
}