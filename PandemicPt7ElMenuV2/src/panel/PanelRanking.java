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

public class PanelRanking extends JPanel implements ActionListener {
	JButton slot1;
	JButton slot2;
	JButton slot3;
	JButton volver;

	Image Fondo;
	Image Ranking;

	static String guardarUsuario;
	static String guardarPass;
	String mensajeError;
	int numeroError;

	boolean continuar = false;

	private static final String USER = "PND_QALQO";
	private static final String PWD = "TYX1234";
	private static final String URL = "jdbc:oracle:thin:@192.168.3.26:1521:xe";

	PanelRanking() {
		setLayout(null);
		Image im = Toolkit.getDefaultToolkit().createImage("imagenes//cursorDefecto.png");
		Image im2 = Toolkit.getDefaultToolkit().createImage("imagenes//cursorHover.png");
		Cursor cur = Toolkit.getDefaultToolkit().createCustomCursor(im, new Point(10, 10), "WILL");
		Cursor cur2 = Toolkit.getDefaultToolkit().createCustomCursor(im2, new Point(10, 10), "WILL");
		setCursor(cur);

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
		add(volver);

		try {
			Fondo = ImageIO.read(new File("Imagenes//Fondo.jpg"));
			Ranking = ImageIO.read(new File("Imagenes//Ranking.png"));

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
		g.drawImage(Fondo, 0, -30, this);
		g.drawImage(Ranking, 400, 110, this);
		
		
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
