package com.mymeds.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;

import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.PropertyConfigurator;

import com.mymeds.utils.ServerConstants;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;


/**
 * @author daniel.neves 26.05.2017
 * 
 */

@SuppressWarnings("restriction")
public class MyMedsHTTPServer {

	public static void main(String[] args) throws IOException {
		loadLogProperties();
		System.out.println("Starting MyMeds HTTPService \n");
		HttpServer crunchifyHTTPServer = createHttpServer();
		crunchifyHTTPServer.start();
		System.out.println(
				String.format("\nJersey Application Server started with WADL available at " + "%sapplication.wadl\n",
						getURI()));
		System.out.println("Started MyMeds Embedded Jersey HTTPServer Successfully !!!");
	}

	private static HttpServer createHttpServer() throws IOException {
		ResourceConfig resourceConfig = new PackagesResourceConfig("com.mymeds.service");
		resourceConfig.getProperties().put("com.sun.jersey.spi.container.ContainerRequestFilters", "Server.Config.ServerConfig");
		return HttpServerFactory.create(getURI(), resourceConfig);
	}

	private static URI getURI() {
		return UriBuilder.fromUri("http://82.154.180.140/").port(8088).build();
	}

	/**
	 * Carrega o ficheiro de propriedades dos logs
	 */
	public static void loadLogProperties() {

		try {
			File file = new File(ServerConstants.PROPS_LOG_FILE);
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			PropertyConfigurator.configure(properties);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carrega o ficheiro de propriedades
	 */
	public static void loadProperties() {

		try {
			File file = new File(ServerConstants.PROPS_FILE);
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();

			Enumeration<Object> enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				System.setProperty(key, value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String getHostName() {
		String hostName = "localhost";
		try {
			hostName = InetAddress.getLocalHost().getCanonicalHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return hostName;
	}
}
