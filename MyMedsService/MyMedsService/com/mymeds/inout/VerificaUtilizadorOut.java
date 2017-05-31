package com.mymeds.inout;

import java.io.Serializable;

import com.mymeds.objects.Utilizador;

public class VerificaUtilizadorOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Boolean existe;
	
	public VerificaUtilizadorOut() {
	}

	public Boolean getExiste() {
		return existe;
	}

	public void setExiste(Boolean existe) {
		this.existe = existe;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
