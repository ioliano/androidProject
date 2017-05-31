package com.mymeds.inout;

import java.io.Serializable;

public class UpdateUtilizadorIn implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String emailUtilizador;
	private String passUtilizador;
	
	
	public UpdateUtilizadorIn() {
	}
	
	
	public String getPassUtilizador() {
		return passUtilizador;
	}

	public void setPassUtilizador(String passUtilizador) {
		this.passUtilizador = passUtilizador;
	}

	public String getEmailUtilizador() {
		return emailUtilizador;
	}
	public void setEmailUtilizador(String emailUtilizador) {
		this.emailUtilizador = emailUtilizador;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
}
