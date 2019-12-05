package aplicacion;


import java.awt.Component;
import java.io.*;
import java.util.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import presentacion.DonkeyPoobGUI;
public class DonkeyPoob{
	public static final int PLATAFORMA_X_NIVEL = 7;         
	public ArrayList<Plataforma> plataforma; 
	
	public ArrayList<Barril> barriles=new ArrayList<Barril>();
	public ArrayList<Elemento> elementos=new ArrayList<Elemento>();
	private boolean visible=true;
	public static final int barrilesRonda = 2;
	public boolean invertir=false;
	public static final long segundo = 1000000000;
	private long time;
	private long time_x_ronda;
	
	public ArrayList<Jugador> Jugadores=new ArrayList<Jugador>();
	private  ArrayList<Escalera> escaleras=new ArrayList<Escalera>();
	//donkeykong
	public Personaje Donkey,princesa;
	//acabo
	public boolean termino;
	//Jugador
	public boolean jumping=false;
	public boolean enAire=false;
	public boolean Escalando=false;
	public boolean enMovimiento=true;
	public boolean martillando=false;
	
	
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
	
	/*
	 * Crea el DonkeyKong y la princesa
	 */
	private void prepareDonkeyPrincesa() {
		Donkey=new DonkeyKong(0,131,"DonkeyIzquierda");
		princesa = new Princesa(300,171,"princesa");
	}
	
	/*
	 * Crea los elementos 
	 */
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
	
	/*
	 * Crea elementos al azar y los ubica en diferentes ubicaciones
	 */
	private Elemento elementoAzar() {
		int lona= (int) Math.random()*plataforma.size()+2;
		
		int k=(int) (Math.random()*5);
		Elemento cualquiera = null;
		if(k==0) {
			 cualquiera=new Martillo(plataforma.get(lona).getInferior()[0],plataforma.get(2).getInferior()[1]+40,"martillo");
			
		}else if(k==1) {
			cualquiera= new Cereza(plataforma.get(lona).getInferior()[0],plataforma.get(3).getInferior()[1]+40,"cereza"); 
		     
		}else if(k==2) {
			cualquiera= new Corazon(plataforma.get(lona).getInferior()[0],plataforma.get(3).getInferior()[1]+40,"corazon");
		   
		}else if(k==3){
			cualquiera= new Hongo(plataforma.get(lona).getInferior()[0],plataforma.get(3).getInferior()[1]+40,"hongo");
		  
		}else if(k==4){
			cualquiera= new Manzana(plataforma.get(lona).getInferior()[0],plataforma.get(3).getInferior()[1]+40,"manzana");
		  
		}
		return cualquiera;
	}
	
	/*
	 * Crea las escaleras tanto de los Jugadores como de los barriles y guarda sus respectivas coordenadas
	 */
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
	
	/*
	 * Crea el o los Jugadores 
	 */
	private void prepareJugadores() {
		Jugador mario= new Jugador(2,550,"MarioDerecha"); 
		Jugadores.add(mario);
		
	}
	
	/*
	 * retorna la cantidad de elementos que hay
	 * @return
	 */
	public int numElementos() {
		return elementos.size();
	}
	
	/*
	 * Crea los barriles de una forma aleatoria
	 */
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
				BarrilRojo gota= new BarrilRojo(80,171); 
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
	
	/*
	 * Crea las plataformas y  guarda sus respectivas coordenadas
	 */
	public void preparePlataformas() throws IOException{
		Plataforma pINf= new Plataforma (new int[]{294,121},new int[]{453,121});
		Plataforma  p0=new Plataforma (new int[]{0,191},new int[]{700,191});
		Plataforma  p1=new Plataforma (new int[]{30,249},new int[]{744,249});
		Plataforma  p2 =new Plataforma (new int[]{0,329},new int[]{693,329});;
		Plataforma p3=new Plataforma (new int[]{30,410},new int[]{724,410});;
		Plataforma  p4=new Plataforma (new int[]{0,490},new int[]{693,490});;
		Plataforma  p5=new Plataforma (new int[]{0,570},new int[]{745,570});;
		plataforma = new ArrayList<Plataforma>();
		plataforma.add(pINf);
		plataforma.add(p0);
		plataforma.add(p1);
		plataforma.add(p2);
		plataforma.add(p3);
		plataforma.add(p4);
		plataforma.add(p5);	
	}

