package com.mymeds.inout;

import java.io.Serializable;
import java.util.Date;

public class InsertMedicamentoIn implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idUtilizador;
	private Long idMedicamento;
	private Date dataInicio;
	private Integer horasIntervalo;
	private Integer numDoses;

	public InsertMedicamentoIn() {
	}
	
	public Long getIdUtilizador() {
		return idUtilizador;
	}

	public void setIdUtilizador(Long idUtilizador) {
		this.idUtilizador = idUtilizador;
	}

	public Long getIdMedicamento() {
		return idMedicamento;
	}

	public void setIdMedicamento(Long idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Integer getHorasIntervalo() {
		return horasIntervalo;
	}

	public void setHorasIntervalo(Integer horasIntervalo) {
		this.horasIntervalo = horasIntervalo;
	}

	public Integer getNumDoses() {
		return numDoses;
	}

	public void setNumDoses(Integer numDoses) {
		this.numDoses = numDoses;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
}
