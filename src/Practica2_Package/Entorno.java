package Practica2_Package;

/**
 *
 * @author Manuel
 */


import java.util.HashMap;
import java.util.Map;

/**
 * Clase Entorno que maneja el estado del agente y el objetivo
 */
class Entorno {
    private Coordenada posicionActual;
    private Coordenada objetivo;
    private Mapa mapa;

    public Entorno(Coordenada inicial, Coordenada objetivo, Mapa mapa) {
        this.posicionActual = inicial;
        this.objetivo = objetivo;
        this.mapa = mapa;
    }

    public Coordenada getPosicionActual() { return posicionActual; }
    public Coordenada getObjetivo() { return objetivo; }
    public boolean esObjetivo(Coordenada pos) { return objetivo.equals(pos); }
    public boolean esAccesible(Coordenada pos) { return mapa.esAccesible(pos); }
    public void actualizarPosicion(Coordenada nuevaPosicion) {
        this.posicionActual = nuevaPosicion;
        mapa.marcarCamino(nuevaPosicion);
    }
    public Mapa getMapa() { return mapa; }
}