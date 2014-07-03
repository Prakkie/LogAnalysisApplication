package com.visualpath.hadoop.loganalysis.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NavigableMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

import com.visualpath.hadoop.loganalysis.dto.BrowsersInfo;
import com.visualpath.hadoop.loganalysis.dto.MonthlyHits;
import com.visualpath.hadoop.loganalysis.dto.SelectedSereverHits;
import com.visualpath.hadoop.loganalysis.utill.LogAnalysisUtill;

public class HBaseConnector {
	HashMap<String, String> data = null;
	byte[] CF = Bytes.toBytes("details");
	byte[] IP_ADDRESS = Bytes.toBytes("ip_address");
	byte[] TIME_STAMP = Bytes.toBytes("time_stamp");
	byte[] REQUEST_TYPE = Bytes.toBytes("request_type");
	byte[] PRODUCT_URL = Bytes.toBytes("product_url");
	byte[] REQUEST_PROTOCOL = Bytes.toBytes("request_protocol");
	byte[] PROTOCOL_VERSION = Bytes.toBytes("protocol_version");
	byte[] RESPCODE = Bytes.toBytes("respcode");
	byte[] BYTES = Bytes.toBytes("bytes");
	byte[] URL = Bytes.toBytes("url");
	byte[] BROWSER = Bytes.toBytes("browser");
	byte[] BROWSER1 = Bytes.toBytes("browser1");
	byte[] BROWSER2 = Bytes.toBytes("browser2");
	byte[] BROWSER3 = Bytes.toBytes("browser3");
	//BrowsersInfo browsersInfo = new BrowsersInfo();
	Configuration config = HBaseConfiguration.create();

	public void consumingSplunkbrowsersInfo(BrowsersInfo browsersInfo) throws IOException {
		byte[] TABLE_NAME = Bytes.toBytes("hbaseAccessCombinedLogData");
		config.addResource(new Path("/conf/hbase-site.xml"));
		HTable table = new HTable(config, TABLE_NAME);
		Scan s = new Scan();
		s.addFamily(CF);
		ResultScanner scanner = table.getScanner(s);
		long mozilla = 0;
		long chrome = 0;
		long ie = 0;
		long safari = 0;
		long gecko = 0;
		long appleWebKit = 0;
		String browser_value = null;
		try {
			for (Result rr = scanner.next(); rr != null; rr = scanner.next()) {
				NavigableMap<byte[],byte[]> colfam = rr.getFamilyMap(CF);
				byte[] browser1 = colfam.get(BROWSER);

				if (browser1 != null) {
					browser_value = new String(browser1);
				}
				if (browser_value.equalsIgnoreCase("Mozilla")) {
					mozilla++;
				}
				if (browser_value.equalsIgnoreCase("Chrome")) {
					chrome++;
				}
				if (browser_value.equalsIgnoreCase("IE")) {
					ie++;
				}
				if (browser_value.equalsIgnoreCase("Safari")) {
					safari++;
				}
				if (browser_value.equalsIgnoreCase("Gecko")) {
					gecko++;
				}
				if (browser_value.equalsIgnoreCase("AppleWebKit")) {
					appleWebKit++;
				}
				if (browser_value.equalsIgnoreCase("Firefox")) {
					mozilla++;
				}
			}
			browsersInfo.setChrome((browsersInfo.getChrome() == 0)? chrome:browsersInfo.getChrome()+chrome);
			browsersInfo.setSafari((browsersInfo.getSafari() == 0)? safari:browsersInfo.getSafari()+safari);
			browsersInfo.setIe((browsersInfo.getIe() == 0)? ie:browsersInfo.getIe()+ie);
			browsersInfo.setFirefox((browsersInfo.getFirefox() == 0)? mozilla:browsersInfo.getFirefox()+mozilla);
			browsersInfo.setGecko((browsersInfo.getGecko() == 0)? gecko:browsersInfo.getGecko()+gecko);
			browsersInfo.setAppleWebKit((browsersInfo.getAppleWebKit() == 0)? appleWebKit:browsersInfo.getAppleWebKit()+appleWebKit);
			
		} finally {
			scanner.close();
		}
		
	}

