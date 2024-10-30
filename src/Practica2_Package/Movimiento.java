package Practica2_Package;

import java.util.*;
import jade.core.behaviours.Behaviour;


/**
 * Clase Movimiento que define el comportamiento de movimiento de un agente 
 * dentro de un mapa utilizando el algoritmo de búsqueda en anchura (BFS).
 * 
 * @author manidmt
 */
/**
 * Clase Movimiento que define el comportamiento de movimiento del agente
 * buscando el mejor movimiento hacia el objetivo teniendo en cuenta la visión limitada.
 */
class Movimiento extends Behaviour {
     private Coordenada[] percepciones; // Para almacenar las celdas visibles
    private Entorno entorno;
    private Agente agente;
    private boolean objetivoAlcanzado = false;

    public Movimiento(Entorno entorno, Agente agente) {
        this.entorno = entorno;
        this.agente = agente;
    }

    @Override
    public void action() {
        Coordenada actual = entorno.getPosicionActual();
        if (entorno.esObjetivo(actual)) {
            objetivoAlcanzado = true;
            System.out.println("Objetivo alcanzado en: " + actual.getFila() + ", " + actual.getColumna());
            entorno.getMapa().imprimirMapa();
            agente.doDelete(); // Finalizar el agente para asegurar que se llame a takeDown()
            return;
        }

        // Actualizar percepciones del entorno inmediato
        see();

        // Elegir el mejor movimiento hacia el objetivo basado en percepciones locales
        Coordenada mejorMovimiento = null;
        double menorDistancia = Double.MAX_VALUE;

        for (Coordenada percepcion : percepciones) {
            if (entorno.esAccesible(percepcion)) {
                double distancia = calcularDistancia(percepcion, entorno.getObjetivo());
                if (distancia < menorDistancia) {
                    menorDistancia = distancia;
                    mejorMovimiento = percepcion;
                }
            }
        }

        if (mejorMovimiento != null) {
            entorno.actualizarPosicion(mejorMovimiento);
            agente.incrementarEnergia(1);
        }
    }

    @Override
    public boolean done() {
        return objetivoAlcanzado;
    }
    
    private void see() {
        Coordenada actual = entorno.getPosicionActual();
        percepciones = new Coordenada[8];
        percepciones[0] = new Coordenada(actual.getFila() - 1, actual.getColumna()); // Norte
        percepciones[1] = new Coordenada(actual.getFila() + 1, actual.getColumna()); // Sur
        percepciones[2] = new Coordenada(actual.getFila(), actual.getColumna() + 1); // Este
        percepciones[3] = new Coordenada(actual.getFila(), actual.getColumna() - 1); // Oeste
        percepciones[4] = new Coordenada(actual.getFila() - 1, actual.getColumna() - 1); // Noroeste
        percepciones[5] = new Coordenada(actual.getFila() - 1, actual.getColumna() + 1); // Noreste
        percepciones[6] = new Coordenada(actual.getFila() + 1, actual.getColumna() - 1); // Suroeste
        percepciones[7] = new Coordenada(actual.getFila() + 1, actual.getColumna() + 1); // Sureste
    }

    private double calcularDistancia(Coordenada a, Coordenada b) {
        return Math.sqrt(Math.pow(a.getFila() - b.getFila(), 2) + Math.pow(a.getColumna() - b.getColumna(), 2));
    }
}
