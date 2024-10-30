/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Practica2_Package;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class Main {
    public static void main(String[] args) {
        // Inicializar la instancia de JADE Runtime
        Runtime runtime = Runtime.instance();

        // Configurar el perfil para el contenedor principal
        Profile profile = new ProfileImpl();
        AgentContainer container = runtime.createMainContainer(profile);

        try {
            // Crear y lanzar el agente en el contenedor
            AgentController agent = container.createNewAgent("AgenteMovil", "Practica2_Package.Agente", null);
            agent.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
