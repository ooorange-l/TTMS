package com.ttms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttms.domain.Plan;
import com.ttms.domain.Room;
import com.ttms.service.PlanService;
import com.ttms.service.RoomService;
import com.ttms.service.UserException;

import net.sf.json.JSONArray;

public class ListPlanServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		List<Plan> _plans=new ArrayList<Plan>();
		PlanService admin=new PlanService();
						 
		try {
			_plans = admin.getAllPlan();
			String json = JSONArray.fromObject(_plans).toString();

			response.getWriter().print(json);
		} catch (UserException e) {
				response.getWriter().print(false);
			}
			
	}

}
