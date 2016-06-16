package nirvana.spider.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ProperyReader {

	private final static String DB_CONFIG="dbconfig.properties";  
	private final static List<String> CONFIG_FILES = new ArrayList<String>();
	private final static Map<String,String>  PROP_CACHE =new HashMap<String, String>();
	static{
		CONFIG_FILES.add(DB_CONFIG);
	}
	
	private ProperyReader(){
		for(String configFile : CONFIG_FILES){
			this.registerConfig(configFile);
		}
	}
	
	public static ProperyReader getReader(){
		return Singleton.properyReader;
	}
	
	/**
	 * 注册配置文件,同时将配置缓存至Map
	 * @return
	 */
	public void registerConfig(String configFile){
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getResourceAsStream("/"+configFile));
			Set<Object> keySet = prop.keySet();
			for(Object key : keySet){
				PROP_CACHE.put(String.valueOf(key), String.valueOf(prop.get(key)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public  String getValByKey(String key){
		return PROP_CACHE.get(key);
	}
	
	private static class Singleton{
		 static ProperyReader properyReader = new ProperyReader();  
	}
	
	public static void main(String[] args) {
		String valByKey = ProperyReader.getReader().getValByKey("mysql.db.driver");
		System.out.println(valByKey);
	}
}