	public void consumingWwwbrowsersInfo(BrowsersInfo browsersInfo) throws IOException {
		byte[] TABLE_NAME = Bytes.toBytes("hbaseAccessLogData");
		config.addResource(new Path("/conf/hbase-site.xml"));
		HTable table = new HTable(config, TABLE_NAME);
		Scan s = new Scan();
		s.addFamily(CF);
		ResultScanner scanner = table.getScanner(s);
		long mozilla = 0;
		long chrome = 0;
		long ie = 0;
		long safari = 0;
		long gecko = 0;
		long appleWebKit = 0;
		String browser1_value = null;
		String browser2_value = null;
		String browser3_value = null;
		try {
			for (Result rr = scanner.next(); rr != null; rr = scanner.next()) {
				NavigableMap<byte[],byte[]> colfam = rr.getFamilyMap(CF);
				byte[] browser1 = colfam.get(BROWSER1);
				byte[] browser2 = colfam.get(BROWSER2);
				byte[] browser3 = colfam.get(BROWSER3);
				if(browser1 != null){
					browser1_value = new String(browser1);
				}
				if(browser2 != null){
					browser2_value = new String(browser2);
				}
				if(browser3 != null){
					browser3_value = new String(browser3);
				}
				if (browser1_value.equalsIgnoreCase("Mozilla") || browser2_value.equalsIgnoreCase("Mozilla") || browser3_value.equalsIgnoreCase("Mozilla")) {
					mozilla++;
				} if (browser1_value.equalsIgnoreCase("Chrome") || browser2_value.equalsIgnoreCase("Chrome") || browser3_value.equalsIgnoreCase("Chrome")) {
					chrome++;
				}  if (browser1_value.equalsIgnoreCase("IE") || browser2_value.equalsIgnoreCase("IE") || browser3_value.equalsIgnoreCase("IE")) {
					ie++;
				}  if (browser1_value.equalsIgnoreCase("Safari") || browser2_value.equalsIgnoreCase("Safari") || browser3_value.equalsIgnoreCase("Safari")) {
					safari++;
				} if (browser1_value.equalsIgnoreCase("Gecko") || browser2_value.equalsIgnoreCase("Gecko") || browser3_value.equalsIgnoreCase("Gecko")) {
					gecko++;
				} if (browser1_value.equalsIgnoreCase("AppleWebKit") || browser2_value.equalsIgnoreCase("AppleWebKit") || browser3_value.equalsIgnoreCase("AppleWebKit")) {
					appleWebKit++;
				} if (browser1_value.equalsIgnoreCase("Firefox") || browser2_value.equalsIgnoreCase("Firefox") || browser3_value.equalsIgnoreCase("Firefox")) {
					mozilla++;
				} 
			}
			browsersInfo.setChrome((browsersInfo.getChrome() == 0)? chrome:browsersInfo.getChrome()+chrome);
			browsersInfo.setSafari((browsersInfo.getSafari() == 0)? safari:browsersInfo.getSafari()+safari);
			browsersInfo.setIe((browsersInfo.getIe() == 0)? ie:browsersInfo.getIe()+ie);
			browsersInfo.setFirefox((browsersInfo.getFirefox() == 0)? mozilla:browsersInfo.getFirefox()+mozilla);
			browsersInfo.setGecko((browsersInfo.getGecko() == 0)? gecko:browsersInfo.getGecko()+gecko);
			browsersInfo.setAppleWebKit((browsersInfo.getAppleWebKit() == 0)? appleWebKit:browsersInfo.getAppleWebKit()+appleWebKit);
		} finally {
			scanner.close();
		}
	}

