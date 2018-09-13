package com.ttms.dao;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.ttms.domain.Movie;
import com.ttms.domain.Plan;

public class PlanDAO {
	private static Connection getConn(){
		String username="root";
		String password="123456789";
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/ttms?useUnicode=true&characterEncoding=utf-8&useSSL=false";
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



	//设置场次计划
		public void setplan(Plan plan){
			Connection conn=getConn();
			String sql="insert into plan(movie_title,room_id,begin_time,price) values('"
					+ plan.getMovie_title() + "',"
					+ plan.getRoom_id() + ",'"
					+ plan.getBegin_time() + "',"
					+ plan.getPrice() + ");";

			try {
				Statement st = conn.createStatement();
				st.executeUpdate(sql);
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	//根据电影名查找相关演出计划
	public List<Plan> searchplanbymovie(String title){
		Connection conn=getConn();
		String sql="select * from plan where movie_title='" + title + "'";
		List<Plan> list = new ArrayList<Plan>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Statement st = conn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				Plan plan=new Plan();
				plan.setMovie_title(rs.getString("movie_title"));
				plan.setRoom_id(rs.getInt("room_id"));
				plan.setBegin_time(df.format(rs.getTimestamp("begin_time")));
				plan.setPrice(rs.getInt("price"));
				plan.setPlan_id(rs.getInt("plan_id"));
				list.add(plan);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//根据演出厅查找演出计划
	public List<Plan> searchplanbyroom(int room_id){
		Connection conn=getConn();
		String sql="select * from plan where room_id='" + room_id + "'";
		List<Plan> list = new ArrayList<Plan>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Statement st = conn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				Plan plan=new Plan();
				plan.setMovie_title(rs.getString("movie_title"));
				plan.setRoom_id(rs.getInt("room_id"));
				plan.setBegin_time(df.format(rs.getTimestamp("begin_time")));//DATETIME类型
				plan.setPrice(rs.getInt("price"));
				plan.setPlan_id(rs.getInt("plan_id"));
				list.add(plan);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//根据id查找演出计划
	public Plan searchplanbyid(int plan_id){
		Connection conn=getConn();
		String sql="select * from plan where plan_id=" + plan_id;
		Plan plan=new Plan();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Statement st = conn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				plan.setMovie_title(rs.getString("movie_title"));
				plan.setRoom_id(rs.getInt("room_id"));
				plan.setBegin_time(df.format(rs.getTimestamp("begin_time")));//DATETIME类型
				plan.setPrice(rs.getInt("price"));
				plan.setPlan_id(rs.getInt("plan_id"));
			}
			return plan;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//列出所有演出计划
	public List<Plan> listAllPlan () {
		Connection conn=getConn();
		String sql="select * from plan";
		List<Plan> list = new ArrayList<Plan>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Statement st = conn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				Plan plan=new Plan();
				plan.setMovie_title(rs.getString("movie_title"));
				plan.setRoom_id(rs.getInt("room_id"));
				plan.setBegin_time(df.format(rs.getTimestamp("begin_time")));//DATETIME类型
				plan.setPrice(rs.getInt("price"));
				plan.setPlan_id(rs.getInt("plan_id"));
				list.add(plan);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//按照计划id删除场次
	public void deleteplan(int plan_id){
		Connection conn=getConn();
		String sql="delete from plan where plan_id=" + plan_id;
		try {
			Statement stmt=conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//判断两个演出计划是否相同
public boolean isSame(int room_id,  String begin_time){
			Connection conn=getConn();
			String sql="select * from plan where room_id="+ room_id+
					" and begin_time='" +begin_time+ "';";
			try {
				Statement st = conn.createStatement();
				ResultSet rs=st.executeQuery(sql);
				while(rs.next()){
						return true;
				}
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
}

	//判断是否有时间冲突
	public boolean isTimeConflict(Plan plan) {
		Connection conn=getConn();
		//判断room_id是否相同
		String sql1 = "select distinct room.room_id from room,plan where room.room_id = " + "room.room_id = "
				+ plan.getRoom_id() + ";";
		//判断影片名是否相同，并获取该影片的时长
		String sql2 = "select distinct movies.spend_time from movies,plan where movies.title = '" + plan.getMovie_title() + "';";
		//给出该计划与所有计划的时间间隔
		String sql3 = "select abs((time_to_sec(timediff(begin_time, '" + plan.getBegin_time() + "')))) from plan;";
		try{
		Statement st = conn.createStatement();
		ResultSet rs1=st.executeQuery(sql1);
		if (rs1 == null) {
			System.out.println("room_id找不到");
			return true;
		}
		ResultSet rs2=st.executeQuery(sql2);
		 //spend_time_sec存储以秒为单位的电影放映与清场的时间总和
		int spend_time_sec = 0;
		while(rs2.next()){
			spend_time_sec = 60 * Integer.parseInt(rs2.getString("spend_time").substring(0, rs2.getString("spend_time").length()-3));
			spend_time_sec += 60 * 10;
		}
		 //判断是否出现时间冲突
		 ResultSet rs3=st.executeQuery(sql3);
		 while(rs3.next()) {
			 if(spend_time_sec > rs3.getInt(1)) {
				 //System.out.println("时间冲突");
				 return true;
			 }
		 }
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
}
