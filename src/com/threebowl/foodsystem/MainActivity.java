package com.threebowl.foodsystem;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.threebowl.foodsystem.base.HttpDate;

import com.threebowl.foodsystem.views.Activity_ContentPage;
import com.threebowl.foodsystem.views.MyAdapter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;

import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import android.widget.TextView;

import android.widget.AbsListView.OnScrollListener;

public class MainActivity extends ListActivity implements OnScrollListener {

	private ListView mListView_content;
	private FoodSystemApp app;
	private int scrollState;
	private Button footerButton;
	private LinearLayout footerlProgressBarLayout; // ����������
	private View view;
	private MyHandle myHandle; // �������handle
	private MyHandler_ONE myHandler_ONE; // ���µ�һ�μ�������
	private MyAdapter adapter;
	private List<Map<String, Object>> listData;
	private int pageID = 2; // ����ҳ��id
	private Bitmap mbitmap;
	private List<Bitmap> listImage = new ArrayList<Bitmap>();
	private List<String> imageUrl = new ArrayList<String>();
	private String mString_Food;
	private int mTag_Food; // ҳ���ʶ
	private String mURL_Page; //
	private String mURL_Food;
	private TextView mTextView_Title;
	private List<Map<String, String>> contentData;
	private ImageButton mImageButton_Back;
	private HttpDate httpDate;
	private ProgressDialog myPB_Dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListView_content = getListView();
		app = (FoodSystemApp) getApplicationContext();
		app.mBitmaps = listImage;
		mTextView_Title = (TextView) findViewById(R.id.Main_Text_Title);
		mImageButton_Back = (ImageButton) findViewById(R.id.Main_ImgBut_Back);
		myPB_Dialog = getMypDialog();
		myPB_Dialog.show();

