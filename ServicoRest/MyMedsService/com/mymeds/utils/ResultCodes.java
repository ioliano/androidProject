package com.mymeds.utils;

public enum ResultCodes {

	SUCESSO_SEM_RESULTADOS("0", "Sucesso sem resultados"),
	SUCESSO_COM_RESULTADOS("1", "Sucesso com resultados"),
	ERRRO_SISTEMA("2", "Erro de Sistema"),
	ERRO_AUTENTICACAO("3", "Autenticacao errada"),
	ERRO_VALIDACAO("4", "Falhou nas validacoes de estrutura"),
	SUCESSO_STATUS_OK("90", "Status OK"),
	SUCESSO_STATUS_NOT_FOUND("91", "Status Not Found");

private String code;
private String desc;

private ResultCodes(String code, String desc) {
	this.setCode(code);
	this.setDesc(desc);
}

public String getCode() {
	return this.code;
}

public String getDesc() {
	return this.desc;
}

public void setCode(String code) {
	this.code = code;
}

public void setDesc(String desc) {
	this.desc = desc;
}

}
