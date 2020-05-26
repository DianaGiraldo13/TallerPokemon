package modelo;

public class HiloPokemon extends Thread{

	private Pokemon pokemon;
	
	public HiloPokemon(Pokemon p) {
		this.pokemon=p;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(pokemon.getVida()>0){
			
			pokemon.movement();
			try {
				sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
