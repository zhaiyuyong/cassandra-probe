package io.digitalis.cassandra.probe.actions;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import io.digitalis.cassandra.probe.ProbeLoggerFactory;
import io.digitalis.cassandra.probe.model.HostProbe;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.net.telnet.TelnetClient;

import ch.qos.logback.classic.Logger;

public class TelnetPortProbe implements ProbeAction {

    private static final Logger LOG = ProbeLoggerFactory.getLogger(TelnetPortProbe.class);

    private int port;
    private final HostProbe host;
    private final int timeoutMs;
    private final String description;

    public TelnetPortProbe(final String description, final HostProbe host, final int port, final int timeoutMs) {
	this.description = description;
	this.host = host;
	this.port = port;
	this.timeoutMs = timeoutMs;
    }

    @Override
    public boolean execute() throws FatalProbeException {

	boolean result = false;

	String toAddress = host.getToAddress();

	TelnetClient telnetClient = new TelnetClient();
	StopWatch stopWatch = new StopWatch();
	try {
	    stopWatch.start();
	    telnetClient.setDefaultTimeout(timeoutMs);
	    //telnetClient.setSoTimeout(timeoutMs);
	    telnetClient.connect(host.getToAddress(), this.port);
	} catch (UnknownHostException uhe) {
	    String msg = "Could not resolve host while attempting to telnet to Cassandra host '" + toAddress + "' on port '" + this.port + "' :" + uhe.getMessage() + " : " + this.host;
	    throw new FatalProbeException(msg, uhe, this.host);
	} catch (SocketException se) {
	    String msg = "Got SocketException while attempting to telnet to Cassandra host '" + toAddress + "' on port '" + this.port + "' :" + se.getMessage() + " : " + this.host;
	    throw new FatalProbeException(msg, se, this.host);
	} catch (IOException ie) {
	    String msg = "Got IOException while attempting to telnet to Cassandra host '" + toAddress + "' on port '" + this.port + "' :" + ie.getMessage() + " : " + this.host;
	    throw new FatalProbeException(msg, ie, this.host);
	} catch (Exception e) {
	    String msg = "Problem ecountered attempting to telnet to Cassandra host '" + toAddress + "' on port '" + this.port + "' :" + e.getMessage() + " : " + this.host;
	    throw new FatalProbeException(msg, e, this.host);
	} finally {
	    stopWatch.stop();
	    LOG.info(description + " - Took " + stopWatch.getTime() + " (ms) to telnet to host '" + toAddress + "' on port '" + this.port + " : " + this.host);

	    if (telnetClient != null) {
		try {
		    telnetClient.disconnect();
		    telnetClient = null;
		} catch (IOException e) {
		}
	    }

	}

	return result;

    }
}
