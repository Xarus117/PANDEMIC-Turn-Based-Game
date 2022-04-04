package panel;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class JButtons extends JButton {

	String nombre;
	JLabel textos;

	public JButtons(String nombre, int x, int y) {
		this.nombre = nombre;
		this.setText(nombre);
		this.setBounds(x - 90, y - 95, 300, 300);
		this.setForeground(Color.WHITE);
		this.setBorder(null);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
	
	}
	
};
