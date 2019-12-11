package aplicacion;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import presentacion.DonkeyPoobGUI;

class DonkeyPoobTest {
	private boolean[] elementos=new boolean[]{false,false,false,false,false,false};
	private boolean[] barriles=new boolean[]{false,false,false,false};
	private boolean[] aspectoMario=new boolean[]{false,false};
	public int players;

	@Test
	void DeberiaCrearTablero()  {
		try {
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void DeberiaCrearPlataformas()  {
		try {
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			assertTrue(juego.plataforma.size() >0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void DeberiaCrearBarriles()  {
		try {
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			assertTrue(juego.barriles.size() >0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void DeberiaCrearJugador() {
		try {
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			assertTrue(juego.Jugadores.size() >0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void DeberiaMoverJugadorIzquierda() {
		try {
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			
			//juego.JugadorNRight(0);
			juego.JugadorNLeft(0);
			assertTrue(juego.Jugadores.get(0).x==juego.Jugadores.get(0).xInicial);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void DeberiaMoverJugadorDerecha() {
		try {
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			juego.JugadorNRight(0);
			assertTrue(juego.Jugadores.get(0).x==juego.Jugadores.get(0).xInicial+5);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void NoDeberiaJugadorMoverseArriba() {
		try {
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			juego.JugadorNUp(0);
			assertTrue(juego.Jugadores.get(0).y==juego.Jugadores.get(0).yInicial);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void NoDeberiaJugadorMoverseAbajo() {
		try {
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			juego.JugadorNDown(0);
			assertTrue(juego.Jugadores.get(0).y==juego.Jugadores.get(0).yInicial);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void DeberiaMoverBarriles()  {
		try {
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			juego.mover(0);
			assertTrue(juego.barriles.get(0).getX()==juego.barriles.get(0).xInicial+1);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void DeberiaSaltarJugador()  {
		try {
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			juego.jummping(0,5);
			
			juego.JugadorNUp(0);
			assertTrue(juego.Jugadores.get(0).y==juego.Jugadores.get(0).yInicial-1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void DeberiaJugadorSaltarHastaLaLonaYBajar()  {
		try {
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			juego.jummping(0,5);
			for(int i=1;i<46;i++) {
				juego.JugadorNUp(0);
			}
			
			assertTrue(juego.Jugadores.get(0).y!=juego.Jugadores.get(0).yInicial-44);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void NoDeberiaBajarSiEstaSaltando()  {
		try {
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			juego.jummping(0,5);
			juego.JugadorNUp(0);
			juego.JugadorNDown(0);
			assertTrue(juego.Jugadores.get(0).y!=juego.Jugadores.get(0).yInicial-1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void DeberiaCrearSorpresas()  {
		try {
			elementos[0]=true;
			elementos[1]=true;
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			assertTrue(juego.elementos.size() >0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void DeberiaPrepareBarrilRojo()  {
		try {
			barriles[barriles.length-1]=true;
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			for(int i=0;i<700;i++) {
				juego.mover(0); 
			}
			juego.prepareBarriles();
			
			assertTrue(juego.barriles.size()>=2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	void DeberiaPrepareBarrilRojoDosJugadores()  {
		try {
			barriles[barriles.length-1]=true;
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 2);
			for(int i=0;i<700;i++) {
				juego.mover(0); 
			}
			juego.prepareBarriles();
			
			assertTrue(juego.barriles.size()>=2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	void DeberiaPrepareBarrilVerde()  {
		try {
			barriles[barriles.length-2]=true;
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			for(int i=0;i<700;i++) {
				juego.mover(0); 
			}
			juego.prepareBarriles();
			
			assertTrue(juego.barriles.size()>=2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	void DeberiaPrepareBarrilVerdeDosJugadores()  {
		try {
			barriles[barriles.length-2]=true;
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 2);
			for(int i=0;i<700;i++) {
				juego.mover(0); 
			}
			juego.prepareBarriles();
			
			assertTrue(juego.barriles.size()>=2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	void DeberiaPrepareBarrilAmarillo()  {
		try {
			barriles[barriles.length-2]=true;
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			for(int i=0;i<700;i++) {
				juego.mover(0); 
			}
			juego.prepareBarriles();
			
			assertTrue(juego.barriles.size()>=2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	void DeberiaPrepareBarrilAmarilloDosJugadores()  {
		try {
			barriles[barriles.length-2]=true;
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 2);
			for(int i=0;i<700;i++) {
				juego.mover(0); 
			}
			juego.prepareBarriles();
			
			assertTrue(juego.barriles.size()>=2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	void DeberiaPrepareBarrilAzul()  {
		try {
			barriles[0]=true;
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			for(int i=0;i<700;i++) {
				juego.mover(0); 
			}
			juego.prepareBarriles();
			
			assertTrue(juego.barriles.size()>=2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	@Test
	void DeberiaPrepareBarrilAzuDosJugadores()  {
		try {
			barriles[0]=true;
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 2);
			for(int i=0;i<700;i++) {
				juego.mover(0); 
			}
			juego.prepareBarriles();
			
			assertTrue(juego.barriles.size()>=2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	//lomio
	@Test
	void DeberiaMarioMuereBarrilAmarillo()   {
		
			barriles[1]=true;
			DonkeyPoob juego;
			try {
				juego = new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			
			juego.JugadorNRight(0);
			
			
			while(juego.Jugadores.get(0).getY()!=juego.barriles.get(0).getY()) {
				juego.mover(0);
			}
			for (int i=0;i<731;i++){
				juego.mover(0);
			} 
			juego.Jugadores.get(0).setY(juego.barriles.get(0).getY());
			juego.validarJugador(juego.Jugadores.get(0));
			barriles[1]=false;
			assertTrue(juego.Jugadores.get(0).vidas==2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	@Test
	void DeberiaMarioMuereBarrilAzul()   {
		
			barriles[0]=true;
			DonkeyPoob juego;
			try {
				juego = new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			
			juego.JugadorNRight(0);
			
			
			while(juego.Jugadores.get(0).getY()!=juego.barriles.get(0).getY()) {
				juego.mover(0);
			}
			juego.prepareBarriles();
			while(juego.Jugadores.get(0).getY()!=juego.barriles.get(1).getY()) {
				juego.mover(1);
			}
			for (int i=0;i<731;i++){
				juego.mover(1);
			} 
			juego.Jugadores.get(0).setY(juego.barriles.get(1).getY());
			juego.validarJugador(juego.Jugadores.get(0));
		
			assertTrue(juego.Jugadores.get(0).vidas==2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	@Test
	void DeberiaMarioMuereBarrilVerde()   {
		
			barriles[2]=true;
			DonkeyPoob juego;
			try {
				juego = new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			
			juego.JugadorNRight(0);
		
			while(juego.Jugadores.get(0).getY()!=juego.barriles.get(0).getY()) {
				juego.mover(0);
			}
			juego.prepareBarriles();
			while(juego.Jugadores.get(0).getY()!=juego.barriles.get(1).getY()) {
				juego.mover(1);
			}
			for (int i=0;i<731;i++){
				juego.mover(1);
			} 
			juego.Jugadores.get(0).setY(juego.barriles.get(1).getY());
			juego.validarJugador(juego.Jugadores.get(2));
		
			assertTrue(juego.Jugadores.get(0).vidas==2);
			barriles[2]=false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	@Test
	void DeberiaMarioMuereCualquierBarril()   {
		
			barriles[0]=true;
			barriles[1]=true;
			barriles[2]=true;
			
			DonkeyPoob juego;
			try {
				juego = new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			
			juego.JugadorNRight(0);
			while(juego.Jugadores.get(0).getY()!=juego.barriles.get(0).getY()) {
				juego.mover(0);
			}
			juego.prepareBarriles();
			while(juego.Jugadores.get(0).getY()!=juego.barriles.get(1).getY()) {
				juego.mover(1);
			}
			for (int i=0;i<731;i++){
				juego.mover(1);
			} 
			juego.Jugadores.get(0).setY(juego.barriles.get(1).getY());
			juego.validarJugador(juego.Jugadores.get(0));
		
			assertTrue(juego.Jugadores.get(0).vidas==2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Test
	void DeberiaCrearTableroDosJugadores()  {
		try {
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void DeberiajugadorDosRight()   {
			
			try {
				DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 2);
				juego.JugadorNRight(1);
				assertTrue(juego.Jugadores.get(1).x==juego.Jugadores.get(1).xInicial+5);
				barriles[1]=false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	@Test
	void DeberiajugadorDosLeft()   {
		try {
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 2);
			//juego.JugadorNRight(0);
			juego.JugadorNLeft(1);
			assertTrue(juego.Jugadores.get(1).x==juego.Jugadores.get(0).xInicial);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	void NoDeberiaJugadorDosUP() {
		try {
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 2);
			juego.JugadorNUp(1);
			assertTrue(juego.Jugadores.get(1).y==juego.Jugadores.get(1).yInicial);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void NoDeberiaJugadorDosDown() {
		try {
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 2);
			juego.JugadorNDown(1);
			assertTrue(juego.Jugadores.get(1).y==juego.Jugadores.get(1).yInicial);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void DeberiaCrearManzana() {
		try {
			elementos[5]=true;
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			
			assertTrue(juego.elementos.get(0) instanceof Manzana);
			elementos[5]=false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void DeberiaCrearCorazon() {
		try {
			elementos[4]=true;
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			
			assertTrue(juego.elementos.get(0) instanceof Corazon);
			elementos[4]=false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void DeberiaCrearSoga() {
		try {
			elementos[1]=true;
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			
			assertTrue(juego.elementos.get(0) instanceof Soga);
			elementos[1]=false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void DeberiaCrearMartillo() {
		try {
			elementos[2]=true;
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			
			assertTrue(juego.elementos.get(0) instanceof Martillo);
			elementos[2]=false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void DeberiaCrearCereza() {
		try {
			elementos[3]=true;
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			
			assertTrue(juego.elementos.get(0) instanceof Cereza);
			elementos[3]=false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void DeberiaCrearHongo() {
		try {
			elementos[0]=true;
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			
			assertTrue(juego.elementos.get(0) instanceof Hongo);
			elementos[0]=false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	////fsdfsdfdsfsdsfdsfds
	@Test
	void NoDeberiaCrearManzana() {
		try {
			elementos[4]=true;
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			
			assertFalse(juego.elementos.get(0) instanceof Manzana);
			elementos[4]=false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void NoDeberiaCrearCorazon() {
		try {
			elementos[2]=true;
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			
			assertFalse(juego.elementos.get(0) instanceof Corazon);
			elementos[2]=false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void NoDeberiaCrearSoga() {
		try {
			elementos[5]=true;
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			
			assertFalse(juego.elementos.get(0) instanceof Soga);
			elementos[5]=false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void NoDeberiaCrearMartillo() {
		try {
			elementos[4]=true;
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			
			assertFalse(juego.elementos.get(0) instanceof Martillo);
			elementos[4]=false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void NoDeberiaCrearCereza() {
		try {
			elementos[5]=true;
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			
			assertFalse(juego.elementos.get(0) instanceof Cereza);
			elementos[5]=false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void NoDeberiaCrearHongo() {
		try {
			elementos[1]=true;
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			
			assertFalse(juego.elementos.get(0) instanceof Hongo);
			elementos[1]=false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}


