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
    
    public Mapa(String fichero) throws IOException{
        
        leerMapa(fichero);
    }
    
    private void leerMapa(String fichero) throws IOException{
        
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))){
            
            filas = Integer.parseInt(br.readLine());
            columnas = Integer.parseInt(br.readLine());
            
            mapa = new String[filas][columnas];
            
            for (int i = 0; i < filas; i++){
                
                String[] columna = br.readLine().split("\t");
                
                System.arraycopy(columna, 0, mapa[i], 0, columnas);   
            }
        }
        
        catch (IOException exception){
        
            System.out.println("Error al leer el archivo: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
    
    public int getFilas(){  return filas;}
    
    public int getColunmas(){   return columnas;}
    
    public String getPos(Coordenada pos){
        
        if(pos.getFila() >= 0 && pos.getFila() < filas && pos.getColumna() < columnas && pos.getColumna() >= 0)
            return mapa[pos.getFila()][pos.getColumna()];
        else
            return "-1";
    }
}
