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

	JButton boton1;
	JButton boton2;
	JButton boton3;

	JButton vacunaAzul;
	JButton vacunaAmarilla;
	JButton vacunaRoja;
	JButton vacunaVerde;
	JButton vacunaGris;

	JButton mapeo;

	JButton guardar;

	JButton salir;
	JTextArea recuadroInfo;

	JButton accion1;
	JButton accion2;
	JButton accion3;
	JButton accion4;

	ArrayList<Ciudades> ciudades = new ArrayList<>();
	ArrayList<String> nombres = new ArrayList<>();
	ArrayList<String> bCiudades = new ArrayList<>();
	ArrayList<String> coordenadas = new ArrayList<>();
	ArrayList<JButtons> colocar = new ArrayList<>();

	int[] y;
	int[] x;
	int vacunas = 4;
	int cont_azul = 0, cont_rojo = 0, cont_amarillo = 0, cont_verde = 0;

	Image image;

	// BOOLEAN PARA ACTIVAR O DESACTIVAR LAS VACUNAS
	boolean azulb = false;
	boolean amarillab = false;
	boolean rojab = false;
	boolean verdeb = false;
	boolean grisb = false;

	// ACCIONES
	int contadorAccion = 4;

	String guardarCiudad = "";
	String ciudadesEscogida = "";

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

		// ICONO GUARDAR

		guardar = new JButton();
		guardar.setIcon(null);

		guardar.setSize(50, 50);
		guardar.setLocation(30, 670);
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

		// SALIR

		recuadroInfo = new JTextArea();
		recuadroInfo.setText("Me follo abuelas");
		recuadroInfo.setFont(new Font("Serif", Font.PLAIN, 16));
		recuadroInfo.setSize(280, 200);
		recuadroInfo.setForeground(Color.WHITE);
		recuadroInfo.setBorder(getBorder());
		recuadroInfo.setBackground(Color.GRAY);
		recuadroInfo.setLocation(5, 5);
		recuadroInfo.setOpaque(true);
		recuadroInfo.setVisible(false);
		add(recuadroInfo);

		salir = new JButton();

		salir.setIcon(null);
		salir.setSize(150, 50);
		salir.setLocation(1120, 10);
		salir.setBackground(Color.green);
		salir.setBorder(null);
		salir.setBorderPainted(false);
		salir.setContentAreaFilled(false);
		salir.setBorderPainted(false);

		try {
			salir.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//salir.png"))));
		} catch (IOException e2) {
			System.out.println("Ha ocurrido un error al cargar el botón de guardar el estado de la partida");
		}

		salir.addActionListener(this);

		add(salir);

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

			String[] xS = new String[coordenadas.size()];
			String[] yS = new String[coordenadas.size()];
			int[] x = new int[coordenadas.size()];
			int[] y = new int[coordenadas.size()];

			for (int i = 0; i < coordenadas.size(); i++) {
				String[] partes = coordenadas.get(i).split(",");
				xS[i] = partes[0];
				yS[i] = partes[1];
				x[i] = Integer.parseInt(xS[i]);
				y[i] = Integer.parseInt(yS[i]);
			}

			for (int i = 0; i < bCiudades.size(); i++) {

				colocar.add(new JButtons(bCiudades.get(i), x[i], y[i]));
				colocar.get(i).addActionListener(this);

				try {
					colocar.get(i).setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//OjeteNo.png"))));

				} catch (IOException e) {
					e.printStackTrace();
				}
				add(colocar.get(i));
			}

			for (int i = 0; i < 48; i++) {
				String ciudad_enfermedad_necesaria;
				if (Enfermedades.Ciudad_Enfermedad[i] != null) {
					ciudad_enfermedad_necesaria = Enfermedades.Ciudad_Enfermedad[i];
					for (int j = 0; j < ciudad_enfermedad_necesaria.length(); j++) {
						if (ciudad_enfermedad_necesaria.contains("0")) {
							cont_amarillo++;
						} else if (ciudad_enfermedad_necesaria.contains("1")) {
							cont_azul++;
						} else if (ciudad_enfermedad_necesaria.contains("2")) {
							cont_rojo++;
						} else if (ciudad_enfermedad_necesaria.contains("3")) {
							cont_verde++;
						}
					}
				}

				ciudades.add(new Ciudades(nombres.get(i), cont_rojo, cont_verde, cont_amarillo, cont_azul));
				colocar.get(i).addActionListener(this);

				try {
					colocar.get(i).setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//OjeteNo.png"))));

				} catch (IOException e) {
					e.printStackTrace();
				}
				add(colocar.get(i));

			}
			cont_amarillo = 0;
			cont_azul = 0;
			cont_rojo = 0;
			cont_verde = 0;
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

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == boton1) {
			contadorAccion -= 4;
			acciones(contadorAccion);
			recuadroInfo.setText("<html>Has pulsado [BUSCAR VACUNA] <br> Puede buscar un total de </html>" + vacunas
					+ "<html> vacunas</html>");
			recuadroInfo.setVisible(true);

		} else if (e.getSource() == boton2) {
			contadorAccion--;
			acciones(contadorAccion);
			recuadroInfo.setVisible(true);
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

		}

		else {

			String guardarCiudad = ((JComponent) e.getSource()).getName();

			for (int i = 0; i < bCiudades.size(); i++) {
				recuadroInfo.setText(guardarCiudad);
				recuadroInfo.setVisible(true);
			}
		}
	}

	public static void leerFichero() throws ParserConfigurationException, SAXException {
		try {
			File file = new File("Ficheros//NuevaPartida.xml");
			DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder DB = DBF.newDocumentBuilder();
			Document Documento = DB.parse(file);
			Documento.getDocumentElement().normalize();
			System.out.println("Root Element: " + Documento.getDocumentElement().getNodeName());
			NodeList nList = Documento.getElementsByTagName("parametros");
			System.out.println("----------------------------");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				System.out.println("Elemento actual :" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Ciudades infectadas al inicio : "
							+ eElement.getElementsByTagName("numCiudadesInfectadasInicio").item(0).getTextContent());
					System.out.println("Ciudades infectadas en la ronda : "
							+ eElement.getElementsByTagName("numCuidadesInfectadasRonda").item(0).getTextContent());
					System.out.println("Enfermedades activas para derrota : "
							+ eElement.getElementsByTagName("numEnfermedadesActivasDerrota").item(0).getTextContent());
					System.out.println("Numero de brotes para derrota : "
							+ eElement.getElementsByTagName("numBrotesDerrota").item(0).getTextContent());
				}
			}

		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
