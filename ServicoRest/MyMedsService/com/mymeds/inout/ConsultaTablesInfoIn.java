package com.mymeds.inout;

import java.io.Serializable;
import java.util.Date;

public class ConsultaTablesInfoIn implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Date dataLastCreation;
	
	
	public ConsultaTablesInfoIn() {
	}
	
	public Date getDataLastCreation() {
		return dataLastCreation;
	}

	public void setDataLastCreation(Date dataLastCreation) {
		this.dataLastCreation = dataLastCreation;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}


}
