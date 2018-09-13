package com.ttms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttms.service.PlanService;
import com.ttms.service.UserException;

public class DeletePlanServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		int id=Integer.parseInt(request.getParameter("ID"));
		
		PlanService planservice=new PlanService();
		try {
			planservice.deleteplan(id);
			response.getWriter().print(true);
		} catch (UserException e) {
			request.setAttribute("error",e.getMessage());  
			response.getWriter().print(false);
		}
	}

}
