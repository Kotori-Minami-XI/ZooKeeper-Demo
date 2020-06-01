package com.Kotori.zookeeper.origin.watcher;

import org.apache.zookeeper.KeeperException;
import org.junit.jupiter.api.Test;


public class TestWatcher {
    /***
     * 创建永久节点
     */
    @Test
    public void createPersistent() throws InterruptedException {
        ZookeeperWatcher zookeeperWatcher = new ZookeeperWatcher();
        zookeeperWatcher.createPersistent("/node4","2014");

        Thread.sleep(Long.MAX_VALUE);
    }

    /***
     * 创建临时节点，session失效后会自动销毁
     */
    @Test
    public void deletePersistent() throws InterruptedException {
        ZookeeperWatcher zookeeperWatcher = new ZookeeperWatcher();
        zookeeperWatcher.deleteNode("/node4");

        Thread.sleep(Long.MAX_VALUE);
    }

    /***
     * 创建临时节点，session失效后会自动销毁
     */
    @Test
    public void createEphemeral() throws InterruptedException {
        ZookeeperWatcher zookeeperWatcher = new ZookeeperWatcher();
        zookeeperWatcher.createEphemeral("/node6","2015");

        Thread.sleep(Long.MAX_VALUE);
    }


    @Test
    public void test() throws InterruptedException, KeeperException {
        ZookeeperWatcher zookeeperWatcher = new ZookeeperWatcher();
        zookeeperWatcher.setNodePath("/node6");
        Thread.sleep(Long.MAX_VALUE);
    }

}
