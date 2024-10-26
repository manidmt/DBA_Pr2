/**
 *
 * @author Manuel
 */

package Practica2_Package;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;



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
                
                for (int j = 0; j < columnas; j++){
                 
                    mapa[i][j] = columna[j];
                }   
            }
        }
        
        catch (IOException exception){   exception.printStackTrace();}
    }
    
    public int getFilas(){  return filas;}
    
    public int getColunmas(){   return columnas;}
    
    //  public String getPos(Coordenadas pos)
}
