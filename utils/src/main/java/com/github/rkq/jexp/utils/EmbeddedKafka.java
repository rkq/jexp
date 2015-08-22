package com.github.rkq.jexp.utils;

import com.google.common.io.Files;
import kafka.server.KafkaConfig;
import kafka.server.KafkaServer;
import kafka.utils.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Properties;

/**
 * Created by rick on 8/20/15.
 */
public class EmbeddedKafka {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddedZooKeeper.class);
    private String zk;
    private int port;
    private KafkaServer kafkaServer;

    public EmbeddedKafka(String zk, int port) {
        this.zk = zk;
        this.port = port;
    }
    public void start() {
        File kafkaDataDir = Files.createTempDir();
        kafkaDataDir.deleteOnExit();
        Properties kafkaProps = new Properties();
        kafkaProps.put("broker.id", "1");
        kafkaProps.put("log.dirs", kafkaDataDir.getAbsolutePath());
        kafkaProps.put("zookeeper.connect",  zk);
        kafkaProps.put("port", Integer.toString(port));
        KafkaConfig kafkaConfig = new KafkaConfig(kafkaProps);
        kafkaServer = new KafkaServer(kafkaConfig, new SystemTime());
        kafkaServer.startup();
        LOGGER.info("Embedded kafka started on port {}.", port);
    }

    public void stop() {
        kafkaServer.shutdown();
        LOGGER.info("Embedded kafka shutdown.");
    }

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
}
