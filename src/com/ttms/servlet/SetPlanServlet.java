package com.ttms.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttms.domain.Plan;
import com.ttms.service.PlanService;
import com.ttms.service.UserException;


public class SetPlanServlet extends HttpServlet {


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PlanService planservice=new PlanService();
		Plan plan=new Plan();
		plan.setMovie_title(request.getParameter("name"));
		plan.setRoom_id(Integer.parseInt(request.getParameter("room-ID")));
		String year_month_day = request.getParameter("time").replace('/','-');
		String hour = request.getParameter("hour").replace("时","");
		String min = request.getParameter("minute").replace("分","");
		String date = year_month_day + " " + hour + ":" + min + ":00";
		plan.setBegin_time(date);//设置begin_time（datetime格式的String类型）
		plan.setPrice(Integer.parseInt(request.getParameter("price")));
		
		try {
			planservice.setplan(plan);
			response.getWriter().print(true);
		} catch (UserException e) {
			request.setAttribute("error",e.getMessage());
			response.getWriter().print(false);
		}
		
	}

}
