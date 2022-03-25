package panel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
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

	JButton vacunaAzul;
	JButton vacunaAmarilla;
	JButton vacunaRoja;
	JButton vacunaVerde;
	JButton vacunaGris;

	// BOOLEAN PARA ACTIVAR O DESACTIVAR LAS VACUNAS
	boolean azulb = false;
	boolean amarillab = false;
	boolean rojab = false;
	boolean verdeb = false;
	boolean grisb = false;

	Image image;

	PanelNuevaPartida() {
		setLayout(null);
		Image im = Toolkit.getDefaultToolkit().createImage("imagenes//cursorDefecto.png");
		Image im2 = Toolkit.getDefaultToolkit().createImage("imagenes//cursorHover.png");
		Cursor cur = Toolkit.getDefaultToolkit().createCustomCursor(im, new Point(10, 10), "WILL");
		Cursor cur2 = Toolkit.getDefaultToolkit().createCustomCursor(im2, new Point(10, 10), "WILL");
		setCursor(cur);

		boton1 = new JButton("Buscar vacuna");
		boton1.setSize(200, 50);
		boton1.setLocation(400, 740);
		boton1.setFont(new Font("Calibri", Font.BOLD, 20));
		boton1.setForeground(Color.WHITE);
		boton1.setBackground(Color.green);
		boton1.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				boton1.setBackground(Color.GRAY);
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				boton1.setBackground(Color.green);
				setCursor(cur);
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
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				boton2.setBackground(Color.green);
				setCursor(cur);
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
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				boton3.setBackground(Color.green);
				setCursor(cur);
			}
		});

		boton1.addActionListener(this);
		boton2.addActionListener(this);

		add(boton1);
		add(boton2);
		add(boton3);

		try {
			image = ImageIO.read(new File("Imagenes//Mapa.jpg"));
		} catch (IOException e1) {
			System.out.println("Ha ocurrido un error al mostrar el mapa");
		}

		// LAS PUTAS VACUNAS
		
		vacunaVerde = new JButton();
		vacunaAzul = new JButton();
		vacunaAmarilla = new JButton();
		vacunaRoja = new JButton();

		vacunaAzul.setIcon(null);
		vacunaRoja.setIcon(null);
		vacunaAmarilla.setIcon(null);
		vacunaRoja.setIcon(null);

		try {

			if (azulb) {

				vacunaAzul.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaAzul.png"))));
			} else {
				vacunaAzul.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaGris.png"))));
			}
			vacunaAzul.setSize(100, 100);
			vacunaAzul.setLocation(1070, 750);
			vacunaAzul.setContentAreaFilled(false);
			vacunaAzul.setBorder(null);

			if (amarillab) {
				vacunaAmarilla.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaAmarilla.png"))));
			} else {
				vacunaAmarilla.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaGris.png"))));
			}
			vacunaAmarilla.setSize(100, 100);
			vacunaAmarilla.setLocation(1140, 750);
			vacunaAmarilla.setContentAreaFilled(false);
			vacunaAmarilla.setBorder(null);

			if (rojab) {
				vacunaRoja.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaRoja.png"))));
			} else {
				vacunaRoja.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaGris.png"))));
			}
			vacunaRoja.setSize(100, 100);
			vacunaRoja.setLocation(1000, 750);
			vacunaRoja.setContentAreaFilled(false);
			vacunaRoja.setBorder(null);

			if (verdeb) {
				vacunaVerde.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaVerde.png"))));
			} else {
				vacunaVerde.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaGris.png"))));
			}
			vacunaVerde.setSize(100, 100);
			vacunaVerde.setLocation(930, 750);
			vacunaVerde.setContentAreaFilled(false);
			vacunaVerde.setBorder(null);

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error al mostrar las vacunas");
		}

		vacunaAzul.addActionListener(this);
		vacunaAmarilla.addActionListener(this);
		vacunaRoja.addActionListener(this);
		vacunaVerde.addActionListener(this);

		add(vacunaAzul);
		add(vacunaAmarilla);
		add(vacunaRoja);
		add(vacunaVerde);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == boton1) { 
		System.out.println("Toni maricon");
		} else if (e.getSource() == boton2) {
		
			// PRUEBAS CAMBIO DE COLOR | VACUNAS
		
		} else if (e.getSource() == vacunaAzul) {
			azulb = true;
			try {
				if (azulb) {

					vacunaAzul.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaAzul.png"))));
				} else {
					vacunaAzul.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaGris.png"))));
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			add(vacunaAzul);

		} else if (e.getSource() == vacunaAmarilla) {
			amarillab = true;
			try {
				if (amarillab) {
					vacunaAmarilla.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaAmarilla.png"))));
				} else {
					vacunaAmarilla.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaGris.png"))));
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			add(vacunaAmarilla);

		} else if (e.getSource() == vacunaRoja) {
			rojab = true;
			try {
				if (rojab) {
					vacunaRoja.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaRoja.png"))));
				} else {
					vacunaRoja.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaGris.png"))));
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			add(vacunaRoja);

		} else if (e.getSource() == vacunaVerde) {
			verdeb = true;
			try {
				if (verdeb) {

					vacunaVerde.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaVerde.png"))));
				} else {
					vacunaVerde.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaGris.png"))));
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			add(vacunaVerde);

		} else {
			System.exit(0); // Cuando se pulse salir, se cerrará el juego
		}
	}
}
