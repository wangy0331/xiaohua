package com.test.xiaohualist.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FooterBtn extends Activity{
	
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.footer);
		button = (Button) findViewById(R.id.footer_btn);
	}

}
