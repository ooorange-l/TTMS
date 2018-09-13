package com.ttms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttms.domain.Movie;
import com.ttms.service.MovieService;
import com.ttms.service.UserException;

public class AddMovieServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		Movie movie=new Movie();
		movie.setTitle(request.getParameter("title"));
		movie.setTime(request.getParameter("time"));
		movie.setCountry(request.getParameter("country"));
		movie.setLan(request.getParameter("lan"));
		movie.setType(request.getParameter("type"));
		movie.setScore(request.getParameter("score"));
		movie.setSpend_time(request.getParameter("spend_time"));
		movie.setDirector(request.getParameter("director"));
		movie.setMain_actor(request.getParameter("main_actor"));
		movie.setImg_url(request.getParameter("img_url"));
		movie.setDirector(request.getParameter("director"));
		
		MovieService movieservice=new MovieService();
		try {
			movieservice.addmovie(movie);
			response.getWriter().print(true);
		} catch (UserException e) {
			request.setAttribute("error",e.getMessage());
			response.getWriter().print(false);
		}
		
		
	}

}
