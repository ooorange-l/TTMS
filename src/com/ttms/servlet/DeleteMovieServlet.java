package com.ttms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttms.service.MovieService;
import com.ttms.service.UserException;

public class DeleteMovieServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String title=request.getParameter("title");
		
		MovieService movieservice=new MovieService();
		try {
			movieservice.deletemovie(title);
			response.getWriter().print(true);
		} catch (UserException e) {
			response.getWriter().print(e.getMessage());
		}
	}

}
