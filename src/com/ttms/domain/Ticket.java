package com.ttms.domain;

public class Ticket {
	private int ticket_id;
	private String user_name;
	private int seat_id;
	private int plan_id;
	private int ticket_price;
	private int ticket_status;
	private long ticket_locked_time;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}
	public int getSeat_id() {
		return seat_id;
	}
	public void setSeat_id(int seat_id) {
		this.seat_id = seat_id;
	}
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	public int getTicket_price() {
		return ticket_price;
	}
	public void setTicket_price(int ticket_price) {
		this.ticket_price = ticket_price;
	}
	public int getTicket_status() {
		return ticket_status;
	}
	public void setTicket_status(int ticket_status) {
		this.ticket_status = ticket_status;
	}
	public long getTicket_locked_time() {
		return ticket_locked_time;
	}
	public void setTicket_locked_time(long ticket_locked_time) {
		this.ticket_locked_time = ticket_locked_time;
	}
	
}
