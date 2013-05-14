package com.threebowl.foodsystem.views;

import java.util.ArrayList;
import java.util.List;

import com.threebowl.foodsystem.MainActivity;
import com.threebowl.foodsystem.R;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
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
		
		mGridView_Common.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(Activity_Common.this,
						MainActivity.class);
				intent.putExtra("FoodName", getStrings().get(arg2));
				intent.putExtra("Tag", 1);
				intent.putExtra("url", getStrings_url().get(arg2));
				startActivity(intent);
				
			}
		});
		
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
					R.drawable.recai));
			Bitmaps.add(getBitmapFromResources(Activity_Common.this,
					R.drawable.liangcai));
			Bitmaps.add(getBitmapFromResources(Activity_Common.this,
					R.drawable.tangzhou));
			Bitmaps.add(getBitmapFromResources(Activity_Common.this,
					R.drawable.jcc));
			Bitmaps.add(getBitmapFromResources(Activity_Common.this,
					R.drawable.haixian));
			Bitmaps.add(getBitmapFromResources(Activity_Common.this,
					R.drawable.gaodian));
			Bitmaps.add(getBitmapFromResources(Activity_Common.this,
					R.drawable.tianpin));
			Bitmaps.add(getBitmapFromResources(Activity_Common.this,
					R.drawable.xicang));
			return Bitmaps;
		}
		
		
		// ���˴��ϵ��URL������List������
		private List<String> getStrings_url() {
			List<String> urlStrings = new ArrayList<String>();
		
				urlStrings.add(Activity_Common.this.getString(R.string.url_recai));
				urlStrings.add(Activity_Common.this.getString(R.string.url_liangcai));
				urlStrings.add(Activity_Common.this.getString(R.string.url_tangzhou));
				urlStrings.add(Activity_Common.this.getString(R.string.url_jiachang));
				urlStrings.add(Activity_Common.this.getString(R.string.url_haixian));
				urlStrings.add(Activity_Common.this.getString(R.string.url_gaodian));
				urlStrings.add(Activity_Common.this.getString(R.string.url_tianpin));
				urlStrings.add(Activity_Common.this.getString(R.string.url_xican));
			
			return urlStrings;
		}
		
		

}
 