package com.github.rkq.jexp.utils;

import com.google.common.io.Files;
import org.apache.zookeeper.server.ServerConfig;
import org.apache.zookeeper.server.ZooKeeperServerMain;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by rick on 8/20/15.
 */
public class EmbeddedZooKeeper extends ZooKeeperServerMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddedZooKeeper.class);
    private final int port;

    public EmbeddedZooKeeper(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        File dataDir = Files.createTempDir();
        dataDir.deleteOnExit();
        Properties props = new Properties();
        props.put("dataDir", dataDir.getAbsolutePath());
        props.put("clientPort", port);
        QuorumPeerConfig quorumConfig = new QuorumPeerConfig();
        quorumConfig.parseProperties(props);
        final ServerConfig config = new ServerConfig();
        config.readFrom(quorumConfig);
        new Thread(new Runnable() {
            public void run() {
                try {
                    runFromConfig(config);
                } catch (IOException e) {
                    LOGGER.error("exception {} in starting embedded zookeeper.", e.getMessage());
                }
            }
        }).start();
        LOGGER.info("embedded zookeeper started on port {}.", port);
    }

    public void stop() {
        shutdown();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Ignore
        }
        LOGGER.info("embedded zookeeper shutdown.");
    }
}
