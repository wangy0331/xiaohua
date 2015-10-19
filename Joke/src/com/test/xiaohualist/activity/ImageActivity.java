package com.test.xiaohualist.activity;

import uk.co.senab.photoview.PhotoViewAttacher;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class ImageActivity extends Activity {
	
	private ImageView imageView;
	
	private PhotoViewAttacher attacher;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.xiaohua_image);
		
		imageView = (ImageView) findViewById(R.id.xiaohua_image);
		
		Intent intent = getIntent();
		String url = intent.getStringExtra("image_url");
		
		ImageLoader.getInstance().displayImage(url, imageView);
		
		
		ImageLoader.getInstance().loadImage(url, new ImageLoadingListener() {
			@Override
			public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
				attacher = new PhotoViewAttacher(imageView); 
				attacher.update();
				attacher.setOnPhotoTapListener(onClick);  
			}

			@Override
			public void onLoadingCancelled(String arg0, View arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onLoadingStarted(String arg0, View arg1) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}
	
	PhotoViewAttacher.OnPhotoTapListener onClick = new PhotoViewAttacher.OnPhotoTapListener()  {
		 @Override  
	       public void onPhotoTap(View view, float x, float y) {  
	           finish();  
	       }  
	};
}
