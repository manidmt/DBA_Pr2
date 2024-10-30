package Practica2_Package;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Manuel
 */

public class Mapa {
    
    private int filas, columnas;
    private String mapa[][];
    
    /**
     * @brief Constructor de la clase Mapa
     * 
     * @param fichero   Ruta del archivo que contiene la estructura del mapa
     * @throws IOException   Lanza una excepción si ocurre un error de lectura del archivo
     */
    public Mapa(String fichero) throws IOException {
        leerMapa(fichero);
    }
    
    /**
     * @brief Lee el mapa desde un archivo y lo carga en una matriz
     * 
     * @param fichero   Ruta del archivo que contiene la estructura del mapa
     * @throws IOException   Lanza una excepción si ocurre un error de lectura del archivo
     */
    private void leerMapa(String fichero) throws IOException {
        
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            filas = Integer.parseInt(br.readLine());
            columnas = Integer.parseInt(br.readLine());
            
            mapa = new String[filas][columnas];
            
            for (int i = 0; i < filas; i++) {
                String[] columna = br.readLine().split("\t");
                System.arraycopy(columna, 0, mapa[i], 0, columnas);   
            }
        } catch (IOException exception) {
            System.out.println("Error al leer el archivo: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
    
    /**
     * @brief Obtiene el número de filas en el mapa
     * 
     * @return int   Número de filas
     */
    public int getFilas() { return filas; }
    
    /**
     * @brief Obtiene el número de columnas en el mapa
     * 
     * @return int   Número de columnas
     */
    public int getColumnas() { return columnas; }
    
    /**
     * @brief Devuelve el contenido de una posición específica en el mapa
     * 
     * @param pos   Coordenada que representa la posición a consultar
     * @return String   Contenido de la posición especificada (como "0" para 
     * libre o "1" para obstáculo) o "-1" si la posición está fuera de los límites
     */
    public String getPos(Coordenada pos) {
        if (pos.getFila() >= 0 && pos.getFila() < filas &&
            pos.getColumna() >= 0 && pos.getColumna() < columnas)
            return mapa[pos.getFila()][pos.getColumna()];
        else
            return "-1";
    }
    
    /**
     * @brief Verifica si una posición específica en el mapa es accesible
     * 
     * @param pos   Coordenada que representa la posición a verificar
     * @return boolean   true si la posición es accesible (sin obstáculos), false en caso contrario
     */
    public boolean esAccesible(Coordenada pos) {
        return pos.getFila() >= 0 && pos.getFila() < filas &&
               pos.getColumna() >= 0 && pos.getColumna() < columnas &&
               "0".equals(mapa[pos.getFila()][pos.getColumna()]);
    }
}
