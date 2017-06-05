package com.mymeds.inout;

import java.io.Serializable;

public class InsertMedicamentoOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idRlUtilizadorMedicamento;

	public InsertMedicamentoOut() {
	}
	
	public Long getIdRlUtilizadorMedicamento() {
		return idRlUtilizadorMedicamento;
	}

	public void setIdRlUtilizadorMedicamento(Long idRlUtilizadorMedicamento) {
		this.idRlUtilizadorMedicamento = idRlUtilizadorMedicamento;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
