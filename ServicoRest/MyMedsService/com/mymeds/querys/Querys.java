package com.mymeds.querys;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.mymeds.objects.RlUtilizadorMedicamento;
import com.mymeds.utils.MyMedsConstants;

public class Querys {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * Query Adicionar Utilizador
	 * @param email
	 * @param password
	 * @return
	 */
	public static String getQueryInsertUtilizador(String email, String password){
		StringBuffer hql = new StringBuffer();
		hql.append(" INSERT INTO UTILIZADOR (    ID_UTILIZADOR,   EMAIL,    PASSWORD,    ID_TIPO_UTILIZADOR,    CREATED_BY,    CREATION_DATE,    UPDATED_BY,    UPDATE_DATE,    ENABLED)  VALUES ( ");
		hql.append(" SEQ_UTILIZADOR.NEXTVAL, ");
		hql.append(" '" + email + "', ");
		hql.append(" '" + password + "', ");
		hql.append(" (SELECT ID_TIPO_UTILIZADOR FROM TIPO_UTILIZADOR WHERE ENABLED = 1 AND CODIGO = 1), ");
		hql.append(" 'MYMEDS.USER.CREATION', ");
		hql.append(" SYSDATE, ");
		hql.append(" 'MYMEDS.USER.CREATION', ");
		hql.append(" SYSDATE, ");
		hql.append(" 1)");
		return hql.toString();
	}
	
	/**
	 * Query Verifica Utilizador
	 * @param email
	 * @param password
	 * @return
	 */
	public static String getQuerySelectUtilizador(String email){
		StringBuffer hql = new StringBuffer();
		hql.append(" SELECT ");
		hql.append(" UTILIZADOR.ID_UTILIZADOR ID_UTILIZADOR ");
		hql.append(" FROM UTILIZADOR WHERE  ");
		hql.append(" UTILIZADOR.EMAIL =  '" + email + "' ");
		hql.append(" AND UTILIZADOR.ENABLED =  1");
		return hql.toString();
	}
	
	/**
	 * Query Verifica TABLES INFO
	 * @param email
	 * @param password
	 * @return
	 */
	public static String getQuerySelectTablesInfo(Date lastCreation){
		StringBuffer hql = new StringBuffer();
		hql.append(" SELECT ");
		hql.append(" TABLES_INFO.ID_TABLES_INFO ID_TABLES_INFO, ");
		hql.append(" TABLES_INFO.MEDICAMENTO MEDICAMENTO, ");
		hql.append(" TABLES_INFO.TIPO_MEDICAMENTO TIPO_MEDICAMENTO, ");
		hql.append(" TABLES_INFO.SUBSTANCIA_ACTIVA SUBSTANCIA_ACTIVA, ");
		hql.append(" TABLES_INFO.PAIS PAIS, ");
		hql.append(" TABLES_INFO.CREATED_BY CREATED_BY, ");
		hql.append(" TABLES_INFO.CREATION_DATE CREATION_DATE, ");
		hql.append(" TABLES_INFO.UPDATED_BY UPDATED_BY, ");
		hql.append(" TABLES_INFO.UPDATE_DATE UPDATE_DATE, ");
		hql.append(" TABLES_INFO.ENABLED ENABLED ");
		hql.append(" FROM TABLES_INFO WHERE  ");
		hql.append(" TABLES_INFO.CREATION_DATE > TO_DATE('" + dateFormat.format(lastCreation) + "','yyyy-MM-dd HH24:mi:ss') ");
		hql.append(" AND TABLES_INFO.ENABLED =  1");
		return hql.toString();
	}
	
	/**
	 * Query Login
	 * @param email
	 * @param password
	 * @return
	 */
	public static String getQueryLogin(String email, String password){
		StringBuffer hql = new StringBuffer();
		hql.append(" SELECT ");
		hql.append(" UTILIZADOR.ID_UTILIZADOR ID_UTILIZADOR ");
		hql.append(" FROM UTILIZADOR WHERE  ");
		hql.append(" UTILIZADOR.EMAIL =  '" + email + "' ");
		hql.append(" AND UTILIZADOR.PASSWORD =  '" + password + "' ");
		hql.append(" AND UTILIZADOR.ENABLED =  1");
		return hql.toString();
	}
	
