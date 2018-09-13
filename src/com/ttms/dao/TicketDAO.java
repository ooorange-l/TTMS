package com.ttms.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.ttms.domain.Seat;
import com.ttms.domain.Ticket;

public class TicketDAO {
	
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
	
	//查看票的状态status
	public int findTicketStatus(Ticket ticket){
		Connection conn=getConn();
		int seat=ticket.getSeat_id();
		int plan=ticket.getPlan_id();
		int status=0;
		String sql="select ticket_status from ticket where seat_id=" + seat + " and plan_id=" + plan;
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				status=rs.getInt("ticket_status");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status; 
	}
	
	//查看票的用户信息
	public String findTicketUser(Ticket ticket){
		Connection conn=getConn();
		int seat=ticket.getSeat_id();
		int plan=ticket.getPlan_id();
		String user = null;
		String sql="select user_name from ticket where seat_id=" + seat + " and plan_id=" + plan;
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				user=rs.getString("user_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user; 
	}
	
	//查看票的时间戳
		public long findTickettime(Ticket ticket){
			Connection conn=getConn();
			int seat=ticket.getSeat_id();
			int plan=ticket.getPlan_id();
			long time=0;
			String sql="select ticket_locked_time from ticket where seat_id=" + seat + " and plan_id=" + plan;
			try {
				Statement st=conn.createStatement();
				ResultSet rs=st.executeQuery(sql);
				while(rs.next()){
					time=rs.getLong("ticket_locked_time");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return time; 
		}
		
	//根据plan_id查看所有已被购买座位信息并返回
	public List<Seat> findAllSeat(int plan_id){
		Connection conn=getConn();
		List list1=new ArrayList();
		List<Seat> list2=new ArrayList<Seat>();
		int seatid;
		try {
			Statement st=conn.createStatement();
			ResultSet rs3=st.executeQuery("select seat_id from ticket where plan_id=" + plan_id
					+ " and ticket_status=9");
			while(rs3.next()){
				seatid=rs3.getInt("seat_id");
				list1.add(seatid);
			}
			for(int i=0;i<list1.size();i++){
				ResultSet rs4=st.executeQuery("select seat_row,seat_line from seat where seat_id=" + list1.get(i));
				while(rs4.next()){
					Seat seat=new Seat();
					seat.setSeat_row(rs4.getInt("seat_row"));
					seat.setSeat_line(rs4.getInt("seat_line"));
					list2.add(seat);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list2;
	}
	
	
	//锁定票，其中status为1
	public void addTicket(Ticket ticket){
		Connection conn=getConn();
		String sql="insert into ticket(user_name,seat_id,plan_id,ticket_price,ticket_status,ticket_locked_time) values('" + 
					ticket.getUser_name() + "'," + 
					ticket.getSeat_id() + "," +
					ticket.getPlan_id() + "," +
					ticket.getTicket_price() + "," +
					1 + "," +
					ticket.getTicket_locked_time() + ")";
		try {
			Statement st=conn.createStatement();
			st.executeUpdate(sql); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//对某张被锁定的票改变其用户（user）
	public void updateTicket(Ticket ticket){
		Connection conn=getConn();
		String sql1="update ticket set user_name='" + ticket.getUser_name() + "' where seat_id=" + ticket.getSeat_id() 
		+ " and plan_id=" + ticket.getPlan_id();
		String sql2="update ticket set ticket_locked_time='" + ticket.getTicket_locked_time() + "' where seat_id=" + ticket.getSeat_id() 
		+ " and plan_id=" + ticket.getPlan_id();
		try {
			Statement st=conn.createStatement();
			st.executeUpdate(sql1); 
			st.executeUpdate(sql2); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//已付款，status为9
	public void sureTicket(Ticket ticket){
		Connection conn=getConn();
		String sql="update ticket set ticket_status=9 where seat_id=" + ticket.getSeat_id() 
		+ " and plan_id=" + ticket.getPlan_id();
		System.out.println(sql);
		try {
			Statement st=conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//退票
}
