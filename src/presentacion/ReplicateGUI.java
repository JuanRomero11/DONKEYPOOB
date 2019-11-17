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

public class ReplicateGUI extends JFrame implements Runnable{
	//Menu
	private JFileChooser file;
	private JPanel principal;
	private CardLayout layout;
	private tablero tableroInicio;
	private BoardJuego tableroJuego;
	private Clip c;
	private DonkeyPoob juego;
	
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
			t = new Thread(this);
			principal.add(tableroJuego, "tjue");
			layout.show(principal, "tjue");
			setSize(new Dimension(767, 645));
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
				while(!juego.isFinished()) {
					juego.ronda();
					tableroJuego.showedGame();
					actualizar();
					Thread.sleep(100);
					while (!juego.isRondaFinished()) {
						if(!juego.enPausa()){
							juego.mover(18);
							actualizar();
						}
						Thread.sleep(20);
					}
				}
				End();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	 private void actualizarPatos(){
			for (int i = 0; i < 2; i++) {
				Sprite s;
				try {
					s = tableroJuego.getSprite(i);
				} catch (IndexOutOfBoundsException ex) {
					tableroJuego.addSprite();
					s = tableroJuego.getSprite(i);
				}
				if (juego.getBarril(i).isVisible()) {
					s.setX(juego.getBarril(i).getX());
					s.setY(juego.getBarril(i).getY());
					s.setRoot(juego.getBarril(i).getRoot());
				} 
				s.setVisible(juego.getBarril(i).isVisible());
			}
		}
	 public void actualizar() {
		 actualizarPatos();
	 }
	 private void End(){
	 }
	 
	
	
}
