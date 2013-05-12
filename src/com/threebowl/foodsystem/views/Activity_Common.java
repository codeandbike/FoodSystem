package com.threebowl.foodsystem.views;

import java.util.ArrayList;
import java.util.List;

import com.threebowl.foodsystem.R;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.GridView;

/**
 *====================================================
 *	@Copyright (C) 2012-2013 杭州领图信息科技有限公司
 *	@All rights reserved
 *	@filename :Activity_Style.java
 * 	@date	2013-5-10
 * 	@time	下午2:10:41
 * 	@author 许度庆 
 * 	@description： 家常页面
 *
 *@---------------代码维护与版本信息---------------------------
 * 			@版本：V1.0  编写人：许度庆  描述：第一次创建 
 *
 *
 *@=====================================================
 */
public class Activity_Common extends Activity {
	
	private GridView mGridView_Common;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_common);
		mGridView_Common = (GridView)findViewById(R.id.FirstPage_Grid_Common);
		
		GridViewAdapter adapter = new GridViewAdapter(Activity_Common.this,getStrings(),getBitmaps());
		mGridView_Common.setAdapter(adapter);
		
	}
	
	private List<String> getStrings(){
		List<String> strings = new ArrayList<String>();
		strings.add("热菜");
		strings.add("凉菜");
		strings.add("汤粥");
		strings.add("家常菜");
		strings.add("海鲜");
		strings.add("糕点主食");
		strings.add("甜品");
		strings.add("西餐");		
		return strings;
		
	}
	
	// 从资源中获取Bitmap
		public static Bitmap getBitmapFromResources(Activity act, int resId) {
			Resources res = act.getResources();
			return BitmapFactory.decodeResource(res, resId);
		}

		private List<Bitmap> getBitmaps() {
			List<Bitmap> Bitmaps = new ArrayList<Bitmap>();
			Bitmaps.add(getBitmapFromResources(Activity_Common.this,
					R.drawable.foodimag));
			Bitmaps.add(getBitmapFromResources(Activity_Common.this,
					R.drawable.foodimag));
			Bitmaps.add(getBitmapFromResources(Activity_Common.this,
					R.drawable.foodimag));
			Bitmaps.add(getBitmapFromResources(Activity_Common.this,
					R.drawable.foodimag));
			Bitmaps.add(getBitmapFromResources(Activity_Common.this,
					R.drawable.foodimag));
			Bitmaps.add(getBitmapFromResources(Activity_Common.this,
					R.drawable.foodimag));
			Bitmaps.add(getBitmapFromResources(Activity_Common.this,
					R.drawable.foodimag));
			Bitmaps.add(getBitmapFromResources(Activity_Common.this,
					R.drawable.foodimag));
			return Bitmaps;
		}

}
 