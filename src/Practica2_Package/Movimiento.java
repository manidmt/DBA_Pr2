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
            return;
        }

        // Posibles movimientos: Norte, Sur, Este, Oeste
        int[] dFila = {-1, 1, 0, 0};
        int[] dColumna = {0, 0, 1, -1};
        Coordenada mejorMovimiento = null;
        double menorDistancia = Double.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            Coordenada nuevaPos = new Coordenada(actual.getFila() + dFila[i], actual.getColumna() + dColumna[i]);
            if (entorno.esAccesible(nuevaPos)) {
                double distancia = calcularDistancia(nuevaPos, entorno.getObjetivo());
                if (distancia < menorDistancia) {
                    menorDistancia = distancia;
                    mejorMovimiento = nuevaPos;
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

    private double calcularDistancia(Coordenada a, Coordenada b) {
        return Math.sqrt(Math.pow(a.getFila() - b.getFila(), 2) + Math.pow(a.getColumna() - b.getColumna(), 2));
    }
}
