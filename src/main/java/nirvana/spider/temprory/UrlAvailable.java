package nirvana.spider.temprory;

public interface UrlAvailable {
	
	public final static int UNKOWN_HOST = -2;
	public final static int TIME_OUT = -1;
	public final static int NOT_AVAILABLE = 0;
	public final static int AVAILABLE = 1;
	int isAvailable(String url);
}
