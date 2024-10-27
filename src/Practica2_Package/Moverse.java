package Practica2_Package;

import java.util.*;

/**
 *
 * @author manidmt
 */
public class Moverse {
    
    private Mapa mapa;
    private Entorno entorno;
    
    /**
     * @brief Constructor de la clase 
     * 
     * @param mapa
     * @param entorno 
     */
    
    public Moverse(Mapa mapa, Entorno entorno){
        
        this.mapa = mapa;   this.entorno = entorno;
    }

    /**
     * @brief Encuentra la ruta más corta desde la posición inicial hasta el objetivo
     *  utilizando el algoritmo de búsqueda en anchura (BFS).
     * 
     * @param inicio   Coordenada de la posición de inicio
     * @param objetivo   Coordenada de la posición objetivo a alcanzar
     * @return List<Coordenada>   Lista de Coordenadas que representa el camino más 
     *  corto desde inicio hasta objetivo, o null si no se encuentra una ruta.
     *  
     */

    public List<Coordenada> Ruta_Anchura(Coordenada inicio, Coordenada objetivo) {
        
        Queue<List<Coordenada>> cola = new LinkedList<>();
        Set<Coordenada> visitados = new HashSet<>();
        
        List<Coordenada> rutaInicial = new ArrayList<>();
        rutaInicial.add(inicio);
        cola.add(rutaInicial);
        visitados.add(inicio);
        
        while (!cola.isEmpty()){
            
            List<Coordenada> rutaActual = cola.poll();
            Coordenada posActual = rutaActual.get(rutaActual.size() - 1);
            
            if (posActual.equals(objetivo)){    return rutaActual;}
        
        
            for (Coordenada adyacente : obtenerPosAdyacente(posActual)){

                if (!visitados.contains(adyacente) /*&& esAccesible(adyacente)*/){
                    
                    visitados.add(adyacente);
                    List<Coordenada> nuevaRuta = new ArrayList<>(rutaActual);
                    nuevaRuta.add(adyacente);
                    cola.add(nuevaRuta);
                }
            }
        
        }
        
        System.out.println("No se ha encontrado ruta al objetivo");
        return null;            // No se ha encontrado ruta al objetivo
    }
    
    /**
     * @brief Devuelve las posiciones adyacentes a una posición dada (arriba, 
     *  abajo, izquierda, derecha)
     * 
     * @param pos   Coordenada de la posición actual
     * @return List<Coordenada>   Lista de posiciones adyacentes accesibles desde 
     *  la posición dada
     */
    public List<Coordenada> obtenerPosAdyacente(Coordenada pos){
        
    List<Coordenada> adyacentes = new ArrayList<>();

    // Añadir solo las posiciones adyacentes que sean accesibles:
    
    Coordenada arriba = new Coordenada(pos.getFila() - 1, pos.getColumna());
    if (esAccesible(arriba)) adyacentes.add(arriba);
    
    Coordenada abajo = new Coordenada(pos.getFila() + 1, pos.getColumna());
    if (esAccesible(abajo)) adyacentes.add(abajo);

    Coordenada izquierda = new Coordenada(pos.getFila(), pos.getColumna() - 1);
    if (esAccesible(izquierda)) adyacentes.add(izquierda);

    Coordenada derecha = new Coordenada(pos.getFila(), pos.getColumna() + 1);
    if (esAccesible(derecha)) adyacentes.add(derecha);

    return adyacentes;
    }
    
    /**
     * @brief Verifica si una posición es accesible, es decir, no tiene un obstáculo
     * 
     * @param pos   Coordenada de la posición a verificar
     * @return boolean   true si la posición es accesible, false en caso contrario
     */
    public boolean esAccesible(Coordenada pos){ 
        
        // Por hacer esta funcion:
        return mapa.esAccesible(pos);
    } 
}


