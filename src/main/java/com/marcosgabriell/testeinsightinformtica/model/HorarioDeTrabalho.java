/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marcosgabriell.testeinsightinformtica.model;





/**
 *
 * @author Marcos
 */


public class HorarioDeTrabalho {
    
    
    private int id;
    
     private String entrada;
     private String saida;

    public HorarioDeTrabalho() {
        super();
    }

    public HorarioDeTrabalho(int id, String entrada, String saida) {
        this.id = id;
        this.entrada = entrada;
        this.saida = saida;
    }
    
    
     
     

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }
     
     
    
}
