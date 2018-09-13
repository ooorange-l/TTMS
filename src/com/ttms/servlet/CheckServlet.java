package com.ttms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttms.domain.User;
import com.ttms.service.BuyService;
import com.ttms.service.UserException;

public class CheckServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		int plan_id=Integer.parseInt(request.getParameter("plan_id"));
		BuyService buy=new BuyService();
		int row=Integer.parseInt(request.getParameter("row"));
		int line=Integer.parseInt(request.getParameter("line"));
		User user=(User)request.getSession().getAttribute("SessionUser");
		String username=null;
		username=user.getUsername();
		
		
		try {
			buy.checkTicket(plan_id, row, line, username);
			response.getWriter().print(true);
		} catch (UserException e) {
			response.getWriter().print(false);
			e.printStackTrace();
		}
	}
}
