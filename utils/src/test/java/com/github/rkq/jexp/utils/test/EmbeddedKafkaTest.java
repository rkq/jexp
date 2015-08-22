package com.github.rkq.jexp.utils.test;

import com.github.rkq.jexp.utils.EmbeddedKafka;
import com.github.rkq.jexp.utils.EmbeddedZooKeeper;
import org.junit.Test;

/**
 * Created by rick on 8/22/15.
 */
public class EmbeddedKafkaTest {
    @Test
    public void testStartKafka() {
        try {
            EmbeddedZooKeeper zk = new EmbeddedZooKeeper(2185);
            zk.start();
            EmbeddedKafka kafka = new EmbeddedKafka("localhost:2185", 9098);
            kafka.start();
            Thread.sleep(5000);
            kafka.stop();
            zk.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
