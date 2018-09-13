package com.ttms.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttms.domain.Movie;
import com.ttms.service.MovieService;

import net.sf.json.JSONArray;

public class SearchMovieServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		MovieService movieservice=new MovieService();
		String title=request.getParameter("title");
		List<Movie> list=movieservice.fuzzyMovie(title);
		String json=JSONArray.fromObject(list).toString();
		response.getWriter().print(json);
	}

}