	public void consumingApacheServerLogData(BrowsersInfo browsersInfo) throws IOException {
		byte[] TABLE_NAME = Bytes.toBytes("hbaseSecureLogData");
		config.addResource(new Path("/conf/hbase-site.xml"));
		HTable table = new HTable(config, TABLE_NAME);
		Scan s = new Scan();
		s.addColumn(CF, IP_ADDRESS);
		s.addColumn(CF, TIME_STAMP);
		s.addColumn(CF, REQUEST_TYPE);
		s.addColumn(CF, PRODUCT_URL);
		s.addColumn(CF, REQUEST_PROTOCOL);
		s.addColumn(CF, PROTOCOL_VERSION);
		s.addColumn(CF, RESPCODE);
		s.addColumn(CF, BYTES);
		s.addColumn(CF, URL);
		s.addColumn(CF, BROWSER);
		ResultScanner scanner = table.getScanner(s);
		int count = 0;
		List<String> rowKey = new ArrayList<String>();
		try {
			for (Result rr = scanner.next(); rr != null; rr = scanner.next()) {
				count++;
				byte[] key = rr.getRow();
				byte[] ip_address = rr.getValue(CF, IP_ADDRESS);
				byte[] time_stamp = rr.getValue(CF, TIME_STAMP);
				byte[] request_type = rr.getValue(CF, REQUEST_TYPE);
				byte[] product_url = rr.getValue(CF, PRODUCT_URL);
				byte[] request_protocol = rr.getValue(CF, REQUEST_PROTOCOL);
				byte[] protocol_version = rr.getValue(CF, PROTOCOL_VERSION);
				byte[] respcode = rr.getValue(CF, RESPCODE);
				byte[] bytes = rr.getValue(CF, BYTES);
				byte[] url = rr.getValue(CF, URL);
				byte[] browser = rr.getValue(CF, BROWSER);
				String row = new String(key);
				rowKey.add(row);
				String ipAddress = new String(ip_address);
				String timeStamp = new String(time_stamp);
				String requestType = new String(request_type);
				String productUrl = new String(product_url);
				String requestProtocol = new String(request_protocol);
				String protocolVersion = new String(protocol_version);
				String respcode_value = new String(respcode);
				String bytes_value = new String(bytes);
				String url_value = new String(url);
				String browser_value = null;
				browser_value = new String(browser);
				/*
				 * String result = count + "---" + row + "----" + ipAddress +
				 * "----" + timeStamp + "----" + requestType + "----" +
				 * productUrl + "----" + requestProtocol + "----" +
				 * protocolVersion + "----" + respcode_value + "----" +
				 * bytes_value + "----" + url_value + "----" + browser_value;
				 * System.out.println(result);
				 */

			}
		} finally {
			scanner.close();
		}
		/*int mozilla = 0;
		int chrome = 0;
		int ie = 0;
		int safari = 0;
		int gecko = 0;
		int appleWebKit = 0;
		for (String key : rowKey) {
			Get g = new Get(key.getBytes());
			Result r = table.get(g);
			byte[] value = r.getValue(CF, BROWSER);
			String valueStr = Bytes.toString(value);
			if (valueStr != null && valueStr != " ") {
				if (valueStr.equalsIgnoreCase("Mozilla")) {
					mozilla++;
				} else if (valueStr.equalsIgnoreCase("Chrome")) {
					chrome++;
				} else if (valueStr.equalsIgnoreCase("IE")) {
					ie++;
				}
			}
		}
		browsersInfo.setChrome(chrome);
		browsersInfo.setSafari(safari);
		browsersInfo.setIe(ie);
		browsersInfo.setFirefox(mozilla);
		browsersInfo.setIe(gecko);
		browsersInfo.setFirefox(appleWebKit);
		
		System.out.println("Browsers Count" + "----" + "Mozilla : "
				+ browsersInfo.getFirefox() + "----" + "Chrome : "
				+ browsersInfo.getChrome() + "----" + "IE : "
				+ browsersInfo.getIe() + "----" + "Safari : "
				+ browsersInfo.getSafari() + "----" + "Gecko : "
				+ browsersInfo.getGecko() + "----" + "AppleWebKit : "
				+ browsersInfo.getAppleWebKit());*/
		}
	public void splunkMonthlyHits(MonthlyHits monthlyHits) throws IOException {
		byte[] TABLE_NAME = Bytes.toBytes("hbaseAccessCombinedLogData");
		config.addResource(new Path("/conf/hbase-site.xml"));
		HTable table = new HTable(config, TABLE_NAME);
		Scan s = new Scan();
		s.addFamily(CF);
		//ResultScanner scanner = table.getScanner(s);
		LogAnalysisUtill loUtill = new LogAnalysisUtill();
		List<String> splunkHits = loUtill.yearlyHits(table.getScanner(s));
		List<String> splunkMonthlyHits = loUtill.monthlyHits(table.getScanner(s));
		List<String> splunkAvgHits = loUtill.avgHits(table.getScanner(s));
		monthlyHits.setSplunkYearly(splunkHits);
		monthlyHits.setSplunk(splunkMonthlyHits);
		monthlyHits.setSplunkAvg(splunkAvgHits);
		//System.out.println(splunkAvgHits);
		
	}
	public void wwMonthlyHits(MonthlyHits monthlyHits) throws IOException {
		byte[] TABLE_NAME = Bytes.toBytes("hbaseAccessLogData");
		config.addResource(new Path("/conf/hbase-site.xml"));
		HTable table = new HTable(config, TABLE_NAME);
		Scan s = new Scan();
		s.addFamily(CF);
		//ResultScanner scanner = table.getScanner(s);
		LogAnalysisUtill loUtill = new LogAnalysisUtill();
		List<String> wwwHits = loUtill.yearlyHits(table.getScanner(s));
		List<String> wwwMonthlyHits = loUtill.monthlyHits(table.getScanner(s));
		List<String> wwwAvgHits = loUtill.avgHits(table.getScanner(s));
		monthlyHits.setWwwYearly(wwwHits);
		monthlyHits.setWww(wwwMonthlyHits);
		monthlyHits.setWwwAvg(wwwAvgHits);
		//System.out.println(wwwAvgHits);
	}

	public void slectedServerMonthlyHits(SelectedSereverHits selectedServer) throws IOException {
		
		byte[] TABLE_NAME = Bytes.toBytes("hbaseAccessLogData");
		config.addResource(new Path("/conf/hbase-site.xml"));
		HTable table = new HTable(config, TABLE_NAME);
		Scan s = new Scan();
		s.addFamily(CF);
		//ResultScanner scanner = table.getScanner(s);
		LogAnalysisUtill loUtill = new LogAnalysisUtill();
		List<String> hits = loUtill.yearlyHits(table.getScanner(s));
		List<String> monthlyHits = loUtill.monthlyHits(table.getScanner(s));
		List<String> avgHits = loUtill.avgHits(table.getScanner(s));
		selectedServer.setServerYearly(hits);
		selectedServer.setServer(monthlyHits);
		selectedServer.setServerAvg(avgHits);
	}
}