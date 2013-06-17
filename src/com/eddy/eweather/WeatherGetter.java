package com.eddy.eweather;


public class WeatherGetter extends Thread {
	
	private MainActivity mainActivity;
	private String cityId = "";
	
	public WeatherGetter(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}
	
	public void getWeather(String city_id) {
		this.cityId = city_id;
		this.start();
	}
	
	@Override
	public void run() {
		try {
			String s = new ClientRequest().request("http://m.weather.com.cn/data/" + cityId + ".html", true);
			mainActivity.getWeatherOver(s);
		} catch (Exception e) {
			e.printStackTrace();
		};
		
	}

}