	/**
	 * Query Adicionar novo medicamento do utilizador
	 * @param email
	 * @param password
	 * @return
	 */
	public static String getQueryInsertMedUtilizador(Long idUtilizador, Long idMedicamento, Date dataInicio, Integer numDoses, Integer horasIntervalo){
		int horasFinais = numDoses * horasIntervalo;
		StringBuffer hql = new StringBuffer();
		hql.append(" INSERT INTO RL_UTILIZADOR_MEDICAMENTO (    ID_RL_UTILIZADOR_MEDICAMENTO,    ID_UTILIZADOR,    ID_MEDICAMENTO,    ");
		/** Campos opcionais **/
		if(dataInicio!=null){
			hql.append(" DATA_INICIO_DOSAGEM,    DATA_FIM_DOSAGEM,  ");
		}
		hql.append(" NUMERO_DOSES, ");
		/** Campos opcionais **/
		hql.append(" HORAS_INTERVALO,");
		hql.append(" ALARME,   CREATED_BY,    CREATION_DATE,   UPDATED_BY,    UPDATE_DATE,    ENABLED) VALUES ( ");
		hql.append(" SEQ_RL_UTILIZADOR_MEDICAMENTO.NEXTVAL, ");
		hql.append(" " + idUtilizador + ", ");
		hql.append(" " + idMedicamento + ", ");
		/** Campos opcionais **/
		if(dataInicio!=null){
			hql.append(" TO_DATE('" + dateFormat.format(dataInicio) + "','yyyy-MM-dd HH24:mi:ss'), ");
			hql.append(" TO_DATE('" + dateFormat.format(calculaDataFinal(dataInicio,horasFinais)) + "','yyyy-MM-dd HH24:mi:ss'), ");
		}
		hql.append(" " + numDoses + ", ");
		/** Campos opcionais **/
		hql.append(" " + horasIntervalo + ", ");
		hql.append(" 2, ");
		hql.append(" (SELECT EMAIL FROM UTILIZADOR WHERE ENABLED = 1 AND ID_UTILIZADOR = " + idUtilizador +"), ");
		hql.append(" SYSDATE, ");
		hql.append(" (SELECT EMAIL FROM UTILIZADOR WHERE ENABLED = 1 AND ID_UTILIZADOR = " + idUtilizador +"), ");
		hql.append(" SYSDATE, ");
		hql.append(" 1)");
		return hql.toString();
	}
	
	/**
	 * Query Editar medicamento do utilizador
	 * @param email
	 * @param password
	 * @return
	 */
	public static String getQueryUpdateMedUtilizador(RlUtilizadorMedicamento rlUserMed){
		StringBuffer hql = new StringBuffer();
		hql.append(" UPDATE RL_UTILIZADOR_MEDICAMENTO SET  ");    
		hql.append(rlUserMed.getDateInicioDosagem()!=null ? " DATA_INICIO_DOSAGEM = TO_DATE('" + dateFormat.format(rlUserMed.getDateInicioDosagem()) + "','yyyy-MM-dd HH24:mi:ss'), ": "");
		hql.append(rlUserMed.getDateFimDosagem()!=null ? " DATA_FIM_DOSAGEM = TO_DATE('" + dateFormat.format(rlUserMed.getDateFimDosagem()) + "','yyyy-MM-dd HH24:mi:ss'), ": "");  
		hql.append(" NUMERO_DOSES = " + rlUserMed.getNumeroDoses() + ", ");    
		hql.append(" HORAS_INTERVALO =  " + rlUserMed.getHorasIntervalo() + ", "); 
		hql.append(" ALARME = " + rlUserMed.getAlarme() + ", "); 
		hql.append(rlUserMed.getDataUltimaToma()!=null ? " DATA_ULTIMA_TOMA = TO_DATE('" + dateFormat.format(rlUserMed.getDataUltimaToma()) + "','yyyy-MM-dd HH24:mi:ss'), " : "  DATA_ULTIMA_TOMA = NULL, "); 
		/** Adiciona o alarme pela sequencia **/
		if(rlUserMed.getAlarme()==MyMedsConstants.ACTIVO && rlUserMed.getIdAlarme()==null){
			hql.append(" ID_ALARME =  SEQ_ALARME.NEXTVAL, "); 
		}
		hql.append(" UPDATED_BY =  (SELECT EMAIL FROM UTILIZADOR WHERE ENABLED = 1 AND ID_UTILIZADOR = " + rlUserMed.getUtilizador().getIdUtilizador() +") , "); 
		hql.append(" UPDATE_DATE = SYSDATE, "); 
		hql.append(" ENABLED = 1 "); 
		hql.append(" WHERE  ");
		hql.append(" ENABLED = " + MyMedsConstants.ACTIVO + " "); 
		hql.append(" AND ID_RL_UTILIZADOR_MEDICAMENTO = " + rlUserMed.getIdRlUtilizadorMedicamento()); 
		return hql.toString();
	}
	
