package com.mymeds.database;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.mymeds.inout.ConsultaMedicamentoIn;
import com.mymeds.inout.ConsultaMedicamentoOut;
import com.mymeds.inout.ConsultaTablesInfoIn;
import com.mymeds.inout.ConsultaTablesInfoOut;
import com.mymeds.inout.VerificaUtilizadorIn;
import com.mymeds.inout.VerificaUtilizadorOut;
import com.mymeds.inout.InsertMedicamentoIn;
import com.mymeds.inout.InsertMedicamentoOut;
import com.mymeds.inout.InsertUtilizadorIn;
import com.mymeds.inout.InsertUtilizadorOut;
import com.mymeds.inout.LoginIn;
import com.mymeds.inout.LoginOut;
import com.mymeds.inout.UpdateMedicamentoIn;
import com.mymeds.inout.UpdateMedicamentoOut;
import com.mymeds.objects.Medicamento;
import com.mymeds.objects.RlUtilizadorMedicamento;
import com.mymeds.objects.TablesInfo;
import com.mymeds.objects.TipoMedicamento;
import com.mymeds.objects.Utilizador;
import com.mymeds.querys.Querys;



public class DBService {
	
	private int FIRST = 0;

	/**
	 * Insere um novo medicamento
	 * @param input
	 * @return
	 * @throws Exception 
	 */
	public InsertMedicamentoOut insertMedicamento(InsertMedicamentoIn input) throws Exception{
		InsertMedicamentoOut output = new InsertMedicamentoOut();
		DataBaseConfig dbConfig = new DataBaseConfig();
		String query = null;
		
		if(input!=null && input.getIdMedicamento() !=null && input.getIdUtilizador() !=null && input.getNumDoses() !=null){
			query = Querys.getQueryInsertMedUtilizador(input.getIdUtilizador(), input.getIdMedicamento(), input.getDataInicio(), input.getNumDoses(), input.getHorasIntervalo());	
		}
		
		/** INSERT **/
		int rsUserMed = 0;
		if(StringUtils.isNotBlank(query)){
			rsUserMed = dbConfig.executeInsert(query);
		}
		
		/** tratar o resultado **/
		Long idUserMed = null;
		ConsultaMedicamentoOut outputUserMed = null;
		if(rsUserMed != 0){
			/**
			 * Devolve o idUtilizador
			 */
			ConsultaMedicamentoIn inputUserMed = new ConsultaMedicamentoIn();
			inputUserMed.setIdUtilizador(input.getIdUtilizador());
			inputUserMed.setIdMedicamento(input.getIdMedicamento());
			outputUserMed = this.consultaMedicamento(inputUserMed);
		}		
		dbConfig.closeConnection();
		
		if(outputUserMed!=null && outputUserMed.getListaMedicamentos()!=null && outputUserMed.getListaMedicamentos().get(FIRST)!=null &&  outputUserMed.getListaMedicamentos().get(FIRST).getIdRlUtilizadorMedicamento()!=null){
			idUserMed = outputUserMed.getListaMedicamentos().get(FIRST).getIdRlUtilizadorMedicamento();
		}
		
		output.setIdRlUtilizadorMedicamento(idUserMed);	
		
		return output;
	}
	
	/**
	 * Update um novo medicamento
	 * @param input
	 * @return
	 * @throws Exception 
	 */
	public UpdateMedicamentoOut updateMedicamento(UpdateMedicamentoIn input) throws Exception{
		UpdateMedicamentoOut output = new UpdateMedicamentoOut();
		DataBaseConfig dbConfig = new DataBaseConfig();
		String query = null;
		
		if(input!=null && input.getRlUtilizadorMedicamento() !=null){
			query = Querys.getQueryUpdateMedUtilizador(input.getRlUtilizadorMedicamento());	
		}
		
		/** UPDATE **/
		int rsUserMed = 0;
		if(StringUtils.isNotBlank(query)){
			rsUserMed = dbConfig.executeUpdate(query);
		}
		
		/** tratar o resultado **/
		Long idUserMed = null;
		ConsultaMedicamentoOut outputUserMed = null;
		if(rsUserMed != 0){
			/**
			 * Devolve o rlUserMed
			 */
			ConsultaMedicamentoIn inputUserMed = new ConsultaMedicamentoIn();
			inputUserMed.setIdUtilizador(input.getRlUtilizadorMedicamento().getUtilizador().getIdUtilizador());
			inputUserMed.setIdMedicamento(input.getRlUtilizadorMedicamento().getMedicamento().getIdMedicamento());
			outputUserMed = this.consultaMedicamento(inputUserMed);
		}		
		dbConfig.closeConnection();
		
		if(outputUserMed!=null && outputUserMed.getListaMedicamentos()!=null && outputUserMed.getListaMedicamentos().get(FIRST)!=null &&  outputUserMed.getListaMedicamentos().get(FIRST).getIdRlUtilizadorMedicamento()!=null){
			output.setRlUtilizadorMedicamento(outputUserMed.getListaMedicamentos().get(FIRST));
		}
		
		
		return output;
	}
	
