package com.mymeds.database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mymeds.utils.ServerConstants;



/**
 *
 * @author daniel.neves 
 */
public class DataBaseConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(DataBaseConfig.class);

	private Connection connection = null;
	private Statement stmt = null;
	private PreparedStatement prepStmt = null;

	/***
	 * inicia a ligacao a base de dados
	 *
	 * @throws Exception
	 */
	public DataBaseConfig() throws Exception {
		log.debug("[DataBaseConfig] Inicio da ligacao a base de dados");

		/** Verifica se tem o driver da oracle **/
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			log.debug("[DataBaseConfig] Oracle Drive not found!");
			e.printStackTrace();
			throw (new Exception("Oracle Drive not found!"));
		}

		try {
			this.connection = DriverManager.getConnection(ServerConstants.DB_URL_KEY, ServerConstants.DB_USER_KEY, ServerConstants.DB_PASS_KEY);
		} catch (SQLException e) {
			log.debug("[DataBaseConfig] Erro a fazer a ligação!");
			e.printStackTrace();
			throw (new Exception("Connection fail!"));
		}
	}

	/**
	 * Close Statement and connection
	 */
	public void closeConnection() {
		try {
			if (this.stmt != null) {
				this.stmt.close();
				this.stmt = null;
				log.debug("[DataBaseConfig.closeConnection]close Statement");
			}
		} catch (Exception e) {
			log.error("[DataBaseConfig.closeConnection]Error fazer close Statement", e);
		}
		try {
			if (this.prepStmt != null) {
				this.prepStmt.close();
				this.prepStmt = null;
				log.debug("[DataBaseConfig.closeConnection]close PreparedStatement");
			}
		} catch (Exception e) {
			log.error("[DataBaseConfig.closeConnection]Error fazer close PreparedStatement", e);
		}
		try {
			if (this.connection != null) {
				this.connection.commit();
				this.connection.close();
				log.debug("[DataBaseConfig.closeConnection]close connection");
			}
		} catch (Exception e) {
			log.error("[DataBaseConfig.closeConnection]Error fazer close connection", e);
		}
	}

	/**
	 * Executa um Delete
	 *
	 * @param update
	 */
	public void executeDelete(String delete) {

		try {
			log.debug("[DataBaseConfig.executeUpdate]Executa Delete : " + delete);
			this.stmt = this.connection.createStatement();
			this.stmt.executeUpdate(delete);
		} catch (Exception e) {
			log.error("[DataBaseConfig.executeUpdate]Error a executar delete", e);
		}
	}

	/**
	 * Executa um Insert
	 *
	 * @param update
	 */
	public int executeInsert(String insert) {

		try {
			log.debug("[DataBaseConfig.executeInsert]Executa Insert : " + insert);
			this.stmt = this.connection.createStatement();
			return this.stmt.executeUpdate(insert);
		} catch (Exception e) {
			log.error("[DataBaseConfig.executeInsert]Error a executar insert", e);
			return 0;
		}
	}

	/**
	 * Executa um select
	 *
	 * @param select
	 * @return ResultSet
	 */
	public ResultSet executeSelect(String select) {

		try {
			log.debug("[DataBaseConfig.executeSelect]Executa Select : " + select);
			this.stmt = this.connection.createStatement();
			this.stmt.execute(select);
			return this.stmt.getResultSet();
		} catch (Exception e) {
			log.error("[DataBaseConfig.executeSelect]Error a executar select", e);
		}
		return null;
	}

	/**
	 * Executa um Update
	 *
	 * @param update
	 */
	public int executeUpdate(String update) {

		try {
			log.debug("[DataBaseConfig.executeUpdate]Executa Update : " + update);
			this.stmt = this.connection.createStatement();
			return this.stmt.executeUpdate(update);
		} catch (Exception e) {
			log.error("[DataBaseConfig.executeUpdate]Error a executar update", e);
			return 0;
		}
	}

}