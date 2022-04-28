package panel;

public class Ciudades {
	private String nombre;
	private int Roja;
	private int Verde;
	private int Amarilla;
	private int Azul;
	private String [] colindantes = new String [5];
	private String guardarCol;
	
	public Ciudades(String nombre, String[] colindantes, String guardarCol) {

		this.nombre = nombre;
		Roja = 0;
		Verde = 0;
		Amarilla = 0;
		Azul = 0;
		this.colindantes = colindantes;
		this.guardarCol = guardarCol;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getguardarCol() {
		return nombre;
	}


	public void setguardarCol(String guardarCol) {
		this.guardarCol = guardarCol;
	}
	
	public String[] getColindantes() {
		return colindantes;
	}


	public void setColindantes(String[] colindantes) {
		this.colindantes = colindantes;
	}

	public int getRoja() {
		return Roja;
	}


	public void setRoja(int roja) {
		if(Roja >= 3) {
			Roja = 3;
		}else {
			Roja = roja;
		}
	}


	public int getVerde() {
		return Verde;
	}


	public void setVerde(int verde) {
		if(Verde >= 3) {
			Verde = 3;
		}else {
			Verde = verde;
		}
	}


	public int getAmarilla() {
		return Amarilla;
	}


	public void setAmarilla(int amarilla) {
		if(Amarilla >= 3) {
			Amarilla = 3;
		}else {
			Amarilla = amarilla;
		}
	}


	public int getAzul() {
		return Azul;
	}


	public void setAzul(int azul) {
		if(Azul >= 3) {
			Azul = 3;
		}else {
			Azul = azul;
		}
	}
}
