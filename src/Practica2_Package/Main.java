/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Practica2_Package;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;


public class Main {
    public static void main(String[] args) {
        Runtime rt = Runtime.instance();
        Profile p = new ProfileImpl();
        p.setParameter(Profile.MAIN_PORT, "1100"); // Cambia a un puerto diferente, por ejemplo 1100
        AgentContainer container = rt.createMainContainer(p);
        try {
            container.createNewAgent("miAgente", "Practica2_Package.Agente", null).start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}



///Escritorio/Practicas DBA/JADE/JADE-all-4.6.0/JADE-bin-4.6.0/jade/lib$ java -cp jade.jar jade.Boot -name dba_server -gui
///Escritorio/Practicas DBA/JADE/JADE-all-4.6.0/JADE-bin-4.6.0/jade/lib$ java -cp ../../../../../DBA_Pr2/dist/DBA_Practica2.jar jade.Boot -container vdelaguila:dba_practica2.main