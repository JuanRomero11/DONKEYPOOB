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

public class ReplicateGUI extends JFrame{
	//Menu
	
	private JFileChooser file;
	private JPanel principal;
	private CardLayout layout;
	private tablero tableroInicio;
	private Clip c;
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
	
	
}
