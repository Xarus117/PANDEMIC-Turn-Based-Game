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
import javax.swing.*;
import javax.swing.border.LineBorder;

public class PanelVersion extends JPanel implements ActionListener {
	JButton volver;
	Image image;
	Image versiones;

	PanelVersion() {
		setLayout(null);
		Image im = Toolkit.getDefaultToolkit().createImage("imagenes//cursorDefecto.png");
		Image im2 = Toolkit.getDefaultToolkit().createImage("imagenes//cursorHover.png");
		Cursor cur = Toolkit.getDefaultToolkit().createCustomCursor(im, new Point(10,10),"WILL");
		Cursor cur2 = Toolkit.getDefaultToolkit().createCustomCursor(im2, new Point(10,10),"WILL");
		setCursor(cur);

		volver = new JButton("Volver");
		volver.setSize(200, 50);
		volver.setLocation(550, 800);
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
			versiones = ImageIO.read(new File("Imagenes//Versiones.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(image, 0, 0, this);
		g.drawImage(versiones, 350, 200, this);

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