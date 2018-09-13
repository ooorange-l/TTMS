package com.ttms.service;

import java.util.ArrayList;
import java.util.List;

import com.ttms.dao.SeatDAO;
import com.ttms.dao.TicketDAO;
import com.ttms.domain.Seat;

public class SeatService {
		public List<Seat> AllSeat(int plan_id){
			TicketDAO ticketdao=new TicketDAO();
			List<Seat> list=ticketdao.findAllSeat(plan_id);
			return list;
		}
		public int Row(int plan_id){
			SeatDAO seatdao=new SeatDAO();
			int row=seatdao.findSeatRow(plan_id);
			return row;
		}
		public int Line(int plan_id){
			SeatDAO seatdao=new SeatDAO();
			int line=seatdao.findSeatLine(plan_id);
			return line;
		}
		public int Price(int plan_id){
			SeatDAO seatdao=new SeatDAO();
			int price=seatdao.findPrice(plan_id);
			return price;
		}
}
