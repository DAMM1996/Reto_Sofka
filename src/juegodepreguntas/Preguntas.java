/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegodepreguntas;

/**
 *
 * @author Albeiro
 */
public class Preguntas {
    private int id;
    private Categoria categoria;
    private String descripcion;
    private DificultadPreguntas dificultad;
    private int respuestaCorrecta;
    private int valorPremio;

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
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the dificultad
     */
    public DificultadPreguntas getDificultad() {
        return dificultad;
    }

    /**
     * @param dificultad the dificultad to set
     */
    public void setDificultad(DificultadPreguntas dificultad) {
        this.dificultad = dificultad;
    }

    /**
     * @return the respuestaCorrecta
     */
    public int getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    /**
     * @param respuestaCorrecta the respuestaCorrecta to set
     */
    public void setRespuestaCorrecta(int respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    /**
     * @return the valorPremio
     */
    public int getValorPremio() {
        return valorPremio;
    }

    /**
     * @param valorPremio the valorPremio to set
     */
    public void setValorPremio(int valorPremio) {
        this.valorPremio = valorPremio;
    }
    
}
