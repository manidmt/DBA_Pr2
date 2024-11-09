package Practica2_Package;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Grupo 204
 */

/**
 * @brief Clase Mapa que representa el mapa en el cual se mueve el agente.
 * 
 * La clase Mapa permite cargar un mapa desde un archivo, consultar posiciones específicas
 * y marcar el camino recorrido por el agente. También puede indicar la posición de inicio y objetivo.
 * 
 */
public class Mapa {
    private int filas, columnas;
    private int[][] mapa;

    /**
     * @brief Constructor de la clase Mapa que carga el mapa desde un archivo.
     * 
     * @param fichero Nombre del archivo de texto que contiene el mapa.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public Mapa(String fichero) throws IOException {
        leerMapa(fichero);
    }

    /**
     * @brief Lee el mapa desde un archivo de texto y lo almacena en una matriz.
     * 
     * Este método inicializa el mapa leyendo el número de filas y columnas del archivo,
     * y luego rellena la matriz `mapa` con los valores del archivo.
     * 
     * @param fichero Nombre del archivo de texto que contiene el mapa.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    private void leerMapa(String fichero) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            filas = Integer.parseInt(br.readLine());
            columnas = Integer.parseInt(br.readLine());
            
            mapa = new int[filas][columnas];
            
            for (int i = 0; i < filas; i++) {
                String[] columna = br.readLine().split("\t");
                for (int j = 0; j < columnas; j++) {
                    mapa[i][j] = Integer.parseInt(columna[j]);
                }
            }
            System.out.println(fichero + "leído correctamente\n");
            
        } catch (IOException exception) {
            System.out.println("Error al leer el archivo: " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * @brief Obtiene el número de filas del mapa.
     * 
     * @return int Número de filas en el mapa.
     */
    public int getFilas() { return filas; }
    
    /**
     * @brief Obtiene el número de columnas del mapa.
     * 
     * @return int Número de columnas en el mapa.
     */
    public int getColumnas() { return columnas; }

    /**
     * @brief Obtiene el valor en una posición específica del mapa.
     * 
     * @param pos La coordenada de la posición en el mapa.
     * @return int El valor en la posición especificada, o -1 si la posición es inválida.
     */
    public int getPos(Coordenada pos) {
        if (pos.getFila() >= 0 && pos.getFila() < filas &&
            pos.getColumna() >= 0 && pos.getColumna() < columnas)
            return mapa[pos.getFila()][pos.getColumna()];
        else
            return -1;
    }

    /**
     * @brief Verifica si una posición en el mapa es accesible.
     * 
     * Una posición es accesible si está dentro de los límites del mapa y
     * no está bloqueada por un obstáculo (representado por un valor distinto de 0 o 8).
     * 
     * @param pos La coordenada de la posición a verificar.
     * @return boolean `true` si la posición es accesible, `false` en caso contrario.
     */
    public boolean esAccesible(Coordenada pos) {
        return pos.getFila() >= 0 && pos.getFila() < filas &&
               pos.getColumna() >= 0 && pos.getColumna() < columnas &&
               (mapa[pos.getFila()][pos.getColumna()] == 0 || 
                mapa[pos.getFila()][pos.getColumna()] == 8);
    }

    /**
     * @brief Marca una posición en el mapa como parte del camino recorrido por el agente.
     * 
     * @param pos La coordenada de la posición a marcar.
     */
    public void marcarCamino(Coordenada pos) {
        if (pos.getFila() >= 0 && pos.getFila() < filas &&
            pos.getColumna() >= 0 && pos.getColumna() < columnas) {
            mapa[pos.getFila()][pos.getColumna()] = 2; 
        }
    }
    
    /**
     * @brief Marca una posición como el punto de inicio en el mapa.
     * 
     * @param pos La coordenada de la posición de inicio.
     */
    public void IndicarInicio(Coordenada pos) {
        mapa[pos.getFila()][pos.getColumna()] = 1;
    }
    
    /**
     * @brief Marca una posición como el objetivo en el mapa.
     * 
     * @param pos La coordenada de la posición objetivo.
     */
    public void IndicarObjetivo(Coordenada pos) {
        mapa[pos.getFila()][pos.getColumna()] = 8;
    }
    
    /**
     * @brief Imprime el mapa en la consola.
     * 
     * Este método recorre la matriz `mapa` y la imprime en la consola,
     * mostrando el estado actual de cada celda.
     */
    public void imprimirMapa() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
    }
}