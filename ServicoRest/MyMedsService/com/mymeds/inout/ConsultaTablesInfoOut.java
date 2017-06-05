package com.mymeds.inout;

import java.io.Serializable;
import java.util.List;

import com.mymeds.objects.RlUtilizadorMedicamento;
import com.mymeds.objects.TablesInfo;

public class ConsultaTablesInfoOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private TablesInfo tablesInfo;


	public TablesInfo getTablesInfo() {
		return tablesInfo;
	}


	public void setTablesInfo(TablesInfo tablesInfo) {
		this.tablesInfo = tablesInfo;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
