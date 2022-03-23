package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelNuevaPartida extends JPanel implements ActionListener {

	JButton boton1;
	JButton boton2;
	JButton boton3;
	Image image;
	Image vacunaAzul;
	Image vacunaAmarilla;
	Image vacunaRoja;
	Image vacunaVerde;

	PanelNuevaPartida() {
		setLayout(null);

		boton1 = new JButton("Buscar cura");
		boton1.setSize(200, 50);
		boton1.setLocation(400, 740);
		boton1.setFont(new Font("Calibri", Font.BOLD, 20));
		boton1.setForeground(Color.WHITE);
		boton1.setBackground(Color.green);
		boton1.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				boton1.setBackground(Color.GRAY);
			}

			public void mouseExited(MouseEvent e) {
				boton1.setBackground(Color.green);
			}
		});
		boton2 = new JButton("Curar ciudad");
		boton2.setSize(200, 50);
		boton2.setLocation(650, 740);
		boton2.setFont(new Font("Calibri", Font.BOLD, 20));
		boton2.setForeground(Color.WHITE);
		boton2.setBackground(Color.green);
		boton2.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				boton2.setBackground(Color.GRAY);
			}

			public void mouseExited(MouseEvent e) {
				boton2.setBackground(Color.green);
			}
		});
		boton3 = new JButton("Aplicar Vacuna");
		boton3.setSize(200, 50);
		boton3.setLocation(400, 800);
		boton3.setFont(new Font("Calibri", Font.BOLD, 20));
		boton3.setForeground(Color.WHITE);
		boton3.setBackground(Color.green);
		boton3.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				boton3.setBackground(Color.GRAY);
			}

			public void mouseExited(MouseEvent e) {
				boton3.setBackground(Color.green);
			}
		});

		boton1.addActionListener(this);
		boton2.addActionListener(this);

		add(boton1);
		add(boton2);
		add(boton3);

		try {
			image = ImageIO.read(new File("Imagenes//Mapa.jpg"));
			vacunaAzul = ImageIO.read(new File("Imagenes//vacunaAzul.png"));
			vacunaAmarilla = ImageIO.read(new File("Imagenes//vacunaAmarilla.png"));
			vacunaRoja = ImageIO.read(new File("Imagenes//vacunaRoja.png"));
			vacunaVerde = ImageIO.read(new File("Imagenes//vacunaVerde.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(image, 0, 0, this);
		g.drawImage(vacunaVerde, 930, 750, this);
		g.drawImage(vacunaRoja, 1000, 750, this);
		g.drawImage(vacunaAzul, 1070, 750, this);
		g.drawImage(vacunaAmarilla, 1140, 750, this);
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
