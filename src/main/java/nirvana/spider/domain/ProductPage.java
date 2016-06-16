package nirvana.spider.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 商品页
 * @author nirvna
 *
 */
public  abstract class ProductPage extends HtmlPage {
	
	/**
	 * 商品id
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
