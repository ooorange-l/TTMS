package com.ttms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttms.domain.Room;
import com.ttms.service.RoomService;
import com.ttms.service.UserException;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

public class ListRoomServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		List<Room> _rooms=new ArrayList<Room>();
		RoomService admin=new RoomService();
						 
		try {
			_rooms = admin.getAllRoom();
			
			
			String json = JSONArray.fromObject(_rooms).toString();

			response.getWriter().print(json);
		} catch (UserException e) {
				response.getWriter().print(false);
			}
			
	}
}


