package modelo;

public class Bush {
	
	private int posX;
	private int posY;
	private Pokemon pokemon;
	
	public Bush(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public Pokemon getPokemon() {
		return pokemon;
	}
	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}
	
	

}
