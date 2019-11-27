package aplicacion;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.ArrayList;
public abstract class  Escalera
{
    private double posXx,defX;
    private double pendiente, puntoCorte,posY;    
   private int xInicial, xFinal,  yInicial, yFinal;
   protected String root;
    public Escalera(int xInicial, int xFinal, int yInicial,int yFinal){
        this.xInicial=xInicial;
        this.xFinal=xFinal;
        this.yInicial=yInicial;
        this.yFinal=yFinal;
        
    }
    
    public int[] getPosicionesX(){
    	
        return (new int[]{xInicial,xFinal});
    }
    public String getRoot() {
    	return root;
    }
    public int[]  getPosicionesY(){
        return (new int[]{yInicial,yFinal});
    }
}



