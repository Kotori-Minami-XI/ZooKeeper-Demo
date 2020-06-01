package com.Kotori.zookeeper.zkclient.znode;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

public class ZkClientCrud {
    private String connectString = "127.0.0.1:2181";

    private ZkClient zkClient;

    public ZkClientCrud() {
        this.zkClient = new ZkClient(connectString, 5000,5000,new SerializableSerializer());
    }

    /***
     * 创建永久节点
     */
    public void createPersistent(String path, Object data) {
        zkClient.createPersistent(path, data);
    }

    /***
     * 创建永久节点
     */
    public Object readData(String path) {
        return zkClient.readData(path);
    }
}
