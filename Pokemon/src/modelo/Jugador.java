package modelo;
import java.util.ArrayList;

public class Jugador implements Comparable<Jugador>{
	public final static int SPEED=10;
	
	private String nombre;
	private String fechaRegistro;
	private int posX;
	private int posY;
	private char dir;
	private int spriteCount;
	private ArrayList<Pokemon> pokemones;
	private Pokedex pokedex;
	
	public Jugador(String nombre, String fechaRegistro){
		super();
		this.nombre = nombre;
		this.fechaRegistro = fechaRegistro;
		this.posX = 370;
		this.posY = 400;
		this.dir = 'f';
		this.spriteCount=1;
		pokemones=new ArrayList<Pokemon>();
		pokedex=new Pokedex();
		
	}
	
	public void capturarPokemon(Pokemon p) {
		pokemones.add(p);
		pokedex.capturarPokemon(p);
	}
	
	public void move(char dir) {
		if(dir=='f') {
			posY+=SPEED;
			if(spriteCount==4) {
				spriteCount=1;
			}
			else {
				spriteCount++;
			}
		}
		else if(dir=='b') {
			posY-=SPEED;
			if(spriteCount==4) {
				spriteCount=1;
			}
			else {
				spriteCount++;
			}
		}
		else if(dir=='l') {
			posX-=SPEED;
			if(spriteCount==4) {
				spriteCount=1;
			}
			else {
				spriteCount++;
			}
		}
		else if(dir=='r') {
			posX+=SPEED;
			if(spriteCount==4) {
				spriteCount=1;
			}
			else {
				spriteCount++;
			}
		}
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
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
	public char getDir() {
		return dir;
	}
	public void setDir(char dir) {
		this.dir = dir;
	}
	public int getSpriteCount() {
		return spriteCount;
	}
	public void setSpriteCount(int spriteCount) {
		this.spriteCount = spriteCount;
	}

	public ArrayList<Pokemon> getPokemones() {
		return pokemones;
	}

	public void setPokemones(ArrayList<Pokemon> pokemones) {
		this.pokemones = pokemones;
	}

	public Pokedex getPokedex() {
		return pokedex;
	}

	public void setPokedex(Pokedex pokedex) {
		this.pokedex = pokedex;
	}

	@Override
	public int compareTo(Jugador arg0) {
		// TODO Auto-generated method stub
		return this.nombre.compareTo(arg0.getNombre());
	}
	
	
	

}
