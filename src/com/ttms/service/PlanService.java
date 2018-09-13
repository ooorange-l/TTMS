package com.ttms.service;

import java.util.List;

import com.ttms.dao.PlanDAO;
import com.ttms.domain.Plan;

public class PlanService {
	//设置演出计划
	public void setplan(Plan plan) throws UserException{
		PlanDAO plandao=new PlanDAO();	
		if(plandao.isSame(plan.getRoom_id(), plan.getBegin_time()) ==true) {
			throw new UserException("该演出计划已存在！");
		}
		if(plandao.isTimeConflict(plan) == true) {
			throw new UserException("插入计划时存在时间冲突问题！");
		}
		plandao.setplan(plan);
		
	}
	
	//根据电影名查找演出计划
	public List<Plan> getplanbytitle(String title) throws UserException{
		PlanDAO plandao=new PlanDAO();
		List<Plan> list=plandao.searchplanbymovie(title);
		if(list==null) throw new UserException("该电影没有安排演出计划！");
		return list;
	}

	//根据演出厅查找演出计划
	public List<Plan> getplanbyroom(int room_id) throws UserException{
		PlanDAO plandao=new PlanDAO();
		List<Plan> list=plandao.searchplanbyroom(room_id);
		if(list==null) throw new UserException("该电影没有安排演出计划！");
		return list;
	}

	//列出所有演出计划
	public List<Plan> getAllPlan() throws UserException{
		PlanDAO plandao = new PlanDAO();
		List<Plan> list = plandao.listAllPlan();
		return list;
	}
	
	//根据计划id删除演出计划
	public void deleteplan(int id) throws UserException{
		PlanDAO plandao=new PlanDAO();
		Plan plan=plandao.searchplanbyid(id);
		if(plan==null) throw new UserException("该演出计划不存在");
		plandao.deleteplan(id);
		
	}
	
}
