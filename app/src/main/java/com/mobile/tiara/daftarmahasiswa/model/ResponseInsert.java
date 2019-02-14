package com.mobile.tiara.daftarmahasiswa.model;

import com.google.gson.annotations.SerializedName;


public class ResponseInsert{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("status")
	private int status;

	public String getPesan(){
		return pesan;
	}

	public int getStatus(){
		return status;
	}
}