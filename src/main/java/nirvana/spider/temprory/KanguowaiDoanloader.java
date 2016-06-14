package nirvana.spider.temprory;

import nirvana.spider.utils.RequestUtils;

public class KanguowaiDoanloader extends PageDownloader {
	
	@Override
	public HtmlPage download(String url) {
		HtmlPage page =new KanguowaiPage();
		String content = RequestUtils.getContent(url);
		page.setContent(content);
		page.setSrcurl(url);
		String[] split = url.split("/");
		String htmlName= split[split.length-1];
		if(htmlName!=null && !htmlName.trim().isEmpty()){
			String[] split2 = htmlName.split("\\.");
			String num = split2[0];
			page.setNum(Integer.parseInt(num));
		}
		return page;
	}
}
