/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Practica2_Package;

/**
 *
 * @author ignacio
 */
import java.util.*;

/**
 * Clase Moverse que permite al agente calcular rutas en el mapa.
 * 
 */
public class Moverse {
    private Mapa mapa;
    private Entorno entorno;

    public Moverse(Mapa mapa, Entorno entorno) {
        this.mapa = mapa;
        this.entorno = entorno;
    }

    public List<Coordenada> Ruta_Anchura(Coordenada inicio, Coordenada objetivo) {
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

        System.out.println("No se ha encontrado ruta al objetivo");
        return null;
    }

    public List<Coordenada> obtenerPosAdyacente(Coordenada pos) {
        List<Coordenada> adyacentes = new ArrayList<>();

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

    public boolean esAccesible(Coordenada pos) {
        return mapa.esAccesible(pos);
    }
}