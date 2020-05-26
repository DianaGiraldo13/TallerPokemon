package control;
import modelo.Juego;

public class Controller {
	
	private Juego juego;
	
	
	public Controller() {
		this.juego=new Juego();
	}


	public Juego getJuego() {
		return juego;
	}


	public void setJuego(Juego juego) {
		this.juego = juego;
	}
	
	

}
