package aplicacion;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.ArrayList;
public class Escalera
{
    private double posXx,defX;
    private double pendiente, puntoCorte,posY;    
   
    public Escalera(double posLo[], int posX ){
        pendiente=posLo[0];
        defX=posX;
        if(pendiente<0){
            posXx=posX-12;
        }
        else{
            //posXx=posX;
        }
        puntoCorte=posLo[1];
        posY=pendiente*posX+puntoCorte;
        
        //elPuncture.positionSet(posXx,posY);
        
    }
    
    public double getX(){
        return defX;
    }
    public double getY(){
        return posY;
    }
}



