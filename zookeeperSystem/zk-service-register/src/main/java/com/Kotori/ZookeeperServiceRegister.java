package com.Kotori;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperServiceRegister {
    private ZooKeeper zooKeeper;

    public String root = "127.0.0.1:2181";

    public ZooKeeper getConnection(Watcher watcher) {
        try {
            zooKeeper = new ZooKeeper(root, 500, watcher);
            return zooKeeper;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
