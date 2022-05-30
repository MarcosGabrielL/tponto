/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marcosgabriell.testeinsightinformtica.model;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.entrada);
        hash = 29 * hash + Objects.hashCode(this.saida);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Atraso other = (Atraso) obj;
        if (!Objects.equals(this.entrada, other.entrada)) {
            return false;
        }
        return Objects.equals(this.saida, other.saida);
    }
    
    
    
}
