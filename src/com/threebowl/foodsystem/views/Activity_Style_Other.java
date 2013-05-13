package com.threebowl.foodsystem.views;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.threebowl.foodsystem.R;
import com.threebowl.foodsystem.R.string;
import com.threebowl.foodsystem.base.HttpDate;
import com.threebowl.foodsystem.base.MyHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @date 2013-5-13
 * @time 下午12:34:04
 * @author 汪家栋
 * 
 *         类说明:
 */
public class Activity_Style_Other extends Activity {

	private String phah_caixi;
	private ListView listView;

	 private List<Map<String, String>> caixiList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_style_other);
		//获取Intent传来的数据
		Intent intent = getIntent();
		phah_caixi = intent.getStringExtra("caixi");

		HttpDate httpDate = new HttpDate();
		String caixiHTML = httpDate.GetHtml(phah_caixi);
		 String caixi_cuthtml = String_Cut(caixiHTML);
		
		 try {
			InputStream inputStream = httpDate.String2InputStream(caixi_cuthtml);
			caixiList = getData(inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 

		listView = (ListView) this.findViewById(R.id.caixi_xml);

		 ArrayAdapter<Map<String, String>> adapter = new ArrayAdapter<Map<String, String>>(
		 Activity_Style_Other.this, android.R.layout.simple_list_item_1,
		 caixiList);
		 listView.setAdapter(adapter);

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
			
			Toast.makeText(Activity_Style_Other.this, e.toString(), Toast.LENGTH_LONG).show();
		}

		return listData;
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

			int i = is.indexOf("<ul>");
			int q = is.indexOf("</ul>");
			str = is.substring(i - 1, q + 5);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return str;
	}

}
