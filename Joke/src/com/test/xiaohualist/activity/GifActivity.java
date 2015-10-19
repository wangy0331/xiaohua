package com.test.xiaohualist.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class GifActivity extends Activity {
	
	private WebView webView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		webView = new WebView(this);
		Intent intent = getIntent();
		String url = intent.getStringExtra("image_url");
		webView.loadUrl(url);
		
		String data = "<HTML><style>body{padding-top:100px;text-align:center;}</style><body><img src=\""+url+"\"/></body></HTML>"; 
		
		webView.loadDataWithBaseURL(url, data, "text/html", "utf-8", null);
		
		setContentView(webView);
	}
}
