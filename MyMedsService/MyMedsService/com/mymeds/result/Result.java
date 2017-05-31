package com.mymeds.result;

import java.io.Serializable;

import com.mymeds.utils.ResultCodes;


public class Result implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codResultado;
	private String descResultado;

	/** Variaveis dinamicas no output **/

	public Result() {
	}

	public Result(ResultCodes result) {
		this.codResultado = result.getCode();
		this.descResultado = result.getDesc();
	}

	public Result(String codResultado, String descResultado) {
		this.codResultado = codResultado;
		this.descResultado = descResultado;
	}

	public String getCodResultado() {
		return this.codResultado;
	}

	public String getDescResultado() {
		return this.descResultado;
	}

	public void setCodResultado(String codResultado) {
		this.codResultado = codResultado;
	}

	public void setDescResultado(String descResultado) {
		this.descResultado = descResultado;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\"resultado\" :{");
		if (codResultado != null) {
			builder.append("\"codResultado\":\"");
			builder.append(codResultado);
			builder.append("\"");
		}
		if (descResultado != null) {
			if (codResultado != null) {
				builder.append(",");
			}
			builder.append("\"descResultado\":\"");
			builder.append(descResultado);
			builder.append("\"");
		}
		builder.append("}");
		return builder.toString();
	}

}