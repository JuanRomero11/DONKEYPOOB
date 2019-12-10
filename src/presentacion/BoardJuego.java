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
	private Sprite princesa;
	private Graphics2D k;
	private boolean showAway;
	public ArrayList<Sprite> barriles;
	private ArrayList<Sprite> jugadores;
	private ArrayList<Sprite> elementos;
	private ArrayList<String> scores;
	protected Boton reiniciar,guardar,abrir;
	public int k1;
	public BoardJuego(int n) throws IOException {
		int k2=(int) (Math.random()*3);
		plataforms=new Sprite(0,0,true,746,600);
		if(k2==1) {
			//plataforms=new Sprite(0,0,true,746,600);
			if(n>=2) {
				plataforms.setRoot("fondoJuego12");
			}else {
				plataforms.setRoot("fondoJuego1");
			}
			
		}else if(k2==0) {
			//plataforms=new Sprite(0,0,true,746,600);
			if(n>=2) {
			plataforms.setRoot("fondoJuego02");
			}else {
				plataforms.setRoot("fondoJuego0");
			}
		}else if(k2==2) {
			if(n>=2) {
				//plataforms=new Sprite(0,0,true,746,600);
				plataforms.setRoot("fondoJuego22");
			}else {
				plataforms.setRoot("fondoJuego2");
			}
		}
		if(n==2) {
			Sprite Mario=new Sprite(2,550,true,20,20);
			Mario.setRoot("MarioDerecha");
			Sprite luigi=new Sprite(2,550,true,20,20);
			luigi.setRoot("MarioDerecha");
			jugadores =new ArrayList<Sprite>();
			jugadores.add(Mario);
			jugadores.add(luigi);
			this.k1=k2;
		}else {
			Sprite Mario=new Sprite(2,550,true,20,20);
			Mario.setRoot("MarioDerecha");
			
			jugadores =new ArrayList<Sprite>();
			jugadores.add(Mario);
			
			this.k1=k2;
		}
		scores = new ArrayList<String>();
		addScore();
		elementos= new ArrayList<Sprite> ();
		Sprite BarrilUno=new Sprite(0,211,true,20,20);
		BarrilUno.setRoot("barril");
		barriles =new ArrayList<Sprite>();
		barriles.add(BarrilUno);
		
		
	}
	public void End(String boton){
		if(boton.equals("gano")) {
			reiniciar = new Boton("winner", 10, 70);  add(reiniciar);
			
			repaint();
		}else {
			
			reiniciar = new Boton("restart", 10, 70);  add(reiniciar);
			repaint();
		}
	}
	public void addScore(){
		if(jugadores.size()>=2) {
			scores.add("1");
			scores.add("0");
			scores.add("1");
			scores.add("0");
		}else {
			scores.add("1");
			scores.add("0");
		}
		
	}
	public void setScore(int n,int i) {
		scores.set(n, Integer.toString(i));
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
        plataforms.paint((Graphics2D) g);
        donkey.paint((Graphics2D) g);
        princesa.paint((Graphics2D) g);
        if(jugadores.size()==1) {
        	 g.setFont(new Font("Tahoma", Font.BOLD, 20));
             g.setColor(Color.RED);
             
             g.drawString(scores.get(0),420 ,40);
             g.setColor(Color.white);
             g.drawString(scores.get(1), 650, 55);
        }else {
        	g.setFont(new Font("Tahoma", Font.BOLD, 20));
            g.setColor(Color.RED);
            
            g.drawString(scores.get(0),453 ,35);
            g.setColor(Color.white);
            g.drawString(scores.get(1), 598,45);
            g.setColor(Color.RED);
            g.drawString(scores.get(0),458 ,85);
            g.setColor(Color.white);
            g.drawString(scores.get(1), 598, 93);
        }
       
      
        for(Sprite s:barriles) {
        	s.paint((Graphics2D) g);
        }
        for(Sprite k:jugadores) {
        	k.paint((Graphics2D) g);
        }
        for(Sprite k:elementos) {
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
	 public Sprite getElemento(int i) {
			return elementos.get(i);
		}
	 public Sprite getSpriteDonkey() {
			return donkey;
		}
	 public void addSprite() {
			barriles.add(new Sprite(0, 0, false));
		}
	 public void addDonkey(int x,int y,String root) {
		 donkey=new Sprite(x,y,true,60,60);
		donkey.setRoot("donkeyIzquierda");
		}
	 public void addPrincesa(int x,int y,String root) {
		 princesa=new Sprite(x,y,true,20,20);
		princesa.setRoot("princesa");
		}
	 public int numeroBarriles() {
		 return barriles.size();
	 }
	 
	 public void addSprite(int x,int y,String root) {
		 Sprite n=new Sprite(0,117,true,20,20);
		n.setRoot(root);
			barriles.add(n);
		}
	 public void addElemento(int x,int y,String root) {
		Sprite n=new Sprite(x,y,true,20,20);
		n.setRoot(root);
		elementos.add(n);
	 }
	 public int numElementos() {
		 return elementos.size();
	 }
	 public void deleteElemento(int x) {
			elementos.remove(x);
			
	 }
	 public void deleteBarril(int x) {
			barriles.remove(x);
			
	 }
	public Sprite getDonkey() {
		
		return donkey;
	}
	public Sprite getPrincesa() {
		// TODO Auto-generated method stub
		return princesa;
	}
	
}
