package com.test.xiaohualist.activity;

import java.io.File;

import android.app.Application;
import android.graphics.Bitmap.CompressFormat;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class MyApplication extends Application {
	@Override  
    public void onCreate() {  
        super.onCreate();  
  
        //创建默认的ImageLoader配置参数  
        //ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);  
        
        File cacheDir = StorageUtils.getCacheDirectory(this);
        DisplayImageOptions options = new DisplayImageOptions.Builder()
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.build();
        
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
		.denyCacheImageMultipleSizesInMemory()
		.diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
		.diskCacheFileCount(100)//Set max cache file count in SD card
		.diskCache(new UnlimitedDiskCache(cacheDir))
		.tasksProcessingOrder(QueueProcessingType.LIFO)
		.defaultDisplayImageOptions(options)
		.build();


        //Initialize ImageLoader with configuration.  
        ImageLoader.getInstance().init(config);  
    }  
}
