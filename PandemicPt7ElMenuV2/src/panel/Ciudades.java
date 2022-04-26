package panel;

public class Ciudades {
	private String nombre;
	private int Roja;
	private int Verde;
	private int Amarilla;
	private int Azul;
	
	public Ciudades(String nombre) {
		super();
		this.nombre = nombre;
		Roja = 0;
		Verde = 0;
		Amarilla = 0;
		Azul = 0;
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
