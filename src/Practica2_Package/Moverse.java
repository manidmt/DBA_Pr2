package Practica2_Package;

import java.util.*;

/**
 *
 * @author Grupo 204
 */

/**
 * @brief Clase Moverse que permite al agente calcular rutas en el mapa utilizando algoritmos de búsqueda.
 * 
 * La clase proporciona métodos para encontrar rutas desde una posición inicial hasta un objetivo,
 * verificar posiciones accesibles y obtener posiciones adyacentes.
 * 
 */
public class Moverse {
    private Mapa mapa;
    private Entorno entorno;

    /**
     * @brief Constructor de la clase Moverse.
     * 
     * @param mapa El mapa en el cual el agente se moverá.
     * @param entorno El entorno que contiene las condiciones del agente.
     */
    public Moverse(Mapa mapa, Entorno entorno) {
        this.mapa = mapa;
        this.entorno = entorno;
    }

    /**
     * @brief Calcula la ruta más corta desde una posición inicial a un objetivo utilizando búsqueda en anchura.
     * 
     * Este método implementa el algoritmo de búsqueda en anchura (BFS) para encontrar la ruta más corta
     * desde la posición de inicio hasta la posición objetivo en un mapa. En caso de que no se encuentre una
     * ruta, se devuelve `null`. Es el método 'fundamental' de la práctica
     * 
     * @param inicio La coordenada de inicio desde donde el agente comienza.
     * @param objetivo La coordenada objetivo a la cual el agente debe llegar.
     * @return List<Coordenada> La ruta encontrada desde inicio hasta objetivo, o `null` si no existe.
     */
    public List<Coordenada> Ruta_Anchura(Coordenada inicio, Coordenada objetivo) {
        
        //System.out.println("Comenzando búsqueda en anchura...\n");
        
        Queue<List<Coordenada>> cola = new LinkedList<>();
        Set<Coordenada> visitados = new HashSet<>();

        List<Coordenada> rutaInicial = new ArrayList<>();
        rutaInicial.add(inicio);
        cola.add(rutaInicial);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            List<Coordenada> rutaActual = cola.poll();
            Coordenada posActual = rutaActual.get(rutaActual.size() - 1);

            if (posActual.equals(objetivo)) {
                System.out.println("Ruta encontrada \n");
                return rutaActual;
            }

            for (Coordenada adyacente : obtenerPosAdyacente(posActual)) {
                if (!visitados.contains(adyacente)) {
                    visitados.add(adyacente);
                    List<Coordenada> nuevaRuta = new ArrayList<>(rutaActual);
                    nuevaRuta.add(adyacente);
                    cola.add(nuevaRuta);
                }
            }
        }

        //System.out.println("No se ha encontrado ruta al objetivo");
        return null;
    }

    /**
     * @brief Obtiene las coordenadas adyacentes accesibles desde una posición dada.
     * 
     * Este método genera todas las posiciones adyacentes (verticales, horizontales y diagonales)
     * de una posición dada y verifica si cada una de ellas es accesible.
     * 
     * @param pos La coordenada actual del agente.
     * @return List<Coordenada> Lista de coordenadas accesibles adyacentes a la posición actual.
     */
    public List<Coordenada> obtenerPosAdyacente(Coordenada pos) {
        List<Coordenada> adyacentes = new ArrayList<>();

        // Movimiento arriba
        Coordenada arriba = new Coordenada(pos.getFila() - 1, pos.getColumna());
        if (esAccesible(arriba)) adyacentes.add(arriba);

        // Movimiento abajo
        Coordenada abajo = new Coordenada(pos.getFila() + 1, pos.getColumna());
        if (esAccesible(abajo)) adyacentes.add(abajo);

        // Movimiento izquierda
        Coordenada izquierda = new Coordenada(pos.getFila(), pos.getColumna() - 1);
        if (esAccesible(izquierda)) adyacentes.add(izquierda);

        // Movimiento derecha
        Coordenada derecha = new Coordenada(pos.getFila(), pos.getColumna() + 1);
        if (esAccesible(derecha)) adyacentes.add(derecha);

        // Movimiento diagonal arriba-izquierda
        Coordenada arribaIzquierda = new Coordenada(pos.getFila() - 1, pos.getColumna() - 1);
        if (esAccesible(arribaIzquierda)) adyacentes.add(arribaIzquierda);

        // Movimiento diagonal arriba-derecha
        Coordenada arribaDerecha = new Coordenada(pos.getFila() - 1, pos.getColumna() + 1);
        if (esAccesible(arribaDerecha)) adyacentes.add(arribaDerecha);

        // Movimiento diagonal abajo-izquierda
        Coordenada abajoIzquierda = new Coordenada(pos.getFila() + 1, pos.getColumna() - 1);
        if (esAccesible(abajoIzquierda)) adyacentes.add(abajoIzquierda);

        // Movimiento diagonal abajo-derecha
        Coordenada abajoDerecha = new Coordenada(pos.getFila() + 1, pos.getColumna() + 1);
        if (esAccesible(abajoDerecha)) adyacentes.add(abajoDerecha);

        return adyacentes;
    }

    
    /**
     * @brief Verifica si una coordenada es accesible en el mapa.
     * 
     * Este método consulta el mapa para verificar si la coordenada especificada es accesible
     * (es decir, no está bloqueada por obstáculos).
     * 
     * @param pos La coordenada que se desea verificar.
     * @return boolean `true` si la coordenada es accesible, `false` en caso contrario.
     */

    public boolean esAccesible(Coordenada pos) {
        return mapa.esAccesible(pos);
    }
}