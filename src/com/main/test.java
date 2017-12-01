package com.main;

import java.sql.SQLException;
import java.util.List;

import com.dao.OperateOracle;
import com.ov.DBbuild;
import com.util.DBadd58;
public class test {

	public static void main(String[] args) throws InterruptedException, SQLException {
		// TODO Auto-generated method stub
		OperateOracle oo=new OperateOracle();
		List<DBbuild> list = oo.se58("date58avg");
		String A;
		String B;
		for (DBbuild dBbuild : list) {
			A = dBbuild.getBldg_name();
			B = dBbuild.getName58();
			try {
				if(!B.contains(A.substring(0,2))){
					System.out.println(A+"_！！！！！！！"+B);
					oo.DeleteDataAVG58(dBbuild.getBldg_no());
				}
			} catch (Exception e) {
				// TODO: handle exception
				oo.DeleteDataAVG58(dBbuild.getBldg_no());
			}
			
		}
	}

}
