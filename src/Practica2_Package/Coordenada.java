package Practica2_Package;

/**
 *
 * @author Grupo 204
 */


/**
 * @brief Clase Coordenada que representa una posición en el mapa.
 * 
 * Esta clase se utiliza para definir una posición en el mapa mediante una fila y una columna.
 * Incluye métodos para obtener y establecer la fila y la columna, así como para comparar
 * objetos de tipo Coordenada.
 */

class Coordenada {
    private int fila, columna;

    /**
     * @brief Constructor de la clase Coordenada.
     * 
     * @param fila Fila de la posición.
     * @param columna Columna de la posición.
     */
    public Coordenada(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * @brief Obtiene la fila de la coordenada.
     * 
     * @return La fila de la coordenada.
     */
    public int getFila() { return fila; }
    
    /**
     * @brief Obtiene la columna de la coordenada.
     * 
     * @return La columna de la coordenada.
     */
    public int getColumna() { return columna; }
    
    /**
     * @brief Establece la fila de la coordenada.
     * 
     * @param fila La nueva fila de la coordenada.
     */
    public void setFila(int fila) { this.fila = fila; }
    
    /**
     * @brief Establece la columna de la coordenada.
     * 
     * @param columna La nueva columna de la coordenada.
     */
    public void setColumna(int columna) { this.columna = columna; }

    /**
     * @brief Compara esta coordenada con otro objeto para verificar la igualdad.
     * 
     * Dos coordenadas se consideran iguales si tienen la misma fila y columna.
     * Este método, junto con el siguiente, son fundamentales para que el HashSet
     * pueda comparar datos y almacenar cada coordenada correctamente
     * 
     * @param obj El objeto a comparar.
     * @return true si las coordenadas son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordenada that = (Coordenada) obj;
        return fila == that.fila && columna == that.columna;
    }
    
    /**
     * @brief Calcula el código hash para esta coordenada.
     * 
     * El código hash se basa en los valores de fila y columna, permitiendo
     * un uso eficiente en estructuras de datos como HashSet.
     * 
     * @return El código hash de la coordenada.
     */
    @Override
    public int hashCode() {
        // Habiendo 10x10 posiciones este hash es suficiente para que cada pos
        // tenga código hash único
        return 31 * fila + columna; 
    }
}