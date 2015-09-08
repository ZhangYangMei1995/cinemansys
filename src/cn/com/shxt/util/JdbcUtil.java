package cn.com.shxt.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcUtil {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private String url = "jdbc:mysql://localhost:3306/cinema";
	private String user = "root";
	private String password = "root";
	 
	/*1 ������������̬����飩*/
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
    /*2 ��ȡ����*/
	public Connection getConn(){
		try {
			conn=DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	/*3 ���²���*/
	public int update(String sql){
		try {
			stmt=getConn().createStatement();
			return stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally{
			release();
		}
		
	}
	
	public List<Map<String,Object>> query(String sql){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try {
			 stmt = getConn().createStatement();
			 rs = stmt.executeQuery(sql);
			 /**�������rsת��ΪList<Map<String,Object>>   ResultSetMetaData��ȡ�� ResultSet������е�����*/
			 ResultSetMetaData rsmt = rs.getMetaData();
			 int count = rsmt.getColumnCount();//��ý�������ֶεĸ���
			 while(rs.next()){
				Map<String,Object> map=new HashMap<String,Object>();
				for(int i = 0;i < count; i++){
					String key=rsmt.getColumnName(i+1);
					Object value=rs.getObject(key);
					map.put(key, value);
				}
				list.add(map);
			 }
		} catch (SQLException e) {
			e.printStackTrace();				
		}finally{
			release();
		}
		return list;
	}
	/*3 �������һ�����*/
	public int [] batch(String []sqls){
		try {
			conn=getConn();
			conn.setAutoCommit(false);
			stmt=conn.createStatement();
			for(String sql:sqls){
				stmt.addBatch(sql);
			}
			return stmt.executeBatch();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}			
			e.printStackTrace();	
			return null;
		} finally {
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			release();
		}
	}
	
	public void release(){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
