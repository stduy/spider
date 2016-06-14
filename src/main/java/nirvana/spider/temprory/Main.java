package nirvana.spider.temprory;

public class Main {
	public static void produceKanguowai(){
		for(int i=1139;i<=8801;i++){
			String url = String.format("http://www.kanguowai.com/site/%d.html",i);
			UrlRepository instance = UrlRepository.getInstance();
			instance.addUrl(i, url);
		}	
		
	}
	public static void main(String[] args) {
		produceKanguowai();
		UrlRepository.getInstance().startSpider();
	}
}
