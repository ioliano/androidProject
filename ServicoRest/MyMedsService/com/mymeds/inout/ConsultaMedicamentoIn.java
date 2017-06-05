package com.mymeds.inout;

import java.io.Serializable;
import java.util.Date;

public class ConsultaMedicamentoIn implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idUtilizador;
	private Long idMedicamento;
//	private Date dataInicio;
	
	
	public ConsultaMedicamentoIn() {
	}
	
	public Long getIdMedicamento() {
		return idMedicamento;
	}

	public void setIdMedicamento(Long idMedicamento) {
		this.idMedicamento = idMedicamento;
	}
	
	public Long getIdUtilizador() {
		return idUtilizador;
	}

	public void setIdUtilizador(Long idUtilizador) {
		this.idUtilizador = idUtilizador;
	}

//	public Date getDataInicio() {
//		return dataInicio;
//	}
//
//	public void setDataInicio(Date dataInicio) {
//		this.dataInicio = dataInicio;
//	}
	
	@Override
	public String toString() {
		return super.toString();
	}


}
