package modelo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Juego {
	
	private Jugador jugador;
	private ArrayList<Jugador> jugadores;
	private Bush[] bushes;
	private Pokemon[] iniciales;
	private Pokemon[] batalla;
	private Pokemon bandera;;
	
	public Juego() {
		
		jugadores=new ArrayList<Jugador>();
		bushes=new Bush[4];
		bushes[0]=new Bush(100, 100);
		bushes[1]=new Bush(300, 200);
		bushes[2]=new Bush(200, 400);
		bushes[3]=new Bush(600, 300);
		iniciales= new Pokemon[3];
		Pokemon pikachu = new Pokemon(1, "Pikachu", 50, 100, "Electrico");
		Pokemon charmander = new Pokemon(1, "Charmander", 150, 200, "Fuego");
		Pokemon ratata = new Pokemon (1,"Ratata",60,90,"Hoja");
		iniciales[0]=pikachu;
		iniciales[1]=charmander;
		iniciales[2]=ratata;
		batalla=new Pokemon[2];
		bandera=new Pokemon(0, "", 0, 0, "");
	}
	
	/**
	 * Metodo para asignar aleatoriamente un pokemon utilizando la clase Random de java.util y ejecutando el metodo nexInt que nos retorna un valor aleatorio
	 * teniendo en cuenta un limite superior
	 */
	public void assignPokemon() {
		Pokemon poke = new Pokemon(5, "Squirtle", 10, 10, "Agua");
		Random r = new Random();
		int low = 0;
		int high = 4;
		int result = r.nextInt(high-low) + low;
		System.out.println(result);
		bushes[result].setPokemon(poke);
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	public void checkPlayer(Jugador p) throws JugadorYaExisteException{
		for(Jugador j:jugadores) {
			if(j.getNombre().equals(p.getNombre())) {
				throw new JugadorYaExisteException("El jugador: "+p.getNombre()+" ya esta registrado");
			}
		}
	}
	
	public void writePlayer(Jugador p) throws JugadorYaExisteException {
		
		 try {
			 	checkPlayer(p);
	            FileWriter writer = new FileWriter("./docs/data/Usuarios.txt", true);
	            String nombre=p.getNombre();
	            String fecha=p.getFechaRegistro();
	            writer.write(nombre+","+fecha+"\n");
	            writer.close();
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        }
	}
	
	public void readPlayers() {
		try {
            FileReader reader = new FileReader("./docs/data/Usuarios.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            String line;
            
            while ((line = bufferedReader.readLine()) != null) {
               String[] datos=line.split(",");
               Jugador nuevo=new Jugador(datos[0], datos[1]);
               jugadores.add(nuevo);
            }
            reader.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	public void ordenarJugadores(int opcion) {
		if(opcion==0) {
			Collections.sort(jugadores);
		}
		else {
			Collections.sort(jugadores,new DateComparator());
		}
	}
	
	public void ordenarPokemones(int opcion) {
		if(opcion==0) {
			Collections.sort(jugador.getPokedex().getPokemones());
		}
		else {
			Collections.sort(jugador.getPokedex().getPokemones(),new TypeComparator());
		}
	}

	public Bush[] getBushes() {
		return bushes;
	}

	public void setBushes(Bush[] bushes) {
		this.bushes = bushes;
	}

	public Pokemon[] getIniciales() {
		return iniciales;
	}

	public void setIniciales(Pokemon[] iniciales) {
		this.iniciales = iniciales;
	}

	public Pokemon[] getBatalla() {
		return batalla;
	}

	public void setBatalla(Pokemon[] batalla) {
		this.batalla = batalla;
	}

	public Pokemon getBandera() {
		return bandera;
	}

	public void setBandera(Pokemon bandera) {
		this.bandera = bandera;
	}
	

}
