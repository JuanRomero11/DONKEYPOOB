
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
    /**
     * Constructor for objects of class barril
     */
    private final String mov;
    private int MvSec;
	private final long segundo = 1000000000;
	private long time;
    public barril(int x, int y, int scoreKill, int MvSec,String root){
		super(x,y,20,20);
		//finals
		mov = "resources/"+root+".png";
		
		time = System.nanoTime();
		this.MvSec = MvSec;
		setRoot(mov);
		alive = true;
	}
    public abstract void fall(DonkeyPoob DonkeyPoob);
    
    public void falling(DonkeyPoob DonkeyPoob,barril w){
        double pos[]={0,0};
    
        while(yPos<490){
            double t=0;
            if (pos[0]>0 && Math.round(pos[1])==yPos){
                xPos+=1;t+=1;
            }else if (pos[0]<0 && Math.round(pos[1])==yPos){               
                xPos-=1;t-=1;
            }else{               
                yPos+=1;                
            }
            //path o camino de agua
            double h[]={getX(),getY(),t};
            pos=w.touch(h,DonkeyPoob);
            
        }
        
        
    }
    
    
    
    public double[] touch(double[] h,DonkeyPoob DonkeyPoob){
        return DonkeyPoob.platafor(h,0);
    }
    
    
    public int getX(){
        return xPos;
    } 
    
    public void setX(int x){xPos=x;}
    
    public int getY(){
        return yPos;
    }
    
    public int getIn(){return ini;}
    
    public String getColor(){return color;}
    
    public int[][] recorrido(){
       return new int[][] {{1,2,3}};
    }
}
