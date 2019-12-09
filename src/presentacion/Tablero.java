package presentacion;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Tablero extends JPanel{
	public BufferedImage fondoInit;
	public static String root= "resources/fondo2.png";
	public BufferedImage fondo;
	protected JButton jugador1, jugador2 ,abrir,controles, salir, JvsD, JvsJ, JvsM, JvsM2 ,playM, playK, goBack,imagencontrol;
	protected JButton start,back;
	protected JButton hongo,soga,martillo,cereza,manzana,corazon,marioRojo,marioVerde,barrilAzul,barrilRojo,barrilAmarillo,barrilVerde;
	public Tablero(String root){
		super(null);
		prepareElementosInicio();
		
		try{
			fondoInit = ImageIO.read(new File("resources/fondo2.png"));
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
	public void prepareElementosSeleccion() throws IOException {
		removeAll();
		fondo=ImageIO.read(new File("resources/seleccione.png"));
		hongo = new Boton("hongo",284,120,40,40);
		add(hongo);
		
		soga = new Boton("soga",334,120);
		add(soga);
		
		martillo = new Boton("martillo1",384,120,40,40);
		add(martillo);
		
		cereza = new Boton("cereza",434,120,40,40);
		add(cereza);
		
		corazon = new Boton("corazon",484,120,40,40);
		add(corazon);
		
		manzana = new Boton("manzana",534,120,40,40);
		add(manzana);
		
		marioRojo = new Boton("MarioCorriendoDerecha", 381, 368,40,40);
		add(marioRojo);
		
		marioVerde = new Boton("MarioCorriendoDerechaVerde", 441,368,40,40);
		add(marioVerde);
		
		barrilAzul= new Boton("barrilAzul20", 334, 200,40,40);
		add(barrilAzul);
		
		barrilRojo= new Boton("barrilRojo", 384, 200,40,40);
		add(barrilRojo); 
		
		barrilVerde= new Boton("barrilVerdeee", 434, 200,40,40);
		add(barrilVerde);
		
		barrilAmarillo= new Boton("barril", 484, 200,40,40);
		add(barrilAmarillo);
		
		start =new Boton("Start",480,480,200,80);
		add(start);
		
		goBack = new Boton("back",210,480,200,80);
		add(goBack);
		
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
