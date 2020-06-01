package com.Kotori.zookeeper.origin.znode;

import org.junit.jupiter.api.Test;


public class TestZnode {
    /***
     * 创建永久节点
     */
    @Test
    public void createPersistent() {
        ZookeeperCrud zookeeperCrud = new ZookeeperCrud();
        zookeeperCrud.createPersistent("/node2","2010");
    }

    /***
     * 创建临时节点，session失效后会自动销毁
     */
    @Test
    public void deletePersistent() {
        ZookeeperCrud zookeeperCrud = new ZookeeperCrud();
        zookeeperCrud.deleteNode("/node2");
    }

    /***
     * 创建临时节点，session失效后会自动销毁
     */
    @Test
    public void createEphemeral() {
        ZookeeperCrud zookeeperCrud = new ZookeeperCrud();
        zookeeperCrud.createEphemeral("/node3","2011");
    }


}
