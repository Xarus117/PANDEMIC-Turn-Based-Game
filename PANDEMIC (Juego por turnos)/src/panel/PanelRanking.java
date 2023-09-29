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
	JButton volver;

	static JLabel primero, segundo, tercero, cuarto, quinto, sexto, septimo, octavo, noveno;

	Image Fondo, Ranking;

	static String guardarUsuario, guardarPass;

	private static final String USER = "PND_QALQO";
	private static final String PWD = "TYX1234";
	private static final String URL = "jdbc:oracle:thin:@192.168.3.26:1521:xe";

	PanelRanking() {
		setLayout(null);
		Image im = Toolkit.getDefaultToolkit().createImage("imagenes//cursorDefecto.png"),
				im2 = Toolkit.getDefaultToolkit().createImage("imagenes//cursorHover.png");
		Cursor cur = Toolkit.getDefaultToolkit().createCustomCursor(im, new Point(10, 10), "WILL"),
				cur2 = Toolkit.getDefaultToolkit().createCustomCursor(im2, new Point(10, 10), "WILL");
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

		try {
			Fondo = ImageIO.read(new File("Imagenes//Fondo.jpg"));
			Ranking = ImageIO.read(new File("Imagenes//Ranking.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] rankingNombre = new String[9];
		int[] rankingPuntos = new int[9], rankingRondas = new int[9];

		Connection con = makeConnection();
		String sql = "SELECT USUARIO, PUNTOS*100/PARTIDAS, RONDAS_SOBREVIVIDAS" + " FROM USUARIO"
				+ " ORDER BY PUNTOS*PARTIDAS/100, RONDAS_SOBREVIVIDAS DESC";

		Statement st = null;

		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			for (int i = 0; i < 9; i++) {
				rs.next();
				rankingNombre[i] = rs.getString("usuario");
				rankingPuntos[i] = rs.getInt("PUNTOS*100/PARTIDAS");
				rankingRondas[i] = rs.getInt("RONDAS_SOBREVIVIDAS");
			}
			st.close();

		} catch (SQLException e) {
			System.out.println("Ha hab�do un error con el select: " + e);

		}

		if (rankingNombre[0] != null && rankingPuntos[0] > 0) {
			primero = new JLabel(
					rankingNombre[0] + ": " + rankingPuntos[0] + "%" + " Rondas jugadas: " + rankingRondas[0]);
			primero.setSize(550, 50);
			primero.setLocation(450, 240);
			primero.setFont(new Font("Arial", Font.BOLD, 28));
			primero.setBackground(new Color(247, 185, 71));
			primero.setForeground(Color.black);
			add(primero);
		}
		if (rankingNombre[1] != null && rankingPuntos[1] > 0) {
			segundo = new JLabel(
					rankingNombre[1] + ": " + rankingPuntos[1] + "%" + " Rondas jugadas: " + rankingRondas[1]);
			segundo.setSize(550, 50);
			segundo.setLocation(450, 310);
			segundo.setFont(new Font("Arial", Font.BOLD, 28));
			segundo.setBackground(new Color(247, 185, 71));
			segundo.setForeground(Color.black);
			add(segundo);
		}
		if (rankingNombre[2] != null && rankingPuntos[2] > 0) {
			tercero = new JLabel(
					rankingNombre[2] + ": " + rankingPuntos[2] + "%" + " Rondas jugadas: " + rankingRondas[2]);
			tercero.setSize(550, 50);
			tercero.setLocation(450, 380);
			tercero.setFont(new Font("Arial", Font.BOLD, 28));
			tercero.setBackground(new Color(247, 185, 71));
			tercero.setForeground(Color.black);
			add(tercero);
		}
		if (rankingNombre[3] != null && rankingPuntos[3] > 0) {
			cuarto = new JLabel(
					rankingNombre[3] + ": " + rankingPuntos[3] + "%" + " Rondas jugadas: " + rankingRondas[3]);
			cuarto.setSize(550, 50);
			cuarto.setLocation(400, 460);
			cuarto.setFont(new Font("Arial", Font.BOLD, 15));
			cuarto.setBackground(new Color(247, 185, 71));
			cuarto.setForeground(Color.black);
			add(cuarto);
		}
		if (rankingNombre[4] != null && rankingPuntos[4] > 0) {
			quinto = new JLabel(
					rankingNombre[4] + ": " + rankingPuntos[4] + "%" + " Rondas jugadas: " + rankingRondas[4]);
			quinto.setSize(550, 50);
			quinto.setLocation(400, 500);
			quinto.setFont(new Font("Arial", Font.BOLD, 15));
			quinto.setBackground(new Color(247, 185, 71));
			quinto.setForeground(Color.black);
			add(quinto);
		}
		if (rankingNombre[5] != null && rankingPuntos[5] > 0) {
			sexto = new JLabel(
					rankingNombre[5] + ": " + rankingPuntos[5] + "%" + " Rondas jugadas: " + rankingRondas[5]);
			sexto.setSize(550, 50);
			sexto.setLocation(400, 540);
			sexto.setFont(new Font("Arial", Font.BOLD, 15));
			sexto.setBackground(new Color(247, 185, 71));
			sexto.setForeground(Color.black);
			add(sexto);
		}
		if (rankingNombre[6] != null && rankingPuntos[6] > 0) {
			septimo = new JLabel(
					rankingNombre[6] + ": " + rankingPuntos[6] + "%" + " Rondas jugadas: " + rankingRondas[6]);
			septimo.setSize(550, 50);
			septimo.setLocation(400, 580);
			septimo.setFont(new Font("Arial", Font.BOLD, 15));
			septimo.setBackground(new Color(247, 185, 71));
			septimo.setForeground(Color.black);
			add(septimo);
		}
		if (rankingNombre[7] != null && rankingPuntos[7] > 0) {
			octavo = new JLabel(
					rankingNombre[7] + ": " + rankingPuntos[7] + "%" + " Rondas jugadas: " + rankingRondas[7]);
			octavo.setSize(550, 50);
			octavo.setLocation(400, 620);
			octavo.setFont(new Font("Arial", Font.BOLD, 15));
			octavo.setBackground(new Color(247, 185, 71));
			octavo.setForeground(Color.black);
			add(octavo);
		}
		if (rankingNombre[8] != null && rankingPuntos[8] > 0) {
			noveno = new JLabel(
					rankingNombre[8] + ": " + rankingPuntos[8] + "%" + " Rondas jugadas: " + rankingRondas[8]);
			noveno.setSize(550, 50);
			noveno.setLocation(400, 640);
			noveno.setFont(new Font("Arial", Font.BOLD, 15));
			noveno.setBackground(new Color(247, 185, 71));
			noveno.setForeground(Color.black);
			add(noveno);
		}
		add(volver);
	}

	public static Connection makeConnection() {
		System.out.println("Conectando a la base de datos...");
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PWD);
			System.out.println("Conexi�n establecida con la base de datos");
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
			System.out.println("Se ha cerrado la conexi�n");
		} catch (SQLException e) {
			System.out.println("Ha ocurrido un error cerrando la conexi�n: " + e);

		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Fondo, 0, -30, this);
		g.drawImage(Ranking, 300, 110, this);

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