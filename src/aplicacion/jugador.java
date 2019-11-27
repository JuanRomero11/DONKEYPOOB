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
	public boolean falling=true;
	public boolean movimiento=true;
	public int pisoy;
	public jugador(int x, int y, String root) {
		this.x = x;
		this.y = y;
		this.dy=y;
		this.limiteY=y;
		this.pisoy=y;
		this.root=root;
		visible = true;
	}
	//Metodos de movimiento
		public void moveUp(){
			if(getRoot().equals("MarioCorriendoDerecha") ||getRoot().equals("MarioDerecha")) {
				setRoot("MarioCorriendoDerecha2");
			}else if (getRoot().equals("Mario") || getRoot().equals("MarioCaminando")) {
				setRoot("MarioCaminando2");
			}
			
			
				y-=1;
			
			

		}
		
		public void moveDown(){
			if(getRoot().equals("MarioCorriendoDerecha") ||getRoot().equals("MarioDerecha")) {
				setRoot("marioSaltandoIzq");
			}else if (getRoot().equals("Mario") || getRoot().equals("MarioCaminando")) {
				setRoot("marioSaltandoDer");
			}
			
			y+=1;
		}
		
		public void moveRight(){
			if(movimiento) {
				setRoot("marioCorriendoDer");
				x+=3;
				movimiento=false;
			}else {
				setRoot("marioCorriendoDer2");
				x+=3;
				movimiento=true;
			}
		}
		public void moveLeft(){
			if(movimiento) {
				setRoot("marioCorriendoIzq");
				x-=3;
				movimiento=false;
			}else {
				setRoot("marioCorriendoIzq2");
				x-=3;
				movimiento=true;
			}
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
	public void jummping(int gravity) {
		pisoy=y;
		gravity-=1;
		saltando=true;
		//limiteY-=45;
		if(gravity<=0.0) {
			saltando=false;
			falling=true;
			limiteY-=45;
			
		}
		dy=y;
		
		if(falling) {
			gravity+=1;
			limiteY-=45;
			
		}
	}
	public boolean validarSalto() {
		boolean bandera=true;
		if(y<=limiteY) {
			bandera=false;
			limiteY=dy;
		}else if(y>dy) {
			bandera=false;
			limiteY=y;
			dy=y;
		}
		
		return bandera;
		
	}
	public void setSubir() {
		if(!root.equals("marioSubiendoEscalera")) {
			setRoot("marioSubiendoEscalera");
		}else if(root.equals("marioSubiendoEscalera")){
			setRoot("marioSubiendoEscalera2");
		}
		// TODO Auto-generated method stub
		
	}
	

}