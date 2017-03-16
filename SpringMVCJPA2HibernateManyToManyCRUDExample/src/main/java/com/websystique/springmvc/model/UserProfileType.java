package com.websystique.springmvc.model;

public enum UserProfileType {
//	USER("USER"),
//	DBA("DBA"),
//	ADMIN("ADMIN"),
	ASD("ASD");
	
	String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
