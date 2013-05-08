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
 * @time ����8:35:54
 * @author �����
 * 
 *         ��˵��:
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

	// ��ʼ��������Ԥ������
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		listMaps = new ArrayList<Map<String, String>>();

	}

	// ������һ��Ԫ�ص�ʱ����
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

	// Ԫ�ؽ�������
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

	// �ڵ����ʱ����
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
