package Practica2_Package;

/**
 *
 * 
 */
import java.util.*;

/**
 * Clase Moverse que permite al agente calcular rutas en el mapa.
 * 
 * @author Manuel
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

        //System.out.println("No se ha encontrado ruta al objetivo");
        return null;
    }

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


    public boolean esAccesible(Coordenada pos) {
        return mapa.esAccesible(pos);
    }
}