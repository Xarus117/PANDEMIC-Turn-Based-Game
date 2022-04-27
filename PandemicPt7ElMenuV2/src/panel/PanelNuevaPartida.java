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
	// Random
	static Random rn = new Random();
	int rd = 0;
	int rd2 = 0;

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
	int vacunaEncontrada = 0;

	// ACCIONES
	int contadorAccion = 4;

	PanelNuevaPartida() {
		setLayout(null);
		Image im = Toolkit.getDefaultToolkit().createImage("imagenes//cursorDefecto.png");
		Image im2 = Toolkit.getDefaultToolkit().createImage("imagenes//cursorHover.png");
		Cursor cur = Toolkit.getDefaultToolkit().createCustomCursor(im, new Point(10, 10), "WILL");
		Cursor cur2 = Toolkit.getDefaultToolkit().createCustomCursor(im2, new Point(10, 10), "WILL");
		setCursor(cur);

		//Botones
		Color verdeBoton = new Color (144, 238, 144);
		boton1 = new JButton("Buscar vacuna");
		boton1.setSize(200, 50);
		boton1.setLocation(400, 740);
		boton1.setFont(new Font("Calibri", Font.BOLD, 20));
		boton1.setForeground(Color.BLACK);
		boton1.setBackground(verdeBoton);
		boton1.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				boton1.setBackground(Color.GRAY);
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				boton1.setBackground(verdeBoton);
				setCursor(cur);
			}
		});
		boton2 = new JButton("Curar ciudad");
		boton2.setSize(200, 50);
		boton2.setLocation(650, 740);
		boton2.setFont(new Font("Calibri", Font.BOLD, 20));
		boton2.setForeground(Color.BLACK);
		boton2.setBackground(verdeBoton);
		boton2.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				boton2.setBackground(Color.GRAY);
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				boton2.setBackground(verdeBoton);
				setCursor(cur);
			}
		});
		boton3 = new JButton("Aplicar Vacuna");
		boton3.setSize(200, 50);
		boton3.setLocation(400, 800);
		boton3.setFont(new Font("Calibri", Font.BOLD, 20));
		boton3.setForeground(Color.BLACK);
		boton3.setBackground(verdeBoton);
		boton3.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				boton3.setBackground(Color.GRAY);
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				boton3.setBackground(verdeBoton);
				setCursor(cur);
			}
		});
		boton4 = new JButton("Pasar turno");
		boton4.setSize(200, 50);
		boton4.setLocation(650, 800);
		boton4.setFont(new Font("Calibri", Font.BOLD, 20));
		boton4.setForeground(Color.BLACK);
		boton4.setBackground(verdeBoton);
		boton4.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				boton4.setBackground(Color.GRAY);
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				boton4.setBackground(verdeBoton);
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
		recuadroInfo.setFont(new Font("Serif", Font.PLAIN, 14));
		recuadroInfo.setSize(210, 120);
		recuadroInfo.setForeground(Color.WHITE);
		recuadroInfo.setBackground(Color.BLACK);
		recuadroInfo.setLocation(180, 740);
		recuadroInfo.setOpaque(true);
		recuadroInfo.setVisible(false);
		add(recuadroInfo);
		recuadroInfo2 = new JTextArea();
		recuadroInfo2.setFont(new Font("Serif", Font.PLAIN, 16));
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
		
		//Mapeo del juego
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
			contagio();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

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
	
	public void buscarVacuna() {
		int rda = 0;
		int rdam = 0;
		int rdv = 0;
		int rdr = 0;
		rda = rn.nextInt(100) + 1;
		rdam = rn.nextInt(100) + 1;
		rdv = rn.nextInt(100) + 1;
		rdr = rn.nextInt(100) + 1;
		
		
		if(azulb && amarillab && verdeb && rojab) {
			recuadroInfo.setText("No es posible buscar más vacunas");
		}if(rda <= 40 && !azulb) {
			recuadroInfo.setText("Ha encontrado una o más vacunas!");
			azulb = true;
			vacunaEncontrada++;
		}if(rdam <= 40 && !amarillab) {
			recuadroInfo.setText("Ha encontrado una o más vacunas!");
			amarillab = true;
			vacunaEncontrada++;
		}if(rdv <= 40 && !verdeb) {
			recuadroInfo.setText("Ha encontrado una o más vacunas!");
			verdeb = true;
			vacunaEncontrada++;
		}if(rdr <= 40 && ! rojab) {
			recuadroInfo.setText("Ha encontrado una o más vacunas!");
			rojab = true;
			vacunaEncontrada++;
		}
		vacunas();
	}
	public void aplicarVacuna() {
		if(azulb) {
			recuadroInfo.setText("Se curó la enfermedad azul");
			ciudades.get(rd2).setAzul(0);
		}if(amarillab) {
			recuadroInfo.setText("Se curó la enfermedad amarilla");
			ciudades.get(rd2).setAmarilla(0);
		}if(rojab) {
			recuadroInfo.setText("Se curó la enfermedad roja");
			ciudades.get(rd2).setRoja(0);
		}if(verdeb) {
			recuadroInfo.setText("Se curó la enfermedad verde");
			ciudades.get(rd2).setVerde(0);
		}if(vacunaEncontrada == 2) {
			recuadroInfo.setText("Se curaron 2 enfermedades");
		}if(vacunaEncontrada == 3) {
			recuadroInfo.setText("Se curaron 3 enfermedades");
		}if(vacunaEncontrada == 4) {
			recuadroInfo.setText("Se curaron las 4 enfermedades!!");
		}if(vacunaEncontrada == 0) {
			recuadroInfo.setText("No tienes vacunas para aplicar :(");
		}
		recuadroInfo.setVisible(true);
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

	public void contagio() {
		int ronda = 5;
		String[] mantener = new String[5];

		for (int i = 0; i < ronda; i++) {
			rd = rn.nextInt(4);
			rd2 = rn.nextInt(48);
			if (rd == 0 && !amarillab) {
				ciudades.get(rd2).setAmarilla(ciudades.get(rd2).getAmarilla() + 1);
			}
			if (rd == 1 && !azulb) {
				ciudades.get(rd2).setAzul(ciudades.get(rd2).getAzul() + 1);
			}
			if (rd == 2 && !rojab) {
				ciudades.get(rd2).setRoja(ciudades.get(rd2).getRoja() + 1);
			}
			if (rd == 3 && !verdeb) {
				ciudades.get(rd2).setVerde(ciudades.get(rd2).getVerde() + 1);
			}
			mantener[i] = ciudades.get(rd2).getNombre();
		}
		ronda = 3;
		recuadroInfo.setText("Las ciudades infectadas son:\n");

		String guardarRecuadro = recuadroInfo.getText();

		for (int j = 0; j < mantener.length; j++) {
			guardarRecuadro = recuadroInfo.getText();
			recuadroInfo.setText(guardarRecuadro + mantener[j] + "\n");
		}
		recuadroInfo.setVisible(true);
	}

	public void curar() {
		if (ciudades.get(indice).getAmarilla() >= 1) {
			ciudades.get(indice).setAmarilla(ciudades.get(indice).getAmarilla() - 1);
		}
		if (ciudades.get(indice).getAzul() >= 1) {
			ciudades.get(indice).setAzul(ciudades.get(indice).getAzul() - 1);
		}
		if (ciudades.get(indice).getRoja() >= 1) {
			ciudades.get(indice).setRoja(ciudades.get(indice).getRoja() - 1);
		}
		if (ciudades.get(indice).getVerde() >= 1) {
			ciudades.get(indice).setVerde(ciudades.get(indice).getVerde() - 1);
		}
		contadorAccion--;
		acciones(contadorAccion);
	}

	public void identificarCiudad() {
		recuadroInfo2.setText(ciudades.get(indice).getNombre() + ": [" + X[indice] + " | " + Y[indice] + "]"
				+ " \r\nActualmente esta infectada por:" + "\r\nRoja: " + ciudades.get(indice).getRoja() + "\r\nVerde: "
				+ ciudades.get(indice).getVerde() + "\r\nAmarilla: " + ciudades.get(indice).getAmarilla() + "\r\nAzul: "
				+ ciudades.get(indice).getAzul());
		recuadroInfo2.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == boton1) {
			if (contadorAccion < 4) {
				recuadroInfo.setText("No es posible [BUSCAR VACUNA] \n"+"Ya que no tiene suficientes acciones");
			}else {
			contadorAccion -= 4;
			acciones(contadorAccion);
			recuadroInfo.setText("No se encontraron vacunas");
			buscarVacuna();
			}
			recuadroInfo.setVisible(true);

		} else if (e.getSource() == boton2) {
			if (contadorAccion < 1) {
				recuadroInfo.setText("No es posible [BUSCAR CURA] \n"+"Ya que no tiene suficientes acciones");
			}else {
			recuadroInfo2.setText("Has curado la infección en 1 todas\nlas enfermedades de: " + ciudades.get(indice).getNombre());
			curar();}
		} else if (e.getSource() == boton3) {
			acciones(contadorAccion);
			aplicarVacuna();
		} else if (e.getSource() == boton4) {
			contadorAccion = 4;
			acciones(contadorAccion);
			contagio();
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
