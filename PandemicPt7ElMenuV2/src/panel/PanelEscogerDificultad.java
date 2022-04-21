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

public class PanelEscogerDificultad extends JPanel implements ActionListener {
	JButton facil;
	JButton normal;
	JButton dificil;
	JButton personalizado;

	JButton volver;

	JLabel texto;

	Image dificultadtitulo;
	Image image;
	Image recuadro;
	Image logo;

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
						"<html><br><br><br><br><br>La dificultad fácil:<br><br>-5 puntos de acción<br><br>-Pandemia más lenta<br><br>-Destinado a nuevos<br> jugadores<html>");
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
						"<html><br><br><br><br><br>La dificultad normal:<br><br>-4 puntos de acción<br><br>-Pandemia por defecto<br><br>-El modo en el cual<br>se planteo el juego<html>");
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

		dificil = new JButton("Difícil");
		dificil.setSize(200, 50);
		dificil.setLocation(420, 580);
		dificil.setFont(new Font("Arial", Font.BOLD, 20));
		dificil.setBackground(Color.green);
		dificil.setBorder(new LineBorder(Color.BLACK));
		dificil.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				dificil.setBackground(Color.MAGENTA);
				setCursor(cur2);
				texto.setText(
						"<html><br><br><br><br><br>La dificultad difícil:<br><br>- Empieza con 3 puntos de acción<br><br>-Pandemia veloz<br><br>-Para jugadores<br>experimentados<html>");
				add(texto);
			}

			public void mouseExited(MouseEvent e) {
				dificil.setBackground(Color.green);
				setCursor(cur);
				texto.setText("<html>Escoja una dificultad<br><br><br><html>");
				add(texto);
			}
		});

		dificil.addActionListener(this);

		add(dificil);

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
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelNuevaPartida());
			marco.setVisible(true);

			try {
				Enfermedades.ronda(Enfermedades.NomFit1, Enfermedades.NomFit, Enfermedades.n0, Enfermedades.ciudades,
						Enfermedades.NomFit2, Enfermedades.Ciudad_Enfermedad);
				for (int j = 0; j < Enfermedades.Ciudad_Enfermedad.length; j++) {
					System.out.println(Enfermedades.Ciudad_Enfermedad[j]);
				}

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (e.getSource() == normal) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelNuevaPartida());
			marco.setVisible(true);
		} else if (e.getSource() == dificil) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelNuevaPartida());
			marco.setVisible(true);
		} else if (e.getSource() == personalizado) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelNuevaPartida());
			marco.setVisible(true);
		} else if (e.getSource() == volver) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelPrincipal());
			marco.setVisible(true);
		}
	}

}
