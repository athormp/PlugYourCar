package com.plugyourcar.backend.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class Utils {

	// Método estático responsable de crear una fecha SQL en formato yyyy-MM-dd HH:mm:ss
	public static Timestamp dateFormatterA(String string) throws ParseException {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			Date parsedDate = new Date();
			if (!string.equals(""))
				parsedDate = dateFormat.parse(string);
			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			return timestamp;
	}
	
	/*// Método estático responsable de generar la fecha y hora actual
	public static Timestamp dateNowCreator() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		Timestamp timestamp = new Timestamp(now.getTime());
		return timestamp;
	}*/

}
