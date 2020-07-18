package com.Kotori;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.apache.zookeeper.Watcher.Event.EventType.NodeChildrenChanged;

public class ZookeeperLock implements Watcher {
    private String connectString = "127.0.0.1:2181";
    private ZooKeeper zooKeeper;
    private String targetNode;
    private String instanceName;
    private CountDownLatch countDownLatch;

    public ZookeeperLock(String targetNode, String instanceName) {
        this.targetNode = targetNode;
        this.instanceName = instanceName;
        try {
            this.zooKeeper = new ZooKeeper(connectString, 10000, null);

            if (null != zooKeeper.exists(targetNode, null)) {
                zooKeeper.delete(targetNode, -1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Lock() throws Exception {
        if (tryLock()) {
            return;
        }
        // 获取锁失败了,陷入沉睡并用自旋锁检查,监听主节点的任何变动
        while (true) {
            //监听主节点的任何变动
            //zooKeeper.exists(targetNode, this);
            System.out.println(instanceName+"抢锁失败");

            countDownLatch = new CountDownLatch(1);
            countDownLatch.await();

            if(tryLock()){
                return;
            }
        }
    }

    public Boolean tryLock() {
        try {
            zooKeeper.create(targetNode, instanceName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void unLock() {
        try {
            System.out.println("unLock");
            zooKeeper.delete(targetNode,-1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent.getType()+"----------------"+watchedEvent.getPath());
        if (countDownLatch == null) {
            throw new NullPointerException("countDownLatch不能为空！");
        }
        countDownLatch.countDown();

        try {
            zooKeeper.exists(targetNode, this);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
