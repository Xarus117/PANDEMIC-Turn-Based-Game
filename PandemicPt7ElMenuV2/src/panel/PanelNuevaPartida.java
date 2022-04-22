package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PanelNuevaPartida extends JPanel implements ActionListener {

	JButton boton1;
	JButton boton2;
	JButton boton3;
	ImageIcon buttonIcon;
	Image image;

	PanelNuevaPartida() {
		setLayout(null);

			boton1 = new JButton("Buscar cura");
			boton1.setSize(200, 50);
			boton1.setLocation(550, 600);
			boton1.setFont(new Font("Calibri", Font.BOLD, 20));
			boton1.setBorder(null);
			boton1.setBorderPainted(false);
			boton1.setContentAreaFilled(false);
			boton2 = new JButton("Curar ciudad");
			boton2.setSize(200, 50);
			boton2.setLocation(550, 650);
			boton2.setFont(new Font("Calibri", Font.BOLD, 20));
			boton2.setBorder(null);
			boton2.setBorderPainted(false);
			boton2.setContentAreaFilled(false);
			boton3 = new JButton("Aplicar Vacuna");
			boton3.setSize(200, 50);
			boton3.setLocation(550, 700);
			boton3.setFont(new Font("Calibri", Font.BOLD, 20));
			boton3.setBorder(null);
			boton3.setBorderPainted(false);
			boton3.setContentAreaFilled(false);
		

		boton1.addActionListener(this);
		boton2.addActionListener(this);
		
		add(boton1);
		add(boton2);
		add(boton3);

		
		try {
			image = ImageIO.read(new File("Imagenes//Mapa.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == boton1) {
		} else if (e.getSource() == boton2) {
			
		} else {
			System.exit(0); // Cuando se pulse salir, se cerrará el juego
		}
	}
}
