package presentacion;
import aplicacion.*;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ReplicateGUI extends JFrame implements Runnable, KeyListener{
	
	//Menu
	private JFileChooser file;
	private JPanel principal;
	private CardLayout layout;
	private tablero tableroInicio;
	private BoardJuego tableroJuego;
	private Clip c;
	private DonkeyPoob juego;
	private int p=0;
	private Thread t;
	
	private boolean j1Up, j1Down, j1Right, j1Left, j2Up;
	private boolean invertir=false;
	private ReplicateGUI(){
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
	public void open(){
		file = new JFileChooser();
		int selec= file.showOpenDialog(null);
		if(selec==JFileChooser.APPROVE_OPTION){
			File selectedFile = file.getSelectedFile();
			JOptionPane.showMessageDialog(this,"en construccion"+selectedFile);
		}
	}
	public void GuardarComo(){
		JFileChooser file = new JFileChooser();
		file.showSaveDialog(this);
		JOptionPane.showMessageDialog(null,"en construccion");
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
					prepareElementosJuego(1);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
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
	public void cerrar(){
		int res = JOptionPane.showConfirmDialog(null,"Desea salir", "Atencion!", JOptionPane.YES_NO_OPTION);
		if( res == JOptionPane.OK_OPTION){
			this.setVisible(false);
			this.dispose();
		
	}
	}
	public static void main(String[] args) {
		ReplicateGUI gui = new ReplicateGUI();
		gui.setVisible(true);
		
	}
	 public void prepararElementosTablero() {
		 	layout = new CardLayout();	       
			principal = new JPanel(layout);
			tableroInicio = new tablero(tablero.fondoInit);
			add(principal);
			principal.add(tableroInicio, "tini");
			layout.show(principal, "tini");
			sonidoIntro();
	 }
	 private void prepareElementosJuego(int players) throws IOException{
			tableroJuego = new BoardJuego(players);
			juego=new DonkeyPoob();
			prepareElementosJuego();
			
			t = new Thread(this);
			principal.add(tableroJuego, "tjue");
			layout.show(principal, "tjue");
			setSize(new Dimension(740,620));
			t.start();
			
			
		}
	 private void prepareJugadores(){

				actualizarJugadores();
			
		}
		
	 private void sonidoIntro(){
			try{
				InputStream is = ReplicateGUI.class.getResourceAsStream("/sonidos/title.wav");
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
					juego.JugadorNUp(0); 
					juego.JugadorNDown(0);
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
			
				tableroJuego.setScore(0, juego.puntajes()[0]);
				tableroJuego.setScore(1, juego.puntajes()[1]);
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
			if(keyCode==KeyEvent.VK_SPACE && !juego.jumping  && !juego.Escalando && !juego.enAire) j2Up=true;
			
			if(juego.invertir && j1Down ) {
				 juego.JugadorNEscalar(0);
			}
			
			if(j1Up) {if(!juego.invertir) {juego.JugadorNEscalar(0);}}
			if(j1Right) juego.JugadorNRight(0);
			if(j1Left) juego.JugadorNLeft(0);
			if(j2Up) {juego.jummping(0,50);
			j2Up=false;}
			actualizarJugadores();
			
			}
		private void actualizarJugadores() {
			for(int i = 0; i < 1; i++){
				Sprite s;
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
			juego.Jugadornormal(0);
				
			
		}
		public void keyTyped(KeyEvent e) {}
		
	private void actualizarElementos() {
		
			for(int i = 0; i < juego.numElementos(); i++){
				if(!juego.getElemento(i).isVisible()) {
					System.out.println("entreeeeeeeeeeeeqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
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
						System.out.println("entreeeeeeeeeeeeqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
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
