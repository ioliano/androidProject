package com.mymeds.inout;

import java.io.Serializable;

public class VerificaUtilizadorIn implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String emailUtilizador;
	
	public VerificaUtilizadorIn() {
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