	/**
	 * Query Editar medicamento do utilizador quanto Toma Medicamento
	 * @param email
	 * @param password
	 * @return
	 */
	public static String getQueryTomarMedicamento(RlUtilizadorMedicamento rlUserMed){
		StringBuffer hql = new StringBuffer();
		hql.append(" UPDATE RL_UTILIZADOR_MEDICAMENTO SET  ");    
		hql.append(" NUMERO_DOSES = " + rlUserMed.getNumeroDoses() + ", "); 
		hql.append(" DATA_ULTIMA_TOMA = SYSDATE, "); 
		hql.append(rlUserMed.getNumeroDoses() == 0 ? " ALARME = " + MyMedsConstants.INACTIVO +  ", " : ""); 
		hql.append(rlUserMed.getNumeroDoses() == 0 ? " ID_ALARME = NULL, " : ""); 
		hql.append(" UPDATED_BY =  (SELECT EMAIL FROM UTILIZADOR WHERE ENABLED = 1 AND ID_UTILIZADOR = " + rlUserMed.getUtilizador().getIdUtilizador() +") , "); 
		hql.append(" UPDATE_DATE = SYSDATE, "); 
		hql.append(" ENABLED = 1 "); 
		hql.append(" WHERE  ");
		hql.append(" ENABLED = " + MyMedsConstants.ACTIVO + " "); 
		hql.append(" AND ID_RL_UTILIZADOR_MEDICAMENTO = " + rlUserMed.getIdRlUtilizadorMedicamento()); 
		return hql.toString();
	}
	
	/**
	 * Query Editar medicamento do utilizador quanto Desactiva Alarme
	 * @param email
	 * @param password
	 * @return
	 */
	public static String getQueryDesactivarAlarme(RlUtilizadorMedicamento rlUserMed){
		StringBuffer hql = new StringBuffer();
		hql.append(" UPDATE RL_UTILIZADOR_MEDICAMENTO SET  ");    
		hql.append(" ALARME = " + MyMedsConstants.INACTIVO +  ", "); 
		hql.append(" ID_ALARME = NULL, "); 
		hql.append(" UPDATED_BY =  (SELECT EMAIL FROM UTILIZADOR WHERE ENABLED = 1 AND ID_UTILIZADOR = " + rlUserMed.getUtilizador().getIdUtilizador() +") , "); 
		hql.append(" UPDATE_DATE = SYSDATE, "); 
		hql.append(" ENABLED = 1 "); 
		hql.append(" WHERE  ");
		hql.append(" ENABLED = " + MyMedsConstants.ACTIVO + " "); 
		hql.append(" AND ID_RL_UTILIZADOR_MEDICAMENTO = " + rlUserMed.getIdRlUtilizadorMedicamento()); 
		return hql.toString();
	}
	
	/**
	 * Query Editar medicamento do utilizador quanto Desactiva Alarme
	 * @param email
	 * @param password
	 * @return
	 */
	public static String getQueryApagarMedicamento(RlUtilizadorMedicamento rlUserMed){
		StringBuffer hql = new StringBuffer();
		hql.append(" UPDATE RL_UTILIZADOR_MEDICAMENTO SET  ");    
		hql.append(" UPDATED_BY =  (SELECT EMAIL FROM UTILIZADOR WHERE ENABLED = 1 AND ID_UTILIZADOR = " + rlUserMed.getUtilizador().getIdUtilizador() +") , "); 
		hql.append(" UPDATE_DATE = SYSDATE, "); 
		hql.append(" ENABLED = " +  MyMedsConstants.INACTIVO + " "); 
		hql.append(" WHERE  ");
		hql.append(" ENABLED = " + MyMedsConstants.ACTIVO + " "); 
		hql.append(" AND ID_RL_UTILIZADOR_MEDICAMENTO = " + rlUserMed.getIdRlUtilizadorMedicamento()); 
		return hql.toString();
	}
	
