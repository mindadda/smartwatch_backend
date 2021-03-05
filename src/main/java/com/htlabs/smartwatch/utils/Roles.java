package com.htlabs.smartwatch.utils;

public enum Roles {

	ADMIN("ROLE_ADMIN"),USER("ROLE_USER");
	
	
	private String roleValue;
	
	private Roles(String roleValue) {
		this.roleValue=roleValue;
	}
	
	public String getRoleValue() {
		return roleValue;
	}

}
