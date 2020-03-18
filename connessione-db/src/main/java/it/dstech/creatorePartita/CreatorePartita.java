package it.dstech.creatorePartita;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import it.dstech.creazioneDigimon.Digimon;
import it.dstech.creazioneDigimon.Giocatore;


public class CreatorePartita {
	static Scanner scanner=new Scanner(System.in);
	static List<Giocatore> utente = new ArrayList<Giocatore>();
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
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
				creaPartita(connessione);
				 break;
			case 2:
				giocaPartita(connessione);
				break;
			case 0:
				System.exit(0);
				break;
			}
		}
	}
	
	
	public static void giocaPartita(Connection connessione) throws SQLException, InterruptedException {
		while(true) {
			System.out.println("Attendere che lo sfidante abbia inserito i suoi Digimon nella partita, poi premere 1");
			int avvio = scanner.nextInt();
			scanner.nextLine();
			if(avvio==1 && controlloSfidante(connessione)) {
				giocaArena(connessione);
			}
		}
	}
	
	
	public static void giocaArena(Connection connessione) throws SQLException, InterruptedException{
		int numeroDigimon=0;
		for(Digimon dig: utente.get(0).getSquadraDigimon()) {
			numeroDigimon++;
			for (int numeroTurno = 1; numeroTurno <= 5; numeroTurno++) {
				generaTurno(dig, numeroDigimon, numeroTurno, connessione);
			} 
		}
	}	
	
	
	public static void generaTurno(Digimon dig ,int numeroDigimon, int numeroTurno , Connection connessione) throws InterruptedException, SQLException {
		PreparedStatement getId = connessione.prepareStatement("select MAX(idPartita) from Partita ;");
		ResultSet executeId = getId.executeQuery();
		int idPartita=0;
		while(executeId.next()) {
			idPartita=executeId.getInt(1);
		}
		
		PreparedStatement idGiocatore = connessione.prepareStatement("select idCreatore from Partita where idPartita = ?;");
		idGiocatore.setInt(1, idPartita);
		
		String queryCreazioneArena= "INSERT INTO Arena ( idPartita, turno ) VALUES (?, ?, ?, ?, ?);";
		PreparedStatement prepareStatement = connessione.prepareStatement(queryCreazioneArena);
		prepareStatement.setInt(1, idPartita);
		prepareStatement.setInt(2, utente.get(0).getIdUtente());
		
		prepareStatement.setInt(3, generaAttacco(dig,numeroDigimon, connessione));
		prepareStatement.setInt(4, generaHP(dig, numeroDigimon, numeroTurno, idPartita, connessione));

	}
	
	
	
	public static int generaHP(Digimon dig, int numeroDigimon, int numeroTurno, int idPartita , Connection connessione) throws SQLException{
		if(numeroTurno==1) {
			String querySelezioneTipo= "SELECT Digimon.id,  Digimon.DEF, Digimon.RES, Digimon.HP Partita.ds\""+numeroDigimon+"\" ;  \r\n" + 
				"		FROM Digimon" + 
				"		INNER JOIN Partita ON Partita.ds\""+numeroDigimon+"\"=Digimon.id;";
			PreparedStatement prepareStatement = connessione.prepareStatement(querySelezioneTipo);
			ResultSet execute = prepareStatement.executeQuery();
			int difesa =0;
			int resistenza=0;
			int HP =0;
			while(execute.next()) {
				difesa=execute.getInt("DEF");
				resistenza=execute.getInt("RES");
				HP =execute.getInt("HP");
			}
			return HP - dig.calcoloHPSfidante(difesa,resistenza,generaAttacco(dig,numeroDigimon,connessione));
		} else {
			String querySelezioneTipo= ("SELECT HP-DS FROM Arena where idMossa= ? and idPartita= ?;");
			PreparedStatement prepareStatement = connessione.prepareStatement(querySelezioneTipo);
			ResultSet execute = prepareStatement.executeQuery();
			prepareStatement.setInt(1, numeroDigimon*numeroTurno);
			prepareStatement.setInt(1, idPartita);

			int HP =0;
				while(execute.next()) {
					HP=execute.getInt("HP-DS");
				}
				return HP - dig.calcoloHPSfidante(difesa,resistenza,generaAttacco(dig,numeroDigimon,connessione));
		}
	}
	
	
	public static int generaAttacco(Digimon dig, int i, Connection connessione) throws SQLException{
		String querySelezioneTipo= "SELECT Digimon.id,  Digimon.tipo, Partita.ds\""+i+"\" ;  \r\n" + 
				"		FROM Digimon" + 
				"		INNER JOIN Partita ON Partita.ds\""+i+"\"=Digimon.id;";
		PreparedStatement prepareStatement = connessione.prepareStatement(querySelezioneTipo);
		ResultSet executeId = prepareStatement.executeQuery();
		String tipoSfidante ="";
		while(executeId.next()) {
			tipoSfidante=executeId.getString("tipo");
		}
		return dig.calcoloVantaggio(tipoSfidante);
	}
	
	
	public static boolean controlloTurno(Connection connessione) throws SQLException, InterruptedException {
		boolean condizione= true;
		while(condizione) {
			System.out.println("Aspetto 10 secondi per controllare se è il tuo turno");
			for(int sec=0; sec<10; sec++) {
				System.out.println(sec+1);
				Thread.sleep(1000);
			}
			if(condizione) {
			
			} else {
				condizione=false;
			}
		}
		return true;
	}
	
	
	public static boolean controlloSfidante(Connection connessione) throws SQLException {
		PreparedStatement getId = connessione.prepareStatement("select MAX(idPartita) from Partita ;");
		ResultSet executeId = getId.executeQuery();
		int idPartita=0;
		while(executeId.next()) {
			idPartita=executeId.getInt(1);
		}
		PreparedStatement controlloAvvio = connessione.prepareStatement("select * from Partita where idPartita = ? ;");
		controlloAvvio.setInt(1, idPartita);
		ResultSet execute = controlloAvvio.executeQuery();
		while(execute.next()) {
			if(execute.getBoolean("idSfidante") && execute.getBoolean("ds1") && execute.getBoolean("ds2") && execute.getBoolean("ds3")) {
				return true;
			} 
		} 
		return false;
	}
	
	
	public static void creaPartita(Connection connessione) throws SQLException {
		String queryCreazionePartita= "INSERT INTO Partita ( idCreatore, password, dc1, dc2, dc3) VALUES (?, ?, ?, ?, ?);";
		PreparedStatement prepareStatement = connessione.prepareStatement(queryCreazionePartita);
		System.out.println("Inserisci il tuo id");
		int idPrimo= scanner.nextInt();
		scanner.nextLine();
		System.out.println("Inserisci la password della partita");
		String password= scanner.nextLine();

		PreparedStatement sceltaDigimon = connessione.prepareStatement("select * from Digimon where idUtente =?;");
		sceltaDigimon.setInt(1, idPrimo);
		ResultSet listaDigimon = sceltaDigimon.executeQuery();
		
		System.out.println("Ecco i digimon che hai a disposizione: ");
		stampa(listaDigimon);		
		
		int[] digimonSelezionati=selezionaDigimon(connessione);
		
		prepareStatement.setInt(1, idPrimo);
		prepareStatement.setString(2, password);
		prepareStatement.setInt(3, digimonSelezionati[0]);
		prepareStatement.setInt(4, digimonSelezionati[1]);
		prepareStatement.setInt(5, digimonSelezionati[2]);
		prepareStatement.execute();
		
		Giocatore primoGiocatore = new Giocatore(idPrimo, "Matteo");
		
		for (int idDigimonScelto : digimonSelezionati) {
			PreparedStatement creaDigimon = connessione.prepareStatement("select * from Digimon where id =?;");
			creaDigimon.setInt(1, idDigimonScelto);
			ResultSet statDigimon = creaDigimon.executeQuery();
			
			primoGiocatore.getSquadraDigimon().add(creazioneClasseDigimon(statDigimon));
		}
		utente.add(primoGiocatore);
	}
	
	
	public static Digimon creazioneClasseDigimon(ResultSet statDigimon) throws SQLException{
		Digimon nuovoDigimon = null;
		while (statDigimon.next()) {
			int id =statDigimon.getInt(1);
			String nome=statDigimon.getString("nome");
			int hp = statDigimon.getInt(3);
			int atk = statDigimon.getInt(4);
			int def = statDigimon.getInt(5);
			int res = statDigimon.getInt(6);
			String stadio = statDigimon.getString(7);
			String tipo2 =statDigimon.getString(9);
			

			nuovoDigimon= new Digimon(id,nome, hp, atk, def, res, stadio, tipo2);
		}
		return nuovoDigimon;
	}
	
	
	public static int[] selezionaDigimon(Connection connessione) throws SQLException {
		
		int[] idSelezionati = new int[3];	
		System.out.println("Inserisci l'id del primo Digimon da selezionare");
		idSelezionati[0]= scanner.nextInt();
		scanner.nextLine();
		System.out.println("Inserisci l'id del secondo Digimon da selezionare");
		idSelezionati[1]= scanner.nextInt();
		scanner.nextLine();
		System.out.println("Inserisci l'id del terzo Digimon da selezionare");
		idSelezionati[2]= scanner.nextInt();
		scanner.nextLine();
		
		return idSelezionati;
	}
	
	
	public static void menu() {
		System.out.println("Cosa vuoi fare?");
		System.out.println("1. Crea partita");
		System.out.println("2. Gioca partita");
		System.out.println("0. Esci");
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
}
