import java.util.ArrayList;
import java.util.Scanner;

/**
 * Nuestra clase principal que tendra en main, y por lo tanto dara inicio al programa.
 * @author Reyes Herrera Janeth Irandy, Hernández Nativitas Sofia Alejandra, Ortiz Briseño Emiliano.
 * @version 04/11/2023
*/
public class Main {
  /**
   * Metodo principal (main), da inicio a la ejecucion del programa.
   * @param args Los argumentos de la linea de comandos
  */
  public static void main(String[] args) throws ExcepcionDomino{
    ArrayList<Domino> fichasDomino = new ArrayList<Domino>(); //Aquí se guardaran todas las fichas que existen en el domino
    Scanner sc = new Scanner(System.in);

    for (int i = 0; i < 7; i++){
      for (int j = i; j < 7; j++){
        fichasDomino.add(new Domino(i,j));
      }  
    }

    ArrayList<AbstractJugador> jugadores = new ArrayList<AbstractJugador>(); //Aquí se guardaran los jugadores
    Tablero tablero = null; //Tablero inicializado en null (referencia)
    boolean bandera = true; //Bandera que se usa para verificar si algun jugador ha ganado
    
    
    System.out.println("------------DOMINO------------");
  System.out.println("Bienvenido al juego de domino, elige un modo de juego:\n1)Jugador vs Bot\n2)Bot vs Bot");

    switch(sc.nextInt()){
      case 1:
        System.out.println("Jugador vs Bot");
        System.out.println("¿Cuantos jugadores?");

        int numJugadores = 0; 

        do{ 
          numJugadores = sc.nextInt(); //Se guarda el numero de jugadores
          if(numJugadores < 2 || numJugadores > 4){
            System.out.println("El numero de jugadores debe ser entre 2 y 4");
          }
        }while(numJugadores < 2 || numJugadores > 4); //Se valida que el numero de jugadores sea correcto)
        

        jugadores.add(new JugadorHumano()); //Se añade primero al jugador humano al solo ser uno
        for (int i = 0; i < numJugadores - 1; i++){ //Los jugadores bot se añaden con un for
          jugadores.add(new JugadorBot((i + 1) + ""));
        }
        //Se le agregan las fichas a cada jugador
        for (int i = 0; i < jugadores.size(); i++){
          jugadores.get(i).setFichas(fichasDomino);
        }

        //Se encuentra la mula mayor recorriendo las fichas de los jugadores
        int numMulaMayor = -1;
        int jugadorMulaMayor = -1;
        int indiceMulaMayor = -1;
        for (int i = 0; i < jugadores.size(); i++){
          for (int j = 0; j < 7; j++){
            if(jugadores.get(i).getFichasJugador().get(j).getMula() == true && jugadores.get(i).getFichasJugador().get(j).getLadoDer() > numMulaMayor){
              numMulaMayor = jugadores.get(i).getFichasJugador().get(j).getLadoDer();
              indiceMulaMayor = j;
              jugadorMulaMayor = i;
            }
          }
        }

        if(numMulaMayor == -1){ //Si el numMulaMayor es -1, significa que ningun jugador tiene fichas mula
          System.out.println("No hay fichas mula, el juego lo inicia el jugador humano");
          tablero = Tablero.getInstance(jugadores.get(0).iniciarJuego()); //Se inicia el juego con el jugador humano;
          jugadorMulaMayor = 0; //Nos ayudara a saber donde iniciar el for de las jugadas
        }
        else{
          System.out.println("El jugador " + jugadores.get(jugadorMulaMayor).getNombre() + " inicia el juego");
          tablero = tablero.getInstance(jugadores.get(jugadorMulaMayor).getFichasJugador().get(indiceMulaMayor)); //Se inicia el juego con el jugador que tiene la mula mayor
        }
        
        tablero.imprimirTablero();

        int tope = jugadorMulaMayor; //Esto ayudara a los ciclos for en caso de que el jugador que inicie la partida
        //sea distinto al humano (que esta en el indice 0 del arraylist)

        do{

          if(fichasDomino.size() == 0 && tablero.cierreJugada(tablero.tablero[0].getLadoIzq())){
            throw new ExcepcionDomino();
          }
          
          for(int i = jugadorMulaMayor+1 ; i < jugadores.size(); i++){
            System.out.println("Turno de " + jugadores.get(i).getNombre());
            jugadores.get(i).colocarFicha(tablero.tablero[0], tablero.tablero[tablero.ultimoDomino-1], fichasDomino, tablero);
            tablero.imprimirTablero();
            bandera = jugadores.get(i).checkWinner(); //Se verifica con cada uno de los jugadores si ya gano
            if(bandera == false){
              break;
            }
          }
          if(bandera == true){
            for(int i = 0; i < tope + 1; i++){
              System.out.println("Turno de " + jugadores.get(i).getNombre());
              jugadores.get(i).colocarFicha(tablero.tablero[0], tablero.tablero[tablero.ultimoDomino-1], fichasDomino, tablero);
              tablero.imprimirTablero();
              bandera = jugadores.get(i).checkWinner();
              if(bandera == false){
                break;
              }
            }
          }
        }while(bandera); //Si la bandera es true entonces aun no hay ganador, si es false entonces hay ganador
        break;
        
      case 2:
        System.out.println("Bot vs Bot");
        System.out.println("¿Cuantos jugadores?");

        numJugadores = 0; 

        do{ 
          numJugadores = sc.nextInt(); //Se guarda el numero de jugadores
          if(numJugadores < 2 || numJugadores > 4){
            System.out.println("El numero de jugadores debe ser entre 2 y 4");
          }
        }while(numJugadores < 2 || numJugadores > 4); //Se valida que el numero de jugadores sea correcto)

        for (int i = 0; i < numJugadores; i++){ //Los jugadores bot se añaden con un for
          jugadores.add(new JugadorBot((i + 1) + ""));
        }
        //Se le agregan las fichas a cada jugador
        for (int i = 0; i < jugadores.size(); i++){
          jugadores.get(i).setFichas(fichasDomino);
        }

        //Se encuentra la mula mayor recorriendo las fichas de los jugadores
        numMulaMayor = -1;
        jugadorMulaMayor = -1;
        indiceMulaMayor = -1;
        for (int i = 0; i < jugadores.size(); i++){
          for (int j = 0; j < 7; j++){
            if(jugadores.get(i).getFichasJugador().get(j).getMula() == true && jugadores.get(i).getFichasJugador().get(j).getLadoDer() > numMulaMayor){
              numMulaMayor = jugadores.get(i).getFichasJugador().get(j).getLadoDer();
              indiceMulaMayor = j;
              jugadorMulaMayor = i;
            }
          }
        }

        if(numMulaMayor == -1){ //Si el numMulaMayor es -1, significa que ningun jugador tiene fichas mula
          System.out.println("No hay fichas mula, el juego lo inicia el jugador bot 1");
          tablero = Tablero.getInstance(jugadores.get(0).iniciarJuego()); //Se inicia el juego con el primer jugador en el array list
          jugadorMulaMayor = 0;
        }
        else{
          System.out.println("El jugador " + jugadores.get(jugadorMulaMayor).getNombre() + " inicia el juego");
          tablero = tablero.getInstance(jugadores.get(jugadorMulaMayor).getFichasJugador().get(indiceMulaMayor)); //Se inicia el juego con el jugador que tiene la mula mayor
        }

        tablero.imprimirTablero();

        tope = jugadorMulaMayor; //Tope para lograr dar las vueltas completas con dos for

        do{
          if(fichasDomino.size() == 0 && tablero.cierreJugada(tablero.tablero[0].getLadoIzq())){
            throw new ExcepcionDomino();
          }
          
          for(int i = jugadorMulaMayor + 1 ; i < jugadores.size(); i++){
            System.out.println("Turno de " + jugadores.get(i).getNombre());
            jugadores.get(i).colocarFicha(tablero.tablero[0], tablero.tablero[tablero.ultimoDomino-1], fichasDomino, tablero);
            tablero.imprimirTablero();
            bandera = jugadores.get(i).checkWinner(); //Se verifica con cada uno de los jugadores si ya gano
            if(bandera == false){
              break;
            }
          }
          if(bandera == true){
            for(int i = 0; i < tope + 1; i++){
              System.out.println("Turno de " + jugadores.get(i).getNombre());
              jugadores.get(i).colocarFicha(tablero.tablero[0], tablero.tablero[tablero.ultimoDomino-1], fichasDomino, tablero);
              tablero.imprimirTablero();
              bandera = jugadores.get(i).checkWinner();
              if(bandera == false){
                break;
              }
            }
          }
        }while(bandera); //Si la bandera es true entonces aun no hay ganador, si es false entonces hay ganador
        break;
      default:
        System.out.println("Modo de juego no válido");
        
    }
  }
}
