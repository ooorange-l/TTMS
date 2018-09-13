package com.ttms.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttms.domain.Movie;
import com.ttms.domain.Page;
import com.ttms.domain.Plan;
import com.ttms.service.PageService;

import net.sf.json.JSONArray;

public class PageServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 1.获取当前页数
		 * 2.设置显示条数
		 * 3.根据pageservice的findmoviebypage方法获得list
		 * 4.将list存入request域中；
		*/
		int pagenum=Integer.valueOf(request.getParameter("pagenum"));
		int pagesize=4;
		PageService pageservice=new PageService();
		Page page=pageservice.findmoviebypage(pagenum, pagesize);
		List<Movie> list=page.getList();
		String json=JSONArray.fromObject(list).toString();
		response.getWriter().print(json);
		System.out.println(json);
	}

}
