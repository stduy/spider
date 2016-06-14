package nirvana.spider.download;

import nirvana.spider.domain.HtmlPage;

public interface Downloadable {

	HtmlPage download(String url);

}
