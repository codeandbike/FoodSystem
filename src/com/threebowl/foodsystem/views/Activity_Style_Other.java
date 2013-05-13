package com.threebowl.foodsystem.views;

import com.threebowl.foodsystem.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @date	2013-5-13
 * @time	下午12:34:04
 * @author	 汪家栋
 *
 * 类说明:
 */
public class Activity_Style_Other extends Activity {
	
	private String phah_caixi;
	private TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_style_other);
		Intent intent = getIntent();
		phah_caixi = intent.getStringExtra("caixi");
		textView=(TextView)this.findViewById(R.id.chuan_url);
		textView.setText(phah_caixi);
		
	}

}
 