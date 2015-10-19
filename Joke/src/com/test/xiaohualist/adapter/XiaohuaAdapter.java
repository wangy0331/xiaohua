package com.test.xiaohualist.adapter;


import uk.co.senab.photoview.PhotoViewAttacher;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.test.xiaohualist.activity.ImageActivity;
import com.test.xiaohualist.activity.LoadingActivity;
import com.test.xiaohualist.activity.MainActivity;
import com.test.xiaohualist.activity.R;
import com.test.xiaohualist.activity.StartActivity;
import com.test.xiaohualist.bean.Xiaohua;
import com.test.xiaohualist.service.HttpService;
import com.test.xiaohualist.util.JsonParser;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class XiaohuaAdapter extends ArrayAdapter<Xiaohua>{
	
	private View view2;
	
	private ImageView largerImage;
	
	private View view;
	
	private ImageView xiaohuaImage;
	
	private TextView xiaohuaView;
	
	private Xiaohua xiaohua;
	
	private String imageUrl;
	
	public XiaohuaAdapter(Context context) {
		super(context, 0);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		//获取当前项的实笑话例
		final Xiaohua xiaohua = getItem(position);
		view = LayoutInflater.from(getContext()).inflate(R.layout.xiaohua_item, null);
		// 
		view2 = LayoutInflater.from(getContext()).inflate(R.layout.loading, null);
		
		largerImage = (ImageView) view2.findViewById(R.id.largeImage);
		
		//获取imageView和textView的实例
		xiaohuaImage = (ImageView) view.findViewById(R.id.image);
		xiaohuaView = (TextView) view.findViewById(R.id.content);
		xiaohuaView.setText(xiaohua.getContent());
//		if (xiaohua.getImage().length() != 0) {
		if(!"".equals(xiaohua.getImage())) {
			ImageLoader.getInstance().displayImage(xiaohua.getImage(), xiaohuaImage);
		} else {
			xiaohuaImage.setVisibility(View.GONE);
		}
		
		//final String imgAddr = xiaohua.getImage();
		xiaohuaImage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent();
//				intent.setClass(getContext(), LoadingActivity.class); 
//				getContext().startActivity(intent);
//				imageResult();
//				view2.findViewById(R.id.image);
				ImageLoader.getInstance().getMemoryCache();
//				String imgaeAddress = xiaohua.getImage();
				Log.d("TAG_URL", String.valueOf(xiaohua.getImage()));
				Intent intent = new Intent(getContext(), ImageActivity.class);
				intent.putExtra("image_url", xiaohua.getImage());
				getContext().startActivity(intent);
//				Log.d("TAG_IMAGE", );
			}
		});
		
		
		xiaohuaView.setTextSize(20);
		
		
		return view;
	}
	
	
//	Handler myHandler = new Handler() {
//
//		@Override
//		public void handleMessage(Message msg) {
//			super.handleMessage(msg);
//			switch (msg.what) {
//			case 1:
//				AlertDialog.Builder builder = new Builder(getContext());
//				builder.setView(view2);
//				builder.create().show();
//				break;
//
//			default:
//				break;
//			}
//		}
//	};
}
