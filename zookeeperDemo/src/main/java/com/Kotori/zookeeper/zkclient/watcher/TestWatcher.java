package com.Kotori.zookeeper.zkclient.watcher;

import com.Kotori.domain.User;
import com.Kotori.zookeeper.zkclient.znode.ZkClientCrud;
import org.junit.jupiter.api.Test;

public class TestWatcher {
    @Test
    public void test() throws InterruptedException {
        ZkClientWatcher zkClientWatcher = new ZkClientWatcher();
        zkClientWatcher.listen("/node1");
        Thread.sleep(Long.MAX_VALUE);
    }
}
