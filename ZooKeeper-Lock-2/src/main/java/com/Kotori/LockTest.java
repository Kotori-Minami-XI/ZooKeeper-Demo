package com.Kotori;

import org.apache.zookeeper.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LockTest {
    private ZookeeperLock lock;

    @Test
    public void JVM2() throws Exception {
        String instanceName = "JVM2";
        lock = new ZookeeperLock("/mainLock",instanceName);
        int i=0;
        while (true) {
            try {
                lock.Lock();
                System.out.println(instanceName + "--抢到了锁"+ i++);
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unLock();
            }
        }
    }

}
