package com.Kotori.zookeeper.origin.acl;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperAcl {
    private String connectString = "127.0.0.1:2181";

    private ZooKeeper zooKeeper;

    private static String scheme = "digest";
    private static String auth = "1111";

    /***
     *  需要权限登录
     */
    public ZookeeperAcl(Boolean flag){
        try {
            this.zooKeeper = new ZooKeeper(connectString, 5000, null);
            if (flag) {
                // 添加权限
                zooKeeper.addAuthInfo(scheme, auth.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 创建永久节点
     */
    public String createPersistent(String path, String data) {
        try {
            return zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
