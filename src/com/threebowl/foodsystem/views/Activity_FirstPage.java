package com.threebowl.foodsystem.views;

import com.threebowl.foodsystem.MainActivity;
import com.threebowl.foodsystem.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;

/**
 * @date	2013-3-20
 * @time	下午8:38:39
 * @author	 许度庆 
 *
 * 类说明:
 */
public class Activity_FirstPage extends Activity {

	private EditText mEditText_input;
	private ImageButton mButton_sou;
	private GridView mGridView_push;
	private GridView mGridView_Menu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_firstpage);
		mEditText_input = (EditText)findViewById(R.id.FirstPage_edit_input);
		mButton_sou = (ImageButton)findViewById(R.id.FirstPage_button_sou);
		mGridView_push = (GridView)findViewById(R.id.FirstPage_Grid_push);
		mGridView_Menu = (GridView)findViewById(R.id.FirstPage_Grid_Menu);
		
		
		GridViewAdapter adapter_push = new GridViewAdapter(Activity_FirstPage.this);
		GridViewAdapter_Menu adapter_Menu = new GridViewAdapter_Menu(Activity_FirstPage.this);
		mGridView_push.setAdapter(adapter_push);
		mGridView_Menu.setAdapter(adapter_Menu);
		
		mButton_sou.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Activity_FirstPage.this, MainActivity.class);
				intent.putExtra("FoodName", mEditText_input.getText().toString());
				startActivity(intent);
			}
		});
	}

}
 