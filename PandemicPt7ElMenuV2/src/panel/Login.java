package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Login extends JPanel implements ActionListener {
	JTextField txtNombre;
	JButton enviar;
	JLabel etiNombre;
	Image image;
	Image Logo;
	Image recuadro;

	Login() {
		setLayout(null);
		etiNombre = new JLabel("Introduzca su nombre");
		etiNombre.setBounds(30, 30, 200, 30);
		etiNombre.setLocation(500, 450);
		txtNombre = new JTextField();
		txtNombre.setBounds(200, 30, 150, 30);
		txtNombre.setLocation(650, 450);
		enviar = new JButton("Enviar");
		enviar.setBounds(200, 70, 100, 30);
		enviar.setLocation(600, 650);
		enviar.setFont(new Font("Comforta", Font.BOLD, 18));

		enviar.addActionListener(this);

		add(etiNombre);
		add(txtNombre);
		add(enviar);
		try {
			image = ImageIO.read(new File("Imagenes//Fondo.jpg"));
			Logo = ImageIO.read(new File("Imagenes//LOGO.png"));
			recuadro = ImageIO.read(new File("Imagenes//recuadro.jpg"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(image, 0, -30, this);
		g.drawImage(Logo, 90, -80, this);
		g.drawImage(recuadro,400,400,this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == enviar) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelNuevaPartida());
			marco.setVisible(true);
		}
	}
}
