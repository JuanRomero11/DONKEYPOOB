package aplicacion;

import java.awt.*;
import java.awt.geom.*;
import java.lang.Math;
import java.util.ArrayList;

/**
 * Write a description of class Water here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BarrilVerde extends Barril
{
 
	
    public BarrilVerde(int x,int y){
        super(x,y, x, 1000000, "barrilverde1",3);
    }
    
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	
	public void setRoot(String s) {
    	super.setRoot(s);
    }
}