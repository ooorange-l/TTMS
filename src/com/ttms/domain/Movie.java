package com.ttms.domain;

public class Movie {
	private String title;//标题
    private String time;//上映时间
    private String country;//国家
    private String lan;//语言
    private String type;//类型
    private String score;//评分
    private String spend_time;//片长
    private String director;//导演
    private String main_actor;//主演
    private String img_url;//图片
    private String introduction;//介绍
    private int flag;		//0为未上映，1为已上映
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLan() {
		return lan;
	}
	public void setLan(String lan) {
		this.lan = lan;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getSpend_time() {
		return spend_time;
	}
	public void setSpend_time(String spend_time) {
		this.spend_time = spend_time;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getMain_actor() {
		return main_actor;
	}
	public void setMain_actor(String main_actor) {
		this.main_actor = main_actor;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
    
    
}
