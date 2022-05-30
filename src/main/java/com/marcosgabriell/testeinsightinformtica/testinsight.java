/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marcosgabriell.testeinsightinformtica;


import com.marcosgabriell.testeinsightinformtica.Connector.Connector;
import com.marcosgabriell.testeinsightinformtica.dao.HorarioDeTrabalhoDao;
import com.marcosgabriell.testeinsightinformtica.dao.MarcacoesFeitasDao;
import com.marcosgabriell.testeinsightinformtica.model.HorarioDeTrabalho;
import com.marcosgabriell.testeinsightinformtica.model.MarcacoesFeitas;
import com.marcosgabriell.testeinsightinformtica.modulo.Painel;

/**
 *
 * @author Marcos
 */
public class testinsight {
    
    
    
    public static void main(String[] args) {
        
       
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            
           
            
            Connector.getConnection();
             Connector.creatdatabase();
             
               HorarioDeTrabalho p = new HorarioDeTrabalho();
            HorarioDeTrabalhoDao dao = new HorarioDeTrabalhoDao();
            p.setEntrada("22:00");
            p.setSaida("01:00");
            dao.creat(p);
            p.setEntrada("02:00");
            p.setSaida("06:00");
            dao.creat(p);
            //p.setEntrada("19:00");
            //p.setSaida("21:00");
            //dao.creat(p);
        
        
            MarcacoesFeitas m = new MarcacoesFeitas();
            MarcacoesFeitasDao mdao = new MarcacoesFeitasDao();
            m.setEntrada("21:00");
            m.setSaida("00:00");
            mdao.creat(m);
            m.setEntrada("01:00");
            m.setSaida("05:00");
            mdao.creat(m);
            //m.setEntrada("19:00");
            //m.setSaida("21:00");
            //mdao.creat(m);
                
         new Painel().setVisible(true);
        
            }
        });
        
         
        
       
    
}
    
}
