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
	protected JButton jugador1, jugador2 ,abrir,controles, salir, JvsD, JvsJ, JvsM, JvsM2 ,playM, playK, goBack,imagencontrol;
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
		
		jugador1 = new Boton("1J", 300, 340);
		add(jugador1);
		
		jugador2 = new  Boton("2J", 300, 400);
		add(jugador2);
		controles = new Boton("Controles",300,460);
		add(controles);
		salir = new Boton("salir", 300, 515);
		add(salir);

		
		 
		repaint();
	}
	public void prepareElementosControl() {
		removeAll();
		imagencontrol = new Boton("instrucciones",170,340);
		add(imagencontrol);
		goBack = new Boton("abandonar",270,550);
		add(goBack);
		repaint();
	}
	
	
	public void prepareElementos2J(){
		removeAll();
		JvsJ = new Boton("JvsJ", 340, 300);
		add(JvsJ);
		
		JvsM = new Boton("JvsM", 340, 360);
		add(JvsM);
		JvsM2 = new Boton("JvsM2",340,420);
		add(JvsM2);
		
		goBack = new Boton("atras", 320, 540);
		add(goBack);
		repaint();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.drawImage(fondo, 0, 0, this);
    }
}
