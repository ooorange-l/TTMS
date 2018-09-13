package com.ttms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttms.domain.Movie;
import com.ttms.domain.Room;
import com.ttms.service.MovieService;
import com.ttms.service.RoomService;
import com.ttms.service.UserException;

import net.sf.json.JSONArray;

/*列出所有上架电影的名称*/
public class ListMovieServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		List<Movie> _movies=new ArrayList<Movie>();
		List<Movie> _movies_new = new ArrayList<Movie>();
		MovieService admin=new MovieService();
						 
		try {
			_movies = admin.getAllMovie();
			for (Movie e : _movies) {
				if(e.getFlag() == 1){
					_movies_new.add(e);
				}
			}
			
			String json = JSONArray.fromObject(_movies_new).toString();

			response.getWriter().print(json);
		} catch (UserException e) {
				response.getWriter().print(false);
			}
			
	}

}
