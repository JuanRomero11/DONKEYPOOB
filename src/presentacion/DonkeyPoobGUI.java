package presentacion;
import aplicacion.*;


import java.awt.*;
import java.awt.event.*;

import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DonkeyPoobGUI extends JFrame implements Runnable, KeyListener{
	
	//Menu
	private JFileChooser file;
	private JPanel principal;
	private CardLayout layout;
	private Tablero tableroInicio;
	private BoardJuego tableroJuego;
	private Clip c;
	public static DonkeyPoob juego;
	
	private Thread t;
	
	private boolean j1Up, j1Down, j1Right, j1Left, j2Up;
	private boolean  j3Up, j2Down, j2Right, j2Left,j4Up;
	private boolean[] elementos=new boolean[]{false,false,false,false,false,false};
	private boolean[] barriles=new boolean[]{false,false,false,false};
	private boolean[] aspectoMario=new boolean[]{false,false};

	private DonkeyPoobGUI(){
		super("Donkey Poob");
		prepareElementos();
		prepareAcciones();
		addKeyListener(this);
		setFocusable(true);
	}
	
	
	
	private void prepareElementos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(900, 680));
		setResizable(false);
		setLocationRelativeTo(null);
		prepararElementosTablero();
	}
	public void refresque(){
        this.revalidate();
    }

	public void prepareAcciones() {
		tableroInicio.salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrar();
			}
		});
		tableroInicio.controles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableroInicio.prepareElementosControl();
				prepareAccionesControl();
			}
		});
		
		tableroInicio.jugador1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tableroInicio.prepareElementosSeleccion();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				prepareAccionesSeleccionar(1);
			}
		});
		tableroInicio.jugador2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tableroInicio.prepareElementos2J();
					prepareAccionesJ2(); 
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
	public void prepareAccionesJ2() {
		tableroInicio.JvsJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tableroInicio.prepareElementosSeleccion();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					prepareAccionesSeleccionar(2);
				
				
			}
		});
	}
	public void prepareAccionesControl() {
		tableroInicio.goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableroInicio.prepareElementosInicio();
				prepareAcciones();
			}
		});
	}
	public void prepareAccionesSeleccionar(int players) {
		tableroInicio.start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					prepareElementosJuego(players);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		tableroInicio.goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableroInicio.fondo=tableroInicio.fondoInit;
				tableroInicio.prepareElementosInicio();
				prepareAcciones();
			}
		});
		tableroInicio.hongo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(elementos[0]==false) 
				{elementos[0]=true;}else {elementos[0]=false;}
			}
		});
		tableroInicio.soga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(elementos[1]==false) 
				{elementos[1]=true;}else {elementos[1]=false;}
			}
		});
		tableroInicio.martillo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(elementos[2]==false) 
				{elementos[2]=true;}else {elementos[2]=false;}
			}
		});
		tableroInicio.cereza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(elementos[3]==false) 
				{elementos[3]=true;}else {elementos[3]=false;}
			}
		});
		tableroInicio.corazon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(elementos[4]==false) 
				{elementos[4]=true;}else {elementos[4]=false;}
			}
		});
		tableroInicio.manzana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(elementos[5]==false) 
				{elementos[5]=true;}else {elementos[5]=false;}
				
			}
		});
		tableroInicio.marioRojo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aspectoMario[0]=true;
				aspectoMario[1]=false;
			}
		});
		tableroInicio.marioVerde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aspectoMario[0]=false;
				aspectoMario[1]=true;
			}
		});
		tableroInicio.barrilAzul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(barriles[0]==false) 
				{barriles[0]=true;}else {barriles[0]=false;}
			}
		});
		tableroInicio.barrilAmarillo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(barriles[1]==false) 
				{barriles[1]=true;}else {barriles[1]=false;}
			}
		});
		tableroInicio.barrilVerde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(barriles[2]==false) 
				{barriles[2]=true;}else {barriles[2]=false;}
			}
		});
		tableroInicio.barrilRojo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(barriles[3]==false) 
				{barriles[3]=true;}else {barriles[3]=false;}
			}
		});
	
		
	}
	public void cerrar(){
		int res = JOptionPane.showConfirmDialog(null,"Desea salir", "Atencion!", JOptionPane.YES_NO_OPTION);
		if( res == JOptionPane.OK_OPTION){
			this.setVisible(false);
			this.dispose();
		
	}
	}
	public static void main(String[] args) {
		DonkeyPoobGUI gui = new DonkeyPoobGUI();
		gui.setVisible(true);
		
	}
	 public void prepararElementosTablero() {
		 	layout = new CardLayout();	       
			principal = new JPanel(layout);
			tableroInicio = new Tablero(Tablero.root);
			add(principal);
			principal.add(tableroInicio, "tini");
			layout.show(principal, "tini");
			sonidoIntro();
	 }
	 private void prepareElementosJuego(int players) throws IOException{
			tableroJuego = new BoardJuego(players);
			juego=new DonkeyPoob(tableroJuego.k1,barriles,elementos,aspectoMario,players);
			prepareElementosJuego();
			
			t = new Thread(this);
			principal.add(tableroJuego, "tjue");
			layout.show(principal, "tjue");
			setSize(new Dimension(740,620));
			t.start();
			
			
		}
	
	 private void sonidoIntro(){
			try{
				InputStream is = DonkeyPoobGUI.class.getResourceAsStream("/sonidos/title.wav");
				AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
				DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
				c = (Clip) AudioSystem.getLine(info);
				c.open(ais);
				c.start();
				c.loop(Clip.LOOP_CONTINUOUSLY);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	 public void run(){
		try {	
				while(!juego.isFinished()) {
					actualizarElementos();
					actualizarDonkey();
					actualizarScore();
					moverBarriles();
					actualizarPrincesa();
					if(juego.Jugadores.size()>=2) {
						juego.JugadorNUp(0); 
						juego.JugadorNDown(0);
						t.sleep(10);
						juego.JugadorNUp(1); 
						juego.JugadorNDown(1);
					}else {
						juego.JugadorNUp(0); 
						juego.JugadorNDown(0);
					}
					
					actualizarBarriles();
					actualizarJugadores();
					t.sleep(7);
				}
				
				End();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	 private void actualizarScore() {
			if(juego.puntajes().length>=2) {
				tableroJuego.setScore(0, juego.puntajes()[0]);
				tableroJuego.setScore(1, juego.puntajes()[1]);
				tableroJuego.setScore(2, juego.puntajes()[2]);
				tableroJuego.setScore(3, juego.puntajes()[3]);
			}else {
				tableroJuego.setScore(0, juego.puntajes()[0]);
				tableroJuego.setScore(1, juego.puntajes()[1]);
			}
			tableroJuego.repaint();
		}
	 private void moverBarriles() {
		
		for(int i=0;i<juego.barriles.size();i++) {
			juego.mover(i); 
		}
		
	}
	public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			
			if(keyCode == KeyEvent.VK_UP)j1Up = true;
			if(keyCode == KeyEvent.VK_DOWN) j1Down = true;
			if(keyCode == KeyEvent.VK_RIGHT) j1Right = true;
			if(keyCode == KeyEvent.VK_LEFT) j1Left = true;
			if(keyCode==KeyEvent.VK_SPACE && !juego.Jugadores.get(0).jumping  && !juego.Jugadores.get(0).Escalando && !juego.Jugadores.get(0).enAire) j2Up=true;
			
			if(juego.invertir && j1Down ) {
				 juego.JugadorNEscalar(0);
			}
			
			if(j1Up) {if(!juego.invertir) {juego.JugadorNEscalar(0);}}
			if(j1Right) juego.JugadorNRight(0);
			if(j1Left) juego.JugadorNLeft(0);
			if(j2Up) {juego.jummping(0,50);
			j2Up=false;}
			if(juego.Jugadores.size()>=2) {
				if(keyCode == KeyEvent.VK_W) j3Up = true;
				if(keyCode == KeyEvent.VK_S) j2Down = true;
				if(keyCode == KeyEvent.VK_D) j2Right = true;
				if(keyCode == KeyEvent.VK_A) j2Left = true;
				if(keyCode == KeyEvent.VK_P  && !juego.Jugadores.get(1).jumping  && !juego.Jugadores.get(1).Escalando && !juego.Jugadores.get(1).enAire) j4Up=true;
				if(juego.invertir && j2Down ) {
					 juego.JugadorNEscalar(1);
				}
				
				if(j3Up) {if(!juego.invertir) {juego.JugadorNEscalar(1);}}
				if(j2Right) juego.JugadorNRight(1);
				if(j2Left) juego.JugadorNLeft(1);
				if(j4Up) {juego.jummping(1,50);
				j4Up=false;}
			}
			actualizarJugadores();
			
			}
		private void actualizarJugadores() {
			for(int i = 0; i < DonkeyPoob.Jugadores.size(); i++){
				Sprite s=null;
					s = tableroJuego.getJugador(i);	
					s.setX(juego.getJugador(i).getX());
					s.setY(juego.getJugador(i).getY());
					s.setRoot(juego.getJugador(i).getRoot());
					//System.out.println(juego.getBarril(i).getRoot()+" "+juego.getBarril(i).getX()+" "+juego.getBarril(i).getY());
					s.setVisible(true);
			}
			tableroJuego.repaint();		
		}
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
			
			if(keyCode == KeyEvent.VK_UP) j1Up = false;
			if(keyCode == KeyEvent.VK_DOWN) j1Down = false;
			if(keyCode == KeyEvent.VK_RIGHT) j1Right = false;
			if(keyCode == KeyEvent.VK_LEFT) j1Left = false;
			if(keyCode==KeyEvent.VK_SPACE ) j2Up=false;
			
			if(keyCode == KeyEvent.VK_W) j3Up = false;
			if(keyCode == KeyEvent.VK_S) j2Down = false;
			if(keyCode == KeyEvent.VK_D) j2Right = false;
			if(keyCode == KeyEvent.VK_A) j2Left = false;
			if(keyCode == KeyEvent.VK_P) j4Up=false;
			
				
			
		}
		public void keyTyped(KeyEvent e) {}
		
	private void actualizarElementos() {
		
			for(int i = 0; i < juego.numElementos(); i++){
				if(!juego.getElemento(i).isVisible()) {
					
					tableroJuego.deleteElemento(i);
				}
			}
			
			tableroJuego.repaint();
			
			
		}
		
	private void prepareElementosJuego() {
		
		for(int i=0;i<juego.numElementos();i++) {
			if(juego.getElemento(i).isVisible()) {
				tableroJuego.addElemento(juego.getElemento(i).getX(),juego.getElemento(i).getY(),juego.getElemento(i).getRoot());
			}
		}
		tableroJuego.addDonkey(juego.getDonkey().getX(),juego.getDonkey().getY(),juego.getDonkey().getRoot());
		tableroJuego.addPrincesa(juego.getPrincesa().getX(),juego.getPrincesa().getY(),juego.getPrincesa().getRoot());

		tableroJuego.repaint();
	}

	 private void actualizarBarriles(){
		 
		 if(!juego.termino) {
			 int x =juego.sizeBarriles();
			 juego.prepareBarriles();
			 int y=juego.sizeBarriles();
			 Sprite g;
			 if(x<y ) {
				tableroJuego.addSprite(0,171,juego.getBarril(y-1).getRoot());}
		 }
			for(int i = 0; i < tableroJuego.numeroBarriles(); i++){
					
					if(!juego.getBarril(i).isVisible()) {
						
						tableroJuego.deleteBarril(i);
					}else {
						Sprite s;
						try {
							s = tableroJuego.getSprite(i);
						} catch (IndexOutOfBoundsException ex) {
							tableroJuego.addSprite();
							s = tableroJuego.getSprite(i);
						}
						tableroJuego.repaint();
						s.setX(juego.getBarril(i).getX());
						s.setY(juego.getBarril(i).getY());
						s.setRoot(juego.getBarril(i).getRoot());
						s.setVisible(true);
						//System.out.println(juego.getBarril(i).getRoot()+" "+juego.getBarril(i).getX()+" "+juego.getBarril(i).getY());
						
					}
			}
			
			tableroJuego.repaint();
		}
	 
	 public void actualizar() {
		 actualizarBarriles();
		 actualizarDonkey();
		 actualizarPrincesa();
		 
	 }
	 public void actualizarDonkey() {
		 
				Sprite s;
					s = tableroJuego.getDonkey();	
					s.setX(juego.getDonkey().getX());
					s.setY(juego.getDonkey().getY());
					s.setRoot(juego.getDonkey().getRoot());
					//System.out.println(juego.getBarril(i).getRoot()+" "+juego.getBarril(i).getX()+" "+juego.getBarril(i).getY());
					s.setVisible(true);
				
			
				
		tableroJuego.repaint();
	 }public void actualizarPrincesa() {
		 Sprite princesa;
			princesa= tableroJuego.getPrincesa();	
			princesa.setX(juego.getPrincesa().getX());
			princesa.setY(juego.getPrincesa().getY());
			princesa.setRoot(juego.getPrincesa().getRoot());
				//System.out.println(juego.getBarril(i).getRoot()+" "+juego.getBarril(i).getX()+" "+juego.getBarril(i).getY());
			princesa.setVisible(true);
			tableroJuego.repaint();
	 }
	 public void prepareReinicio(){
			tableroJuego.reiniciar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					reiniciar();
				}
			});
			
		}
	 private void End(){
		 tableroJuego.End(juego.Gano());
		 prepareReinicio();
	 }
	 private void reiniciar(){
		 c.stop();
			sonidoIntro();
			tableroInicio.prepareElementosInicio();
			prepareAcciones();
			layout.show(principal, "tini");
			setSize(new Dimension(740,620));
			tableroInicio.repaint();
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
	 }
	
}
