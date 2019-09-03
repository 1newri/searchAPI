package kr.co.searchAPI.common.util;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

public class DateUtil {
	
	private static final String TIME_SERVER = "pool.ntp.org";
	
	public static String getTime() {
		
		NTPUDPClient timeClient = new NTPUDPClient();
		timeClient.setDefaultTimeout(1000);
		LocalDateTime localDateTime = null;
		
		try {
			timeClient.open();
			InetAddress address = InetAddress.getByName(TIME_SERVER);
			TimeInfo timeInfo = timeClient.getTime(address);
			// 서버로부터 시간가져오기
			long returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
			Date date = new Date(returnTime);
			
			localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return localDateTime.toString();
	}

}
