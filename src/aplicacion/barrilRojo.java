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
public class barrilRojo extends barril
{
 
	
    public barrilRojo(int x,int y){
        super(x,y, x, 1000000, "barrilRojo");
    }
    
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fall(DonkeyPoob DonkeyPoob) {
		// TODO Auto-generated method stub
		
	}
	public void setRoot(String s) {
    	super.setRoot(s);
    }
}