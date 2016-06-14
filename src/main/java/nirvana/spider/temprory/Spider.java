package nirvana.spider.temprory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;


public class Spider  implements Runnable{
	private int no;
	private BufferedWriter bw;
	public Spider(int no){
		this.no = no;
		try {
			this.bw = new BufferedWriter(new FileWriter(new File(String.format("/home/nirvna/kanguowai_%d",no))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	private void proccess(String url){
		long starttime = System.currentTimeMillis();
		PageDownloader loader = new KanguowaiDoanloader();
		HtmlPage page = loader.download(url);
		long downloadendtime = System.currentTimeMillis();
		String content = page.getContent();
		String keyUrl = null;
		boolean downloadflag = false;
		int available = 22;
		if(content!=null && !content.isEmpty()){
			downloadflag=true;
			 Map<String,String>  attr = page.getKeyUrl(); 
			UrlAvailable a = new CurlAvailable();
			String href = attr.get("href");
			 available = a.isAvailable(href);
			 keyUrl = attr.get("urlContent");
			try {
				bw.write(keyUrl+"\t"+getVisitable(available)+"\t"+(available>0 ? available : "")+"\n");
				bw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			logger.error("downloadfail "+url+"\t reput into repository ");
			UrlRepository.getInstance().addUrl(-1, url);
		}
		logger.debug(page.getSrcurl()+"\t"+keyUrl+"\t"+downloadflag+"\t"+available+"\t"+(downloadendtime-starttime));

	}
	
	private static int getVisitable(int available){
		if(available<=0){
			return available;
		}
		if(available>=400 && available<500){
			return -3;
		}
		if(available>=500){
			return -3;
		}
		return 1;
	}
	
	public static void main(String[] args) throws IOException {
	 
	}

	public void run() {
		UrlRepository instance = UrlRepository.getInstance();
		while(true){
			String takeUrl = instance.takeUrl(this.no);
			if(takeUrl==null || takeUrl.isEmpty()){
				continue;
			}
			try{
				proccess(takeUrl);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
	/*	long starttime = System.currentTimeMillis();
		PageDownloader loader = new KanguowaiDoanloader();
		UrlRepository instance = UrlRepository.getInstance();
		String url = instance.takeUrl(this.no);
		HtmlPage page = loader.download();
		long downloadendtime = System.currentTimeMillis();
		String content = page.getContent();
		
		boolean downloadflag = false;
		if(content!=null && !content.isEmpty()){
			downloadflag=true;
			String keyUrl = page.getKeyUrl();
			System.out.println(keyUrl);
			UrlAvailable a = new PingTimeOutUrlAvailable();
			//System.out.println(a.isAvailable(keyUrl));
			bw.write(keyUrl+"\t"+a.isAvailable(keyUrl)+"\n");
		}*/
		
	}
}
