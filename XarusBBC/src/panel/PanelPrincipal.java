package panel;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.*;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class PanelPrincipal extends JPanel implements ActionListener {

	int contadorVolumen = 0;
	JButton boton1;
	JButton boton2;
	JButton boton3;
	JButton boton4;
	JButton boton5;
	JButton boton6;
	JButton boton7;
	JButton boton8;
	JButton discord;
	ImageIcon buttonIcon;
	Image image;
	Image Logo;
	Image discordI;

	PanelPrincipal() {
		int partidas = 1;
		setLayout(null);
		Image im = Toolkit.getDefaultToolkit().createImage("imagenes//cursorDefecto.png");
		Image im2 = Toolkit.getDefaultToolkit().createImage("imagenes//cursorHover.png");
		Cursor cur = Toolkit.getDefaultToolkit().createCustomCursor(im, new Point(10, 10), "WILL");
		Cursor cur2 = Toolkit.getDefaultToolkit().createCustomCursor(im2, new Point(10, 10), "WILL");
		setCursor(cur);

		if (partidas > 0) {
			boton1 = new JButton("Nueva Partida");
			boton1.setSize(200, 50);
			boton1.setLocation(550, 300);
			boton1.setFont(new Font("Arial", Font.BOLD, 18));
			boton1.setBackground(Color.green);
			boton1.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					boton1.setBackground(Color.GRAY);
					setCursor(cur2);

				}

				public void mouseExited(MouseEvent e) {
					boton1.setBackground(Color.green);
					setCursor(cur);

				}
			});
			boton2 = new JButton("Nueva Partida +");
			boton2.setSize(200, 50);
			boton2.setLocation(550, 370);
			boton2.setFont(new Font("Arial", Font.BOLD, 18));
			boton2.setBackground(Color.green);
			boton2.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					boton2.setBackground(Color.GRAY);
					setCursor(cur2);
				}

				public void mouseExited(MouseEvent e) {
					boton2.setBackground(Color.green);
					setCursor(cur);
				}
			});
			boton3 = new JButton("Cargar Partida");
			boton3.setSize(200, 50);
			boton3.setLocation(550, 440);
			boton3.setFont(new Font("Arial", Font.BOLD, 18));
			boton3.setBackground(Color.green);
			boton3.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					boton3.setBackground(Color.GRAY);
					setCursor(cur2);
				}

				public void mouseExited(MouseEvent e) {
					boton3.setBackground(Color.green);
					setCursor(cur);
				}
			});
			boton4 = new JButton("Informaci�n");
			boton4.setSize(200, 50);
			boton4.setLocation(550, 510);
			boton4.setFont(new Font("Arial", Font.BOLD, 18));
			boton4.setBackground(Color.green);
			boton4.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					boton4.setBackground(Color.GRAY);
					setCursor(cur2);
				}

				public void mouseExited(MouseEvent e) {
					boton4.setBackground(Color.green);
					setCursor(cur);
				}
			});
			boton5 = new JButton("Ranking");
			boton5.setSize(200, 50);
			boton5.setLocation(550, 580);
			boton5.setFont(new Font("Arial", Font.BOLD, 18));
			boton5.setBackground(Color.green);
			boton5.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					boton5.setBackground(Color.GRAY);
					setCursor(cur2);
				}

				public void mouseExited(MouseEvent e) {
					boton5.setBackground(Color.green);
					setCursor(cur);
				}
			});
			boton6 = new JButton("Autores");
			boton6.setSize(200, 50);
			boton6.setLocation(550, 650);
			boton6.setFont(new Font("Arial", Font.BOLD, 18));
			boton6.setBackground(Color.green);
			boton6.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					boton6.setBackground(Color.GRAY);
					setCursor(cur2);
				}

				public void mouseExited(MouseEvent e) {
					boton6.setBackground(Color.green);
					setCursor(cur);
				}
			});
			boton7 = new JButton("Versi�n");
			boton7.setSize(200, 50);
			boton7.setLocation(550, 720);
			boton7.setFont(new Font("Arial", Font.BOLD, 18));
			boton7.setBackground(Color.green);
			boton7.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					boton7.setBackground(Color.GRAY);
					setCursor(cur2);
				}

				public void mouseExited(MouseEvent e) {
					boton7.setBackground(Color.green);
					setCursor(cur);
				}
			});
			boton8 = new JButton("Salir");
			boton8.setSize(200, 50);
			boton8.setLocation(550, 790);
			boton8.setFont(new Font("Arial", Font.BOLD, 18));
			boton8.setBackground(Color.green);
			boton8.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					boton8.setBackground(Color.GRAY);
					setCursor(cur2);
				}

				public void mouseExited(MouseEvent e) {
					boton8.setBackground(Color.green);
					setCursor(cur);
				}
			});

			boton1.setBorder(new LineBorder(Color.BLACK));
			boton2.setBorder(new LineBorder(Color.BLACK));
			boton3.setBorder(new LineBorder(Color.BLACK));
			boton4.setBorder(new LineBorder(Color.BLACK));
			boton5.setBorder(new LineBorder(Color.BLACK));
			boton6.setBorder(new LineBorder(Color.BLACK));
			boton7.setBorder(new LineBorder(Color.BLACK));
			boton8.setBorder(new LineBorder(Color.BLACK));

		} else {
			boton1 = new JButton("Nueva Partida");
			boton1.setSize(200, 50);
			boton1.setLocation(550, 300);
			boton1.setFont(new Font("Arial", Font.BOLD, 18));
			boton1.setBackground(Color.green);
			boton1.addMouseListener(new MouseAdapter() {

				public void mouseEntered(MouseEvent e) {
					boton1.setBackground(Color.GRAY);
					setCursor(cur2);
				}

				public void mouseExited(MouseEvent e) {
					boton1.setBackground(Color.green);
					setCursor(cur);
				}
			});
			boton3 = new JButton("Cargar Partida");
			boton3.setSize(200, 50);
			boton3.setLocation(550, 370);
			boton3.setFont(new Font("Arial", Font.BOLD, 18));
			boton3.setBackground(Color.green);
			boton3.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					boton3.setBackground(Color.GRAY);
					setCursor(cur2);
				}

				public void mouseExited(MouseEvent e) {
					boton3.setBackground(Color.green);
					setCursor(cur);
				}
			});
			boton4 = new JButton("Informaci�n");
			boton4.setSize(200, 50);
			boton4.setLocation(550, 440);
			boton4.setFont(new Font("Arial", Font.BOLD, 18));
			boton4.setBackground(Color.green);
			boton4.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					boton4.setBackground(Color.GRAY);
					setCursor(cur2);
				}

				public void mouseExited(MouseEvent e) {
					boton4.setBackground(Color.green);
					setCursor(cur);
				}
			});
			boton5 = new JButton("Ranking");
			boton5.setSize(200, 50);
			boton5.setLocation(550, 510);
			boton5.setFont(new Font("Arial", Font.BOLD, 18));
			boton5.setBackground(Color.green);
			boton5.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					boton5.setBackground(Color.GRAY);
					setCursor(cur2);
				}

				public void mouseExited(MouseEvent e) {
					boton5.setBackground(Color.green);
					setCursor(cur);
				}
			});
			boton6 = new JButton("Autores");
			boton6.setSize(200, 50);
			boton6.setLocation(550, 580);
			boton6.setFont(new Font("Arial", Font.BOLD, 18));
			boton6.setBackground(Color.green);
			boton6.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					boton6.setBackground(Color.GRAY);
					setCursor(cur2);
				}

				public void mouseExited(MouseEvent e) {
					boton6.setBackground(Color.green);
					setCursor(cur);
				}
			});
			boton7 = new JButton("Versi�n");
			boton7.setSize(200, 50);
			boton7.setLocation(550, 650);
			boton7.setFont(new Font("Arial", Font.BOLD, 18));
			boton7.setBackground(Color.green);
			boton7.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					boton7.setBackground(Color.GRAY);
					setCursor(cur2);
				}

				public void mouseExited(MouseEvent e) {
					boton7.setBackground(Color.green);
					setCursor(cur);
				}
			});
			boton8 = new JButton("Salir");
			boton8.setSize(200, 50);
			boton8.setLocation(550, 720);
			boton8.setFont(new Font("Arial", Font.BOLD, 18));
			boton8.setBackground(Color.green);
			boton8.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					boton8.setBackground(Color.GRAY);
					setCursor(cur2);
				}

				public void mouseExited(MouseEvent e) {
					boton8.setBackground(Color.green);
					setCursor(cur);
				}
			});

			boton1.setBorder(new LineBorder(Color.BLACK));
			boton3.setBorder(new LineBorder(Color.BLACK));
			boton4.setBorder(new LineBorder(Color.BLACK));
			boton5.setBorder(new LineBorder(Color.BLACK));
			boton6.setBorder(new LineBorder(Color.BLACK));
			boton7.setBorder(new LineBorder(Color.BLACK));
			boton8.setBorder(new LineBorder(Color.BLACK));
		}

		boton1.addActionListener(this);
		boton3.addActionListener(this);
		boton4.addActionListener(this);
		boton5.addActionListener(this);
		boton6.addActionListener(this);
		boton7.addActionListener(this);
		boton8.addActionListener(this);

		add(boton1);
		if (partidas > 0) {
			boton2.addActionListener(this);
			add(boton2);
		}
		add(boton3);
		add(boton4);
		add(boton5);
		add(boton6);
		add(boton7);
		add(boton8);

		// DISCORD

		try {
			discordI = ImageIO.read(new File("Imagenes//DiscordLogo.png"));
			image = ImageIO.read(new File("Imagenes//Fondo.jpg"));
			Logo = ImageIO.read(new File("Imagenes//LOGO.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		discord = new JButton();

		discord.setIcon(new ImageIcon(discordI));
		discord.setSize(100, 100);
		discord.setLocation(1150, 770);
		discord.setFont(new Font("Arial", Font.BOLD, 20));
		discord.setContentAreaFilled(false);
		discord.setBorder(null);

		discord.addActionListener(this);

		add(discord);

		// ----
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(image, 0, -30, this);
		g.drawImage(Logo, 90, -80, this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == boton1) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new Login());
			marco.setVisible(true);
		} else if (e.getSource() == boton2) {
		} else if (e.getSource() == boton3) {
		} else if (e.getSource() == boton4) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelReglas());
			marco.setVisible(true);
		} else if (e.getSource() == boton5) {
		} else if (e.getSource() == boton6) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelAutores());
			marco.setVisible(true);
		} else if (e.getSource() == boton7) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelVersion());
			marco.setVisible(true);
		} else if (e.getSource() == discord) {
			try {
				Desktop.getDesktop().browse(new URL("https://discord.gg/72D5jjT6Sg").toURI());
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}

		} else {
			System.exit(0); // Cuando se pulse salir, se cerrar� el juego
		}
	}

}