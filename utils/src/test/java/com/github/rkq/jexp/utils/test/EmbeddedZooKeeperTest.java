package com.github.rkq.jexp.utils.test;

import com.github.rkq.jexp.utils.EmbeddedZooKeeper;
import org.junit.Test;

/**
 * Created by rick on 8/20/15.
 */
public class EmbeddedZooKeeperTest {
    @Test
    public void testStartZK() {
        try {
            EmbeddedZooKeeper zk = new EmbeddedZooKeeper(2185);
            zk.start();
            Thread.sleep(5000);
            zk.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