	/**
	 * Insere um novo utilizador na base de dados
	 * @param input
	 * @return
	 */
	public InsertUtilizadorOut insertUtilizador(InsertUtilizadorIn input)  throws Exception{
		InsertUtilizadorOut output = new InsertUtilizadorOut();
		DataBaseConfig dbConfig = new DataBaseConfig();
		String query = null;
		
		if(input!=null && input.getEmailUtilizador()!=null && input.getPassUtilizador()!=null){
			query = Querys.getQueryInsertUtilizador(input.getEmailUtilizador(), input.getPassUtilizador());	
		}
		
		/** SELECT **/
		int rsUtilizador = 0;
		if(StringUtils.isNotBlank(query)){
			rsUtilizador = dbConfig.executeInsert(query);
		}
		
		/** tratar o resultado **/
		Long idUtilizador = null;
		LoginOut outputLogin = null;
		if(rsUtilizador != 0){
			/**
			 * Devolve o idUtilizador
			 */
			LoginIn inputLogin = new LoginIn();
			inputLogin.setEmailUtilizador(input.getEmailUtilizador());
			inputLogin.setPassUtilizador(input.getPassUtilizador());
			outputLogin = this.login(inputLogin);
		}		
		dbConfig.closeConnection();
		
		if(outputLogin!=null){
			idUtilizador = outputLogin.getIdUtilizador();
		}
		
		output.setIdUtilizador(idUtilizador);	
		
		return output;
	}
	
	
	/**
	 * Login do utilizador
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public LoginOut login(LoginIn input) throws Exception{
		LoginOut output = new LoginOut();
		DataBaseConfig dbConfig = new DataBaseConfig();
		String query = null;
		
		if(input!=null && input.getEmailUtilizador()!=null && input.getPassUtilizador()!=null){
			query = Querys.getQueryLogin(input.getEmailUtilizador(), input.getPassUtilizador());	
		}
		
		/** SELECT **/
		ResultSet rsUtilizador = null;
		if(StringUtils.isNotBlank(query)){
			rsUtilizador = dbConfig.executeSelect(query);
		}
		
		/** tratar o resultado **/
		Long idUtilizador = null;
		if(rsUtilizador != null){
			while(rsUtilizador.next()){
				idUtilizador = rsUtilizador.getLong("ID_UTILIZADOR");
			}
		}		
		dbConfig.closeConnection();
		
		output.setIdUtilizador(idUtilizador);	
		
