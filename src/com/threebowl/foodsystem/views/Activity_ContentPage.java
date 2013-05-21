package com.threebowl.foodsystem.views;

import java.util.ArrayList;
import java.util.List;

import com.threebowl.foodsystem.R;
import com.threebowl.foodsystem.base.HttpDate;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @date	2013-3-20
 * @time	����9:15:09
 * @author	 ����� 
 *
 * ��˵��:
 */
public class Activity_ContentPage extends Activity {

	
	private ListView mListView_Span;
	private TextView mTextView_Title;
	private ImageButton mImageButton_Back;
	private String url_a;
	private HttpDate date;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contentpage);
		mTextView_Title = (TextView)findViewById(R.id.Content_Text_Title);
		mImageButton_Back = (ImageButton)findViewById(R.id.Content_ImgBut_Back);
		mListView_Span = (ListView)findViewById(R.id.content_listview_span);
		Intent intent1  = getIntent();
		String h2 = intent1.getStringExtra("Foodh2");
		mTextView_Title.setText(h2);
		mImageButton_Back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		Intent intent = getIntent();
		url_a = new String();
		url_a = "http://home.meishichina.com/" + intent.getStringExtra("FoodURL");// ��bundle����ȡString��a��ǩ������
		date = new HttpDate();
		String tempHtml = date.GetHtml(url_a);//��ȡ��������htmlҳ������
		String tempCut = date.String_Cut(tempHtml);//��ȡ��������
		tempCut = tempCut.replaceAll("&nbsp;", "");
		List<String> tempUrls = new ArrayList<String>();
		List<String> tempSpans = date.GetSpan(tempCut);
		List<Bitmap> bitmaps = new ArrayList<Bitmap>();
		

		try {
			tempUrls = date.getData(date.String2InputStream(tempCut));
			for (int i = 0; i < tempUrls.size(); i++) {
				bitmaps.add(date.getBitmap(tempUrls.get(i)));
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		Adapter_Contnet adapter = new Adapter_Contnet(Activity_ContentPage.this, tempSpans,bitmaps);
		mListView_Span.setAdapter(adapter);

	}
	
	
}
 