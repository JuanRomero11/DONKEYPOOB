package aplicacion;


import java.io.*;
import java.util.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import presentacion.ReplicateGUI;
public class DonkeyPoob{
	public static final int PLATAFORMA_X_NIVEL = 7;         
	public ArrayList<plataformas> plataforma; 
	private ArrayList<Object> list=new ArrayList<Object>();
	public barril[] barriles;
	private boolean visible=true;
	public static final int barrilesRonda = 2;
	private boolean enPausa=false,onMute;
	public static final long segundo = 1000000000;
	private ArrayList<barril> gotas=new ArrayList<barril>();
	public DonkeyPoob() throws IOException{
		preparePlataformas();
		prepareBarriles();
	}
	public void prepareBarriles() {
		BarrilNormal gota= new BarrilNormal(30); 
		barriles=new barril[1];
        barriles[0]=gota;
	}
	public void preparePlataformas() throws IOException{
		plataformas pINf= new plataformas(new int[]{302,132},new int[]{468,132});
		plataformas p0=new plataformas(new int[]{0,202},new int[]{717,202});
		plataformas p1=new plataformas(new int[]{52,292},new int[]{766,263});
		plataformas p2 =new plataformas(new int[]{0,343},new int[]{716,372});;
		plataformas p3=new plataformas(new int[]{52,453},new int[]{765,424});;
		plataformas p4=new plataformas(new int[]{0,504},new int[]{716,535});;
		plataformas p5=new plataformas(new int[]{0,600},new int[]{765,584});;
		plataforma = new ArrayList<plataformas>();
		plataforma.add(pINf);
		plataforma.add(p0);
		plataforma.add(p1);
		plataforma.add(p2);
		plataforma.add(p3);
		plataforma.add(p4);
		plataforma.add(p5);	
	}

    private void  ordenar(){
        plataformas temp;
        for(int i=2; i<plataforma.size(); i++){
            for(int j=1; j<plataforma.size()-i; j++){
                if(plataforma.get(j).getLimx()[0]>plataforma.get(j+1).getLimx()[0]){
                    temp=plataforma.get(j);
                    plataforma.add(j,plataforma.get(j+1));
                    plataforma.add(j+1,temp);
                }
            }
        }

    }
    public boolean isFinished(){
		boolean ans = true;
		return ans;
	}
    private double[] nearTrap(double pendiente,boolean xTrue, double[] posgota,double lon, int t){
        double y=-1,count=10000000;
        boolean xTemp;
        for(int i=0; i< plataforma.size(); i++){
            int lim[]= plataforma.get(i).getLimx();
            if (posgota[0]>=lim[0] && posgota[0]<=lim[1]){
                double equa[]=plataforma.get(i).getEQ();
                double posY1=equa[0]*posgota[0]+equa[1];
                xTemp=plataforma.get(i).hayEscalera((int)posgota[0]);
                double dis=posY1-posgota[1];
                if(count>=dis && dis>=0){
                    count=dis;
                    y=posY1;
                    lon=(double)i;
                    xTrue=xTemp;
                    pendiente=equa[0] ;
                }

            }
        }
        
        double h[]={y,pendiente,lon};
        return h;
    }
    public double[] platafor(double[] posgota,int t){
        int lon=-1;
        double y=-1,pendiente=0,res[]=nearTrap(0,false,posgota,-1,t);
        y=res[0];
        pendiente = res[1];
        lon=(int)res[2];
        if (pendiente>=0){ y = y-5;
        }else{y = y-15;}
        if(plataforma.size()>0 && lon>-1){
            if (t==1){
                if (plataforma.get(lon).hayEscalera((int)posgota[0])){
                    pendiente=0;
                }else{
                    int n= plataforma.size();
                    hacerEscalera(lon,(int)posgota[0]);
                    if (n==plataforma.size()){
                        if (plataforma.get(lon).hayEscalera((int)posgota[0])){
                            pendiente=0;
                            }
                    }
                    else{
                    	pendiente=0;
                    }
                }
            }
        }

        double h[]= {pendiente,y};
        return h;
    }
    public void ronda(){
		
	}
    public void hacerEscalera(int loona, int posX){
        int t=plataforma.get(loona-1).puntc(posX,visible);
        if(t==0){removeEscalera(loona);}   
    }
    public void removeEscalera(int position){
        if(position>=0 && position<plataforma.size()){
            plataforma.remove(position);
            
        }
       
    } 
    public Elemento getBarril(int i){
		return barriles[i];
	}
    public boolean isRondaFinished(){
		boolean ans = true;
		for(barril p: barriles){
			ans &= !p.isVisible();
		}
		return ans;
	}
    public boolean enPausa(){
		return enPausa;
	}
    public void mover(int x){
    	barriles[x].setX(barriles[x].getX()+1);    
	}
    private void CrearBarriles(barril gota){
        gota.fall(this);
        list.add(0,gota);
        gotas.add(gota); 
    }
  
}
