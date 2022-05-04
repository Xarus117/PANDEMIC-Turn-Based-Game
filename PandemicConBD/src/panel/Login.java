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

public class Login extends JPanel implements ActionListener {
	JTextField txtNombre;
	JTextField txtPass;
	JLabel etiNombre;
	JLabel etiPass;
	JLabel mensaje;

	JButton login;
	JButton registrar;

	Image image;
	Image Logo;
	Image recuadro;

	static String guardarUsuario;
	static String guardarPass;
	String mensajeError;
	int numeroError;

	boolean continuar = false;

	private static final String USER = "PND_QALQO";
	private static final String PWD = "TYX1234";
	private static final String URL = "jdbc:oracle:thin:@192.168.3.26:1521:xe";

	Login() {
		setLayout(null);
		Image im = Toolkit.getDefaultToolkit().createImage("imagenes//cursorDefecto.png");
		Image im2 = Toolkit.getDefaultToolkit().createImage("imagenes//cursorHover.png");
		Cursor cur = Toolkit.getDefaultToolkit().createCustomCursor(im, new Point(10, 10), "WILL");
		Cursor cur2 = Toolkit.getDefaultToolkit().createCustomCursor(im2, new Point(10, 10), "WILL");
		setCursor(cur);
		etiNombre = new JLabel("Introduzca su nombre");
		etiNombre.setBounds(30, 30, 200, 30);
		etiNombre.setLocation(500, 450);

		etiPass = new JLabel("Introduzca la contraseña");
		etiPass.setBounds(30, 30, 200, 30);
		etiPass.setLocation(500, 550);

		mensaje = new JLabel("");
		mensaje.setBounds(30, 30, 400, 30);
		mensaje.setLocation(500, 600);
		mensaje.setForeground(Color.red);

		txtNombre = new JTextField();
		txtNombre.setBounds(200, 30, 150, 30);
		txtNombre.setLocation(650, 450);

		txtPass = new JTextField();
		txtPass.setBounds(200, 30, 150, 30);
		txtPass.setLocation(650, 550);

		login = new JButton("Entrar");
		login.setBounds(200, 70, 100, 30);
		login.setLocation(700, 650);
		login.setFont(new Font("Comforta", Font.BOLD, 18));
		login.setBackground(Color.WHITE);
		login.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				login.setBackground(Color.GRAY);
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				login.setBackground(Color.WHITE);
				setCursor(cur);
			}
		});

		login.addActionListener(this);

		registrar = new JButton("Registrar");
		registrar.setBounds(200, 70, 150, 30);
		registrar.setLocation(500, 650);
		registrar.setFont(new Font("Comforta", Font.BOLD, 18));
		registrar.setBackground(Color.WHITE);
		registrar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				registrar.setBackground(Color.GRAY);
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				registrar.setBackground(Color.WHITE);
				setCursor(cur);
			}
		});

		registrar.addActionListener(this);

		add(etiNombre);
		add(etiPass);
		add(txtNombre);
		add(txtPass);
		add(mensaje);
		add(registrar);
		add(login);

		try {
			image = ImageIO.read(new File("Imagenes//Fondo.jpg"));
			Logo = ImageIO.read(new File("Imagenes//LOGO.png"));
			recuadro = ImageIO.read(new File("Imagenes//recuadro.jpg"));

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
		g.drawImage(Logo, 90, -80, this);
		g.drawImage(recuadro, 400, 400, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == registrar) {
			guardarUsuario = txtNombre.getText();
			guardarPass = txtPass.getText();

			if (guardarUsuario.length() > 0 && guardarPass.length() > 0) {
				Connection connection = makeConnection();
				mensajeError = insertWithStatement(connection, guardarUsuario, guardarPass);
				if (mensajeError == "Correcto") {
					mensaje.setText("¡REGISTRO REALIZADO! Ahora debe iniciar sesión");
					insertarPartidasUsuario(connection, guardarUsuario);
				} else {
					String[] partes = mensajeError.split("-");
					txtNombre.setText("");
					txtPass.setText("");
					mensaje.setText(partes[2]);
				}
				closeConnection(connection);
			} else {
				mensaje.setText("Formato incorrecto, vuelva a introducir los valores");
			}
			txtNombre.setText("");
			txtPass.setText("");

		}

		else if (e.getSource() == login) {
			guardarUsuario = txtNombre.getText();
			guardarPass = txtPass.getText();

			if (guardarUsuario.length() > 0 && guardarPass.length() > 0) {
				Connection connection = makeConnection();
				numeroError = selectWithStatement(connection, guardarUsuario, guardarPass);
				closeConnection(connection);
				if (numeroError == 1) {
					JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
					marco.remove(this);
					marco.add(new PanelEscogerDificultad());
					marco.setVisible(true);

				} else {
					txtNombre.setText("");
					txtPass.setText("");
					mensaje.setText("Creedenciales incorrectas");
				}
			}

		}
	}
}
