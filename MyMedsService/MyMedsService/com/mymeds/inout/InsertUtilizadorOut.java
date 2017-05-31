package com.mymeds.inout;

import java.io.Serializable;

public class InsertUtilizadorOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idUtilizador;

	public InsertUtilizadorOut() {
	}
	
	public Long getIdUtilizador() {
		return idUtilizador;
	}

	public void setIdUtilizador(Long idUtilizador) {
		this.idUtilizador = idUtilizador;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
