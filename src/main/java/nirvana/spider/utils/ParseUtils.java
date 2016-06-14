package nirvana.spider.utils;


import java.util.ArrayList;
import java.util.List;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import nirvana.spider.domain.HtmlPage;
import nirvana.spider.download.HttpClientDownload;

public class ParseUtils {
  
	public static  String getByXPath(String content,String xpath){
		HtmlCleaner cleaner = new HtmlCleaner() ;
		TagNode rootNode = cleaner.clean(content);
		try {
			Object[] evaluateXPath = rootNode.evaluateXPath(xpath);
			if(evaluateXPath.length>0){
				TagNode titleNode = (TagNode)evaluateXPath[0];
				return titleNode.getText().toString();
			}
			
		} catch (XPatherException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static  String getTextByXPath(TagNode  rootNode,String xpath){
		try {
			Object[] evaluateXPath = rootNode.evaluateXPath(xpath);
			if(evaluateXPath.length>0){
				TagNode titleNode = (TagNode)evaluateXPath[0];
				return titleNode.getText().toString();
			}
			
		} catch (XPatherException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static  String getAttrValByXPath(TagNode  rootNode,String xpath,String attrName){
		try {
			Object[] evaluateXPath = rootNode.evaluateXPath(xpath);
			if(evaluateXPath.length>0){
				TagNode node = (TagNode)evaluateXPath[0];
				return node.getAttributeByName(attrName).toString();
			}
			
		} catch (XPatherException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<String> getAttrValsByXPath(TagNode  rootNode,String xpath,String attrName){
		try {
			Object[] evaluateXPath = rootNode.evaluateXPath(xpath);
			if(evaluateXPath.length>0){
				List<String> attrVals = new ArrayList<String>(evaluateXPath.length);
				for(Object obj : evaluateXPath){
					TagNode node = (TagNode)obj;
					String attrVal = node.getAttributeByName(attrName);
					if(attrVal != null){
					   attrVals.add(attrVal);
					}   
				}
				return  attrVals;
			}
			
		} catch (XPatherException e) {
			e.printStackTrace();
		}
		return null;	}
	
	public static void main(String[] args) {
		HttpClientDownload httpClientDownload = new HttpClientDownload();
		HtmlPage page = httpClientDownload.download("http://shop.zhe800.com/products/ze151012163225000934?jump_source=1&qd_key=qyOwt6Jn");
		page.parse();
	}
}
