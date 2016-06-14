package nirvana.spider.temprory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

public class UrlRepository {
	private final static  Logger logger = Logger.getLogger(UrlRepository.class); 
	Random random = new Random(System.currentTimeMillis());
	private List<ArrayBlockingQueue<String>> container;
	private ArrayBlockingQueue<String> new_container;
	private List<Spider> spiders;
	private final static int PROCCESS_NUM =4;
	private  static UrlRepository instance = new UrlRepository(PROCCESS_NUM);
	
	public static  UrlRepository getInstance(){
		return instance;
	}

	private UrlRepository(int proccerNum){
		container = new ArrayList<ArrayBlockingQueue<String>>(proccerNum);
		new_container = new ArrayBlockingQueue<String>(10000);
		spiders = new  ArrayList<Spider>(proccerNum);
		for(int i=0;i<proccerNum;i++){
			Spider spider = new Spider(i);
			//thread.start();
			spiders.add(spider);
		}
	}
	
	public void startSpider(){
		for(int i=0;i<spiders.size();i++){
			Thread thread = new Thread(spiders.get(i));
			thread.setName("spider-"+i);
			thread.start();
		}
	}
	
	public synchronized void addUrl(int num,String url){
		int i = num%PROCCESS_NUM;
		new_container.add(url);
	}
	
	public  synchronized String takeUrl(int no){
		try {
			long a = random.nextInt(1000);
			if(a<1000){
				a=a+500;
			}
			Thread.sleep(a);
			
			String url = new_container.take();
			logger.debug("'begin  proccess url'\t"+url+"\t "+ (10000-new_container.remainingCapacity()));
			return url;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
