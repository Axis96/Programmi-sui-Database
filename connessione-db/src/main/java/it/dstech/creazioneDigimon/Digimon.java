package it.dstech.creazioneDigimon;

import it.dstech.creatorePartita.StadioEvolutivo;
import it.dstech.creatorePartita.Tipo;

public class Digimon {

	private String nome;
	private int HP;
	private int ATK;
	private int DEF;
	private int RES;
	private String Evo;
	private String tipo;
	
	
	
	
	public Digimon(String nome, int hP, int aTK, int dEF, int rES, String evo, String tipo) {
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
	public String getEvo() {
		return Evo;
	}
	public void setEvo(String evo) {
		Evo = evo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "nuovoDigimon [nome=" + nome + ", HP=" + HP + ", ATK=" + ATK + ", DEF=" + DEF + ", RES=" + RES + ", Evo="
				+ Evo + ", tipo=" + tipo + "]";
	}
	



	

}
