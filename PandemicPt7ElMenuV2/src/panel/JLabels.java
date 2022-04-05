package panel;

import java.awt.Color;
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
