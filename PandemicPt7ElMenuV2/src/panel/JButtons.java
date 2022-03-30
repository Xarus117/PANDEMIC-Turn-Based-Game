package panel;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;

public class JButtons extends JButton{

	String nombre;
	String coordenada;
	
	
	
	public JButtons(String nombre, int x, int y) {
		this.nombre = nombre;
		this.setBounds(x - 90, y -95, 10, 10);
		this.setForeground(Color.WHITE);
	}
	
	
	

}
