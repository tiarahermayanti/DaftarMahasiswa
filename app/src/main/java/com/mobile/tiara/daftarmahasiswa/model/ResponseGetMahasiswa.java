package com.mobile.tiara.daftarmahasiswa.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class ResponseGetMahasiswa{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("datanya")
	private List<DataMahasiswa> datanya;

	@SerializedName("status")
	private int status;

	public String getPesan(){
		return pesan;
	}

	public List<DataMahasiswa> getDatanya(){
		return datanya;
	}

	public int getStatus(){
		return status;
	}
}