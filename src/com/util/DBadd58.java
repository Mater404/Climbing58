package com.util;

import java.text.DecimalFormat;
import java.util.List;

import com.dao.OperateOracle;
import com.ov.DBbuild;
import com.ov.Html58build;

public class DBadd58 {
	public static void dbadd(List<DBbuild> list,int i,List<Html58build> date58,double AVG58) {
		OperateOracle oo=new OperateOracle();
		DecimalFormat df = new DecimalFormat("######0.00");  //保留两位小数
		//存入数据
		DBbuild d58a = new DBbuild();
		
		d58a.setBldg_no(list.get(i).getBldg_no());
		d58a.setBldg_name(list.get(i).getBldg_name());
		d58a.setZone_no(list.get(i).getZone_no());
		d58a.setZj_avg_price(list.get(i).getZj_avg_price());
		d58a.setZj_avg_price_u(list.get(i).getZj_avg_price_u());
		try {
			d58a.setName58(date58.get(0).getName());
    		d58a.setAvg58(""+df.format(AVG58));
    		d58a.setMoney58(""+date58.get(0).getMoney());
    		d58a.setHx58(date58.get(0).getHx());	//户型
		} catch (Exception e) {
			d58a.setName58("");
    		d58a.setAvg58("");
    		d58a.setMoney58("");
			// TODO: handle exception
		}
//		写入
		oo.adddate58avg(d58a);
	}
}
