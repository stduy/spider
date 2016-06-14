package nirvana.spider.temprory;

import java.util.Map;

public abstract class HtmlPage {
	
	private int num;
	
	private String srcurl;
	
	public String getSrcurl() {
		return srcurl;
	}

	public void setSrcurl(String srcurl) {
		this.srcurl = srcurl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

 

	private String content;
	
	public abstract Map<String, String> getKeyUrl();

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
