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
 *	@Copyright (C) 2012-2013 ������ͼ��Ϣ�Ƽ����޹�˾
 *	@All rights reserved
 *	@filename :Activity_Style.java
 * 	@date	2013-5-10
 * 	@time	����2:10:41
 * 	@author ����� 
 * 	@description�� �ҳ�ҳ��
 *
 *@---------------����ά����汾��Ϣ---------------------------
 * 			@�汾��V1.0  ��д�ˣ������  ��������һ�δ��� 
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
		strings.add("�Ȳ�");
		strings.add("����");
		strings.add("����");
		strings.add("�ҳ���");
		strings.add("����");
		strings.add("�����ʳ");
		strings.add("��Ʒ");
		strings.add("����");		
		return strings;
		
	}
	
	// ����Դ�л�ȡBitmap
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
 