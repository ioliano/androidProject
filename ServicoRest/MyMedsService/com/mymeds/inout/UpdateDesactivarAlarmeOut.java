package com.mymeds.inout;

import java.io.Serializable;

import com.mymeds.objects.RlUtilizadorMedicamento;

public class UpdateDesactivarAlarmeOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Boolean sucesso;
	
	public UpdateDesactivarAlarmeOut() {
	}

	public Boolean getSucesso() {
		return sucesso;
	}

	public void setSucesso(Boolean sucesso) {
		this.sucesso = sucesso;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
