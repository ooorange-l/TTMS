package com.ttms.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttms.domain.User;
import com.ttms.service.BuyService;
import com.ttms.service.UserException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BuyServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		BuyService buy=new BuyService();
		
		int plan_id=Integer.parseInt(request.getParameter("plan_id"));
		int row=Integer.parseInt(request.getParameter("row"));
		int line=Integer.parseInt(request.getParameter("line"));
		
		User user=(User)request.getSession().getAttribute("SessionUser");
		String username=null;
        username=user.getUsername();
        try {
			buy.buyTicket(plan_id, row, line,username);
			response.getWriter().print(true);
		} catch (UserException e) {
			response.getWriter().print(false);
			request.setAttribute("error",e.getMessage());
		}
		
		
	}
}
