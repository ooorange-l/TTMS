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

import net.sf.json.JSONObject;

public class SearchRoomServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		int id=Integer.parseInt(request.getParameter("ID"));
		
		Room _room=new Room();
		RoomService admin=new RoomService();
		try {
			_room=admin.findbyroomid(id);
			JSONObject json=new JSONObject();
			json.put("ID",_room.getId());
			json.put("rows",_room.getRow());
			json.put("cols",_room.getLine());
			json.put("description",_room.getIntro());
			System.out.println("  ID:" + _room.getId() + "  description:" + _room.getIntro() );
			response.getWriter().print(json);
		} catch (UserException e) {
			JSONObject json=new JSONObject();
			json.put("id","null");
			response.getWriter().print(json);
		}
	}

}
