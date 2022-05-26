/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marcosgabriell.testeinsightinformtica.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class ResultSubtracao {
    
    public  List<Atraso> atrasos;
    public  List<HoraExtra> extras;

    public ResultSubtracao() {
    }

    public ResultSubtracao(List<Atraso> atrasos, List<HoraExtra> extras) {
        this.atrasos = atrasos;
        this.extras = extras;
    }

    public List<Atraso> getAtrasos() {
        return atrasos;
    }

    public void setAtrasos(List<Atraso> atrasos) {
        this.atrasos = atrasos;
    }

    public List<HoraExtra> getExtras() {
        return extras;
    }

    public void setExtras(List<HoraExtra> extras) {
        this.extras = extras;
    }
    
    
    
}
