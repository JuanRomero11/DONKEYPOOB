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
	void CrearTablero()  {
		try {
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void CrearPlataformas()  {
		try {
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			assertTrue(juego.plataforma.size() >0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void CrearBarriles()  {
		try {
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			assertTrue(juego.barriles.size() >0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void CrearJugador() {
		try {
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			assertTrue(juego.Jugadores.size() >0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void JugadorLeft() {
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
	void JugadorRight() {
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
	void NoJugadorUP() {
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
	void NoJugadorDown() {
		try {
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 1);
			juego.JugadorNDown(0);
			assertTrue(juego.Jugadores.get(0).y!=juego.Jugadores.get(0).yInicial);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void MoverBarriles()  {
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
	void jumping()  {
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
	void jumpingComplete()  {
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
	void NoDownJumping()  {
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
	void CrearSorpresas()  {
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
	void PrepareBarrilRojo()  {
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
	void PrepareBarrilRojoDosJugadores()  {
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
	void PrepareBarrilVerde()  {
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
	void PrepareBarrilVerdeDosJugadores()  {
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
	void PrepareBarrilAmarillo()  {
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
	void PrepareBarrilAmarilloDosJugadores()  {
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
	void PrepareBarrilAzul()  {
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
	void PrepareBarrilAzuDosJugadores()  {
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
	void MarioMuereBarrilAmarillo()   {
		
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
	void MarioMuereBarrilAzul()   {
		
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
	void MarioMuereBarrilVerde()   {
		
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
	void MarioMuereCualquierBarril()   {
		
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
	void CrearTableroDosJugadores()  {
		try {
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void jugadorDosRight()   {
			
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
	void jugadorDosLeft()   {
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
	void NoJugadorDosUP() {
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
	void NoJugadorDosDown() {
		try {
			DonkeyPoob juego=new DonkeyPoob(1, barriles, elementos, aspectoMario, 2);
			juego.JugadorNDown(1);
			assertTrue(juego.Jugadores.get(1).y==juego.Jugadores.get(1).yInicial);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
}


