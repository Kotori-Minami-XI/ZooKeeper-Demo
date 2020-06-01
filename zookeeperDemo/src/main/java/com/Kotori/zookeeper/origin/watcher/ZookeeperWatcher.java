package com.Kotori.zookeeper.origin.watcher;

import org.apache.zookeeper.*;

public class ZookeeperWatcher implements Watcher{
    private String connectString = "127.0.0.1:2181";

    private ZooKeeper zooKeeper;

    private String nodePath;

    public String getNodePath() {
        return nodePath;
    }

    public void setNodePath(String nodePath) {
        this.nodePath = nodePath;
    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public ZookeeperWatcher(){
        try {
            this.zooKeeper = new ZooKeeper(connectString, 5000, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 创建永久节点
     */
    public String createPersistent(String path, String data) {
        try {
            return zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 创建临时节点，session失效后会自动销毁
     */
    public String createEphemeral(String path, String data) {
        try {
            return zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 删除节点
     */
    public void deleteNode (String path) {
        try {
            if(null != zooKeeper.exists(path, true)) {
                zooKeeper.delete(path, -1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        //获取当前的状态
        Event.KeeperState keeperState = watchedEvent.getState();
        //获取通知类型
        Event.EventType eventType=watchedEvent.getType();
        //获取操作节点的路径
        String path=watchedEvent.getPath();

        try {
            // 监听节点活动
            zooKeeper.exists(this.getNodePath(),true);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("watcher-------------------------" + watchedEvent);
    }
}
