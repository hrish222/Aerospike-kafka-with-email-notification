package com.restapi.Configuration;



import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("aerospike")
public class aerospikeConfiguration {
    private String host;
    private int port;
    private String namespace;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
