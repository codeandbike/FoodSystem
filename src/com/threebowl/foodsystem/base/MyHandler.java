package com.threebowl.foodsystem.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @date 2012-11-10
 * @time 下午8:35:54
 * @author 许度庆
 * 
 *         类说明:
 */
public class MyHandler extends DefaultHandler {

	private List<Map<String, String>> listMaps;
	private Map<String, String> map;
	private String elementTag = null;

	public List<Map<String, String>> getListMaps() {
		return listMaps;
	}

	public MyHandler() {
		// TODO Auto-generated constructor stub
	}

	// 开始解析，做预处理工作
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		listMaps = new ArrayList<Map<String, String>>();

	}

	// 解析第一个元素的时触发
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		elementTag = localName;
		if (localName.equals("li")) {
			this.map = new HashMap<String, String>();
		}
		if (map !=null) {
			if (qName.equals("a")) {
				map.put("a", attributes.getValue(0));
			}else if(qName.equals("img")){
				for (int i = 0; i < attributes.getLength(); i++) {
					if (attributes.getQName(i).equals("src")) {
						map.put("img", attributes.getValue(i));
					}
				}
			}
		}
		

	}

	// 元素解析结束
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		elementTag = null;
		if (localName.equals("li")) {
			listMaps.add(map);
			map = null;
		}

	}

	// 节点解析时触发
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		String data = new String(ch, start, length);

		if (elementTag != null && map != null) {
			if ("h2".endsWith(elementTag)) {
				map.put("h2", data);
			}
			if ("span".endsWith(elementTag)) {
				map.put("span", data);
			}
		}

	}

}
