package nirvana.spider.domain;

import java.util.List;
import java.util.Map;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import nirvana.spider.utils.ParseUtils;

public class Zhe800HtmlPage extends HtmlPage {

	public void parse() {
		String content = super.getContent();
		HtmlCleaner cleaner = new HtmlCleaner() ;
		TagNode rootNode = cleaner.clean(content);
		//商品标题
		String title = ParseUtils.getTextByXPath(rootNode, "//*[@id=\"detail\"]/div[2]/div[1]/h1");
		//价格
		String price = ParseUtils.getTextByXPath(rootNode, "//*[@id=\"detail\"]/div[2]//dl[1]/dd/strong/i");
		String prevPrice = ParseUtils.getTextByXPath(rootNode, "//*[@id=\"detail\"]/div[2]//dl[1]/dd/del/i");
		//$x("//*[@id=\"detail\"]/div[1]//img")
		List<String> imageUrls = ParseUtils.getAttrValsByXPath(rootNode, "//*[@id=\"detail\"]/div[1]//img","bigimage-data");
		System.out.println(imageUrls);
		
	}

}