	/*
	 * Retorna si es juego ya se acabo o no
	 * @returns
	 */
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
    
    /*
     * Retorna una barril en una posicion dada de su Arraylist
     * @return
     */
    public Elemento getBarril(int i){
    	return barriles.get(i);
	}
    
    /*
     * Retorna una Jugador en una posicion dada de su Arraylist
     * @return
     */
    public Jugador getJugador(int i){
		return Jugadores.get(i);
	}
    
  //Move el Jugador
    
    /*
     * Realiza el salto para el o los Jugadores
     */
  	public void JugadorNUp(int n){ 	
  		validarElementos(Jugadores.get(n)) ;
  		if(System.nanoTime() -time > time_x_ronda) martillando=false;
  		if(jumping && Jugadores.get(n).validarSalto() && !Escalando && !enAire) {
  			
  			
  			Jugadores.get(n).moveUp();
  			
  			jumping=Jugadores.get(n).validarSalto();  			
  		}if(enAire) {
  			jumping=false;
  		}if(Escalando) {
  			jumping=false;
  			enAire=false;
  		}
  		}
  	
  	/*
  	 * Realiza la caída del Jugador ya sea de un salto o al bajar de una plataforma
  	 */
  	public void JugadorNDown(int n){
  		validarElementos(Jugadores.get(n)) ;
  		validarJugador(Jugadores.get(n));
  		validarPrincesa(Jugadores.get(n));
  		if(System.nanoTime() -time > time_x_ronda) martillando=false;
  		if(!EstaEnLona(Jugadores.get(n)) && !jumping && !Escalando ){
  			
  			Jugadores.get(n).moveDown(); 
  			enAire=true;
  		}else if(Jugadores.get(n).validarSalto()  && !EstaEnLona(Jugadores.get(n))) {
  			jumping=Jugadores.get(n).validarSalto();
  			enAire= false;
  		}
  		
  		}

	/*
	 * Realiza el movimiento hacia la izquierda del Jugador o los Jugadores 	
	 */
  	public void JugadorNLeft(int n){
  		validarElementos(Jugadores.get(n)) ;
  		validarJugador(Jugadores.get(n));
  		validarPrincesa(Jugadores.get(n));
  		if(System.nanoTime() -time > time_x_ronda) martillando=false;
  		if(Jugadores.get(0).getX()>2) {
  			
  			if(!termino) {
  				JugadorNEscalar(n);
  				JugadorNDown(n);
  				if(!martillando) {
  					if(!invertir) {
  	  	  	  	  		Jugadores.get(n).moveLeft();
  	  	  	  		}else {
  	  	  	  			Jugadores.get(n).moveRight();
  	  	  	  		}
  					
  				}else {
  					Jugadores.get(n).moveMartilloLeft();
  				}
  	  			
  	  		}
  	  			
  		}
  		
  		
  		
  		}
  	
