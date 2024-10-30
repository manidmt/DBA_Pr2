package Practica2_Package;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import java.util.List;

public class Agente extends Agent {
    
    private Entorno entorno;
    private Moverse moverse;

    @Override
    protected void setup() {
        // Suponiendo que se inicia con una posición y objetivo específicos en el entorno:
        Coordenada posicionInicial = new Coordenada(0, 0);
        Coordenada objetivo = new Coordenada(9, 9);  // Posición objetivo
        Mapa mapa = new Mapa("ruta/al/archivo/mapa.txt");  // Asegúrate de que la ruta sea correcta

        entorno = new Entorno(posicionInicial, objetivo, mapa);
        moverse = new Moverse(mapa, entorno);

        addBehaviour(new MovimientoAgente());
    }

    private class MovimientoAgente extends Behaviour {
        private boolean alcanzado = false;

        @Override
        public void action() {
            if (entorno.esObjetivo(entorno.getPosicionAgente())) {
                alcanzado = true;
                System.out.println("El agente ha alcanzado el objetivo!");
                return;
            }

            List<Coordenada> ruta = moverse.Ruta_Anchura(entorno.getPosicionAgente(), entorno.getObjetivo());
            if (ruta != null && !ruta.isEmpty()) {
                Coordenada siguientePosicion = ruta.get(1);  // Siguiente paso en la ruta
                entorno.actualizarPosicion(siguientePosicion);
                System.out.println("Moviendo a: " + siguientePosicion.getFila() + ", " + siguientePosicion.getColumna());
            } else {
                System.out.println("No se puede alcanzar el objetivo desde la posición actual.");
                alcanzado = true;  // Termina si no hay ruta
            }
        }

        @Override
        public boolean done() {
            return alcanzado;
        }
    }
}

