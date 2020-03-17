package it.dstech.creazioneDigimon;

import it.dstech.creatorePartita.StadioEvolutivo;
import it.dstech.creatorePartita.Tipo;

public class Digimon {

	private String nome;
	private int HP;
	private int ATK;
	private int DEF;
	private int RES;
	StadioEvolutivo Evo;
	Tipo tipo;
	



	public Digimon(String nome, int hP, int aTK, int dEF, int rES, StadioEvolutivo evo, Tipo tipo) {
		super();
		this.nome = nome;
		HP = hP;
		ATK = aTK;
		DEF = dEF;
		RES = rES;
		Evo = evo;
		this.tipo = tipo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getHP() {
		return HP;
	}


	public void setHP(int hP) {
		HP = hP;
	}


	public int getATK() {
		return ATK;
	}


	public void setATK(int aTK) {
		ATK = aTK;
	}

	
	public int getDEF() {
		return DEF;
	}


	public void setDEF(int dEF) {
		DEF = dEF;
	}


	public int getRES() {
		return RES;
	}


	public void setRES(int rES) {
		RES = rES;
	}


	public StadioEvolutivo getEvo() {
		return Evo;
	}


	public void setEvo(StadioEvolutivo evo) {
		Evo = evo;
	}


	public Tipo getTipo() {
		return tipo;
	}


	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}


	@Override
	public String toString() {
		return "Digimon [nome=" + nome + ", HP=" + HP + ", ATK=" + ATK + ", DEF=" + DEF + ", RES=" + RES + ", Evo="
				+ Evo + ", tipo=" + tipo + "]";
	}


}
