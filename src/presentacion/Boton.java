package presentacion;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
public class Boton extends JButton {
	
	private BufferedImage imagen;
	private int reemplazo=0;
	private int reemplazo2=0;
	public Boton(String root, int x, int y){
		super();
		setRequestFocusEnabled(false);
		try{
			imagen = ImageIO.read(new File("resources/"+root+".png"));
		}catch(IOException e){
			e.printStackTrace();
		}
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBounds(x, y, imagen.getWidth(), imagen.getHeight());
	}
	public Boton(String root, int x, int y,int reemplazo,int reemplazo2){
		super();
		this.reemplazo=reemplazo;
		this.reemplazo2=reemplazo2;
		setRequestFocusEnabled(false);
		try{
			imagen = ImageIO.read(new File("resources/"+root+".png"));
		}catch(IOException e){
			e.printStackTrace();
		}
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBounds(x, y, reemplazo, reemplazo2);
	}
	public void paint(Graphics g){
		if(reemplazo!=0 && reemplazo2!=0) {
			g.drawImage(imagen, 0, 0,reemplazo,reemplazo2, null);
		}else {
			g.drawImage(imagen, 0, 0, null);
		}
	}
}