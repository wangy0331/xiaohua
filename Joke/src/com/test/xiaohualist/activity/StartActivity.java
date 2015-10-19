package com.test.xiaohualist.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class StartActivity extends Activity{
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);   
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.start);  
      
    new Handler().postDelayed(new Runnable(){  
        public void run(){    
        	Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            StartActivity.this.finish(); 
        }  
    }, 3000);  
   }  
}
