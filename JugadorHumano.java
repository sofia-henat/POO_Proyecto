import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que hereda de AbstractJugador para la representacion de un jugador humano.
 * @author Reyes Herrera Janeth Irandy, Hernández Nativitas Sofia Alejandra, Ortiz Briseño Emiliano.
 * @version 04/11/2023
*/
public class JugadorHumano extends AbstractJugador{

  Scanner sc = new Scanner(System.in);

  //------------------Metodos------------------------

  /**
  * Metodo que manda el respectivo nombre del jugador real
  */
  JugadorHumano() {
    setNombre();
  }

  /**
  * Solicita el nombre del jugador
  */
  public void setNombre() {
    System.out.println("Ingrese el nombre del jugador:");
    super.setNombre(sc.nextLine());
  }

  //Se imprime primero el arraylist de fichas del jugador
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
    boolean coloco = true;//Comprueba si el jugador fue capaz de colocar alguna ficha

    System.out.println("Turno del jugador:" + getNombre() + "\n");

    do{
      this.verFichas(); //Se imprime primero el arraylist de fichas del jugador
      System.out.println("Ingrese el indice de la ficha que desea colocar:");
      int fichaSeleccionada = -1;
      do{
        //Se elige la ficha a colocar
        fichaSeleccionada = sc.nextInt(); //Indice de la ficha seleccionada
        if (fichaSeleccionada < -1 || fichaSeleccionada >= this.getFichasJugador().size()){
          System.out.println("El indice ingresado no es valido, intentelo de nuevo");
        }
      }while(fichaSeleccionada < 0 || fichaSeleccionada >= this.getFichasJugador().size());
      

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
          System.out.println("La ficha seleccionada no es valida");
          coloco = true; //Coloco sigue siendo verdadero
          //Si la ficha seleccionada no es valida, se asume que no se cuenta con una ficha posible de colocar, por lo que se roba una nueva ficha
        }      
    }while(coloco && robarFicha(fichasDisponibles));//Si ambas son verdaderas se significa que no se ha podido colocar la ficha pero que aun se puede robar
    if(coloco){
      System.out.println("El jugador " + getNombre() + " no logro colocar ninguna ficha");
    }
  }

  @Override
  /**
  * redefinicion del metodo iniciarJuego, en donde se le asignaran fichas al azar a los jugadores bot.
  */
  public Domino iniciarJuego() {
  //El jugador elige con que ficha comenzar
  System.out.println("Seleccione con que ficha comenzar");
  this.verFichas();
  int fichaSeleccionada = sc.nextInt();
  Domino ficha = this.getFichasJugador().get(fichaSeleccionada);
  this.getFichasJugador().remove(fichaSeleccionada);
  return ficha;
  }
}