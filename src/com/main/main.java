package com.main;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;






import com.dao.OperateOracle;
import com.ov.DBbuild;
import com.ov.Html58build;
import com.util.CnAVG;
import com.util.DBadd58;
import com.util.DealStrSub;
import com.util.Save58html;

public class main {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		/*
		 * *
		 * 1.传入项目名
		 * 2.步数
		 * 3.传入分区代码
		 * 罗湖	luohu
		 * 福田	futian
		 * 南山	nanshan
		 * 盐田	yantian
		 * 宝安	baoan
		 * 龙岗 longgang
		 */
		String project = "ZHUZHAI_NS_BUILD_1710301106";
		init(project,"nanshan",0);
	}

	private static void init(String project,String fq , int i) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		Document doc = null;
		OperateOracle oo=new OperateOracle();
		List<DBbuild> list = oo.se(project);
		boolean ZT = true; 	//状态
		int pq=0;
        for (; i < list.size(); i++) {
			Thread.sleep(500);	//休息5毫秒
//        	System.out.println(i);
			String url = geturl(list.get(i).getBldg_name(),fq);	//拼接url
        	try {
        		doc = Jsoup.connect(url).userAgent("Mozilla").get();  //获取网页
    			System.out.println(url);
        	} catch (IOException e) {
    			// TODO Auto-generated catch block
    			System.out.println("网络异常");
    			e.printStackTrace();
    		}
        	
        	//DOM数据处理
        	List<Html58build> date58 = new ArrayList<Html58build>();
        	date58 = Save58html.Handlehtml(doc);
        	
        	
        	//检查是否被封
        	if(Save58html.GetVerify(doc)){
        		if(ZT){
        			//打开浏览器
            		Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://sz.58.com/zufang/?key=ggbh");
            		ZT = false;
        		}
        		i--;
        		continue;
        	}
        	ZT = true;
        	//计算平均值
        	double AVG58 = CnAVG.cn58Avg(date58);
        	//存入数据
        	DBadd58.dbadd(list, i, date58, AVG58);
    		System.out.println("当前进度:"+i+"/"+list.size()+" 爬取进度："+pq+++"/200");
    		if(pq>=200){
    			pq=0;
    			System.out.println("爬取休息中15s.....");
    			Thread.sleep(15000);		//休息时间
    		}
		}
	}

	//拼接url
	private static String geturl(String name,String fq) {
		String url = "http://sz.58.com/"+fq+"/zufang/0/?key="+name+"";
		return url;
	}

}
