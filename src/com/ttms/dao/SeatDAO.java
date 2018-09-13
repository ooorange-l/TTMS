package com.ttms.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;
import com.ttms.domain.Ticket;

public class SeatDAO {
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

	/*
	 * 根据ticket的plan_id查找seat_id
	 * 1.根据plan_id寻找room_id
	 * 2.根据room_id寻找seat_id
	 */	
	public int findSeatid(int plan_id,int row,int line){
		int roomid=-1;
		int seatid=-1;
		Connection conn=getConn(); 
		String sql="select room_id from plan where plan_id=" + plan_id;
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs1=stmt.executeQuery(sql);
			while(rs1.next()){
				roomid=rs1.getInt("room_id");
			}
			ResultSet rs2=stmt.executeQuery("select seat_id from seat where room_id=" + roomid
					+ " and seat_row=" + row
					+ " and seat_line=" + line);
			while(rs2.next()){
				seatid=rs2.getInt("seat_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seatid;
	}
	/*
	 * 根据ticket的plan_id查找row
	 */	
	public int findSeatRow(int plan_id){
		int roomid=-1;
		int row = 0;
		Connection conn=getConn(); 
		String sql="select room_id from plan where plan_id=" + plan_id;
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs1=stmt.executeQuery(sql);
			while(rs1.next()){
				roomid=rs1.getInt("room_id");
			}
			ResultSet rs2=stmt.executeQuery("select room_row from room where room_id=" + roomid);
			while(rs2.next()){
				row=rs2.getInt("room_row");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	/*
	 * 根据ticket的plan_id查找line
	 */	
	public int findSeatLine(int plan_id){
		int roomid=-1;
		int line = 0;
		Connection conn=getConn(); 
		String sql="select room_id from plan where plan_id=" + plan_id;
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs1=stmt.executeQuery(sql);
			while(rs1.next()){
				roomid=rs1.getInt("room_id");
			}
			ResultSet rs2=stmt.executeQuery("select room_line from room where room_id=" + roomid);
			while(rs2.next()){
				line=rs2.getInt("room_line");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return line;
	}
	
	//根据plan_id获得price
	public int findPrice(int plan_id){
		Connection conn=getConn(); 
		int price=-1;
		String sql="select price from plan where plan_id=" + plan_id;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				price=rs.getInt("price");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return price;
	}
	
	
	//查找座位情况status（1为可用0为不可用）
	public int findSeatStatus(int seatid){
		int status=0;
		Connection conn=getConn();
		String sql="select seat_status from seat where seat_id" + seatid;
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				status=rs.getInt("seat_status");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	//每次设置演出厅时设置座位
	public void setSeat(int room_id,int row,int line){
		int i,j;
		Connection conn=getConn();
				try {
					Statement st=conn.createStatement();
					for(i=1;i<=row;i++){
						for(j=1;j<=line;j++){
							st.executeUpdate("insert into seat(room_id,seat_row,seat_line,seat_status) values(" +
							room_id + "," + i + "," + j + "," + 1 + ")");
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
		}
		
	}
}
