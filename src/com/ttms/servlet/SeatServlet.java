package com.ttms.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttms.domain.Seat;
import com.ttms.service.SeatService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SeatServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		int plan_id=Integer.parseInt(request.getParameter("plan_id"));
		SeatService seatservice=new SeatService();
		
		
		int row=seatservice.Row(plan_id);
		int line=seatservice.Line(plan_id);
		int price=seatservice.Price(plan_id);
		
		JSONObject obj = new JSONObject();
		obj.put("row", row);
		obj.put("line", line);
		obj.put("price", price);  
		
		response.getWriter().print(obj);
	}

}
