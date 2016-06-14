package nirvana.spider.domain;

import java.util.HashMap;
import java.util.Map;

import nirvana.spider.download.Parseable;

public abstract class HtmlPage  implements Parseable {
	
	/**
	 * 网页url
	 */
	private String url;
	
	/**
	 * 网页标题(title)
	 */
	private String title;
	
	/**
	 * 网页内容
	 */
	private String content;
	
    //网页中的其他信息元素
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

	
	public void addFiled(String key,Object value){
		this.values.put(key, value);
	}

	 
}
