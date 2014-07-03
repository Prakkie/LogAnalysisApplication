package com.visualpath.hadoop.loganalysis.utill;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.util.Bytes;

import com.visualpath.hadoop.loganalysis.dto.MonthlyHits;

public class LogAnalysisUtill {
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
	
	public List<String> yearlyHits(ResultScanner scanner) throws IOException {
		int year_2010 = 0;
		int year_2011 = 0;
		int year_2012 = 0;
		int year_2013 = 0;
		int year_2014 = 0;
		try {
			List<String> yearValues = new ArrayList<String>();

			for (Result rr = scanner.next(); rr != null; rr = scanner.next()) {
				NavigableMap<byte[], byte[]> colfam = rr.getFamilyMap(CF);
				byte[] timeStamp = colfam.get(TIME_STAMP);
				String time_Stamp = new String(timeStamp);
				String month = time_Stamp.split(":")[0].split("/")[1]
						.toString();
				String year = time_Stamp.split(":")[0].split("/")[2].toString();
				if (year.equals("2010")) {
					year_2010++;
				}
				if (year.equals("2011")) {
					year_2011++;
				}
				if (year.equals("2012")) {
					year_2012++;
				}
				if (year.equals("2013")) {
					year_2013++;
				}
				if (year.equals("2014")) {
					year_2014++;
				}
			}
			yearValues.add(String.valueOf(year_2010));
			yearValues.add(String.valueOf(year_2011));
			yearValues.add(String.valueOf(year_2012));
			yearValues.add(String.valueOf(year_2013));
			yearValues.add(String.valueOf(year_2014));
			return yearValues;
		} finally {
			scanner.close();
		}

	}
	public List<String> monthlyHits(ResultScanner scanner) throws IOException {
		int jan = 0;
		int feb = 0;
		int mar = 0;
		int aprl = 0;
		int may = 0;
		int jun = 0;
		int july = 0;
		int aug = 0;
		int sep = 0;
		int oct = 0;
		int nov = 0;
		int dec = 0;
		try {
			List<String> monthValues = new ArrayList<String>();
			List<String> avgValues = new ArrayList<String>();
			for (Result rr = scanner.next(); rr != null; rr = scanner.next()) {
				NavigableMap<byte[], byte[]> colfam = rr.getFamilyMap(CF);
				byte[] timeStamp = colfam.get(TIME_STAMP);
				String time_Stamp = new String(timeStamp);
				String month = time_Stamp.split(":")[0].split("/")[1]
						.toString();
				String year = time_Stamp.split(":")[0].split("/")[2].toString();
				if (month.equalsIgnoreCase("Jan")) {
					jan++;
				}
				if (month.equalsIgnoreCase("Feb")) {
					feb++;
				}
				if (month.equalsIgnoreCase("Mar")) {
					mar++;
				}
				if (month.equalsIgnoreCase("Apr")) {
					aprl++;
				}
				if (month.equalsIgnoreCase("May")) {
					may++;
				}
				if (month.equalsIgnoreCase("Jun")) {
					jun++;
				}
				if (month.equalsIgnoreCase("Jul")) {
					july++;
				}
				if (month.equalsIgnoreCase("Aug")) {
					aug++;
				}
				if (month.equalsIgnoreCase("Sep")) {
					sep++;
				}
				if (month.equalsIgnoreCase("Oct")) {
					oct++;
				}
				if (month.equalsIgnoreCase("Nov")) {
					nov++;
				}
				if (month.equalsIgnoreCase("Dec")) {
					dec++;
				}
				
			}
			int total = jan+feb+mar+aprl+may+jun+july+aug+sep+oct+nov+dec;
			monthValues.add(String.valueOf(jan));
			monthValues.add(String.valueOf(feb));
			monthValues.add(String.valueOf(mar));
			monthValues.add(String.valueOf(aprl));
			monthValues.add(String.valueOf(may));
			monthValues.add(String.valueOf(jun));
			monthValues.add(String.valueOf(july));
			monthValues.add(String.valueOf(aug));
			monthValues.add(String.valueOf(sep));
			monthValues.add(String.valueOf(oct));
			monthValues.add(String.valueOf(nov));
			monthValues.add(String.valueOf(dec));
			
			avgValues.add(String.valueOf(jan/total));
			avgValues.add(String.valueOf(feb/total));
			avgValues.add(String.valueOf(mar/total));
			avgValues.add(String.valueOf(aprl/total));
			avgValues.add(String.valueOf(may/total));
			avgValues.add(String.valueOf(jun/total));
			avgValues.add(String.valueOf(july/total));
			avgValues.add(String.valueOf(aug/total));
			avgValues.add(String.valueOf(sep/total));
			avgValues.add(String.valueOf(oct/total));
			avgValues.add(String.valueOf(nov/total));
			avgValues.add(String.valueOf(dec/total));
			
			return monthValues;
		} finally {
			scanner.close();
		}

	}
	public List<String> avgHits(ResultScanner scanner) throws IOException {
		int jan = 0;
		int feb = 0;
		int mar = 0;
		int aprl = 0;
		int may = 0;
		int jun = 0;
		int july = 0;
		int aug = 0;
		int sep = 0;
		int oct = 0;
		int nov = 0;
		int dec = 0;
		try {
			List<String> avgValues = new ArrayList<String>();
			for (Result rr = scanner.next(); rr != null; rr = scanner.next()) {
				NavigableMap<byte[], byte[]> colfam = rr.getFamilyMap(CF);
				byte[] timeStamp = colfam.get(TIME_STAMP);
				String time_Stamp = new String(timeStamp);
				String month = time_Stamp.split(":")[0].split("/")[1]
						.toString();
				String year = time_Stamp.split(":")[0].split("/")[2].toString();
				if (month.equalsIgnoreCase("Jan")) {
					jan++;
				}
				if (month.equalsIgnoreCase("Feb")) {
					feb++;
				}
				if (month.equalsIgnoreCase("Mar")) {
					mar++;
				}
				if (month.equalsIgnoreCase("Apr")) {
					aprl++;
				}
				if (month.equalsIgnoreCase("May")) {
					may++;
				}
				if (month.equalsIgnoreCase("Jun")) {
					jun++;
				}
				if (month.equalsIgnoreCase("Jul")) {
					july++;
				}
				if (month.equalsIgnoreCase("Aug")) {
					aug++;
				}
				if (month.equalsIgnoreCase("Sep")) {
					sep++;
				}
				if (month.equalsIgnoreCase("Oct")) {
					oct++;
				}
				if (month.equalsIgnoreCase("Nov")) {
					nov++;
				}
				if (month.equalsIgnoreCase("Dec")) {
					dec++;
				}
				
			}
			int total = jan+feb+mar+aprl+may+jun+july+aug+sep+oct+nov+dec;
			avgValues.add(String.valueOf((jan == 0)? jan:total/jan));
			avgValues.add(String.valueOf((feb == 0)? feb:total/feb));
			avgValues.add(String.valueOf((mar == 0)? mar:total/mar));
			avgValues.add(String.valueOf((aprl == 0)? aprl:total/aprl));
			avgValues.add(String.valueOf((may == 0)? may:total/may));
			avgValues.add(String.valueOf((jun == 0)? jun:total/jun));
			avgValues.add(String.valueOf((july == 0)? july:total/july));
			avgValues.add(String.valueOf((aug == 0)? aug:total/aug));
			avgValues.add(String.valueOf((sep == 0)? sep:total/sep));
			avgValues.add(String.valueOf((oct == 0)? oct:total/oct));
			avgValues.add(String.valueOf((nov == 0)? nov:total/nov));
			avgValues.add(String.valueOf((dec == 0)? dec:total/dec));
			return avgValues;
		} finally {
			scanner.close();
		}

	}
}
