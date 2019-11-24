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

public class ReplicateGUI extends JFrame implements Runnable{
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
	
	private ReplicateGUI(){
		super("Donkey Poob");
		prepareElementos();
		prepareAcciones();
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
					// TODO Auto-generated catch block
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
			t = new Thread(this);
			principal.add(tableroJuego, "tjue");
			layout.show(principal, "tjue");
			setSize(new Dimension(740,620));
			t.start();
			
			
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

			
				while(juego.isFinished()) {
			
					actualizarDonkey();
					juego.mover(0);
					actualizarBarriles();
					t.sleep(7);
					
					
				
				}
				
				End();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
			
		}
	 private void actualizarBarriles(){
			for(int i = 0; i < 1; i++){
				Sprite s;
				try {
					s = tableroJuego.getSprite(i);
				} catch (IndexOutOfBoundsException ex) {
					tableroJuego.addSprite();
					s = tableroJuego.getSprite(i);
				}
					s.setX(juego.getBarril(i).getX());
					s.setY(juego.getBarril(i).getY());
					s.setRoot(juego.getBarril(i).getRoot());
					//System.out.println(juego.getBarril(i).getRoot()+" "+juego.getBarril(i).getX()+" "+juego.getBarril(i).getY());

					s.setVisible(true);
			}
			tableroJuego.repaint();
		}
	 public void actualizar() {
		 actualizarBarriles();
		 actualizarDonkey();
	 }
	 public void actualizarDonkey() {
		if(tableroJuego.getSpriteDonkey().getRoot().equals("resources/DonkeyDerecha.png")) {
			tableroJuego.getSpriteDonkey().setRoot("DonkeyIzquierda");
		}else {
			tableroJuego.getSpriteDonkey().setRoot("DonkeyDerecha");
		}
		tableroJuego.repaint();
	 }
	 private void End(){
	 }
	 
	
	
}
