package com.lu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class ResolverTranslateFileUtil {
	
	private List<String> countris=new ArrayList<String>();
	private List<String> templateStr=new ArrayList<String>();
	static Map<String, String> country = new HashMap<>();
	private File root=new File("");
	static{
		 	 country.put("中文", "zh");
			 country.put("英语", "en");
			 country.put("粤语", "yue");
			 country.put("wyw", "文言文");
			 country.put("日语", "jp");
			 country.put("韩语", "kor");
			 country.put("法语", "fra");
			 country.put("西班牙语", "spa");
			 country.put("葡萄牙语", "pt");
			 country.put("德语", "de");
			 country.put("意大利语", "it");
			 country.put("nl", "荷兰语");
			 country.put("波兰语", "pl");
			 country.put("匈牙利语", "hu");
			 country.put("繁体中文", "cht");
		}
	public void resolver(File  countypath,File strPath){
		resolverCounty((countypath));
		resolverString((strPath));
	}
	public void resolver(String countypath,String strPath){
		resolverCounty(new File(countypath));
		resolverString(new File(strPath));
	}
	public void resolverString(File file){
		BufferedReader read = null;
	      try {
	    	  read = new BufferedReader(new FileReader(file));
	    	  String str=null;
	    	  BaiduTranslateDemo b = new BaiduTranslateDemo();
	    	  String rootPath=root.getAbsolutePath()+File.separator+"translate";
				File dir=new File(rootPath);
				if(!dir.exists()){
					dir.mkdirs();
				}
	    	  while((str=read.readLine())!=null){
	    		  if (str.contains(">") && str.contains("</")) {
	  				int index = str.indexOf(">");
	  				int end = str.indexOf("</");
	  				if (end > index) {
	  					String key = str.substring(index + 1, end);
	  					System.out.println(key);
	  					for(String contury:countris){
	  						String value=country.get(contury);
	  						if(value!=null){
	  							String translate=b.translate(key, "zh", value);
//	  							translate.replace("", newChar)
	  							if(translate.contains("'")){
	  								translate=translate.replaceAll("'", "\\\\'");
	  							}
	  							System.out.println(key+"-"+contury+"->"+translate);
	  							File conturyFile=new File(dir,contury+".txt");
	  							String replace=str.substring(0, index+1)+translate+str.substring(end);
	  							IOUtil.writerString(conturyFile.getAbsolutePath(), replace);
	  						}
	  					}
	  					
	  				}
	  			}
	    	  }
	      }catch(Exception e){
	    	  
	      }finally{
	    	  IOUtil.closeStream(read);
	      }
	      
	      JOptionPane.showMessageDialog(null, "翻译完成", "提示", JOptionPane.QUESTION_MESSAGE);
		
	}
	public void resolverCounty(File file){
		String result=null;
		countris.clear();
		try {
			result=IOUtil.readerString(file.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(result==null){
			return;
		}
		if(result.trim().length()==0){
			return;
		}
		countris.addAll(Arrays.asList(result.split(",")));
	}
	private boolean stringIsEmpty(String str){
		if(str==null){
			return true;
		}
		if(str.trim().length()==0){
			return true;
		}
		return false;
	}
}
