package com.ttms.service;

import java.util.List;

import com.ttms.dao.MovieDAO;
import com.ttms.domain.Movie;
import com.ttms.domain.Page;

public class PageService {
	public Page<Movie> findmoviebypage(int pagenum,int pagesize){
		
		/*
		 * 获得分页数据
		 * 1.依赖moviedao对象的findallmovie方法获得所有数据
		 * 2.获得所有数据的条数（amount）
		 * 3.使用三个参数构造page对象
		 * 4.调用moviedao的findpage方法获得当前分页的数据并返回
		 */	
		MovieDAO moviedao=new MovieDAO();
		List<Movie> allmovie=moviedao.findallmovie();
		int amount=allmovie.size();
		
		Page<Movie> page=new Page<Movie>(pagenum,pagesize,amount);
		int startindex=page.getStartindex();
		page.setList(moviedao.fingpage(startindex, pagesize)); 
		return page;
	}
	
}
