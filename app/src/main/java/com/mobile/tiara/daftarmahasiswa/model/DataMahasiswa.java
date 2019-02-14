package com.mobile.tiara.daftarmahasiswa.model;


import com.google.gson.annotations.SerializedName;


public class DataMahasiswa {

	@SerializedName("mahasiswa_jekel")
	private String mahasiswaJekel;

	@SerializedName("mahasiswa_email")
	private String mahasiswaEmail;

	@SerializedName("mahasiswa_jurusan")
	private String mahasiswaJurusan;

	@SerializedName("mahasiswa_nama")
	private String mahasiswaNama;

	@SerializedName("mahasiswa_id")
	private String mahasiswaId;

	@SerializedName("mahasiswa_nim")
	private String mahasiswaNim;

	public String getMahasiswaJekel(){
		return mahasiswaJekel;
	}

	public String getMahasiswaEmail(){
		return mahasiswaEmail;
	}

	public String getMahasiswaJurusan(){
		return mahasiswaJurusan;
	}

	public String getMahasiswaNama(){
		return mahasiswaNama;
	}

	public String getMahasiswaId(){
		return mahasiswaId;
	}

	public String getMahasiswaNim(){
		return mahasiswaNim;
	}
}