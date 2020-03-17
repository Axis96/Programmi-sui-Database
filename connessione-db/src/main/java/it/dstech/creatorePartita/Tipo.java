package it.dstech.creatorePartita;

public enum Tipo {

	FUOCO ("FUOCO" ,0),
	ACQUA ("ACQUA" ,1),
	ARIA("ARIA" ,2),
	TERRA("TERRA" ,3);
	
	
	private String value;
	private int indice;
	
	Tipo(String value, int indice) {
		this.value=value;
		this.indice=indice;
	}
	
	Tipo(String value) {
		this.value=value;
	}
	
	Tipo(int indice) {
		this.indice=indice;
	}
}
