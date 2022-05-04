package panel;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

public class PanelNuevaPartida extends JPanel implements ActionListener {
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
	// Recuadros
	JTextArea recuadroInfo;
	JTextArea recuadroInfo2;
	JTextArea mostrarInfeccion;
	JTextArea mostrarBrotes;
	// Acciones del usuario
	JButton accion1;
	JButton accion2;
	JButton accion3;
	JButton accion4;
	static // Arrays para calculos
	ArrayList<Ciudades> ciudades = new ArrayList<>();
	ArrayList<String> nombres = new ArrayList<>();
	ArrayList<JButtons> colocar = new ArrayList<>();
	static ArrayList<String> coordenadas = new ArrayList<>();
	// Random
	static Random rn = new Random();
	int rd = 0;
	int rd2 = 0;
	static int ronda = 0;
	//Slots guardado
	JButton slot1;
	JButton slot2;
	JButton slot3;

	static int indice = 0;
	int vacunas = 4;
	static String guardarCol;
	static String mantener[] = new String[48];
	int brote;
	int infeccionAmarilla = 0;
	int infeccionAzul = 0;
	int infeccionVerde = 0;
	int infeccionRoja = 0;
	int sumaTotal;

	String[] conservarRonda = new String[48];

	static String jugador = Login.guardarUsuario;

	Image image;

	// BOOLEAN PARA ACTIVAR O DESACTIVAR LAS VACUNAS
	static boolean azulb = false;
	static boolean amarillab = false;
	static boolean rojab = false;
	static boolean verdeb = false;
	static boolean grisb = false;
	int vacunaEncontrada = 0;

	// ACCIONES
	int contadorAccion = 4;

	// Guardado Valores XML
	static int infectadasInicio;
	static int infectadasRonda;
	static int infeccionDerrota;
	static int InfeccionPerder;

	private static final String USER = "PND_QALQO";
	private static final String PWD = "TYX1234";
	private static final String URL = "jdbc:oracle:thin:@192.168.3.26:1521:xe";

	// Fuentes
	Font fuente1;
	Font fuente2;

	public void guardar() {
		insertWithStatement(makeConnection());
	}

