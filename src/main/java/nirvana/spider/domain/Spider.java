package nirvana.spider.domain;

import java.io.IOException;

import nirvana.spider.download.Downloadable;
import nirvana.spider.utils.RequestUtils;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.alibaba.fastjson.JSONArray;

/**
 * 
 * @author nirvana
 * 1¡¢ÏÂÔØÍøÒ³
 * 2¡¢½âÎöÍøÒ³
 * 3¡¢´æ´¢ÍøÒ³
 *
 */
public class Spider {
	private Downloadable downloadable;

	public Spider(Downloadable downloadable){
		this.downloadable = downloadable;
	}
	public void start() {
	}
 
	/**
	 * ÏÂÔØurlÖ¸¶¨µÄÒ³Ãæ
	 * @param url
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public HtmlPage downloadPage(String url)  {
		return this.downloadable.download(url);
	}
	public HtmlPage proccess(HtmlPage page) {
		String content = page.getContent();
		HtmlCleaner cleaner = new HtmlCleaner() ;
		TagNode rootNode = cleaner.clean(content);
		try {
			Object[] evaluateXPath = rootNode.evaluateXPath("//*[@id=\"name\"]/h1");
			if(evaluateXPath.length>0){
				TagNode titleNode = (TagNode)evaluateXPath[0];
				page.addFiled("title", titleNode.getText().toString());
			}
			
			 evaluateXPath = rootNode.evaluateXPath("//*[@id=\"spec-n1\"]/img");
			if(evaluateXPath.length>0){
				TagNode imageNode = (TagNode)evaluateXPath[0];
				page.addFiled("imgurl", imageNode.getText().toString());
			}
			
			String priceUrl = "http://p.3.cn/prices/get?skuid=J_1269262";
			String priceContent = RequestUtils.getContent(priceUrl);
			JSONArray array = JSONArray.parseArray(priceContent) ;
			page.addFiled("price", array.getJSONObject(0).getString("p"));

			return page;
		} catch (XPatherException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
