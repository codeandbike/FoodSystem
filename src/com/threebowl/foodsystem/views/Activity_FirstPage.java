package com.threebowl.foodsystem.views;

import java.util.ArrayList;
import java.util.List;

import com.threebowl.foodsystem.MainActivity;
import com.threebowl.foodsystem.R;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/**
 * @date 2013-3-20
 * @time 下午8:38:39
 * @author 许度庆
 * 
 *         类说明:
 */
public class Activity_FirstPage extends Activity implements OnItemClickListener {

	private EditText mEditText_input;
	private ImageButton mButton_sou;
	private GridView mGridView_push;
	private boolean isExit = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_firstpage);
		mEditText_input = (EditText) findViewById(R.id.FirstPage_edit_input);
		mButton_sou = (ImageButton) findViewById(R.id.FirstPage_button_sou);
		mGridView_push = (GridView) findViewById(R.id.FirstPage_Grid_push);

		GridViewAdapter adapter_push = new GridViewAdapter(
				Activity_FirstPage.this, getStrings(), getBitmaps());
		mGridView_push.setAdapter(adapter_push);
		mGridView_push.setOnItemClickListener(this);

		mButton_sou.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Activity_FirstPage.this, MainActivity.class);
				intent.putExtra("FoodName", mEditText_input.getText()
						.toString());
				intent.putExtra("Tag", 0);
				startActivity(intent);
			}
		});
	}

	public List<String> getStrings() {
		List<String> strings = new ArrayList<String>();
		strings.add("水煮鱼");
		strings.add("红烧肉");
		strings.add("可乐鸡翅");
		strings.add("酱牛肉");
		strings.add("地三鲜");
		strings.add("豆腐");
		strings.add("白萝卜");
		strings.add("鲫鱼汤");

		return strings;

	}

	// 从资源中获取Bitmap
	public static Bitmap getBitmapFromResources(Activity act, int resId) {
		Resources res = act.getResources();
		return BitmapFactory.decodeResource(res, resId);
	}

	private List<Bitmap> getBitmaps() {
		List<Bitmap> Bitmaps = new ArrayList<Bitmap>();
		Bitmaps.add(getBitmapFromResources(Activity_FirstPage.this,
				R.drawable.shuizhuyu));
		Bitmaps.add(getBitmapFromResources(Activity_FirstPage.this,
				R.drawable.hongshaorou));
		Bitmaps.add(getBitmapFromResources(Activity_FirstPage.this,
				R.drawable.kelejichi));
		Bitmaps.add(getBitmapFromResources(Activity_FirstPage.this,
				R.drawable.jiangniurou));
		Bitmaps.add(getBitmapFromResources(Activity_FirstPage.this,
				R.drawable.disanxian));
		Bitmaps.add(getBitmapFromResources(Activity_FirstPage.this,
				R.drawable.doufu));
		Bitmaps.add(getBitmapFromResources(Activity_FirstPage.this,
				R.drawable.bailuobo));
		Bitmaps.add(getBitmapFromResources(Activity_FirstPage.this,
				R.drawable.jiyutang));
		return Bitmaps;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long rowid) {
		Intent intent = new Intent();
		intent.setClass(Activity_FirstPage.this, MainActivity.class);
		intent.putExtra("FoodName", getStrings().get(position));
		intent.putExtra("Tag", 0);
		startActivity(intent);

	}

	// @Override
	// public boolean onKeyDown(int keyCode, KeyEvent event) {
	// // TODO Auto-generated method stub
	//
	// if (keyCode == KeyEvent.KEYCODE_BACK) {
	// ToQuitApp();
	// return false;
	// } else {
	// return super.onKeyDown(keyCode, event);
	// }
	// }
	//
	// private void ToQuitApp(){
	// if (isExit) {
	// Intent intent = new Intent(Intent.ACTION_MAIN);
	// intent.addCategory(intent.CATEGORY_HOME);
	// startActivity(intent);
	// System.exit(0);
	// } else {
	// isExit = true;
	// Toast.makeText(Activity_FirstPage.this, "再按一次返回键退出APP",
	// Toast.LENGTH_SHORT).show();
	// mHandler.sendEmptyMessageDelayed(0, 3000);
	//
	// }
	// }
	//
	// Handler mHandler = new Handler(){
	// public void handleMessage(Message msg) {
	// super.handleMessage(msg);
	// isExit = false;
	// };
	// };

}
