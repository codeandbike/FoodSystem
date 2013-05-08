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
 * @time ����8:49:09
 * @author �����
 * 
 *         ��˵��:�����������ݵĴ����ࡣ����ҳ�����ݵĻ�ȡ��ҳ�����ݵĽ�����ͼƬ���ݵĻ�ȡ��
 */
public class HttpDate {

	public HttpDate() {
		// TODO Auto-generated constructor stub
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

	/**
	 * ���ݽ���
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
	 * GET��ȡ��������
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
	 * �ַ�����ȡ
	 * 
	 * @param is
	 * @return
	 */
	public String String_Cut(String is) {
		String str = null;
		try {

			int i = is.indexOf("��������</div>");
			int q = is.indexOf("</ul></div>");
			str = is.substring(i+10, q + 11);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return str;
	}
	
	/**
	 * ��ȡ�����еĲ�Ʒ��������
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

}
