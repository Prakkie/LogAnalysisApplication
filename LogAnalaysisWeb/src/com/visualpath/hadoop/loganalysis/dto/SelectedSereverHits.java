package com.visualpath.hadoop.loganalysis.dto;

import java.util.List;

public class SelectedSereverHits {
	List<String> server;
	List<String> serverYearly;
	List<String> serverAvg;
	String selServer ;

	
	public String getSelServer() {
		return selServer;
	}

	public void setSelServer(String selServer) {
		this.selServer = selServer;
	}

	public List<String> getServer() {
		return server;
	}

	public void setServer(List<String> server) {
		this.server = server;
	}

	public List<String> getServerYearly() {
		return serverYearly;
	}

	public void setServerYearly(List<String> serverYearly) {
		this.serverYearly = serverYearly;
	}

	public List<String> getServerAvg() {
		return serverAvg;
	}

	public void setServerAvg(List<String> serverAvg) {
		this.serverAvg = serverAvg;
	}

}
