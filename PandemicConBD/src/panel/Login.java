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

	JButton enviar;
	JButton registrar;

	Image image;
	Image Logo;
	Image recuadro;

	String guardarUsuario;
	String guardarPass;
	String mensajeError;

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

		enviar = new JButton("Entrar");
		enviar.setBounds(200, 70, 100, 30);
		enviar.setLocation(700, 650);
		enviar.setFont(new Font("Comforta", Font.BOLD, 18));
		enviar.setBackground(Color.WHITE);
		enviar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				enviar.setBackground(Color.GRAY);
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				enviar.setBackground(Color.WHITE);
				setCursor(cur);
			}
		});

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
		add(enviar);
		add(registrar);

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
		// intentamos la conexion a la base de datos
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PWD);

			System.out.println("Conexión establecida con la base de datos");

		} catch (SQLException e) {
			throw new IllegalStateException("No se ha podido conectar a la base de datos ", e);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// devolvemos el valor de la conexion
		return con;
	}

	public static void closeConnection(Connection con) {
		// cierra la conexi�n
		try {
			con.close();
			System.out.println("Se ha cerrado la conexión");
		} catch (SQLException e) {
			System.out.println("Ha ocurrido un error cerrando la conexión: " + e);

		}
	}

	public static void selectWithStatement(Connection con) {

		String sql = "SELECT * FROM USUARIO";

		Statement st = null;

		try {
			st = con.createStatement();

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				int puntuacion = rs.getInt("puntos");

				System.out.println("Id:" + id + " Nombre:" + nombre + " Puntuacion:" + puntuacion);
			}

			st.close();

		} catch (SQLException e) {
			System.out.println("The SELECT had problems!! " + e);

		}
	}

	public static String insertWithStatement(Connection con, String guardarUsuario, String guardarPass) {

		String sql = "INSERT INTO USUARIO (usuario, contraseña) VALUES ('" + guardarUsuario + "',' " + guardarPass
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
					JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
					marco.remove(this);
					marco.add(new PanelEscogerDificultad());
					marco.setVisible(true);
					closeConnection(connection);
				} else {
					String[] partes = mensajeError.split("-");
					txtNombre.setText("");
					txtPass.setText("");
					mensaje.setText(partes[2]);
				}
			} else {
				mensaje.setText("Formato incorrecto, vuelva a introducir los valores");
			}
			txtNombre.setText("");
			txtPass.setText("");
		}

		else if (e.getSource() == enviar) {
			guardarUsuario = txtNombre.getText();
			guardarPass = txtPass.getText();
			if (guardarUsuario.length() > 0 && guardarPass.length() > 0) {

			}
		}
	}
}
