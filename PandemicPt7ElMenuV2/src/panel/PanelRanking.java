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

	Image Fondo;
	Image Ranking;

	static String guardarUsuario;
	static String guardarPass;

	private static final String USER = "PND_QALQO";
	private static final String PWD = "TYX1234";
	private static final String URL = "jdbc:oracle:thin:@oracle.ilerna.com:1521:xe";

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

		try {
			Fondo = ImageIO.read(new File("Imagenes//Fondo.jpg"));
			Ranking = ImageIO.read(new File("Imagenes//Ranking.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] rankingNombre = new String[9];
		int[] rankingPuntos = new int[9];

		Connection con = makeConnection();

		String sql = "SELECT USUARIO, PUNTOS" + " FROM USUARIO" + " ORDER BY PUNTOS ASC";

		Statement st = null;

		try {
			st = con.createStatement();

			ResultSet rs = st.executeQuery(sql);

			for (int i = 0; i < 9; i++) {
				rs.next();
				rankingNombre[i] = rs.getString("usuario");
				rankingPuntos[i] = rs.getInt("puntos");
			}
			st.close();

		} catch (SQLException e) {
			System.out.println("Ha habído un error con el select: " + e);

		}

		for (int i = 0; i < 9; i++) {
			if (rankingNombre[i] == null) {
				rankingNombre[i] = "";
			}

			primero = new JLabel(rankingNombre[0] + ": " + rankingPuntos[0]);
			primero.setSize(250, 50);
			primero.setLocation(550, 240);
			primero.setFont(new Font("Arial", Font.BOLD, 40));
			primero.setBackground(new Color(247, 185, 71));
			primero.setForeground(Color.black);

			segundo = new JLabel(rankingNombre[1] + ": " + rankingPuntos[1]);
			segundo.setSize(250, 50);
			segundo.setLocation(550, 330);
			segundo.setFont(new Font("Arial", Font.BOLD, 40));
			segundo.setBackground(new Color(247, 185, 71));
			segundo.setForeground(Color.black);

			tercero = new JLabel(rankingNombre[2] + ": " + rankingPuntos[2]);
			tercero.setSize(250, 50);
			tercero.setLocation(550, 420);
			tercero.setFont(new Font("Arial", Font.BOLD, 40));
			tercero.setBackground(new Color(247, 185, 71));
			tercero.setForeground(Color.black);

			cuarto = new JLabel(rankingNombre[3] + ": " + rankingPuntos[3]);
			cuarto.setSize(200, 50);
			cuarto.setLocation(520, 510);
			cuarto.setFont(new Font("Arial", Font.BOLD, 20));
			cuarto.setBackground(new Color(247, 185, 71));
			cuarto.setForeground(Color.black);

			quinto = new JLabel(rankingNombre[4] + ": " + rankingPuntos[4]);
			quinto.setSize(200, 50);
			quinto.setLocation(520, 555);
			quinto.setFont(new Font("Arial", Font.BOLD, 20));
			quinto.setBackground(new Color(247, 185, 71));
			quinto.setForeground(Color.black);

			sexto = new JLabel(rankingNombre[5] + ": " + rankingPuntos[5]);
			sexto.setSize(200, 50);
			sexto.setLocation(520, 600);
			sexto.setFont(new Font("Arial", Font.BOLD, 20));
			sexto.setBackground(new Color(247, 185, 71));
			sexto.setForeground(Color.black);

			septimo = new JLabel(rankingNombre[6] + ": " + rankingPuntos[6]);
			septimo.setSize(200, 50);
			septimo.setLocation(520, 640);
			septimo.setFont(new Font("Arial", Font.BOLD, 20));
			septimo.setBackground(new Color(247, 185, 71));
			septimo.setForeground(Color.black);

			octavo = new JLabel(rankingNombre[7] + ": " + rankingPuntos[7]);
			octavo.setSize(200, 50);
			octavo.setLocation(520, 680);
			octavo.setFont(new Font("Arial", Font.BOLD, 20));
			octavo.setBackground(new Color(247, 185, 71));
			octavo.setForeground(Color.black);

			noveno = new JLabel(rankingNombre[8] + ": " + rankingPuntos[8]);
			noveno.setSize(200, 50);
			noveno.setLocation(520, 725);
			noveno.setFont(new Font("Arial", Font.BOLD, 20));
			noveno.setBackground(new Color(247, 185, 71));
			noveno.setForeground(Color.black);

			add(primero);
			add(segundo);
			add(tercero);
			add(cuarto);
			add(quinto);
			add(sexto);
			add(septimo);
			add(octavo);
			add(noveno);
			add(volver);
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
