package nirvana.spider.temprory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.log4j.Logger;

import nirvana.spider.utils.RequestUtils;

public class UrlList {
	private final static  Logger logger = Logger.getLogger(UrlList.class); 

	
	public static void main(String[] args) throws IOException {
		//List<String> urlLists = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(new File("/home/nirvna/url_list")));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("/home/nirvna/url_list.result")));

		String line = null;
		while((line = br.readLine() ) != null){
			if(line.trim().isEmpty()){
				continue;
			}
			int responseCode = RequestUtils.getResponseCode("www."+line.trim(),true);
			
			int responseCode2 =0;
			if(responseCode<=0){
				  responseCode2 = RequestUtils.getResponseCode("www."+line.trim(),true);
				  if(responseCode2>0){
					  responseCode = responseCode2;
				  }
			}
			 
			bw.write(line.trim()+"\t"+getVisitable(responseCode)+"\t"+(responseCode>0 ? responseCode : "")+"\n");
			bw.flush();
			logger.debug(line.trim()+"\t"+getVisitable(responseCode)+"\t"+(responseCode>0 ? responseCode : "")+"\n");
		}
		br.close();
		bw.close();
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
}
