import java.util.ArrayList;
import java.util.Random;

/**
 * Esta es una clase abstracta que representa a un jugador.
 * @author Reyes Herrera Janeth Irandy, Hernández Nativitas Sofia Alejandra, Ortiz Briseño Emiliano.
 * @version 04/11/2023
*/

public abstract class AbstractJugador implements Jugador{

  private ArrayList<Domino> fichasJugador = new ArrayList<Domino>();//Guarda las fichas de cada jugador

  private String nombre;//Nombre de jugador

  //Respectivos getters y setters
  
  /**
  * Getter para tener acceso al nombre del jugador.
  * @return devuleve el nombre del jugador.
  */
  public String getNombre(){
    return nombre;
  }

  //Al jugado humano lo dejara escoger su nombre, mierntras que al bot se le dará uno por default
  
  /**
  * Setter para modificar al nombre del jugador.
  */
  public void setNombre(String nombre){
    this.nombre = nombre;
  }

  /**
  * Getter para tener acceso a las fichas del jugador.
  * @return devuleve las fichas del jugador.
  */
  public ArrayList<Domino> getFichasJugador(){
    return this.fichasJugador;
  }

  /**
  * Setter para modificar las fichas que posee el jugador.
  */
  public void setFichas(ArrayList<Domino> fichasDomino){
    Random r = new Random(); //Se dan fichas al azar
    for (int i = 0; i < 7; i++){ //Se colocan 7 fichas
      int x = r.nextInt(0,fichasDomino.size());
      this.fichasJugador.add(fichasDomino.get(x)); //Se agrega a las fichas del jugador
      fichasDomino.remove(x); //Se elimina de las fichas del domino
    }
  }

  @Override
  
  /**
  * Se encarga de dar una ficha de manera aleatoria, simula la accion de "comer" en el Domino
  * @return Devuelve un booleano que representa si se pueden comer fichas o no.
  */
  public boolean robarFicha(ArrayList<Domino> fichasDisponibles){//Recibe la ficha que va tomar
    if (fichasDisponibles.size() > 0){
      System.out.println("El jugador " + getNombre() + " roba una ficha\n");
      Random r2 = new Random();
      int y = r2.nextInt(0,fichasDisponibles.size());
      this.fichasJugador.add(fichasDisponibles.get(y)); //La agrega a su respectivo arreglo
      fichasDisponibles.remove(y); //La elimina de la lista de fichas para comer
      return true;
    }
    else{
      System.out.println("Ya no hay mas fichas para comer\n");
      return false;
    }
  }

  /**
  * Este metodo comprueba constantemente si las fichas de alguno de los jugadores ha llegado a 0.
  * @return Devuelve un booleano que representa si alguno de los jugadores ya ha ganado.
  */
  public boolean checkWinner(){ //Revisa que no haya ganado ningun jugador
    boolean bandera;//Booleano que va a regresar
    if (fichasJugador.size() == 0){//Comprobar si las fichas del jugador no han llegado a 0
      bandera = false;
      System.out.println("\n El jugador " + getNombre() + " ha ganado");
    } else {
      bandera = true;
    }
    return bandera;
  }

  //Metodo que tendra que ser redefinido
  public abstract Domino iniciarJuego();
}
