package Practica2_Package;

/**
 *
 * @author Manuel
 */
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
        return mapa.getPos(pos);  // Devuelve si es libre ("0") o un obstáculo ("-1")
    }
    
    
    
    
    
}
