package com.mymeds.service;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.mymeds.database.DBService;
import com.mymeds.inout.VerificaUtilizadorIn;
import com.mymeds.inout.VerificaUtilizadorOut;
import com.mymeds.inout.ConsultaMedicamentoIn;
import com.mymeds.inout.ConsultaMedicamentoOut;
import com.mymeds.inout.ConsultaTablesInfoIn;
import com.mymeds.inout.ConsultaTablesInfoOut;
import com.mymeds.inout.InsertMedicamentoIn;
import com.mymeds.inout.InsertMedicamentoOut;
import com.mymeds.inout.InsertUtilizadorIn;
import com.mymeds.inout.InsertUtilizadorOut;
import com.mymeds.inout.LoginIn;
import com.mymeds.inout.LoginOut;
import com.mymeds.inout.UpdateMedicamentoIn;
import com.mymeds.inout.UpdateMedicamentoOut;
import com.mymeds.result.Result;
import com.mymeds.utils.BooleanFormat;
import com.mymeds.utils.DateHourFormat;
import com.mymeds.utils.ResultCodes;
import com.mymeds.utils.ServerConstants;

import oracle.net.aso.i;



@Path("MyMedsService")
public class MyMedsAPI {
	
	private static Logger log = LoggerFactory.getLogger(MyMedsAPI.class);
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/insertMedicamento")
	public String insertMedicamento(String inputJsonObj) {
		log.info("[MyMedsService.reciveInsertMedicamento]Entrada na consulta");
		StringBuilder result = new StringBuilder(ServerConstants.JSON_OPEN_TAG);
		try {
			/** Inicia o contexto Gson **/
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateHourFormat());
			gsonBuilder.registerTypeAdapter(Boolean.class, new BooleanFormat());
			Gson gson = gsonBuilder.create();

			/** cria o objeto de rececao de episodios **/
			InsertMedicamentoIn input = gson.fromJson(inputJsonObj, InsertMedicamentoIn.class);

			/** Valida de o objeto de input esta preenchido **/
			if (input == null) {
				result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro, INPUT sem registos!"));
				result.append(ServerConstants.JSON_CLOSE_TAG);
				return result.toString();
			} else if (input.getIdMedicamento()==null || input.getIdUtilizador()==null) {
				result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro, Input incompleto!"));
				result.append(ServerConstants.JSON_CLOSE_TAG);
				return result.toString();
			}

			/** Cria estrutura de output **/
			InsertMedicamentoOut output = new InsertMedicamentoOut();
			DBService dBService = new DBService();
			output = dBService.insertMedicamento(input);

			/** Devolve o resultado a atualizacao **/
			log.debug("[MyMedsService.reciveInsertMedicamento]Output: " + gson.toJson(output));
			return gson.toJson(output);

		} catch (JsonSyntaxException ex) {
			log.error("[MyMedsService.reciveInsertMedicamento]Error ", ex);
			result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro a na construção do input!"));
			result.append("[JsonSyntaxException]" + ex);
			result.append(ServerConstants.JSON_CLOSE_TAG);
		} catch (Exception ex) {
			log.error("[MyMedsService.reciveInsertMedicamento]Error ", ex);
			result.append(new Result(ResultCodes.ERRRO_SISTEMA.getCode(), "Erro genérico!"));
			result.append(ServerConstants.JSON_CLOSE_TAG);
		} finally {
			log.info("[MyMedsService.reciveInsertMedicamento]Saida da consulta");
		}
		return result.toString();	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/insertUtilizador")
	public String insertUtilizador(String inputJsonObj) {
		log.info("[MyMedsService.insertUtilizador]Entrada na consulta");
		StringBuilder result = new StringBuilder(ServerConstants.JSON_OPEN_TAG);
		try {
			/** Inicia o contexto Gson **/
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateHourFormat());
			gsonBuilder.registerTypeAdapter(Boolean.class, new BooleanFormat());
			Gson gson = gsonBuilder.create();

			/** cria o objeto de rececao de episodios **/
			InsertUtilizadorIn input = gson.fromJson(inputJsonObj, InsertUtilizadorIn.class);

			/** Valida de o objeto de input esta preenchido **/
			if (input == null) {
				result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro, INPUT sem registos!"));
				result.append(ServerConstants.JSON_CLOSE_TAG);
				return result.toString();
			} else if (StringUtils.isEmpty(input.getEmailUtilizador()) || StringUtils.isEmpty(input.getPassUtilizador())) {
				result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro, Input incompleto!"));
				result.append(ServerConstants.JSON_CLOSE_TAG);
				return result.toString();
			}

			/** Cria estrutura de output **/
			InsertUtilizadorOut output = new InsertUtilizadorOut();
			DBService dBService = new DBService();
			output = dBService.insertUtilizador(input);

			/** Devolve o resultado do insert **/
			log.debug("[MyMedsService.insertUtilizador]Output: " + gson.toJson(output));
			return gson.toJson(output);

		} catch (JsonSyntaxException ex) {
			log.error("[MyMedsService.insertUtilizador]Error ", ex);
			result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro a na construção do input!"));
			result.append("[JsonSyntaxException]" + ex);
			result.append(ServerConstants.JSON_CLOSE_TAG);
		} catch (Exception ex) {
			log.error("[MyMedsService.insertUtilizador]Error ", ex);
			result.append(new Result(ResultCodes.ERRRO_SISTEMA.getCode(), "Erro genérico!"));
			result.append(ServerConstants.JSON_CLOSE_TAG);
		} finally {
			log.info("[MyMedsService.insertUtilizador]Saida da consulta");
		}
		return result.toString();	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/consultarUtilizador")
	public String consultarUtilizador(String inputJsonObj) {
		log.info("[MyMedsService.consultarUtilizador]Entrada na consulta");
		StringBuilder result = new StringBuilder(ServerConstants.JSON_OPEN_TAG);
		try {
			/** Inicia o contexto Gson **/
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateHourFormat());
			gsonBuilder.registerTypeAdapter(Boolean.class, new BooleanFormat());
			Gson gson = gsonBuilder.create();

			/** cria o objeto de rececao de episodios **/
			VerificaUtilizadorIn input = gson.fromJson(inputJsonObj, VerificaUtilizadorIn.class);

			/** Valida de o objeto de input esta preenchido **/
			if (input == null) {
				result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro, INPUT sem registos!"));
				result.append(ServerConstants.JSON_CLOSE_TAG);
				return result.toString();
			} else if (StringUtils.isEmpty(input.getEmailUtilizador())) {
				result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro, Input incompleto!"));
				result.append(ServerConstants.JSON_CLOSE_TAG);
				return result.toString();
			}

			/** Cria estrutura de output **/
			VerificaUtilizadorOut output = new VerificaUtilizadorOut();
			DBService dBService = new DBService();
			output = dBService.consultaUtilizador(input);

			/** Devolve o resultado a atualizacao **/
			log.debug("[MyMedsService.consultarUtilizador]Output: " + gson.toJson(output));
			return gson.toJson(output);

		} catch (JsonSyntaxException ex) {
			log.error("[MyMedsService.consultarUtilizador]Error ", ex);
			result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro a na construção do input!"));
			result.append("[JsonSyntaxException]" + ex);
			result.append(ServerConstants.JSON_CLOSE_TAG);
		} catch (Exception ex) {
			log.error("[MyMedsService.consultarUtilizador]Error ", ex);
			result.append(new Result(ResultCodes.ERRRO_SISTEMA.getCode(), "Erro genérico!"));
			result.append(ServerConstants.JSON_CLOSE_TAG);
		} finally {
			log.info("[MyMedsService.consultarUtilizador]Saida da consulta");
		}
		return result.toString();	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public String login(String inputJsonObj) {
		log.info("[MyMedsService.login]Entrada na consulta");
		StringBuilder result = new StringBuilder(ServerConstants.JSON_OPEN_TAG);
		try {
			/** Inicia o contexto Gson **/
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateHourFormat());
			gsonBuilder.registerTypeAdapter(Boolean.class, new BooleanFormat());
			Gson gson = gsonBuilder.create();

			/** cria o objeto de rececao de episodios **/
			LoginIn input = gson.fromJson(inputJsonObj, LoginIn.class);

			/** Valida de o objeto de input esta preenchido **/
			if (input == null) {
				result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro, INPUT sem registos!"));
				result.append(ServerConstants.JSON_CLOSE_TAG);
				return result.toString();
			} else if (StringUtils.isEmpty(input.getEmailUtilizador()) || StringUtils.isEmpty(input.getPassUtilizador())) {
				result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro, Input incompleto!"));
				result.append(ServerConstants.JSON_CLOSE_TAG);
				return result.toString();
			}

			/** Cria estrutura de output **/
			LoginOut output = new LoginOut();
			DBService dBService = new DBService();
			output = dBService.login(input);

			/** Devolve o resultado a atualizacao **/
			log.debug("[MyMedsService.login]Output: " + gson.toJson(output));
			return gson.toJson(output);

		} catch (JsonSyntaxException ex) {
			log.error("[MyMedsService.login]Error ", ex);
			result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro a na construção do input!"));
			result.append("[JsonSyntaxException]" + ex);
			result.append(ServerConstants.JSON_CLOSE_TAG);
		} catch (Exception ex) {
			log.error("[MyMedsService.login]Error ", ex);
			result.append(new Result(ResultCodes.ERRRO_SISTEMA.getCode(), "Erro genérico!"));
			result.append(ServerConstants.JSON_CLOSE_TAG);
		} finally {
			log.info("[MyMedsService.login]Saida da consulta");
		}
		return result.toString();	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/consultarMedicamentosUtilizador")
	public String consultarMedicamentosUtilizador(String inputJsonObj) {
		log.info("[MyMedsService.consultarMedicamentosUtilizador]Entrada na consulta");
		StringBuilder result = new StringBuilder(ServerConstants.JSON_OPEN_TAG);
		try {
			/** Inicia o contexto Gson **/
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateHourFormat());
			gsonBuilder.registerTypeAdapter(Boolean.class, new BooleanFormat());
			Gson gson = gsonBuilder.create();

			/** cria o objeto de rececao de episodios **/
			ConsultaMedicamentoIn input = gson.fromJson(inputJsonObj, ConsultaMedicamentoIn.class);

			/** Valida de o objeto de input esta preenchido **/
			if (input == null) {
				result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro, INPUT sem registos!"));
				result.append(ServerConstants.JSON_CLOSE_TAG);
				return result.toString();
			} else if (input.getIdUtilizador()==null) {
				result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro, Input incompleto!"));
				result.append(ServerConstants.JSON_CLOSE_TAG);
				return result.toString();
			}

			/** Cria estrutura de output **/
			ConsultaMedicamentoOut output = new ConsultaMedicamentoOut();
			DBService dBService = new DBService();
			output = dBService.consultaMedicamento(input);

			/** Devolve o resultado a atualizacao **/
			log.debug("[MyMedsService.consultarMedicamentosUtilizador]Output: " + gson.toJson(output));
			return gson.toJson(output);

		} catch (JsonSyntaxException ex) {
			log.error("[MyMedsService.consultarMedicamentosUtilizador]Error ", ex);
			result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro a na construção do input!"));
			result.append("[JsonSyntaxException]" + ex);
			result.append(ServerConstants.JSON_CLOSE_TAG);
		} catch (Exception ex) {
			log.error("[MyMedsService.consultarMedicamentosUtilizador]Error ", ex);
			result.append(new Result(ResultCodes.ERRRO_SISTEMA.getCode(), "Erro genérico!"));
			result.append(ServerConstants.JSON_CLOSE_TAG);
		} finally {
			log.info("[MyMedsService.consultarMedicamentosUtilizador]Saida da consulta");
		}
		return result.toString();	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/insertMedicamentoUtilizador")
	public String insertMedicamentoUtilizador(String inputJsonObj) {
		log.info("[MyMedsService.insertMedicamentoUtilizador]Entrada na consulta");
		StringBuilder result = new StringBuilder(ServerConstants.JSON_OPEN_TAG);
		try {
			/** Inicia o contexto Gson **/
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateHourFormat());
			gsonBuilder.registerTypeAdapter(Boolean.class, new BooleanFormat());
			Gson gson = gsonBuilder.create();

			/** cria o objeto de rececao de episodios **/
			InsertMedicamentoIn input = gson.fromJson(inputJsonObj, InsertMedicamentoIn.class);

			/** Valida de o objeto de input esta preenchido **/
			if (input == null) {
				result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro, INPUT sem registos!"));
				result.append(ServerConstants.JSON_CLOSE_TAG);
				return result.toString();
			} else if (input.getIdMedicamento()==null || input.getIdUtilizador()==null || input.getNumDoses()==null) {
				result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro, Input incompleto!"));
				result.append(ServerConstants.JSON_CLOSE_TAG);
				return result.toString();
			}

			/** Cria estrutura de output **/
			InsertMedicamentoOut output = new InsertMedicamentoOut();
			DBService dBService = new DBService();
			output = dBService.insertMedicamento(input);

			/** Devolve o resultado do insert **/
			log.debug("[MyMedsService.insertMedicamentoUtilizador]Output: " + gson.toJson(output));
			return gson.toJson(output);

		} catch (JsonSyntaxException ex) {
			log.error("[MyMedsService.insertMedicamentoUtilizador]Error ", ex);
			result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro a na construção do input!"));
			result.append("[JsonSyntaxException]" + ex);
			result.append(ServerConstants.JSON_CLOSE_TAG);
		} catch (Exception ex) {
			log.error("[MyMedsService.insertMedicamentoUtilizador]Error ", ex);
			result.append(new Result(ResultCodes.ERRRO_SISTEMA.getCode(), "Erro genérico!"));
			result.append(ServerConstants.JSON_CLOSE_TAG);
		} finally {
			log.info("[MyMedsService.insertMedicamentoUtilizador]Saida da consulta");
		}
		return result.toString();	
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/updateMedicamentoUtilizador")
	public String updateMedicamentoUtilizador(String inputJsonObj) {
		log.info("[MyMedsService.updateMedicamentoUtilizador]Entrada na consulta");
		StringBuilder result = new StringBuilder(ServerConstants.JSON_OPEN_TAG);
		try {
			/** Inicia o contexto Gson **/
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateHourFormat());
			gsonBuilder.registerTypeAdapter(Boolean.class, new BooleanFormat());
			Gson gson = gsonBuilder.create();

			/** cria o objeto de rececao de episodios **/
			UpdateMedicamentoIn input = gson.fromJson(inputJsonObj, UpdateMedicamentoIn.class);

			/** Valida de o objeto de input esta preenchido **/
			if (input == null) {
				result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro, INPUT sem registos!"));
				result.append(ServerConstants.JSON_CLOSE_TAG);
				return result.toString();
			} else if (input.getRlUtilizadorMedicamento()==null) {
				result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro, Input incompleto!"));
				result.append(ServerConstants.JSON_CLOSE_TAG);
				return result.toString();
			}

			/** Cria estrutura de output **/
			UpdateMedicamentoOut output = new UpdateMedicamentoOut();
			DBService dBService = new DBService();
			output = dBService.updateMedicamento(input);

			/** Devolve o resultado do insert **/
			log.debug("[MyMedsService.updateMedicamentoUtilizador]Output: " + gson.toJson(output));
			return gson.toJson(output);

		} catch (JsonSyntaxException ex) {
			log.error("[MyMedsService.updateMedicamentoUtilizador]Error ", ex);
			result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro a na construção do input!"));
			result.append("[JsonSyntaxException]" + ex);
			result.append(ServerConstants.JSON_CLOSE_TAG);
		} catch (Exception ex) {
			log.error("[MyMedsService.updateMedicamentoUtilizador]Error ", ex);
			result.append(new Result(ResultCodes.ERRRO_SISTEMA.getCode(), "Erro genérico!"));
			result.append(ServerConstants.JSON_CLOSE_TAG);
		} finally {
			log.info("[MyMedsService.updateMedicamentoUtilizador]Saida da consulta");
		}
		return result.toString();	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/consultarTablesInfo")
	public String consultarTablesInfo(String inputJsonObj) {
		log.info("[MyMedsService.consultarTablesInfo]Entrada na consulta");
		StringBuilder result = new StringBuilder(ServerConstants.JSON_OPEN_TAG);
		try {
			/** Inicia o contexto Gson **/
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateHourFormat());
			gsonBuilder.registerTypeAdapter(Boolean.class, new BooleanFormat());
			Gson gson = gsonBuilder.create();

			/** cria o objeto de rececao de episodios **/
			ConsultaTablesInfoIn input = gson.fromJson(inputJsonObj, ConsultaTablesInfoIn.class);

			/** Valida de o objeto de input esta preenchido **/
			if (input == null) {
				result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro, INPUT sem registos!"));
				result.append(ServerConstants.JSON_CLOSE_TAG);
				return result.toString();
			} else if (input.getDataLastCreation()==null) {
				result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro, Input incompleto!"));
				result.append(ServerConstants.JSON_CLOSE_TAG);
				return result.toString();
			}

			/** Cria estrutura de output **/
			ConsultaTablesInfoOut output = new ConsultaTablesInfoOut();
			DBService dBService = new DBService();
			output = dBService.consultaTablesInfo(input);

			/** Devolve o resultado a atualizacao **/
			log.debug("[MyMedsService.consultarTablesInfo]Output: " + gson.toJson(output));
			return gson.toJson(output);

		} catch (JsonSyntaxException ex) {
			log.error("[MyMedsService.consultarTablesInfo]Error ", ex);
			result.append(new Result(ResultCodes.ERRO_VALIDACAO.getCode(), "Erro a na construção do input!"));
			result.append("[JsonSyntaxException]" + ex);
			result.append(ServerConstants.JSON_CLOSE_TAG);
		} catch (Exception ex) {
			log.error("[MyMedsService.consultarTablesInfo]Error ", ex);
			result.append(new Result(ResultCodes.ERRRO_SISTEMA.getCode(), "Erro genérico!"));
			result.append(ServerConstants.JSON_CLOSE_TAG);
		} finally {
			log.info("[MyMedsService.consultarTablesInfo]Saida da consulta");
		}
		return result.toString();	
	}
	
}
