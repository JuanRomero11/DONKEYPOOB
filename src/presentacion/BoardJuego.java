package presentacion;
import java.util.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BoardJuego extends JPanel{
	private BufferedImage fondo;
	private Sprite plataforms;
	private Sprite donkey;
	private Graphics2D k;
	private boolean showAway;
	private ArrayList<Sprite> barriles;
	private ArrayList<Sprite> jugadores;
	public BoardJuego(int n) throws IOException {
		plataforms=new Sprite(0,0,true,746,600);
		plataforms.setRoot("fondoJuego2");
		
		
		Sprite BarrilUno=new Sprite(0,211,true,20,20);
		BarrilUno.setRoot("barril");
		barriles =new ArrayList<Sprite>();
		barriles.add(BarrilUno);
		
		donkey=new Sprite(0,117,true,60,74);
		donkey.setRoot("DonkeyIzquierda");
		
		Sprite Mario=new Sprite(2,550,true,20,20);
		Mario.setRoot("MarioDerecha");
		jugadores =new ArrayList<Sprite>();
		jugadores.add(Mario);
		
		
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
        plataforms.paint((Graphics2D) g);
        donkey.paint((Graphics2D) g);
        for(Sprite s:barriles) {
        	s.paint((Graphics2D) g);
        }
        for(Sprite k:jugadores) {
        	k.paint((Graphics2D) g);
        }
        
    }
	 public void showedGame(){
			//showedGame = true;
			//showedDog = false;
			showAway = false;
		}
	 public Sprite getSprite(int i) {
			return barriles.get(i);
		}
	 public Sprite getJugador(int i) {
			return jugadores.get(i);
		}
	 public Sprite getSpriteDonkey() {
			return donkey;
		}
	 public void addSprite() {
			barriles.add(new Sprite(0, 0, false));
		}
	 public int numeroBarriles() {
		 return barriles.size();
	 }
	 public void addSprite(int x,int y,String root) {
		 Sprite n=new Sprite(0,117,true,20,20);
		n.setRoot(root);
			barriles.add(n);
		}
}
