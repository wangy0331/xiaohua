package com.test.xiaohualist.activity;

import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

import com.test.xiaohualist.adapter.XiaohuaAdapter;
import com.test.xiaohualist.bean.Xiaohua;
import com.test.xiaohualist.service.HttpService;
import com.test.xiaohualist.util.JsonParser;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private String url = "http://api.1-blog.com/biz/bizserver/xiaohua/list.do";
	
	private List<Xiaohua> xiaohualist;
	
	private Button button;
	
	private ListView listView;
	
	private String[] xiaohuaArray;
	
	private int page = 0;
	
	private XiaohuaAdapter adapter;
	
	private SwipeRefreshLayout mSwipeRefreshLayout;
	
	private PhotoViewAttacher mAttacher;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sendRequestWithHttpClient();
		
		listView = (ListView) findViewById(R.id.list);
		
		View viewBtn = LayoutInflater.from(this).inflate(R.layout.footer, null);
		
		mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.list_refresh);
		
		button = (Button) viewBtn.findViewById(R.id.footer_btn);
		
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				button.setText("加载中...");
				page = page + 1;
				sendRequestWithHttpClient();
			}
		});
		
		
		
		mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				mSwipeRefreshLayout.setRefreshing(true);
            	page = 0;
            	adapter.clear();
            	sendRequestWithHttpClient();
            	mSwipeRefreshLayout.setRefreshing(false);
            }
		});	
		
		listView.addFooterView(button);
		
		adapter = new XiaohuaAdapter(MainActivity.this);
		
	}
	
	private void sendRequestWithHttpClient() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					String response = HttpService.get(url,page);
					xiaohualist = JsonParser.parseJSONWithJSONObject(response); 
					xiaohuaArray = new String[xiaohualist.size()];
					for (int a = 0; a < xiaohualist.size(); a++) {
						xiaohuaArray[a] = xiaohualist.get(a).getContent();
					}
					myHandler.sendEmptyMessage(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}).start();
	}
	
	
	
	Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				if(listView.getAdapter() == null) {
					listView.setAdapter(adapter);
				}
				adapter.addAll(xiaohualist);
				adapter.notifyDataSetChanged(); 
				button.setText("加载更多");
				break;

			default:
				break;
			}
		}
	};
	
}