	PanelNuevaPartida() throws ParserConfigurationException, SAXException {
		setLayout(null);
		Image im = Toolkit.getDefaultToolkit().createImage("imagenes//cursorDefecto.png");
		Image im2 = Toolkit.getDefaultToolkit().createImage("imagenes//cursorHover.png");
		Cursor cur = Toolkit.getDefaultToolkit().createCustomCursor(im, new Point(10, 10), "WILL");
		Cursor cur2 = Toolkit.getDefaultToolkit().createCustomCursor(im2, new Point(10, 10), "WILL");
		setCursor(cur);
		leerFichero();
		try {
			fuente1 = Font.createFont(Font.TRUETYPE_FONT, new File("fuentes//Averta.otf")).deriveFont(20f);
			fuente2 = Font.createFont(Font.TRUETYPE_FONT, new File("fuentes//Averta.otf")).deriveFont(13f);

		} catch (FontFormatException | IOException e3) {
			e3.printStackTrace();
		}

		// Botones
		Color verdeBoton = new Color(247, 185, 71);
		boton1 = new JButton("Buscar vacuna");
		boton1.setSize(200, 50);
		boton1.setLocation(400, 740);
		boton1.setFont(fuente1);
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
		boton2.setFont(fuente1);
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
		boton4 = new JButton("Pasar turno");
		boton4.setSize(200, 50);
		boton4.setLocation(530, 800);
		boton4.setFont(fuente1);
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
		slot1 = new JButton("Slot 1");
		slot1.setSize(100, 20);
		slot1.setLocation(1130, 550);
		slot1.setFont(fuente1);
		slot1.setForeground(Color.BLACK);
		slot1.setBackground(verdeBoton);
		slot1.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				slot1.setBackground(Color.GRAY);
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				slot1.setBackground(verdeBoton);
				setCursor(cur);
			}
		});
		slot2 = new JButton("Slot 2");
		slot2.setSize(100, 20);
		slot2.setLocation(1130, 590);
		slot2.setFont(fuente1);
		slot2.setForeground(Color.BLACK);
		slot2.setBackground(verdeBoton);
		slot2.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				slot2.setBackground(Color.GRAY);
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				slot2.setBackground(verdeBoton);
				setCursor(cur);
			}
		});
		slot3 = new JButton("Slot 3");
		slot3.setSize(100, 20);
		slot3.setLocation(1130, 630);
		slot3.setFont(fuente1);
		slot3.setForeground(Color.BLACK);
		slot3.setBackground(verdeBoton);
		slot3.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				slot3.setBackground(Color.GRAY);
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				slot3.setBackground(verdeBoton);
				setCursor(cur);
			}
		});

		boton1.addActionListener(this);
		boton2.addActionListener(this);
		boton4.addActionListener(this);
		slot1.addActionListener(this);
		slot2.addActionListener(this);
		slot3.addActionListener(this);

		add(boton1);
		add(boton2);
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
		guardar.setLocation(1130, 670);
		guardar.setBackground(Color.red);
		guardar.setBorder(null);
		guardar.setBorderPainted(false);
		guardar.setContentAreaFilled(false);
		guardar.setBorderPainted(false);
		guardar.addActionListener(this);
		try {
			guardar.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//guardar.png"))));
		} catch (IOException e2) {
			System.out.println("Ha ocurrido un error al cargar el botón de guardar el estado de la partida");
		}
		guardar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				setCursor(cur);
			}
		});
		add(guardar);

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

		// Crear JLabels
		mostrarInfeccion = new JTextArea();
		mostrarInfeccion.setSize(130, 15);
		mostrarInfeccion.setLocation(860, 740);
		mostrarInfeccion.setFont(fuente2);
		mostrarInfeccion.setForeground(Color.WHITE);
		mostrarInfeccion.setBackground(Color.BLACK);
		mostrarInfeccion.setOpaque(true);
		mostrarInfeccion.setVisible(true);
		add(mostrarInfeccion);

		mostrarBrotes = new JTextArea();
		mostrarBrotes.setSize(115, 15);
		mostrarBrotes.setLocation(860, 770);
		mostrarBrotes.setFont(fuente2);
		mostrarBrotes.setForeground(Color.WHITE);
		mostrarBrotes.setBackground(Color.BLACK);
		mostrarBrotes.setOpaque(true);
		mostrarBrotes.setVisible(true);
		add(mostrarBrotes);

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

		jugador = Login.guardarUsuario;

		// Mapeo del juego
		Mapeo();

	}

	public void Mapeo() {

		String linea = "";
		try {
			File myObj = new File("Ficheros//ciudades.txt");
			Scanner myReader = new Scanner(myObj);
			for (int i = 0; i < 48; i++) {
				linea = myReader.nextLine();
				String[] datos = linea.split(";");
				String[] c = datos[2].split(",");
				nombres.add(datos[0]);
				int x = Integer.parseInt(c[0]);
				int y = Integer.parseInt(c[1]);
				String[] colindantes = datos[3].split(",");
				guardarCol = datos[3];
				mantener[i] = guardarCol;

				colocar.add(new JButtons(nombres.get(i), x, y));
				colocar.get(i).addActionListener(this);

				try {
					colocar.get(i).setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//OjeteNo.png"))));

				} catch (IOException e) {
					e.printStackTrace();

				}
				add(colocar.get(i));

				ciudades.add(new Ciudades(datos[0], colindantes, guardarCol));
			}
			myReader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < colocar.size(); i++) { // PARA QUE SE ILUMINEN LOS ICONOS
			int nombre = i;

			colocar.get(i).addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					try {
						colocar.get(nombre).setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//Ojete.png"))));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

				public void mouseExited(MouseEvent e) {
					try {
						colocar.get(nombre).setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//OjeteNo.png"))));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});

		}
		contagio();
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

		if (azulb && amarillab && verdeb && rojab) {
			recuadroInfo.setText("No es posible buscar más vacunas");
		}
		if (PanelEscogerDificultad.dificultad == 1) {
			if (rda <= 30 && !azulb) {
				recuadroInfo.setText("Ha encontrado una o más vacunas!");
				azulb = true;
				vacunaEncontrada++;
				for (int i = 0; i < 48; i++) {
					ciudades.get(i).setAzul(0);
					infeccionAzul = 0;
				}
			}
			if (rdam <= 30 && !amarillab) {
				recuadroInfo.setText("Ha encontrado una o más vacunas!");
				amarillab = true;
				vacunaEncontrada++;
				for (int i = 0; i < 48; i++) {
					ciudades.get(i).setAmarilla(0);
					infeccionAmarilla = 0;
				}
			}
			if (rdv <= 30 && !verdeb) {
				recuadroInfo.setText("Ha encontrado una o más vacunas!");
				verdeb = true;
				vacunaEncontrada++;
				for (int i = 0; i < 48; i++) {
					ciudades.get(i).setRoja(0);
					infeccionVerde = 0;
				}
			}
			if (rdr <= 30 && !rojab) {
				recuadroInfo.setText("Ha encontrado una o más vacunas!");
				rojab = true;
				vacunaEncontrada++;
				for (int i = 0; i < 48; i++) {
					ciudades.get(i).setVerde(0);
					infeccionRoja = 0;
				}
			}
		} else if (PanelEscogerDificultad.dificultad == 2) {
			if (rda <= 15 && !azulb) {
				recuadroInfo.setText("Ha encontrado una o más vacunas!");
				azulb = true;
				vacunaEncontrada++;
				for (int i = 0; i < 48; i++) {
					ciudades.get(i).setAzul(0);
					infeccionAzul = 0;
				}
			}
			if (rdam <= 15 && !amarillab) {
				recuadroInfo.setText("Ha encontrado una o más vacunas!");
				amarillab = true;
				vacunaEncontrada++;
				for (int i = 0; i < 48; i++) {
					ciudades.get(i).setAmarilla(0);
					infeccionAmarilla = 0;
				}
			}
			if (rdv <= 15 && !verdeb) {
				recuadroInfo.setText("Ha encontrado una o más vacunas!");
				verdeb = true;
				vacunaEncontrada++;
				for (int i = 0; i < 48; i++) {
					ciudades.get(i).setRoja(0);
					infeccionVerde = 0;
				}
			}
			if (rdr <= 15 && !rojab) {
				recuadroInfo.setText("Ha encontrado una o más vacunas!");
				rojab = true;
				vacunaEncontrada++;
				for (int i = 0; i < 48; i++) {
					ciudades.get(i).setVerde(0);
					infeccionRoja = 0;
				}
			}
		}
		vacunas();
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

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(image, 0, 0, this);
	}

	public void contagio() {
		ArrayList<String> mantener = new ArrayList<>();

		for (int i = 0; i < infectadasInicio; i++) {
			rd = rn.nextInt(4);
			rd2 = rn.nextInt(48);
			if (rd == 0 && !amarillab) {
				ciudades.get(rd2).setAmarilla(ciudades.get(rd2).getAmarilla() + 1);
				infeccionAmarilla++;
			}
			if (rd == 1 && !azulb) {
				ciudades.get(rd2).setAzul(ciudades.get(rd2).getAzul() + 1);
				infeccionAzul++;
			}
			if (rd == 2 && !rojab) {
				ciudades.get(rd2).setRoja(ciudades.get(rd2).getRoja() + 1);
				infeccionRoja++;
			}
			if (rd == 3 && !verdeb) {
				ciudades.get(rd2).setVerde(ciudades.get(rd2).getVerde() + 1);
				infeccionVerde++;
			}
			mantener.add(ciudades.get(rd2).getNombre());
		}
		sumaTotal = infeccionAmarilla + infeccionAzul + infeccionRoja + infeccionVerde;
		recuadroInfo.setText("Las ciudades infectadas son:\n");

		String guardarRecuadro = recuadroInfo.getText();

		for (int j = 0; j < mantener.size(); j++) {
			guardarRecuadro = recuadroInfo.getText();
			recuadroInfo.setText(guardarRecuadro + mantener.get(j) + "\n");
		}
		recuadroInfo.setVisible(true);
	}

	public void brote() {

		for (int i = 0; i < 48; i++) {
			if (ciudades.get(i).getAmarilla() >= 3) {
				buscarColindante(0, i);
				recuadroInfo2.setText("Ha habido un brote amarillo en: " + ciudades.get(i).getNombre()
						+ "\nSe han infectado las siguientes ciudades:\n");
				String guardarRecuadro = recuadroInfo2.getText();
				guardarRecuadro = recuadroInfo2.getText();
				recuadroInfo2.setText(guardarRecuadro + mantener[i] + "\n");
				brote++;
			} else if (ciudades.get(i).getRoja() >= 3) {
				buscarColindante(1, i);
				recuadroInfo2.setText("Ha habido un brote azul en: " + ciudades.get(i).getNombre()
						+ "\nSe han infectado las siguientes ciudades:\n");
				String guardarRecuadro = recuadroInfo2.getText();
				guardarRecuadro = recuadroInfo2.getText();
				recuadroInfo2.setText(guardarRecuadro + mantener[i] + "\n");
				brote++;
			} else if (ciudades.get(i).getAzul() >= 3) {
				buscarColindante(2, i);
				recuadroInfo2.setText("Ha habido un brote rojo en: " + ciudades.get(i).getNombre()
						+ "\nSe han infectado las siguientes ciudades:\n");
				String guardarRecuadro = recuadroInfo2.getText();
				guardarRecuadro = recuadroInfo2.getText();
				recuadroInfo2.setText(guardarRecuadro + mantener[i] + "\n");
				brote++;
			} else if (ciudades.get(i).getVerde() >= 3) {
				buscarColindante(3, i);
				recuadroInfo2.setText("Ha habido un brote verde en: " + ciudades.get(i).getNombre()
						+ "\nSe han infectado las siguientes ciudades:\n");
				String guardarRecuadro = recuadroInfo2.getText();
				guardarRecuadro = recuadroInfo2.getText();
				recuadroInfo2.setText(guardarRecuadro + mantener[i] + "\n");
				brote++;
			}
		}
	}

	public void buscarColindante(int id, int i) {

		String a[] = new String[0];

		a = Arrays.copyOf(a, ciudades.get(i).getColindantes().length);
		a = ciudades.get(i).getColindantes();

		for (int j = 0; j < a.length; j++) {
			for (int k = 0; k < ciudades.size(); k++) {
				if (a[j].equals(ciudades.get(k).getNombre())) {
					if (id == 0 && ciudades.get(k).getAmarilla() < 3) {
						ciudades.get(k).setAmarilla(ciudades.get(k).getAmarilla() + 1);
					}
					if (id == 1 && ciudades.get(k).getRoja() < 3) {
						ciudades.get(k).setRoja(ciudades.get(k).getRoja() + 1);
					}
					if (id == 2 && ciudades.get(k).getAzul() < 3) {
						ciudades.get(k).setAzul(ciudades.get(k).getAzul() + 1);
					}
					if (id == 3 && ciudades.get(k).getVerde() < 3) {
						ciudades.get(k).setVerde(ciudades.get(k).getVerde() + 1);
					}
				}
			}
		}
	}

	public void victoria() {

		if (azulb && amarillab && verdeb && rojab) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			try {
				marco.add(new PanelVictoria());
			} catch (ParserConfigurationException e1) {
				e1.printStackTrace();
			} catch (SAXException e1) {
				e1.printStackTrace();
			}
			marco.setVisible(true);
		}
	}

	public void curar() {
		if (ciudades.get(indice).getAmarilla() >= 1) {
			ciudades.get(indice).setAmarilla(ciudades.get(indice).getAmarilla() - 1);
			infeccionAmarilla--;
		}
		if (ciudades.get(indice).getAzul() >= 1) {
			ciudades.get(indice).setAzul(ciudades.get(indice).getAzul() - 1);
			infeccionAzul--;
		}
		if (ciudades.get(indice).getRoja() >= 1) {
			ciudades.get(indice).setRoja(ciudades.get(indice).getRoja() - 1);
			infeccionRoja--;
		}
		if (ciudades.get(indice).getVerde() >= 1) {
			ciudades.get(indice).setVerde(ciudades.get(indice).getVerde() - 1);
			infeccionVerde--;
		}
		contadorAccion--;
		acciones(contadorAccion);
	}

	public void identificarCiudad() {
		recuadroInfo2.setText(ciudades.get(indice).getNombre() + " \r\nActualmente esta infectada por:" + "\r\nRoja: "
				+ ciudades.get(indice).getRoja() + "\r\nVerde: " + ciudades.get(indice).getVerde() + "\r\nAmarilla: "
				+ ciudades.get(indice).getAmarilla() + "\r\nAzul: " + ciudades.get(indice).getAzul());
		recuadroInfo2.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == boton1) {
			if (contadorAccion < 4) {
				recuadroInfo.setText("No es posible [BUSCAR VACUNA] \n" + "Ya que no tiene suficientes acciones");
			} else {
				contadorAccion -= 4;
				acciones(contadorAccion);
				recuadroInfo.setText("No se encontraron vacunas");
				buscarVacuna();
			}
			recuadroInfo.setVisible(true);
		} else if (e.getSource() == boton2) {
			if (contadorAccion < 1) {
				recuadroInfo.setText("No es posible [BUSCAR CURA] \n" + "Ya que no tiene suficientes acciones");
			} else {
				recuadroInfo2.setText(
						"Has curado la infección en 1 todas\nlas enfermedades de: " + ciudades.get(indice).getNombre());
				curar();
			}
		} else if (e.getSource() == boton4) {
			ronda++;
			victoria();
			contadorAccion = 4;
			acciones(contadorAccion);
			contagio();
			brote();
			mostrarInfeccion.setText("Cantidad infección: " + sumaTotal);
			mostrarBrotes.setText("Cantidad brotes: " + brote);
			if (brote >= InfeccionPerder) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
				marco.remove(this);
				try {
					marco.add(new PanelDerrota());
				} catch (ParserConfigurationException e1) {
					e1.printStackTrace();
				} catch (SAXException e1) {
					e1.printStackTrace();
				}
				marco.setVisible(true);
			} else if (sumaTotal >= infeccionDerrota) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
				marco.remove(this);
				try {
					marco.add(new PanelDerrota());
				} catch (ParserConfigurationException e1) {
					e1.printStackTrace();
				} catch (SAXException e1) {
					e1.printStackTrace();
				}
				marco.setVisible(true);
			}
		} else if (e.getSource() == salir) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelPrincipal());
			marco.setVisible(true);
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

		} else if (e.getSource() == guardar) {
			add(slot1);
			add(slot2);
			add(slot3);
			if (e.getSource() == slot1) {
				guardar();
				remove(slot1);
			}else if (e.getSource() == slot2) {
				guardar();
				remove(slot2);
			}else if (e.getSource() == slot3) {
				guardar();
				remove(slot3);
			}
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

	public static void leerFichero() throws ParserConfigurationException, SAXException {
		try {
			File file = new File("Ficheros//NuevaPartida.xml");
			DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder DB = DBF.newDocumentBuilder();
			Document Documento = DB.parse(file);
			Documento.getDocumentElement().normalize();
			NodeList nList = Documento.getElementsByTagName("parametros");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					if (PanelEscogerDificultad.dificultad == 1) {
						infectadasInicio = Integer.parseInt(eElement
								.getElementsByTagName("numCiudadesInfectadasInicioFacil").item(0).getTextContent());
						infectadasRonda = Integer.parseInt(eElement
								.getElementsByTagName("numCuidadesInfectadasRondaFacil").item(0).getTextContent());
						infeccionDerrota = Integer.parseInt(eElement
								.getElementsByTagName("numEnfermedadesActivasDerrotaFacil").item(0).getTextContent());
						InfeccionPerder = Integer.parseInt(
								eElement.getElementsByTagName("numBrotesDerrotaFacil").item(0).getTextContent());
					}
					if (PanelEscogerDificultad.dificultad == 2) {
						infectadasInicio = Integer.parseInt(eElement
								.getElementsByTagName("numCiudadesInfectadasInicioNormal").item(0).getTextContent());
						infectadasRonda = Integer.parseInt(eElement
								.getElementsByTagName("numCuidadesInfectadasRondaNormal").item(0).getTextContent());
						infeccionDerrota = Integer.parseInt(eElement
								.getElementsByTagName("numEnfermedadesActivasDerrotaNormal").item(0).getTextContent());
						InfeccionPerder = Integer.parseInt(
								eElement.getElementsByTagName("numBrotesDerrotaNormal").item(0).getTextContent());
					}
				}
			}
		} catch (IOException a) {
		}
	}

	public static Connection makeConnection() {
		System.out.println("Conectando a la base de datos...");

		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PWD);

			System.out.println("Conexión establecida con la base de datos");

		} catch (SQLException e) {
			throw new IllegalStateException("No se ha podido conectar a la base de datos ", e);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void closeConnection(Connection con) {
		try {
			con.close();
			System.out.println("Se ha cerrado la conexión");
		} catch (SQLException e) {
			System.out.println("Ha ocurrido un error cerrando la conexión: " + e);

		}
	}

	public static void insertWithStatement(Connection con) {

		int azul = 0;
		int roja = 0;
		int amarilla = 0;
		int verde = 0;

		if (azulb == true) {
			azul = 1;
		} else if (rojab == true) {
			roja = 1;
		} else if (amarillab == true) {
			amarilla = 1;
		} else if (verdeb == true) {
			verde = 1;
		}

		System.out.println(jugador);
		System.out.println(ronda);
		System.out.println(azul);
		System.out.println(amarilla);
		System.out.println(roja);
		System.out.println(verde);

		int slot = 1; // A FUTURO MODIFICABLE CON UN MENÚ

		for (int i = 0; i < 48; i++) {
			String sql = "UPDATE INFO_CIUDADES SET CIUDAD" + i + " = CIUDAD('" + ciudades.get(i).getNombre() + "','"
					+ ciudades.get(i).getRoja() + "','" + ciudades.get(i).getVerde() + "','"
					+ ciudades.get(i).getAmarilla() + "','" + ciudades.get(i).getAzul() + "')" + "where id_partida = '"
					+ slot + "'" + "and USUARIO = '" + jugador + "'";

			try {
				Statement statement = (Statement) con.createStatement();
				statement.execute(sql);
				statement.close();

			} catch (SQLException e) {
				System.out.println("Ha habído un error:" + e);

			}
		}

		String sql = "UPDATE PARTIDA SET NUM_RONDAS = " + ronda + ", FECHA_PARTIDA = SYSDATE, V_AZUL = " + azul + ", V_AMARILLA =  " + amarilla
				+ ", V_ROJA = " + roja + ", V_VERDE = " + verde
				+  "WHERE NOMBRE_USUARIO = '" + jugador +"' AND ID_PARTIDA = " + slot + ""
				+ "";

		try {
			Statement statement = (Statement) con.createStatement();
			statement.execute(sql);
			statement.close();

		} catch (SQLException e) {
			System.out.println("Ha habído un error:" + e);

		}

	}

}
