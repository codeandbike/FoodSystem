package com.threebowl.foodsystem.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.threebowl.foodsystem.R;
import com.threebowl.foodsystem.R.string;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.renderscript.Mesh.Primitive;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

/**
 * ====================================================
 * 
 * @Copyright (C) 2012-2013 杭州领图信息科技有限公司
 * @All rights reserved
 * @filename :Activity_Style.java
 * @date 2013-5-10
 * @time 下午2:10:41
 * @author 许度庆
 * @description： 菜系页面
 * 
 * @---------------代码维护与版本信息---------------------------
 * @版本：V1.0 编写人：许度庆 描述：第一次创建
 * 
 * 
 * @=====================================================
 */
public class Activity_Style extends Activity implements OnItemClickListener {

	private GridView mGridView_Style;
	private List<String> data_url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_style);
		mGridView_Style = (GridView) findViewById(R.id.FirstPage_Grid_Style);

		GridViewAdapter adapter = new GridViewAdapter(Activity_Style.this,
				getStrings(), getBitmaps());
		mGridView_Style.setAdapter(adapter);

		data_url = getStrings_url();

		mGridView_Style.setOnItemClickListener(this);

	}

	// 将八大菜系的URL储存在List集合中
	private List<String> getStrings_url() {
		List<String> urlStrings = new ArrayList<String>();
	
			urlStrings.add(Activity_Style.this.getString(R.string.url_chuan));
			urlStrings.add(Activity_Style.this.getString(R.string.url_lu));
			urlStrings.add(Activity_Style.this.getString(R.string.url_min));
			urlStrings.add(Activity_Style.this.getString(R.string.url_yue));
			urlStrings.add(Activity_Style.this.getString(R.string.url_shu));
			urlStrings.add(Activity_Style.this.getString(R.string.url_zhe));
			urlStrings.add(Activity_Style.this.getString(R.string.url_xiang));
			urlStrings.add(Activity_Style.this.getString(R.string.url_hui));
		
		return urlStrings;
	}

	private List<String> getStrings() {
		List<String> strings = new ArrayList<String>();
		strings.add("川菜");
		strings.add("鲁菜");
		strings.add("闽菜");
		strings.add("粤菜");
		strings.add("苏菜");
		strings.add("浙菜");
		strings.add("湘菜");
		strings.add("徽菜");

		return strings;

	}

	// 从资源中获取Bitmap
	public static Bitmap getBitmapFromResources(Activity act, int resId) {
		Resources res = act.getResources();
		return BitmapFactory.decodeResource(res, resId);
	}

	private List<Bitmap> getBitmaps() {
		List<Bitmap> Bitmaps = new ArrayList<Bitmap>();
		Bitmaps.add(getBitmapFromResources(Activity_Style.this,
				R.drawable.chuan));
		Bitmaps.add(getBitmapFromResources(Activity_Style.this, R.drawable.lu));
		Bitmaps.add(getBitmapFromResources(Activity_Style.this, R.drawable.min));
		Bitmaps.add(getBitmapFromResources(Activity_Style.this, R.drawable.yue));
		Bitmaps.add(getBitmapFromResources(Activity_Style.this,
				R.drawable.jiang));
		Bitmaps.add(getBitmapFromResources(Activity_Style.this, R.drawable.zhe));
		Bitmaps.add(getBitmapFromResources(Activity_Style.this,
				R.drawable.xiang));
		Bitmaps.add(getBitmapFromResources(Activity_Style.this, R.drawable.hui));
		return Bitmaps;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long rowid) {
		Intent intent = new Intent(Activity_Style.this,
				Activity_Style_Other.class);
		intent.putExtra("caixi", data_url.get(position));
		startActivity(intent);

	}

}
