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

public class JLabels extends JLabel {
	String nombre;

	public JLabels(String nombre, int x, int y) {
		
		this.nombre = nombre;
		this.setBounds(x - 90, y - 95, 150, 150);
		this.setBackground(Color.WHITE);
		this.setVisible(true);
	}
	
}
