/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marcosgabriell.testeinsightinformtica.modulo;

import com.marcosgabriell.testeinsightinformtica.dao.HorarioDeTrabalhoDao;
import com.marcosgabriell.testeinsightinformtica.dao.MarcacoesFeitasDao;
import com.marcosgabriell.testeinsightinformtica.model.Atraso;
import com.marcosgabriell.testeinsightinformtica.model.HoraExtra;
import com.marcosgabriell.testeinsightinformtica.model.HorarioDeTrabalho;
import com.marcosgabriell.testeinsightinformtica.model.MarcacoesFeitas;
import com.marcosgabriell.testeinsightinformtica.model.ResultSubtracao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Marcos
 */
public class SubtracaoEntreHorarios {
    List<HorarioDeTrabalho> horarios = new ArrayList<>();
      List<MarcacoesFeitas> horariosmarcacoes = new ArrayList<>();
    
   
    
    public ResultSubtracao CalculaHorarios(List<HorarioDeTrabalho> HorariosDeTrabalho,List<MarcacoesFeitas> MarcacoesFeitas ) throws ParseException{
         
         
        
         SimpleDateFormat formatador1 = new SimpleDateFormat("HH:mm");
         SimpleDateFormat formatador2 = new SimpleDateFormat("HH:mm dd/MMM");
         
         List<Atraso> atrasos = new ArrayList<>();
         List<HoraExtra> extras = new ArrayList<>();
         
         List<Interval> Turnos = new ArrayList<>();
         List<Interval> Trabalhos = new ArrayList<>();
         
         List<Interval> Intervalos = new ArrayList<>();
          List<Interval> all = new ArrayList<>();
         
        Calendar c = Calendar.getInstance();
         boolean TurnoMadrugada = false;
        
         for(HorarioDeTrabalho a : HorariosDeTrabalho){
                 int f = Integer.valueOf(a.getSaida().substring(0,2))*60 + Integer.valueOf(a.getSaida().substring(3,5));
                 int i = Integer.valueOf(a.getEntrada().substring(0,2))*60 + Integer.valueOf(a.getEntrada().substring(3,5));
                    
                 if(i>f){
                     TurnoMadrugada = true;
                 }
         }
         
         
               
       if(TurnoMadrugada){
            for(HorarioDeTrabalho a : HorariosDeTrabalho){
             try {
                 
                 Date Inicio = formatador1.parse(a.getEntrada());
                 Date Final = formatador1.parse(a.getSaida());
                 
                 //Checa se vira o dia
                   if(Inicio.after(Final)){
                       
                      
                        c.setTime(Final);
                        c.add(Calendar.DAY_OF_YEAR, +1);
                        Final = c.getTime();
                      TurnoMadrugada = true;
                       
                   }
                 
                 Interval interval = new Interval(Inicio.getTime(), Final.getTime());
                 
                 Turnos.add(interval);
                 all.add(interval);
                 
                 } catch (ParseException ex) {
                 Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        for(MarcacoesFeitas a : MarcacoesFeitas){
             try {
                 
                 Date Inicio = formatador1.parse(a.getEntrada());
                 Date Final = formatador1.parse(a.getSaida());
                 
                 //Checa se vira o dia
                   if(Inicio.after(Final)){
                       //Atualiza dia da Hora Para o dia seginte
                        c.setTime(Final);
                        c.add(Calendar.DAY_OF_YEAR, +1);
                        Final = c.getTime();
                   }else{
                       c.setTime(Inicio);
                        c.add(Calendar.DAY_OF_YEAR, +1);
                        Inicio = c.getTime();
                        c.setTime(Final);
                        c.add(Calendar.DAY_OF_YEAR, +1);
                        Final = c.getTime();
                       
                   }
                 
                 Interval interval = new Interval(Inicio.getTime(), Final.getTime());
                 
                 Trabalhos.add(interval);
                 all.add(interval);
                 
                 } catch (ParseException ex) {
                 Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
       }else{
            for(HorarioDeTrabalho a : HorariosDeTrabalho){
             try {
                 
                 Date Inicio = formatador1.parse(a.getEntrada());
                 Date Final = formatador1.parse(a.getSaida());
                 
                 
                 
                 
                 Interval interval = new Interval(Inicio.getTime(), Final.getTime());
                 
                 Turnos.add(interval);
                 all.add(interval);
                 
                 } catch (ParseException ex) {
                 Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        for(MarcacoesFeitas a : MarcacoesFeitas){
             try {
                 
                 Date Inicio = formatador1.parse(a.getEntrada());
                 Date Final = formatador1.parse(a.getSaida());
                 
                 
                 
                 Interval interval = new Interval(Inicio.getTime(), Final.getTime());
                 
                 Trabalhos.add(interval);
                 all.add(interval);
                 
                 } catch (ParseException ex) {
                 Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
       }
                           
       
        
        
        
        if(TurnoMadrugada){
            
            //Ordenar Lista Crescente
             Collections.sort(Turnos, new IntervalStartComparator());
            //Primeiro dia ás 00
            DateTime InicioIntervalo = Turnos.get(0).getStart();
            //Ultimo dia ás 23:59
            DateTime FinalIntervalo = Turnos.get(0).getEnd();
            //Get Intervalos = Pausas
            c.setTime(formatador1.parse("23:59"));
            c.add(Calendar.DAY_OF_YEAR, +1);
            
            Interval interval = new Interval(formatador1.parse("00:00").getTime(), c.getTime().getTime());
            all.add(interval);
            Intervalos = findGaps(Turnos,interval);
            System.out.println("Intervalos: "+Intervalos);
            
        }else{
            //Get Intervalos = Pausas
            Interval interval = new Interval(formatador1.parse("00:00").getTime(), formatador1.parse("23:59").getTime());
            all.add(interval);
            Intervalos = findGaps(Turnos,interval);
            System.out.println("Intervalos: "+Intervalos);
        }
         
         //VERIFICA EXTRAS
         for(Interval searchInterval : Intervalos){
             
             for(Interval trabalhou : Trabalhos){
                 
                   System.out.println("InicioIntervalo: "+ formatador1.format(searchInterval.getStart().getMillis())
                                  +"\tFinalIntervalo"+formatador1.format(searchInterval.getEnd().getMillis()));
                   System.out.println("EntradaNoTrabalho: "+ formatador1.format(trabalhou.getStart().getMillis())
                                   +"\tSaidaNoTrabalho"+formatador1.format(trabalhou.getEnd().getMillis())+"\n\n");
                   
                                
                 
                 //Entrou depois do inicio do intervalo e saiu antes do fim do intervalo
                  if (trabalhou.getStart().isAfter(searchInterval.getStart()) && trabalhou.getEnd().isBefore(searchInterval.getEnd())) {
                      try{
                      Interval intervalo = new Interval(trabalhou.getStart(), trabalhou.getEnd());
                       System.out.println("EXTRA 1: "  + formatador1.format(intervalo.getStart().getMillis()) +" "
                                                          + formatador1.format(intervalo.getEnd().getMillis()));
                       
                        HoraExtra extra = new HoraExtra();
                        extra.setEntrada(formatador1.format(intervalo.getStart().getMillis()));
                        extra.setSaida(formatador1.format(intervalo.getEnd().getMillis()));
                        extra.setQuantidade(Math.toIntExact(
                                TimeUnit.MILLISECONDS.toMinutes( intervalo.getStart().toInstant().toDate().getTime() 
                                                                                    - intervalo.getEnd().toInstant().toDate().getTime()
                                                                )
                                                            ));
                        extras.add(extra);
                       
                      }catch( java.lang.IllegalArgumentException e){}
                    }
                  //Entrou depois do inicio do intervalo e saiu depois do fim do intervalo
                  if (trabalhou.getStart().isAfter(searchInterval.getStart()) && trabalhou.getEnd().isAfter(searchInterval.getEnd())) {
                     
                      if(!(searchInterval.getEnd().toInstant().toDate().getTime() - trabalhou.getStart().toInstant().toDate().getTime() ==0)
                      && !(searchInterval.getStart().toInstant().toDate().getTime() - trabalhou.getEnd().toInstant().toDate().getTime() ==0)){
                       
                      try{
                      Interval intervalo = new Interval(trabalhou.getStart(), searchInterval.getEnd());
                       System.out.println("EXTRA 2: "  + formatador1.format(intervalo.getStart().getMillis()) +" "
                                                          + formatador1.format(intervalo.getEnd().getMillis()));
                       
                       HoraExtra extra = new HoraExtra();
                        extra.setEntrada(formatador1.format(intervalo.getStart().getMillis()));
                        extra.setSaida(formatador1.format(intervalo.getEnd().getMillis()));
                        extra.setQuantidade(Math.toIntExact(
                                TimeUnit.MILLISECONDS.toMinutes( intervalo.getStart().toInstant().toDate().getTime() 
                                                                                    - intervalo.getEnd().toInstant().toDate().getTime()
                                                                )
                                                            ));
                        extras.add(extra);
                      }catch( java.lang.IllegalArgumentException e){}
                      
                     }else{
                          
                      }
                    }
                  //Entrou antes do inicio do intervalo e saiu antes do fim do intervalo
                  if ( trabalhou.getStart().isBefore(searchInterval.getStart()) && trabalhou.getEnd().isBefore(searchInterval.getEnd()) ) {
                      //Verifica se final trabalho diferente inicio intervalo
                      
                      if(!(searchInterval.getStart().toInstant().toDate().getTime() - trabalhou.getEnd().toInstant().toDate().getTime() ==0)){
                          
                      
                      try{
                      Interval intervalo = new Interval(searchInterval.getStart(), trabalhou.getEnd());
                       System.out.println("EXTRA 3: "  + formatador1.format(intervalo.getStart().getMillis()) +" "
                                                          + formatador1.format(intervalo.getEnd().getMillis()));
                       HoraExtra extra = new HoraExtra();
                        extra.setEntrada(formatador1.format(intervalo.getStart().getMillis()));
                        extra.setSaida(formatador1.format(intervalo.getEnd().getMillis()));
                        extra.setQuantidade(Math.toIntExact(
                                TimeUnit.MILLISECONDS.toMinutes( intervalo.getStart().toInstant().toDate().getTime() 
                                                                                    - intervalo.getEnd().toInstant().toDate().getTime()
                                                                )
                                                            ));
                        extras.add(extra);
                      }catch( java.lang.IllegalArgumentException e){}
                      
                      }
                    }
                  
                  //Entrou depois e terminou justo
                  if ( trabalhou.getStart().isAfter(searchInterval.getStart()) &&
                         searchInterval.getEnd().toInstant().toDate().getTime() - trabalhou.getEnd().toInstant().toDate().getTime() ==0 ){
                      //Verifica se final trabalho diferente inicio intervalo
                      
                      
                      try{
                      Interval intervalo = new Interval(trabalhou.getStart(), trabalhou.getEnd());
                       System.out.println("EXTRA 5: "  + formatador1.format(intervalo.getStart().getMillis()) +" "
                                                          + formatador1.format(intervalo.getEnd().getMillis()));
                       HoraExtra extra = new HoraExtra();
                        extra.setEntrada(formatador1.format(intervalo.getStart().getMillis()));
                        extra.setSaida(formatador1.format(intervalo.getEnd().getMillis()));
                        extra.setQuantidade(Math.toIntExact(
                                TimeUnit.MILLISECONDS.toMinutes( intervalo.getStart().toInstant().toDate().getTime() 
                                                                                    - intervalo.getEnd().toInstant().toDate().getTime()
                                                                )
                                                            ));
                        extras.add(extra);
                      }catch( java.lang.IllegalArgumentException e){}
                      
                      
                    }
                  
                  //Entrou justo no intervalo e saiu antes
                    if ( searchInterval.getStart().toInstant().toDate().getTime() - trabalhou.getStart().toInstant().toDate().getTime() ==0 &&
                        trabalhou.getEnd().isBefore(searchInterval.getEnd()) ){
                       try{
                      Interval intervalo = new Interval(searchInterval.getStart(), trabalhou.getEnd());
                       System.out.println("EXTRA 4: "  + formatador1.format(intervalo.getStart().getMillis()) +" "
                                                          + formatador1.format(intervalo.getEnd().getMillis()));
                       HoraExtra extra = new HoraExtra();
                        extra.setEntrada(formatador1.format(intervalo.getStart().getMillis()));
                        extra.setSaida(formatador1.format(intervalo.getEnd().getMillis()));
                        extra.setQuantidade(Math.toIntExact(
                                TimeUnit.MILLISECONDS.toMinutes( intervalo.getStart().toInstant().toDate().getTime() 
                                                                                    - intervalo.getEnd().toInstant().toDate().getTime()
                                                                )
                                                            ));
                        extras.add(extra);
                      }catch( java.lang.IllegalArgumentException e){}
                    }
                  
                      
                   //Entrou antes do inicio do intervalo e saiu depois do intervalo
                   if (trabalhou.getStart().isBefore(searchInterval.getStart()) && trabalhou.getEnd().isAfter(searchInterval.getEnd())) {
                      try{
                      Interval intervalo = new Interval(searchInterval.getStart(), searchInterval.getEnd());
                       System.out.println("EXTRA 4: "  + formatador1.format(intervalo.getStart().getMillis()) +" "
                                                          + formatador1.format(intervalo.getEnd().getMillis()));
                       HoraExtra extra = new HoraExtra();
                        extra.setEntrada(formatador1.format(intervalo.getStart().getMillis()));
                        extra.setSaida(formatador1.format(intervalo.getEnd().getMillis()));
                        extra.setQuantidade(Math.toIntExact(
                                TimeUnit.MILLISECONDS.toMinutes( intervalo.getStart().toInstant().toDate().getTime() 
                                                                                    - intervalo.getEnd().toInstant().toDate().getTime()
                                                                )
                                                            ));
                        extras.add(extra);
                      }catch( java.lang.IllegalArgumentException e){}
                    }
                
                 }
             }
            
        
        
        for(Interval searchInterval: Turnos){
            
             for(Interval trabalhou : Trabalhos){
                 
                 System.out.println("InicioTurno: "+ formatador2.format(searchInterval.getStart().getMillis())
                                  +"\tFinalTurno"+formatador2.format(searchInterval.getEnd().getMillis()));
                   System.out.println("EntradaNoTrabalho: "+ formatador2.format(trabalhou.getStart().getMillis())
                                   +"\tSaidaNoTrabalho"+formatador2.format(trabalhou.getEnd().getMillis())+"\n\n");
                   
                   
                             
                   
                   //1- Entrou depois do inicio do turno 2- e saiu antes do fim do turno
                  if (trabalhou.getStart().isAfter(searchInterval.getStart())  &&
                      (trabalhou.getEnd().isBefore(searchInterval.getEnd()) ||
                          trabalhou.getEnd().toInstant().toDate().getTime() - searchInterval.getEnd().toInstant().toDate().getTime() ==0 ) ) {
                      //Entrou depois e saiu na hora
                      if( trabalhou.getEnd().toInstant().toDate().getTime() - searchInterval.getEnd().toInstant().toDate().getTime() ==0  ){
                          try{
                          //1
                      Interval intervalo = new Interval(searchInterval.getStart(), trabalhou.getStart());
                       System.out.println("ATRASO 1 : "  + formatador1.format(intervalo.getStart().getMillis()) +" "
                                                          + formatador1.format(intervalo.getEnd().getMillis()));
                       Atraso atraso = new Atraso();
                        atraso.setEntrada(formatador1.format(intervalo.getStart().getMillis()));
                        atraso.setSaida(formatador1.format(intervalo.getEnd().getMillis()));
                        atraso.setQuantidade(Math.toIntExact(
                                TimeUnit.MILLISECONDS.toMinutes( intervalo.getStart().toInstant().toDate().getTime() 
                                                                                    - intervalo.getEnd().toInstant().toDate().getTime()
                                                                )
                                                            ));
                        atrasos.add(atraso);
                        }catch( java.lang.IllegalArgumentException e){}
                      }else{
                      
                      try{
                          //1
                      Interval intervalo = new Interval(searchInterval.getStart(), trabalhou.getStart());
                       System.out.println("ATRASO 1 : "  + formatador1.format(intervalo.getStart().getMillis()) +" "
                                                          + formatador1.format(intervalo.getEnd().getMillis()));
                       Atraso atraso = new Atraso();
                        atraso.setEntrada(formatador1.format(intervalo.getStart().getMillis()));
                        atraso.setSaida(formatador1.format(intervalo.getEnd().getMillis()));
                        atraso.setQuantidade(Math.toIntExact(
                                TimeUnit.MILLISECONDS.toMinutes( intervalo.getStart().toInstant().toDate().getTime() 
                                                                                    - intervalo.getEnd().toInstant().toDate().getTime()
                                                                )
                                                            ));
                        atrasos.add(atraso);
                        
                       //2
                      Interval intervalo1 = new Interval(trabalhou.getEnd(), searchInterval.getEnd());
                       System.out.println("ATRASO 2: "  + formatador1.format(intervalo1.getStart().getMillis()) +" "
                                                          + formatador1.format(intervalo1.getEnd().getMillis()));
                       Atraso atraso1 = new Atraso();
                        atraso1.setEntrada(formatador1.format(intervalo1.getStart().getMillis()));
                        atraso1.setSaida(formatador1.format(intervalo1.getEnd().getMillis()));
                        atraso1.setQuantidade(Math.toIntExact(
                                TimeUnit.MILLISECONDS.toMinutes( intervalo.getStart().toInstant().toDate().getTime() 
                                                                                    - intervalo.getEnd().toInstant().toDate().getTime()
                                                                )
                                                            ));
                        atrasos.add(atraso1);
                      }catch( java.lang.IllegalArgumentException e){}
                      
                      }
                    }else{
                       //Entrou depois do inicio do turno e saiu depois
                  if ((trabalhou.getStart().isAfter(searchInterval.getStart()) && trabalhou.getStart().isBefore(searchInterval.getEnd())) && trabalhou.getEnd().isAfter(searchInterval.getEnd())) {
                     
                      if(!TurnoMadrugada){
                           /* boolean tem = false;
                             if(Turnos.size() > 1){
                                 //verifica se tem outro turno anterior

                                    for(Interval a: Turnos){
                                        if(a.getEnd().isBefore(searchInterval.getStart())){
                                            tem = true;
                                        }
                                        
                                    }
                             }
                             if(tem){*/
                                    try{
                                    Interval intervalo = new Interval(searchInterval.getStart(), trabalhou.getStart());
                                     System.out.println("ATRASO 3: "  + formatador1.format(intervalo.getStart().getMillis()) +" "
                                                                        + formatador1.format(intervalo.getEnd().getMillis()));
                                     Atraso atraso = new Atraso();
                        atraso.setEntrada(formatador1.format(intervalo.getStart().getMillis()));
                        atraso.setSaida(formatador1.format(intervalo.getEnd().getMillis()));
                        atraso.setQuantidade(Math.toIntExact(
                                TimeUnit.MILLISECONDS.toMinutes( intervalo.getStart().toInstant().toDate().getTime() 
                                                                                    - intervalo.getEnd().toInstant().toDate().getTime()
                                                                )
                                                            ));
                        atrasos.add(atraso);
                                    }catch( java.lang.IllegalArgumentException e){}
                         //    }
                   
                  }else{
                        try{
                            Interval intervalo = new Interval(searchInterval.getStart(), trabalhou.getStart());
                             System.out.println("ATRASO 3: "  + formatador1.format(intervalo.getStart().getMillis()) +" "
                                                                + formatador1.format(intervalo.getEnd().getMillis()));
                             Atraso atraso = new Atraso();
                        atraso.setEntrada(formatador1.format(intervalo.getStart().getMillis()));
                        atraso.setSaida(formatador1.format(intervalo.getEnd().getMillis()));
                        atraso.setQuantidade(Math.toIntExact(
                                TimeUnit.MILLISECONDS.toMinutes( intervalo.getStart().toInstant().toDate().getTime() 
                                                                                    - intervalo.getEnd().toInstant().toDate().getTime()
                                                                )
                                                            ));
                        atrasos.add(atraso);
                            }catch( java.lang.IllegalArgumentException e){}
                  }
                       }
                  }
                
                 //Começou o trabalho antes do turno ou justo e Saiu antes de terminar o turno
                  if ((trabalhou.getStart().isBefore(searchInterval.getStart()) || 
                           trabalhou.getStart().toInstant().toDate().getTime() - searchInterval.getStart().toInstant().toDate().getTime() ==0 )
                     
                          &&  trabalhou.getEnd().isBefore(searchInterval.getEnd() )) {
                      boolean tem = false;
                     if(Turnos.size() > 1){
                         //verifica se tem outro turno anterior
                           
                            for(Interval a: Turnos){
                                if(a.getEnd().isBefore(searchInterval.getStart()) && a.getStart().isBefore(searchInterval.getStart())){
                                    tem = true;
                                }
                            }
                     }
                     if(!tem){
                      try{
                      Interval intervalo = new Interval(trabalhou.getEnd(), searchInterval.getEnd());
                       System.out.println("ATRASO 4: "  + formatador1.format(intervalo.getStart().getMillis()) +" "
                                                          + formatador1.format(intervalo.getEnd().getMillis()));
                       Atraso atraso = new Atraso();
                        atraso.setEntrada(formatador1.format(intervalo.getStart().getMillis()));
                        atraso.setSaida(formatador1.format(intervalo.getEnd().getMillis()));
                        atraso.setQuantidade(Math.toIntExact(
                                TimeUnit.MILLISECONDS.toMinutes( intervalo.getStart().toInstant().toDate().getTime() 
                                                                                    - intervalo.getEnd().toInstant().toDate().getTime()
                                                                )
                                                            ));
                        atrasos.add(atraso);
                      }catch( java.lang.IllegalArgumentException e){}
                      
                     }else{
                         //Vê se termina dentro do turno
                                   if(trabalhou.getEnd().isBefore(searchInterval.getEnd())
                                           && trabalhou.getEnd().isAfter(searchInterval.getStart()) ){
                                    
                                
                      try{
                      Interval intervalo = new Interval(trabalhou.getEnd(), searchInterval.getEnd());
                       System.out.println("ATRASO 5: "  + formatador1.format(intervalo.getStart().getMillis()) +" "
                                                          + formatador1.format(intervalo.getEnd().getMillis()));
                       Atraso atraso = new Atraso();
                        atraso.setEntrada(formatador1.format(intervalo.getStart().getMillis()));
                        atraso.setSaida(formatador1.format(intervalo.getEnd().getMillis()));
                        atraso.setQuantidade(Math.toIntExact(
                                TimeUnit.MILLISECONDS.toMinutes( intervalo.getStart().toInstant().toDate().getTime() 
                                                                                    - intervalo.getEnd().toInstant().toDate().getTime()
                                                                )
                                                            ));
                        atrasos.add(atraso);
                      }catch( java.lang.IllegalArgumentException e){}
                      
                     }
                         
                     }
                    }
                   
                                
                 
                 
             }
            
                 
             } 
        
        //Não entou ou passou pelo turno
                    //Não Trabalhou no turno
       for(Interval searchInterval: Turnos){
             boolean contains = false;
              
             for(Interval trabalhou : Trabalhos){
                
                      
                    if( (searchInterval.contains(trabalhou.getEndMillis()) 
                            || searchInterval.getEndMillis() - trabalhou.getEndMillis() == 0)
                            ||  searchInterval.contains(trabalhou.getStartMillis())
                            || trabalhou.contains(searchInterval.getEndMillis())
                            || trabalhou.contains(searchInterval.getStartMillis())){
                            
                        contains = true;
                      //Entrou antes e saiu antes
                      //Entrou antes e saiu depois
                      //Entrou depois e saiu depois
                      //Entrou depois e saiu antes
                    }
                    
                
                 
             }
             
             if(!contains){
                 System.out.println("Não Trabalhou no intervalo: "+searchInterval);
                 try{
                      Interval intervalo = searchInterval;
                       System.out.println("ATRASO 6: "  + formatador1.format(intervalo.getStart().getMillis()) +" "
                                                          + formatador1.format(intervalo.getEnd().getMillis()));
                       Atraso atraso = new Atraso();
                        atraso.setEntrada(formatador1.format(intervalo.getStart().getMillis()));
                        atraso.setSaida(formatador1.format(intervalo.getEnd().getMillis()));
                        atraso.setQuantidade(Math.toIntExact(
                                TimeUnit.MILLISECONDS.toMinutes( intervalo.getStart().toInstant().toDate().getTime() 
                                                                                    - intervalo.getEnd().toInstant().toDate().getTime()
                                                                )
                                                            ));
                        atrasos.add(atraso);
                      }catch( java.lang.IllegalArgumentException e){}
             }
             
       }
       
       ResultSubtracao result = new ResultSubtracao();
       List<Interval> ar = new ArrayList<>();
       List<Atraso> newatrasos = new ArrayList<>();
         List<HoraExtra> newextras = new ArrayList<>();
       for(Atraso a :atrasos){
            try{
           Interval interval = new Interval(formatador1.parse(a.getEntrada()).getTime(), formatador1.parse(a.getSaida()).getTime());
            
           ar.add(interval);
            }catch( java.lang.IllegalArgumentException e){}
            
           
       }
        Collections.sort(ar, new IntervalStartComparator());
       if(ar.size()>1){
       Interval interval = new Interval(ar.get(0).getStart(), ar.get(ar.size()-1).getEnd());
       //Interval interval = new Interval(formatador1.parse("08:01").getTime(), formatador1.parse("11:59").getTime());
            
            System.out.println("Interval: "+interval );
            System.out.println("Todos atrasos: "+ar );
            
       List<Interval> arinterno = new ArrayList<>();
       for(Interval atraso : ar){
           
            for(Interval atraso1 : ar){
                    
                        if(atraso1.getStart().isBefore(atraso.getStart()) && atraso1.getEnd().isBefore(atraso.getEnd())){
                              try{
                            Interval a = new Interval(atraso.getStart(), atraso1.getEnd());
                            arinterno.add(a);
                             }catch( java.lang.IllegalArgumentException e){}
                        }
           
                 }
           
       }
       //arinterno = findGaps(ar,interval);
       System.out.println("Atrasos: "+arinterno );
       
         for(Interval atraso: arinterno){
             Atraso NewAtraso = new Atraso();
             NewAtraso.setEntrada(formatador1.format(atraso.getStart().getMillis()));
             NewAtraso.setSaida(formatador1.format(atraso.getEnd().getMillis()));
             NewAtraso.setQuantidade(Math.toIntExact(
                                TimeUnit.MILLISECONDS.toMinutes( atraso.getStart().toInstant().toDate().getTime() 
                                                                                    - atraso.getEnd().toInstant().toDate().getTime()
                                                                )
                                                            ));
             newatrasos.add(NewAtraso);
         }
         if(newatrasos.size()>=1){
             atrasos = newatrasos;
         }
       }else{
           
       }
       
       
       result.setAtrasos(atrasos);
       result.setExtras(extras);
        
        return result;
        
        
    }
    
    
    
    
     
     public List<Interval> findGaps(List<Interval> existingIntervals, Interval searchInterval) {
        List<Interval> gaps = new ArrayList<Interval>();

        DateTime searchStart = searchInterval.getStart();
        DateTime searchEnd = searchInterval.getEnd();

        if (hasNoOverlap(existingIntervals, searchInterval, searchStart, searchEnd)) {
            gaps.add(searchInterval);
            return gaps;
        }

        
        List<Interval> subExistingList = removeNoneOverlappingIntervals(existingIntervals, searchInterval);
        DateTime subEarliestStart = subExistingList.get(0).getStart();
        DateTime subLatestStop = subExistingList.get(subExistingList.size() - 1).getEnd();

        
        if (searchStart.isBefore(subEarliestStart)) {
            gaps.add(new Interval(searchStart, subEarliestStart));
        }

        
        gaps.addAll(getExistingIntervalGaps(subExistingList));

       
        if (searchEnd.isAfter(subLatestStop)) {
            gaps.add(new Interval(subLatestStop, searchEnd));
        }
        return gaps;
    }
    

    private List<Interval> getExistingIntervalGaps(List<Interval> existingList) {
        List<Interval> gaps = new ArrayList<Interval>();
        Interval current = existingList.get(0);
        for (int i = 1; i < existingList.size(); i++) {
            Interval next = existingList.get(i);
            Interval gap = current.gap(next);
            if (gap != null)
                gaps.add(gap);
            current = next;
        }
        return gaps;
    }

    private List<Interval> removeNoneOverlappingIntervals(List<Interval> existingIntervals, Interval searchInterval) {
        List<Interval> subExistingList = new ArrayList<Interval>();
        for (Interval interval : existingIntervals) {
            if (interval.overlaps(searchInterval)) {
                subExistingList.add(interval);
            }
        }
        return subExistingList;
    }

    private boolean hasNoOverlap(List<Interval> existingIntervals, Interval searchInterval, DateTime searchStart, DateTime searchEnd) {
        DateTime earliestStart = existingIntervals.get(0).getStart();
        DateTime latestStop = existingIntervals.get(existingIntervals.size() - 1).getEnd();
        
        if (searchEnd.isBefore(earliestStart) || searchStart.isAfter(latestStop)) {
            return true;
        }
        return false;
    }
    
    public class IntervalStartComparator implements Comparator<Interval> {
    @Override
    public int compare(Interval x, Interval y) {
        return x.getStart().compareTo(y.getStart());
    }
}
}