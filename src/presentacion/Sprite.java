package presentacion;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class Sprite {

	private BufferedImage imagen;
	private String rut;
	private int x,y,width, height;
	private boolean visible;

	public Sprite(int x, int y, boolean visible) {
		this.x = x;
		this.y = y;
		this.visible = visible;
	}
	
	public Sprite(int x, int y, boolean visible, int width, int height){
		this(x,y,visible);
		this.width = width;
		this.height = height;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setVisible(boolean v) {
		this.visible = v;
	}

	public void setRoot(String root) {
		try {
			rut="resources/"+root+".png";
			imagen = ImageIO.read(new File("resources/"+root+".png"));
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getRoot() {
		return rut;
	}

	public void paint(Graphics graphics) {
		if (visible)
			if(width==0 && height == 0)
				graphics.drawImage(imagen, x, y, null);
			else
				graphics.drawImage(imagen, x, y, width, height, null);
	}

}

