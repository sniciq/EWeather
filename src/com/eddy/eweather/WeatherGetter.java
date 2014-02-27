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
			String[] arr = new String[3];
			arr[0] = new ClientRequest().request("http://php.weather.sina.com.cn/xml.php?city="+cityId+"&password=DJOYnieT8234jlsK&day=0", true);
			arr[1] = new ClientRequest().request("http://php.weather.sina.com.cn/xml.php?city="+cityId+"&password=DJOYnieT8234jlsK&day=1", true);
			arr[2] = new ClientRequest().request("http://php.weather.sina.com.cn/xml.php?city="+cityId+"&password=DJOYnieT8234jlsK&day=2", true);
			mainActivity.getWeatherOver(arr);
		} catch (Exception e) {
			e.printStackTrace();
		};
	}
}
