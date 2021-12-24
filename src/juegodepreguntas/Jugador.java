/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegodepreguntas;

import java.util.Scanner;
import javax.swing.JOptionPane;


public class Jugador {
    
   private int id;  
   private String nombre;
   private int acumulado;
   private int puntaje;
   private Preguntas pregunta;

    public Jugador(String nombre, int acumulado, int puntaje) {
        this.nombre = nombre;
        this.acumulado = acumulado;
        this.puntaje = puntaje;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the acumulado
     */
    public int getAcumulado() {
        return acumulado;
    }

    /**
     * @param acumulado the acumulado to set
     */
    public void setAcumulado(int acumulado) {
        this.acumulado = acumulado;
    }

    /**
     * @return the puntaje
     */
    public int getPuntaje() {
        return puntaje;
    }

    /**
     * @param puntaje the puntaje to set
     */
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the pregunta
     */
    public Preguntas getPregunta() {
        return pregunta;
    }

    /**
     * @param pregunta the pregunta to set
     */
    public void setPregunta(Preguntas pregunta) {
        this.pregunta = pregunta;
    }
    

    
   
    
    
    
}
