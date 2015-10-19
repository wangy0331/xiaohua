package com.test.xiaohualist.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


public class HttpService {
	/**
	 * @param urlAll
	 *            :请求接口
	 * @param httpArg
	 *            :参数
	 * @return 返回结果
	 */
	public static String get(String url,int page) {
		String result = null;
		String httpArg = String.format("page=%s", page);
		url = url + "?" + httpArg;
		
		try {
			HttpClient httpClient = new DefaultHttpClient();
			//指定访问的服务器地址
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				//请求响应了成功了
				HttpEntity entity = httpResponse.getEntity();
				result = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (Exception e) {
			Log.e("catch", e.toString());
		}
		return result;
	}
	
	public static Bitmap returnBitMap(String url){  
        URL myFileUrl = null;    
        Bitmap bitmap = null;   
        try {    
            myFileUrl = new URL(url);    
        } catch (MalformedURLException e) {    
            e.printStackTrace();    
        }    
        try {    
            HttpURLConnection conn = (HttpURLConnection) myFileUrl    
              .openConnection();    
            conn.setDoInput(true);    
            conn.connect();    
            InputStream is = conn.getInputStream();    
            bitmap = BitmapFactory.decodeStream(is); 
            is.close();    
        } catch (IOException e) {    
              e.printStackTrace();    
        }    
              return bitmap;    
    }    
}
