/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marcosgabriell.testeinsightinformtica.model;

/**
 *
 * @author Marcos
 */
public class Atraso {
    
     private String entrada;
     private String saida;
     private int quantidade;

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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Atraso(String entrada, String saida, int quantidade) {
        this.entrada = entrada;
        this.saida = saida;
        this.quantidade = quantidade;
    }

    public Atraso() {
        super();
    }
    
}
