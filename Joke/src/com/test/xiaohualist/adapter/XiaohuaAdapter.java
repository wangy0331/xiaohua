package com.test.xiaohualist.adapter;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.test.xiaohualist.activity.GifActivity;
import com.test.xiaohualist.activity.ImageActivity;
import com.test.xiaohualist.activity.R;
import com.test.xiaohualist.bean.Xiaohua;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class XiaohuaAdapter extends ArrayAdapter<Xiaohua> {

	private View view;

	private ImageView xiaohuaImage;

	private TextView xiaohuaView;

	public XiaohuaAdapter(Context context) {
		super(context, 0);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// 获取当前项的实笑话例
		final Xiaohua xiaohua = getItem(position);
		view = LayoutInflater.from(getContext()).inflate(R.layout.xiaohua_item,null);

		// 获取imageView和textView的实例
		xiaohuaImage = (ImageView) view.findViewById(R.id.image);
		xiaohuaView = (TextView) view.findViewById(R.id.content);
		xiaohuaView.setText(xiaohua.getContent());
		if (!"".equals(xiaohua.getImage())) {
			ImageLoader.getInstance().displayImage(xiaohua.getImage(),
					xiaohuaImage);
		} else {
			xiaohuaImage.setVisibility(View.GONE);
		}

		xiaohuaImage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ImageLoader.getInstance().getMemoryCache();
				Log.d("TAG_URL", String.valueOf(xiaohua.getImage()));
				String path = xiaohua.getImage();
				String type = path.substring(path.lastIndexOf(".")+1);
				Log.d("TYPE" , type);
				
				if ("gif".equals(type)) {
					Intent intent = new Intent(getContext(), GifActivity.class);
					intent.putExtra("image_url", xiaohua.getImage());
					getContext().startActivity(intent);
				} else {
					Intent intent = new Intent(getContext(), ImageActivity.class);
					intent.putExtra("image_url", xiaohua.getImage());
					getContext().startActivity(intent);
				}
			}
		});

		xiaohuaView.setTextSize(20);

		return view;
	}
	
	
	
}
