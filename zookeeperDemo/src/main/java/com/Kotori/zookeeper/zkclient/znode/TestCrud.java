package com.Kotori.zookeeper.zkclient.znode;

import com.Kotori.domain.User;
import org.junit.jupiter.api.Test;

public class TestCrud {
    @Test
    public void test() {
        ZkClientCrud zkClientCrud = new ZkClientCrud();
        User user = new User("lz", 15);
        zkClientCrud.createPersistent("/node1", user);

        System.out.println(zkClientCrud.readData("/node1"));
    }
}
