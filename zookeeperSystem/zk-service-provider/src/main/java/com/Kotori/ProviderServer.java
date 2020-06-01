package com.Kotori;

import org.apache.zookeeper.*;

public class ProviderServer implements Watcher {
    private ZookeeperServiceRegister zookeeperServiceRegister = new ZookeeperServiceRegister();

    public void register(String serverName) throws KeeperException, InterruptedException {
        ZooKeeper zooKeeper = zookeeperServiceRegister.getConnection(this);
        String node = zooKeeper.create(
                 "/server" + zookeeperServiceRegister.root, serverName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("已经创建" + serverName + "节点" + node);
    }

    public void process(WatchedEvent watchedEvent) {

    }


    public static void main(String[] args) throws KeeperException, InterruptedException {
        ProviderServer providerServer = new ProviderServer();

        StatDto statDto = new StatDto();
        int i = 0;
//        statDto.setIp(args[i++]);
//        statDto.setPort(args[i++]);
//        statDto.setName(args[i++]);
        statDto.setNum(0);
        statDto.setStatus(ServerStatus.wait);

        providerServer.register(statDto.toString());
        Thread.sleep(Long.MAX_VALUE);
    }

}
