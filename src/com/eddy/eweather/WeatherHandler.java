package com.eddy.eweather;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class WeatherHandler extends Handler {
	private Activity activity;
	
	private static String[] dayWeekName = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
	
	public WeatherHandler(Activity activity) {
		this.activity = activity;
	}
	
	public void handleMessage(Message msg) { 
		try {
			String[] s = msg.getData().getStringArray("weatherinfo");
			WeatherData weather = new WeatherData(s[0]);
			WeatherData weather2 = new WeatherData(s[1]);
			WeatherData weather3 = new WeatherData(s[2]);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(weather.getSavedate_weather()));
			String weekDay = dayWeekName[cal.get(Calendar.DAY_OF_WEEK)  - 1];
			
			TextView tv = (TextView) activity.findViewById(R.id.text_date);
			tv.setText(weather.getSavedate_weather() + " " + weekDay);
			
			tv = (TextView) activity.findViewById(R.id.text_city);
			tv.setText(weather.getCity());
			
			String weatherStr = weather.getStatus1() + "  " + weather.getTemperature1() + "℃," + weather.getTemperature2() + "℃";
			tv = (TextView) activity.findViewById(R.id.text_weather);
			tv.setText(weatherStr);

			tv = (TextView) activity.findViewById(R.id.text_fl);
			tv.setText(weather.getDirection1() + " " + weather.getPower1() + "级, " + "污染:" + weather.getPollution_l());
			
			cal.setTime(new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(weather2.getSavedate_weather()));
			weekDay = dayWeekName[cal.get(Calendar.DAY_OF_WEEK)  - 1];
			tv = (TextView) activity.findViewById(R.id.text_day2);
			tv.setText(weather2.getSavedate_weather() + " " + weekDay);
			
			cal.setTime(new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(weather3.getSavedate_weather()));
			weekDay = dayWeekName[cal.get(Calendar.DAY_OF_WEEK)  - 1];
			tv = (TextView) activity.findViewById(R.id.text_day3);
			tv.setText(weather3.getSavedate_weather() + " " + weekDay);
			
			weatherStr = weather2.getStatus1() + "  " + weather2.getTemperature1() + "℃," + weather2.getTemperature2() + "℃";
			tv = (TextView) activity.findViewById(R.id.text_day2_weather);
			tv.setText(weatherStr);
			
			weatherStr = weather3.getStatus1() + "  " + weather3.getTemperature1() + "℃," + weather3.getTemperature2() + "℃";
			tv = (TextView) activity.findViewById(R.id.text_day3_weather);
			tv.setText(weatherStr);
			
			tv = (TextView) activity.findViewById(R.id.text_day2_fl);
			tv.setText(weather2.getDirection1() + " " + weather2.getPower1() + "级, " + "污染:" + weather2.getPollution_l());
			
			tv = (TextView) activity.findViewById(R.id.text_day3_fl);
			tv.setText(weather3.getDirection1() + " " + weather3.getPower1() + "级, " + "污染:" + weather3.getPollution_l());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
