package com.ttms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttms.domain.User;
import com.ttms.service.UserException;
import com.ttms.service.UserService;

import net.sf.json.JSONObject;

public class RegistServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			
			//封装json成_user
			UserService userservice=new UserService();
			User _user=new User();
			_user.setUsername(request.getParameter("username"));
			_user.setPassword(request.getParameter("password"));
			
			try {
				userservice.regist(_user);
				response.getWriter().print(true);
			} catch (UserException e) {
				request.setAttribute("error", e.getMessage());
				response.getWriter().print(false);
			}
	}

}
