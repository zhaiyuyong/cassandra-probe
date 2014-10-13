package com.datastax.probe.model;

public class HostProbe {
    
    private final String toAddress;
    private final int nativePort;
    private final int storagePort;
    private final int rpcPort;
    private final String dc;
    private final String rack;
    private final String cassandraVersion;
    
    
    public HostProbe(final String toAddress, final int nativePort, final int storagePort, final int rpcPort, final String dc, final String rack, final String cassandraVersion) {
	this.toAddress = toAddress;
	this.nativePort = nativePort;
	this.storagePort = storagePort;
	this.rpcPort = rpcPort;
	this.dc = dc;
	this.rack = rack;
	this.cassandraVersion = cassandraVersion;
    }


    public String getToAddress() {
        return toAddress;
    }

    public int getNativePort() {
        return nativePort;
    }

    public int getStoragePort() {
        return storagePort;
    }

    public int getRpcPort() {
        return rpcPort;
    }

    public String getDc() {
        return dc;
    }

    public String getRack() {
        return rack;
    }

    public String getCassandraVersion() {
        return cassandraVersion;
    }


    @Override
    public String toString() {
	return "HostProbe [toAddress=" + toAddress + ", nativePort=" + nativePort + ", storagePort=" + storagePort + ", rpcPort=" + rpcPort + ", dc=" + dc + ", rack=" + rack
		+ ", cassandraVersion=" + cassandraVersion + "]";
    }




}