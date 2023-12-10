import java.util.ArrayList;
/**
 * Clase que hereda de AbstractJugador para la representacion de un jugador Bot.
 * @author Reyes Herrera Janeth Irandy, Hernández Nativitas Sofia Alejandra, Ortiz Briseño Emiliano.
 * @version 04/11/2023
*/
public class JugadorBot extends AbstractJugador{

  //Atributo
  String numero; //Numero de bot creado


  //Metodos
  /**
  * Metodo que le coloca su respectivo numero al Bot
  */
  JugadorBot(String numero){
    this.numero = numero;
    this.setNombre();
  }

  /**
  * Setter para modificar al nombre del jugador bot.
  */
  public void setNombre() {
    String n = "Bot " + numero; //se concatena con el numero
    super.setNombre(n);
  }

  /**
  * Metodo que nos permite visualizar nuestras fichas, asi como sus indices para elegirlas
  */
  public void verFichas() {
    System.out.println("Fichas del jugador " + getNombre() + ":");
    for (int i = 0; i < this.getFichasJugador().size(); i++){
      System.out.print(i);
      this.getFichasJugador().get(i).MostrarFicha();
      System.out.print(",");
    }
    System.out.println(" ");

  }

  @Override
  /**
  * redefinicion del metodo colocarFichas, en donde se llevar a cabo los pasos necesarios para colocar una ficha en nuestra tablero.
  * @param fichaIzq, fichaDer, tablero, fichasDisponibles; son los elementos que requerimos para la acción
  */
  public void colocarFicha(Domino fichaIzq, Domino fichaDer, ArrayList <Domino> fichasDisponibles, Tablero tablero){
    boolean coloco = true;//Comprueba si el bot fue capaz de colocar alguna ficha
    System.out.println("Turno del jugador:" + getNombre() + "\n");
    do{
      
      this.verFichas(); //Se imprime primero el arraylist de fichas del jugador

      //Se elige la ficha a colocar, probando con cada ficha que posee
      for (int i = 0; i < this.getFichasJugador().size(); i++){
        int fichaSeleccionada = i; //Indice de la ficha seleccionada
        if(fichaIzq.getLadoIzq() == this.getFichasJugador().get(fichaSeleccionada).getLadoDer()){
          //Se coloca en el tablero en el lado izq, sin rotar
          tablero.agregarFichaIzquierdo(this.getFichasJugador().get(fichaSeleccionada));
          this.getFichasJugador().remove(fichaSeleccionada); //Se remueve la ficha
          coloco = false;
          break;
        }
        else if(fichaDer.getLadoDer() == this.getFichasJugador().get(fichaSeleccionada).getLadoIzq()){
          //Se coloca en el tablero en el lado der, sin rotar
          tablero.agregarFichaDerecho(this.getFichasJugador().get(fichaSeleccionada));
          this.getFichasJugador().remove(fichaSeleccionada); //Se remueve la ficha
          coloco = false;
          break;
        }
        else if(fichaIzq.getLadoIzq() == this.getFichasJugador().get(fichaSeleccionada).getLadoIzq()){
          //Se coloca en el tablero en el lado izq, pero con rotacion
          tablero.agregarFichaIzquierdo(this.getFichasJugador().get(fichaSeleccionada).rotarFicha());
            this.getFichasJugador().remove(fichaSeleccionada); //Se remueve la ficha
          coloco = false;
          break;
        }else if(fichaDer.getLadoDer() == this.getFichasJugador().get(fichaSeleccionada).getLadoDer()){
          //Se coloca en el tablero en el lado der, pero con rotacion
          tablero.agregarFichaDerecho(this.getFichasJugador().get(fichaSeleccionada).rotarFicha());
          this.getFichasJugador().remove(fichaSeleccionada); //Se remueve la ficha
          coloco = false;
          break;
        }
        else{
          coloco = true; //Coloco sigue siendo verdadero
        }
      }
      System.out.println(" ");
    }while(coloco && robarFicha(fichasDisponibles)); //Si ambas son verdaderas se significa que no se ha podido colocar la ficha pero que aun se puede robar
    if(coloco){
      System.out.println("El jugador " + getNombre() + " no logro colocar ninguna ficha");
    }
  }
  
  @Override
  /**
  * redefinicion del metodo iniciarJuego, en donde se le asignaran fichas al azar a los jugadores bot.
  */
  public Domino iniciarJuego() {
    //Se elige una ficha al azar
    Domino fichaInicial = this.getFichasJugador().get(0);
    this.getFichasJugador().remove(0);
    return fichaInicial;
  }
}