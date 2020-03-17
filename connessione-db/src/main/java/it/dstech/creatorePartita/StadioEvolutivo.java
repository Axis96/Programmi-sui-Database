package it.dstech.creatorePartita;

public enum StadioEvolutivo {
	
	PRIMARIO ("PRIMARIO"),
	INTERMEDIO ("INTERMEDIO"),
	CAMPIONE ("CAMPIONE" ),
	EVOLUTO ("EVOLUTO" ),
	MEGA ("MEGA");

	private String value;
	private int indice;
	
	StadioEvolutivo(String value, int indice) {
		this.value=value;
		this.indice=indice;
	}
	
	StadioEvolutivo(String value) {
		this.value=value;
	}
	
	StadioEvolutivo(int indice) {
		this.indice=indice;
	}
}
