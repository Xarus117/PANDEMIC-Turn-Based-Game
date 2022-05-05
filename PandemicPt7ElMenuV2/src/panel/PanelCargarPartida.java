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
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class PanelCargarPartida extends JPanel implements ActionListener {
	JButton slot1;
	JButton slot2;
	JButton slot3;
	JButton volver;

	Image image;
	Image Logo;

	static String guardarUsuario;
	static String guardarPass;
	String mensajeError;
	int numeroError;

	boolean continuar = false;

	private static final String USER = "PND_QALQO";
	private static final String PWD = "TYX1234";
	private static final String URL = "jdbc:oracle:thin:@192.168.3.26:1521:xe";

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

		try {
			slot1.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//Slots1.png"))));
		} catch (IOException e2) {
		}
		slot1.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				setCursor(cur);
			}
		});

		slot2 = new JButton();
		slot2.setIcon(null);
		slot2.setSize(525, 150);
		slot2.setLocation(380, 460);
		slot2.setBackground(Color.red);
		slot2.setBorder(null);
		slot2.setBorderPainted(false);
		slot2.setContentAreaFilled(false);
		slot2.setBorderPainted(false);

		try {
			slot2.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//Slots2.png"))));
		} catch (IOException e2) {
		}
		slot2.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				setCursor(cur);
			}
		});

		slot3 = new JButton();
		slot3.setIcon(null);
		slot3.setSize(525, 150);
		slot3.setLocation(380, 620);
		slot3.setBackground(Color.red);
		slot3.setBorder(null);
		slot3.setBorderPainted(false);
		slot3.setContentAreaFilled(false);
		slot3.setBorderPainted(false);

		try {
			slot3.setIcon(new ImageIcon(ImageIO.read(new File("Imagenes//Slots3.png"))));
		} catch (IOException e2) {
		}
		slot3.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				setCursor(cur);
			}
		});

		volver = new JButton("Volver");
		volver.setSize(200, 50);
		volver.setLocation(550, 820);
		volver.setFont(new Font("Arial", Font.BOLD, 20));
		volver.setBackground(new Color(247, 185, 71));
		volver.setBorder(new LineBorder(Color.BLACK));
		volver.setForeground(Color.black);
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

		volver.addActionListener(this);
		slot1.addActionListener(this);
		slot2.addActionListener(this);
		slot3.addActionListener(this);
		add(slot3);
		add(volver);
		add(slot1);
		add(slot2);

		try {
			image = ImageIO.read(new File("Imagenes//Fondo.jpg"));
			Logo = ImageIO.read(new File("Imagenes//cargar_partida.png"));

		} catch (IOException e) {
			e.printStackTrace();
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

	public static int selectWithStatement(Connection con, String guardarUsuario, String guardarPass) {

		int valorReturn = 0;
		String sql = "SELECT * FROM USUARIO";

		Statement st = null;

		try {
			st = con.createStatement();

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				String usuario = rs.getString("usuario");
				String pass = rs.getString("contraseña");

				System.out.println("\nLISTA USUARIOS:");
				System.out.println(rs.getString("usuario"));

				if (usuario.equals(guardarUsuario) && pass.equals(guardarPass)) {
					valorReturn = 1;
				}
			}

			st.close();

		} catch (SQLException e) {
			System.out.println("Ha habído un error con el select: " + e);

		}
		return valorReturn;
	}

	public static String insertWithStatement(Connection con, String guardarUsuario, String guardarPass) {

		String sql = "INSERT INTO USUARIO (usuario, contraseña) VALUES ('" + guardarUsuario + "','" + guardarPass
				+ "')";

		String mensajeError = "Correcto";

		try {
			Statement statement = (Statement) con.createStatement();
			statement.execute(sql);
			statement.close();

		} catch (SQLException e) {
			System.out.println(e);

			mensajeError = e.getMessage();

		}
		return mensajeError;
	}

	public static void insertarPartidasUsuario(Connection con, String guardarUsuario) {

		for (int i = 1; i < 4; i++) {

			String sql = "INSERT INTO PARTIDA (ID_PARTIDA, NOMBRE_USUARIO)" + "VALUES(" + i + "," + "'" + guardarUsuario
					+ "'" + ")";

			try { // SE EJECUTA LA SENTENCIA PARA CREAR LOS SLOTS DE CADA USUARIO

				Statement statement = (Statement) con.createStatement();
				statement.execute(sql);
				statement.close();

			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}

		for (int i = 1; i < 4; i++) {

			String sql = "INSERT INTO INFO_CIUDADES (ID_PARTIDA, USUARIO)" + "VALUES(" + i + "," + "'" + guardarUsuario
					+ "'" + ")";

			try { // SE EJECUTA LA SENTENCIA PARA CREAR LOS SLOTS DE CADA USUARIO

				Statement statement = (Statement) con.createStatement();
				statement.execute(sql);
				statement.close();

			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}

		System.out.println("Se han creado los slots de guardado del usuario: " + guardarUsuario);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, -30, this);
		g.drawImage(Logo, 280, 100, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == volver) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelPrincipal());
			marco.setVisible(true);
		}
	}
}
