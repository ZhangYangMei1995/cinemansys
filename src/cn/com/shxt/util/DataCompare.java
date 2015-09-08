package cn.com.shxt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataCompare {
	private SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat de = new SimpleDateFormat("yyyy-MM-dd");
	private String date_start;
	private String date_down;
	public long timeSub(String dateUp,String dateDown){
		Date d;
		try {
			d = ds.parse(dateUp);
			date_start = ds.format(d);
			
			Date d0 = ds.parse(dateDown);
			date_down = ds.format(d0);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long subTime = 0;
		String [] date1 =date_start.split(" ")[0].split("-");
		String [] time1 = date_start.split(" ")[1].split(":");
		int year1 = Integer.parseInt(date1[0]);
		int month1 = Integer.parseInt(date1[1]);
		int day1 = Integer.parseInt(date1[2]);
		int hour1 = Integer.parseInt(time1[0]);
		int minutes1 = Integer.parseInt(time1[1]);
		int seconds1 = Integer.parseInt(time1[2]);
		
		String [] date2 =date_down.split(" ")[0].split("-");
		String [] time2 = date_down.split(" ")[1].split(":");
		int year2 = Integer.parseInt(date2[0]);
		int month2 = Integer.parseInt(date2[1]);
		int day2 = Integer.parseInt(date2[2]);
		int hour2 = Integer.parseInt(time2[0]);
		int minutes2 = Integer.parseInt(time2[1]);
		int seconds2 = Integer.parseInt(time2[2]);
		
		@SuppressWarnings("deprecation")
		Date movieUp = new Date(year1,month1,day1,hour1,minutes1,seconds1);
		long movie_up = movieUp.getTime();
		@SuppressWarnings("deprecation")
		Date movieDown = new Date(year2,month2,day2,hour2,minutes2,seconds2);
		long movie_down = movieDown.getTime();
		subTime = movie_down - movie_up;
		return subTime;
	}
	
	public long Sub(String dateUp,String dateDown){
		Date d;
		try {
			d = de.parse(dateUp);
			date_start = de.format(d);
			
			Date d0 = de.parse(dateDown);
			date_down = de.format(d0);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long subTime = 0;
		String [] date1 =date_start.split(" ")[0].split("-");
		int year1 = Integer.parseInt(date1[0]);
		int month1 = Integer.parseInt(date1[1]);
		int day1 = Integer.parseInt(date1[2]);
		
		String [] date2 =date_down.split(" ")[0].split("-");
		int year2 = Integer.parseInt(date2[0]);
		int month2 = Integer.parseInt(date2[1]);
		int day2 = Integer.parseInt(date2[2]);
		
		@SuppressWarnings("deprecation")
		Date movieUp = new Date(year1,month1,day1);
		long movie_up = movieUp.getTime();
		
		@SuppressWarnings("deprecation")
		Date movieDown = new Date(year2,month2,day2);
		long movie_down = movieDown.getTime();
		
		subTime = movie_down - movie_up;
		return subTime;
	}
}
