package aplicacion;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

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
	
		
	
}

