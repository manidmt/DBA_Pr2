package Practica2_Package;

/**
 *
 * @author Manuel
 */


import java.util.HashMap;
import java.util.Map;

public class Entorno {
    private Coordenada posicionAgente;
    private Coordenada objetivo;
    private Mapa mapa;

    public Entorno(Coordenada posicionInicial, Coordenada objetivo, Mapa mapa) {
        this.posicionAgente = posicionInicial;
        this.objetivo = objetivo;
        this.mapa = mapa;
    }

    public Coordenada getPosicionAgente() { return posicionAgente; }
    public Coordenada getObjetivo() { return objetivo; }

    public void actualizarPosicion(Coordenada nuevaPosicion) {
        this.posicionAgente = nuevaPosicion;
    }

    public boolean esObjetivo(Coordenada pos) {
        return objetivo.equals(pos);
    }

    public String percibirPosicion(Coordenada pos) {
        return mapa.getPos(pos);
    }

    public Map<String, String> see() {
        Map<String, String> percepcion = new HashMap<>();
        Coordenada posActual = posicionAgente;

        percepcion.put("arriba", percibirPosicion(new Coordenada(posActual.getFila() - 1, posActual.getColumna())));
        percepcion.put("abajo", percibirPosicion(new Coordenada(posActual.getFila() + 1, posActual.getColumna())));
        percepcion.put("izquierda", percibirPosicion(new Coordenada(posActual.getFila(), posActual.getColumna() - 1)));
        percepcion.put("derecha", percibirPosicion(new Coordenada(posActual.getFila(), posActual.getColumna() + 1)));

        return percepcion;
    }
}
