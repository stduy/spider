package nirvana.spider.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * ��Ʒҳ
 * @author nirvna
 *
 */
public  abstract class ProductPage extends HtmlPage {
	
	/**
	 * ��Ʒid
	 */
	private String goodsid;
 
	
	public void setGoodsid(String goodsid) {
		 this.goodsid = goodsid;
	}


	public String getGoodsid() {
		return goodsid;
	}


	public  abstract void parse();
	
	public  void parseGoodsid(){
	
	}

}
