package Practica2_Package;

import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import java.io.IOException;
import java.util.List;

public class Agente extends Agent {
    private Moverse moverse;
    private List<Coordenada> ruta;
    private Entorno entorno; // Instancia del entorno
    private Interfaz interfaz;

    @Override
    protected void setup() {
        try {
            Mapa mapa = new Mapa("Mapas/mapWithComplexObstacle1.txt");
            Coordenada inicio = new Coordenada(0, 0); // Ajusta según tus datos
            Coordenada objetivo = new Coordenada(5, 5); // Ajusta según tus datos

            // Inicializar el entorno
            entorno = new Entorno(inicio, objetivo, mapa);
            moverse = new Moverse(mapa, entorno);
            
            interfaz = new Interfaz(mapa.getFilas(), mapa.getColumnas());
            interfaz.actualizarMapa(mapa, inicio, objetivo);

            // Calcular la ruta con BFS
            ruta = moverse.Ruta_Anchura(inicio, objetivo);

            if (ruta != null) {
                SequentialBehaviour secuenciaMovimiento = new SequentialBehaviour();

                for (Coordenada paso : ruta) {
                    secuenciaMovimiento.addSubBehaviour(new OneShotBehaviour() {
                        @Override
                        public void action() {
                            
                            try {
                                // Pausa de 500 milisegundos entre movimientos
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            
                            entorno.actualizarPosicion(paso);
                            mapa.imprimirMapa();
                            if (paso != inicio) interfaz.actualizarBoton(paso.getFila(), paso.getColumna(), "2");
                            System.out.println("Número de pasos: " + entorno.getNumeroPasos());
                            interfaz.actualizarEnergia(entorno.getNumeroPasos());
                        
                            if (entorno.esObjetivo(paso)) {
                                System.out.println("El agente ha llegado al objetivo.");
                                doDelete(); // Finaliza el agente
                            }
                        }
                    });
                }

                // Añadir el comportamiento secuencial al agente
                addBehaviour(secuenciaMovimiento);
            } else {
                System.out.println("No se encontró una ruta al objetivo.");
                doDelete(); // Termina el agente si no hay ruta
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el mapa: " + e.getMessage());
            doDelete(); // Finaliza el agente si hay un error al cargar el mapa
        }
    }

    @Override
    protected void takeDown() {
        // Mostrar un mensaje al finalizar el agente
        System.out.println("Agente finalizado después de " + entorno.getNumeroPasos() + " pasos.");
    }
}



