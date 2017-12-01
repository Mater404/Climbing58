package com.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ov.DBbuild;



public class OperateOracle {

    // ��������������ַ���
    // 192.168.0.X�Ǳ�����ַ(Ҫ�ĳ��Լ���IP��ַ)��1521�˿ںţ�XE�Ǿ����Oracle��Ĭ�����ݿ���
    private static String USERNAMR = "test";
    private static String PASSWORD = "test";
    private static String DRVIER = "oracle.jdbc.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";

    // ����һ�����ݿ�����
    Connection connection = null;
    // ����Ԥ����������һ�㶼�������������Statement
    PreparedStatement pstm = null;
    // ����һ�����������
    ResultSet rs = null;

    /**
     * �����ݿ�����������
     * ���Ȼ�ȡ������������,����+1Ϊ�������ݵ�idֵ
     * @param stuName:ѧ������
     * @param gender:ѧ���Ա�,1��ʾ����,2��ʾŮ��
     * @param age:ѧ������
     * @param address:ѧ��סַ
     */
    public void AddData(String stuName, int gender, int age, String address) {
        connection = getConnection();
        // String sql =
        // "insert into student values('1','��С��','1','17','�����к�ƽ������30��¥7��102')";
        String sql = "select count(*) from student where 1 = 1";
        String sqlStr = "insert into student values(?,?,?,?,?)";
        int count = 0;

        try {
            // �������ݿ�student������������
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1) + 1;
                System.out.println(rs.getInt(1));
            }
            // ִ�в������ݲ���
            pstm = connection.prepareStatement(sqlStr);
            pstm.setInt(1, count);
            pstm.setString(2, stuName);
            pstm.setInt(3, gender);
            pstm.setInt(4, age);
            pstm.setString(5, address);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    }
    

    public void adddate58avg(DBbuild da58avg) {
        connection = getConnection();
        // String sql =
        // "insert into student values('1','��С��','1','17','�����к�ƽ������30��¥7��102')";
        String sqlStr = "insert into date58avg values(?,?,?,?,?,?,?,?,?,?,?)";
        int count = 0;

        try {
            // ִ�в������ݲ���
            pstm = connection.prepareStatement(sqlStr);
            
            pstm.setString(1, da58avg.getBldg_no());
            pstm.setString(2, da58avg.getBldg_name());
            pstm.setString(3, da58avg.getZone_no());
            pstm.setString(4, da58avg.getZj_avg_price());
            pstm.setString(5, da58avg.getZj_avg_price_u());
            pstm.setString(6, da58avg.getName58());
            pstm.setString(7, da58avg.getAvg58());
            pstm.setString(8, da58avg.getMoney58());
            pstm.setString(9, "2017-10-01");
            pstm.setString(10, da58avg.getHx58());
            pstm.setString(11, "סլ");
            
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    }
    
    /**
     * �����ݿ���ɾ������
     * @param stuName:��������ɾ������
     */
    public void DeleteData(String stuName) {
        connection = getConnection();
        String sqlStr = "delete from student where stu_name=?";
        System.out.println(stuName);
        try {
            // ִ��ɾ�����ݲ���
            pstm = connection.prepareStatement(sqlStr);
            pstm.setString(1, stuName);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    }
    /**
     * ɾ��û��ƥ���ϵ�����
     * @param stuName:��������ɾ������
     */
    public void DeleteDataAVG58(String ID) {
        connection = getConnection();
        String sqlStr = "delete from date58avg where BLDG_NO=?";
//       System.out.println(sqlStr);
        try {
            // ִ��ɾ�����ݲ���
            pstm = connection.prepareStatement(sqlStr);
            pstm.setString(1, ID);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    }
    /**
     * �����ݿ����޸�����
     * @param stuName:ѧ������,���ݴ�ֵ��ѯҪ�޸ĵ�ĳ��ֵ
     * @param gender
     * @param age
     * @param address
     */
    public void UpdateData(String stuName, int gender, int age, String address) {
        connection = getConnection();
        String sql = "select id from student where 1 = 1 and stu_name = ?";
        String sqlStr = "update student set stu_name=?,gender=?,age=?,address=? where id=?";
        int count = 0;

        try {
            // �������ݿ�student������������
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, stuName);
            rs = pstm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
                System.out.println(rs.getInt(1));
            }
            // ִ�в������ݲ���
            pstm = connection.prepareStatement(sqlStr);
            pstm.setString(1, stuName);
            pstm.setInt(2, gender);
            pstm.setInt(3, age);
            pstm.setString(4, address);
            pstm.setInt(5, count);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    }

    /**
     * �����ݿ��в�ѯ����
     */
    public void SelectData() {
        connection = getConnection();
        String sql = "select * from student";
        try {
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("stu_name");
                String gender = rs.getString("gender");
                String age = rs.getString("age");
                String address = rs.getString("address");
                
                System.out.println(id + "\t" + name + "\t" + gender + "\t"
                        + age + "\t" + address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    }
    
    public List<DBbuild> se(String project) {
        connection = getConnection();
//        String sql = "select bldg_no,bldg_name from ZHUZHAI_LH_BUILD_1710201129  order by bldg_name";
//        String sql = "select bldg_no,bldg_name,zj_avg_price,zj_avg_price_u  from "+project+" where rownum < 100 order by bldg_name";
        String sql = "select bldg_no,bldg_name,zone_no,zj_avg_price,zj_avg_price_u  from "+project+" order by bldg_name";
        List<DBbuild> list = new ArrayList<DBbuild>();
        
        try {
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
            	DBbuild build = new DBbuild();
                
            	build.setBldg_no(rs.getString("bldg_no"));
            	build.setBldg_name(rs.getString("bldg_name"));
            	build.setZone_no(rs.getString("zone_no"));
            	build.setZj_avg_price(rs.getString("zj_avg_price"));
            	build.setZj_avg_price_u(rs.getString("zj_avg_price_u"));
            	list.add(build);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
        return list;
    }
    /**
     * 
    * @Title: se58   
    * @Description: TODO
    * @param @param project
    * @param @return
    * @return List<DBbuild>
    * @throws
     */
    public List<DBbuild> se58(String project) {
        connection = getConnection();
//        String sql = "select bldg_no,bldg_name from ZHUZHAI_LH_BUILD_1710201129  order by bldg_name";
//        String sql = "select bldg_no,bldg_name,zj_avg_price,zj_avg_price_u  from "+project+" where rownum < 100 order by bldg_name";
        String sql = "select BLDG_NO,BLDG_NAME,NAME58  from "+project+" order by bldg_name";
        List<DBbuild> list = new ArrayList<DBbuild>();
        
        try {
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
            	DBbuild build = new DBbuild();
                
            	build.setBldg_no(rs.getString("bldg_no"));
            	build.setBldg_name(rs.getString("bldg_name"));
            	build.setName58(rs.getString("name58"));
            	list.add(build);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
        return list;
    }
    /**
     * ʹ��ResultSetMetaData��������
     */
    public void SelectData2() {
        connection = getConnection();
        String sql = "select * from employees where 1 = 1";
        int count = 0;

        try {
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                count++;
            }

            ResultSetMetaData rsmd = rs.getMetaData();
            int cols_len = rsmd.getColumnCount();

            System.out.println("count=" + count + "\tcols_len=" + cols_len);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    }

    /**
     * ��ȡConnection����
     * 
     * @return
     */
    public Connection getConnection() {
        try {
            Class.forName(DRVIER);
            connection = DriverManager.getConnection(URL, USERNAMR, PASSWORD);
//            System.out.println("�ɹ��������ݿ�");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("class not find !", e);
        } catch (SQLException e) {
            throw new RuntimeException("get connection error!", e);
        }

        return connection;
    }

    /**
     * �ͷ���Դ
     */
    public void ReleaseResource() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstm != null) {
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}