package com.ttms.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.ttms.domain.Room;

public class RoomDAO {
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
	
	
	/*
	 * 查找对应演出厅是否存在
	 * 如果存在返回该room对象
	 */	
	public Room findbyroomid(int id){
		Room room=new Room();
		Connection conn=getConn();
		String sql="select * from room where room_id=" + id;
			try {
				Statement st=conn.createStatement();
				ResultSet rs=st.executeQuery(sql);
				while(rs.next()){
					if(id==rs.getInt("room_id")){
						room.setId(rs.getInt("room_id"));
						room.setRow(rs.getInt("room_row"));
						room.setLine(rs.getInt("room_line"));
						room.setIntro(rs.getString("room_intro"));
						st.close();
						conn.close();
						return room;
					} 
				}
			}catch (Exception e) {
				e.printStackTrace();	
		}
			return null;
	}
	
	//添加演出厅,级联添加座位
	public void add(Room room){
		Connection conn=getConn();
		int id=room.getId();
		int row=room.getRow();
		int line=room.getLine();
		String intro=room.getIntro();
		String sql="insert into room(room_id,room_row,room_line,room_intro) values(" 
				+ id + "," + row + "," + line + ",'" + intro + "')";

		try {
			Statement st=conn.createStatement();
			st.executeUpdate(sql);
			int i = 0, j = 0;
			for (i = 1; i<= row; i++) {
				for (j = 1; j <= line; j++) {
					st.executeUpdate("insert into seat(room_id,seat_row,seat_line,seat_status) values(" +
							id + "," + i + "," + j + "," + 1 + ")");
				}
			}
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	
	//修改演出厅信息
	public void update(Room room){
		Connection conn=getConn();
		int id=room.getId();
		int row=room.getRow();
		int line=room.getLine();
		String intro=room.getIntro();
		String sql="update room set room_row=" 
					+ row + ",room_line=" + line + ",room_intro='" + intro
					+ "'where room_id=" + id;
		try {
			Statement st=conn.createStatement();
			st.executeUpdate(sql);
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	//删除演出厅，级联删除座位
	public void delete(int id){
		Connection conn=getConn();
		String sql="delete from room where room_id=" + id;
		try {
			Statement st=conn.createStatement();
			st.executeUpdate("set sql_safe_updates=0; ");
			/*st.executeUpdate("delete from room where plan.room_id=" +id + " and ticket.plan_id=plan.plan_id");
			st.executeUpdate("delete from seat where room_id=" + id + ";");
			st.executeUpdate("delete from plan where plan.room_id="+id);
*/			st.executeUpdate(sql);
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public List<Room> getAll() {
		List<Room> rooms = new ArrayList<Room>();
		Connection conn=getConn();
		String sql="select * from room";
			try {
				Statement st=conn.createStatement();
				ResultSet rs=st.executeQuery(sql);
				while(rs.next()){
						Room room = new Room();
						room.setId(rs.getInt("room_id"));
						room.setRow(rs.getInt("room_row"));
						room.setLine(rs.getInt("room_line"));
						room.setIntro(rs.getString("room_intro"));
						rooms.add(room);
				}
				st.close();
				conn.close();
				if(rooms.size() != 0)
					return rooms;
				else
					return null;
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	}
}
