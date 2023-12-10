/**
 * Clase que viene siendo la representación de una ficha de domino.
 * @author Reyes Herrera Janeth Irandy, Hernández Nativitas Sofia Alejandra, Ortiz Briseño Emiliano.
 * @version 04/11/2023
*/
public class Domino{

  private int ladoIzq = 0;
  private int ladoDer = 0;
  private boolean mula = false;

  /**
  * Getter para tener acceso al lado izquierdo de la ficha de domino.
  * @return Devuelve el valor del lado izquierdo de la ficha.
  */
  public int getLadoIzq(){
    return ladoIzq;
  }

  /**
  * Getter para tener acceso al lado derecho de la ficha de domino.
  * @return Devuelve el valor del lado derecho de la ficha.
  */
  public int getLadoDer(){
    return ladoDer;
  }

  public boolean getMula(){
    return this.mula;
  }

  /**
  * Este metodo nos sirve para crear nuevas fichas de Domino.
  * @param ladoIzq, ladoDer son los parametros que recibe el metodo para contruir la ficha.
  */
  public Domino(int ladoIzq, int ladoDer){ //Sirve para crear nuevos domino
    this.ladoIzq = ladoIzq;
    this.ladoDer = ladoDer;
    esMula();
  }

  //Modifica el atributo mula con true si es mula
  public void esMula(){
    if (this.ladoIzq == this.ladoDer){
      this.mula = true;
    }
  }

  /**
  * Metodo que imprimer la respectiva ficha en la pantalla
  */
  public void MostrarFicha(){
    System.out.print("|"+ this.ladoIzq + ":" + this.ladoDer+ "|");
  }

  /**
  * Se encarga de rotar las fichas a la hora de colocarlas sobre el tablero
  * @return Devuelve un Domino (ficha) que es la rotación de aquel que se le mando.
  */
  public Domino rotarFicha(){
    //swap(this.ladoIzq, this.ladoDer);

    int aux = this.ladoIzq;
    this.ladoIzq = this.ladoDer;
    this.ladoDer = aux;
    return this;
  }
}