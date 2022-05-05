package panel;

import java.sql.*;
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
import java.math.BigDecimal;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class PanelCargarPartida extends JPanel implements ActionListener {
	JButton slot1;
	JButton slot2;
	JButton slot3;
	JButton volver;

	JLabel nombre1, nombre2, nombre3, vacuna1, vacuna2, vacuna3, fecha1, fecha2, fecha3;

	Image image, Logo;

	static String guardarUsuario;
	static String guardarPass;
	String mensajeError;
	int numeroError;

	private static final String USER = "PND_QALQO";
	private static final String PWD = "TYX1234";
	private static final String URL = "jdbc:oracle:thin:@oracle.ilerna.com:1521:xe";

	boolean continuar = false;

	PanelCargarPartida() {
		setLayout(null);
		Image im = Toolkit.getDefaultToolkit().createImage("imagenes//cursorDefecto.png");
		Image im2 = Toolkit.getDefaultToolkit().createImage("imagenes//cursorHover.png");
		Cursor cur = Toolkit.getDefaultToolkit().createCustomCursor(im, new Point(10, 10), "WILL");
		Cursor cur2 = Toolkit.getDefaultToolkit().createCustomCursor(im2, new Point(10, 10), "WILL");
		setCursor(cur);

		slot1 = new JButton();
		slot1.setIcon(null);
		slot1.setSize(525, 150);
		slot1.setLocation(380, 300);
		slot1.setBackground(Color.red);
		slot1.setBorder(null);
		slot1.setBorderPainted(false);
		slot1.setContentAreaFilled(false);
		slot1.setBorderPainted(false);
		slot1.addActionListener(this);
		slot1.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				setCursor(cur);
			}
		});
		try {
			slot1.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//Slots1.png"))));
		} catch (IOException e2) {
		}
		slot2 = new JButton();
		slot2.setIcon(null);
		slot2.setSize(525, 150);
		slot2.setLocation(380, 460);
		slot2.setBackground(Color.red);
		slot2.setBorder(null);
		slot2.setBorderPainted(false);
		slot2.setContentAreaFilled(false);
		slot2.setBorderPainted(false);
		slot2.addActionListener(this);
		slot2.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				setCursor(cur);
			}
		});
		try {
			slot2.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//Slots2.png"))));
		} catch (IOException e2) {
		}
		slot3 = new JButton();
		slot3.setIcon(null);
		slot3.setSize(525, 150);
		slot3.setLocation(380, 620);
		slot3.setBackground(Color.red);
		slot3.setBorder(null);
		slot3.setBorderPainted(false);
		slot3.setContentAreaFilled(false);
		slot3.setBorderPainted(false);
		slot3.addActionListener(this);
		slot3.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				setCursor(cur);
			}
		});
		try {
			slot3.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//Slots3.png"))));
		} catch (IOException e2) {
		}
		volver = new JButton("Volver");
		volver.setSize(200, 50);
		volver.setLocation(550, 820);
		volver.setFont(new Font("Arial", Font.BOLD, 20));
		volver.setBackground(new Color(247, 185, 71));
		volver.setBorder(new LineBorder(Color.BLACK));
		volver.setForeground(Color.black);
		volver.addActionListener(this);
		volver.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				volver.setBackground(Color.GRAY);
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				volver.setBackground(new Color(247, 185, 71));
				setCursor(cur);
			}
		});

		nombre1 = new JLabel(Login.guardarUsuario);
		nombre1.setSize(100, 30);
		nombre1.setLocation(430, 360);
		nombre1.setFont(new Font("Arial", Font.BOLD, 20));
		nombre1.setForeground(Color.black);
		nombre1.setVisible(true);
		nombre1.setOpaque(false);

		nombre2 = new JLabel(Login.guardarUsuario);
		nombre2.setSize(100, 30);
		nombre2.setLocation(430, 520);
		nombre2.setFont(new Font("Arial", Font.BOLD, 20));
		nombre2.setForeground(Color.black);
		nombre2.setVisible(true);
		nombre2.setOpaque(false);

		nombre3 = new JLabel(Login.guardarUsuario);
		nombre3.setSize(100, 30);
		nombre3.setLocation(430, 680);
		nombre3.setFont(new Font("Arial", Font.BOLD, 20));
		nombre3.setForeground(Color.black);
		nombre3.setVisible(true);
		nombre3.setOpaque(false);

		fecha1 = new JLabel(PrimeraFecha(makeConnection()));
		fecha1.setSize(200, 30);
		fecha1.setLocation(560, 330);
		fecha1.setFont(new Font("Arial", Font.BOLD, 20));
		fecha1.setForeground(Color.black);
		fecha1.setVisible(true);
		fecha1.setOpaque(false);

		fecha2 = new JLabel(SegundaFecha(makeConnection()));
		fecha2.setSize(200, 30);
		fecha2.setLocation(560, 490);
		fecha2.setFont(new Font("Arial", Font.BOLD, 20));
		fecha2.setForeground(Color.black);
		fecha2.setVisible(true);
		fecha2.setOpaque(false);

		fecha3 = new JLabel(TerceraFecha(makeConnection()));
		fecha3.setSize(200, 30);
		fecha3.setLocation(560, 650);
		fecha3.setFont(new Font("Arial", Font.BOLD, 20));
		fecha3.setForeground(Color.black);
		fecha3.setVisible(true);
		fecha3.setOpaque(false);

		vacuna1 = new JLabel(PrimerSlot(makeConnection()));
		vacuna1.setSize(200, 30);
		vacuna1.setLocation(560, 650);
		vacuna1.setFont(new Font("Arial", Font.BOLD, 20));
		vacuna1.setForeground(Color.black);
		vacuna1.setVisible(true);
		vacuna1.setOpaque(false);

		vacuna2 = new JLabel(TerceraFecha(makeConnection()));
		vacuna2.setSize(200, 30);
		vacuna2.setLocation(560, 650);
		vacuna2.setFont(new Font("Arial", Font.BOLD, 20));
		vacuna2.setForeground(Color.black);
		vacuna2.setVisible(true);
		vacuna2.setOpaque(false);

		vacuna3 = new JLabel(TerceraFecha(makeConnection()));
		vacuna3.setSize(200, 30);
		vacuna3.setLocation(560, 650);
		vacuna3.setFont(new Font("Arial", Font.BOLD, 20));
		vacuna3.setForeground(Color.black);
		vacuna3.setVisible(true);
		vacuna3.setOpaque(false);

		add(nombre1);
		add(nombre2);
		add(nombre3);

		add(fecha1);
		add(fecha2);
		add(fecha3);

		add(vacuna1);
		add(vacuna2);
		add(vacuna3);

		add(slot1);
		add(slot2);
		add(slot3);

		add(volver);

		try {
			image = ImageIO.read(new File("Imagenes//Fondo.jpg"));
			Logo = ImageIO.read(new File("Imagenes//cargar_partida.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, -30, this);
		g.drawImage(Logo, 280, 100, this);
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

	public static String PrimeraFecha(Connection con) {
		String sql = "SELECT FECHA_PARTIDA FROM PARTIDA WHERE NOMBRE_USUARIO = '" + Login.guardarUsuario + "' AND " + "ID_PARTIDA = 1";
		Statement st = null;
		String fecha = "";

		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				fecha = rs.getString("FECHA_PARTIDA");
			}
			st.close();
		} catch (SQLException e) {
			System.out.println("Ha habído un error con el select: " + e);
		}
		return fecha;
	}

	public static String SegundaFecha(Connection con) {
		String sql = "SELECT FECHA_PARTIDA FROM PARTIDA WHERE NOMBRE_USUARIO = '" + Login.guardarUsuario + "' AND " + "ID_PARTIDA = 2";
		Statement st = null;
		String fecha = "";
		
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				fecha = rs.getString("FECHA_PARTIDA");
			}
			st.close();
		} catch (SQLException e) {
			System.out.println("Ha habído un error con el select: " + e);
		}
		return fecha;
	}

	public static String TerceraFecha(Connection con) {
		String sql = "SELECT FECHA_PARTIDA FROM PARTIDA WHERE NOMBRE_USUARIO = '" + Login.guardarUsuario + "' AND " + "ID_PARTIDA = 3";
		Statement st = null;
		String fecha = "";

		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				fecha = rs.getString("FECHA_PARTIDA");
			}
			st.close();
		} catch (SQLException e) {
			System.out.println("Ha habído un error con el select: " + e);
		}
		return fecha;
	}

	public static String PrimerSlot(Connection con) {

		int valorReturn = 0;
		String sql = "SELECT V_AZUL, V_AMARILLA, V_ROJA, V_VERDE FROM PARTIDA WHERE NOMBRE_USUARIO =" + Login.guardarUsuario + " AND " + "ID_PARTIDA = 1";
		Statement st = null;

		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int azul = rs.getInt("v_azul");
				int amarilla = rs.getInt("v_amarilla");
				int roja = rs.getInt("v_roja");
				int verde = rs.getInt("v_verde");

				valorReturn = azul + amarilla + roja + verde;
			}
			st.close();
		} catch (SQLException e) {
			System.out.println("Ha habído un error con el select: " + e);
		}
		String StringRetornar = String.valueOf(valorReturn);

		return StringRetornar;
	}

	public static int SegundoSlot(Connection con) {

		int valorReturn = 0;
		String sql = "SELECT V_AZUL, V_AMARILLA, V_ROJA, V_VERDE FROM PARTIDA WHERE NOMBRE_USUARIO =" + Login.guardarUsuario + " AND " + "ID_PARTIDA = 1";
		Statement st = null;

		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int azul = rs.getInt("v_azul");
				int amarilla = rs.getInt("v_amarilla");
				int roja = rs.getInt("v_roja");
				int verde = rs.getInt("v_verde");

				valorReturn = azul + amarilla + roja + verde;
			}
			st.close();

		} catch (SQLException e) {
			System.out.println("Ha habído un error con el select: " + e);
		}
		return valorReturn;
	}

	public static int TercerSlot(Connection con) {

		int valorReturn = 0;
		String sql = "SELECT V_AZUL, V_AMARILLA, V_ROJA, V_VERDE FROM PARTIDA WHERE NOMBRE_USUARIO =" + Login.guardarUsuario + " AND " + "ID_PARTIDA = 1";
		Statement st = null;

		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int azul = rs.getInt("v_azul");
				int amarilla = rs.getInt("v_amarilla");
				int roja = rs.getInt("v_roja");
				int verde = rs.getInt("v_verde");

				valorReturn = azul + amarilla + roja + verde;
			}
			st.close();

		} catch (SQLException e) {
			System.out.println("Ha habído un error con el select: " + e);
		}
		return valorReturn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == slot1) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			PanelNuevaPartida.cargado = 1;
			try {
				marco.add(new PanelNuevaPartida());
			} catch (ParserConfigurationException e1) {
				e1.printStackTrace();
			} catch (SAXException e1) {
				e1.printStackTrace();
			}
			marco.setVisible(true);

		} else if (e.getSource() == slot2) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			PanelNuevaPartida.cargado = 2;
			try {
				marco.add(new PanelNuevaPartida());
			} catch (ParserConfigurationException e1) {
				e1.printStackTrace();
			} catch (SAXException e1) {
				e1.printStackTrace();
			}
			marco.setVisible(true);
		} else if (e.getSource() == slot3) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			PanelNuevaPartida.cargado = 3;
			try {
				marco.add(new PanelNuevaPartida());
			} catch (ParserConfigurationException e1) {
				e1.printStackTrace();
			} catch (SAXException e1) {
				e1.printStackTrace();
			}
			marco.setVisible(true);
		}
		if (e.getSource() == volver) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelPrincipal());
			marco.setVisible(true);
		}
	}
}
