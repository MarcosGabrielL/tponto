/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.marcosgabriell.testeinsightinformtica.modulo;

import com.marcosgabriell.testeinsightinformtica.Connector.Connector;
import com.marcosgabriell.testeinsightinformtica.dao.HorarioDeTrabalhoDao;
import com.marcosgabriell.testeinsightinformtica.dao.MarcacoesFeitasDao;
import com.marcosgabriell.testeinsightinformtica.model.Atraso;
import com.marcosgabriell.testeinsightinformtica.model.HoraExtra;
import com.marcosgabriell.testeinsightinformtica.model.HorarioDeTrabalho;
import com.marcosgabriell.testeinsightinformtica.model.MarcacoesFeitas;
import com.marcosgabriell.testeinsightinformtica.model.ResultSubtracao;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 *
 * @author Marcos
 */
public class Painel extends javax.swing.JFrame {

    /**
     * Creates new form Painel
     */
    
     List<HorarioDeTrabalho> horarios = new ArrayList<>();
      List<MarcacoesFeitas> horariosmarcacoes = new ArrayList<>();
    
    public Painel() {
        initComponents();
     estilojpanel();
         
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setBackground(new java.awt.Color(255,255,255));
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
         setLocationRelativeTo(null);
         addWindowListener(new WindowAdapter() {
	public void windowClosing(WindowEvent evt) {
		
                if (JOptionPane.showConfirmDialog(null,"Deseja sair")==JOptionPane.YES_OPTION){
                    
                    dispose();	
                }
                    
        }
        });
         
          readjTableHorarioDeTrabalho();
          readjTableMarcacoesFeitas();
          
         
          
         
         
    }
    
      private void estilojpanel(){
    
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
        }
        SwingUtilities.updateComponentTreeUI(jScrollPane1);
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        
        Atraso.setDefaultRenderer(String.class, centerRenderer);
         Atraso.getTableHeader().setForeground(new java.awt.Color(204,41,69));
         Atraso.getTableHeader().setFont(new java.awt.Font("Tahoma", Font.BOLD, 14)); // NOI18N
         JTableHeader Theader3 = Atraso.getTableHeader();
        Theader3.setFont(new Font("Tahome", Font.BOLD, 14)); // font name style size
        ((DefaultTableCellRenderer)Theader3.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER); // center header tex
        Atraso.setRowHeight(30);
         
          HoraExtra.setDefaultRenderer(String.class, centerRenderer);
         HoraExtra.getTableHeader().setForeground(new java.awt.Color(204,41,69));
         HoraExtra.getTableHeader().setFont(new java.awt.Font("Tahoma", Font.BOLD, 14)); // NOI18N
         JTableHeader Theader1 = HoraExtra.getTableHeader();
        Theader1.setFont(new Font("Tahome", Font.BOLD, 14)); // font name style size
        ((DefaultTableCellRenderer)Theader1.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER); // center header tex
        HoraExtra.setRowHeight(30);
         
          MarcacoesFeitas.setDefaultRenderer(String.class, centerRenderer);
         MarcacoesFeitas.getTableHeader().setForeground(new java.awt.Color(204,41,69));
         MarcacoesFeitas.getTableHeader().setFont(new java.awt.Font("Tahoma", Font.BOLD, 14)); // NOI18N
         JTableHeader Theader2 = MarcacoesFeitas.getTableHeader();
        Theader2.setFont(new Font("Tahome", Font.BOLD, 14)); // font name style size
        ((DefaultTableCellRenderer)Theader2.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER); // center header tex
        MarcacoesFeitas.setRowHeight(30);
         
          HorarioDeTrabalho.setDefaultRenderer(String.class, centerRenderer);
         HorarioDeTrabalho.getTableHeader().setForeground(new java.awt.Color(204,41,69));
         HorarioDeTrabalho.getTableHeader().setFont(new java.awt.Font("Tahoma", Font.BOLD, 14)); // NOI18N
         //HorarioDeTrabalho.getTableHeader().setHorizontalAlignment(JLabel.CENTER);
         
         
        
    
        //Modifica titulo da jtable
        JTableHeader Theader = HorarioDeTrabalho.getTableHeader();
        //Theader.setBackground(new java.awt.Color(51,51,51)); // change the Background color
        //Theader.setForeground(new java.awt.Color(120,0,20)); // change the Foreground
        
        Theader.setFont(new Font("Tahome", Font.BOLD, 14)); // font name style size
        ((DefaultTableCellRenderer)Theader.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER); // center header tex
        
        
        
