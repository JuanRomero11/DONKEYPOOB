package aplicacion;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class DonkeyPoobTest {


	@Test
	void CrearTablero()  {
		try {
			DonkeyPoob juego=new DonkeyPoob();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void CrearPlataformas()  {
		try {
			DonkeyPoob juego=new DonkeyPoob();
			assertTrue(juego.plataforma.size() >0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void CrearBarriles()  {
		try {
			DonkeyPoob juego=new DonkeyPoob();
			assertTrue(juego.barriles.size() >0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
