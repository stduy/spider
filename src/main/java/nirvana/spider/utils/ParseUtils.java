package nirvana.spider.utils;


import java.util.ArrayList;
import java.util.List;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import nirvana.spider.domain.HtmlPage;
import nirvana.spider.domain.Zhe800IndexPage;
import nirvana.spider.domain.Zhe800ListPage;
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
	
	
	public static List<String> getTextsByXPath(TagNode rootNode,String xpath){
		try {
			Object[] evaluateXPath = rootNode.evaluateXPath(xpath);
			if(evaluateXPath.length>0){
				List<String> attrVals = new ArrayList<String>(evaluateXPath.length);
				for(Object obj : evaluateXPath){
					TagNode node = (TagNode)obj;
					String text = node.getText().toString();
					if(text != null){
					   attrVals.add(text);
					}   
				}
				return  attrVals;
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
	
	public static void main(String[] args) throws InterruptedException {
		HttpClientDownload httpClientDownload = new HttpClientDownload();
		String url = "http://www.zhe800.com/";
		HtmlPage page = new Zhe800IndexPage();
		page.setUrl(url);
		HtmlPage download = httpClientDownload.download(page);
		download.parse();
		/*String url = "http://www.zhe800.com/ju_tag/taomeishi";
	    while(url!=null){
		 Zhe800ListPage page = (Zhe800ListPage)httpClientDownload.download(url);
		 page.parse();
		 url=page.getNextPage();
		 System.out.println(page.getNextPage());
		 Thread.sleep(1000);
	   }	*/ 
	}
}
