package it.dstech.creazioneDigimon;

import java.util.ArrayList;

import java.util.List;


public class Giocatore {
	private int idUtente;
	private String nome;
	List<Digimon> squadraDigimon = new ArrayList<Digimon>();
	
	
	public Giocatore(int idUtente, String nome) {
		super();
		this.idUtente = idUtente;
		this.nome = nome;
	}


	public int getIdUtente() {
		return idUtente;
	}


	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public List<Digimon> getSquadraDigimon() {
		return squadraDigimon;
	}


	public void setSquadraDigimon(List<Digimon> squadraDigimon) {
		this.squadraDigimon = squadraDigimon;
	}


	@Override
	public String toString() {
		return "Giocatore [idUtente=" + idUtente + ", nome=" + nome + ", squadraDigimon=" + squadraDigimon + "]";
	}

	
	
}