  	/*
  	 * Hace que el Jugador pueda subir de una plataforma a otra mediante la escalera
  	 */
  	public void JugadorNEscalar(int n){ 
  		validarElementos(Jugadores.get(n)) ;
  		validarJugador(Jugadores.get(n));
  		validarPrincesa(Jugadores.get(n));
		

  		for(int i=0;i<escaleras.size();i++) {
  			if(escaleras.get(i).getRoot().equals("EscaleraMario") && Jugadores.get(n).getX()>=escaleras.get(i).getPosicionesX()[0] && Jugadores.get(n).getX()<=escaleras.get(i).getPosicionesX()[1] && Jugadores.get(n).getY()<=escaleras.get(i).getPosicionesY()[0] && Jugadores.get(n).getY()>=escaleras.get(i).getPosicionesY()[1] ) {
  				Escalando=true;
  				Jugadores.get(n).setSubir();
  				jumping=false;
  				enAire=false;
  				Jugadores.get(n).moveUp();
  				break;
  			
  			}
  			
  			else if(i==escaleras.size()-1 && (!escaleras.get(i).getRoot().equals("EscaleraMario") || Jugadores.get(n).getX()<escaleras.get(i).getPosicionesX()[0] || Jugadores.get(n).getX()>escaleras.get(i).getPosicionesX()[1] ||  Jugadores.get(n).getY()>=escaleras.get(i).getPosicionesY()[0] || Jugadores.get(n).getY()<=escaleras.get(i).getPosicionesY()[1] )) {
  				
  				Escalando=false;
  				enAire=true;
  				break;
  			}
  		}
		}
  	
  	/*
  	 * Validad si el Jugador ya uso el o los elementos de la partida
  	 */
  	private void validarElementos(Jugador Jugador) {
  		for(int i=0;i<elementos.size();i++) {
  			if(!elementos.get(i).isVisible()) {
  				elementos.remove(i);
  				break;
  			}
  			else if(elementos.get(i).getX()>=Jugador.getX() && elementos.get(i).getX()<=Jugador.getX()+20 && elementos.get(i).getY()>=Jugador.getY() && elementos.get(i).getY()<=Jugador.getY()+20) {
  				
  				activeElemento(elementos.get(i));
  				elementos.get(i).setVisible(false);
  				
  				break;
  			}
  			else if((elementos.get(i).getX()+20>=Jugador.getX() && elementos.get(i).getX()+20<=Jugador.getX()+20 && elementos.get(i).getY()>=Jugador.getY() && elementos.get(i).getY()<=Jugador.getY()+20)) {
  				activeElemento(elementos.get(i));
  				elementos.get(i).setVisible(false);
  				
  				break;
  			}
  			else if((elementos.get(i).getX()<=Jugador.getX()+20 && elementos.get(i).getX()+20>=Jugador.getX()+20 && elementos.get(i).getY()>=Jugador.getY() && elementos.get(i).getY()<=Jugador.getY()+20)) {
  				activeElemento(elementos.get(i));
  				elementos.get(i).setVisible(false);
  				
  				break;
  			}
  			
  		}
  	}
  	
  	/*
  	 * Valida si el Jugador hace contacto con los barriles 
  	 */
  	private void validarJugador(Jugador Jugador) {
  		for(int i=0;i<barriles.size();i++) {
  			if(!barriles.get(i).isVisible()) {
  				barriles.remove(i);
  				break;
  			}
  			if(barriles.get(i).getX()>=Jugador.getX() && barriles.get(i).getX()<=Jugador.getX()+20 && barriles.get(i).getY()>=Jugador.getY() && barriles.get(i).getY()<=Jugador.getY()+20) {
  				activeBarril(barriles.get(i));
  				
  				break;
  			}
  			else if((barriles.get(i).getX()+20>=Jugador.getX() && barriles.get(i).getX()+20<=Jugador.getX()+20 && barriles.get(i).getY()>=Jugador.getY() && barriles.get(i).getY()<=Jugador.getY()+20)) {
  				activeBarril(barriles.get(i));
  				
  				break;
  			}
  			else if((barriles.get(i).getX()<=Jugador.getX()+20 && barriles.get(i).getX()+20>=Jugador.getX()+20 && barriles.get(i).getY()>=Jugador.getY() && barriles.get(i).getY()<=Jugador.getY()+20)) {	
  				activeBarril(barriles.get(i));
  				
  				break;
  			}
  			
  		}
  	}
  	
