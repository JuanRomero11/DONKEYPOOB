package aplicacion;


import java.awt.Component;
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
	public ArrayList<barril> barriles=new ArrayList<barril>();
	public ArrayList<Elemento> elementos=new ArrayList<Elemento>();
	private boolean visible=true;
	public static final int barrilesRonda = 2;
	public boolean invertir=false;
	public static final long segundo = 1000000000;
	private ArrayList<barril> gotas=new ArrayList<barril>();
	private ArrayList<jugador> jugadores=new ArrayList<jugador>();
	private  ArrayList<Escalera> escaleras=new ArrayList<Escalera>();
	//donkeykong
	public personaje Donkey,princesa;
	//acabo
	public boolean termino;
	//jugador
	public boolean jumping=false;
	public boolean enAire=false;
	public boolean Escalando=false;
	
	
	public DonkeyPoob() throws IOException{
		prepareDonkeyPrincesa();
		preparePlataformas();
		prepareBarriles();
		prepareJugadores();
		prepareEscaleras();
		
		int k=(int) (Math.random()*3+1);
		for(int i=0;i<k;i++) {
		
			prepareElementos();
		}
		termino=false;
		
		
	}
	private void prepareDonkeyPrincesa() {
		Donkey=new DonkeyKong(0,131,"DonkeyIzquierda");
		princesa = new Princesa(300,171,"princesa");
	}
	private void prepareElementos() {
		if(elementos.size()>0) {
			Elemento nuevo=elementoAzar();
			
			if(nuevo.getX()==elementos.get(0).getX() && nuevo.getY()==elementos.get(0).getY()) {
				nuevo.setX(nuevo.getX()+500);
				elementos.add(nuevo);
			}else {
				elementos.add(nuevo);
			}
		}else {
			Elemento nuevo=elementoAzar();
			elementos.add(nuevo);
		}
		
	}
	private Elemento elementoAzar() {
		int lona= (int) Math.random()*plataforma.size()+2;
		
		int k=(int) (Math.random()*5);
		Elemento cualquiera = null;
		if(k==0) {
			 cualquiera=new martillo(plataforma.get(lona).getInferior()[0],plataforma.get(2).getInferior()[1]+40,"martillo");
			
		}else if(k==1) {
			cualquiera= new Cereza(plataforma.get(lona).getInferior()[0],plataforma.get(3).getInferior()[1]+40,"cereza"); 
		     
		}else if(k==2) {
			cualquiera= new Corazon(plataforma.get(lona).getInferior()[0],plataforma.get(3).getInferior()[1]+40,"corazon");
		   
		}else if(k==3){
			cualquiera= new Hongo(plataforma.get(lona).getInferior()[0],plataforma.get(3).getInferior()[1]+40,"hongo");
		  
		}else if(k==4){
			cualquiera= new Manzana(plataforma.get(lona).getInferior()[0],plataforma.get(3).getInferior()[1]+40,"hongo");
		  
		}
		return cualquiera;
	}
	
	private void prepareEscaleras() {
		EscaleraBarril nueva =new EscaleraBarril(217,251,570,470);
		EscaleraMario nuevaUno =new EscaleraMario(624,653,570,471);	
		EscaleraMario nuevaDos =new EscaleraMario(92,122,490,391);
		EscaleraBarril nuevaTres =new EscaleraBarril(303,332,490,390);
		EscaleraBarril nuevaCuatro =new EscaleraBarril(144,171,410,309);
		EscaleraMario nuevaCinco =new EscaleraMario(377,408,410,310);
		EscaleraMario nuevaSeis =new EscaleraMario(602,630,410,310);
		EscaleraMario nuevaSiete =new EscaleraMario(72,104,329,230);
		EscaleraMario nuevaOcho =new EscaleraMario(203,235,329,230);
		EscaleraBarril nuevaNueve =new EscaleraBarril(579,610,329,229);
		EscaleraBarril nuevaDiez =new EscaleraBarril(243,281,249,170);
		EscaleraMario nuevaOnce =new EscaleraMario(598,626,249,172);
		EscaleraMario nuevaDoce =new EscaleraMario(186,212,191,102);
		escaleras = new ArrayList<Escalera>();
		escaleras.add(nueva);
		escaleras.add(nuevaUno);
		escaleras.add(nuevaDos);
		escaleras.add(nuevaTres);
		escaleras.add(nuevaCuatro);
		escaleras.add(nuevaCinco);
		escaleras.add(nuevaSeis);	
		escaleras.add(nuevaSiete);
		escaleras.add(nuevaOcho);
		escaleras.add(nuevaNueve);
		escaleras.add(nuevaDiez);
		escaleras.add(nuevaOnce);
		escaleras.add(nuevaDoce);

	}
	private void prepareJugadores() {
		jugador mario= new jugador(2,550,"MarioDerecha"); 
		jugadores.add(mario);
		
	}
	public int numElementos() {
		return elementos.size();
	}
	public void prepareBarriles() {
		if(barriles.size()==0) {
			
			BarrilNormal gota= new BarrilNormal(80,171); 
	        barriles.add(gota);
		}else if(barriles.get(barriles.size()-1).getY()>191){
			int k=(int) (Math.random()*4);
			Donkey.setRoot("DonkeyIzquierda");
			if(k==0) {
				
				BarrilNormal gota= new BarrilNormal(80,171); 
		        barriles.add(gota);
			}else if(k==1) {
				BarrilAzul gota= new BarrilAzul(80,171); 
		        barriles.add(gota);
			}else if(k==2) {
				barrilRojo gota= new barrilRojo(80,171); 
		        barriles.add(gota);
			}else if(k==3){
				BarrilVerde gota= new BarrilVerde(80,171); 
		        barriles.add(gota);
			}
			
		}else {
			if(Donkey.getRoot().equals("DonkeyIzquierda")) {
				Donkey.setRoot("DonkeyDerecha");
			}
			
		}
		
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

  
    public boolean isFinished(){
		return termino;
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
		return barriles.get(i);
	}
    public jugador getJugador(int i){
		return jugadores.get(i);
	}
  //Move el jugador
  	public void JugadorNUp(int n){ 	
  		validarElementos(jugadores.get(n)) ;
  		
  		if(jumping && jugadores.get(n).validarSalto() && !Escalando && !enAire) {
  			
  			System.out.println("  adjaoijdoiasjd "+jumping+" "+jugadores.get(n).validarSalto()+" "+Escalando+" "+enAire);
  			jugadores.get(n).moveUp();
  			
  			jumping=jugadores.get(n).validarSalto();  			
  		}if(enAire) {
  			jumping=false;
  		}if(Escalando) {
  			jumping=false;
  			enAire=false;
  		}
  		}
  	
  	public void JugadorNDown(int n){
  		validarElementos(jugadores.get(n)) ;
  		validarJugador(jugadores.get(n));
  		validarPrincesa(jugadores.get(n));
  		if(!EstaEnLona(jugadores.get(n)) && !jumping && !Escalando ){
  			System.out.println(EstaEnLona(jugadores.get(n))+" "+jumping+" "+Escalando+" "+enAire);
  			jugadores.get(n).moveDown();
  			enAire=true;
  		}else if(jugadores.get(n).validarSalto()  && !EstaEnLona(jugadores.get(n))) {
  			jumping=jugadores.get(n).validarSalto();
  			enAire= false;
  		}
  		
  		}
  	public void JugadorNLeft(int n){
  		validarElementos(jugadores.get(n)) ;
  		validarJugador(jugadores.get(n));
  		validarPrincesa(jugadores.get(n));
  		if(!termino) {
  			if(!invertir) {
  	  	  		jugadores.get(n).moveLeft();
  	  		}else {
  	  			jugadores.get(n).moveRight();
  	  		}
  		}
  		
  		
  		}
  	public void JugadorNEscalar(int n){ 
  		validarElementos(jugadores.get(n)) ;
  		validarJugador(jugadores.get(n));
  		validarPrincesa(jugadores.get(n));
		System.out.println(EstaEnLona(jugadores.get(n))+" "+jumping+" "+Escalando+" "+enAire);

  		for(int i=0;i<escaleras.size();i++) {
  			if(escaleras.get(i).getRoot().equals("EscaleraMario") && jugadores.get(n).getX()>=escaleras.get(i).getPosicionesX()[0] && jugadores.get(n).getX()<=escaleras.get(i).getPosicionesX()[1] && jugadores.get(n).getY()<=escaleras.get(i).getPosicionesY()[0] && jugadores.get(n).getY()>=escaleras.get(i).getPosicionesY()[1] ) {
  				Escalando=true;
  				jugadores.get(n).setSubir();
  				jumping=false;
  				enAire=false;
  				jugadores.get(n).moveUp();
  				break;
  			
  			}
  			
  			else if(i==escaleras.size()-1 && (!escaleras.get(i).getRoot().equals("EscaleraMario") || jugadores.get(n).getX()<escaleras.get(i).getPosicionesX()[0] || jugadores.get(n).getX()>escaleras.get(i).getPosicionesX()[1] ||  jugadores.get(n).getY()>=escaleras.get(i).getPosicionesY()[0] || jugadores.get(n).getY()<=escaleras.get(i).getPosicionesY()[1] )) {
  				
  				Escalando=false;
  				break;
  			}
  		}
		}

  	private void validarElementos(jugador jugador) {
  		for(int i=0;i<elementos.size();i++) {
  			if(!elementos.get(i).isVisible()) {
  				elementos.remove(i);
  				break;
  			}
  			else if(elementos.get(i).getX()>=jugador.getX() && elementos.get(i).getX()<=jugador.getX()+20 && elementos.get(i).getY()>=jugador.getY() && elementos.get(i).getY()<=jugador.getY()+20) {
  				
  				activeElemento(elementos.get(i));
  				elementos.get(i).setVisible(false);
  				
  				break;
  			}
  			else if((elementos.get(i).getX()+20>=jugador.getX() && elementos.get(i).getX()+20<=jugador.getX()+20 && elementos.get(i).getY()>=jugador.getY() && elementos.get(i).getY()<=jugador.getY()+20)) {
  				activeElemento(elementos.get(i));
  				elementos.get(i).setVisible(false);
  				
  				break;
  			}
  			else if((elementos.get(i).getX()<=jugador.getX()+20 && elementos.get(i).getX()+20>=jugador.getX()+20 && elementos.get(i).getY()>=jugador.getY() && elementos.get(i).getY()<=jugador.getY()+20)) {
  				activeElemento(elementos.get(i));
  				elementos.get(i).setVisible(false);
  				
  				break;
  			}
  			
  		}
  	}
  	private void validarJugador(jugador jugador) {
  		for(int i=0;i<barriles.size();i++) {
  			if(!barriles.get(i).isVisible()) {
  				barriles.remove(i);
  				break;
  			}
  			if(barriles.get(i).getX()>=jugador.getX() && barriles.get(i).getX()<=jugador.getX()+20 && barriles.get(i).getY()>=jugador.getY() && barriles.get(i).getY()<=jugador.getY()+20) {
  				activeBarril(barriles.get(i));
  				
  				break;
  			}
  			else if((barriles.get(i).getX()+20>=jugador.getX() && barriles.get(i).getX()+20<=jugador.getX()+20 && barriles.get(i).getY()>=jugador.getY() && barriles.get(i).getY()<=jugador.getY()+20)) {
  				activeBarril(barriles.get(i));
  				
  				break;
  			}
  			else if((barriles.get(i).getX()<=jugador.getX()+20 && barriles.get(i).getX()+20>=jugador.getX()+20 && barriles.get(i).getY()>=jugador.getY() && barriles.get(i).getY()<=jugador.getY()+20)) {	
  				activeBarril(barriles.get(i));
  				
  				break;
  			}
  			
  		}
  	}
  	private void validarPrincesa(jugador jugador) {
  		
  			if(princesa.getX()>=jugador.getX() && princesa.getX()<=jugador.getX()+20 && princesa.getY()>=jugador.getY() && princesa.getY()<=jugador.getY()+20) {
  				termino=true;
  				jugadores.get(0).gano=1;
  			}
  			else if((princesa.getX()+20>=jugador.getX() && princesa.getX()+20<=jugador.getX()+20 && princesa.getY()>=jugador.getY() && princesa.getY()<=jugador.getY()+20)) {
  				termino=true;
  				jugadores.get(0).gano=1;
  			}
  			else if((princesa.getX()<=jugador.getX()+20 && princesa.getX()+20>=jugador.getX()+20 && princesa.getY()>=jugador.getY() && princesa.getY()<=jugador.getY()+20)) {	
  				termino=true;
  				jugadores.get(0).gano=1;
  			}
  			
  		
  	}
  	private void reinicieJuego() {
		jugadores.get(0).inicial();
		for(int i=0;i<barriles.size();i++) {
			barriles.get(i).setVisible(false);
		}
		termino=false;
	}
	private void activeBarril(barril barril) {
		if(barril instanceof BarrilVerde) {
			jugador.sumeVida(1);
			barril.setVisible(false);
		}else {
			jugador.setRoot("MarioMuerto");
			jugador.sumeVida(-1);
			
			if(jugador.vidas==0) {
				termino=true;
			}else {
				invertir=false;
				reinicieJuego();	
			}
		}
		
	}

	private void activeElemento(Elemento elemento) {
		if(elemento instanceof Corazon) {
			jugador.sumeVida(1);
		}else if(elemento instanceof Cereza) {
			jugador.sumeScore(10);
		}else if(elemento instanceof Manzana) {
			jugador.sumeScore(5);
		}else if(elemento instanceof Hongo) {
			if(invertir) {
				invertir=false;
			}else {
			invertir=true;
			}
			
		}
		
	}
  	public int[] puntajes() {
  		return new int[] {jugador.vidas,jugador.score};
  	}
	private boolean EstaEnLona(jugador jugador) {
  		
  		boolean bandera=false;
  		for(int i =0 ;i<plataforma.size();i++) {
    		if( jugador.getY()==plataforma.get(i).getInferior()[1]-20 && jugador.getX()>=plataforma.get(i).getInferior()[0] && jugador.getX() <=plataforma.get(i).getSuperior()[0] ) {
    			bandera=true;
    			enAire=false;
    			jumping=false;

    			break;
    		}
    		else if(jugador.getY()==plataforma.get(i).getInferior()[1]-20 && ( jugador.getX()<plataforma.get(i).getInferior()[0] || jugador.getX() >plataforma.get(i).getSuperior()[0] )){
    			bandera=false;
    			jumping=false;
    			break;
    		}else if(jugador.getY()!=plataforma.get(i).getInferior()[1]-20 && i==-1 && !jumping ){
    			bandera=false;
    			break;
    		}
    	}
		return bandera;
  	}
	
  	
	public void JugadorNRight(int n){
		validarElementos(jugadores.get(n)) ;
		validarJugador(jugadores.get(n));
		if(!termino) {
			 if(!invertir) {
					jugadores.get(n).moveRight();
				}else {
					jugadores.get(n).moveLeft();
				}
		}
		
			
		
		
  			
		
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
    
    public void mover(int x){
    	if(barriles.get(x) instanceof BarrilNormal || barriles.get(x) instanceof BarrilVerde) {
    		moverBarrilNormal(x);
    		
    	}else if(barriles.get(x) instanceof BarrilAzul) {
    		moverBarriAzul(x);
    	}else if(barriles.get(x) instanceof barrilRojo) {
    		barriles.get(x).setY(barriles.get(x).getY()+1);
    	}
    	
    	 
    	 
	}
    private void moverBarriAzul(int x) {
    	for(int i =1 ;i<plataforma.size();i++) {
    		System.out.println("no se si "+ barrilEnEscalera(barriles.get(x)));
    			if( barrilEnEscalera(barriles.get(x)) ){
        			barriles.get(x).setY(barriles.get(x).getY()+1);
        			break;
        		
    		}
    		
    		else if(barriles.get(x).getY()==plataforma.get(i).getInferior()[1]-20 && barriles.get(x).getX()>=plataforma.get(i).getInferior()[0] && barriles.get(x).getX() <=plataforma.get(i).getSuperior()[0] && i%2!=0) {
    			barriles.get(x).setX(barriles.get(x).getX()+1);
    			break;
    		}
    		
    		else if (barriles.get(x).getY()==plataforma.get(i).getInferior()[1]-20 && barriles.get(x).getX()>=plataforma.get(i).getInferior()[0] && barriles.get(x).getX() <=plataforma.get(i).getSuperior()[0] && i%2==0 ){
    			barriles.get(x).setX(barriles.get(x).getX()-1);
    			break;
    		}
    		else if(barriles.get(x).getY()!=plataforma.get(i).getInferior()[1]-20 && i==plataforma.size()-1){
    			barriles.get(x).setY(barriles.get(x).getY()+1);
    			break;
    		}
    	}
    }
    private void moverBarrilNormal(int x) {
    	for(int i =1 ;i<plataforma.size();i++) {
    		if(barriles.get(x).getY()==plataforma.get(i).getInferior()[1]-20 && barriles.get(x).getX()>=plataforma.get(i).getInferior()[0] && barriles.get(x).getX() <=plataforma.get(i).getSuperior()[0] && i%2!=0 ) {
    			barriles.get(x).setX(barriles.get(x).getX()+1);
    			break;
    		}
    		
    		else if (barriles.get(x).getY()==plataforma.get(i).getInferior()[1]-20 && barriles.get(x).getX()>=plataforma.get(i).getInferior()[0] && barriles.get(x).getX() <=plataforma.get(i).getSuperior()[0] && i%2==0 ){
    			barriles.get(x).setX(barriles.get(x).getX()-1);
    			break;
    		}
    		else if(barriles.get(x).getY()!=plataforma.get(i).getInferior()[1]-20 && i==plataforma.size()-1){
    			barriles.get(x).setY(barriles.get(x).getY()+1);
    			break;
    		}
    	}
		
	}
    private boolean barrilEnEscalera(barril barril) {
    	boolean f=false;
    	for(int i=0;i<escaleras.size();i++) {
  			if(barril.getX()>=escaleras.get(i).getPosicionesX()[0] && barril.getX()<=escaleras.get(i).getPosicionesX()[1] && barril.getY()<=escaleras.get(i).getPosicionesY()[0]-25 && barril.getY()>=escaleras.get(i).getPosicionesY()[1] ) {
  				f=true;
  				break;
  			}
  			
  		}
  		return f;
    	
    }
	
	public int getJugadores() {
		
		return 1;
	}
	public void jummping(int i,int gravity) {
		System.out.println("entre aqui "+jugadores.get(i).validarSalto() );
		if(!jugadores.get(i).validarSalto() ) {
			System.out.println("entsadasdsadsadsadsadsadsadsade");
			jugadores.get(i).jummping(gravity);
			jumping=true;           
			enAire=false;
		}
		
	}
	public int sizeBarriles() {
		
		return barriles.size();
	}
	public Elemento getElemento(int i) {
	
		return elementos.get(i);
	}
	public personaje getDonkey() {
		return Donkey;
	}
	public String Gano() {
		return jugadores.get(0).gano();
	}
	public personaje getPrincesa() {
		// TODO Auto-generated method stub
		return princesa;
	}
	
	
  
}
