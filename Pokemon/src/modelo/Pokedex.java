package modelo;
import java.util.ArrayList;

public class Pokedex {
	
	private ArrayList<Pokemon> pokemones;
	
	private Pokemon actual;
	
	private int index;
	
	public Pokedex() {
		pokemones = new ArrayList<Pokemon>();
		Pokemon pikachu = new Pokemon(1, "Pikachu", 50, 100, "Electrico");
		Pokemon charmander = new Pokemon(1, "Charmander", 150, 200, "Fuego");
		Pokemon ratata = new Pokemon (1,"Ratata",60,90,"Hoja");
		Pokemon squirtle= new Pokemon(1, "Squirtle", 60, 100, "Agua");
		pokemones.add(pikachu);
		pokemones.add(charmander);
		pokemones.add(ratata);
		pokemones.add(squirtle);
		actual=pokemones.get(index);
	}
	
	public void passPage() {
		if(index<pokemones.size()-1) {
			index++;
		}
		else {
			index=0;
		}
		actual=pokemones.get(index);
	}

	public ArrayList<Pokemon> getPokemones() {
		return pokemones;
	}

	public void setPokemones(ArrayList<Pokemon> pokemones) {
		this.pokemones = pokemones;
	}

	public Pokemon getActual() {
		return actual;
	}

	public void setActual(Pokemon actual) {
		this.actual = actual;
	}
	
	public void capturarPokemon(Pokemon a) {
		for(Pokemon p : pokemones) {
			if(p.getNombre().equals(a.getNombre())) {
				p.setNivel(a.getNivel());
				p.setCapturado(true);
			}
		}
	}

}
