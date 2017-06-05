package com.mymeds.inout;

import java.io.Serializable;

import com.mymeds.objects.RlUtilizadorMedicamento;

public class DeleteMedicamentoOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Boolean sucesso;
	
	public DeleteMedicamentoOut() {
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
