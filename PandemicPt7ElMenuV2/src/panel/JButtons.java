package panel;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class JButtons extends JButton{

	String nombre;
	String coordenada;
	
	
	
	public JButtons(String nombre, int x, int y) {
		this.nombre = nombre;
		this.setBounds(x - 90, y -95, 15, 15);
		this.setForeground(Color.WHITE);
		this.setBorder(null);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
	}
	
	
	

}
