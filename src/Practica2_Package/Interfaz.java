package Practica2_Package;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Manuel
 */
public class Interfaz extends JFrame{
    
    private JPanel panel;
    private JButton[][] botones;
    private Coordenada inicioSeleccionado;
    private Coordenada objetivoSeleccionado;
    private boolean seleccionandoInicio = true;
    private boolean seleccionandoObjetivo = false;
    private JLabel energiaLabel;
    private JLabel coordenadasLabel;
    
    public Interfaz(int filas, int columnas) {
        super("Mapa del Agente");
        panel = new JPanel(new GridLayout(filas, columnas));
        botones = new JButton[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                botones[i][j] = new JButton();
                final int filaFinal = i; // Variable final para i
                final int columnaFinal = j; // Variable final para j
                
                /*botones[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manejarClicEnBoton(filaFinal, columnaFinal);
                    }
                });*/
                
                botones[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        mostrarCoordenadas(filaFinal, columnaFinal);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        ocultarCoordenadas();
                    }
                });
                panel.add(botones[i][j]);
            }
        }
        
        // Label para el numero de pasos
        energiaLabel = new JLabel("Pasos: 0");
        this.add(energiaLabel, BorderLayout.SOUTH); //  parte inferior
        
        //Label para mostrar las coordenadas
        coordenadasLabel = new JLabel("Coordenadas: ");
        this.add(coordenadasLabel, BorderLayout.NORTH);
        
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600); // tamaño interfaz
        this.setVisible(true);
    }
    
    public void actualizarBoton(int fila, int columna, String texto) {
        
        JButton boton = botones[fila][columna];

        if (null == texto) {
            boton.setBackground(Color.WHITE); // Casilla por defecto
        } else switch (texto) {
            case "-1":
                boton.setBackground(Color.BLACK); // Obstáculos
                break;
            case "1":
                boton.setBackground(Color.GREEN); // Inicio
                break;
            case "8":
                boton.setBackground(Color.RED); // Objetivo
                break;
            case "2":
                boton.setBackground(Color.BLUE); // Casillas visitadas
                break;
            default:
                boton.setBackground(Color.WHITE); // Casilla por defecto
                break;
        }
    }
    
    /*
    private void manejarClicEnBoton(int fila, int columna) {
        
        JButton boton = botones[fila][columna];
        
        if (seleccionandoInicio) {
            
            inicioSeleccionado = new Coordenada(fila, columna);
            boton.setBackground(Color.GREEN); 
            seleccionandoInicio = false;
            seleccionandoObjetivo = true;
        } else if(seleccionandoObjetivo){
            
            objetivoSeleccionado = new Coordenada(fila, columna);
            boton.setBackground(Color.RED); 
            seleccionandoObjetivo = false;
        }
    }*/
    
    private void mostrarCoordenadas(int fila, int columna) {
        coordenadasLabel.setText("Coordenadas: (" + fila + ", " + columna + ")");
    }
    
    public void actualizarEnergia(int energia) {
        energiaLabel.setText("Pasos: " + energia);
    }

    private void ocultarCoordenadas() {
        coordenadasLabel.setText("");
    }
    
   public void actualizarMapa(Mapa mapa, Coordenada inicio, Coordenada objetivo) {
        
       for (int i = 0; i < mapa.getFilas(); i++) {
            for (int j = 0; j < mapa.getColumnas(); j++) {
                Coordenada posActual = new Coordenada(i, j);
                int valorCelda = mapa.getPos(posActual); // Obtener el valor de la celda como int

                if (posActual.equals(inicio)) {
                    actualizarBoton(i, j, "1"); // Representar la posición de inicio con "1"
                } else if (posActual.equals(objetivo)) {
                    actualizarBoton(i, j, "8"); // Representar la posición de objetivo con "8"
                } else if (valorCelda == -1) {
                    actualizarBoton(i, j, "-1"); // Representar los obstáculos con "-1"
                } else {
                    actualizarBoton(i, j, null); // Dejar las casillas normales con su color por defecto
                }
            }
        }
    }



}
