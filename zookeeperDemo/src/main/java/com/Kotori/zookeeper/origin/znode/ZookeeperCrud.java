package com.Kotori.zookeeper.origin.znode;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperCrud {
    private String connectString = "127.0.0.1:2181";

    private ZooKeeper zooKeeper;

    public ZookeeperCrud(){
        try {
            this.zooKeeper = new ZooKeeper(connectString, 5000, null);
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
            if(null != zooKeeper.exists(path, false)) {
                zooKeeper.delete(path, -1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