		// ȡ������Activity����������
		Intent intent = getIntent();
		mString_Food = intent.getStringExtra("FoodName");
		mTag_Food = intent.getIntExtra("Tag", 0);
		mURL_Page = intent.getStringExtra("url");
		mTextView_Title.setText(mString_Food);
		try {
			mURL_Food = URLEncoder.encode(mString_Food, "UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
		}

		httpDate = new HttpDate();
		listData = new ArrayList<Map<String, Object>>();
		contentData = new ArrayList<Map<String, String>>();
		
		adapter = new MyAdapter(listData, MainActivity.this, app);

		myHandler_ONE = new MyHandler_ONE();
		DataThread dThread = new DataThread();
		new Thread(dThread).start();

		// ���س�ʼ��ͼƬ
		InitImage();

		myHandle = new MyHandle();

		loadThread lThread = new loadThread();
		new Thread(lThread).start();

		// ���ؽ���������
		LayoutInflater inflater = LayoutInflater.from(this);
		view = inflater.inflate(R.layout.other_listview_footer_more, null);
		footerButton = (Button) view.findViewById(R.id.button);
		footerlProgressBarLayout = (LinearLayout) view
				.findViewById(R.id.linearlayout);
		footerlProgressBarLayout.setVisibility(View.GONE);

		footerButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				footerButton.setVisibility(View.GONE);
				footerlProgressBarLayout.setVisibility(View.VISIBLE);
				// �첽����ʣ����Դ
				MyThread myThread = new MyThread();
				new Thread(myThread).start();

			}
		});

		mListView_content
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
//						myPB_Dialog.show();
						Intent intent = new Intent();
						intent.setClass(MainActivity.this,
								Activity_ContentPage.class);
						String urlString = contentData.get(arg2).get("a");
						String h2Str = contentData.get(arg2).get("h2");
						intent.putExtra("FoodURL", urlString);
						intent.putExtra("Foodh2", h2Str);
						startActivity(intent);
					}

				});

		mImageButton_Back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}

	/**
	 * ������һ���ύ������
	 */
	public void getMyData() {
		InputStream inputStream = null;
		try {
			String str;
			// mTag_Food������ʾ���ݴ��ĸ�Activity����
			if (mTag_Food == 0) {

				str = httpDate.PostHtml(
						"http://home.meishichina.com/wap.php?ac=search",
						mString_Food);
			} else {
				str = httpDate.GetHtml(mURL_Page);
			}
			String xmlData = String_Cut(str);
			xmlData = xmlData.replaceAll("&nbsp;", "");
			inputStream = httpDate.String2InputStream(xmlData);
			contentData = httpDate.getSouData(inputStream);

			String img_url;
			for (int i = 0; i < contentData.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				img_url = contentData.get(i).get("img");
				imageUrl.add(img_url);
				map.put("h2", contentData.get(i).get("h2"));
				map.put("span", contentData.get(i).get("span"));
				listImage.add(mbitmap);
				listData.add(map);

			}

		} catch (Exception e) {

		}
		// �첽����ͼƬ
		loadThread lThread = new loadThread();
		new Thread(lThread).start();

	}
	
	/**
	 * �ȴ�ͼ��Ի���
	 */
	public ProgressDialog getMypDialog() {
		ProgressDialog mpDialog = new ProgressDialog(MainActivity.this);
		mpDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mpDialog.setTitle("���Ե�");// ���ñ���
		mpDialog.setIcon(R.drawable.icon_logo);// ����ͼ��
		mpDialog.setMessage("���ڼ�����Ҫ������...");
		mpDialog.setIndeterminate(false);// ���ý������Ƿ�Ϊ����ȷ
		mpDialog.setCancelable(true);// ���ý������Ƿ���԰��˻ؼ�ȡ��
		return mpDialog;
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stu

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

		this.scrollState = scrollState;
	}

	/**
	 * �����봦����Ϣ
	 * 
	 * @date 2013-1-10
	 * @time ����8:46:01
	 * @author �����
	 * 
	 *         ��˵�������µ�һ�μ�������
	 */
	class MyHandler_ONE extends Handler {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			adapter.notifyDataSetChanged();
			myPB_Dialog.cancel();
			mListView_content.addFooterView(view);
			mListView_content.setAdapter(adapter);
			mListView_content.setOnScrollListener(MainActivity.this);
		}
	}

	/**
	 * �����봦����Ϣ
	 * 
	 * @date 2013-1-10
	 * @time ����8:46:01
	 * @author �����
	 * 
	 *         ��˵����
	 */
	class MyHandle extends Handler {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			// ����UI
			adapter.notifyDataSetChanged();
			footerButton.setVisibility(View.VISIBLE);
			footerlProgressBarLayout.setVisibility(View.GONE);

		}
	}

	class DataThread implements Runnable {

		@Override
		public void run() {
			Message msg = new Message();

			getMyData();

			MainActivity.this.myHandler_ONE.sendMessage(msg);

		}

	}

	/**
	 * ����׷���߳�
	 * 
	 * @date 2013-3-4
	 * @time ����9:43:20
	 * @author �����
	 * 
	 *         ��˵����
	 */
	class MyThread implements Runnable {

		@Override
		public void run() {

			Message msg = new Message();
			if (mTag_Food == 0) {
				getPictureData("http://home.meishichina.com/wap.php?ac=search&q="
						+ mURL_Food + "&t=3&fr=&page=" + pageID + "");
			} else {
				getPictureData(mURL_Page+"&page=" + pageID);

			}
			
			
			pageID++;
			MainActivity.this.myHandle.sendMessage(msg);

		}
	}

	/**
	 * ͼƬ�����߳�
	 * 
	 * @date 2013-3-4
	 * @time ����9:43:53
	 * @author �����
	 * 
	 *         ��˵����
	 */
	class loadThread implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (int i = 0; i < 10; i++) {
				String img_url;
				try {
					int temp = (pageID - 2) * 10 + i;
					img_url = imageUrl.get(temp);
					listImage.set(temp, httpDate.getBitmap(img_url));
				} catch (Exception e) {
					// TODO: handle exception
				}
				Message msg = new Message();
				MainActivity.this.myHandle.sendMessage(msg);
			}
		}
	}

	/**
	 * ����׷��
	 * 
	 * @param path
	 * @return
	 */
	public String getPictureData(String path) {
		String html = null;

		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inputStream = conn.getInputStream();
			byte[] data = httpDate.readInputStream(inputStream);
			html = new String(data);

			String xmlData = String_Cut(html);
			xmlData = xmlData.replaceAll("&nbsp;", "");
			inputStream = httpDate.String2InputStream(xmlData);

			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			list = httpDate.getSouData(inputStream);

			for (int i = 0; i < list.size(); i++) {
				contentData.add(list.get(i));
			}

			String img_url;
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				img_url = list.get(i).get("img");
				imageUrl.add(img_url);
				map.put("h2", list.get(i).get("h2"));
				map.put("span", list.get(i).get("span"));
				listImage.add(mbitmap);
				listData.add(map);

			}

			loadThread lThread = new loadThread();
			new Thread(lThread).start();

		} catch (Exception e) {
			// TODO: handle exception

		}

		return html;

	}

	/**
	 * ��ʼ��ͼƬ����
	 */
	private void InitImage() {

		Drawable d = getBaseContext().getResources().getDrawable(
				R.drawable.icon_logo);
		BitmapDrawable bd = (BitmapDrawable) d;
		mbitmap = bd.getBitmap();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/**
	 * �ַ�����ȡ
	 * 
	 * @param is
	 * @return
	 */
	public String String_Cut(String inputString) {
		String str = null;
		try {
			int i = inputString.indexOf("<ul>");
			int q = inputString.lastIndexOf("</ul>");
			str = inputString.substring(i - 1, q + 5);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return str;
	}

}
