package Practica2_Package;

import java.util.*;
import jade.core.behaviours.Behaviour;


/**
 * Clase Movimiento que define el comportamiento de movimiento de un agente 
 * dentro de un mapa utilizando el algoritmo de búsqueda en anchura (BFS).
 * 
 * @author manidmt
 */
public class Movimiento extends Behaviour {
    private Entorno entorno;
    private Moverse moverse;
    private Agente agente; // referencia al agente si necesitas acceso a él
    private boolean alcanzado = false;

    public Movimiento(Entorno entorno, Moverse moverse, Agente agente) {
        this.entorno = entorno;
        this.moverse = moverse;
        this.agente = agente;
    }

    @Override
    public void action() {
        if (entorno.esObjetivo(entorno.getPosicionAgente())) {
            alcanzado = true;
            System.out.println("El agente ha alcanzado el objetivo!");
            return;
        }

        List<Coordenada> ruta = moverse.Ruta_Anchura(entorno.getPosicionAgente(), entorno.getObjetivo());
        if (ruta != null && !ruta.isEmpty()) {
            Coordenada siguientePosicion = ruta.get(1);
            entorno.actualizarPosicion(siguientePosicion);
            agente.incrementarEnergia(1);
            System.out.println("Moviendo a: " + siguientePosicion.getFila() + ", " + siguientePosicion.getColumna());
        } else {
            System.out.println("No se puede alcanzar el objetivo desde la posición actual.");
            alcanzado = true;
        }
    }

    @Override
    public boolean done() {
        return alcanzado;
    }
}