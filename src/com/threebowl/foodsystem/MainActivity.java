package com.threebowl.foodsystem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.threebowl.foodsystem.base.HttpDate;
import com.threebowl.foodsystem.base.MyHandler;
import com.threebowl.foodsystem.views.Activity_ContentPage;
import com.threebowl.foodsystem.views.MyAdapter;

import android.R.drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;

public class MainActivity extends ListActivity implements OnScrollListener {

	private ListView mListView_content;
	private FoodSystemApp app;
	private int scrollState;
	private Button footerButton;
	private LinearLayout footerlProgressBarLayout; // ����������
	private View view;
	private MyHandle myHandle; // �������handle
	private MyAdapter adapter;
	private List<Map<String, Object>> listData;
	private int pageID; // ����ҳ��id
	private Bitmap mbitmap;
	private List<Bitmap> listImage = new ArrayList<Bitmap>();
	private List<String> imageUrl = new ArrayList<String>();
	private String mString_Food;
	private int mTag_Food;   //ҳ���ʶ
	private String mURL_Page; //
	private String mURL_Food;
	private TextView mTextView_Title;
	private List<Map<String, String>> contentData;
	private ImageButton mImageButton_Back;
	private String phah_caixi;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListView_content = getListView();
		app = (FoodSystemApp) getApplicationContext();
		app.mBitmaps = listImage;
		mTextView_Title = (TextView) findViewById(R.id.Main_Text_Title);
		mImageButton_Back = (ImageButton) findViewById(R.id.Main_ImgBut_Back);
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
			
		// ���س�ʼ��ͼƬ
		InitImage();
		pageID = 2; // �ӵڶ�ҳ��ʼ����
		InputStream inputStream = null;
		listData = new ArrayList<Map<String, Object>>();
		contentData = new ArrayList<Map<String, String>>();
		try {
			String str;
			if (mTag_Food == 0) {
				str = PostHtml(
						"http://home.meishichina.com/wap.php?ac=search",
						mString_Food);
			}else {
				HttpDate httpDate = new HttpDate();
				str = httpDate.GetHtml(mURL_Page);

			}

			
//			HttpDate httpDate = new HttpDate();
//			String caixiHTML = httpDate.GetHtml("http://home.meishichina.com/wap.php?ac=collect&id=43422&t=3&fr=#utm_source=wap3_index_caixi");
			String xmlData = String_Cut(str);
			xmlData=xmlData.replaceAll("&nbsp;", "");
			
			inputStream = String2InputStream(xmlData);
			contentData = getData(inputStream);

			String img_url;
			for (int i = 0; i < contentData.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				img_url = contentData.get(i).get("img");
				imageUrl.add(img_url);
				// map.put("img", mbitmap);
				map.put("h2", contentData.get(i).get("h2"));
				map.put("span", contentData.get(i).get("span"));
				listImage.add(mbitmap);
				listData.add(map);

			}

		} catch (Exception e) {

		}

		adapter = new MyAdapter(listData, this, app);

		myHandle = new MyHandle();

		loadThread lThread = new loadThread();
		new Thread(lThread).start();

		// ���ؽ���������
		LayoutInflater inflater = LayoutInflater.from(this);
		view = inflater.inflate(R.layout.other_listview_footer_more, null);
		footerButton = (Button) view.findViewById(R.id.button);
		footerlProgressBarLayout = (LinearLayout) view
				.findViewById(R.id.linearlayout);
		footerlProgressBarLayout.setVisibility(view.GONE);

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

		mListView_content.addFooterView(view);
		mListView_content.setAdapter(adapter);
		mListView_content.setOnScrollListener(this);

		mListView_content
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
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

	public List<Map<String, String>> getData(InputStream inputStream) {
		List<Map<String, String>> listData = new ArrayList<Map<String, String>>();
		try {

			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser parser = spf.newSAXParser();
			MyHandler handler = new MyHandler();
			parser.parse(inputStream, handler);
			inputStream.close();

			listData = handler.getListMaps();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return listData;
	}

	/**
	 * ͼƬ�Ļ�ȡ
	 * 
	 * @param is
	 * @return
	 */

	public Bitmap getBitmap(String myUrl) throws Exception {
		URL url = new URL(myUrl);
		Bitmap bitmap = null;
		HttpURLConnection httpURLConnection = (HttpURLConnection) url
				.openConnection();
		httpURLConnection.setConnectTimeout(5000);
		httpURLConnection.setDoInput(true);
		httpURLConnection.setRequestMethod("GET");
		InputStream inputStream = httpURLConnection.getInputStream();
		bitmap = BitmapFactory.decodeStream(inputStream);
		inputStream.close();
		return bitmap;
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

	/**
	 * post��ȡ��������
	 * 
	 * @param url
	 * @return
	 */
	private String PostHtml(String url, String FoodName) {

		String urlAPI = url;
		String data = null;
		/* ����post���� */
		HttpPost httpPost = new HttpPost(urlAPI);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("q", FoodName));

		try {
			// ����HTTP request
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// ȡ��HTTP response
			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(httpPost);

			// ��״̬��Ϊ200 OK
			if (httpResponse.getStatusLine().getStatusCode() == 200) {

				data = EntityUtils.toString(httpResponse.getEntity(), "utf-8");

			} else {
				data = httpResponse.getStatusLine().toString();
			}

		} catch (Exception e) {

			return null;
		}

		return data;
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
			getPictureData("http://home.meishichina.com/wap.php?ac=search&q="
					+ mURL_Food + "&t=3&fr=&page=" + pageID + "");
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
					// listImage.add(i, getBitmap(img_url));
					listImage.set(temp, getBitmap(img_url));
				} catch (Exception e) {
					// TODO: handle exception
				}
				Message msg = new Message();
				MainActivity.this.myHandle.sendMessage(msg);
			}
		}
	}

	// ����׷��
	public String getPictureData(String path) {
		String html = null;

		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inputStream = conn.getInputStream();
			byte[] data = readInputStream(inputStream);
			html = new String(data);

			String xmlData = String_Cut(html);
			inputStream = String2InputStream(xmlData);

			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			list = getData(inputStream);

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

	// ��ȡ�������е����ݣ������ֽ�����byte[]
	public byte[] readInputStream(InputStream inStream) throws Exception {
		// ����ʵ����һ������������е����ݱ�д��һ�� byte ����
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// �ֽ�����
		byte[] buffer = new byte[1024];
		int len = 0;
		// ���������ж�ȡһ���������ֽڣ�������洢�ڻ���������buffer ��
		while ((len = inStream.read(buffer)) != -1) {
			// ��ָ�� byte �����д�ƫ���� off ��ʼ�� len ���ֽ�д��������
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		// toByteArray()����һ���·���� byte ���顣
		return outStream.toByteArray();
	}

	/**
	 * ��ʼ��ͼƬ����
	 */
	private void InitImage() {

		Drawable d = getBaseContext().getResources().getDrawable(
				R.drawable.ic_launcher);
		BitmapDrawable bd = (BitmapDrawable) d;
		mbitmap = bd.getBitmap();

	}

	/**
	 * string ת�� inputstream
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public InputStream String2InputStream(String str) throws Exception {
		ByteArrayInputStream stream = new ByteArrayInputStream(
				str.getBytes("utf-8"));
		return stream;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
