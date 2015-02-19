package com.bdd.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {

	public static DateTime asDateTime( String date ) {
		DateTimeFormatter format = DateTimeFormat.forPattern( "MM/dd/YYYY HH:mm" );
		return format.parseDateTime( date );
	}

}
