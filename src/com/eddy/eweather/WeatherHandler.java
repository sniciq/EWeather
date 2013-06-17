package com.eddy.eweather;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONObject;

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
			String s = msg.getData().getString("weatherinfo");
			JSONObject jsonObj = new JSONObject(s);
			WeatherData weather = new WeatherData(jsonObj.getJSONObject("weatherinfo"));
			
			TextView tv = (TextView) activity.findViewById(R.id.text_date);
			tv.setText(weather.getDate_y() + "  " + weather.getWeek());
			
			tv = (TextView) activity.findViewById(R.id.text_city);
			tv.setText(weather.getCity());
			
			String weatherStr = weather.getWeather1() + "  " + weather.getTemp1();
			tv = (TextView) activity.findViewById(R.id.text_weather);
			tv.setText(weatherStr);
			
			tv = (TextView) activity.findViewById(R.id.text_fl);
			tv.setText("风力: " + weather.getFl1());
			
			
			tv = (TextView) activity.findViewById(R.id.text_day2);
			Calendar cal = Calendar.getInstance();
			
			SimpleDateFormat df = null;
			if(weather.getDate_y().contains("年")) {
				df = new SimpleDateFormat("yyyy年MM月dd日");
			}
			else {
				df = new SimpleDateFormat("yyyy-MM-dd");
			}
			cal.setTime(df.parse(weather.getDate_y()));
			cal.add(Calendar.DAY_OF_YEAR, 1);
			tv.setText(dayWeekName[cal.get(Calendar.DAY_OF_WEEK)  - 1]);
			tv = (TextView) activity.findViewById(R.id.text_day3);
			cal.add(Calendar.DAY_OF_YEAR, 1);
			tv.setText(dayWeekName[cal.get(Calendar.DAY_OF_WEEK)  - 1]);
			tv = (TextView) activity.findViewById(R.id.text_day4);
			cal.add(Calendar.DAY_OF_YEAR, 1);
			tv.setText(dayWeekName[cal.get(Calendar.DAY_OF_WEEK)  - 1]);
			tv = (TextView) activity.findViewById(R.id.text_day5);
			cal.add(Calendar.DAY_OF_YEAR, 1);
			tv.setText(dayWeekName[cal.get(Calendar.DAY_OF_WEEK)  - 1]);
			tv = (TextView) activity.findViewById(R.id.text_day6);
			cal.add(Calendar.DAY_OF_YEAR, 1);
			tv.setText(dayWeekName[cal.get(Calendar.DAY_OF_WEEK)  - 1]);
			
			tv = (TextView) activity.findViewById(R.id.text_day2_weather);
			tv.setText(weather.getWeather2());
			tv = (TextView) activity.findViewById(R.id.text_day3_weather);
			tv.setText(weather.getWeather3());
			tv = (TextView) activity.findViewById(R.id.text_day4_weather);
			tv.setText(weather.getWeather4());
			tv = (TextView) activity.findViewById(R.id.text_day5_weather);
			tv.setText(weather.getWeather5());
			tv = (TextView) activity.findViewById(R.id.text_day6_weather);
			tv.setText(weather.getWeather6());
			
			tv = (TextView) activity.findViewById(R.id.text_day2_fl);
			tv.setText(weather.getFl2());
			tv = (TextView) activity.findViewById(R.id.text_day3_fl);
			tv.setText(weather.getFl3());
			tv = (TextView) activity.findViewById(R.id.text_day4_fl);
			tv.setText(weather.getFl4());
			tv = (TextView) activity.findViewById(R.id.text_day5_fl);
			tv.setText(weather.getFl5());
			tv = (TextView) activity.findViewById(R.id.text_day6_fl);
			tv.setText(weather.getFl6());
			
			tv = (TextView) activity.findViewById(R.id.text_day2_temp);
			tv.setText(weather.getTemp2());
			tv = (TextView) activity.findViewById(R.id.text_day3_temp);
			tv.setText(weather.getTemp3());
			tv = (TextView) activity.findViewById(R.id.text_day4_temp);
			tv.setText(weather.getTemp4());
			tv = (TextView) activity.findViewById(R.id.text_day5_temp);
			tv.setText(weather.getTemp5());
			tv = (TextView) activity.findViewById(R.id.text_day6_temp);
			tv.setText(weather.getTemp6());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
