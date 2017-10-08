package com.example.feng.myapplication;


import android.test.AndroidTestCase;
import android.util.Log;

import com.tuling.util.HttpUtils;

import java.net.MalformedURLException;

public class TestHttpUtils extends AndroidTestCase
{
	public void testSendInfo()
	{
		String res = null;
		try {
			res = HttpUtils.doGet("给我讲个笑话");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Log.e("TAG", res);
		try {
			res = HttpUtils.doGet("你好");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Log.e("TAG", res);
		try {
			res = HttpUtils.doGet("你真美");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Log.e("TAG", res);
		try {
			res = HttpUtils.doGet("给我讲个鬼故事");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Log.e("TAG", res);
	}
}
