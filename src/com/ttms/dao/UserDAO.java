package com.ttms.dao;

import java.sql.*;

import com.ttms.domain.User;
import com.mysql.jdbc.Connection;

public class UserDAO {
	/*
	 * 操作：
	 * 1.获取连接
	 * 2.执行操作：
	 * 	注册：（1）.通过用户名查找，如果找到则报出异常
	 * 	（2）.找不到则添加
	 */
	
	//获取连接Connection
	private static Connection getConn(){
		String username="root";
		String password="123456789";
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/ttms";
		Connection conn=null;
		
		try{
			Class.forName(driver);
			conn=(Connection)DriverManager.getConnection(url,username,password);
			
		}catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		return conn;
	} 
	
	//查找对应用户是否存在
	public User findbyusername(String username){
		User user=new User();
		Connection conn=getConn();
		String sql="select * from user where user_username='" + username + "'";
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
			if(username.equals(rs.getString("user_username"))){
				user.setUsername(username);
				user.setPassword(rs.getString("user_password"));
				return user;
			}
			}
			rs.close();
			stmt.close();
	        conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 将新的数据存入数据库
	 * 1.建立连接
	 * 2.获取数据username和password
	 * 3.将数据存入数据库
	 */	
	public void add(User user){
		Connection conn=getConn();
		String username=user.getUsername();
		String password=user.getPassword();
		String sql="insert into user(user_username,user_password) values('" + 
		username + "','" + password + "'" + ")";
		try {
			Statement stmt=conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
	        conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
