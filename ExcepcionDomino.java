/**
 * Clase que representa nuestra propia excepcion.
 * @author Reyes Herrera Janeth Irandy, Hernández Nativitas Sofia Alejandra, Ortiz Briseño Emiliano.
 * @version 04/11/2023
 * @throws ExcepcionDomino Excepcion que se presenta cuando el juego se "cierra", osease, cuando ya no hay forma de que los jugaodores puedan ganar y sus fichas no han llegado a 0.
*/
public class ExcepcionDomino extends Exception{

  /**
   * Imprime el mensaje de nuestra excepcion
  */
  public ExcepcionDomino(){
    super("Ningun jugador es capaz de colocar más fichas, por lo tanto se queda en empate.");
  }
}