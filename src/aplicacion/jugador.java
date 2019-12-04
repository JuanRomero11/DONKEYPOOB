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
	private int xPos;
    private int yPos;
    private final long segundo = 1000000000;
    private int MvSec;
    private long time;
	public boolean saltando=false;
	public boolean falling=true;
	public boolean movimiento=true;
	public int pisoy;
	public int mr=0;
	public int mi=0;
	public jugador(int x, int y, String root) {
		this.x = x;
		this.y = y;
		xPos=x;
		yPos=y;
		this.xInicial = x;
		this.yInicial = y;
		this.dy=y;
		this.limiteY=y;
		this.pisoy=y;
		this.root=root;
		this.vidas=3;
		this.score=0;
		visible = true;
		time = System.nanoTime();
		this.MvSec = MvSec;
	}
	
	//Metodos de movimiento
		
		/*
		 * Realiza el movimiento hacia arriba del jugador
		 */
		public void moveUp(){
			if(getRoot().equals("MarioCorriendoDerecha") ||getRoot().equals("MarioDerecha")) {
				setRoot("MarioCorriendoDerecha2");
			}else if (getRoot().equals("Mario") || getRoot().equals("MarioCaminando")) {
				setRoot("MarioCaminando2");
			}

				y-=1;
			
			

		}
		
		/*
		 * Realiza el movimiento hacia abajo del jugador
		 */
		public void moveDown(){
			if(getRoot().equals("MarioCorriendoDerecha") ||getRoot().equals("MarioDerecha")) {
				setRoot("marioSaltandoIzq");
			}else if (getRoot().equals("Mario") || getRoot().equals("MarioCaminando")) {
				setRoot("marioSaltandoDer");
			}
			
			y+=1;
		}
		
		/*
		 * Realiza el movimiento hacia la derecha del jugador
		 */
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
		
		/*
		 * Realiza el movimiento hacia la izquierda del jugador
		 */
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
	
		/*
		 * retorna el root de cada sprite del jugador
		 */
	public String getRoot() {
		return root;
	}
	
	/*
	 * returna la posicion en x del jugador
	 */
	public int getX() {
		return x;
	}
	
	/*
	 * returna la posicion en y del jugador
	 */
	public int getY() {
		return y;
	}
	
	/*
	 * returna si es visible o no el jugador
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/*
	 * Asigna un nuevo root 
	 */
	public static void setRoot(String r) {
		root = r;
	}
	
	/*
	 * Hace visible o no al jugador
	 */
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
	
	/*
	 * Realiza el movimiento hacia la derecha del jugador teniendo el martillo
	 */
	public void moveMartilloRight() {
		if(mr==0) {
			setRoot("mariorojo16");
			x+=5;
			mr+=1;
		}else if(mr==1){
			setRoot("mariorojo17");
			x+=5;
			mr+=1;
		}else if(mr==2) {
			setRoot("mariorojo18");
			x+=5;
			mr+=1;
		}else {
			setRoot("mariorojo19");
			x+=5;
			mr-=3;
		}
	}
	
	/*
	 * Realiza el movimiento hacia la izquierda del jugador teniendo el martillo
	 */
	public void moveMartilloLeft() {
		if(mi==0) {
			setRoot("mariorojo7");
			x-=5;
			mi+=1;
		}else if(mi==1){
			setRoot("mariorojo8");
			x-=5;
			mi+=1;
		}else if(mi==2) {
			setRoot("mariorojo9");
			x-=5;
			mi+=1;
		}else {
			setRoot("mariorojo10");
			x-=5;
			mi-=3;
		}
	}
	
	/*
	 * retorna si gano o no la partida
	 */
	public String gano() {
		String s=null;
		if(gano==0) {
			s="perdio";
		}else if(gano==1) {
			s="gano";
		}
		return s;
	}
	/*
	 * Valida el movimiento del salto del jugador
	 */
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
	
	/*
	 * valida si el jugador esta saltando o no
	 */
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
	
	/*
	 * Valida que el jugador suba por las escaleras
	 */
	public void setSubir() {
		if(!root.equals("marioSubiendoEscalera")) {
			setRoot("marioSubiendoEscalera");
		}else if(root.equals("marioSubiendoEscalera")){
			setRoot("marioSubiendoEscalera2");
		}
		// TODO Auto-generated method stub
		
		
	}
	
	/*
	 * validad el score de la partida
	 */
	public int score(int x) {
		int r=0;
		if(x==0) {
			r=vidas;
		}else {
			r=score;
		}
		return r;
	}
	
	/*
	 * Suma vidas al jugador
	 */
	public static  void sumeVida(int i) {
		vidas+=i;
		
	}
	
	/*
	 * suma puntajes al jugador
	 */
	public static  void sumeScore(int i) {
		score+=i;
		
	}
	public void inicial() {
		this.x=xInicial;
		this.y=yInicial;
		
		
	}
	

}