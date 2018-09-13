package com.ttms.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttms.domain.Plan;
import com.ttms.service.PlanService;
import com.ttms.service.UserException;

import net.sf.json.JSONArray;

public class SearchPlanByRoomServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        PlanService planservice=new PlanService();
        int room_id=Integer.parseInt(request.getParameter("ID"));
        try {
            List<Plan> list=planservice.getplanbyroom(room_id);
            String json= JSONArray.fromObject(list).toString();
            response.getWriter().print(json);
        } catch (UserException e) {
            request.setAttribute("error",e.getMessage());
            response.getWriter().print(false);
        }
    }
}
