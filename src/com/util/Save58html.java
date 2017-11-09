package com.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.ov.Html58build;

public class Save58html {
	//处理数据
	public static List<Html58build> Handlehtml(Document doc) {
		List<Html58build> date58=new ArrayList<Html58build>();	//存搜索的数据
		//正则表达式
		String mj = "卫     (.*?)㎡";
		String hx = "(.*?)     "; 
		
		//获取指定区域的DOM
    	Elements elements1 = doc.select("[class=content]");
    	
    	Elements title = elements1.select("li div.des h2");	//获取名字
    	Elements area = elements1.select("li div.des p.room");	//获取面积/户型
    	Elements money = elements1.select("li div.money b");	//获取租金
    	System.out.println("当前条数:"+title.size());
    	
		//取出DOM数据
    	for (int j = 0; j < title.size(); j++) {
    		Html58build ul58 = new Html58build();
    		
    		try {
    			double doumj = Double.parseDouble(DealStrSub.getSubUtilSimple(area.get(j).text(),mj));
    			//剔除面积小于25的房子
    			if(doumj < 25){
    				continue;
    			}
    			ul58.setArea(doumj);
        		ul58.setName(title.get(j).text());
    	    	ul58.setMoney(Double.parseDouble(money.get(j).text()));
    	    	ul58.setHx(DealStrSub.getSubUtilSimple(area.get(j).text(),hx));
    		} catch (Exception e) {
				// TODO: handle exception
    			System.out.println("获取失败:"+title.get(j).text()+"|"+DealStrSub.getSubUtilSimple(area.get(j).text(),mj)+"|"+money.get(j).text());
			}
	    	date58.add(ul58);
		}
    	return date58;
	}
	//获取验证页面
	public static boolean GetVerify(Document doc) {
		//获取指定区域的DOM
    	Elements elements1 = doc.select("[class=pop]");
    	
    	Elements title = elements1.select("p.title");	//获取名字
    	if(title.size() == 0 ){
    		return false;
    	}
		return true;
	}
}
