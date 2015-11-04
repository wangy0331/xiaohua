package com.test.xiaohualist.activity;

import java.io.IOException;
import java.io.InputStream;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class GifActivity extends Activity {
	
	private WebView mWebView;
	
	private Button mButton;
	
	private  String text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.gif_image);
		Intent intent = getIntent();
		String url = intent.getStringExtra("image_url");
		
		mWebView = (WebView) findViewById(R.id.gif_image);
		
		mButton = (Button) findViewById(R.id.fanhui);
		
		mButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		InputStream is;
		try {
			is = getAssets().open("gif.html");
			  int size = is.available();  
				
			  byte[] buffer = new byte[size];  
			  is.read(buffer);  
			  is.close();  
			
			  text = new String(buffer, "UTF-8");  
			  text = text.replace("IMAGE_URL", url);
			  
			  Log.d("TAG_STARING", text);
			  
		} catch (IOException e) {
			e.printStackTrace();
		}  
		
		mWebView.loadDataWithBaseURL(url, text, "text/html", "utf-8", null);
		
	}
}
