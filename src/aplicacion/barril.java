
package aplicacion;


import java.awt.geom.*;
import java.lang.Math;
import java.util.ArrayList;

/**
 * Abstract class barril - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class barril extends Elemento
{
    private int ini;//posicion de nacimiento
    private int xPos;
    private int yPos;
    private int vis=0;
    private String color;
    private boolean alive;
    /**s
     * Constructor for objects of class barril
     */
    private final String mov;
    private int MvSec;
	private final long segundo = 1000000000;
	private long time;
    public barril(int x, int y, int scoreKill, int MvSec,String root){
		super(x,y,20,20);
		//finals
		mov =root;
		xPos=x;
		yPos=y;
		time = System.nanoTime();
		this.MvSec = MvSec;
		setRoot(mov);
		alive = true;
	}
    public abstract void fall(DonkeyPoob DonkeyPoob);
    
    /*
     * returna la posicion en x del barril
     */
    public int getX(){
        return xPos;
    } 
    
    /*
     * Valida cada x por el que el carril se mueve con el fin de cambiarle el root
     */
    public void setX(int x){
    	xPos=x;
    	if(getRoot().equals("barril")) {
    		
    		setRoot("barrilCuatro");
    	}else if(getRoot().equals("barrilCuatro")) {
    		
    		setRoot("barrilDos");
    	}   else if(getRoot().equals("barrilDos")) {
    		
    		setRoot("barrilTres");
    	}    	else if(getRoot().equals("barrilTres")) {
    		
    		setRoot("barril");
    	}  }
    
    /*
     * le sagina al barril una nueva posicion en y
     */
    public void setY(int y){yPos=y;}
   
    /*
     * retorna la posicion en y del barril
     */
    public int getY(){
        return yPos;
    }
    
    public int getIn(){return ini;}
    
    public String getColor(){return color;}
    
    public int[][] recorrido(){
       return new int[][] {{1,2,3}};
    }
}
