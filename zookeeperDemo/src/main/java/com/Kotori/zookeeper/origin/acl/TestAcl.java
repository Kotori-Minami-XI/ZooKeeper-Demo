package com.Kotori.zookeeper.origin.acl;

import org.junit.jupiter.api.Test;


public class TestAcl {
    /***
     * 创建永久节点
     */
    @Test
    public void testAcl() throws InterruptedException {
        ZookeeperAcl zookeeperAcl = new ZookeeperAcl(true);
        zookeeperAcl.createPersistent("/node11", "123");
    }

}
