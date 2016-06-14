package nirvana.spider.temprory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class PingTimeOutUrlAvailable implements UrlAvailable {

	private static final int DEFAULT_TIME_OUT = 10000;

	public  int isAvailable(String url) {
		return availaleByPing(url); 
	}
	
	public int availaleByPing(String url){
		try{
			String pingStr = String.format("ping %s -w %d ", new Object[]{url,3});
			Process pro = Runtime.getRuntime().exec(pingStr);
			BufferedReader buf = new BufferedReader(new InputStreamReader(pro.getInputStream()));  
			String line = null;
			while ((line = buf.readLine()) != null){ 
				System.out.println(line);
				//dns解析失败
				if(line.indexOf("unknown host") > -1){
					pro.destroy();
					return UNKOWN_HOST;
				}
				//通
				if(line.indexOf("time=")>-1){
					pro.destroy();
					return AVAILABLE;
				}
				//ping不通
				if(line.indexOf("Destination Net Unreachable")>-1){
					pro.destroy();
					return NOT_AVAILABLE;
				}
			/*	if(line.indexOf("% packet loss") > -1){
					String[] splits = line.split("% packet loss");
					String[] split = splits[0].split("\\s");
					System.out.println(Arrays.toString(split));
					pro.destroy();
				}*/
			}
			
		}catch (Exception ex){          
			System.out.println(ex.getMessage());   
		}
		//ping超时
		return TIME_OUT;
	}
	
	public int availableByInetAddress(String url) {
		if(url==null ||  url.isEmpty()){
			return NOT_AVAILABLE;
		}
		 boolean reachable =false;
		try {
			reachable = InetAddress.getByName(url).isReachable(DEFAULT_TIME_OUT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return reachable ? AVAILABLE : NOT_AVAILABLE;
	}
	public static void main(String[] args) {
		PingTimeOutUrlAvailable a = new PingTimeOutUrlAvailable();
		System.out.println(a.availaleByPing("http://www.aabaidua.com"));
	}

}
