package it.dstech.creazioneDigimon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class GestioneDigimon {
	static Scanner scanner=new Scanner(System.in);
public static void main(String[] args) throws SQLException, ClassNotFoundException {
	Class.forName("com.mysql.cj.jdbc.Driver");
	String password ="SWCTvf0TtX";
	String username = "J2bCsBdKMg";
	String url = "jdbc:mysql://remotemysql.com:3306/J2bCsBdKMg?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC&createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false";
	Connection connessione = DriverManager.getConnection(url, username, password);
	
	while(true) {
		menu();
		int scelta=scanner.nextInt();
		scanner.nextLine();
		
		switch (scelta) {
		case 1:
			creazioneDigimon(connessione);	
			break;
		case 2:
			rimozioneDigimon(connessione);
			break;
		case 0:
			System.exit(0);
			break;
		}
	}
}
	
	
	
	private static void creazioneDigimon(Connection connessione) throws SQLException {
		String queryInserimentoDigimon= "INSERT INTO Digimon (nome, HP, ATK, DEF, RES, EVO, idUtente, tipo) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement prepareStatement = connessione.prepareStatement(queryInserimentoDigimon);
		System.out.println("Inserisci il nome");
		prepareStatement.setString(1, scanner.nextLine());
		prepareStatement.setInt(2, randInt(1000 ,1600 ) );
		prepareStatement.setInt(3, randInt(100 ,150 ) );
		prepareStatement.setInt(4, randInt(10 ,30 ) );
		prepareStatement.setInt(5, randInt(5 , 10) );
		System.out.println("dammi lo stadio evolutivo del digimon");
		System.out.println("PRIMARIO,INTERMEDIO,\r\n" + 
	            "CAMPIONE,EVOLUTO,MEGA");
		prepareStatement.setString(6, scanner.nextLine());
		System.out.println("Inserisci l'id dell'utente");
		prepareStatement.setString(7, scanner.nextLine());
		System.out.println("Inserisci il tipo del Digimon");
		System.out.println("FUOCO,ACQUA,\r\n" + 
	            "ARIA,TERRA");
		prepareStatement.setString(8, scanner.nextLine());
		prepareStatement.execute();
	}


	private static void rimozioneDigimon(Connection connessione) throws SQLException {
		PreparedStatement prepareStatement = connessione.prepareStatement("select * from Digimon;");
		ResultSet executeQuery = prepareStatement.executeQuery();
		System.out.println("Ecco i digimon nel database: ");
	
		stampa(executeQuery);
		
		String queryRimozioneDigimon= "DELETE FROM Digimon WHERE id= ?";
		PreparedStatement prepareStatement2 = connessione.prepareStatement(queryRimozioneDigimon);
		System.out.println("Inserisci l'id del Digimon da rimuovere");
		prepareStatement2.setInt(1, scanner.nextInt());
		prepareStatement2.execute();
		System.out.println("Digimon eliminato");
	}
	public static void stampa(ResultSet executeQuery) throws SQLException {
		while (executeQuery.next()) {
			int id =executeQuery.getInt(1);
			String nome=executeQuery.getString("nome");
			int hp = executeQuery.getInt(3);
			int atk = executeQuery.getInt(4);
			int def = executeQuery.getInt(5);
			int res = executeQuery.getInt(6);
			String evo = executeQuery.getString(7);
			int idUtente = executeQuery.getInt(8);
			String tipo = executeQuery.getString(9);
			System.out.println(id + " " + nome + " " + hp + " " + atk + " " + def + " " + res + " " + evo + " " + idUtente + " " + tipo);
		}
	}
	public static int randInt(int min, int max ) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max-min)-1)+min;
		return randomNum;
	}
	public static void menu() {
		System.out.println("Cosa vuoi fare?");
		System.out.println("1. Crea un Digimon nel database");
		System.out.println("2. Rimuovi un Digimon dal database");
		System.out.println("0. Esci");
	}
}
