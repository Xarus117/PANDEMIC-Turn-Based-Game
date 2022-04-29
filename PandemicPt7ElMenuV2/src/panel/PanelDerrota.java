package panel;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PanelDerrota extends JPanel implements ActionListener {
	// Botones principales
	JButton boton1;
	JButton boton2;
	JButton boton4;
	// Las vacunas
	JButton vacunaAzul;
	JButton vacunaAmarilla;
	JButton vacunaRoja;
	JButton vacunaVerde;
	JButton vacunaGris;
	// Botones Miscelanea
	JButton guardar;
	JButton salir;
	JLabel dragonite;
	// Recuadro
	JTextArea recuadroInfo;
	JTextArea recuadroInfo2;
	// Acciones del usuario
	JButton accion1;
	JButton accion2;
	JButton accion3;
	JButton accion4;

	Image image;

	// BOOLEAN PARA ACTIVAR O DESACTIVAR LAS VACUNAS
	boolean azulb = false;
	boolean amarillab = false;
	boolean rojab = false;
	boolean verdeb = false;
	boolean grisb = false;
	int vacunaEncontrada = 0;
	int contadorAccion = 4;

	// Fuentes
	Font fuente1;
	Font fuente2;

	PanelDerrota() throws ParserConfigurationException, SAXException {
		setLayout(null);
		Image im = Toolkit.getDefaultToolkit().createImage("imagenes//cursorDefecto.png");
		Image im2 = Toolkit.getDefaultToolkit().createImage("imagenes//cursorHover.png");
		Cursor cur = Toolkit.getDefaultToolkit().createCustomCursor(im, new Point(10, 10), "WILL");
		Cursor cur2 = Toolkit.getDefaultToolkit().createCustomCursor(im2, new Point(10, 10), "WILL");
		setCursor(cur);
		try {
			fuente1 = Font.createFont(Font.TRUETYPE_FONT, new File("fuentes//Averta.otf")).deriveFont(20f);
			fuente2 = Font.createFont(Font.TRUETYPE_FONT, new File("fuentes//Averta.otf")).deriveFont(13f);

		} catch (FontFormatException | IOException e3) {
			e3.printStackTrace();
		}

		// Botones
		boton1 = new JButton("Buscar vacuna");
		boton1.setSize(200, 50);
		boton1.setLocation(400, 740);
		boton1.setFont(fuente1);
		boton1.setForeground(Color.BLACK);
		boton1.setBackground(Color.GRAY);
		boton2 = new JButton("Curar ciudad");
		boton2.setSize(200, 50);
		boton2.setLocation(650, 740);
		boton2.setFont(fuente1);
		boton2.setForeground(Color.BLACK);
		boton2.setBackground(Color.GRAY);
		boton4 = new JButton("Pasar turno");
		boton4.setSize(200, 50);
		boton4.setLocation(650, 800);
		boton4.setFont(fuente1);
		boton4.setForeground(Color.BLACK);
		boton4.setBackground(Color.GRAY);

		add(boton1);
		add(boton2);
		add(boton4);

		try {
			image = ImageIO.read(new File("Imagenes//Mapa.jpg"));
		} catch (IOException e1) {
			System.out.println("Ha ocurrido un error al mostrar el mapa");
		}
		
		// SALIR
		salir = new JButton();
		salir.setIcon(null);
		salir.setSize(50, 50);
		salir.setLocation(1200, 670);
		salir.setBackground(Color.red);
		salir.setBorder(null);
		salir.setBorderPainted(false);
		salir.setContentAreaFilled(false);
		salir.setBorderPainted(false);
		salir.addActionListener(this);

		try {
			salir.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//salir.png"))));
		} catch (IOException e2) {
			System.out.println("Ha ocurrido un error al cargar el botón de salir de la partida");
		}
		salir.addMouseListener(new MouseAdapter() {
		public void mouseEntered(MouseEvent e) {
			setCursor(cur2);
		}

		public void mouseExited(MouseEvent e) {
			setCursor(cur);
		}
	});
		add(salir);

		// DRAGONITE
		dragonite = new JLabel();
		dragonite.setIcon(new ImageIcon("Imagenes//vivaEspaña.jpg"));
		dragonite.setSize(600, 600);
		dragonite.setLocation(450, 120);
		dragonite.setBackground(Color.red);
		dragonite.setBorder(null);
		add(dragonite);

		// RECUADROS
		recuadroInfo = new JTextArea();
		recuadroInfo.setFont(fuente2);
		recuadroInfo.setSize(210, 120);
		recuadroInfo.setForeground(Color.WHITE);
		recuadroInfo.setBackground(Color.BLACK);
		recuadroInfo.setLocation(180, 740);
		recuadroInfo.setOpaque(true);
		recuadroInfo.setVisible(false);
		add(recuadroInfo);
		recuadroInfo2 = new JTextArea();
		recuadroInfo2.setFont(fuente2);
		recuadroInfo2.setSize(250, 140);
		recuadroInfo2.setForeground(Color.WHITE);
		recuadroInfo2.setBorder(BorderFactory.createLineBorder(Color.white));
		recuadroInfo2.setBackground(Color.BLACK);
		recuadroInfo2.setLocation(1000, 5);
		recuadroInfo2.setOpaque(true);
		recuadroInfo2.setVisible(false);
		add(recuadroInfo2);

		// INICIAR VACUNAS
		vacunaVerde = new JButton();
		vacunaAzul = new JButton();
		vacunaAmarilla = new JButton();
		vacunaRoja = new JButton();

		vacunaAzul.setIcon(null);
		vacunaRoja.setIcon(null);
		vacunaAmarilla.setIcon(null);
		vacunaRoja.setIcon(null);

		vacunaAzul.setSize(90, 100);
		vacunaAzul.setLocation(1070, 750);
		vacunaAzul.setContentAreaFilled(false);
		vacunaAzul.setBorder(null);

		vacunaAmarilla.setSize(90, 100);
		vacunaAmarilla.setLocation(1140, 750);
		vacunaAmarilla.setContentAreaFilled(false);
		vacunaAmarilla.setBorder(null);

		vacunaRoja.setSize(90, 100);
		vacunaRoja.setLocation(1000, 750);
		vacunaRoja.setContentAreaFilled(false);
		vacunaRoja.setBorder(null);

		vacunaVerde.setSize(90, 100);
		vacunaVerde.setLocation(930, 750);
		vacunaVerde.setContentAreaFilled(false);
		vacunaVerde.setBorder(null);

		// INICIAR ACCIONES
		accion1 = new JButton();
		accion2 = new JButton();
		accion3 = new JButton();
		accion4 = new JButton();

		accion1.setIcon(null);
		accion2.setIcon(null);
		accion3.setIcon(null);
		accion4.setIcon(null);
		acciones(contadorAccion);
		vacunas();
	}

	public void vacunas() {

		try {
			if (azulb) {
				vacunaAzul.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaAzul.png"))));
			} else {
				vacunaAzul.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaGris.png"))));
			}

			if (amarillab) {
				vacunaAmarilla.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaAmarilla.png"))));
			} else {
				vacunaAmarilla.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaGris.png"))));
			}

			if (rojab) {
				vacunaRoja.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaRoja.png"))));
			} else {
				vacunaRoja.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaGris.png"))));
			}

			if (verdeb) {
				vacunaVerde.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaVerde.png"))));
			} else {
				vacunaVerde.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//vacunaGris.png"))));
			}

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error al mostrar las vacunas");
		}

		add(vacunaAzul);
		add(vacunaAmarilla);
		add(vacunaRoja);
		add(vacunaVerde);
	}

	public void acciones(int contadorAccion) { // LAS PUTAS ACCIONES
		try {
			accion1.setSize(20, 100);
			accion1.setLocation(40, 750);
			accion1.setContentAreaFilled(false);
			accion1.setBorder(null);

			accion2.setSize(20, 100);
			accion2.setLocation(70, 750);
			accion2.setContentAreaFilled(false);
			accion2.setBorder(null);

			accion3.setSize(20, 100);
			accion3.setLocation(100, 750);
			accion3.setContentAreaFilled(false);
			accion3.setBorder(null);

			accion4.setSize(20, 100);
			accion4.setLocation(130, 750);
			accion4.setContentAreaFilled(false);
			accion4.setBorder(null);

			if (contadorAccion >= 4) {
				accion1.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//AccionNo.png"))));
				accion2.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//AccionNo.png"))));
				accion3.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//AccionNo.png"))));
				accion4.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//AccionNo.png"))));
			}

			if (contadorAccion <= 3) {
				accion4.setIcon(null);
				accion4.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//AccionNo.png"))));
			}
			if (contadorAccion <= 2) {
				accion3.setIcon(null);
				accion3.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//AccionNo.png"))));
			}
			if (contadorAccion <= 1) {
				accion2.setIcon(null);
				accion2.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//AccionNo.png"))));
			}
			if (contadorAccion <= 0) {
				accion1.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//AccionNo.png"))));
			}

			add(accion1);
			add(accion2);
			add(accion3);
			add(accion4);

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error al mostrar las acciones");
		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == salir) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelPrincipal());
			marco.setVisible(true);
		}
	}
}
