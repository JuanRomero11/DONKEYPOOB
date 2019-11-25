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
	public jugador(int x, int y, String root) {
		this.x = x;
		this.y = y;
		
		this.root=root;
		visible = true;
	}
	//Metodos de movimiento
		public void moveUp(){
			setRoot("MarioSubiendo1");
				y-=10;

		}
		
		public void moveDown(){
			setRoot("MarioSubiendo1");
				y+=10;
		}
		
		public void moveRight(){
			setRoot("MarioCorriendoDerecha");
				x+=10;
		}
		public void moveLeft(){
			setRoot("MarioCaminando");
				x-=10;
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

}