package com.threebowl.foodsystem.base;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @date 2013-3-26
 * @time 下午9:33:21
 * @author 许度庆
 * 
 *         类说明:
 */
public class ContentHandle extends DefaultHandler {
	List<String> bitmapUrls;
	public List<String> getBitmapUrls() {
		return bitmapUrls;
	}


	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		bitmapUrls = new ArrayList<String>();
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		if (qName.equals("img")) {
			for (int i = 0; i < attributes.getLength(); i++) {
				if (attributes.getQName(i).equals("src")) {
					//map.put("img", attributes.getValue(i));
					bitmapUrls.add(attributes.getValue(i));
				}
				
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}
}
