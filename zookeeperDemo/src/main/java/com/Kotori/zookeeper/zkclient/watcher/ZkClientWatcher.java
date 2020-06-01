package com.Kotori.zookeeper.zkclient.watcher;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.util.List;

public class ZkClientWatcher {
    private String connectString = "127.0.0.1:2181";

    private ZkClient zkClient;

    public ZkClientWatcher() {
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

    public void listen(String path) {
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.printf("变更的节点为%s,%s",s,o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.printf("删除的节点为%s",s);
            }
        });


        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println("parent path:" + s + "child path:" + list);
            }
        });
    }

}
