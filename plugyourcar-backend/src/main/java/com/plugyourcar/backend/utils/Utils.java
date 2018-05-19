package com.plugyourcar.backend.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Utils {

	public static Timestamp dateFormatterA(String string) throws ParseException {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			Date parsedDate = dateFormat.parse(string);
			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			return timestamp;
	}

}
