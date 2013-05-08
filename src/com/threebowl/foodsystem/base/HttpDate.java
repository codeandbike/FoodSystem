package com.threebowl.foodsystem.base;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


/**
 * @date 2013-3-19
 * @time 下午8:49:09
 * @author 许度庆
 * 
 *         类说明:关于网络数据的处理类。包括页面数据的获取、页面数据的解析、图片数据的获取等
 */
public class HttpDate {

	public HttpDate() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * post获取网络数据
	 * 
	 * @param url
	 * @return
	 */
	private String PostHtml(String url, String FoodName) {

		String urlAPI = url;
		String data = null;
		/* 建立post连线 */
		HttpPost httpPost = new HttpPost(urlAPI);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("q", FoodName));

		try {
			// 发出HTTP request
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// 取得HTTP response
			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(httpPost);
			// 若状态码为200 OK
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
	 * string 转换 inputstream
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

	/**
	 * 数据解析
	 * 
	 * @param inputStream
	 * @return
	 */
	public List<String> getData(InputStream inputStream) {
		List<String> listData = new ArrayList<String>();
		try {

			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser parser = spf.newSAXParser();
			ContentHandle handler = new ContentHandle();
			parser.parse(inputStream, handler);
			inputStream.close();
			listData = handler.getBitmapUrls();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return listData;
	}

	/**
	 * GET获取网络数据
	 * 
	 * @param urlString
	 * @return
	 */
	public String GetHtml(String urlString) {
		String html = null;
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inputStream = conn.getInputStream();
			byte[] data = readInputStream(inputStream);
			html = new String(data);
		} catch (Exception e) {
			// TODO: handle exception
			html = e.toString();
		}

		return html;
	}

	// 读取输入流中的数据，返回字节数组byte[]
	public byte[] readInputStream(InputStream inStream) throws Exception {
		// 此类实现了一个输出流，其中的数据被写入一个 byte 数组
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 字节数组
		byte[] buffer = new byte[1024];
		int len = 0;
		// 从输入流中读取一定数量的字节，并将其存储在缓冲区数组buffer 中
		while ((len = inStream.read(buffer)) != -1) {
			// 将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此输出流
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		// toByteArray()创建一个新分配的 byte 数组。
		return outStream.toByteArray();
	}
	
	/**
	 * 字符串截取
	 * 
	 * @param is
	 * @return
	 */
	public String String_Cut(String is) {
		String str = null;
		try {

			int i = is.indexOf("制作步骤</div>");
			int q = is.indexOf("</ul></div>");
			str = is.substring(i+10, q + 11);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return str;
	}
	
	/**
	 * 获取数据中的菜品制作过程
	 * @param input
	 */
	public List<String> GetSpan(String input){
		
		String s = input;
		List<String> mStrings = new ArrayList<String>();
		
		while (s.indexOf("</span>")!=-1) {

			s = s.substring(s.indexOf("</span>")+7);
			mStrings.add(s.substring(0, s.indexOf("</div>")));
		}
		
		return mStrings;
		
	}
	
	/**
	 * 图片的获取
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

}
