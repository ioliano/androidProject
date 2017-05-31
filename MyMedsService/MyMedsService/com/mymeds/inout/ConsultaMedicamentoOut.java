package com.mymeds.inout;

import java.io.Serializable;
import java.util.List;

import com.mymeds.objects.RlUtilizadorMedicamento;

public class ConsultaMedicamentoOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<RlUtilizadorMedicamento> listaMedicamentos;

	public List<RlUtilizadorMedicamento> getListaMedicamentos() {
		return listaMedicamentos;
	}

	public void setListaMedicamentos(List<RlUtilizadorMedicamento> listaMedicamentos) {
		this.listaMedicamentos = listaMedicamentos;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
