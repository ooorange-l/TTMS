package com.ttms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttms.domain.Room;
import com.ttms.service.RoomService;
import com.ttms.service.UserException;

public class UpdateRoomServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		Room _room=new Room();
		_room.setId(Integer.parseInt(request.getParameter("ID")));
		_room.setRow(Integer.parseInt(request.getParameter("rows")));
		_room.setLine(Integer.parseInt(request.getParameter("cols")));
		_room.setIntro(request.getParameter("description"));
		
		RoomService admin=new RoomService();
		try {
			admin.updateroom(_room);
			response.getWriter().print(true);
		} catch (UserException e) {
			response.getWriter().print(false);
		}
	}

}
