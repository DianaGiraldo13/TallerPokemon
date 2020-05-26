package vista;
import java.awt.Image;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import control.Controller;
import modelo.Bush;
import modelo.HiloPokemon;
import modelo.Jugador;
import modelo.JugadorYaExisteException;
import modelo.Pokedex;
import modelo.Pokemon;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet{
	
	public final static String BASE_ROUTE="./docs/data/Character/";
	private Controller controller;
	private int screen=0;
	private String nombre="";
	private boolean pokedex;
	private boolean started;
	private boolean turno;
	private String consola;
	
	public static void main(String[] args) {
		PApplet.main(Main.class);
	}
	
	public void settings() {
		  size(800, 600);
		}
	
	public void setup() {
		controller=new Controller();
		controller.getJuego().readPlayers();
		frameRate(60);
		turno=true;
		consola="";
	}
	
	public void batalla(Pokemon pokemon) {
		screen=3;
		Jugador j=controller.getJuego().getJugador();
		Pokemon p=null;
		for(Pokemon po:j.getPokemones()) {
			if(po.getVida()>0) {
				p=po;
				break;
			}
		}
		if(p!=null) {
			HiloPokemon hilo = new HiloPokemon(p);
			hilo.start();
		}
		HiloPokemon hilo2=new HiloPokemon(pokemon);
		hilo2.start();
		controller.getJuego().getBatalla()[1]=p;
		
	}
	
	public void checkBushes() {
		for(Bush b:controller.getJuego().getBushes()) {
			Jugador j=controller.getJuego().getJugador();
			if((j.getPosX()>b.getPosX()&&j.getPosX()<=b.getPosX()+40)&&(j.getPosY()>b.getPosY()&&j.getPosY()<=b.getPosY()+40)) {
				if(b.getPokemon()!=null) {
					controller.getJuego().getBatalla()[0]=b.getPokemon();
					if(!started) {
						batalla(b.getPokemon());
						started=true;
						b.setPokemon(null);
					}
					
				}
			}
		}
		
	}
	
	
	
	public void drawPlayer() {
		Jugador player=controller.getJuego().getJugador();
		PImage sprite= loadImage(BASE_ROUTE+player.getDir()+player.getSpriteCount()+".png");
		textSize(15);
		
		text(player.getNombre(), player.getPosX()+player.getNombre().length()*2, player.getPosY()-10);
		image(sprite, player.getPosX(), player.getPosY());
	}
	
	public void movePlayer(char dir) {
		Jugador player=controller.getJuego().getJugador();
		player.setDir(dir);
		player.move(dir);
	}
	
	public void drawBushes() {
		for(Bush b:controller.getJuego().getBushes()) {
			image(loadImage("./docs/data/others/Bush.png"), b.getPosX(), b.getPosY(),80,80);
		}
	}
	
	public void drawBattle() {
		
		background(100);
		Pokemon player=controller.getJuego().getBatalla()[1];
		Pokemon enemy=controller.getJuego().getBatalla()[0];
		fill(255);
		if(player!=null) {
			
			image(loadImage(player.getSprite()), 350, 200);
			text(player.getVida(), 365, 200);
		}
		if(enemy!=null) {
			
			text(enemy.getVida(), 365, 50);
			image(loadImage(enemy.getSprite()), 350, 50);
		}
		text("Atacar [A]", 10, 500);
		text("Defender [D]", 10, 530);
		text("Salir [Enter]", 10, 560);
		text(consola, 350, 500);
		if(player!=null) {
			if(player.getVida()==0) {
				consola="El pokemon ha escapado";
				player.setVida(100);
				enemy.setVida(100);
				controller.getJuego().getBatalla()[1]=null;
			}
		}
		if(enemy!=null) {
			
			if(enemy.getVida()==0) {
				consola="Atrapaste al pokemon";
				controller.getJuego().getJugador().capturarPokemon(enemy);
				player.setVida(100);
				enemy.setVida(100);
				controller.getJuego().getBatalla()[0]=null;
				
			}
		}
	}
	
	public void draw() {
		background(20);
		if(screen==0) {
			image(loadImage("./docs/data/others/Background.png"),0,0);
//			noFill();
//			rect(540,450,240,70);
			
		}
		else if(screen==1) {
			fill(255);
			textSize(40); 
			text("Escriba su nombre: ", 200, 200);
			text(nombre,200,300);
		}
		else if(screen==2) {
			image(loadImage("./docs/data/others/Terrain.png"),0,0);
			drawPlayer();	
			drawPokebolas();
			if(!controller.getJuego().getBandera().getNombre().equals("")) {
				image(loadImage(controller.getJuego().getBandera().getSprite()), 380, 450);
			}
			drawBushes();
			checkBushes();
		}
		else if(screen==3) {
			
			drawBattle();
		}
		else if(screen==4) {
			ArrayList<Jugador> jugadores=controller.getJuego().getJugadores();
			text("Jugadores",340,90);
			for(int i=0;i<jugadores.size();i++) {
				fill(255);
				text(jugadores.get(i).getNombre()+" "+jugadores.get(i).getFechaRegistro(), 300, 150+(20*i));
			}
		}
		if(pokedex) {
			drawPokedex();
		}
		
	}
	
	public void drawPokedex() {
		image(loadImage("./docs/data/others/Pokedex.png"),550,50,250,500);
		Pokedex pokedex=controller.getJuego().getJugador().getPokedex();
		Pokemon actual=pokedex.getActual();
		image(loadImage(actual.getSprite()), 660, 200);
		if(actual.isCapturado()) {
			image(loadImage("./docs/data/others/Ball.png"), 700, 200);
		}
		text("Nombre: "+actual.getNombre(), 600, 260);
		text("Nivel: "+actual.getNivel(), 600, 280);
		text("Tipo: "+actual.getTipo(), 600, 300);
		text("Altura: "+actual.getAltura()+"", 600, 320);
		text("Peso: "+actual.getPeso()+"", 600, 340);
	}
	
	public void drawPokebolas() {
		for(int i=0;i<controller.getJuego().getIniciales().length;i++) {
			image(loadImage("./docs/data/others/Ball.png"), 340+(i*40), 500,20,20);
		}
	}
	
	public boolean cargarJugador(String nombre, String fechaRegistro) {
		Jugador nuevo=new Jugador(nombre, fechaRegistro);
		controller.getJuego().setJugador(nuevo);
		try {
			controller.getJuego().writePlayer(nuevo);
			controller.getJuego().assignPokemon();
			return true;
		} catch (JugadorYaExisteException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	public void keyPressed() {
		if(screen==0) {
			
		}
		else if (screen==1) {
			
			if(key==BACKSPACE) {
				String temp=nombre;
				nombre="";
				for(int i=0;i<temp.length()-1;i++) {
					nombre+=temp.toCharArray()[i];
				}
				
			}
			else if(key==ENTER) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();  
				if(cargarJugador(nombre, dtf.format(now))) {
					screen=2;
				}
				else {
					controller=new Controller();
				}
				
				
			}
			else {
				nombre+=key;
			}
		}
		else if(screen==2) {
			if(keyCode==UP) {
				movePlayer('b');
			
			}
			else if (keyCode==DOWN) {
				movePlayer('f');
				
			}
			else if (keyCode==LEFT) {
				movePlayer('l');
				
			}
			else if (keyCode==RIGHT) {
				movePlayer('r');
				
			}
			else if(keyCode==48) {
				controller.getJuego().ordenarPokemones(0);
				System.out.println(0);
			}
			else if(keyCode==49) {
				controller.getJuego().ordenarPokemones(1);
				System.out.println(1);
			}
			if(keyCode==TAB) {
				pokedex=!pokedex;
			}
			if(!controller.getJuego().getBandera().getNombre().equals("")) {
				if(key==ENTER) {
					controller.getJuego().getJugador().capturarPokemon(controller.getJuego().getBandera());
					controller.getJuego().setBandera(new Pokemon(0, "", 0, 0, ""));
					controller.getJuego().setIniciales(new Pokemon [0]);
				}
			}
		}
		else if(screen==3) {
			if(key==ENTER) {
				screen=2;
				controller.getJuego().assignPokemon();
				started=false;
				consola="";
			}
			else if(keyCode==UP) {
				consola="";
				if(turno) {
					controller.getJuego().getBatalla()[1].atacar(controller.getJuego().getBatalla()[0]);	
					turno=false;
					consola+="\n"+controller.getJuego().getBatalla()[1].getNombre()+" ha atacado";
					turnoEnemigo();
				}
			}
			else if(keyCode==DOWN) {
				consola="";
				if(turno) {
					controller.getJuego().getBatalla()[1].defender();
					turno=false;
					consola+="\n"+controller.getJuego().getBatalla()[1].getNombre()+" ha aumentado sus defensas a "+controller.getJuego().getBatalla()[1].getDefensa();
					turnoEnemigo();
					
				}
			}
		}
		else if(screen==4) {
			if(key==ENTER) {
				screen=0;
			}
			else if(keyCode==UP) {
				controller.getJuego().ordenarJugadores(0);
			}
			else if(keyCode==DOWN) {
				controller.getJuego().ordenarJugadores(1);
			}
		}
		
		  
		}
	
	public void turnoEnemigo() {
		
		int opcion=(int)random(2);
		if(opcion==0) {
			controller.getJuego().getBatalla()[0].atacar(controller.getJuego().getBatalla()[1]);
			consola+="\n"+controller.getJuego().getBatalla()[0].getNombre()+" ha atacado";
			turno=true;
		}
		else {
			controller.getJuego().getBatalla()[0].defender();
			consola+="\n"+controller.getJuego().getBatalla()[0].getNombre()+" ha aumentado sus defensas a "+controller.getJuego().getBatalla()[1].getDefensa();
			turno=true;
		}
	}
	
	public void mouseClicked() {
		if(screen==0) {
			if((mouseX>=540 && mouseX<=780) && (mouseY>=430 && mouseY<=490)) {
				screen=1;
			}
			else if((mouseX>=540 && mouseX<=780) && (mouseY>=480 && mouseY<=550)) {
				screen=4;
			}
		}
		else if(screen==2) {
			if(!pokedex) {				
				int posX=mouseX;
				int index=(posX-340)/40;
				controller.getJuego().setBandera(controller.getJuego().getIniciales()[index]);
			}
			
			
		}
		if(pokedex) {
			controller.getJuego().getJugador().getPokedex().passPage();
		}
	}

}