  	/*
  	 * Valida si el Jugador llego a la posicion de la princesa y termina el juego
  	 */
  	private void validarPrincesa(Jugador Jugador) {
  		
  			if(princesa.getX()>=Jugador.getX() && princesa.getX()<=Jugador.getX()+20 && princesa.getY()>=Jugador.getY() && princesa.getY()<=Jugador.getY()+20) {
  				termino=true;
  				Jugadores.get(0).gano=1;
  			}
  			else if((princesa.getX()+20>=Jugador.getX() && princesa.getX()+20<=Jugador.getX()+20 && princesa.getY()>=Jugador.getY() && princesa.getY()<=Jugador.getY()+20)) {
  				termino=true;
  				Jugadores.get(0).gano=1;
  			}
  			else if((princesa.getX()<=Jugador.getX()+20 && princesa.getX()+20>=Jugador.getX()+20 && princesa.getY()>=Jugador.getY() && princesa.getY()<=Jugador.getY()+20)) {	
  				termino=true;
  				Jugadores.get(0).gano=1;
  			}
  			
  		
  	}
  	
  	/*
  	 * Reinicia el juego tanto para el Jugador como para los barriles
  	 */
  	private void reinicieJuego() {
		Jugadores.get(0).inicial();
		for(int i=0;i<barriles.size();i++) {
			barriles.get(i).setVisible(false);
		}
		termino=false;
	}
  	
  	/*
  	 * Valida que barriles quitan o dan una vida o puntos
  	 */
	private void activeBarril(Barril barril) {
		if(martillando) {
			if(barril instanceof BarrilVerde) {
				Jugador.sumeVida(1);
				barril.setVisible(false);
			}else {
				Jugador.sumeScore(100);
				barril.setVisible(false);
			}
			
		}
		else {
			
				Jugador.setRoot("MarioMuerto");
				Jugador.sumeVida(-1);
				
				if(Jugador.vidas==0) {
					termino=true;
				}else {
					invertir=false;
					reinicieJuego();	
				}
			
			
		}
		
	}
	
	/*
	 * Valida que hace cada sorpresa
	 */
	private void activeElemento(Elemento elemento) {
		if(elemento instanceof Corazon) {
			Jugador.sumeVida(1);
		}else if(elemento instanceof Cereza) {
			Jugador.sumeScore(10);
		}else if(elemento instanceof Manzana) {
			Jugador.sumeScore(5);
		}else if(elemento instanceof Hongo) {
			if(invertir) {
				invertir=false;
			}else {
			invertir=true;
			}
			
		}else if(elemento instanceof Martillo) {
			martillando=true;
			time = System.nanoTime();
			time_x_ronda = segundo*30;
		}
		
	}
	
	/*
	 * Valida el puntaje del Jugador durante las rondas de la partida
	 */
  	public int[] puntajes() {
  		return new int[] {Jugador.vidas,Jugador.score};
  	}
  	
  	/*
  	 * Valida si el Jugador esta o no en una plataforma
  	 */
	private boolean EstaEnLona(Jugador Jugador) {
  		
  		boolean bandera=false;
  		for(int i =0 ;i<plataforma.size();i++) {
    		if( Jugador.getY()==plataforma.get(i).getInferior()[1]-20 && Jugador.getX()>=plataforma.get(i).getInferior()[0] && Jugador.getX() <=plataforma.get(i).getSuperior()[0] ) {
    			bandera=true;
    			enAire=false;
    			jumping=false;

    			break;
    		}
    		else if(Jugador.getY()==plataforma.get(i).getInferior()[1]-20 && ( Jugador.getX()<plataforma.get(i).getInferior()[0] || Jugador.getX() >plataforma.get(i).getSuperior()[0] )){
    			bandera=false;
    			jumping=false;
    			break;
    		}else if(Jugador.getY()!=plataforma.get(i).getInferior()[1]-20 && i==-1 && !jumping ){
    			bandera=false;
    			break;
    		}
    	}
		return bandera;
  	}
	
