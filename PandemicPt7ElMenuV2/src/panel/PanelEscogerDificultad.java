package panel;

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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class PanelEscogerDificultad extends JPanel implements ActionListener {

	JButton facil;
	JButton normal;

	JButton volver;

	JLabel Textos;

	Image dificultadtitulo;
	Image Fondo;
	Image recuadro;
	Image logo;

	static int dificultad = 0;

	PanelEscogerDificultad() {
		setLayout(null);
		Image im = Toolkit.getDefaultToolkit().createImage("imagenes//cursorDefecto.png");
		Image im2 = Toolkit.getDefaultToolkit().createImage("imagenes//cursorHover.png");
		Cursor cur = Toolkit.getDefaultToolkit().createCustomCursor(im, new Point(10, 10), "WILL");
		Cursor cur2 = Toolkit.getDefaultToolkit().createCustomCursor(im2, new Point(10, 10), "WILL");
		setCursor(cur);

		Textos = new JLabel("<html>Escoja una dificultad<br><br><br><html>");
		Textos.setFont(new Font("Arial", Font.BOLD, 16));
		Textos.setBounds(635, 310, 315, 340);
		Textos.setForeground(Color.black);
		add(Textos);

		facil = new JButton("Fácil");
		facil.setSize(200, 50);
		facil.setLocation(420, 440);
		facil.setFont(new Font("Arial", Font.BOLD, 20));
		facil.setBackground(new Color(247, 185, 71));
		facil.setBorder(new LineBorder(Color.BLACK));
		facil.setForeground(Color.black);
		facil.addActionListener(this);
		facil.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				facil.setBackground(Color.cyan);
				setCursor(cur2);
				Textos.setText(
						"<html><br><br><br><br><br>La dificultad fácil:<br><br>-4 puntos de acción<br><br>-5 infecciones por ronda<br><br>-80 infecciones y 7 brotes <br>para derrota<br><br>-Destinado a nuevos jugadores<br><html>");
				add(Textos);
			}

			public void mouseExited(MouseEvent e) {
				facil.setBackground(new Color(247, 185, 71));
				setCursor(cur);
				Textos.setText("<html>Escoja una dificultad<br><br><br><html>");
				add(Textos);
			}
		});
		normal = new JButton("Normal");
		normal.setSize(200, 50);
		normal.setLocation(420, 510);
		normal.setFont(new Font("Arial", Font.BOLD, 20));
		normal.setBackground(new Color(247, 185, 71));
		normal.setBorder(new LineBorder(Color.BLACK));
		normal.setForeground(Color.black);
		normal.addActionListener(this);
		normal.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				normal.setBackground(Color.yellow);
				setCursor(cur2);
				Textos.setText(
						"<html><br><br><br><br><br>La dificultad normal:<br><br>-4 puntos de acción<br><br>-7 infecciones por ronda<br><br>-80 infecciones y 5 brotes <br>para derrota<br><br>-El modo en el cual<br>el juego no será tan fácil<html>");
				add(Textos);
			}

			public void mouseExited(MouseEvent e) {
				normal.setBackground(new Color(247, 185, 71));
				setCursor(cur);
				Textos.setText("<html>Escoja una dificultad<br><br><br><html>");
				add(Textos);
			}
		});
		volver = new JButton("Volver");
		volver.setSize(200, 50);
		volver.setLocation(420, 580);
		volver.setFont(new Font("Arial", Font.BOLD, 20));
		volver.setBackground(new Color(247, 185, 71));
		volver.setBorder(new LineBorder(Color.BLACK));
		volver.setForeground(Color.black);
		volver.addActionListener(this);
		volver.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				volver.setBackground(Color.GRAY);
				setCursor(cur2);
				Textos.setText("<html>Volver al menú<html>");
				add(Textos);
			}

			public void mouseExited(MouseEvent e) {
				volver.setBackground(new Color(247, 185, 71));
				setCursor(cur);
				Textos.setText("<html>Escoja una dificultad<br><br><br><html>");
				add(Textos);
			}
		});
		
		add(facil);
		add(normal);
		add(volver);

		try {
			Fondo = ImageIO.read(new File("Imagenes//Fondo.jpg"));
			recuadro = ImageIO.read(new File("Imagenes//Recuadro3.png"));
			logo = ImageIO.read(new File("Imagenes//LOGO.png"));
			dificultadtitulo = ImageIO.read(new File("Imagenes//Dificultad.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Fondo, 0, 0, this);
		g.drawImage(logo, 90, -80, this);
		g.drawImage(recuadro, 400, 360, this);
		g.drawImage(dificultadtitulo, 400, 270, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == facil) {
			dificultad = 1;
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			try {
				marco.add(new PanelNuevaPartida());
			} catch (ParserConfigurationException e1) {
				e1.printStackTrace();
			} catch (SAXException e1) {
				e1.printStackTrace();
			}
			marco.setVisible(true);
		} else if (e.getSource() == normal) {
			dificultad = 2;
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			try {
				marco.add(new PanelNuevaPartida());
			} catch (ParserConfigurationException e1) {
				e1.printStackTrace();
			} catch (SAXException e1) {
				e1.printStackTrace();
			}
			marco.setVisible(true);
		} else if (e.getSource() == volver) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelPrincipal());
			marco.setVisible(true);
		}
	}

}
