package Practica2_Package;

import jade.core.Agent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class Agente extends Agent {
    private Entorno entorno;
    private Movimiento moverse;
    private int energiaConsumida = 0;

    @Override
    protected void setup() {
        Coordenada posicionInicial = new Coordenada(0, 0);
        Coordenada objetivo = new Coordenada(9, 9);
        Mapa mapa = new Mapa("ruta/al/archivo/mapa.txt");

        entorno = new Entorno(posicionInicial, objetivo, mapa);
        moverse = new Movimiento(mapa, entorno);

        Movimiento movimiento = new Movimiento(entorno, moverse, this);
        addBehaviour(movimiento);
    }

    public void incrementarEnergia(int cantidad) {
        energiaConsumida += cantidad;
    }

    @Override
    protected void takeDown() {
        System.out.println("Energía total consumida: " + energiaConsumida);
    }

    public static void main(String[] args) {
        jade.core.Runtime runtime = jade.core.Runtime.instance();
        jade.core.Profile profile = new jade.core.ProfileImpl();
        AgentContainer container = runtime.createMainContainer(profile);

        try {
            AgentController agent = container.createNewAgent("AgenteMovil", "Practica2_Package.Agente", null);
            agent.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}

