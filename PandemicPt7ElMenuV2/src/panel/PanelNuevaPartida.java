package panel;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class PanelNuevaPartida extends JPanel implements ActionListener {
	// Botones principales
	JButton boton1;
	JButton boton2;
	JButton boton3;
	JButton boton4;
	// Las vacunas
	JButton vacunaAzul;
	JButton vacunaAmarilla;
	JButton vacunaRoja;
	JButton vacunaVerde;
	JButton vacunaGris;
	// Botones Miscelanea
	JButton guardar;
	// Recuadro
	JTextArea recuadroInfo;
	JTextArea recuadroInfo2;
	// Acciones del usuario
	JButton accion1;
	JButton accion2;
	JButton accion3;
	JButton accion4;
	// Arrays para calculos
	ArrayList<Ciudades> ciudades = new ArrayList<>();
	ArrayList<String> nombres = new ArrayList<>();
	ArrayList<String> bCiudades = new ArrayList<>();
	static ArrayList<String> coordenadas = new ArrayList<>();
	ArrayList<JButtons> colocar = new ArrayList<>();

	static int[] X = new int[48];
	static int[] Y = new int[48];
	static int indice = 0;
	int vacunas = 4;

	String[] conservarRonda = new String[48];

	Image image;

	// BOOLEAN PARA ACTIVAR O DESACTIVAR LAS VACUNAS
	boolean azulb = false;
	boolean amarillab = false;
	boolean rojab = false;
	boolean verdeb = false;
	boolean grisb = false;

	// ACCIONES
	int contadorAccion = 4;

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
		boton4 = new JButton("Pasar turno");
		boton4.setSize(200, 50);
		boton4.setLocation(650, 800);
		boton4.setFont(new Font("Calibri", Font.BOLD, 20));
		boton4.setForeground(Color.WHITE);
		boton4.setBackground(Color.green);
		boton4.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				boton4.setBackground(Color.GRAY);
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				boton4.setBackground(Color.green);
				setCursor(cur);
			}
		});

		boton1.addActionListener(this);
		boton2.addActionListener(this);
		boton3.addActionListener(this);
		boton4.addActionListener(this);

		add(boton1);
		add(boton2);
		add(boton3);
		add(boton4);

		try {
			image = ImageIO.read(new File("Imagenes//Mapa.jpg"));
		} catch (IOException e1) {
			System.out.println("Ha ocurrido un error al mostrar el mapa");
		}

		// ICONO GUARDAR

		guardar = new JButton();
		guardar.setIcon(null);

		guardar.setSize(50, 50);
		guardar.setLocation(1150, 670);
		guardar.setBackground(Color.red);
		guardar.setBorder(null);
		guardar.setBorderPainted(false);
		guardar.setContentAreaFilled(false);
		guardar.setBorderPainted(false);

		try {
			guardar.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//guardar.png"))));
		} catch (IOException e2) {
			System.out.println("Ha ocurrido un error al cargar el botón de guardar el estado de la partida");
		}

		add(guardar);
		guardar(); // FUNCIÓN PARA GUARDAR

		// RECUADROS
		recuadroInfo = new JTextArea();
		recuadroInfo.setFont(new Font("Serif", Font.PLAIN, 16));
		recuadroInfo.setSize(200, 710);
		recuadroInfo.setForeground(Color.WHITE);
		recuadroInfo.setBorder(BorderFactory.createLineBorder(Color.white));
		recuadroInfo.setBackground(Color.BLACK);
		recuadroInfo.setLocation(5, 5);
		recuadroInfo.setOpaque(true);
		recuadroInfo.setVisible(false);
		add(recuadroInfo);

		recuadroInfo2 = new JTextArea();
		recuadroInfo2.setFont(new Font("Serif", Font.PLAIN, 16));
		recuadroInfo2.setSize(200, 140);
		recuadroInfo2.setForeground(Color.WHITE);
		recuadroInfo2.setBorder(BorderFactory.createLineBorder(Color.white));
		recuadroInfo2.setBackground(Color.BLACK);
		recuadroInfo2.setLocation(1050, 5);
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

		Mapeo();

	}

	public void Mapeo() {

		String linea = "";
		int contador = 0;
		try {
			File myObj = new File("Ficheros//ciudades.txt");
			Scanner myReader = new Scanner(myObj);

			while (myReader.hasNext()) {
				linea = myReader.nextLine();
				String[] datos = linea.split(";");
				nombres.add(datos[0]);
				contador = 0;
				while (linea.charAt(contador) != ';') {
					contador++;
				}
				bCiudades.add(linea.substring(0, contador));
			}
			myReader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int contador2 = 0;

		try {
			File myObj = new File("Ficheros//ciudades.txt");
			Scanner myReader = new Scanner(myObj);

			while (myReader.hasNext()) {
				linea = myReader.nextLine();
				contador = 0;
				contador2 = 0;
				while (linea.charAt(contador) != ';') {
					contador++;

				}

				contador2 = contador + 3;

				while (linea.charAt(contador2) != ';') {
					contador2++;
				}

				String coordenada = linea.substring(contador + 3, contador2);

				coordenadas.add(coordenada);

			}
			myReader.close();

			for (int i = 0; i < coordenadas.size(); i++) {
				String[] partes = coordenadas.get(i).split(",");
				X[i] = Integer.parseInt(partes[0]);
				Y[i] = Integer.parseInt(partes[1]);
			}

			for (int i = 0; i < bCiudades.size(); i++) {
				ciudades.add(new Ciudades(nombres.get(i)));
				colocar.add(new JButtons(bCiudades.get(i), X[i], Y[i]));
				colocar.get(i).addActionListener(this);

				try {
					colocar.get(i).setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//OjeteNo.png"))));

				} catch (IOException e) {
					e.printStackTrace();

				}
				add(colocar.get(i));
			}
			try {
				conservarRonda = Enfermedades.ronda(Enfermedades.NomFit1, Enfermedades.NomFit, Enfermedades.n0,
						Enfermedades.ciudades, Enfermedades.NomFit2, Enfermedades.Ciudad_Enfermedad);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			for (int i = 0; i < 48; i++) {
				System.out.println(conservarRonda[i] + " " + i);
				if (conservarRonda[i] != null) {
					if (conservarRonda[i].contains("0")) {
						ciudades.get(i).setAmarilla(ciudades.get(i).getAmarilla() + 1);
					}if (conservarRonda[i].contains("1")) {
						ciudades.get(i).setAzul(ciudades.get(i).getAzul() + 1);
					}if (conservarRonda[i].contains("2")) {
						ciudades.get(i).setRoja(ciudades.get(i).getRoja() + 1);
					}if (conservarRonda[i].contains("3")) {
						ciudades.get(i).setVerde(ciudades.get(i).getVerde() + 1);
					}
				}
			}
			recuadroInfo.setText("Las ciudades infectadas son:\n");

			String guardarRecuadro = recuadroInfo.getText();

			for (int j = 0; j < conservarRonda.length; j++) {
				if (conservarRonda[j] != null) {
					guardarRecuadro = recuadroInfo.getText();
					recuadroInfo.setText(guardarRecuadro + ciudades.get(j).getNombre() + "\n");

				}
			}
			recuadroInfo.setVisible(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	public void vacunas() { // LAS PUTAS VACUNAS
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

		vacunaAzul.addActionListener(this);
		vacunaAmarilla.addActionListener(this);
		vacunaRoja.addActionListener(this);
		vacunaVerde.addActionListener(this);

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

			if (contadorAccion == 4) {
				accion1.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//Accion.png"))));
				accion2.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//Accion.png"))));
				accion3.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//Accion.png"))));
				accion4.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//Accion.png"))));
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

	public void guardar() {
		// FUNCIÓN PARA GUARDAR LA PARTIDA CON FICHEROS
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(image, 0, 0, this);
	}
	
	public void pasarRonda() {

		actualizarCiudad();
		recuadroInfo.setText("Las ciudades infectadas son:\n");

		String guardarRecuadro = recuadroInfo.getText();

		for (int j = 0; j < conservarRonda.length; j++) {
			if (conservarRonda[j] != null) {
				guardarRecuadro = recuadroInfo.getText();
				recuadroInfo.setText(guardarRecuadro + ciudades.get(j).getNombre() + "\n");

			}
		}
		recuadroInfo.setVisible(true);
	}
	
	public void actualizarCiudad() {
		try {
			conservarRonda = Enfermedades.ronda(Enfermedades.NomFit1, Enfermedades.NomFit, Enfermedades.n0,
					Enfermedades.ciudades, Enfermedades.NomFit2, Enfermedades.Ciudad_Enfermedad);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (int i = 0; i < 48; i++) {
			System.out.println(conservarRonda[i] + " " + i);
			if (conservarRonda[i] != null) {
				if (conservarRonda[i].contains("0")) {
					ciudades.get(i).setAmarilla(ciudades.get(i).getAmarilla() + 1);
				}
				if (conservarRonda[i].contains("1")) {
					ciudades.get(i).setAzul(ciudades.get(i).getAzul() + 1);
				}
				if (conservarRonda[i].contains("2")) {
					ciudades.get(i).setRoja(ciudades.get(i).getRoja() + 1);
				}
				if (conservarRonda[i].contains("3")) {
					ciudades.get(i).setVerde(ciudades.get(i).getVerde() + 1);
				}
			}

		}
	}
	
	public void identificarCiudad() {
		recuadroInfo2.setText(ciudades.get(indice).getNombre() + ": [" + X[indice] + " | " + Y[indice] + "]"
				+ " \r\nActualmente esta infectada por la enfermedad" + "\r\nRoja: " + ciudades.get(indice).getRoja()
				+ "\r\nVerde: " + ciudades.get(indice).getVerde() + "\r\nAmarilla: "
				+ ciudades.get(indice).getAmarilla() + "\r\nAzul: " + ciudades.get(indice).getAzul());
		recuadroInfo2.setVisible(true);
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == boton1) {
			contadorAccion -= 4;
			acciones(contadorAccion);
			recuadroInfo.setText("Has pulsado [BUSCAR VACUNA]\nPuede buscar un total de: " + vacunas + " vacunas");
			recuadroInfo.setVisible(true);

		} else if (e.getSource() == boton2) {
			contadorAccion--;
			acciones(contadorAccion);
			recuadroInfo.setText("Has pulsado [CURAR CIUDAD]\nPuede curar las siguentes ciudades: ");
			recuadroInfo.setVisible(true);
		} else if (e.getSource() == boton3) {
			acciones(contadorAccion);
			recuadroInfo.setText("Has pulsado [CURAR CIUDAD]\nPuede curar las siguentes ciudades: ");
			recuadroInfo.setVisible(true);
		} else if (e.getSource() == boton4) {
			contadorAccion += 4;
			acciones(contadorAccion);
			pasarRonda();
			recuadroInfo.setVisible(true);
		} else if (e.getSource() == vacunaAzul) {
			if (azulb == false) {
				azulb = true;
			} else {
				azulb = false;
			}
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
			if (amarillab == false) {
				amarillab = true;
			} else {
				amarillab = false;
			}
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
			if (rojab == false) {
				rojab = true;
			} else {
				rojab = false;
			}
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
			if (verdeb == false) {
				verdeb = true;
			} else {
				verdeb = false;
			}
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

		}
		if (e.getSource() == colocar.get(0)) {
			indice = 0;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(1)) {
			indice = 1;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(2)) {
			indice = 2;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(3)) {
			indice = 3;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(4)) {
			indice = 4;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(5)) {
			indice = 5;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(6)) {
			indice = 6;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(7)) {
			indice = 7;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(7)) {
			indice = 7;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(8)) {
			indice = 8;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(9)) {
			indice = 9;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(10)) {
			indice = 10;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(11)) {
			indice = 11;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(12)) {
			indice = 12;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(13)) {
			indice = 13;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(14)) {
			indice = 14;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(15)) {
			indice = 15;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(16)) {
			indice = 16;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(17)) {
			indice = 17;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(18)) {
			indice = 18;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(19)) {
			indice = 19;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(20)) {
			indice = 20;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(21)) {
			indice = 21;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(22)) {
			indice = 22;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(23)) {
			indice = 23;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(24)) {
			indice = 24;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(25)) {
			indice = 25;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(26)) {
			indice = 26;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(27)) {
			indice = 27;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(28)) {
			indice = 28;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(29)) {
			indice = 29;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(30)) {
			indice = 30;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(31)) {
			indice = 31;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(31)) {
			indice = 31;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(32)) {
			indice = 32;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(33)) {
			indice = 33;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(34)) {
			indice = 34;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(35)) {
			indice = 35;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(36)) {
			indice = 36;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(37)) {
			indice = 37;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(38)) {
			indice = 38;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(39)) {
			indice = 39;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(40)) {
			indice = 40;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(41)) {
			indice = 41;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(42)) {
			indice = 42;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(43)) {
			indice = 43;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(44)) {
			indice = 44;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(45)) {
			indice = 45;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(46)) {
			indice = 46;
			identificarCiudad();
		}
		if (e.getSource() == colocar.get(47)) {
			indice = 47;
			identificarCiudad();
		} 
	}


}
