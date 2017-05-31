package com.mymeds.inout;

import java.io.Serializable;

import com.mymeds.objects.RlUtilizadorMedicamento;

public class UpdateMedicamentoOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private RlUtilizadorMedicamento rlUtilizadorMedicamento;
	
	public UpdateMedicamentoOut() {
	}
	
	public RlUtilizadorMedicamento getRlUtilizadorMedicamento() {
		return rlUtilizadorMedicamento;
	}

	public void setRlUtilizadorMedicamento(RlUtilizadorMedicamento rlUtilizadorMedicamento) {
		this.rlUtilizadorMedicamento = rlUtilizadorMedicamento;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
