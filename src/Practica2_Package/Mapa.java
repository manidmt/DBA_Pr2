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
    private int[][] mapa;

    public Mapa(String fichero) throws IOException {
        leerMapa(fichero);
    }

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
        } catch (IOException exception) {
            System.out.println("Error al leer el archivo: " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    public int getFilas() { return filas; }
    public int getColumnas() { return columnas; }

    public int getPos(Coordenada pos) {
        if (pos.getFila() >= 0 && pos.getFila() < filas &&
            pos.getColumna() >= 0 && pos.getColumna() < columnas)
            return mapa[pos.getFila()][pos.getColumna()];
        else
            return -1;
    }

    public boolean esAccesible(Coordenada pos) {
        return pos.getFila() >= 0 && pos.getFila() < filas &&
               pos.getColumna() >= 0 && pos.getColumna() < columnas &&
               (mapa[pos.getFila()][pos.getColumna()] == 0 || 
                mapa[pos.getFila()][pos.getColumna()] == 8);
    }

    public void marcarCamino(Coordenada pos) {
        if (pos.getFila() >= 0 && pos.getFila() < filas &&
            pos.getColumna() >= 0 && pos.getColumna() < columnas) {
            mapa[pos.getFila()][pos.getColumna()] = 2; // Marca la celda por la que el agente ha pasado
        }
    }
    
    public void IndicarObjetivo(Coordenada pos) {
        mapa[pos.getFila()][pos.getColumna()] = 8;
    }
    public void imprimirMapa() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
    }
}