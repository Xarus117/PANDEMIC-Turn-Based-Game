package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PanelReglas extends JPanel implements ActionListener {
	JButton volver;
	Image image;
	Image reglas;

	PanelReglas() {
		setLayout(null);

		volver = new JButton("Volver");
		volver.setSize(200, 50);
		volver.setLocation(40, 800);
		volver.setFont(new Font("Arial", Font.BOLD, 20));
		volver.setBackground(Color.green);

		volver.addActionListener(this);

		add(volver);
		try {
			image = ImageIO.read(new File("Imagenes//Fondo.jpg"));
			reglas = ImageIO.read(new File("Imagenes//Reglas.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(image, 0, 0, this);
		g.drawImage(reglas, 260, 50, this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == volver) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelPrincipal());
			marco.setVisible(true);
		}
	}
}
