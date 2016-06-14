package nirvana.spider.temprory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import nirvana.spider.utils.RequestUtils;

public class CurlAvailable implements UrlAvailable {

	public int isAvailable(String url) {
		
		return RequestUtils.getResponseCode(url);
		/*String pingStr = String.format("curl  --connect-timeout %d  -I '%s' ", new Object[]{5,url});

		try{
			System.out.println(pingStr);
			Process pro = Runtime.getRuntime().exec(pingStr);
			BufferedReader buf = new BufferedReader(new InputStreamReader(pro.getInputStream()));  
			String line = null;
		while ((line = buf.readLine()) != null){ 
			System.out.println(line);
			//dns解析失败
			if(line.indexOf("lookup timed out") > -1){
				pro.destroy();
				return UNKOWN_HOST;
			}
			//链接超时
			if(line.indexOf("couldn't connect to host")>-1){
				pro.destroy();
				return TIME_OUT;
			}
		 
			//ping不通
			if(line.startsWith("HTTP/1.1")){
				String[] split = line.split("\\s");
				pro.destroy();
				return  Integer.parseInt(split[1]);
			}
			if(line.indexOf("% packet loss") > -1){
				String[] splits = line.split("% packet loss");
				String[] split = splits[0].split("\\s");
				System.out.println(Arrays.toString(split));
				pro.destroy();
			}
		}
		
    	}catch (Exception ex){          
		System.out.println(ex.getMessage());   
	   }
	//ping超时
	return TIME_OUT;*/
}	

	public static void main(String[] args) {
		CurlAvailable a = new CurlAvailable();
		System.out.println(a.isAvailable("http://www.facebook.com"));
	}
}
