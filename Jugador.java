import java.util.ArrayList;

/**
 * Interface en lo que colocaremos metodos que redefiniremos para los Jugadores.
 * @author Reyes Herrera Janeth Irandy, Hernández Nativitas Sofia Alejandra, Ortiz Briseño Emiliano.
 * @version 04/11/2023
*/
public interface Jugador{
  /**
  * Metodo para colocar fichas, aun sin definir completamente.
  * @param fichaIzq, fichaDer, tablero, fichasDisponibles; son los elementos que requerimos para la acción
  */
  public void colocarFicha(Domino fichaIzq, Domino fichaDer, ArrayList <Domino> fichasDisponibles, Tablero tablero); //Mostrar fichas en el domino
  
  /**
  * Metodo para "comer" fichas, aun sin definir completamente.
  * @param fichasDisponibles; son las fichas que aun estan disponibles para comer
  */
  public boolean robarFicha(ArrayList<Domino> fichasDisponibles); //Comer fichas en el domino
}