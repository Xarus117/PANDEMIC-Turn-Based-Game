package panel;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class PanelPrincipal extends JPanel implements ActionListener {

	JButton boton1;
	JButton boton2;
	JButton boton3;
	JButton boton4;
	JButton boton5;
	JButton boton6;
	JButton boton7;
	JButton boton8;
	ImageIcon buttonIcon;
	Image image;
	Image Logo;

	PanelPrincipal() {
		int partidas = 0;
		setLayout(null);

		if (partidas > 0) {
			boton1 = new JButton("Nueva Partida");
			boton1.setSize(200, 50);
			boton1.setLocation(550, 300);
			boton1.setFont(new Font("Arial", Font.BOLD, 18));
			boton1.setBackground(Color.green);
			boton2 = new JButton("Nueva Partida +");
			boton2.setSize(200, 50);
			boton2.setLocation(550, 370);
			boton2.setFont(new Font("Arial", Font.BOLD, 18));
			boton2.setBackground(Color.green);
			boton3 = new JButton("Cargar Partida");
			boton3.setSize(200, 50);
			boton3.setLocation(550, 440);
			boton3.setFont(new Font("Arial", Font.BOLD, 18));
			boton3.setBackground(Color.green);
			boton4 = new JButton("Información");
			boton4.setSize(200, 50);
			boton4.setLocation(550, 510);
			boton4.setFont(new Font("Arial", Font.BOLD, 18));
			boton4.setBackground(Color.green);
			boton5 = new JButton("Ranking");
			boton5.setSize(200, 50);
			boton5.setLocation(550, 580);
			boton5.setFont(new Font("Arial", Font.BOLD, 18));
			boton5.setBackground(Color.green);
			boton6 = new JButton("Autores");
			boton6.setSize(200, 50);
			boton6.setLocation(550, 650);
			boton6.setFont(new Font("Arial", Font.BOLD, 18));
			boton6.setBackground(Color.green);
			boton7 = new JButton("Versión");
			boton7.setSize(200, 50);
			boton7.setLocation(550, 720);
			boton7.setFont(new Font("Arial", Font.BOLD, 18));
			boton7.setBackground(Color.green);
			boton8 = new JButton("Salir");
			boton8.setSize(200, 50);
			boton8.setLocation(550, 790);
			boton8.setFont(new Font("Arial", Font.BOLD, 18));
			boton8.setBackground(Color.green);
		} else {
			boton1 = new JButton("Nueva Partida");
			boton1.setSize(200, 50);
			boton1.setLocation(550, 300);
			boton1.setFont(new Font("Arial", Font.BOLD, 18));
			boton1.setBackground(Color.green);
			boton3 = new JButton("Cargar Partida");
			boton3.setSize(200, 50);
			boton3.setLocation(550, 370);
			boton3.setFont(new Font("Arial", Font.BOLD, 18));
			boton3.setBackground(Color.green);
			boton4 = new JButton("Información");
			boton4.setSize(200, 50);
			boton4.setLocation(550, 440);
			boton4.setFont(new Font("Arial", Font.BOLD, 18));
			boton4.setBackground(Color.green);
			boton5 = new JButton("Ranking");
			boton5.setSize(200, 50);
			boton5.setLocation(550, 510);
			boton5.setFont(new Font("Arial", Font.BOLD, 18));
			boton5.setBackground(Color.green);
			boton6 = new JButton("Autores");
			boton6.setSize(200, 50);
			boton6.setLocation(550, 580);
			boton6.setFont(new Font("Arial", Font.BOLD, 18));
			boton6.setBackground(Color.green);
			boton7 = new JButton("Versión");
			boton7.setSize(200, 50);
			boton7.setLocation(550, 650);
			boton7.setFont(new Font("Arial", Font.BOLD, 18));
			boton7.setBackground(Color.green);
			boton8 = new JButton("Salir");
			boton8.setSize(200, 50);
			boton8.setLocation(550, 720);
			boton8.setFont(new Font("Arial", Font.BOLD, 18));
			boton8.setBackground(Color.green);
		}

		boton1.addActionListener(this);
		boton3.addActionListener(this);
		boton4.addActionListener(this);
		boton5.addActionListener(this);
		boton6.addActionListener(this);
		boton7.addActionListener(this);
		boton8.addActionListener(this);
		
		add(boton1);
		if (partidas > 0) {
			boton2.addActionListener(this);
			add(boton2);
		}
		add(boton3);
		add(boton4);
		add(boton5);
		add(boton6);
		add(boton7);
		add(boton8);

		
		try {
			image = ImageIO.read(new File("Imagenes//Fondo.jpg"));
			Logo = ImageIO.read(new File("Imagenes//LOGO.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(image, 0, -30, this);
		g.drawImage(Logo, 90, -80, this);

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == boton1) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new Login());
			marco.setVisible(true);
		} else if (e.getSource() == boton2) {
		} else if (e.getSource() == boton3) {
		} else if (e.getSource() == boton4) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelReglas());
			marco.setVisible(true);
		} else if (e.getSource() == boton5) {
		} else if (e.getSource() == boton6) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelAutores());
			marco.setVisible(true);
		} else if (e.getSource() == boton7) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelVersion());
			marco.setVisible(true);
		} else {
			System.exit(0); // Cuando se pulse salir, se cerrará el juego
		}
	}

}
