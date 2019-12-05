
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
public abstract class Barril extends Elemento
{
    private int ini;//posicion de nacimiento
    private int xPos;
    private int yPos;
    public int xInicial;
    public int yInicial;
    private int tipe;
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
    public Barril(int x, int y, int scoreKill, int MvSec,String root,int tipo){
		super(x,y,20,20);
		//finals
		mov =root;
		xPos=x;
		yPos=y;
		xInicial=x;
		yInicial=y;
		tipe=tipo;
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
    	if(tipe==0) {
    		if(getRoot().equals("barrilamarillo1")) {
        		
        		setRoot("barrilamarillo2");
        	}else if(getRoot().equals("barrilamarillo2")) {
        		
        		setRoot("barrilamarillo3");
        	}   else if(getRoot().equals("barrilamarillo3")) {
        		
        		setRoot("barrilamarillo4");
        	}    	else if(getRoot().equals("barrilamarillo4")) {
        		
        		setRoot("barrilamarillo1");
        	}
    	}else if(tipe==2){
    		if(getRoot().equals("barrilazul1")) {
        		
        		setRoot("barrilazul2");
        	}else if(getRoot().equals("barrilazul2")) {
        		
        		setRoot("barrilazul3");
        	}   else if(getRoot().equals("barrilazul3")) {
        		
        		setRoot("barrilazul4");
        	}    	else if(getRoot().equals("barrilazul4")) {
        		
        		setRoot("barrilazul1");
        	}
    	}else if(tipe==3) {
    		if(getRoot().equals("barrilverde1")) {
        		
        		setRoot("barrilverde2");
        	}else if(getRoot().equals("barrilverde2")) {
        		
        		setRoot("barrilverde3");
        	}   else if(getRoot().equals("barrilverde3")) {
        		
        		setRoot("barrilverde4");
        	}    	else if(getRoot().equals("barrilverde4")) {
        		
        		setRoot("barrilverde1");
        	}
    		
    	}
    	  }
    	
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