		return output;
	}
	
	/**
	 * Consulta todos os medicamento de um utilizador
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public ConsultaMedicamentoOut consultaMedicamento(ConsultaMedicamentoIn input) throws Exception{
		ConsultaMedicamentoOut output = new ConsultaMedicamentoOut();
		DataBaseConfig dbConfig = new DataBaseConfig();
		String query = null;
		/**
		 * Se não estiver preenchido no input a data de inicio e o idMedicamento o select é de todos o medicamentos do utilizador
		 */
		if(input!=null && input.getIdUtilizador()!=null){
			query = Querys.getQuerySelectMedicamento(input.getIdUtilizador().intValue(), input.getIdMedicamento()!=null ? input.getIdMedicamento().intValue() : null);
		}
		
		/** SELECT **/
		ResultSet rsUserMed = null;
		if(StringUtils.isNotBlank(query)){
			rsUserMed = dbConfig.executeSelect(query);
		}
		
		/** tratar o resultado **/
		RlUtilizadorMedicamento rlUserMed = null;
		List<RlUtilizadorMedicamento> listaUserMeds = null;
		if(rsUserMed != null){
			listaUserMeds = new ArrayList<RlUtilizadorMedicamento>();
			while(rsUserMed.next()){
				rlUserMed = new RlUtilizadorMedicamento();
				rlUserMed.setIdRlUtilizadorMedicamento(rsUserMed.getLong("ID_RL_UTILIZADOR_MEDICAMENTO"));
//DNEVES
//				/**
//				 * Se não estiver preenchido no input a data de inicio e o idMedicamento o select é de todos o medicamentos do utilizador
//				 */
//				if(input.getIdMedicamento()==null){
					/** Medicamento **/
					Medicamento medicamento = new Medicamento();
					medicamento.setIdMedicamento(rsUserMed.getLong("ID_MEDICAMENTO"));
					medicamento.setDescricao(rsUserMed.getString("DESC_MEDICAMENTO"));
					TipoMedicamento tipoMedicamento = new TipoMedicamento();
					tipoMedicamento.setDescricao(rsUserMed.getString("DESC_TIPO"));
					medicamento.setTipoMedicamento(tipoMedicamento);
					rlUserMed.setMedicamento(medicamento);
					/** Utilizador **/
					Utilizador utilizador = new Utilizador();
					utilizador.setIdUtilizador(input.getIdUtilizador());
					/** Data Inicio **/
					rlUserMed.setDateInicioDosagem(rsUserMed.getTimestamp("DATA_INICIO_DOSAGEM"));
					/** Data Fim **/
					rlUserMed.setDateFimDosagem(rsUserMed.getTimestamp("DATA_FIM_DOSAGEM"));
					/**	Numero de Doses **/
					rlUserMed.setNumeroDoses(rsUserMed.getInt("NUMERO_DOSES"));
					/**	Horas Intervalo **/
					rlUserMed.setHorasIntervalo(rsUserMed.getInt("HORAS_INTERVALO"));
					/**	Alarme **/
					rlUserMed.setAlarme(rsUserMed.getInt("ALARME"));
					/**	Data Ultima Toma **/
					rlUserMed.setDataUltimaToma(rsUserMed.getTimestamp("DATA_ULTIMA_TOMA"));
					/**	Id Alarme **/
					rlUserMed.setIdAlarme(rsUserMed.getLong("ID_ALARME"));
					/** Created by **/
					rlUserMed.setCreatedBy(rsUserMed.getString("CREATED_BY"));
					/** Creation date **/
					rlUserMed.setCreationDate(rsUserMed.getTimestamp("CREATION_DATE"));
					/** Updated by **/
					rlUserMed.setUpdatedBy(rsUserMed.getString("UPDATED_BY"));
					/** Update date **/
					rlUserMed.setUpdateDate(rsUserMed.getTimestamp("UPDATE_DATE"));
					/** Enabled **/
					rlUserMed.setEnabled(rsUserMed.getInt("ENABLED"));
//				}
				listaUserMeds.add(rlUserMed);
			}
		}
		
		dbConfig.closeConnection();
		output.setListaMedicamentos(listaUserMeds);
		
		return output;
	}
	
	/**
	 * Consulta se o utilizador já existe na base de dados
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public VerificaUtilizadorOut consultaUtilizador(VerificaUtilizadorIn input) throws Exception{
		VerificaUtilizadorOut output = new VerificaUtilizadorOut();
		DataBaseConfig dbConfig = new DataBaseConfig();
		String query = null;
		
		if(input!=null && input.getEmailUtilizador()!=null){
			query = Querys.getQuerySelectUtilizador(input.getEmailUtilizador());	
		}
		
		/** SELECT **/
		ResultSet rsUtilizador = null;
		if(StringUtils.isNotBlank(query)){
			rsUtilizador = dbConfig.executeSelect(query);
		}
		
		/** tratar o resultado **/
		Utilizador utilizador = null;
		if(rsUtilizador != null){
			while(rsUtilizador.next()){
				utilizador = new Utilizador();
				utilizador.setIdUtilizador(rsUtilizador.getLong("ID_UTILIZADOR"));
			}
		}
		
		dbConfig.closeConnection();
		if(utilizador!=null && utilizador.getIdUtilizador()!=null){
			output.setExiste(true);
		}else{
			output.setExiste(false);
		}
		
		return output;
	}
	
	/**
	 * Consulta as Tables info
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public ConsultaTablesInfoOut consultaTablesInfo(ConsultaTablesInfoIn input) throws Exception{
		ConsultaTablesInfoOut output = new ConsultaTablesInfoOut();
		DataBaseConfig dbConfig = new DataBaseConfig();
		String query = null;
		
		if(input!=null && input.getDataLastCreation()!=null){
			query = Querys.getQuerySelectTablesInfo(input.getDataLastCreation());	
		}
		
		/** SELECT **/
		ResultSet rsTablesInfo = null;
		if(StringUtils.isNotBlank(query)){
			rsTablesInfo = dbConfig.executeSelect(query);
		}
		
		/** tratar o resultado **/
		TablesInfo tablesInfo = null;
		if(rsTablesInfo != null){
			while(rsTablesInfo.next()){
				tablesInfo = new TablesInfo();
				tablesInfo.setIdTablesInfo(rsTablesInfo.getLong("ID_TABLES_INFO"));
				tablesInfo.setMedicamento(rsTablesInfo.getBoolean("MEDICAMENTO"));
				tablesInfo.setTipoMedicamento(rsTablesInfo.getBoolean("TIPO_MEDICAMENTO"));
				tablesInfo.setSubstanciaActiva(rsTablesInfo.getBoolean("SUBSTANCIA_ACTIVA"));
				tablesInfo.setPais(rsTablesInfo.getBoolean("PAIS"));
				tablesInfo.setMedicamento(rsTablesInfo.getBoolean("MEDICAMENTO"));
				/** Created by **/
				tablesInfo.setCreatedBy(rsTablesInfo.getString("CREATED_BY"));
				/** Creation date **/
				tablesInfo.setCreationDate(rsTablesInfo.getTimestamp("CREATION_DATE"));
				/** Updated by **/
				tablesInfo.setUpdatedBy(rsTablesInfo.getString("UPDATED_BY"));
				/** Update date **/
				tablesInfo.setUpdateDate(rsTablesInfo.getTimestamp("UPDATE_DATE"));
				/** Enabled **/
				tablesInfo.setEnabled(rsTablesInfo.getInt("ENABLED"));
			}
		}
		
		dbConfig.closeConnection();
		output.setTablesInfo(tablesInfo);
		return output;
	}
	
}
