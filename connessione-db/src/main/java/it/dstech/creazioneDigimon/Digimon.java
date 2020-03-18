package it.dstech.creazioneDigimon;

public class Digimon {

	private int id;
	private String nome;
	private int HP;
	private int ATK;
	private int DEF;
	private int RES;
	private String Evo;
	private String tipo;
	
	
	
	
	public Digimon(int id, String nome, int hP, int aTK, int dEF, int rES, String evo, String tipo) {
		super();
		this.id= id;
		this.nome = nome;
		HP = hP;
		ATK = aTK;
		DEF = dEF;
		RES = rES;
		Evo = evo;
		this.tipo = tipo;
	}
	
	
	public int calcoloHPSfidante(int difesa, int resistenza, int attacco) {
		int danniSubiti =  (attacco-difesa)*(resistenza/100);
		return danniSubiti;
	}
	
	
	public int calcoloVantaggio(String tipoSfidante){
		int atk=this.ATK;
		if (this.tipo.equals("FUOCO") && tipoSfidante.equals("ACQUA")) {
			return atk *= 0.5;
		} else if (this.tipo.equals("FUOCO") && tipoSfidante.equals("TERRA")){
			return atk *= 2;
		} else if (this.tipo.equals("TERRA") && tipoSfidante.equals("FUOCO")){
			return atk *= 0.5;
		} else if (this.tipo.equals("TERRA") && tipoSfidante.equals("ARIA")){
			return atk *= 2;
		} else if (this.tipo.equals("ACQUA") && tipoSfidante.equals("ARIA")){
			return atk *= 0.5;
		} else if (this.tipo.equals("ACQUA") && tipoSfidante.equals("FUOCO")){
			return atk *= 2;
		} else if (this.tipo.equals("ARIA") && tipoSfidante.equals("TERRA")){
			return atk *= 0.5;
		} else if (this.tipo.equals("ARIA") && tipoSfidante.equals("ACQUA")){
			return atk *= 2;
		}
		return atk;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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
		return "[nome=" + nome + ", HP=" + HP + ", ATK=" + ATK + ", DEF=" + DEF + ", RES=" + RES + ", Evo="
				+ Evo + ", tipo=" + tipo + "]";
	}

}
