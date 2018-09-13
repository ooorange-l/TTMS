package com.ttms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttms.domain.Room;
import com.ttms.service.RoomService;
import com.ttms.service.UserException;

public class AddRoomServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 增加演出厅
		 * 将前端表单数据存入_room中
		 * 将数据存入数据库room表中
		 */		
		
		Room _room=new Room();
//		System.out.println(request.getParameter("ID"));
//		System.out.println(request.getParameter("rows"));
//		System.out.println(request.getParameter("cols"));
		_room.setId(Integer.parseInt(request.getParameter("ID")));
		_room.setRow(Integer.parseInt(request.getParameter("rows")));
		_room.setLine(Integer.parseInt(request.getParameter("cols")));
		_room.setIntro(request.getParameter("description"));
		
		RoomService admin=new RoomService();
		try {
			admin.addroom(_room);
			response.getWriter().print(true);
		} catch (UserException e) {
			response.getWriter().print(false);
		}
		
	}

}
