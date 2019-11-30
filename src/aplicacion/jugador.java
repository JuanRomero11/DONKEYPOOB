package aplicacion;

import java.io.Serializable;
public class jugador{

	protected int x,xInicial;
	protected int y,yInicial;
	protected int dx;
	protected int dy;
	protected static String root;
	protected boolean visible;
	protected static final int VELOCIDAD = 30;
	private static final int LIMIT_Y = 650;
	private static final int LIMIT_X = 900;
	private int limiteY;
	public static int vidas;
	public static int score;
	public int gano=0;
	public boolean saltando=false;
	public boolean falling=true;
	public boolean movimiento=true;
	public int pisoy;
	public jugador(int x, int y, String root) {
		this.x = x;
		this.y = y;
		this.xInicial = x;
		this.yInicial = y;
		this.dy=y;
		this.limiteY=y;
		this.pisoy=y;
		this.root=root;
		this.vidas=3;
		this.score=0;
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
				x+=5;
				movimiento=false;
			}else {
				setRoot("marioCorriendoDer2");
				x+=5;
				movimiento=true;
			}
		}
		public void moveLeft(){
			if(movimiento) {
				setRoot("marioCorriendoIzq");
				x-=5;
				movimiento=false;
			}else {
				setRoot("marioCorriendoIzq2");
				x-=5;
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

	public static void setRoot(String r) {
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
	public String gano() {
		String s=null;
		if(gano==0) {
			s="perdio";
		}else if(gano==1) {
			s="gano";
		}
		return s;
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
	public int score(int x) {
		int r=0;
		if(x==0) {
			r=vidas;
		}else {
			r=score;
		}
		return r;
	}
	public static  void sumeVida(int i) {
		vidas+=i;
		
	}
	public static  void sumeScore(int i) {
		score+=i;
		
	}
	public void inicial() {
		this.x=xInicial;
		this.y=yInicial;
		
		
	}
	

}