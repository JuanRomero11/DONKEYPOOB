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
	private ArrayList<jugador> jugadores=new ArrayList<jugador>();
	private int bajar=0;
	public boolean jumping=false;
	public boolean enAire=false;
	public DonkeyPoob() throws IOException{
		preparePlataformas();
		prepareBarriles();
		prepareJugadores();
	}
	private void prepareJugadores() {
		jugador mario= new jugador(0,550,"MarioDerecha"); 
		jugadores.add(mario);
		
	}
	public void prepareBarriles() {
		BarrilNormal gota= new BarrilNormal(0,171); 
		barriles=new barril[1];
        barriles[0]=gota;
	}
	public void preparePlataformas() throws IOException{
		plataformas pINf= new plataformas(new int[]{294,121},new int[]{453,121});
		plataformas p0=new plataformas(new int[]{0,191},new int[]{700,191});
		plataformas p1=new plataformas(new int[]{30,249},new int[]{744,249});
		plataformas p2 =new plataformas(new int[]{0,329},new int[]{693,329});;
		plataformas p3=new plataformas(new int[]{30,410},new int[]{724,410});;
		plataformas p4=new plataformas(new int[]{0,490},new int[]{693,490});;
		plataformas p5=new plataformas(new int[]{0,570},new int[]{745,570});;
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
    public jugador getJugador(int i){
		return jugadores.get(i);
	}
  //Move el jugador
  	public void JugadorNUp(int n){
  		if(jumping) {
  			jugadores.get(n).moveUp();
  			jumping=jugadores.get(n).validarSalto();
  			
  		}
  		}
  	public void JugadorNDown(int n){
  		if(!EstaEnLona(jugadores.get(n)) && !jumping) {
  			jugadores.get(n).moveDown();
  			jumping=jugadores.get(n).validarSalto();
  			enAire=true;
  		}else {
  			enAire=false;
  		}
  		}
  	public void JugadorNLeft(int n){   
  		
  			jugadores.get(n).moveLeft();
  			
  		
  		
  		}
  	private boolean EstaEnLona(jugador jugador) {
  		boolean bandera=false;
  		for(int i =1 ;i<plataforma.size();i++) {
    		if(jugador.getY()==plataforma.get(i).getInferior()[1]-20 && jugador.getX()>=plataforma.get(i).getInferior()[0] && jugador.getX() <=plataforma.get(i).getSuperior()[0] ) {
    			bandera=true;
    			
    			break;
    		}
    		else if(jugador.getY()!=plataforma.get(i).getInferior()[1]-20 && jugador.getX()<=plataforma.get(i).getInferior()[0] && jugador.getX() >=plataforma.get(i).getSuperior()[0] ){
    			bandera=false;
    			break;
    		}
    	}
		return bandera;
	}
  	private boolean EstaEnLonaSuperior(jugador jugador) {
  		boolean bandera=false;
  		for(int i =1 ;i<plataforma.size();i++) {
    		if(jugador.getY()==plataforma.get(i).getInferior()[1]+20 && jugador.getX()>=plataforma.get(i).getInferior()[0] && jugador.getX() <=plataforma.get(i).getSuperior()[0] ) {
    			bandera=true;
    			
    			break;
    		}
    		else if(jugador.getY()!=plataforma.get(i).getInferior()[1]+20 && jugador.getX()<=plataforma.get(i).getInferior()[0] && jugador.getX() >=plataforma.get(i).getSuperior()[0] ){
    			bandera=false;
    			break;
    		}
    	}
		return bandera;
	}
	public void JugadorNRight(int n){
	
			jugadores.get(n).moveRight();
  			
		
		}
  	public void Jugadornormal(int n) {jugadores.get(n).moveNormal();	
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
    	for(int i =1 ;i<plataforma.size();i++) {
    		if(barriles[x].getY()==plataforma.get(i).getInferior()[1]-20 && barriles[x].getX()>=plataforma.get(i).getInferior()[0] && barriles[x].getX() <=plataforma.get(i).getSuperior()[0] && i%2!=0 ) {
    			barriles[x].setX(barriles[x].getX()+1);
    			
    			break;
    		}
    		
    		else if (barriles[x].getY()==plataforma.get(i).getInferior()[1]-20 && barriles[x].getX()>=plataforma.get(i).getInferior()[0] && barriles[x].getX() <=plataforma.get(i).getSuperior()[0] && i%2==0 ){
    			barriles[x].setX(barriles[x].getX()-1);
    			break;
    		}
    		else if(barriles[x].getY()!=plataforma.get(i).getInferior()[1]-20 && i==plataforma.size()-1){
    			barriles[x].setY(barriles[x].getY()+1);
    			
    			break;
    		}
    	}
    	 
    	 
	}
    private void CrearBarriles(barril gota){
        gota.fall(this);
        list.add(0,gota);
        gotas.add(gota); 
    }
	public int getJugadores() {
		// TODO Auto-generated method stub
		return 1;
	}
	public void jummping(int i) {
		jumping=true;
		jugadores.get(i).jummping();
		
	}
	
  
}
