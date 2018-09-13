package com.ttms.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.ttms.domain.Movie;
import com.ttms.domain.Plan;

public class MovieDAO {
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
	
	//查找所有电影  
	public List<Movie> findallmovie(){
		Connection conn=getConn();
		String sql="select * from movies where flag=1";
		List<Movie> list = new ArrayList<Movie>();  
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()) {  
				Movie movie=new Movie();  
				movie.setTitle(rs.getString("title"));
				movie.setTime(rs.getString("time"));
				movie.setCountry(rs.getString("country"));
				movie.setLan(rs.getString("lan"));
				movie.setType(rs.getString("type"));
				movie.setScore(rs.getString("score"));
				movie.setSpend_time(rs.getString("spend_time"));
				movie.setDirector(rs.getString("director"));
				movie.setMain_actor(rs.getString("main_actor"));
				movie.setImg_url(rs.getString("img_url"));
				movie.setIntroduction(rs.getString("introduction"));
				movie.setFlag(rs.getInt("flag"));
	            list.add(movie);  
	        }
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//查找电影（分页）								
	public List<Movie> fingpage(int startindex,int pagesize){
		Connection conn=getConn();
		String sql="select * from movies where flag=1 limit " + startindex + "," + pagesize;
		List<Movie> list = new ArrayList<Movie>();  
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()) {  
				Movie movie=new Movie();  
				movie.setTitle(rs.getString("title"));
				movie.setTime(rs.getString("time"));
				movie.setCountry(rs.getString("country"));
				movie.setLan(rs.getString("lan"));
				movie.setType(rs.getString("type"));
				movie.setScore(rs.getString("score"));
				movie.setSpend_time(rs.getString("spend_time"));
				movie.setDirector(rs.getString("director"));
				movie.setMain_actor(rs.getString("main_actor"));
				movie.setImg_url(rs.getString("img_url"));
				movie.setIntroduction(rs.getString("introduction"));
				movie.setFlag(rs.getInt("flag"));
	            list.add(movie);  
	        }
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	//查找某一部电影
	public Movie findbytitle(String title){
		Movie movie=new Movie();
		Connection conn=getConn();
		String sql="select * from movies where title='" + title + "'";
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				if(title.equalsIgnoreCase(rs.getString("title"))){
					movie.setTitle(rs.getString("title"));
					movie.setTime(rs.getString("time"));
					movie.setCountry(rs.getString("country"));
					movie.setLan(rs.getString("lan"));
					movie.setType(rs.getString("type"));
					movie.setScore(rs.getString("score"));
					movie.setSpend_time(rs.getString("spend_time"));
					movie.setDirector(rs.getString("director"));
					movie.setMain_actor(rs.getString("main_actor"));
					movie.setImg_url(rs.getString("img_url"));
					movie.setIntroduction(rs.getString("introduction"));
					movie.setFlag(rs.getInt("flag"));
					stmt.close();
					conn.close();
					return movie;
				} 
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//模糊查找
	public List<Movie> findbytitlefuzzy(String title){
		Connection conn=getConn();
		String sql="select * from movies where title like '%" + title + "%'";
		List<Movie> list = new ArrayList<Movie>();  
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()) {  
				Movie movie=new Movie();  
				movie.setTitle(rs.getString("title"));
				movie.setTime(rs.getString("time"));
				movie.setCountry(rs.getString("country"));
				movie.setLan(rs.getString("lan"));
				movie.setType(rs.getString("type"));
				movie.setScore(rs.getString("score"));
				movie.setSpend_time(rs.getString("spend_time"));
				movie.setDirector(rs.getString("director"));
				movie.setMain_actor(rs.getString("main_actor"));
				movie.setImg_url(rs.getString("img_url"));
				movie.setIntroduction(rs.getString("introduction"));
				movie.setFlag(rs.getInt("flag"));
	            list.add(movie);  
	        }
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void add(Movie movie){
		Connection conn=getConn();
		String sql="insert into movies(title,time,country,lan,type,score,spend_time,"
				+ "director,main_actor,img_url,introduction,flag) values('" 
				+ movie.getTitle() + "','" 
				+ movie.getTime() + "','" 
				+ movie.getCountry() + "','" 
				+ movie.getLan() + "','" 
				+ movie.getType() + "','" 
				+ movie.getScore() + "','" 
				+ movie.getSpend_time() + "','"
				+ movie.getDirector() + "','" 
				+ movie.getMain_actor() + "','" 
				+ movie.getImg_url() + "','" 
				+ movie.getIntroduction() + "',1)";
		try {
			Statement st=conn.createStatement();
			st.executeUpdate(sql);
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//下架电影，设置flag为0，不用删除数据库中的数据
	public void delete(String title){
		Connection conn=getConn();
		String sql="update movies set flag=0 where title='" + title + "'";
		try {
			Statement st=conn.createStatement();
			st.executeUpdate(sql);
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
}