	/**
	 * Query select medicamento
	 * @param email
	 * @param password
	 * @return
	 */
	public static String getQuerySelectMedicamento(Integer idUtilizador, Integer idMedicamento){
		StringBuffer hql = new StringBuffer();
		hql.append(" SELECT ");
		hql.append(" RL_UTILIZADOR_MEDICAMENTO.ID_RL_UTILIZADOR_MEDICAMENTO, ");
// DNEVES
//		/**
//		 * Select de um unico medicamento
//		 */
//		if(idMedicamento==null){
			hql.append(" RL_UTILIZADOR_MEDICAMENTO.ID_UTILIZADOR, ");
			hql.append(" RL_UTILIZADOR_MEDICAMENTO.ID_MEDICAMENTO, ");
			hql.append(" MEDICAMENTO.DESCRICAO DESC_MEDICAMENTO, ");
			hql.append(" TIPO_MEDICAMENTO.DESCRICAO DESC_TIPO, ");
			hql.append(" RL_UTILIZADOR_MEDICAMENTO.DATA_INICIO_DOSAGEM, ");
			hql.append(" RL_UTILIZADOR_MEDICAMENTO.DATA_FIM_DOSAGEM, ");
			hql.append(" RL_UTILIZADOR_MEDICAMENTO.NUMERO_DOSES, ");
			hql.append(" RL_UTILIZADOR_MEDICAMENTO.HORAS_INTERVALO, "); 
			hql.append(" RL_UTILIZADOR_MEDICAMENTO.ALARME, "); 
			hql.append(" RL_UTILIZADOR_MEDICAMENTO.DATA_ULTIMA_TOMA, ");
			hql.append(" RL_UTILIZADOR_MEDICAMENTO.ID_ALARME, "); 
			hql.append(" RL_UTILIZADOR_MEDICAMENTO.CREATED_BY, ");
			hql.append(" RL_UTILIZADOR_MEDICAMENTO.CREATION_DATE, "); 
			hql.append(" RL_UTILIZADOR_MEDICAMENTO.UPDATED_BY, ");  
			hql.append(" RL_UTILIZADOR_MEDICAMENTO.UPDATE_DATE, ");   
//		}
		hql.append(" RL_UTILIZADOR_MEDICAMENTO.ENABLED ");
		hql.append(" FROM RL_UTILIZADOR_MEDICAMENTO ");
		hql.append(" INNER JOIN MEDICAMENTO ON MEDICAMENTO.ID_MEDICAMENTO = RL_UTILIZADOR_MEDICAMENTO.ID_MEDICAMENTO  ");
		hql.append(" INNER JOIN TIPO_MEDICAMENTO ON TIPO_MEDICAMENTO.ID_TIPO_MEDICAMENTO = MEDICAMENTO.ID_TIPO_MEDICAMENTO  ");
		hql.append(" WHERE ");
		hql.append(" RL_UTILIZADOR_MEDICAMENTO.ID_UTILIZADOR =  '" + idUtilizador + "' ");
		/**
		 * Select de um unico medicamento
		 */
		if(idMedicamento!=null){
			hql.append(" AND RL_UTILIZADOR_MEDICAMENTO.ID_MEDICAMENTO =  '" + idMedicamento + "' ");
		}
		
		hql.append(" AND RL_UTILIZADOR_MEDICAMENTO.ENABLED =  1");
		return hql.toString();
	}
	
	/**
	 * Metodo usado para calcular a data de fim de medicação
	 * @param dataInicio
	 * @param horasFinais
	 * @return
	 */
	private static Date calculaDataFinal(Date dataInicio, int horasFinais){
		Date dataFim = new Date(dataInicio.getTime());
		dataFim = DateUtils.addHours(dataFim, horasFinais);
		return dataFim;
	}
	
}
