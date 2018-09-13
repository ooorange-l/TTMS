package com.ttms.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ttms.dao.SeatDAO;
import com.ttms.dao.TicketDAO;
import com.ttms.domain.Ticket;

public class BuyService {
	/*
	 * 买票
	 * 1.如果seat有问题（0），无法购票
	 * 2.如果seat没问题（1），ticket状态为0，可以买，将该信息存入ticket
	 * 3.如果seat没问题，ticket状态为1，先检测当前时间戳和该票时间戳是否超过15分钟，如果没有超过，
	 * 		检测user_name是否和之前一致，如果一致则确认买票，ticket设置为9。不一致则抛出异常
	 * 4.如果seat没问题，ticket状态为1，先检测当前时间戳和该票时间戳是否超过15分钟，如果已经超过，
	 * 		将当前user_name替换为当前username，时间锁设置为当前时间
	*/
	public void buyTicket(int plan_id,int row,int line,String username) throws UserException{
		SeatDAO seatdao=new SeatDAO();
		TicketDAO ticketdao=new TicketDAO();
		Ticket ticket=new Ticket();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		int seatid=seatdao.findSeatid(plan_id,row,line);
		int price=seatdao.findPrice(plan_id);
		ticket.setSeat_id(seatid);
		ticket.setPlan_id(plan_id);
		ticket.setTicket_price(price);
		long time=ticketdao.findTickettime(ticket);
		ticket.setTicket_locked_time(time);
		int ticket_status=ticketdao.findTicketStatus(ticket);
		ticket.setUser_name(username);
		ticket.setTicket_locked_time(System.currentTimeMillis());
		
		/*
		 * 查看该票时间戳和当前时间
		 * (min为票的时间戳和当前时间戳相差的分钟数)
		 * 如果min>15意味着相差超过了15分钟，将该票的时间锁设置为当前时间
		 */
		long min=(System.currentTimeMillis()-ticket.getTicket_locked_time())/(1000*60);
//		if(ticket_status==0||String.valueOf(ticket_status)==null||(ticket_status==1&&min>15)){
		/*if(String.valueOf(ticket_status)==null){
			System.out.println("111111111111111111111");
			ticketdao.addTicket(ticket);
		}*/
		if(ticket_status==9){
			throw new UserException("该座位已售出");
		}else if(ticket_status==1&&min>15){
			ticketdao.updateTicket(ticket);
		}else if(ticket_status==1&&min<=15){
			String user=ticketdao.findTicketUser(ticket);
			if(user.equalsIgnoreCase(username)){
				ticketdao.sureTicket(ticket);
		}
		}else {
			ticketdao.addTicket(ticket);
		}
	}
	
	
	//首先检测所有座位情况
	public void checkTicket(int plan_id,int row,int line,String username) throws UserException{
		SeatDAO seatdao=new SeatDAO();
		TicketDAO ticketdao=new TicketDAO();
		Ticket ticket=new Ticket();
		
		int seatid=seatdao.findSeatid(plan_id,row,line);
		int price=seatdao.findPrice(plan_id);
		
		ticket.setSeat_id(seatid);
		ticket.setPlan_id(plan_id);
		ticket.setTicket_price(price);
		long time=ticketdao.findTickettime(ticket);
		ticket.setTicket_locked_time(time);
		int ticket_status=ticketdao.findTicketStatus(ticket);
		
		long min=(System.currentTimeMillis()-ticket.getTicket_locked_time())/(1000*60);
		
		if(ticket_status==1&&min<15){
			String user=ticketdao.findTicketUser(ticket);
			if(!user.equalsIgnoreCase(username))throw new UserException("该票已被订购！");
		}
	}
}
