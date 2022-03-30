package panel;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JLabels extends JLabel{

	String nombre;
	String coordenada;
	
	public JLabels(String nombre, String coordenada) {
		this.nombre = nombre;
		try {
			this.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//OjeteNo.png"))));
		} catch (IOException e) {
			System.out.println("Toni maricon");
			e.printStackTrace();
		}
		this.coordenada = coordenada;
	}
	
	
	

}
