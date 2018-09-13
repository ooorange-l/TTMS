package com.ttms.domain;

public class Seat {
	private int seat_id;
	private int room_id;
	private int seat_row;
	private int seat_line;
	private int seat_status;
	private int price;
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSeat_id() {
		return seat_id;
	}
	public void setSeat_id(int seat_id) {
		this.seat_id = seat_id;
	}
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	public int getSeat_row() {
		return seat_row;
	}
	public void setSeat_row(int seat_row) {
		this.seat_row = seat_row;
	}
	public int getSeat_line() {
		return seat_line;
	}
	public void setSeat_line(int seat_line) {
		this.seat_line = seat_line;
	}
	public int getSeat_status() {
		return seat_status;
	}
	public void setSeat_status(int seat_status) {
		this.seat_status = seat_status;
	}
	
}
