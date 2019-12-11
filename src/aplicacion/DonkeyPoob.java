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
	private static DonkeyPoob juego = null;
	public ArrayList<Barril> barriles=new ArrayList<Barril>();
	public ArrayList<Elemento> elementos=new ArrayList<Elemento>();
	private boolean visible=true;
	public static final int barrilesRonda = 2;
	public boolean invertir=false;
	public static final long segundo = 1000000000;
	private long time;
	private long time_x_ronda;
	
	public static ArrayList<Jugador> Jugadores=new ArrayList<Jugador>();
	private  ArrayList<Escalera> escaleras=new ArrayList<Escalera>();
	//donkeykong
	public Personaje Donkey,princesa;
	//acabo
	public boolean termino;
	//Jugador
	
	
	
	private boolean[] elementosDisponibles;
	private boolean[] barrilesDisponibles;
	private boolean[] aspectoMario;
	public int players;
	
	public DonkeyPoob(int escenario,boolean[] barriles,boolean[] elementos, boolean[] jugadores,int players) throws IOException{
		this.elementosDisponibles=elementos;
		this.barrilesDisponibles=barriles;
		this.aspectoMario=jugadores;
		this.players=players;
		prepareDonkeyPrincesa();
		preparePlataformas();
		prepareBarriles();
		prepareJugadores();
		prepareEscaleras();
		int r=0,l=0;
		for(int i=0;i< elementosDisponibles.length;i++) {
			if(!elementosDisponibles[i]) r++;
			}
		
		if(r!=elementosDisponibles.length) {
			int k=(int) (Math.random()*3+1);
			for(int i=0;i<k;i++) {
			
				prepareElementos();
			}
		}
		if(escenario==0)prepareEscaleras();
		if(escenario==1)prepareEscalerasUno();
		if(escenario==2)prepareEscalerasDos();
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
	public void prepareElementos() {
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
		Elemento cualquiera = null;
		while(cualquiera ==null ) {
			int lona= (int) Math.random()*plataforma.size()+2;
			
			int k=(int) (Math.random()*5);
			System.out.println(k+" "+lona);
			
			if(k==0 && elementosDisponibles[2]) {
				 cualquiera=new Martillo(plataforma.get(lona).getInferior()[0],plataforma.get(2).getInferior()[1]+40,"martillo");
				
			}else if(k==1 && elementosDisponibles[3]) {
				cualquiera= new Cereza(plataforma.get(lona).getInferior()[0],plataforma.get(3).getInferior()[1]+40,"cereza"); 
			     
			}else if(k==2  && elementosDisponibles[4]) {
				cualquiera= new Corazon(plataforma.get(lona).getInferior()[0],plataforma.get(3).getInferior()[1]+40,"corazon");
			   
			}else if(k==3  && elementosDisponibles[0]){
				cualquiera= new Hongo(plataforma.get(lona).getInferior()[0],plataforma.get(3).getInferior()[1]+40,"hongo");
			  
			}else if(k==4  && elementosDisponibles[1]){
				cualquiera= new Manzana(plataforma.get(lona).getInferior()[0],plataforma.get(3).getInferior()[1]+40,"manzana");
			}
			
		}
		return cualquiera;
		
	}
	
	/*
	 * Crea las escaleras tanto de los Jugadores como de los barriles y guarda sus respectivas coordenadas
	 */
	private void prepareEscaleras() {
		EscaleraMario nuevaUnos =new EscaleraMario(338,364,570,470);
		EscaleraBarril nuevas =new EscaleraBarril(510,539,570,470);
		EscaleraMario nuevaDoss =new EscaleraMario(664,691,491,387);
		EscaleraBarril nuevaTres =new EscaleraBarril(306,334,490,387);
		EscaleraMario nuevaCuatro =new EscaleraMario(94,122,490,387);
		EscaleraBarril nuevaCinco =new EscaleraBarril(149,171,413,309);
		EscaleraMario nuevaSeis =new EscaleraMario(602,629,411,309);
		EscaleraBarril nuevaSiete =new EscaleraBarril(579,609,329,228);
		EscaleraMario nuevaOcho =new EscaleraMario(76,104,330,228);
		EscaleraMario nuevaNueve =new EscaleraMario(599,623,251,168);
		EscaleraMario nuevaDiez =new EscaleraMario(429,453,189,103);
		escaleras = new ArrayList<Escalera>();
		escaleras.add(nuevaUnos);
		escaleras.add(nuevas);
		escaleras.add(nuevaDoss);
		escaleras.add(nuevaTres);
		escaleras.add(nuevaCuatro);
		escaleras.add(nuevaCinco);
		escaleras.add(nuevaSeis);
		escaleras.add(nuevaSiete);
		escaleras.add(nuevaOcho);
		escaleras.add(nuevaNueve);
		escaleras.add(nuevaDiez);

	}
	
	/*
	 * Crea el o los Jugadores 
	 */
	private void prepareJugadores() {
		if(players==1) {
			if(aspectoMario[0]) {
				Jugador mario= new Jugador(2,550,"Rojo"); 
				Jugadores.add(mario);
			}else if(aspectoMario[1]){
				Jugador mario= new Jugador(2,550,"Verde"); 
				Jugadores.add(mario);
			}else {
				Jugador mario= new Jugador(2,550,"Rojo"); 
				Jugadores.add(mario);
			}
		}else {

					Jugador mario= new Jugador(40,550,"Rojo"); 
					Jugador Luigi= new Jugador(2,550,"Verde"); 
					Jugadores.add(mario);
					Jugadores.add(Luigi);
				
		}
		
		
		
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
		}
		if(UnbarrilDisponible()) {
		 if(barriles.get(barriles.size()-1).getY()>191){
		
			
			Donkey.setRoot("DonkeyIzquierda");
			Barril gota=null;
			while(gota==null) {
				int k=(int) (Math.random()*4);
				if(k==0 && barrilesDisponibles[1]) {
					gota= new BarrilNormal(80,171); 
			        barriles.add(gota);
				}else if(k==1  && barrilesDisponibles[0]) {
					 gota= new BarrilAzul(80,171); 
			        barriles.add(gota);
				}else if(k==2  && barrilesDisponibles[3]) {
					 gota= new BarrilRojo(80,171); 
			        barriles.add(gota);
				}else if(k==3  && barrilesDisponibles[2]){
					 gota= new BarrilVerde(80,171); 
			        barriles.add(gota);
				}
			}
			
			
		}else {
			if(Donkey.getRoot().equals("DonkeyIzquierda")) {
				Donkey.setRoot("DonkeyDerecha");
			}
			
		}
		}
		
	}
	private void prepareEscalerasUno() {
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
	private boolean UnbarrilDisponible() {
		boolean todos=true;
		int r=0;
		for(int i=0;i<barrilesDisponibles.length;i++) {
			if(!barrilesDisponibles[i]) {
				r++;
			}
		}
		if(r==barrilesDisponibles.length) todos=false;
		return todos;
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
    public static Jugador getJugador(int i){
		return Jugadores.get(i);
	}
    
  //Move el Jugador
    
    /*
     * Realiza el salto para el o los Jugadores
     */
  	public void JugadorNUp(int n){ 	
  		validarElementos(Jugadores.get(n)) ;
  		if(System.nanoTime() -time > time_x_ronda) Jugadores.get(n).martillando=false;
  		if(Jugadores.get(n).jumping && Jugadores.get(n).validarSalto() && !Jugadores.get(n).Escalando && !Jugadores.get(n).enAire) {
  			
  			
  			Jugadores.get(n).moveUp();
  			
  			Jugadores.get(n).jumping=Jugadores.get(n).validarSalto();  			
  		}if(Jugadores.get(n).enAire) {
  			Jugadores.get(n).jumping=false;
  		}if(Jugadores.get(n).Escalando) {
  			Jugadores.get(n).jumping=false;
  			Jugadores.get(n).enAire=false;
  		}
  		}
  	
  	/*
  	 * Realiza la caída del Jugador ya sea de un salto o al bajar de una plataforma
  	 */
  	public void JugadorNDown(int n){
  		validarElementos(Jugadores.get(n)) ;
  		validarJugador(Jugadores.get(n));
  		validarPrincesa(Jugadores.get(n));
  		if(System.nanoTime() -time > time_x_ronda) Jugadores.get(n).martillando=false;
  		if(!EstaEnLona(Jugadores.get(n)) && !Jugadores.get(n).jumping && !Jugadores.get(n).Escalando ){
  			
  			Jugadores.get(n).moveDown(); 
  			Jugadores.get(n).enAire=true;
  		}else if(Jugadores.get(n).validarSalto()  && !EstaEnLona(Jugadores.get(n))) {
  			Jugadores.get(n).jumping=Jugadores.get(n).validarSalto();
  			Jugadores.get(n).enAire= false;
  		}
  		
  		}

	/*
	 * Realiza el movimiento hacia la izquierda del Jugador o los Jugadores 	
	 */
  	public void JugadorNLeft(int n){
  		validarElementos(Jugadores.get(n)) ;
  		validarJugador(Jugadores.get(n));
  		validarPrincesa(Jugadores.get(n));
  		if(System.nanoTime() -time > time_x_ronda) Jugadores.get(n).martillando=false;
  		if(Jugadores.get(n).getX()>2) {
  			
  			if(!termino) {
  				JugadorNEscalar(n);
  				JugadorNDown(n);
  				if(!Jugadores.get(n).martillando) {
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
  				Jugadores.get(n).Escalando=true;
  				Jugadores.get(n).setSubir();
  				Jugadores.get(n).jumping=false;
  				Jugadores.get(n).enAire=false;
  				Jugadores.get(n).moveUp();
  				break;
  			
  			}
  			
  			else if(i==escaleras.size()-1 && (!escaleras.get(i).getRoot().equals("EscaleraMario") || Jugadores.get(n).getX()<escaleras.get(i).getPosicionesX()[0] || Jugadores.get(n).getX()>escaleras.get(i).getPosicionesX()[1] ||  Jugadores.get(n).getY()>=escaleras.get(i).getPosicionesY()[0] || Jugadores.get(n).getY()<=escaleras.get(i).getPosicionesY()[1] )) {
  				
  				Jugadores.get(n).Escalando=false;
  				Jugadores.get(n).enAire=true;
  				break;
  			}
  		}
		}
  	
  	/*
  	 * Validad si el Jugador ya uso el o los elementos de la partida
  	 */
  	public void validarElementos(Jugador Jugador) {
  		for(int i=0;i<elementos.size();i++) {
  			if(!elementos.get(i).isVisible()) {
  				elementos.remove(i);
  				break;
  			}
  			else if(elementos.get(i).getX()>=Jugador.getX() && elementos.get(i).getX()<=Jugador.getX()+20 && elementos.get(i).getY()>=Jugador.getY() && elementos.get(i).getY()<=Jugador.getY()+20) {
  				
  				activeElemento(elementos.get(i),Jugador);
  				elementos.get(i).setVisible(false);
  				
  				break;
  			}
  			else if((elementos.get(i).getX()+20>=Jugador.getX() && elementos.get(i).getX()+20<=Jugador.getX()+20 && elementos.get(i).getY()>=Jugador.getY() && elementos.get(i).getY()<=Jugador.getY()+20)) {
  				activeElemento(elementos.get(i),Jugador);
  				elementos.get(i).setVisible(false);
  				
  				break;
  			}
  			else if((elementos.get(i).getX()<=Jugador.getX()+20 && elementos.get(i).getX()+20>=Jugador.getX()+20 && elementos.get(i).getY()>=Jugador.getY() && elementos.get(i).getY()<=Jugador.getY()+20)) {
  				activeElemento(elementos.get(i),Jugador);
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
  				activeBarril(barriles.get(i),Jugador);
  				
  				break;
  			}
  			else if((barriles.get(i).getX()+20>=Jugador.getX() && barriles.get(i).getX()+20<=Jugador.getX()+20 && barriles.get(i).getY()>=Jugador.getY() && barriles.get(i).getY()<=Jugador.getY()+20)) {
  				activeBarril(barriles.get(i),Jugador);
  				
  				break;
  			}
  			else if((barriles.get(i).getX()<=Jugador.getX()+20 && barriles.get(i).getX()+20>=Jugador.getX()+20 && barriles.get(i).getY()>=Jugador.getY() && barriles.get(i).getY()<=Jugador.getY()+20)) {	
  				activeBarril(barriles.get(i),Jugador);
  				
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
  				Jugador.gano=1;
  			}
  			else if((princesa.getX()+20>=Jugador.getX() && princesa.getX()+20<=Jugador.getX()+20 && princesa.getY()>=Jugador.getY() && princesa.getY()<=Jugador.getY()+20)) {
  				termino=true;
  				Jugador.gano=1;
  			}
  			else if((princesa.getX()<=Jugador.getX()+20 && princesa.getX()+20>=Jugador.getX()+20 && princesa.getY()>=Jugador.getY() && princesa.getY()<=Jugador.getY()+20)) {	
  				termino=true;
  				Jugador.gano=1;
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
	private void activeBarril(Barril barril,Jugador jugador) {
		if(jugador.martillando) {
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
	private void activeElemento(Elemento elemento,Jugador Jugador) {
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
			Jugador.martillando=true;
			time = System.nanoTime();
			time_x_ronda = segundo*30;
		}
		
	}
	
	/*
	 * Valida el puntaje del Jugador durante las rondas de la partida
	 */
  	public int[] puntajes() {
  		int[] lista;
  		if(Jugadores.size()>=2) {
  			lista=new int[] {Jugadores.get(0).vidas,Jugadores.get(0).score,Jugadores.get(1).vidas,Jugadores.get(1).score};
  		}else {
  			lista= new int[] {Jugadores.get(0).vidas,Jugadores.get(0).score};
  		}
  		return lista;
  	}
  	
  	/*
  	 * Valida si el Jugador esta o no en una plataforma
  	 */
	private boolean EstaEnLona(Jugador Jugador) {
  		
  		boolean bandera=false;
  		for(int i =0 ;i<plataforma.size();i++) {
    		if( Jugador.getY()==plataforma.get(i).getInferior()[1]-20 && Jugador.getX()>=plataforma.get(i).getInferior()[0] && Jugador.getX() <=plataforma.get(i).getSuperior()[0] ) {
    			bandera=true;
    			Jugador.enAire=false;
    			Jugador.jumping=false;

    			break;
    		}
    		else if(Jugador.getY()==plataforma.get(i).getInferior()[1]-20 && ( Jugador.getX()<plataforma.get(i).getInferior()[0] || Jugador.getX() >plataforma.get(i).getSuperior()[0] )){
    			bandera=false;
    			Jugador.jumping=false;
    			break;
    		}else if(Jugador.getY()!=plataforma.get(i).getInferior()[1]-20 && i==-1 && !Jugador.jumping ){
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
		if(System.nanoTime() -time > time_x_ronda) Jugadores.get(n).martillando=false;
		if(Jugadores.get(0).getX()<710 ) {
			if(!termino) {
				
	  	  		JugadorNEscalar(n);
	  	  		JugadorNDown(n);
	  	  		if(!Jugadores.get(n).martillando) {
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
    private void prepareEscalerasDos() {
    	EscaleraMario nuevaUno =new EscaleraMario(519,546,570,471);
		EscaleraMario nuevaDos =new EscaleraMario(632,659,489,389);
		EscaleraBarril nuevaTres =new EscaleraBarril(305,333,489,389);
		EscaleraMario nuevaCuatro =new EscaleraMario(95,122,489,389);
		EscaleraBarril nuevaCinco =new EscaleraBarril(145,161,410,310);
		EscaleraMario nuevaSeis =new EscaleraMario(379,407,410,310);
		EscaleraMario nuevaSiete =new EscaleraMario(580,612,328,228);
		EscaleraMario nuevaOcho =new EscaleraMario(76,103,328,228);
		EscaleraMario nuevaNueve =new EscaleraMario(350,375,251,169);
		EscaleraMario nuevaDiez =new EscaleraMario(430,454,190,427);
		
		escaleras = new ArrayList<Escalera>();
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
			Jugadores.get(i).jumping=true;           
			Jugadores.get(i).enAire=false;
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
	public void barrilesParaJugar(int[] barriles)throws DonkeyPoobException {
		int[] barrilesNivel = convertir(barriles);
		if (barrilesNivel.length == 0) {
			throw new DonkeyPoobException(DonkeyPoobException.SIN_BARRIL);
		
		}
	}
	public void sorpresasParaJugar(int[] sorpresas)throws DonkeyPoobException {
		int[] sorpresasNivel = convertir(sorpresas);
		if (sorpresasNivel.length == 0) {
			throw new DonkeyPoobException(DonkeyPoobException.SIN_SORPRESAS);
		
		}
	}
	public int[] convertir(int[] objetos) {
		int cantidad = 0;
		for(int i  = 0; i < objetos.length; i++) {
			if (objetos[i]==1) {
				cantidad++;
			}
		}
		int[] barrilesNivel = new int[cantidad];
		int posicion = 0;
		for(int i  = 0; i < objetos.length; i++) {
			if (objetos[i]==1) {
				objetos[posicion]= i+1;
				posicion++;
			}
		}
		return barrilesNivel;
	}
	public void salvar (File archivo) throws DonkeyPoobException {
		if (juego == null) {
			//throw new DonkeyPoobException(DonkeyPoobException.SIN_JUEGO);
		}
		try{
			ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream(archivo));
			out.writeObject(juego);
			out.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
  
}
