package com.ttms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttms.domain.Seat;
import com.ttms.service.SeatService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AllSeatServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		int plan_id=Integer.parseInt(request.getParameter("plan_id"));
		SeatService seatservice=new SeatService();
		List<Seat> list=seatservice.AllSeat(plan_id);
		
		int row=seatservice.Row(plan_id);
		int line=seatservice.Line(plan_id);
		int[][] seats=new int[row+1][line+1];
		for(int i=1;i<=row;i++){
			for(int j=1;j<=line;j++){
				seats[i][j]=0;
			}
		}
		for(int i = 0 ; i < list.size() ; i++) {
			  seats[list.get(i).getSeat_row()][list.get(i).getSeat_line()]=1;
		}
		
		System.out.println("6666666666666666666666666");
		System.out.println(seats[3][1]);
		//将所有座位信息转化成json格式字符串传给前端
				StringBuffer sb = new StringBuffer();
				boolean first = true;
				sb.append("[");
				for(int i=1;i<=row;i++){
					for(int j=1;j<=line;j++){
						 if (!first) {
							 sb.append(",");
						 }
						 sb.append("{");
						 sb.append("\"row\":" + i + ",");
						 sb.append("\"line\":" + j + ",");
						 sb.append("\"status\":" + seats[i][j]);
						 sb.append("}");
						 first = false;
					}
				}
				 sb.append("]");
				 System.out.println(sb);
				 JSONArray json=JSONArray.fromObject(sb.toString());
				 response.getWriter().print(json);
				 System.out.println(json);
	}

}
