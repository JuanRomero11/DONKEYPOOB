package aplicacion;

import java.io.Serializable;
public class jugador{

	protected int x;
	protected int y;
	protected int dx;
	protected int dy;
	protected String root;
	protected boolean visible;
	protected static final int VELOCIDAD = 30;
	private static final int LIMIT_Y = 650;
	private static final int LIMIT_X = 900;
	private int limiteY;
	public boolean saltando=false;
	public jugador(int x, int y, String root) {
		this.x = x;
		this.y = y;
		this.dy=y;
		this.limiteY=y;
		this.root=root;
		visible = true;
	}
	//Metodos de movimiento
		public void moveUp(){
			if(getRoot().equals("MarioCorriendoDerecha") ||getRoot().equals("MarioDerecha")) {
				setRoot("MarioCorriendoDerecha2");
			}else if (getRoot().equals("Mario") || getRoot().equals("MarioCaminando")) {}
			setRoot("MarioCaminando2");
				y-=2;

		}
		
		public void moveDown(){
			if(getRoot().equals("MarioCorriendoDerecha") ||getRoot().equals("MarioDerecha")) {
				setRoot("MarioCorriendoDerecha2");
			}else if (getRoot().equals("Mario") || getRoot().equals("MarioCaminando")) {}
			setRoot("MarioCaminando2");
				y+=2;
		}
		
		public void moveRight(){
			setRoot("MarioCorriendoDerecha");
				x+=5;
		}
		public void moveLeft(){
			setRoot("MarioCaminando");
				x-=5;
		}
	

	public String getRoot() {
		return root;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setRoot(String r) {
		root = r;
	}
	
	public void setVisible(boolean v){
		visible = v;
	}
	public void moveNormal() {
		if(root.equals("MarioCorriendoDerecha")) {
			setRoot("MarioDerecha");
		}else if(root.equals("MarioCaminando")){
			setRoot("Mario");
		}
		
		
	}
	public void jummping() {
		saltando=true;
		limiteY-=45;
		dy=y;
	}
	public boolean validarSalto() {
		boolean bandera=true;
		if(y<=limiteY) {
			bandera=false;
			limiteY=dy;
		}
		return bandera;
		
	}
	

}