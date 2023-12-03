package com.couchbase;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.ClusterOptions;

/**
 * @author bhargodev on 03/12/23
 **/
public class CouchbaseDAO {

    private Cluster cluster;
    private String bucketName;

    public CouchbaseDAO() {
        this.cluster = Cluster.connect("couchbase://127.0.0.1", ClusterOptions.clusterOptions("", ""));
    }
}
