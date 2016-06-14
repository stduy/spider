package nirvana.spider.temprory;

import java.util.Map;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

public class KanguowaiPage extends HtmlPage {

	@Override
	public Map<String,String> getKeyUrl() {
		String content = super.getContent();
		HtmlCleaner cleaner = new HtmlCleaner() ;
		TagNode rootNode = cleaner.clean(content);
		try {
			Object[] evaluateXPath = rootNode.evaluateXPath("//*[@id=\"tosite\"]/a");
			if(evaluateXPath.length>0){
				TagNode xml = (TagNode)evaluateXPath[0];
				Map<String, String> attributes = xml.getAttributes();
				String text = String.valueOf(xml.getText());
				if(text==null || text.trim().isEmpty()){
					UrlRepository.getInstance().addUrl(-1, super.getSrcurl());
				}else{
					if(text.contains("/")){
						String[] split = text.split("/");
						text = split[0];
					}
				}
				attributes.put("urlContent",text);
				return attributes;
				
			}
			
		} catch (XPatherException e) {
			e.printStackTrace();
		}
		return null;
	}	

}
