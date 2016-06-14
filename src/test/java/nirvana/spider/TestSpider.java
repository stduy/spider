package nirvana.spider;

import nirvana.spider.domain.HtmlPage;
import nirvana.spider.domain.Spider;
import nirvana.spider.download.HttpClientDownload;

import org.junit.Test;

public class TestSpider {
	
	@Test
	public void testSpider(){
		Spider spider = new Spider(new HttpClientDownload());
		String url = "http://item.jd.com/1269262.html";
		HtmlPage page = spider.downloadPage(url);
		spider.proccess(page);
		
	}
}
