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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PanelEscogerDificultad extends JPanel implements ActionListener {

	 JButton facil;
	 JButton normal;


	JButton volver;

	JLabel texto;

	Image dificultadtitulo;
	Image image;
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

		texto = new JLabel("<html>Escoja una dificultad<br><br><br><html>");
		texto.setFont(new Font("Arial", Font.BOLD, 20));
		texto.setBounds(635, 310, 315, 340);
		texto.setForeground(Color.white);
		add(texto);

		facil = new JButton("Fácil");
		facil.setSize(200, 50);
		facil.setLocation(420, 440);
		facil.setFont(new Font("Arial", Font.BOLD, 20));
		facil.setBackground(Color.green);
		facil.setBorder(new LineBorder(Color.BLACK));
		facil.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				facil.setBackground(Color.cyan);
				setCursor(cur2);
				texto.setText(
						"<html><br><br><br><br><br>La dificultad fácil:<br><br>-4 puntos de acción<br><br>-5 infecciones por ronda<br><br>-80 infecciones y 7 brotes para derrota<br><br>-Destinado a nuevos jugadores<br><html>");
				add(texto);
			}

			public void mouseExited(MouseEvent e) {
				facil.setBackground(Color.green);
				setCursor(cur);
				texto.setText("<html>Escoja una dificultad<br><br><br><html>");
				add(texto);
			}
		});

		facil.addActionListener(this);

		add(facil);

		normal = new JButton("Normal");
		normal.setSize(200, 50);
		normal.setLocation(420, 510);
		normal.setFont(new Font("Arial", Font.BOLD, 20));
		normal.setBackground(Color.green);
		normal.setBorder(new LineBorder(Color.BLACK));
		normal.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				normal.setBackground(Color.yellow);
				setCursor(cur2);
				texto.setText(
						"<html><br><br><br><br><br>La dificultad normal:<br><br>-4 puntos de acción<br><br>-7 infecciones por ronda<br><br>-80 infecciones y 5 brotes para derrota<br><br>-El modo en el cual<br>el juego no será tan fácil<html>");
				add(texto);
			}

			public void mouseExited(MouseEvent e) {
				normal.setBackground(Color.green);
				setCursor(cur);
				texto.setText("<html>Escoja una dificultad<br><br><br><html>");
				add(texto);
			}
		});

		normal.addActionListener(this);

		add(normal);

		volver = new JButton("Volver");
		volver.setSize(200, 50);
		volver.setLocation(550, 710);
		volver.setFont(new Font("Arial", Font.BOLD, 20));
		volver.setBackground(Color.green);
		volver.setBorder(new LineBorder(Color.BLACK));
		volver.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				volver.setBackground(Color.GRAY);
				setCursor(cur2);
			}

			public void mouseExited(MouseEvent e) {
				volver.setBackground(Color.green);
				setCursor(cur);
			}
		});
		volver.addActionListener(this);

		add(volver);

		try {
			image = ImageIO.read(new File("Imagenes//Fondo.jpg"));
			recuadro = ImageIO.read(new File("Imagenes//Recuadro.jpg"));
			logo = ImageIO.read(new File("Imagenes//LOGO.png"));
			dificultadtitulo = ImageIO.read(new File("Imagenes//dificultadtitulo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(image, 0, 0, this);
		g.drawImage(logo, 90, -80, this);
		g.drawImage(recuadro, 400, 400, this);
		g.drawImage(dificultadtitulo, 400, 220, this);

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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SAXException e1) {
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SAXException e1) {
				// TODO Auto-generated catch block
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
