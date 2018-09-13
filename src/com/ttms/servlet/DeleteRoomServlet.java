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

public class DeleteRoomServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		int id=Integer.parseInt(request.getParameter("ID"));
		
		RoomService admin=new RoomService();
		try {
			admin.deleteroom(id);
			response.getWriter().print(true);
		} catch (UserException e) {
			request.setAttribute("error",e.getMessage());
			response.getWriter().print(false);
		}
	}

}
