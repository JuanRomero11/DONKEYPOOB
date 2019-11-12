package presentacion;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class tablero extends JPanel{
	public static final String fondoInit = "resources/fondo2.png";
	private BufferedImage fondo;
	protected JButton jugdor1, jugador2 ,abrir,controles, salir;
	public tablero(String root){
		super(null);
		prepareElementosInicio();
		try{
			fondo = ImageIO.read(new File(root));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void prepareElementosInicio(){
		removeAll();
		jugdor1 = new Boton("1J", 300, 340);
		add(jugdor1);
		
		jugador2 = new  Boton("2J", 300, 400);
		add(jugador2);
		controles = new Boton("Controles",300,460);
		add(controles);
		salir = new Boton("salir", 300, 515);
		add(salir);

		
		 
		repaint();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.drawImage(fondo, 0, 0, this);
    }
}
