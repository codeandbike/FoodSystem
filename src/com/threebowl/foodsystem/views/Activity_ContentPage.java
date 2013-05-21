package com.threebowl.foodsystem.views;

import java.util.ArrayList;
import java.util.List;

import com.threebowl.foodsystem.MainActivity;
import com.threebowl.foodsystem.R;
import com.threebowl.foodsystem.base.HttpDate;

import android.app.Activity;
import android.app.ProgressDialog;
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
 * @time	下午9:15:09
 * @author	 许度庆 
 *
 * 类说明:
 */
public class Activity_ContentPage extends Activity {

	
	private ListView mListView_Span;
	private TextView mTextView_Title;
	private ImageButton mImageButton_Back;
	private String url_a;
	private HttpDate date;
	private MyHandler myHandler;
	private ProgressDialog pDialog;
	private String  mString_html;
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
		myHandler = new MyHandler();
		url_a = new String();
		url_a = "http://home.meishichina.com/" + intent.getStringExtra("FoodURL");// 将bundle中提取String即a标签的数据
		date = new HttpDate();
		
		MyThread thread = new MyThread();
		new Thread(thread).start();
		
		pDialog = getMypDialog();
		pDialog.show();
		
	}
	
	/**
	 * 等待图标对话框
	 */
	public ProgressDialog getMypDialog() {
		ProgressDialog mpDialog = new ProgressDialog(Activity_ContentPage.this);
		mpDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//		mpDialog.setTitle("请稍等");// 设置标题
//		mpDialog.setIcon(R.drawable.icon_logo);// 设置图标
		mpDialog.setMessage("正在加载您要的数据...");
		mpDialog.setIndeterminate(false);// 设置进度条是否为不明确
		mpDialog.setCancelable(false);// 设置进度条是否可以按退回键取消
		return mpDialog;
	}
	
	class MyThread implements Runnable{
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = new Message();
			
			mString_html = date.GetHtml(url_a);//获取到完整的html页面数据
			
			Activity_ContentPage.this.myHandler.sendMessage(msg);
		}
	}
	
	
	class MyHandler extends Handler{
		
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			
			String tempCut = date.String_Cut(mString_html);//截取有用数据
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
			
			pDialog.dismiss();
			
		}
	}
	
	
}
 