package panel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;


public class JButtons extends JButton {

	private String nombre;

	

	public JButtons(String nombre, int x, int y) {
		String nombreCompleto = (nombre + ": [" + x + " | " + y + "]" + " \nActualmente esta infectada por la enfermedad"+"\nRoja: " + Ciudades.Roja + "\n Verde: " + Ciudades.Verde + "\n Amarilla: " + Ciudades.Amarilla + "\n Azul: " + Ciudades.Azul);
		
		Image im = Toolkit.getDefaultToolkit().createImage("imagenes//cursorDefecto.png");
		Image im2 = Toolkit.getDefaultToolkit().createImage("imagenes//cursorHover.png");
		Cursor cur = Toolkit.getDefaultToolkit().createCustomCursor(im, new Point(10, 10), "WILL");
		Cursor cur2 = Toolkit.getDefaultToolkit().createCustomCursor(im2, new Point(10, 10), "WILL");
		this.setName(nombreCompleto); 
		this.nombre = nombre;
		this.setBounds(x - 90, y - 95, 15, 15);
		this.setForeground(Color.WHITE);
		this.setBorder(null);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.setVisible(true);
		this.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				setCursor(cur);
			}
		});
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
};
