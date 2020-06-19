package com.yzl.pojo.dto;

import java.util.UUID;

public  class UUIDS {

	private static String id = "fx";
	
	private static UUIDS uuids = new UUIDS();
	
	public UUIDS() {
		id = UUID.randomUUID().toString();
	}
	

	public String getId() {
		return id;
	}

	public static void setId() {
		
		UUID randomUUID = UUID.randomUUID();
		id = randomUUID.toString();
	}
	
}
