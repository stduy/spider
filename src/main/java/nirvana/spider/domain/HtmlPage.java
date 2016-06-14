package nirvana.spider.domain;

import java.util.HashMap;
import java.util.Map;

import nirvana.spider.download.Parseable;

public abstract class HtmlPage  implements Parseable {
	
	/**
	 * ��Ʒurl
	 */
	private String url;
	
	/**
	 * ��Ʒid
	 */
	private String goodsid;
	/**
	 * ��ҳ����
	 */
	private String content;
	
	
 

	private Map<String,Object> values = new HashMap<String,Object>(); 

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	
	public void addFiled(String key,Object value){
		this.values.put(key, value);
	}

	 
}
