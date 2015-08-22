package com.github.rkq.jexp.zk;

import com.google.common.io.Files;
import org.apache.zookeeper.server.ServerConfig;
import org.apache.zookeeper.server.ZooKeeperServerMain;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;

import java.io.File;
import java.util.Properties;

/**
 * Created by rick on 8/19/15.
 */
public class EmbeddedZKTest {
    public static void main(String[] args) throws Exception {
        File dataDir = Files.createTempDir();
        dataDir.deleteOnExit();
        System.out.println(dataDir.getAbsolutePath());
        Properties props = new Properties();
        props.put("dataDir", dataDir.getAbsolutePath());
        props.put("clientPort", 2185);
        QuorumPeerConfig quorumConfig = new QuorumPeerConfig();
        quorumConfig.parseProperties(props);
        ServerConfig config = new ServerConfig();
        config.readFrom(quorumConfig);
        ZooKeeperServerMain zkServer = new ZooKeeperServerMain();
        zkServer.runFromConfig(config);
    }
}