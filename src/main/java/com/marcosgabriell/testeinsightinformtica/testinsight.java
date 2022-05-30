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
             
          
                
         new Painel().setVisible(true);
        
            }
        });
        
         
        
       
    
}
    
}
