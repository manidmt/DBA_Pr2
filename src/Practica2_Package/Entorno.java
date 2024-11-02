package Practica2_Package;

/**
 * Clase Entorno que maneja el estado del agente y el objetivo
 * 
 * @author Manuel
 */
public class Entorno {
    private Coordenada posicionActual;
    private Coordenada objetivo;
    private Mapa mapa;
    private int numeroPasos; // Contador de pasos

    /**
     * @brief Constructor de la clase Entorno
     * 
     * @param inicial  Coordenada inicial donde comienza el agente
     * @param objetivo Coordenada objetivo que el agente debe alcanzar
     * @param mapa     Objeto Mapa que representa el entorno
     */
    public Entorno(Coordenada inicial, Coordenada objetivo, Mapa mapa) {
        this.mapa = mapa;
        this.objetivo = objetivo;
        this.posicionActual = inicial;
        this.numeroPasos = 0; // Inicializar el contador de pasos

        // Marca la posición inicial del agente en el mapa
        mapa.marcarCamino(inicial);
        mapa.IndicarObjetivo(objetivo);
    }

    /**
     * @brief Obtiene la posición actual del agente
     * @return Coordenada actual del agente
     */
    public Coordenada getPosicionActual() {
        return posicionActual;
    }

    /**
     * @brief Obtiene la posición objetivo
     * @return Coordenada objetivo
     */
    public Coordenada getObjetivo() {
        return objetivo;
    }

    /**
     * @brief Verifica si la posición dada es la posición objetivo
     * @param pos Coordenada a verificar
     * @return true si la posición es el objetivo, false en caso contrario
     */
    public boolean esObjetivo(Coordenada pos) {
        return objetivo.equals(pos);
    }

    /**
     * @brief Verifica si una posición es accesible en el mapa
     * @param pos Coordenada a verificar
     * @return true si la posición es accesible, false en caso contrario
     */
    public boolean esAccesible(Coordenada pos) {
        return mapa.esAccesible(pos);
    }

    /**
     * @brief Actualiza la posición del agente en el entorno y marca el camino en el mapa
     * @param nuevaPosicion Nueva posición del agente
     */
    public void actualizarPosicion(Coordenada nuevaPosicion) {
        this.posicionActual = nuevaPosicion;
        mapa.marcarCamino(nuevaPosicion);
        numeroPasos++; // Incrementa el contador de pasos
    }

    /**
     * @brief Obtiene el número de pasos que el agente ha dado
     * @return Número de pasos
     */
    public int getNumeroPasos() {
        return numeroPasos;
    }

    /**
     * @brief Devuelve el objeto Mapa asociado al entorno
     * @return Objeto Mapa
     */
    public Mapa getMapa() {
        return mapa;
    }
}
