package nirvana.spider.download;

import nirvana.spider.domain.HtmlPage;
import nirvana.spider.domain.Zhe800ListPage;
import nirvana.spider.domain.Zhe800ProductPage;
import nirvana.spider.utils.RequestUtils;

public class HttpClientDownload implements Downloadable {
	
	public HtmlPage download(String url){
		HtmlPage page = new Zhe800ListPage();
		page.setContent(RequestUtils.getContent(url));
		return page;
	}

	public HtmlPage download(HtmlPage page) {
		page.setContent(RequestUtils.getContent(page.getUrl()));
		return page;
	}
}