        DefaultTableModel modelo = (DefaultTableModel)HorarioDeTrabalho.getModel();
        modelo.setNumRows(0);  //Zera a tabela
        HorarioDeTrabalho.setRowHeight(30); // Tamanho vertical da tabela
         
           
        
    }
      
       public void readjTableHorarioDeTrabalho(){
        
        DefaultTableModel modelo = (DefaultTableModel)HorarioDeTrabalho.getModel();
        modelo.setNumRows(0);
        
       
        
        HorarioDeTrabalhoDao mdao = new HorarioDeTrabalhoDao();
        horarios = mdao.read();
        for(HorarioDeTrabalho m: mdao.read()){
           
        
            modelo.addRow(new Object[]{
                m.getEntrada(),
                m.getSaida()
                        
                    });
    }
       }
       
       public void  readjTableMarcacoesFeitas(){
    
     DefaultTableModel modelo = (DefaultTableModel)MarcacoesFeitas.getModel();
        modelo.setNumRows(0);
        
       
        
        MarcacoesFeitasDao mdao = new MarcacoesFeitasDao();
        horariosmarcacoes = mdao.read();
        for(MarcacoesFeitas m: mdao.read()){
           
        
            modelo.addRow(new Object[]{
                m.getEntrada(),
                m.getSaida()
                        
                    });
            }
        }
       
      public void AdicionarHorarioDeTrabalho(){
          
           HorarioDeTrabalho p = new HorarioDeTrabalho();
            HorarioDeTrabalhoDao dao = new HorarioDeTrabalhoDao();
           
            
            if(dao.Count() < 3){
            
        p.setEntrada(jFormattedTextField4.getText());
        p.setSaida(jFormattedTextField3.getText());
        
        dao.creat(p);

        //prepara proximo
        preparaproximo();
        
        
        
            }else{
                JOptionPane.showMessageDialog(rootPane, "Numero maximo de HorarioDeTrabalho Atingido");
            }
        
        
        
      }
      
      public void AdicionarMarcacoesFeitas(){
          
          //Check
           Locale locale = new Locale("pt","BR");
         GregorianCalendar calendar = new GregorianCalendar();
         SimpleDateFormat formatador1 = new SimpleDateFormat("HH:mm");
          try {
         Date Inicio = formatador1.parse(jFormattedTextField2.getText());
         Date Final = formatador1.parse(jFormattedTextField1.getText());
         
         if(Integer.valueOf(jFormattedTextField2.getText().substring(0,2)) > 24 || 
               Integer.valueOf(jFormattedTextField2.getText().substring(3,5)) >60   ){
             
         }else{
              MarcacoesFeitas p = new MarcacoesFeitas();
            MarcacoesFeitasDao dao = new MarcacoesFeitasDao();
           
        p.setEntrada(jFormattedTextField2.getText());
        p.setSaida(jFormattedTextField1.getText());
        
        dao.creat(p);

        //prepara proximo
        preparaproximoMarcacoes();
          }
         
         
         } catch (ParseException ex) {
            JOptionPane.showConfirmDialog(this, "Formato invalido!");
         }
          
           
        
        
        
            
        
        
        
      }
      
       public void ApagarHorarioDeTrabalho(){
           
            if(HorarioDeTrabalho.getSelectionModel().isSelectionEmpty()){
                JOptionPane.showMessageDialog(rootPane, "Edite uma linha para apagar ");
        }else{
    
         int a = HorarioDeTrabalho.getSelectedRow();
             HorarioDeTrabalho p = new HorarioDeTrabalho();
            HorarioDeTrabalhoDao dao = new HorarioDeTrabalhoDao();
            
           
           
            p.setId(horarios.get(a).getId());
            dao.delete(p);
            
            horarios = dao.read();

            preparaproximo();

            }
    
    }
       
       public void ApagarMarcacoesFeitas(){
           
            if(MarcacoesFeitas.getSelectionModel().isSelectionEmpty()){
                JOptionPane.showMessageDialog(rootPane, "Edite uma linha para apagar ");
        }else{
    
         int a = MarcacoesFeitas.getSelectedRow();
             MarcacoesFeitas p = new MarcacoesFeitas();
            MarcacoesFeitasDao dao = new MarcacoesFeitasDao();
            
           
           
            p.setId(horariosmarcacoes.get(a).getId());
            dao.delete(p);
            
            horariosmarcacoes = dao.read();

            preparaproximoMarcacoes();

            }
    
    }
       
    public void AtualizaHorarioDeTrabalho(){
        
        if(HorarioDeTrabalho.getSelectionModel().isSelectionEmpty()){
                JOptionPane.showMessageDialog(rootPane, "Edite uma linha para atualizar ");
        }else{
            
            HorarioDeTrabalho p = new HorarioDeTrabalho();
            HorarioDeTrabalhoDao dao = new HorarioDeTrabalhoDao();
            
            int a = HorarioDeTrabalho.getSelectedRow();
            
            p.setEntrada(String.valueOf(HorarioDeTrabalho.getValueAt(a,0)));
            p.setSaida(String.valueOf(HorarioDeTrabalho.getValueAt(a,1)));
            p.setId(horarios.get(a).getId());
            
            dao.update(p);
            
            horarios = dao.read();
            
            preparaproximo();
        }
    }
    
    public void AtualizaMarcacoesFeitas(){
        
        if(MarcacoesFeitas.getSelectionModel().isSelectionEmpty()){
                JOptionPane.showMessageDialog(rootPane, "Edite uma linha para atualizar ");
        }else{
            
             MarcacoesFeitas p = new MarcacoesFeitas();
            MarcacoesFeitasDao dao = new MarcacoesFeitasDao();
            
            int a = MarcacoesFeitas.getSelectedRow();
            
            p.setEntrada(String.valueOf(MarcacoesFeitas.getValueAt(a,0)));
            p.setSaida(String.valueOf(MarcacoesFeitas.getValueAt(a,1)));
            p.setId(horariosmarcacoes.get(a).getId());
            
            dao.update(p);
            
            horariosmarcacoes = dao.read();
            
            preparaproximoMarcacoes();
        }
    }
    
    public void preparaproximo(){
        
        jFormattedTextField4.setText("");
        jFormattedTextField3.setText("");
        
        readjTableHorarioDeTrabalho();
    }
    
    public void preparaproximoMarcacoes(){
        
        jFormattedTextField2.setText("");
        jFormattedTextField1.setText("");
        
        readjTableMarcacoesFeitas();
    }
    
     public void readjTableAtrasos(){
         PreencheTabelas(); 
     }
     
    public void readjTableHoraExtra(){
         PreencheTabelas();
     }
    
    //Sem Bibliotecas
     public void Subtrai( ){
         
         
         
         List<Atraso> atrasos = new ArrayList<>();
         List<HoraExtra> extras = new ArrayList<>();
         
         List<Integer> aberturas = new ArrayList<>();
         List<Integer> fechamentos = new ArrayList<>();
         
         List<HorarioDeTrabalho> intervalos = new ArrayList<>();
         
         int PrimeiraEntrada = 0;
         int UltimaSaida = 0;
         int FimPrimeiroTurno = 0;
         boolean rompe = false;
                    
                                 /**
                                 * VERIFICA SE HORA FINAL < HORA INICIAL
                                 */
             for(HorarioDeTrabalho a : horarios){
                 int f = Integer.valueOf(a.getSaida().substring(0,2))*60 + Integer.valueOf(a.getSaida().substring(3,5));
                 int i = Integer.valueOf(a.getEntrada().substring(0,2))*60 + Integer.valueOf(a.getEntrada().substring(3,5));
                    
                 if(i>f){
                     rompe = true;
                 }
             }
             
             if(rompe){
                 Subtrainegativos();
             }else{
                 try {
                    
                        int count = horarios.size();
                        //Checa intervalos se count > 2
                        for(HorarioDeTrabalho a : horarios){
                            
                            //Organizar Aberturas numa lista
                            aberturas.add(Integer.valueOf(a.getEntrada().substring(0,2))*60 + Integer.valueOf(a.getEntrada().substring(3,5)));
                            //Organizar Fechamentos numa lista
                            fechamentos.add(Integer.valueOf(a.getSaida().substring(0,2))*60 + Integer.valueOf(a.getSaida().substring(3,5)));
                           
                        }
                        //Ordenar a duas listas de forma crescente
                         Collections.sort(aberturas);
                        Collections.sort(fechamentos);
                        
                        /**
                             * Para 3 Turnos --> Intervalo 1 = Menor Fechamento - Segundo Maior Abertura
                             *                   Intervalo 2 = 2 Menor Fechamento -  Maior Abertura
                             *                   Intervalo 3 = ultimo fechamento - primeiro abertura
                             *
                             * Para 2 Turnos --> Intervalo 1 = 2 Maior Fechamento - Maior Abertura
                             *                   Intervalo 2 = ultimo fechamento - primeiro abertura
                             */
                        //Criar intervalos em formato (Entrada,Saida)
                        if(count==3){
                            HorarioDeTrabalho Intervalo = new HorarioDeTrabalho();
                            //Intervalo 1
                            Intervalo.setEntrada(fechamentos.get(0).toString());
                            Intervalo.setSaida(aberturas.get(1).toString());
                            intervalos.add(Intervalo);
                            //Intervalo 2
                            Intervalo.setEntrada(fechamentos.get(1).toString());
                            Intervalo.setSaida(aberturas.get(2).toString());
                            intervalos.add(Intervalo);
                             //Intervalo 3
                            //Intervalo.setEntrada(fechamentos.get(2).toString());
                            //Intervalo.setSaida(aberturas.get(0).toString());
                            //intervalos.add(Intervalo);
                            
                            PrimeiraEntrada = aberturas.get(0);
                            UltimaSaida = fechamentos.get(2);
                            FimPrimeiroTurno = fechamentos.get(0);
                            
                        }
                        if(count==2){
                             HorarioDeTrabalho Intervalo = new HorarioDeTrabalho();
                            //Intervalo 1
                            Intervalo.setEntrada(fechamentos.get(0).toString());
                            Intervalo.setSaida(aberturas.get(1).toString());
                            intervalos.add(Intervalo);
                            //Intervalo 2 
                            //Intervalo.setEntrada(fechamentos.get(1).toString());
                            //Intervalo.setSaida(aberturas.get(0).toString());
                            //intervalos.add(Intervalo);
                            
                            PrimeiraEntrada = aberturas.get(0);
                            UltimaSaida = fechamentos.get(1);
                            FimPrimeiroTurno = fechamentos.get(0);
                            
                        }if(count==1){
                            
                            PrimeiraEntrada = aberturas.get(0);
                            UltimaSaida = fechamentos.get(0);
                            
                        }
                            
                        //Percorre todas as marcações
                        for(MarcacoesFeitas b : horariosmarcacoes){
                            int EntradaNoTrabalho = Integer.valueOf(b.getEntrada().substring(0,2))*60+Integer.valueOf(b.getEntrada().substring(3,5));
                            int SaidaNoTrabalho = Integer.valueOf(b.getSaida().substring(0,2))*60+Integer.valueOf(b.getSaida().substring(3,5));

                        
                            //Checa se Trabalha nestes intervalos Subtração = (HoraExtra)
                            for(HorarioDeTrabalho pausa : intervalos){
                                int InicioIntervalo = Integer.valueOf(pausa.getEntrada());
                                int FinalIntervalo = Integer.valueOf(pausa.getSaida());
                                
                                System.out.println("InicioIntervalo: "+ InicioIntervalo/60+":"+InicioIntervalo%60+"\tFinalIntervalo"+FinalIntervalo/60+":"+FinalIntervalo%60);
                                System.out.println("EntradaNoTrabalho: "+ EntradaNoTrabalho/60+":"+EntradaNoTrabalho%60+"\tSaidaNoTrabalho"+SaidaNoTrabalho/60+":"+SaidaNoTrabalho%60+"\n\n");
                                
                                

                                //Se horario de entrada entre intervalo
                                if(InicioIntervalo < EntradaNoTrabalho && EntradaNoTrabalho < FinalIntervalo){
                                    //Começou a trabalhar dentro do intervalo
                                    
                                        //Verifica se saida antes do fim do intervalo
                                        if(SaidaNoTrabalho < FinalIntervalo){
                                             //Terminou o trabalho antes do fim do intervalo
                                             
                                                //Entrou depois do inicio do intervalo e saiu antes do fim do intervalo
                                                 System.out.println("Hora Extra: "+b.getEntrada()+"  "+ b.getSaida() +
                                                 "\tTotal de "+(SaidaNoTrabalho-EntradaNoTrabalho)/60+":"+((SaidaNoTrabalho-EntradaNoTrabalho)%60)+" Horas Extras");
                                                 
                                                 //Add Hora Extra
                                                 HoraExtra extra = new HoraExtra();
                                                    extra.setEntrada(""+(EntradaNoTrabalho)/60+":"+((EntradaNoTrabalho)%60));
                                                    extra.setSaida(""+(SaidaNoTrabalho)/60+":"+((SaidaNoTrabalho)%60));
                                                    extra.setQuantidade(SaidaNoTrabalho-EntradaNoTrabalho);
                                                    extras.add(extra);
                                   
                                            }else{
                                                //Terminou o trabalho depois do fim do intervalo

                                                    //Entrou depois do inicio do intervalo e saiu depois do fim do intervalo
                                                      System.out.println("Hora Extra: "+b.getEntrada()+"  "+ pausa.getSaida() +
                                                      "\tTotal de "+(FinalIntervalo-EntradaNoTrabalho)/60+":"+((FinalIntervalo-EntradaNoTrabalho)%60)+" Horas Extras");
                                                      //Add Hora Extra
                                                  HoraExtra extra = new HoraExtra();
                                                    extra.setEntrada(""+(EntradaNoTrabalho)/60+":"+((EntradaNoTrabalho)%60));
                                                    extra.setSaida(""+(FinalIntervalo)/60+":"+((FinalIntervalo)%60));
                                                    extra.setQuantidade(FinalIntervalo-EntradaNoTrabalho);
                                                    extras.add(extra);
                                            }
                                        }

                                //Se horario de entrada antes do intervalo
                                if(EntradaNoTrabalho < InicioIntervalo){
                                    //Começou o trabalho antes do intervalo
                                    
                                        //Verifica se saida antes do inicio do intervalo
                                        if(SaidaNoTrabalho < InicioIntervalo ){
                                            //Não trabalhou no intervalo

                                            }else{
                                            //Trabalhou no intervalo

                                                 //Verifica se saida antes do fim do intervalo 
                                                 if(SaidaNoTrabalho < FinalIntervalo){
                                                    //Terminou o trabalho antes do fim do intervalo

                                                        //Entrou antes do inicio do intervalo e saiu antes do fim do intervalo
                                                        System.out.println("Hora Extra: "+pausa.getEntrada()+"  "+ b.getSaida()+
                                                      "\tTotal de "+(SaidaNoTrabalho-InicioIntervalo)/60+":"+((SaidaNoTrabalho-InicioIntervalo)%60)+" Horas Extras");
                                                         //Add Hora Extra
                                                  HoraExtra extra = new HoraExtra();
                                                    extra.setEntrada(""+(InicioIntervalo)/60+":"+((InicioIntervalo)%60));
                                                    extra.setSaida(""+(SaidaNoTrabalho)/60+":"+((SaidaNoTrabalho)%60));
                                                    extra.setQuantidade(SaidaNoTrabalho-InicioIntervalo);
                                                    extras.add(extra);
                                                   }else{
                                                       //Terminou o trabalho depois do fim do intervalo

                                                       //Trabalhou todo o intervalo
                                                        //Entrou antes do inicio do intervalo e saiu antes do fim do intervalo
                                                        System.out.println("Hora Extra: "+InicioIntervalo/60+":"+InicioIntervalo%60+"  "+FinalIntervalo/60+":"+FinalIntervalo%60 +
                                                      "\tTotal de "+(FinalIntervalo-InicioIntervalo)/60+":"+((FinalIntervalo-InicioIntervalo)%60)+" Horas Extras");
                                                        //Add Hora Extra
                                                        HoraExtra extra = new HoraExtra();
                                                        extra.setEntrada(""+(InicioIntervalo)/60+":"+((InicioIntervalo)%60));
                                                        extra.setSaida(""+(FinalIntervalo)/60+":"+((FinalIntervalo)%60));
                                                        extra.setQuantidade(FinalIntervalo-InicioIntervalo);
                                                        extras.add(extra);
                                                   }
                                        }
                                }
                                
                                
                                
                               
                               
                            }
                            
                            //Chega se trabalha antes dos turnos
                            if(EntradaNoTrabalho < PrimeiraEntrada){
                                //Entrou antes dos primeiro turno
                                
                                System.out.println("Hora Extra Antes: "+EntradaNoTrabalho/60+":"+EntradaNoTrabalho%60+"  "+PrimeiraEntrada/60+":"+PrimeiraEntrada%60 +
                                                      "\tTotal de "+(PrimeiraEntrada-EntradaNoTrabalho)/60+":"+((PrimeiraEntrada-EntradaNoTrabalho)%60)+" Horas Extras");
                                        //Add Hora Extra
                                                        HoraExtra extra = new HoraExtra();
                                                        extra.setEntrada(""+(EntradaNoTrabalho)/60+":"+((EntradaNoTrabalho)%60));
                                                        extra.setSaida(""+(PrimeiraEntrada)/60+":"+((PrimeiraEntrada)%60));
                                                        extra.setQuantidade(PrimeiraEntrada-EntradaNoTrabalho);
                                                        extras.add(extra);                       
                            }
                              
                            //Checa se trabalha depois dos turnos
                            if(UltimaSaida < SaidaNoTrabalho){
                                //Entrou antes dos primeiro turno
                                
                                System.out.println("Hora Extra Depois: "+UltimaSaida/60+":"+UltimaSaida%60+"  "+SaidaNoTrabalho/60+":"+SaidaNoTrabalho%60 +
                                                      "\tTotal de "+(SaidaNoTrabalho-UltimaSaida)/60+":"+((SaidaNoTrabalho-UltimaSaida)%60)+" Horas Extras");
                                        //Add Hora Extra
                                                        HoraExtra extra = new HoraExtra();
                                                        extra.setEntrada(""+(UltimaSaida)/60+":"+((UltimaSaida)%60));
                                                        extra.setSaida(""+(SaidaNoTrabalho)/60+":"+((SaidaNoTrabalho)%60));
                                                        extra.setQuantidade(SaidaNoTrabalho-UltimaSaida);
                                                        extras.add(extra);                       
                            }
                            
                        }
                    
                        
           //----------------------------------------------------------------------------------------------------//
                       
                    
                     
                    //Checa se Não Trabalha no Turnos Subtração = -(Atraso)
                    for(HorarioDeTrabalho turno : horarios){
                        
                        
                        int InicioTurnoTrabalho = Integer.valueOf(turno.getEntrada().substring(0,2))*60+Integer.valueOf(turno.getEntrada().substring(3,5));
                        int FimTurnoTrabalho = Integer.valueOf(turno.getSaida().substring(0,2))*60+Integer.valueOf(turno.getSaida().substring(3,5));
                                 
                        for(MarcacoesFeitas b : horariosmarcacoes){
                            
                            int EntradaNoTrabalho = Integer.valueOf(b.getEntrada().substring(0,2))*60+Integer.valueOf(b.getEntrada().substring(3,5));
                            int SaidaNoTrabalho = Integer.valueOf(b.getSaida().substring(0,2))*60+Integer.valueOf(b.getSaida().substring(3,5));

                            
                            //System.out.println("InicioTurnoTrabalho: "+ InicioTurnoTrabalho/60+":"+InicioTurnoTrabalho%60+"\tFinalIntervalo"+FimTurnoTrabalho/60+":"+FimTurnoTrabalho%60);
                            //System.out.println("EntradaNoTrabalho: "+ EntradaNoTrabalho/60+":"+EntradaNoTrabalho%60+"\tSaidaNoTrabalho"+SaidaNoTrabalho/60+":"+SaidaNoTrabalho%60+"\n\n");
                            
                            
                            
                            //Se horario de entrada entre turno
                                if(InicioTurnoTrabalho < EntradaNoTrabalho && EntradaNoTrabalho < FimTurnoTrabalho){
                                    //Começou a trabalhar dentro do turno
                                    
                                        //Verifica se saida antes do fim do turno
                                        if(SaidaNoTrabalho < FimTurnoTrabalho){
                                             //Terminou o trabalho antes do fim do turno
                                             
                                                //1 -Entrou depois do inicio do turno  
                                                 System.out.println("Atraso 1: "+(InicioTurnoTrabalho)/60+":"+((InicioTurnoTrabalho)%60)+"  "+ (EntradaNoTrabalho)/60+":"+((EntradaNoTrabalho)%60)+
                                                 "\tTotal de "+(EntradaNoTrabalho-InicioTurnoTrabalho)/60+":"+((EntradaNoTrabalho-InicioTurnoTrabalho)%60)+" Horas Atrasada");
                                                 
                                                 //Add Hora Extra
                                                 Atraso extra = new Atraso();
                                                    extra.setEntrada(""+(InicioTurnoTrabalho)/60+":"+((InicioTurnoTrabalho)%60));
                                                    extra.setSaida(""+(EntradaNoTrabalho)/60+":"+((EntradaNoTrabalho)%60));
                                                    extra.setQuantidade(EntradaNoTrabalho-InicioTurnoTrabalho);
                                                    atrasos.add(extra);
                                                    
                                                 //2 - e saiu antes do fim do turno
                                                 System.out.println("Atraso 2: "+(SaidaNoTrabalho)/60+":"+((SaidaNoTrabalho)%60)+"  "+ (FimTurnoTrabalho)/60+":"+((FimTurnoTrabalho)%60)+
                                                 "\tTotal de "+(FimTurnoTrabalho-SaidaNoTrabalho)/60+":"+((FimTurnoTrabalho-SaidaNoTrabalho)%60)+" Horas Atrasada");
                                                 
                                                 //Add Hora Extra
                                                  Atraso extra2 = new Atraso();
                                                    extra2.setEntrada(""+(SaidaNoTrabalho)/60+":"+((SaidaNoTrabalho)%60));
                                                    extra2.setSaida(""+(FimTurnoTrabalho)/60+":"+((FimTurnoTrabalho)%60));
                                                    extra2.setQuantidade(FimTurnoTrabalho-SaidaNoTrabalho);
                                                    atrasos.add(extra2);
                                   
                                            }else{
                                                //Terminou o trabalho depois do fim do turno

                                                    //1 -Entrou depois do inicio do turno e saiu antes
                                                      System.out.println("Atraso 3: "+(InicioTurnoTrabalho)/60+":"+((InicioTurnoTrabalho)%60)+"  "+ (EntradaNoTrabalho)/60+":"+((EntradaNoTrabalho)%60)+
                                                 "\tTotal de "+(EntradaNoTrabalho-InicioTurnoTrabalho)/60+":"+((EntradaNoTrabalho-InicioTurnoTrabalho)%60)+" Horas Atrasada");
                                                 
                                                 //Add Hora Extra
                                                 Atraso extra = new Atraso();
                                                    extra.setEntrada(""+(InicioTurnoTrabalho)/60+":"+((InicioTurnoTrabalho)%60));
                                                    extra.setSaida(""+(EntradaNoTrabalho)/60+":"+((EntradaNoTrabalho)%60));
                                                    extra.setQuantidade(EntradaNoTrabalho-InicioTurnoTrabalho);
                                                    atrasos.add(extra);
                                            }
                                        }

                                //Se horario de entrada antes do turno
                                if(EntradaNoTrabalho < InicioTurnoTrabalho){
                                    //Começou o trabalho antes do turno
                                    
                                        //Verifica se saida antes do final do turno
                                        if(SaidaNoTrabalho < FimTurnoTrabalho){ 
                                            //Saiu antes de terminar o turno
                                            
                                            //verifica se tem outro turno anterior
                                             if(FimTurnoTrabalho > FimPrimeiroTurno && count > 1){
                                                 //Horario contabiliza outro turno
                                             }else{
                                            
                                            System.out.println("\n\nInicioTurnoTrabalho: "+ InicioTurnoTrabalho/60+":"+InicioTurnoTrabalho%60+"\tFinalIntervalo"+FimTurnoTrabalho/60+":"+FimTurnoTrabalho%60);
                                            System.out.println("EntradaNoTrabalho: "+ EntradaNoTrabalho/60+":"+EntradaNoTrabalho%60+"\tSaidaNoTrabalho"+SaidaNoTrabalho/60+":"+SaidaNoTrabalho%60+"");
                            
                                                
                                                //2 - saiu antes do fim do turno e entrou antes
                                                 System.out.println("Atraso 4: "+(SaidaNoTrabalho)/60+":"+((SaidaNoTrabalho)%60)+"  "+ (FimTurnoTrabalho)/60+":"+((FimTurnoTrabalho)%60)+
                                                 "\tTotal de "+(FimTurnoTrabalho-SaidaNoTrabalho)/60+":"+((FimTurnoTrabalho-SaidaNoTrabalho)%60)+" Horas Atrasada");
                                                 
                                                 //Add Hora Extra
                                                    Atraso extra = new Atraso();
                                                    extra.setEntrada(""+(SaidaNoTrabalho)/60+":"+((SaidaNoTrabalho)%60));
                                                    extra.setSaida(""+(FimTurnoTrabalho)/60+":"+((FimTurnoTrabalho)%60));
                                                    extra.setQuantidade(FimTurnoTrabalho-SaidaNoTrabalho);
                                                    atrasos.add(extra);
                                             }
                                            }else{
                                            //Saiu depois de terminar o turno

                                                 
                                        }
                                }
                                
                                 /**
                                 * VERIFICA SE HORA FINAL < HORA INICIAL
                                 */
                                if(FimTurnoTrabalho<InicioTurnoTrabalho){
                                    //Turno Rompe a meia noite
                                    
                                    //
                                }
                        
                                
                        }
                                
                    }
                        
                      
                    
                } catch (NumberFormatException ex){
                 Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
             }
                 //ATUALIZA TABELA ATRASADOS
                  Locale locale = new Locale("pt","BR");
         GregorianCalendar calendar = new GregorianCalendar();
         SimpleDateFormat formatador1 = new SimpleDateFormat("HH:mm");
               DefaultTableModel modelo = (DefaultTableModel)Atraso.getModel();
                modelo.setNumRows(0);
                for(Atraso m: atrasos){
                    
                   
                     
                     try{
                         Date DateInicioTurnoTrabalho = formatador1.parse(m.getEntrada());
                         Date DateEntradaNoTrabalho = formatador1.parse(m.getSaida());
                         
                           String entrada = formatador1.format(DateInicioTurnoTrabalho.getTime());
                           String saida = formatador1.format(DateEntradaNoTrabalho.getTime());
                     
                     

                     
                                               
                     

                modelo.addRow(new Object[]{
                    entrada,
                    saida,
                    m.getQuantidade()/60+" Hora(s) e "+m.getQuantidade()%60+" Minuto(s)"

                        });
                } catch (ParseException ex) {
                         Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
                     }
                }
                
                //ATUALIZA TABELA HORAEXTRA
               DefaultTableModel modelo1 = (DefaultTableModel)HoraExtra.getModel();
                modelo1.setNumRows(0);
                for(HoraExtra m: extras){
                    
                     try{
                         Date DateInicioTurnoTrabalho = formatador1.parse(m.getEntrada());
                         Date DateEntradaNoTrabalho = formatador1.parse(m.getSaida());
                         
                           String entrada = formatador1.format(DateInicioTurnoTrabalho.getTime());
                           String saida = formatador1.format(DateEntradaNoTrabalho.getTime());
                     

                modelo1.addRow(new Object[]{
                    entrada,
                    saida,
                    m.getQuantidade()/60+" Hora(s) e "+m.getQuantidade()%60+" Minuto(s)"

                        });
                    } catch (ParseException ex) {
                         Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
                     }
                }
           }
                
                
               
     }
     
     //Sem Bibliotecas
     public void Subtrainegativos(){
         List<Atraso> atrasos = new ArrayList<>();
         List<HoraExtra> extras = new ArrayList<>();
         
         List<Integer> aberturas = new ArrayList<>();
         List<Integer> fechamentos = new ArrayList<>();
         
         List<HorarioDeTrabalho> intervalos = new ArrayList<>();
         
         List<HorarioDeTrabalho> newhorarios = new ArrayList<>();
         List<MarcacoesFeitas> newhorariosmarcacoes = new ArrayList<>();
         
         
         int PrimeiraEntrada = 0;
         int UltimaSaida = 0;
         int FimPrimeiroTurno = 0;
         
         Locale locale = new Locale("pt","BR");
         GregorianCalendar calendar = new GregorianCalendar();
         SimpleDateFormat formatador1 = new SimpleDateFormat("HH:mm");
         
         int horasamais = 0;
         //Atualiza Horarios
         //System.out.println("HoraTrabalho: ");
          for(HorarioDeTrabalho a : horarios){
              
            int InicioTurno = Integer.valueOf(a.getEntrada().substring(0,2))*60 + Integer.valueOf(a.getEntrada().substring(3,5));
            int FinalTurno = Integer.valueOf(a.getSaida().substring(0,2))*60 + Integer.valueOf(a.getSaida().substring(3,5));
                           
            //System.out.println(a.getEntrada() +" - "+ a.getSaida());
                        boolean antes = true;            
             try {
                 Date Inicio = formatador1.parse(a.getEntrada());
                 Date Final = formatador1.parse(a.getSaida());
                 
                 if(FinalTurno<InicioTurno){ antes = true;  }
                 while(antes){
                     Calendar c = Calendar.getInstance();
                     c.setTime(Final);
                     c.add(Calendar.HOUR, +3);
                     Final = c.getTime();
                     
                     Calendar ca = Calendar.getInstance();
                     ca.setTime(Inicio);
                     ca.add(Calendar.HOUR, +3);
                     Inicio = ca.getTime();
                     horasamais = horasamais+3;
                     //System.out.println(formatador1.format(Inicio.getTime()) +" " + formatador1.format(Final.getTime()));
                     FinalTurno = Integer.valueOf(formatador1.format(Final.getTime()).substring(0,2))*60 + Integer.valueOf(formatador1.format(Final.getTime()).substring(3,5));
                     InicioTurno = Integer.valueOf(formatador1.format(Inicio.getTime()).substring(0,2))*60 + Integer.valueOf(formatador1.format(Inicio.getTime()).substring(3,5));
                     if(FinalTurno>InicioTurno){ 
                         antes = false; 
                         HorarioDeTrabalho atthorario = new HorarioDeTrabalho();
                         atthorario.setEntrada(formatador1.format(Inicio.getTime()));
                         atthorario.setSaida(formatador1.format(Final.getTime()));
                         newhorarios.add(atthorario); }
                 }
                 System.out.println("Horas a mais: "+ horasamais);
             } catch (ParseException ex) {
                 Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
             }
             
              
          }
          //horarios = newhorarios;
          //System.out.println("Marcações: ");
          for(MarcacoesFeitas a : horariosmarcacoes){
              
               //System.out.println(a.getEntrada() +" - "+ a.getSaida());
                                  
             try {
                 Date Inicio = formatador1.parse(a.getEntrada());
                 Date Final = formatador1.parse(a.getSaida());
                 
                     Calendar c = Calendar.getInstance();
                     c.setTime(Final);
                     c.add(Calendar.HOUR, +horasamais);
                     Final = c.getTime();
                     
                     Calendar ca = Calendar.getInstance();
                     ca.setTime(Inicio);
                     ca.add(Calendar.HOUR, +horasamais);
                     Inicio = ca.getTime();
                     
                     //System.out.println(formatador1.format(Inicio.getTime()) +" " + formatador1.format(Final.getTime()));
                     
                     MarcacoesFeitas atthorario = new MarcacoesFeitas();
                         atthorario.setEntrada(formatador1.format(Inicio.getTime()));
                         atthorario.setSaida(formatador1.format(Final.getTime()));
                         newhorariosmarcacoes.add(atthorario);
                 
                
             } catch (ParseException ex) {
                 Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
             }
              
          }
          //horariosmarcacoes = newhorariosmarcacoes;
         
                                    
                                
         
         
         
         
             
            
                 try {
                    
                        int count = horarios.size();
                        //Checa intervalos se count > 2
                        for(HorarioDeTrabalho a : newhorarios){
                            
                            
                            //Organizar Aberturas numa lista
                            aberturas.add(Integer.valueOf(a.getEntrada().substring(0,2))*60 + Integer.valueOf(a.getEntrada().substring(3,5)));
                            //Organizar Fechamentos numa lista
                            fechamentos.add(Integer.valueOf(a.getSaida().substring(0,2))*60 + Integer.valueOf(a.getSaida().substring(3,5)));
                           
                        }
                        //Ordenar a duas listas de forma crescente
                         Collections.sort(aberturas);
                        Collections.sort(fechamentos);
                        
                        /**
                             * Para 3 Turnos --> Intervalo 1 = Menor Fechamento - Segundo Maior Abertura
                             *                   Intervalo 2 = 2 Menor Fechamento -  Maior Abertura
                             *                   Intervalo 3 = ultimo fechamento - primeiro abertura
                             *
                             * Para 2 Turnos --> Intervalo 1 = 2 Maior Fechamento - Maior Abertura
                             *                   Intervalo 2 = ultimo fechamento - primeiro abertura
                             */
                        //Criar intervalos em formato (Entrada,Saida)
                        if(count==3){
                            HorarioDeTrabalho Intervalo = new HorarioDeTrabalho();
                            //Intervalo 1
                            Intervalo.setEntrada(fechamentos.get(0).toString());
                            Intervalo.setSaida(aberturas.get(1).toString());
                            intervalos.add(Intervalo);
                            //Intervalo 2
                            Intervalo.setEntrada(fechamentos.get(1).toString());
                            Intervalo.setSaida(aberturas.get(2).toString());
                            intervalos.add(Intervalo);
                             //Intervalo 3
                            //Intervalo.setEntrada(fechamentos.get(2).toString());
                            //Intervalo.setSaida(aberturas.get(0).toString());
                            //intervalos.add(Intervalo);
                            
                            PrimeiraEntrada = aberturas.get(0);
                            UltimaSaida = fechamentos.get(2);
                            FimPrimeiroTurno = fechamentos.get(0);
                            
                        }
                        if(count==2){
                             HorarioDeTrabalho Intervalo = new HorarioDeTrabalho();
                            //Intervalo 1
                            Intervalo.setEntrada(fechamentos.get(0).toString());
                            Intervalo.setSaida(aberturas.get(1).toString());
                            intervalos.add(Intervalo);
                            //Intervalo 2 
                            //Intervalo.setEntrada(fechamentos.get(1).toString());
                            //Intervalo.setSaida(aberturas.get(0).toString());
                            //intervalos.add(Intervalo);
                            
                            PrimeiraEntrada = aberturas.get(0);
                            UltimaSaida = fechamentos.get(1);
                            FimPrimeiroTurno = fechamentos.get(0);
                            
                        }if(count==1){
                            
                            PrimeiraEntrada = aberturas.get(0);
                            UltimaSaida = fechamentos.get(0);
                            
                        }
                            
                        //Percorre todas as marcações
                        for(MarcacoesFeitas b : newhorariosmarcacoes){
                            int EntradaNoTrabalho = Integer.valueOf(b.getEntrada().substring(0,2))*60+Integer.valueOf(b.getEntrada().substring(3,5));
                            int SaidaNoTrabalho = Integer.valueOf(b.getSaida().substring(0,2))*60+Integer.valueOf(b.getSaida().substring(3,5));
 
                        
                            //Checa se Trabalha nestes intervalos Subtração = (HoraExtra)
                            for(HorarioDeTrabalho pausa : intervalos){
                                int InicioIntervalo = Integer.valueOf(pausa.getEntrada());
                                int FinalIntervalo = Integer.valueOf(pausa.getSaida());
                                
                             //   System.out.println("InicioIntervalo: "+ InicioIntervalo/60+":"+InicioIntervalo%60+"\tFinalIntervalo"+FinalIntervalo/60+":"+FinalIntervalo%60);
                             //   System.out.println("EntradaNoTrabalho: "+ EntradaNoTrabalho/60+":"+EntradaNoTrabalho%60+"\tSaidaNoTrabalho"+SaidaNoTrabalho/60+":"+SaidaNoTrabalho%60+"\n\n");
                                
                               
                                

                                //Se horario de entrada entre intervalo
                                if(InicioIntervalo < EntradaNoTrabalho && EntradaNoTrabalho < FinalIntervalo){
                                    //Começou a trabalhar dentro do intervalo
                                    
                                        //Verifica se saida antes do fim do intervalo
                                        if(SaidaNoTrabalho < FinalIntervalo){
                                             //Terminou o trabalho antes do fim do intervalo
                                             
                                                //Entrou depois do inicio do intervalo e saiu antes do fim do intervalo
                                                 
                                                 int quantidade = SaidaNoTrabalho-EntradaNoTrabalho;
                                                 
                                                 Date DateEntradaNoTrabalho = formatador1.parse(""+(EntradaNoTrabalho)/60+":"+((EntradaNoTrabalho)%60));
                                                 Date DateSaidaNoTrabalho = formatador1.parse(""+(SaidaNoTrabalho)/60+":"+((SaidaNoTrabalho)%60));

                                                        Calendar c = Calendar.getInstance();
                                                        c.setTime(DateEntradaNoTrabalho);
                                                        c.add(Calendar.HOUR, -horasamais);
                                                        DateEntradaNoTrabalho = c.getTime();

                                                        Calendar ca = Calendar.getInstance();
                                                        ca.setTime(DateSaidaNoTrabalho);
                                                        ca.add(Calendar.HOUR, -horasamais);
                                                        DateSaidaNoTrabalho = ca.getTime();
                                                        
                                                 String entrada = formatador1.format(DateEntradaNoTrabalho.getTime());
                                                 String saida = formatador1.format(DateSaidaNoTrabalho.getTime());
                                                        
                                                System.out.println("Hora Extra: "+formatador1.format(DateEntradaNoTrabalho.getTime())+"  "+ formatador1.format(DateSaidaNoTrabalho.getTime()) +
                                                 "\tTotal de "+(SaidaNoTrabalho-EntradaNoTrabalho)/60+":"+((SaidaNoTrabalho-EntradaNoTrabalho)%60)+" Horas Extras");
                                                 
                                                 
                                                 //Add Hora Extra
                                                 HoraExtra extra = new HoraExtra();
                                                    extra.setEntrada(formatador1.format(DateEntradaNoTrabalho.getTime()));
                                                    extra.setSaida(formatador1.format(DateSaidaNoTrabalho.getTime()));
                                                    extra.setQuantidade(quantidade);
                                                    extras.add(extra);
                                   
                                            }else{
                                                //Terminou o trabalho depois do fim do intervalo
                                                
                                                 int quantidade = SaidaNoTrabalho-EntradaNoTrabalho;
                                                 
                                                 Date DateEntradaNoTrabalho = formatador1.parse(""+(EntradaNoTrabalho)/60+":"+((EntradaNoTrabalho)%60));
                                                 Date DateFinalIntervalo = formatador1.parse(""+(SaidaNoTrabalho)/60+":"+((SaidaNoTrabalho)%60));

                                                        Calendar c = Calendar.getInstance();
                                                        c.setTime(DateEntradaNoTrabalho);
                                                        c.add(Calendar.HOUR, -horasamais);
                                                        DateEntradaNoTrabalho = c.getTime();

                                                        Calendar ca = Calendar.getInstance();
                                                        ca.setTime(DateFinalIntervalo);
                                                        ca.add(Calendar.HOUR, -horasamais);
                                                        DateFinalIntervalo = ca.getTime();

                                                    //Entrou depois do inicio do intervalo e saiu depois do fim do intervalo
                                                      System.out.println("Hora Extra: "+b.getEntrada()+"  "+ pausa.getSaida() +
                                                      "\tTotal de "+(FinalIntervalo-EntradaNoTrabalho)/60+":"+((FinalIntervalo-EntradaNoTrabalho)%60)+" Horas Extras");
                                                      //Add Hora Extra
                                                  HoraExtra extra = new HoraExtra();
                                                    extra.setEntrada(formatador1.format(DateEntradaNoTrabalho.getTime()));
                                                    extra.setSaida(formatador1.format(DateFinalIntervalo.getTime()));
                                                    extra.setQuantidade(FinalIntervalo-EntradaNoTrabalho);
                                                    extras.add(extra);
                                            }
                                        }

                                //Se horario de entrada antes do intervalo
                                if(EntradaNoTrabalho < InicioIntervalo){
                                    //Começou o trabalho antes do intervalo
                                    
                                        //Verifica se saida antes do inicio do intervalo
                                        if(SaidaNoTrabalho < InicioIntervalo){
                                            //Não trabalhou no intervalo

                                            }else{
                                            //Trabalhou no intervalo

                                                 //Verifica se saida antes do fim do intervalo 
                                                 if(SaidaNoTrabalho < FinalIntervalo){
                                                    //Terminou o trabalho antes do fim do intervalo
                                                    
                                                    Date DateInicioIntervalo = formatador1.parse(""+(InicioIntervalo)/60+":"+((InicioIntervalo)%60));
                                                 Date DateSaidaNoTrabalho = formatador1.parse(""+(SaidaNoTrabalho)/60+":"+((SaidaNoTrabalho)%60));

                                                        Calendar c = Calendar.getInstance();
                                                        c.setTime(DateInicioIntervalo);
                                                        c.add(Calendar.HOUR, -horasamais);
                                                        DateInicioIntervalo = c.getTime();

                                                        Calendar ca = Calendar.getInstance();
                                                        ca.setTime(DateSaidaNoTrabalho);
                                                        ca.add(Calendar.HOUR, -horasamais);
                                                        DateSaidaNoTrabalho = ca.getTime();
                                                        
                                                 String entrada = formatador1.format(DateInicioIntervalo.getTime());
                                                 String saida = formatador1.format(DateSaidaNoTrabalho.getTime());

                                                        //Entrou antes do inicio do intervalo e saiu antes do fim do intervalo
                                                        System.out.println("Hora Extra: "+entrada+"  "+ saida+
                                                      "\tTotal de "+(SaidaNoTrabalho-InicioIntervalo)/60+":"+((SaidaNoTrabalho-InicioIntervalo)%60)+" Horas Extras");
                                                         //Add Hora Extra
                                                  HoraExtra extra = new HoraExtra();
                                                    extra.setEntrada(entrada);
                                                    extra.setSaida(saida);
                                                    extra.setQuantidade(SaidaNoTrabalho-InicioIntervalo);
                                                    extras.add(extra);
                                                   }else{
                                                       //Terminou o trabalho depois do fim do intervalo
                                                       
                                                       Date DateInicioIntervalo = formatador1.parse(""+(InicioIntervalo)/60+":"+((InicioIntervalo)%60));
                                                 Date DateFinalIntervalo = formatador1.parse(""+(FinalIntervalo)/60+":"+((FinalIntervalo)%60));

                                                        Calendar c = Calendar.getInstance();
                                                        c.setTime(DateInicioIntervalo);
                                                        c.add(Calendar.HOUR, -horasamais);
                                                        DateInicioIntervalo = c.getTime();

                                                        Calendar ca = Calendar.getInstance();
                                                        ca.setTime(DateFinalIntervalo);
                                                        ca.add(Calendar.HOUR, -horasamais);
                                                        DateFinalIntervalo = ca.getTime();
                                                        
                                                 String entrada = formatador1.format(DateInicioIntervalo.getTime());
                                                 String saida = formatador1.format(DateFinalIntervalo.getTime());

                                                       //Trabalhou todo o intervalo
                                                        //Entrou antes do inicio do intervalo e saiu antes do fim do intervalo
                                                        System.out.println("Hora Extra: "+InicioIntervalo/60+":"+InicioIntervalo%60+"  "+FinalIntervalo/60+":"+FinalIntervalo%60 +
                                                      "\tTotal de "+(FinalIntervalo-InicioIntervalo)/60+":"+((FinalIntervalo-InicioIntervalo)%60)+" Horas Extras");
                                                        //Add Hora Extra
                                                        HoraExtra extra = new HoraExtra();
                                                        extra.setEntrada(entrada);
                                                        extra.setSaida(saida);
                                                        extra.setQuantidade(FinalIntervalo-InicioIntervalo);
                                                        extras.add(extra);
                                                   }
                                        }
                                }
                                
                                
                                
                               
                               
                            }
                            
                            //Chega se trabalha antes dos turnos
                            if(EntradaNoTrabalho < PrimeiraEntrada){
                                //Entrou antes dos primeiro turno
                                
                                Date DateEntradaNoTrabalho = formatador1.parse(""+(EntradaNoTrabalho)/60+":"+((EntradaNoTrabalho)%60));
                                Date DatePrimeiraEntrada = formatador1.parse(""+(PrimeiraEntrada)/60+":"+((PrimeiraEntrada)%60));

                                                        Calendar c = Calendar.getInstance();
                                                        c.setTime(DateEntradaNoTrabalho);
                                                        c.add(Calendar.HOUR, -horasamais);
                                                        DateEntradaNoTrabalho = c.getTime();

                                                        Calendar ca = Calendar.getInstance();
                                                        ca.setTime(DatePrimeiraEntrada);
                                                        ca.add(Calendar.HOUR, -horasamais);
                                                        DatePrimeiraEntrada = ca.getTime();
                                
                                System.out.println("Hora Extra Antes: "+formatador1.format(DateEntradaNoTrabalho.getTime())+"  "+formatador1.format(DatePrimeiraEntrada.getTime()) +
                                                      "\tTotal de "+(PrimeiraEntrada-EntradaNoTrabalho)/60+":"+((PrimeiraEntrada-EntradaNoTrabalho)%60)+" Horas Extras");
                                        //Add Hora Extra
                                                        HoraExtra extra = new HoraExtra();
                                                        extra.setEntrada(formatador1.format(DateEntradaNoTrabalho.getTime()));
                                                        extra.setSaida(formatador1.format(DatePrimeiraEntrada.getTime()));
                                                        extra.setQuantidade(PrimeiraEntrada-EntradaNoTrabalho);
                                                        extras.add(extra);                       
                            }
                              
                            //Checa se trabalha depois dos turnos
                            if(UltimaSaida < SaidaNoTrabalho){
                                //Entrou antes dos primeiro turno
                                
                                     Date DateUltimaSaida = formatador1.parse(""+(UltimaSaida)/60+":"+((UltimaSaida)%60));
                                                 Date DateSaidaNoTrabalho = formatador1.parse(""+(SaidaNoTrabalho)/60+":"+((SaidaNoTrabalho)%60));

                                                        Calendar c = Calendar.getInstance();
                                                        c.setTime(DateUltimaSaida);
                                                        c.add(Calendar.HOUR, -horasamais);
                                                        DateUltimaSaida = c.getTime();

                                                        Calendar ca = Calendar.getInstance();
                                                        ca.setTime(DateSaidaNoTrabalho);
                                                        ca.add(Calendar.HOUR, -horasamais);
                                                        DateSaidaNoTrabalho = ca.getTime();
                                                        
                                                 String entrada = formatador1.format(DateUltimaSaida.getTime());
                                                 String saida = formatador1.format(DateSaidaNoTrabalho.getTime());
                                
                                System.out.println("Hora Extra Depois: "+UltimaSaida/60+":"+UltimaSaida%60+"  "+SaidaNoTrabalho/60+":"+SaidaNoTrabalho%60 +
                                                      "\tTotal de "+(SaidaNoTrabalho-UltimaSaida)/60+":"+((SaidaNoTrabalho-UltimaSaida)%60)+" Horas Extras");
                                        //Add Hora Extra
                                                        HoraExtra extra = new HoraExtra();
                                                        extra.setEntrada(entrada);
                                                        extra.setSaida(saida);
                                                        extra.setQuantidade(SaidaNoTrabalho-UltimaSaida);
                                                        extras.add(extra);                       
                            }
                            
                        }
                    
                        
           //----------------------------------------------------------------------------------------------------//
                      
                     
                    //Checa se Não Trabalha no Turnos Subtração = -(Atraso)
                    for(HorarioDeTrabalho turno : newhorarios){
                        
                        
                        int InicioTurnoTrabalho = Integer.valueOf(turno.getEntrada().substring(0,2))*60+Integer.valueOf(turno.getEntrada().substring(3,5));
                        int FimTurnoTrabalho = Integer.valueOf(turno.getSaida().substring(0,2))*60+Integer.valueOf(turno.getSaida().substring(3,5));
                                 
                        for(MarcacoesFeitas b : newhorariosmarcacoes){
                            
                            int EntradaNoTrabalho = Integer.valueOf(b.getEntrada().substring(0,2))*60+Integer.valueOf(b.getEntrada().substring(3,5));
                            int SaidaNoTrabalho = Integer.valueOf(b.getSaida().substring(0,2))*60+Integer.valueOf(b.getSaida().substring(3,5));

                            
                            //System.out.println("InicioTurnoTrabalho: "+ InicioTurnoTrabalho/60+":"+InicioTurnoTrabalho%60+"\tFinalIntervalo"+FimTurnoTrabalho/60+":"+FimTurnoTrabalho%60);
                            //System.out.println("EntradaNoTrabalho: "+ EntradaNoTrabalho/60+":"+EntradaNoTrabalho%60+"\tSaidaNoTrabalho"+SaidaNoTrabalho/60+":"+SaidaNoTrabalho%60+"\n\n");
                            
                            
                            
                            //Se horario de entrada entre turno
                                if(InicioTurnoTrabalho < EntradaNoTrabalho && EntradaNoTrabalho < FimTurnoTrabalho){
                                    //Começou a trabalhar dentro do turno
                                    
                                        //Verifica se saida antes do fim do turno
                                        if(SaidaNoTrabalho < FimTurnoTrabalho){
                                             //Terminou o trabalho antes do fim do turno
                                             
                                                //1 -Entrou depois do inicio do turno 
                                                 Date DateInicioTurnoTrabalho = formatador1.parse(""+(InicioTurnoTrabalho)/60+":"+((InicioTurnoTrabalho)%60));
                                                 Date DateEntradaNoTrabalho = formatador1.parse(""+(EntradaNoTrabalho)/60+":"+((EntradaNoTrabalho)%60));

                                                        Calendar c = Calendar.getInstance();
                                                        c.setTime(DateInicioTurnoTrabalho);
                                                        c.add(Calendar.HOUR, -horasamais);
                                                        DateInicioTurnoTrabalho = c.getTime();

                                                        Calendar ca = Calendar.getInstance();
                                                        ca.setTime(DateEntradaNoTrabalho);
                                                        ca.add(Calendar.HOUR, -horasamais);
                                                        DateEntradaNoTrabalho = ca.getTime();
                                                        
                                                 String entrada = formatador1.format(DateInicioTurnoTrabalho.getTime());
                                                 String saida = formatador1.format(DateEntradaNoTrabalho.getTime());
                                                 
                                                 System.out.println("Atraso 1: "+entrada+"  "+ saida+
                                                 "\tTotal de "+(EntradaNoTrabalho-InicioTurnoTrabalho)/60+":"+((EntradaNoTrabalho-InicioTurnoTrabalho)%60)+" Horas Atrasada");
                                                 
                                                 //Add Hora Extra
                                                 Atraso extra = new Atraso();
                                                    extra.setEntrada(entrada);
                                                    extra.setSaida(saida);
                                                    extra.setQuantidade(EntradaNoTrabalho-InicioTurnoTrabalho);
                                                    atrasos.add(extra);
                                                    
                                                    
                                                 //2 - e saiu antes do fim do turno
                                                  Date DateSaidaNoTrabalho = formatador1.parse(""+(SaidaNoTrabalho)/60+":"+((SaidaNoTrabalho)%60));
                                                 Date DateFimTurnoTrabalho = formatador1.parse(""+(FimTurnoTrabalho)/60+":"+((FimTurnoTrabalho)%60));

                                                        Calendar c1 = Calendar.getInstance();
                                                        c1.setTime(DateSaidaNoTrabalho);
                                                        c1.add(Calendar.HOUR, -horasamais);
                                                        DateSaidaNoTrabalho = c.getTime();

                                                        Calendar ca1 = Calendar.getInstance();
                                                        ca1.setTime(DateFimTurnoTrabalho);
                                                        ca1.add(Calendar.HOUR, -horasamais);
                                                        DateFimTurnoTrabalho = ca.getTime();
                                                        
                                                 String entrada1 = formatador1.format(DateSaidaNoTrabalho.getTime());
                                                 String saida1 = formatador1.format(DateFimTurnoTrabalho.getTime());
                                                 
                                                 System.out.println("Atraso 2: "+(SaidaNoTrabalho)/60+":"+((SaidaNoTrabalho)%60)+"  "+ (FimTurnoTrabalho)/60+":"+((FimTurnoTrabalho)%60)+
                                                 "\tTotal de "+(FimTurnoTrabalho-SaidaNoTrabalho)/60+":"+((FimTurnoTrabalho-SaidaNoTrabalho)%60)+" Horas Atrasada");
                                                 
                                                 //Add Hora Extra
                                                  Atraso extra2 = new Atraso();
                                                    extra2.setEntrada(entrada1);
                                                    extra2.setSaida(saida1);
                                                    extra2.setQuantidade(FimTurnoTrabalho-SaidaNoTrabalho);
                                                    atrasos.add(extra2);
                                   
                                            }else{
                                                //Terminou o trabalho depois do fim do turno
                                                
                                                Date DateInicioTurnoTrabalho = formatador1.parse(""+(InicioTurnoTrabalho)/60+":"+((InicioTurnoTrabalho)%60));
                                                 Date DateEntradaNoTrabalho = formatador1.parse(""+(EntradaNoTrabalho)/60+":"+((EntradaNoTrabalho)%60));

                                                        Calendar c = Calendar.getInstance();
                                                        c.setTime(DateInicioTurnoTrabalho);
                                                        c.add(Calendar.HOUR, -horasamais);
                                                        DateInicioTurnoTrabalho = c.getTime();

                                                        Calendar ca = Calendar.getInstance();
                                                        ca.setTime(DateEntradaNoTrabalho);
                                                        ca.add(Calendar.HOUR, -horasamais);
                                                        DateEntradaNoTrabalho = ca.getTime();
                                                        
                                                 String entrada = formatador1.format(DateInicioTurnoTrabalho.getTime());
                                                 String saida = formatador1.format(DateEntradaNoTrabalho.getTime());

                                                    //1 -Entrou depois do inicio do turno e saiu antes
                                                      System.out.println("Atraso 3: "+(InicioTurnoTrabalho)/60+":"+((InicioTurnoTrabalho)%60)+"  "+ (EntradaNoTrabalho)/60+":"+((EntradaNoTrabalho)%60)+
                                                 "\tTotal de "+(EntradaNoTrabalho-InicioTurnoTrabalho)/60+":"+((EntradaNoTrabalho-InicioTurnoTrabalho)%60)+" Horas Atrasada");
                                                 
                                                 //Add Hora Extra
                                                 Atraso extra = new Atraso();
                                                    extra.setEntrada(entrada);
                                                    extra.setSaida(saida);
                                                    extra.setQuantidade(EntradaNoTrabalho-InicioTurnoTrabalho);
                                                    atrasos.add(extra);
                                            }
                                        }

                                //Se horario de entrada antes do turno
                                if(EntradaNoTrabalho < InicioTurnoTrabalho){
                                    //Começou o trabalho antes do turno
                                    
                                        //Verifica se saida antes do final do turno
                                        if(SaidaNoTrabalho < FimTurnoTrabalho){ 
                                            //Saiu antes de terminar o turno
                                            
                                            //verifica se tem outro turno anterior
                                             if(FimTurnoTrabalho > FimPrimeiroTurno && count > 1){
                                                 //Horario contabiliza outro turno
                                             }else{
                                            
                                           Date DateSaidaNoTrabalho = formatador1.parse(""+(SaidaNoTrabalho)/60+":"+((SaidaNoTrabalho)%60));
                                            Date DateFimTurnoTrabalho = formatador1.parse(""+(FimTurnoTrabalho)/60+":"+((FimTurnoTrabalho)%60));

                                                        Calendar c = Calendar.getInstance();
                                                        c.setTime(DateSaidaNoTrabalho);
                                                        c.add(Calendar.HOUR, -horasamais);
                                                        DateSaidaNoTrabalho = c.getTime();

                                                        Calendar ca = Calendar.getInstance();
                                                        ca.setTime(DateFimTurnoTrabalho);
                                                        ca.add(Calendar.HOUR, -horasamais);
                                                        DateFimTurnoTrabalho = ca.getTime();
                                                
                                                //2 - saiu antes do fim do turno e entrou antes
                                                 System.out.println("Atraso 4: "+formatador1.format(DateSaidaNoTrabalho.getTime())+"  "+ formatador1.format(DateFimTurnoTrabalho.getTime())+
                                                 "\tTotal de "+(FimTurnoTrabalho-SaidaNoTrabalho)/60+":"+((FimTurnoTrabalho-SaidaNoTrabalho)%60)+" Horas Atrasada");
                                                 
                                                 //Add Hora Extra
                                                    Atraso extra = new Atraso();
                                                    extra.setEntrada(formatador1.format(DateSaidaNoTrabalho.getTime()));
                                                    extra.setSaida(formatador1.format(DateFimTurnoTrabalho.getTime()));
                                                    extra.setQuantidade(FimTurnoTrabalho-SaidaNoTrabalho);
                                                    atrasos.add(extra);
                                             }
                                            }else{
                                            //Saiu depois de terminar o turno

                                                 
                                        }
                                }
                                
                                
                        
                                
                        }
                                
                    }
                    
               
                
                        
                      
                    
                } catch (NumberFormatException ex){
                 Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
             } catch (ParseException ex) {
             Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
         }finally{
                     
                     
                
                     
                 }
             
                      //ATUALIZA TABELA ATRASADOS
               DefaultTableModel modelo = (DefaultTableModel)Atraso.getModel();
                modelo.setNumRows(0);
                for(Atraso m: atrasos){
                    
                System.out.println("Atraso: "+m.getEntrada() + " " + m.getSaida());
                    
                modelo.addRow(new Object[]{
                    m.getEntrada(),
                    m.getSaida(),
                     m.getQuantidade()/60+" Hora(s) e "+m.getQuantidade()%60+" Minuto(s)"

                        });
                }
                
                //ATUALIZA TABELA HORAEXTRA
               DefaultTableModel modelo1 = (DefaultTableModel)HoraExtra.getModel();
                modelo1.setNumRows(0);
                for(HoraExtra m: extras){
                    
                    System.out.println("Extras: "+m.getEntrada() + " " + m.getSaida());

                modelo1.addRow(new Object[]{
                    m.getEntrada(),
                    m.getSaida(),
                     m.getQuantidade()/60+" Hora(s) e "+m.getQuantidade()%60+" Minuto(s)"

                        });
                }
                
              
         
     }
     
     public void PreencheTabelas(){
          SubtracaoEntreHorarios a = new SubtracaoEntreHorarios();
         try {
             ResultSubtracao result  = a.CalculaHorarios(horarios, horariosmarcacoes);
             
                    //ATUALIZA TABELA ATRASADOS
               DefaultTableModel modelo = (DefaultTableModel)Atraso.getModel();
                modelo.setNumRows(0);
                for(Atraso m: result.getAtrasos()){
                    
                   
                modelo.addRow(new Object[]{
                    m.getEntrada(),
                    m.getSaida(),
                     (m.getQuantidade()*(-1))/60+" Hora(s) e "+m.getQuantidade()%60+" Minuto(s)"

                        });
                }
                
                //ATUALIZA TABELA HORAEXTRA
               DefaultTableModel modelo1 = (DefaultTableModel)HoraExtra.getModel();
                modelo1.setNumRows(0);
                for(HoraExtra m: result.getExtras()){
                    
               
                modelo1.addRow(new Object[]{
                    m.getEntrada(),
                    m.getSaida(),
                     (m.getQuantidade()*(-1))/60+" Hora(s) e "+m.getQuantidade()%60+" Minuto(s)"

                        });
                }
        
        
        
         } catch (ParseException ex) {
             Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
         }
         
     }
     

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        HorarioDeTrabalho = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        MarcacoesFeitas = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Atraso = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        HoraExtra = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Test Ponto");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setBackground(new java.awt.Color(204, 41, 69));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 41, 69));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test.png"))); // NOI18N
        jLabel5.setText(" TABELAS");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTabbedPane1.setRequestFocusEnabled(false);
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        HorarioDeTrabalho.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        HorarioDeTrabalho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Entrada ", "Saida"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(HorarioDeTrabalho);

        jLabel10.setBackground(new java.awt.Color(204, 41, 69));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Edição Horario de Trabalho");
        jLabel10.setOpaque(true);

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("Entrada");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("Saida ");

        jFormattedTextField3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        jFormattedTextField3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jFormattedTextField3.setPreferredSize(new java.awt.Dimension(7, 30));
        jFormattedTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField3ActionPerformed(evt);
            }
        });
        jFormattedTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextField3KeyPressed(evt);
            }
        });

        jFormattedTextField4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        jFormattedTextField4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jFormattedTextField4.setPreferredSize(new java.awt.Dimension(135, 30));

        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton7.setText("Cancelar");
        jButton7.setBorderPainted(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(204, 41, 69));
        jButton8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add-button-with-plus-symbol-in-a-black-circle.png"))); // NOI18N
        jButton8.setText("Salvar");
        jButton8.setBorderPainted(false);
        jButton8.setDefaultCapable(false);
        jButton8.setFocusPainted(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(204, 55, 59));
        jButton9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/actualize-arrows-couple-in-circle.png"))); // NOI18N
        jButton9.setText("Atualizar");
        jButton9.setBorderPainted(false);
        jButton9.setDefaultCapable(false);
        jButton9.setFocusPainted(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton10.setText("Apagar");
        jButton10.setBorderPainted(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 41, 69));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adf.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addGap(18, 18, 18)
                                .addComponent(jButton8)))
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton8)
                            .addComponent(jButton7)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton10))
                .addGap(71, 71, 71))
        );

        jTabbedPane1.addTab("Horario De Trabalho ", jPanel5);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        MarcacoesFeitas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MarcacoesFeitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Entrada ", "Saida"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(MarcacoesFeitas);

        jLabel7.setBackground(new java.awt.Color(204, 41, 69));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Marcações");
        jLabel7.setOpaque(true);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Entrada");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Saida ");

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        jFormattedTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jFormattedTextField1.setPreferredSize(new java.awt.Dimension(7, 30));
        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });

        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        jFormattedTextField2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jFormattedTextField2.setPreferredSize(new java.awt.Dimension(7, 30));

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setText("Cancelar");
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(204, 41, 69));
        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add-button-with-plus-symbol-in-a-black-circle.png"))); // NOI18N
        jButton5.setText("Salvar");
        jButton5.setBorderPainted(false);
        jButton5.setDefaultCapable(false);
        jButton5.setFocusPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(204, 55, 59));
        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/actualize-arrows-couple-in-circle.png"))); // NOI18N
        jButton6.setText("Atualizar");
        jButton6.setBorderPainted(false);
        jButton6.setDefaultCapable(false);
        jButton6.setFocusPainted(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton11.setText("Apagar");
        jButton11.setBorderPainted(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 41, 69));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/specialist-user.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setFocusPainted(false);
        jButton2.setFocusable(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton5)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Marcacoes Feitas", jPanel4);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        Atraso.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Atraso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Entrada ", "Saida", "Qtd Hora Atraso"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(Atraso);

        jLabel13.setBackground(new java.awt.Color(204, 41, 69));
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Atrasos");
        jLabel13.setOpaque(true);

        jButton12.setBackground(new java.awt.Color(204, 41, 69));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/megaphone.png"))); // NOI18N
        jButton12.setBorderPainted(false);
        jButton12.setFocusPainted(false);
        jButton12.setFocusable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton12)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Atrasos", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        HoraExtra.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        HoraExtra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Entrada ", "Saida", "Qtd Hora Extra"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(HoraExtra);

        jLabel14.setBackground(new java.awt.Color(204, 41, 69));
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Horas Extra");
        jLabel14.setOpaque(true);

        jButton3.setBackground(new java.awt.Color(204, 41, 69));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dollar-symbol.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setFocusPainted(false);
        jButton3.setFocusable(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hora Extra ", jPanel7);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cleaning.png"))); // NOI18N
        jButton13.setToolTipText("Apagar Registros");
        jButton13.setBorderPainted(false);
        jButton13.setContentAreaFilled(false);
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton13.setFocusable(false);
        jButton13.setRequestFocusEnabled(false);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton13)
                        .addGap(48, 48, 48))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       preparaproximoMarcacoes();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        preparaproximo();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
          AdicionarHorarioDeTrabalho();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
       AtualizaHorarioDeTrabalho();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        ApagarHorarioDeTrabalho();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        AdicionarMarcacoesFeitas();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        ApagarMarcacoesFeitas();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
      AtualizaMarcacoesFeitas();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
       javax.swing.JTabbedPane pane = (javax.swing.JTabbedPane) evt.getSource();
                            
                            if(pane.getSelectedIndex()==1){
                                 readjTableMarcacoesFeitas();
                            }
                            if(pane.getSelectedIndex()==2){
                                 readjTableAtrasos();
                            }
                            if(pane.getSelectedIndex()==3 ){
                                 readjTableAtrasos();
                            }
                        
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
       Connector.creatdatabase();
        readjTableHorarioDeTrabalho();
          readjTableMarcacoesFeitas();
          DefaultTableModel modelo = (DefaultTableModel)Atraso.getModel();
                modelo.setNumRows(0);
               
                
                //ATUALIZA TABELA HORAEXTRA
               DefaultTableModel modelo1 = (DefaultTableModel)HoraExtra.getModel();
                modelo1.setNumRows(0);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jFormattedTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField3KeyPressed
        
    }//GEN-LAST:event_jFormattedTextField3KeyPressed

    private void jFormattedTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField3ActionPerformed
       AdicionarHorarioDeTrabalho();
    }//GEN-LAST:event_jFormattedTextField3ActionPerformed

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
         AdicionarMarcacoesFeitas();
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Atraso;
    private javax.swing.JTable HoraExtra;
    private javax.swing.JTable HorarioDeTrabalho;
    private javax.swing.JTable MarcacoesFeitas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JFormattedTextField jFormattedTextField4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
