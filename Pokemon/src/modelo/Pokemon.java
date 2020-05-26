package modelo;
import java.util.Random;

public class Pokemon implements Comparable<Pokemon>{
	private int nivel;
	private String nombre;
	private double altura;
	private double peso;
	private String tipo;
	private boolean capturado;
	private String sprite;
	private int position;
	private int vida;
	private int defensa;
	
	public Pokemon(int nivel, String nombre, double altura, double peso, String tipo) {
		super();
		this.nivel = nivel;
		this.nombre = nombre;
		this.altura = altura;
		this.peso = peso;
		this.tipo = tipo;
		this.sprite="./docs/data/Pokemons/"+nombre+"1"+".png";
		this.position=1;
		this.vida=100;
		this.defensa=0;
	}
	
	public void movement() {
		if(position<4) {
			position++;
		}
		else {
			position=1;
		}
		
		sprite="/docs/data/Pokemons/"+nombre+position+".png";
	}
	
	/**
	 * Este metodo genera un valor aleatorio de daño utilizando la clase Random de java.util
	 * @param p Pokemon el cual va a ser atacado
	 */
	public void atacar(Pokemon p) {
		Random r = new Random();
		int low = 10;
		int high = 90;
		int result = r.nextInt(high-low) + low;
		int damage = result;
		p.atacado(damage);
	}
	
	/**
	 * Metodo para asignar aleatoriamente un valor de defensa a un pokemon utilizando la clase Random de java.util y ejecutando el metodo nexInt que nos retorna un valor aleatorio
	 * teniendo en cuenta un limite superior
	 */
	public void defender() {
		Random r = new Random();
		int low = 0;
		int high = 50;
		int result = r.nextInt(high-low) + low;
		defensa=result;
	}
	
	public void atacado(int damage) {
		int trueDamage=damage-defensa;
		defensa-=damage;
		if(defensa<0) {
			defensa=0;
		}
		if(trueDamage<0) {
			trueDamage=0;
		}
		int rest=vida-trueDamage;
		if(rest>0) {
			vida=rest;
		}
		else{
			vida=0;
		}
	}
	
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isCapturado() {
		return capturado;
	}

	public void setCapturado(boolean capturado) {
		this.capturado = capturado;
	}

	public String getSprite() {
		return sprite;
	}

	public void setSprite(String sprite) {
		this.sprite = sprite;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	@Override
	public int compareTo(Pokemon o) {
		// TODO Auto-generated method stub
		return this.getNombre().compareTo(o.getNombre());
	}
	
	

}
