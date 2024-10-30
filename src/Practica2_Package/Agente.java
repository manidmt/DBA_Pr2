package Practica2_Package;

import jade.core.Agent;
import java.io.IOException;

/**
 * Clase Agente que configura el entorno y los comportamientos del agente.
 * 
 */
public class Agente extends Agent {
    private Entorno entorno;
    private Moverse moverse;
    private int energiaConsumida = 0; // Variable para rastrear la energía consumida

    @Override
    protected void setup() {
        try {
            // Configuración de la posición inicial y objetivo del agente
            Coordenada posicionInicial = new Coordenada(0, 0);
            Coordenada objetivo = new Coordenada(9, 9);
            Mapa mapa = new Mapa("Mapas/mapWithComplexObstacle1.txt"); // Ruta del mapa

            // Inicializar el entorno y el comportamiento de movimiento
            entorno = new Entorno(posicionInicial, objetivo, mapa);
            moverse = new Moverse(mapa, entorno);

            // Crear el comportamiento de movimiento y añadirlo al agente
            Movimiento movimiento = new Movimiento(entorno, moverse, this);
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



