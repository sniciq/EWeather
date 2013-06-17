package com.eddy.eweather;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
	
	private static final String city_beijing = "101010100";//北京
	private static final String city_mdj = "101050301";//牡丹江
	private static final String city_dl = "101290201";//大理
	private String nowCityId = "";
	
	private static WeatherHandler weatherHandler = null;
	private ProgressDialog loadingDialog = null;  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		weatherHandler = new WeatherHandler(this);
		nowCityId = city_beijing;
		
		loadingDialog = ProgressDialog.show(this, "更新", "请稍等...");
		new WeatherGetter(this).getWeather(nowCityId);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_beijing:
			nowCityId = city_beijing;
			break;
		case R.id.action_mdj:
			nowCityId = city_mdj;
			break;
		case R.id.action_dl:
			nowCityId = city_dl;
			break;
		default:
			break;
		}
		loadingDialog = ProgressDialog.show(this, "更新", "请稍等...");
		new WeatherGetter(this).getWeather(nowCityId);
		return super.onOptionsItemSelected(item);
	}
	
	public void getWeatherOver(String s) {
		Message msg = new Message();
		Bundle data = new Bundle();
		data.putString("weatherinfo", s);
		msg.setData(data);
		weatherHandler.sendMessage(msg);
		loadingDialog.dismiss();
	}
	
}
