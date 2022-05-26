/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marcosgabriell.testeinsightinformtica.dao;



import com.marcosgabriell.testeinsightinformtica.Connector.Connector;
import com.marcosgabriell.testeinsightinformtica.model.HorarioDeTrabalho;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;



/**
 *
 * @author Marcos
 */


public class HorarioDeTrabalhoDao {
    
   
     public void creat(HorarioDeTrabalho c){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("INSERT INTO ponto.horariodetrabalho (entrada,saida) VaLUES(?,?)");
            stmt.setString(1,c.getEntrada());
            stmt.setString(2,c.getSaida());
           
            stmt.executeUpdate();
            
          //  JOptionPane.showMessageDialog(null, "Salvo com sucesso");
                    } catch (SQLException ex ) {
                        JOptionPane.showMessageDialog(null, "Erro "+ex);
                    }catch (java.lang.NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "Erro "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    public List<HorarioDeTrabalho> read(){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<HorarioDeTrabalho> horarios = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM ponto.horariodetrabalho");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                HorarioDeTrabalho horario = new HorarioDeTrabalho();
                
                horario.setEntrada(rs.getString("entrada"));
                horario.setSaida(rs.getString("saida"));
                horario.setId(rs.getInt("id"));
                
                horarios.add(horario);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return horarios;
        
    
    }
    
    public void update(HorarioDeTrabalho c){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("UPDATE ponto.horariodetrabalho SET entrada = ?,saida = ? WHERE id = ?");
            stmt.setString(1,c.getEntrada());
            stmt.setString(2,c.getSaida());
            stmt.setInt(3,c.getId());
            
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atalizar: "+ex);
            }catch (java.lang.NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "Erro "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
    
    public void delete(HorarioDeTrabalho c){
    
    Connection con = Connector.getConnection();
    PreparedStatement stmt = null;
    
    
        try {
            stmt = con.prepareStatement("DELETE FROM ponto.horariodetrabalho WHERE id = ?");
            stmt.setInt(1,c.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Deletar: "+ex);
        } finally{
        
        Connector.closeConnection(con, stmt);
        }
    
    }
    
      public int Count(){
    
        Connection con = Connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<HorarioDeTrabalho> horarios = new ArrayList<>();
        int count = 0;
        
        try {
            stmt = con.prepareStatement("SELECT COUNT(*) FROM ponto.horariodetrabalho");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
               count = rs.getInt(1);
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex);
        }finally{
            Connector.closeConnection(con, stmt, rs);        
            
        }
        
        return count;
        
    
    }
   
    
}
