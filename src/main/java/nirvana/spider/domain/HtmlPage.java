package nirvana.spider.domain;

import java.util.HashMap;
import java.util.Map;

import nirvana.spider.download.Parseable;

public abstract class HtmlPage  implements Parseable {
	
	/**
	 * ��ҳurl
	 */
	private String url;
	
	/**
	 * ��ҳ����(title)
	 */
	private String title;
	
	/**
	 * ��ҳ����
	 */
	private String content;
	
    //��ҳ�е�������ϢԪ��
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
