package nirvana.spider.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Proxy;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import nirvana.spider.domain.HtmlPage;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

public class RequestUtils {
	private static final int TIMEOUT_SECONDS = 1000;
	static Map<String,Integer> proxyIps = new HashMap<String,Integer>();
	
	static{
		proxyIps.put("202.100.167.137",80);
		proxyIps.put("182.90.12.168", 80);
		proxyIps.put("182.90.17.81", 80);
		proxyIps.put("110.73.10.66", 8123);
	}
	
	
	public static int getResponseCode(String url,boolean hasTarget){
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT_SECONDS * 3)
		        .setConnectTimeout(TIMEOUT_SECONDS * 3).build();
		CloseableHttpClient client = HttpClients.custom().setUserAgent("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)").setDefaultRequestConfig(defaultRequestConfig).build();
		
     HttpHost target = new HttpHost(url, 80,  
                "http"); 
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse reponse;
		int code = -1;
		try {
			reponse = client.execute(target,httpGet);
			StatusLine statusLine = reponse.getStatusLine();
			code = statusLine.getStatusCode();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			code=-3;
		} catch(UnknownHostException ex){
			ex.printStackTrace();
			code = -2;
		}catch(HttpHostConnectException ex){
			ex.printStackTrace();
			code = -1;
		}catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return code;	}
	public static int getResponseCode(String url){
		return getResponseCode(url,false);
	}
	public static String getContent(String url){
		HttpClientBuilder builder = HttpClients.custom();
		CloseableHttpClient client = builder.build();
        HttpHost target = new HttpHost(url, 80,  
                "http");  
        // 依次是代理地址，代理端口号，协议类型  
       // HttpHost proxy = new HttpHost("202.100.167.137", 80, "http");  
        //RequestConfig config = RequestConfig.custom().setProxy(proxy).build();  

        
		HttpGet httpGet = new HttpGet(url);
		//httpGet.setConfig(config);
		
		CloseableHttpResponse reponse;
		try {
			reponse = client.execute(httpGet);
			HttpEntity entity = reponse.getEntity();
			return EntityUtils.toString(entity,"UTF-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
