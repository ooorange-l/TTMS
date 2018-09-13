package com.ttms.service;

import java.util.List;

import com.ttms.dao.MovieDAO;
import com.ttms.domain.Movie;
import com.ttms.domain.Room;

public class MovieService {
	private MovieDAO moviedao=new MovieDAO();
	/*
	 * 增加电影
	 * 删除电影
	 * 查找电影（以及相关的演出计划）
	 */
	public void addmovie(Movie movie) throws UserException{
		Movie _movie=moviedao.findbytitle(movie.getTitle());
		if(_movie!=null)
			throw new UserException("该电影已存在");
			moviedao.add(movie);
	}
	public void deletemovie(String title) throws UserException{
		Movie _movie=moviedao.findbytitle(title);
		if(_movie==null) throw new UserException("该电影不存在");
		moviedao.delete(title);
	}
	public List<Movie> getAllMovie () throws UserException {
		List<Movie> _movies = moviedao.findallmovie();
		return _movies;
	}
	public List<Movie> fuzzyMovie(String title){
		List<Movie> list=moviedao.findbytitlefuzzy(title);
		return list;
	}
}

