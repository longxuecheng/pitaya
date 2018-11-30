package org.lxc.mall.common.utils.time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatter {

	public static final String PATTERN_TO_SECONS = "yyyy-MM-dd HH:mm:ss";
	
	private static final String Empty = "";
	
	public static String formatTime(Date time , String format) {
		if (time == null) {
			return Empty;
		}
		if (format != null ) {
			return new SimpleDateFormat(format).format(time);
		}
		return formatDefault(time);
		
	}
	
	public static String formatDefault(Date time) {
		if (time == null) {
			return Empty;
		}
		return new SimpleDateFormat(PATTERN_TO_SECONS).format(time);
	}
}
