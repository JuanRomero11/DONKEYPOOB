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
public class BarrilNormal extends barril
{
 
	
    public BarrilNormal(int x){
        super(x,x, x, 1000000, "barril");
    }
    public void fall(DonkeyPoob DonkeyPoob){
        super.falling(DonkeyPoob,this);
    }
    
    public double[] touch(double[] h,DonkeyPoob DonkeyPoob){
        return DonkeyPoob.platafor(h,0);
    }
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
}