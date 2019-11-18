package aplicacion;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class plataformas {
	
	private int superior[]=new int[2], supe[]=new int[2];
	private int inferior[]=new int[2], infe[]=new int[2];
	private double m;
	private double b;
	private double[] puntos;
	private ArrayList<Escalera> escaleras=new ArrayList<Escalera>();
	public plataformas(int[] posicionesIniciales, int[] posicionesFinales) {
		
		this.inferior=posicionesIniciales;
		this.superior=posicionesFinales;
		m=(posicionesFinales[1]-posicionesIniciales[1])/(posicionesFinales[0]-posicionesIniciales[0]);
		
		b=posicionesFinales[1]-(m*posicionesFinales[0]);
		superior=posicionesFinales;
	       inferior=posicionesIniciales;  
	       if (posicionesFinales[1]>inferior[1]){
	           supe=posicionesIniciales;
	           infe=posicionesFinales;
	       }
	       else{
	           supe=posicionesFinales;
	           infe=posicionesIniciales;
	       }
		
	}
	 public int[] getSuperior(){   
	       return supe;
	   }   
	   
	   public int[] getInferior(){    
	       return infe;
	   }
	   
	   public int[] getescaleras(){
	       int[] temp=new int[escaleras.size()];
	       for(int i=0; i<escaleras.size(); i++){
	           temp[i]=(int)escaleras.get(i).getX();
	       }       
	       return temp;
	   }
	   
	   public int puntc(int x,boolean visible){
	        hacerEscalera(x,visible);
	        return 1;
	    }
	   public void hacerEscalera(int x,boolean visible){
		    Escalera h =new Escalera(getEQ(),x);
		    escaleras.add(h);
	   }
	   public int[] getLimx(){
	       int a[]=new int[2];
	       if (inferior[0]>superior[0]){
	           a[0]=superior[0];
	           a[1]=inferior[0];
	       }else{
	           a[1]=superior[0];
	           a[0]=inferior[0];
	        }
	       return a;
	   }
	   
	  
	   public double[] getLimy(){
	       double a[]=new double[2];
	       if (inferior[1]>superior[1]){
	           a[0]=superior[1];
	           a[1]=inferior[1];
	       }else{
	           a[1]=superior[1];
	           a[0]=inferior[1];
	        }
	       return a;
	   }
	   
	   public double[] getEQ(){
	       double t[] ={m,b};       
	       return t;
	    }
	    
	   private void eq(){
	       double restaY,restaX; 
	       
	       restaY=superior[1]-inferior[1];
	       restaX=superior[0]-inferior[0];
	       m=restaY/restaX;
	       b=inferior[1]-(m*inferior[0]);
	       
	    }
	   public boolean hayEscalera(int x){
	          
	       for(int i=0; i<escaleras.size(); i++){
	           if(escaleras.get(i).getX()==x){
	               return true;
	           }
	       }       
	       return false;
	   }
	  
	  
	   
}
