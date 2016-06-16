package nirvana.spider.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import nirvana.spider.utils.ParseUtils;
/**
 * ÕÛ800Ê×Ò³
 * @author nirvna
 *
 */
public class Zhe800IndexPage extends HtmlPage {
	/**
	 * 
	 */
	private List<String> produtListUrls = new ArrayList<String>();
	
	public void parse() {
		String content = super.getContent();
		HtmlCleaner cleaner = new HtmlCleaner() ;
		TagNode rootNode = cleaner.clean(content);
		produtListUrls = ParseUtils.getAttrValsByXPath(rootNode, "//*[@id=\"index_nav\"]/ul/li/a", "href");
		List<String> productNames = ParseUtils.getTextsByXPath(rootNode, "//*[@id=\"index_nav\"]/ul/li/a");
		System.err.println(productNames);
		List<String> aprodutListUrl = ParseUtils.getAttrValsByXPath(rootNode, "//*[@id=\"index_nav\"]/ul/li[ @class=\"bottom\"]/a", "href");
		List<String> aproductNames = ParseUtils.getTextsByXPath(rootNode, "//*[@id=\"index_nav\"]/ul/li[ @class=\"bottom\"]/a");

		List<String> bprodutListUrl = ParseUtils.getAttrValsByXPath(rootNode, "//*[@id=\"index_nav\"]/ul/li[ @class=\"bottom border_b\"]/a", "href");
		List<String> bproductNames = ParseUtils.getTextsByXPath(rootNode, "//*[@id=\"index_nav\"]/ul/li[ @class=\"bottom border_b\"]/a");

		produtListUrls.removeAll(aprodutListUrl);
		produtListUrls.removeAll(bprodutListUrl);
	}

}
