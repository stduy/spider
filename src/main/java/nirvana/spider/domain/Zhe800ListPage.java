package nirvana.spider.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;

import com.alibaba.fastjson.JSON;

import javax.script.*;
import nirvana.spider.utils.ParseUtils;
import nirvana.spider.utils.RequestUtils;

/**
 * 折800商品列表页
 * @author nirvna
 *
 */
public class Zhe800ListPage extends HtmlPage {
	List<String> products = new ArrayList<String>() ;
	
	private String nextPage;
	
	//解析商品列表
	public void parse() {
		String content = super.getContent();
		HtmlCleaner cleaner = new HtmlCleaner() ;
		TagNode rootNode = cleaner.clean(content);//*[@id="dealWarpperwide_e"]/div[3]/div/span[6]/a
		this.setNextPage(ParseUtils.getAttrValByXPath(rootNode,"//*[@id=\"dealWarpperwide_e\"]/div[3]/div//a[@rel=\"next\"]", "href"));
				
		
	/*	 try {
			Document doc = new DomSerializer(new org.htmlcleaner.CleanerProperties()).createDOM(rootNode);
			Object obj = javax.xml.xpath.XPathFactory.newInstance().newXPath().evaluate("//*[starts-with(@id,'deal')]/div/a[1]", doc, javax.xml.xpath.XPathConstants.NODE);
			System.out.println(obj);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}*/
		// ScriptEngineManager engineManager = new ScriptEngineManager();  
	     //ScriptEngine engine = engineManager.getEngineByName("JavaScript"); //得到脚本引擎
	     

		/*String title = ParseUtils.getTextByXPath(rootNode, "//*[@id=\"detail\"]/div[2]/div[1]/h1");
		String productIds = ParseUtils.getTextByXPath(rootNode, "//*[@id=\"dealWarpperwide_e\"]/script").split(";")[0].split("=")[1].replace("[", "").replace("]", "").replace("{", "").replace("}", "").replace("ids:", "").trim();
		System.out.println(productIds);
		String content2 = RequestUtils.getContent("http://status.tuanimg.com/n/deal_service/json?deal_ids="+productIds);
		
		
		this.products = ParseUtils.getAttrValsByXPath(rootNode, "//div/a[@class=\"goods_img\"]", "href");
		System.out.println(content2);*/
		
	}

	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}
	
}
