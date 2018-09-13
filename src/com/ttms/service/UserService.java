package com.ttms.service;

import com.ttms.dao.UserDAO;
import com.ttms.domain.User;

public class UserService {
	private UserDAO userdao=new UserDAO();
	/*
	 * 注册功能：
	 * 1.使用用户名查询，如果返回null，完成添加
	 * 2.如果不是null，抛出异常
	 */
	public void regist(User user) throws UserException{
		User _user=userdao.findbyusername(user.getUsername());
		if(_user!=null) 
			throw new UserException("用户名" + _user.getUsername() + "已经被注册过了！");
			userdao.add(user);
	}
	
	/*
	 * 登录功能
	 * 1.使用用户名查找，返回User对象
	 * 2.如果user为null，则抛出异常显示用户不存在
	 * 3.如果user不为null，获取user中的password和form中的password比较，如果不同则抛出异常
	 * 4.如果相同则返回查询结果
	*/
	public User login(User user) throws UserException{
		User _user=userdao.findbyusername(user.getUsername());
		if(_user==null)
			throw new UserException("用户" + user.getUsername() + "不存在");
		if(!user.getPassword().equalsIgnoreCase(_user.getPassword()))
			throw new UserException("密码错误");
		return _user;
	}
}
