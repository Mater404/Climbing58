package com.util;

import java.util.List;

import com.ov.Html58build;

public class CnAVG {
	public static double cn58Avg(List<Html58build> date58) {
		double AVG58 = 0;
		int index58 = 0;
		//求平局
		for (Html58build d : date58) {
			//租金/面积
			if(d.getMoney()==0||d.getArea()==0)
			{
//					System.out.println("计算:"+AVG58+"|"+d.getMoney()+"|"+d.getArea());
				index58++;
				continue;
			}
			AVG58 += d.getMoney() / d.getArea();
//				System.out.println("计算:"+AVG58+"|"+d.getArea()+"|"+d.getMoney()+"|"+d.getName());

		}
		AVG58 = AVG58/(date58.size()-index58);
		System.out.println("结果:"+AVG58);
		return AVG58;
	}
}