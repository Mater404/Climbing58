package com.dao;

public class Test {

    public static void main(String[] args) {
        /**
         * 增删改查完成,但是有一定局限性
         * 1.增  问题不大
         * 2.删  要给出一个值去删除(可能值不存在-->没有处理机制,值不唯一怎么处理?)
         * 3.改  同删的问题
         * 4.查  问题不大
         */
        //创建OperateOracle对象
        OperateOracle oo=new OperateOracle();
        //测试增加数据操作
//        oo.AddData("孙中山",1,25,"北京市海淀区红旗路111号");
        //测试删除数据操作
        //oo.DeleteData("孙中山");
        //测试更新数据操作
//        oo.UpdateData("孙中山",1,30,"北京市东城区岳山路11号");
        //测试查询数据操作
//        oo.SelectData();
//        List<Build> list = oo.se();
//        for (Build str : list) {
//        	System.out.println(str.getBldg_no());
//        	System.out.println(str.getBldg_name());
//		}
        //测试ResultSetMetaData类
        //oo.SelectData2();
    }

}