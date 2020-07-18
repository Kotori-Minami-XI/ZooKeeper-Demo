package com.Kotori;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class KWatcher implements Watcher {
    String connectString = "127.0.0.1:2181";
    ZooKeeper zooKeeper;

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent.getType());
        System.out.println(watchedEvent.getState());
        System.out.println(watchedEvent.getPath());
        try {
            Stat stat = zooKeeper.exists("/node1", this);
            //监听所有的子节点
            if (null != stat) {
                List<String> children = zooKeeper.getChildren("/node1", this);
                for (String child : children) {
                    System.out.println(child);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() throws Exception {
        zooKeeper = new ZooKeeper(connectString, 5000, null);
        String node = "/node1";
        Stat stat = zooKeeper.exists("/node1", this);
        System.in.read();
    }

    @Test
    public void testInsertNode() {
        try {
            this.zooKeeper = new ZooKeeper(connectString, 10000, null);
            String data = "my data";
            String node = "/node1";

            Stat stat = zooKeeper.exists("/node1", null);
            if (null == stat) {
                String res = zooKeeper.create(node, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                System.out.println(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetNode() {
        try {
            this.zooKeeper = new ZooKeeper(connectString, 5000, null);
            String node = "/node1";
            byte[] data = zooKeeper.getData(node, null, null);
            System.out.println(new String (data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExistNode() {
        try {
            this.zooKeeper = new ZooKeeper(connectString, 5000, null);
            String node = "/node1";
            Stat stat = zooKeeper.exists("/node1", null);
            System.out.println(stat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {

        }
    }




}
