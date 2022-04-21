package panel;

public class Ciudades {
	static String nombre;
	static int Roja;
	static int Verde;
	static int Amarilla;
	static int Azul;
	
	public Ciudades(String nombre, int roja, int verde, int amarilla, int azul) {
		super();
		this.nombre = nombre;
		Roja = roja;
		Verde = verde;
		Amarilla = amarilla;
		Azul = azul;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getRoja() {
		return Roja;
	}


	public void setRoja(int roja) {
		Roja = roja;
	}


	public int getVerde() {
		return Verde;
	}


	public void setVerde(int verde) {
		Verde = verde;
	}


	public int getAmarilla() {
		return Amarilla;
	}


	public void setAmarilla(int amarilla) {
		Amarilla = amarilla;
	}


	public int getAzul() {
		return Azul;
	}


	public void setAzul(int azul) {
		Azul = azul;
	}
}
