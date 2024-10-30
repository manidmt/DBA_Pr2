package Practica2_Package;

import jade.core.Agent;
import java.io.IOException;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.ControllerException;


/**
 * Clase Agente que configura el entorno y los comportamientos del agente.
 * 
 */

/**
 * Clase Agente que configura el entorno y los comportamientos del agente.
*/
public class Agente extends Agent {
    private Entorno entorno;
    private int energiaConsumida = 0; // Variable para rastrear la energía consumida

    @Override
    protected void setup() {
        // Definir variables de posición inicial y objetivo que se pueden personalizar en cada ejecución
        int inicioFila = 1; // Cambiar este valor según se necesite
        int inicioColumna = 1; // Cambiar este valor según se necesite
        int objetivoFila = 8; // Cambiar este valor según se necesite
        int objetivoColumna = 8; // Cambiar este valor según se necesite

        try {
            // Configuración de la posición inicial y objetivo del agente
            Coordenada posicionInicial = new Coordenada(inicioFila, inicioColumna);
            Coordenada objetivo = new Coordenada(objetivoFila, objetivoColumna);
            Mapa mapa = new Mapa("Mapas/mapWithComplexObstacle1.txt"); // Ruta del mapa

            // Inicializar el entorno y el comportamiento de movimiento
            entorno = new Entorno(posicionInicial, objetivo, mapa);

            // Crear el comportamiento de movimiento y añadirlo al agente
            Movimiento movimiento = new Movimiento(entorno, this);
            addBehaviour(movimiento);
        } catch (IOException e) {
            System.out.println("Error al cargar el mapa: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Método para incrementar la energía consumida en el movimiento
     * 
     * @param cantidad La cantidad de energía a incrementar
     */
    public void incrementarEnergia(int cantidad) {
        energiaConsumida += cantidad;
    }

    @Override
    protected void takeDown() {
        // Mostrar la energía total consumida al finalizar la ejecución
        System.out.println("Energía total consumida: " + energiaConsumida);
    }
}
