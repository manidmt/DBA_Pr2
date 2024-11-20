package Practica2_Package;

import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Grupo 204
 */

/**
 * @brief Clase Agente que representa un agente que se mueve en un mapa para alcanzar un objetivo.
 * 
 * Esta clase extiende la clase `Agent` de JADE y define el comportamiento del agente para moverse en un mapa.
 * El agente calcula una ruta usando búsqueda en anchura y sigue la ruta paso a paso, actualizando la interfaz
 * gráfica y mostrando el progreso en la consola.
 * 
 */ 

public class Agente extends Agent {
    private Moverse moverse;
    private List<Coordenada> ruta;
    private Entorno entorno; // Instancia del entorno
    private Interfaz interfaz;

    /**
     * @brief Método de configuración inicial del agente.
     * 
     * Este método se ejecuta al iniciar el agente. Carga el mapa, inicializa el entorno y la interfaz gráfica,
     * calcula la ruta hacia el objetivo usando búsqueda en anchura (BFS) y define un comportamiento secuencial
     * para que el agente siga la ruta paso a paso.
     */
    @Override
    protected void setup() {
        try {
            Mapa mapa = new Mapa("Mapas/mapWithComplexObstacle2.txt");
            
            Coordenada inicio = solicitarCoordenada("inicio", mapa);
            Coordenada objetivo = solicitarCoordenada("objetivo", mapa);

            // Inicializar el entorno
            entorno = new Entorno(inicio, objetivo, mapa);
            moverse = new Moverse(mapa, entorno);
            
            interfaz = new Interfaz(mapa.getFilas(), mapa.getColumnas());
            interfaz.actualizarMapa(mapa, inicio, objetivo);

            // Calcular la ruta con BFS
            ruta = moverse.Ruta_Anchura(inicio, objetivo);

            if (ruta != null) {
                SequentialBehaviour secuenciaMovimiento = new SequentialBehaviour();

                for (Coordenada paso : ruta) {
                    secuenciaMovimiento.addSubBehaviour(new OneShotBehaviour() {
                        @Override
                        public void action() {
                            
                            try {
                                // Pausa de 500 milisegundos entre movimientos
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            
                            entorno.actualizarPosicion(paso);
                            mapa.imprimirMapa();
                            if (paso != inicio) interfaz.actualizarBoton(paso.getFila(), paso.getColumna(), "2");
                            System.out.println("Número de pasos: " + entorno.getNumeroPasos());
                            interfaz.actualizarEnergia(entorno.getNumeroPasos());
                        
                            if (entorno.esObjetivo(paso)) {
                                System.out.println("El agente ha llegado al objetivo.");
                                doDelete(); // Finaliza el agente
                            }
                        }
                    });
                }

                // Añadir el comportamiento secuencial al agente
                addBehaviour(secuenciaMovimiento);
            } else {
                System.out.println("No se encontró una ruta al objetivo.");
                doDelete(); // Termina el agente si no hay ruta
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el mapa: " + e.getMessage());
            doDelete(); // Finaliza el agente si hay un error al cargar el mapa
        }
    }

    /**
     * @brief Método de terminación del agente.
     * 
     * Este método se ejecuta cuando el agente finaliza, ya sea al alcanzar el objetivo
     * o por algún error. Muestra un mensaje en la consola indicando el número de pasos realizados.
     */
    @Override
    protected void takeDown() {
        // Mostrar un mensaje al finalizar el agente
        System.out.println("Agente finalizado después de " + entorno.getNumeroPasos() + " pasos.");
    }
    
    /**
    * @brief Solicita las coordenadas al usuario y las valida contra el mapa.
    * 
    * @param tipo Indica si es la coordenada de "inicio" o "objetivo".
    * @param mapa El mapa donde se validan las coordenadas.
    * @return Una coordenada válida dentro del mapa.
    */
   private Coordenada solicitarCoordenada(String tipo, Mapa mapa) {
       Scanner scanner = new Scanner(System.in);
       Coordenada coordenada = null;

       while (coordenada == null) {
           System.out.println("Introduce las coordenadas de " + tipo + " (fila columna):");
           int fila = scanner.nextInt();
           int columna = scanner.nextInt();

           Coordenada posibleCoordenada = new Coordenada(fila, columna);
           if (mapa.esAccesible(posibleCoordenada)) {
               coordenada = posibleCoordenada;
           } else {
               System.out.println("Las coordenadas introducidas no son válidas. Inténtalo de nuevo.");
           }
       }

       return coordenada;
   }
}



