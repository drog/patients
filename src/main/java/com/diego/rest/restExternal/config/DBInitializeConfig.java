package com.diego.rest.restExternal.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBInitializeConfig {

	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void initialize(){
		try {
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			
			
			/*
			 * Patients
			 */
			statement.execute("DROP TABLE IF EXISTS patient");
			statement.executeUpdate(
					"CREATE TABLE patient(" +
					"id_patient integer primary key, " +
					"nip varchar(30) not null," +
					"name varchar(100) not null," +
					"birth_date varchar(30), " +
					"age varchar(30)," +
					"sex varchar(30))" 
					);
			statement.executeUpdate(
					"INSERT INTO patient " +
					"(id_patient, nip,name,birth_date,age,sex) " +
					"VALUES " + "(10000631, '179911043','Diego Canales','697777200000','25',"
						+ " 'Hombre')"
					);
			
			/*
			 * summarized_clinical_history
			 */
			statement.execute("DROP TABLE IF EXISTS summarized_clinical_history");
			statement.executeUpdate(
					"CREATE TABLE summarized_clinical_history(" +
					"id_summarized_clinical_history integer primary key," +
					"id_patient integer, " +
					"FOREIGN KEY(id_patient) REFERENCES patient(id_patient))" );

			
			statement.executeUpdate(
					"INSERT INTO summarized_clinical_history " +
					"(id_patient) " +
					"VALUES " + "(10000631)"
					);
			
			
			/*
			 * file
			 */
			
			statement.execute("DROP TABLE IF EXISTS file");
			statement.executeUpdate(
					"CREATE TABLE file(" +
					"id_file integer primary key, " +
					"file_size integer, " +
					"file_type varchar(100), " +	
					"file_name varchar(100), " +
					"base64 varchar(9999999999))" 
					);
			
			
			/*
			 * document_summary
			 */
			
			statement.execute("DROP TABLE IF EXISTS document_summary");
			statement.executeUpdate(
					"CREATE TABLE document_summary(" +
					"id_document_summary integer primary key, " +
					"document_date varchar(30), " +
					"description varchar(100), " +	
					"code varchar(100), " +
					"ehcos_identification_id bigint UNIQUE, " +
					"doc_image varchar(100), " +
					"file varchar(9999999999), " +
					"id_summarized_clinical_history integer," +
					"id_file integer," +
					"FOREIGN KEY(id_summarized_clinical_history) REFERENCES summarized_clinical_history(id_summarized_clinical_history)," +
					"FOREIGN KEY(id_file) REFERENCES file(id_file))" 
					);
			
			statement.executeUpdate(
					"INSERT INTO document_summary " +
					"(document_date, description, code, ehcos_identification_id, id_summarized_clinical_history) " +
					"VALUES " + "('666', 'descripcion externa del documento', 'codigo externo del documento' , 666, 1)"
					);
			
			
			statement.close();
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}