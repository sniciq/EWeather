package com.eddy.eweather;

import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class WeatherData {

	private static final String ns = null;
	
//	@SuppressWarnings("rawtypes")
//	public WeatherData(JSONObject jsonObj) {
//		try {
//			Iterator itor = jsonObj.keys();
//			while(itor.hasNext()) {
//				String key = (String) itor.next();
//				String value = jsonObj.getString(key);
//				String fc = key.charAt(0) + "";
//				key = key.replaceFirst(fc, fc.toUpperCase());
//				String methodName = "set" + key;
//				
//				try {
//					Class<?>[] paramClass = new Class<?>[1];
//					paramClass[0] = String.class;
//					Method m = this.getClass().getDeclaredMethod(methodName, paramClass);
//					m.invoke(this, value);
//				} catch (NoSuchMethodException e) {
//					Log.i("NoSuchMethod", e.getMessage());
//				}
//				
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public WeatherData(String xmlStr) {
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(new StringReader(xmlStr));
			parser.nextTag();
			parser.require(XmlPullParser.START_TAG, ns, "Profiles");
			parser.nextTag();
			parser.require(XmlPullParser.START_TAG, ns, "Weather");
			
		    while (parser.next() != XmlPullParser.END_TAG) {
		    	if (parser.getEventType() != XmlPullParser.START_TAG) {
		            continue;
		        }
		    	String name = parser.getName();
		    	String text = "";
		    	if (parser.next() == XmlPullParser.TEXT) {
		    		text = parser.getText();
		            parser.nextTag();
		        }
		    	if (name.equals("city")) {
		    		this.city = text;
		    	}
		    	else if (name.equals("status1")) {
		    		this.status1 = text;
		    	}
		    	else if (name.equals("direction1")) {
		    		this.direction1 = text;
		    	}
		    	else if (name.equals("power1")) {
		    		this.power1 = text;
		    	}
		    	else if (name.equals("temperature1")) {
		    		this.temperature1 = text;
		    	}
		    	else if (name.equals("temperature2")) {
		    		this.temperature2 = text;
		    	}
		    	else if (name.equals("pollution_l")) {
		    		this.pollution_l = text;
		    	}
		    	else if (name.equals("savedate_weather")) {
		    		this.savedate_weather = text;
		    	}
		    }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String city;//"北京",
	private String status1;
	private String direction1;
	private String power1;
	private String temperature1;
	private String temperature2;
	private String pollution_l;
	private String savedate_weather;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStatus1() {
		return status1;
	}
	public void setStatus1(String status1) {
		this.status1 = status1;
	}
	public String getDirection1() {
		return direction1;
	}
	public void setDirection1(String direction1) {
		this.direction1 = direction1;
	}
	public String getPower1() {
		return power1;
	}
	public void setPower1(String power1) {
		this.power1 = power1;
	}
	public String getTemperature1() {
		return temperature1;
	}
	public void setTemperature1(String temperature1) {
		this.temperature1 = temperature1;
	}
	public String getTemperature2() {
		return temperature2;
	}
	public void setTemperature2(String temperature2) {
		this.temperature2 = temperature2;
	}
	public String getPollution_l() {
		return pollution_l;
	}
	public void setPollution_l(String pollution_l) {
		this.pollution_l = pollution_l;
	}
	public String getSavedate_weather() {
		return savedate_weather;
	}
	public void setSavedate_weather(String savedate_weather) {
		this.savedate_weather = savedate_weather;
	}
	
}
