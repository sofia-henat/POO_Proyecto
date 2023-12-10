import java.util.Collections;
/**
 * Clase que representa el tablero para ir colocando las fichas de domino.
 * @author Reyes Herrera Janeth Irandy, Hernández Nativitas Sofia Alejandra, Ortiz Briseño Emiliano.
 * @version 04/11/2023
*/
public class Tablero{
  //------------------Atributos------------------------
  private static Tablero tab; //Variable de la unica instancia del tablero
  public Domino[] tablero = new Domino[28];
  public int ultimoDomino = 0; //Posicion del ultimo domino usado en el tablero

  //------------------Metodos------------------------

  /**
  * Metodo que nos sirve para colocar una ficha inicial en el tablero.
  * @param FichaInicial Es la ficha domino con la que se inicia el juego.
  */
  private Tablero(Domino FichaInicial){
    agregarFichaDerecho(FichaInicial);
  }

  public static Tablero getInstance(Domino FichaInicial){
    if(tab == null){
      tab = new Tablero(FichaInicial);
    }
    return tab;
  }
  
  /**
  * Coloca la ficha desde el lado derecho de nuestro tablero.
  * @param ficha Representa la ficha domino que colocaremos.
  */
  public void agregarFichaDerecho(Domino ficha){
    this.tablero[ultimoDomino] = ficha;
    ultimoDomino +=1;
  }

  /**
  * Coloca la ficha desde el lado izquierdo de nuestro tablero.
  * @param ficha Representa la ficha domino que colocaremos.
  */
  public void agregarFichaIzquierdo(Domino ficha){
    //Se recorre el tablero para poder poner una la ficha a la izquierda
    for (int i = ultimoDomino; i > 0; i--){
      tablero[i] = tablero [i-1];
    }
    tablero[0] = ficha; 
    ultimoDomino +=1;
  }

  /**
  * Imprime todo el tablero hasta el momento.
  */
  public void imprimirTablero(){
    System.out.println("\nJuego: ");
    for (int i = 0; i < ultimoDomino; i++){
      this.tablero[i].MostrarFicha();
    }
    System.out.println("\n");
  }

  /**
  * Metodo que nos devuelve un booleano para comprobar que la jugada ya ha acabado
  * @param n Representa el numero de fichas que hay en el tablero.
  * @return Devuelve un booleano que nos dice si la jugada ya ha acabado o no.
  */
  public boolean cierreJugada(int n){
    int fichasJugadas = 0; //Nos ayuda a contar cuantas fichas con ese numero se han colocado en el tablero
    for (int i = 0; i < ultimoDomino; i++){
      if(this.tablero[i].getLadoIzq() == n || this.tablero[i].getLadoDer() == n){
          fichasJugadas +=1;
      }
    }
    if(fichasJugadas == 7){
      return true;
    }
    return false;
  }
}