package com.github.rkq.jexp.kafka;

import com.google.common.io.Files;
import kafka.server.KafkaConfig;
import kafka.server.KafkaServer;
import kafka.utils.Time;
import org.apache.zookeeper.server.ServerConfig;
import org.apache.zookeeper.server.ZooKeeperServerMain;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by rick on 8/19/15.
 */

/**
 * http://stackoverflow.com/questions/28324736/error-producing-to-embedded-kafka-queue-after-upgrade-from-0-7-to-0-8-1-1
 */
class SystemTime implements Time {

    public long milliseconds() {
        return System.currentTimeMillis();
    }

    public long nanoseconds() {
        return System.nanoTime();
    }

    public void sleep(long arg0) {
        try {
            Thread.sleep(arg0);
        } catch (InterruptedException e) {
            // Nothing to do
        }
    }

}

public class EmbeddedKafkaTest {
    public static void main(String[] args) throws Exception {
        // run zk
        File zkDataDir = Files.createTempDir();
        zkDataDir.deleteOnExit();
        System.out.println(zkDataDir.getAbsolutePath());
        Properties zkProps = new Properties();
        zkProps.put("dataDir", zkDataDir.getAbsolutePath());
        zkProps.put("clientPort", 2185);
        QuorumPeerConfig quorumConfig = new QuorumPeerConfig();
        quorumConfig.parseProperties(zkProps);
        final ServerConfig zkConfig = new ServerConfig();
        zkConfig.readFrom(quorumConfig);
        final ZooKeeperServerMain zkServer = new ZooKeeperServerMain();
        new Thread(new Runnable() {
            public void run() {
                try {
                    zkServer.runFromConfig(zkConfig);
                } catch (IOException e) {
                    // TODO
                }
            }
        }).start();

        // run kafka
        File kafkaDataDir = Files.createTempDir();
        kafkaDataDir.deleteOnExit();
        Properties kafkaProps = new Properties();
        kafkaProps.put("broker.id", "1");
        kafkaProps.put("log.dirs", kafkaDataDir.getAbsolutePath());
        kafkaProps.put("zookeeper.connect", "localhost:2185");
        kafkaProps.put("port", "9527");
        KafkaConfig kafkaConfig = new KafkaConfig(kafkaProps);
        KafkaServer kafkaServer = new KafkaServer(kafkaConfig, new SystemTime());
        kafkaServer.startup();
        kafkaServer.awaitShutdown();
    }
}