	/*
	 * Realiza el movimiento hacia la derecha del Jugador o los Jugadores 	
	 */
	public void JugadorNRight(int n){
		validarElementos(Jugadores.get(n)) ;
		validarJugador(Jugadores.get(n));
		if(System.nanoTime() -time > time_x_ronda) martillando=false;
		if(Jugadores.get(0).getX()<710 ) {
			if(!termino) {
				
	  	  		JugadorNEscalar(n);
	  	  		JugadorNDown(n);
	  	  		if(!martillando) {
					if(!invertir) {
						Jugadores.get(n).moveRight();
					}else {
						Jugadores.get(n).moveLeft();
					}
				}else {
					Jugadores.get(n).moveMartilloRight();
				} 
			}
		}
		}

	public void Jugadornormal(int n) {Jugadores.get(n).moveNormal();	
	}
  	
  	/*
  	 * Valida si la ronda ya termino
  	 */
    public boolean isRondaFinished(){
		boolean ans = true;
		for(Barril p: barriles){
			ans &= !p.isVisible();
		}
		return ans;
	}
    
    /*
     * Valida el movimiento respectivo de cada barril
     */
    public void mover(int x){
    	if(barriles.get(x) instanceof BarrilNormal || barriles.get(x) instanceof BarrilVerde) {
    		moverBarrilNormal(x);
    		
    	}else if(barriles.get(x) instanceof BarrilAzul) {
    		moverBarriAzul(x);
    	}else if(barriles.get(x) instanceof BarrilRojo) {
    		barriles.get(x).setY(barriles.get(x).getY()+1);
    	}
    	
    	 
    	 
	}
    
    /*
     * Realiza el movimiento del barril azul donde puede bajar por cualquier escalera del mapa
     */
    private void moverBarriAzul(int x) {
    	for(int i =1 ;i<plataforma.size();i++) {
    		
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
    
    /*
     * Realiza el movimiento de los barriles cuyo movimiento es bajar sin tener encuenta las escaleras
     */
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
    
    /*
     * Validad si los barriles estan en una escalera
     */
    private boolean barrilEnEscalera(Barril barril) {
    	boolean f=false;
    	for(int i=0;i<escaleras.size();i++) {
    		
  			if(barril.getX()>=escaleras.get(i).getPosicionesX()[0] && barril.getX()<=escaleras.get(i).getPosicionesX()[1] && barril.getY()<=escaleras.get(i).getPosicionesY()[0]-25 && barril.getY()>=escaleras.get(i).getPosicionesY()[1]-25) {
  				if(barril.getX()==escaleras.get(i).getPosicionesX()[0]) {
  					
  					f=true;
  				}
  				
  				break;
  			}
  			
  		}
  		return f;
    	
    }
	
	public int getJugadores() {
		
		return 1;
	}
	
	/*
	 * Valida si el Jugador esta saltando
	 */
	public void jummping(int i,int gravity) {
		
		if(!Jugadores.get(i).validarSalto() ) {
		
			Jugadores.get(i).jummping(gravity);
			jumping=true;           
			enAire=false;
		}
		
	}
	
	/*
	 * Retorna la cantidad de barriles
	 * @return
	 */
	public int sizeBarriles() {
		
		return barriles.size();
	}
	
	/*
	 * Retorna un elemento de una posicion dada
	 * @return
	 */
	public Elemento getElemento(int i) {
	
		return elementos.get(i);
	}
	
	/*
	 * Retorna el donkeykong
	 * @return
	 */
	public Personaje getDonkey() {
		return Donkey;
	}
	
	/*
	 * Valida si el Jugador gano o no
	 */
	public String Gano() {
		return Jugadores.get(0).gano();
	}
	public Personaje getPrincesa() {
		// TODO Auto-generated method stub
		return princesa;
	}
	
  
}
