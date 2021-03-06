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
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
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
                       
                   }else{
                       if(TurnoMadrugada){
                       c.setTime(Inicio);
                        c.add(Calendar.DAY_OF_YEAR, +1);
                        Inicio = c.getTime();
                        c.setTime(Final);
                        c.add(Calendar.DAY_OF_YEAR, +1);
                        Final = c.getTime();
                       }
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
            //Primeiro dia ??s 00
            DateTime InicioIntervalo = Turnos.get(0).getStart();
            //Ultimo dia ??s 23:59
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
                 
                  System.out.println("InicioIntervalo: "+ formatador2.format(searchInterval.getStart().getMillis())
                                  +"\tFinalIntervalo"+formatador2.format(searchInterval.getEnd().getMillis()));
                   System.out.println("EntradaNoTrabalho: "+ formatador2.format(trabalhou.getStart().getMillis())
                                   +"\tSaidaNoTrabalho"+formatador2.format(trabalhou.getEnd().getMillis())+"\n\n");
                   
                               
                 
                 //Entrou depois do inicio do intervalo e saiu antes do fim do intervalo
                  if (trabalhou.getStart().isAfter(searchInterval.getStart())  && trabalhou.getEnd().isBefore(searchInterval.getEnd())) {
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
                    
                    //Entrou justo no intervalo e saiu depois
                    if ( searchInterval.getStart().toInstant().toDate().getTime() - trabalhou.getStart().toInstant().toDate().getTime() ==0 &&
                        trabalhou.getEnd().isAfter(searchInterval.getEnd()) ){
                       try{
                      Interval intervalo = new Interval(searchInterval.getStart(), searchInterval.getEnd());
                       System.out.println("EXTRA 4.1: "  + formatador1.format(intervalo.getStart().getMillis()) +" "
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
                    
                    //Entrou justo no intervalo e saiu justo
                    if ( searchInterval.getStart().toInstant().toDate().getTime() - trabalhou.getStart().toInstant().toDate().getTime() ==0 &&
                        searchInterval.getEnd().toInstant().toDate().getTime() - trabalhou.getEnd().toInstant().toDate().getTime() ==0 ){
                       try{
                      Interval intervalo = new Interval(searchInterval.getStart(), searchInterval.getEnd());
                       System.out.println("EXTRA 4.2: "  + formatador1.format(intervalo.getStart().getMillis()) +" "
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
                       System.out.println("EXTRA 4.3: "  + formatador1.format(intervalo.getStart().getMillis()) +" "
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
                   
                   //Entrou antes do inicio do intervalo e saiu justo no intervalo
                   if (trabalhou.getStart().isBefore(searchInterval.getStart()) 
                  &&  searchInterval.getEnd().toInstant().toDate().getTime() - trabalhou.getEnd().toInstant().toDate().getTime() ==0 ){
                       
                      try{
                      Interval intervalo = new Interval(searchInterval.getStart(), searchInterval.getEnd());
                       System.out.println("EXTRA 4.4: "  + formatador1.format(intervalo.getStart().getMillis()) +" "
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
            
        
         Collections.sort(Turnos, new IntervalStartComparator());
        for(Interval searchInterval: Turnos){
            
           
                                
               
               Interval interval1 = new Interval(searchInterval.getStart(), searchInterval.getEnd());
                System.out.println("ATRASOS: "+ findGaps(Trabalhos,interval1));
                
                if(findGaps(Trabalhos,interval1).isEmpty()){
                   
           
             for(Interval trabalhou : Trabalhos){
                 
               /*  System.out.println("InicioTurno: "+ formatador2.format(searchInterval.getStart().getMillis())
                                  +"\tFinalTurno"+formatador2.format(searchInterval.getEnd().getMillis()));
                   System.out.println("EntradaNoTrabalho: "+ formatador2.format(trabalhou.getStart().getMillis())
                                   +"\tSaidaNoTrabalho"+formatador2.format(trabalhou.getEnd().getMillis())+"\n\n");
                   
                   */
                             
                   
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
                  
                  //Come??ou o turno antes e saiu antes
                  
                  
                
                 //Come??ou o trabalho justo e Saiu antes de terminar o turno
                  if (trabalhou.getStart().toInstant().toDate().getTime() - searchInterval.getStart().toInstant().toDate().getTime() ==0 
                     
                          &&  trabalhou.getEnd().isBefore(searchInterval.getEnd() )) {
                      
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
                      
                     
                    }
                   
                                
                 
                 
             }
            
                 
             } 
                    
                    
                
                   
              for(Interval atraso : findGaps(Trabalhos,interval1)){
                   Atraso NewAtraso = new Atraso();
             NewAtraso.setEntrada(formatador1.format(atraso.getStart().getMillis()));
             NewAtraso.setSaida(formatador1.format(atraso.getEnd().getMillis()));
             NewAtraso.setQuantidade(Math.toIntExact(
                                TimeUnit.MILLISECONDS.toMinutes( atraso.getStart().toInstant().toDate().getTime() 
                                                                                    - atraso.getEnd().toInstant().toDate().getTime()
                                                                )
                                                            ));
             atrasos.add(NewAtraso);
              }
            
                 
             }
        
        
        
       
        //N??o entou ou passou pelo turno
                    //N??o Trabalhou no turno
        
       for(Interval searchInterval: Turnos){
             boolean contains = false;
              
             for(Interval trabalhou : Trabalhos){
                
                    List<Interval> ar = new ArrayList<>();
                    ar.add(trabalhou);
                    ar.add(searchInterval);
                      
                    if( (searchInterval.contains(trabalhou) 
                            || findOverlappingIntervalApproach2(ar))){
                            
                        
                        contains = true;
                      //Entrou antes e saiu antes
                      //Entrou antes e saiu depois
                      //Entrou depois e saiu depois
                      //Entrou depois e saiu antes
                    }
                    
                
                 
             }
             
             if(!contains){
                 System.out.println("N??o Trabalhou no intervalo: "+searchInterval);
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
       
       
       //RETIRA ATRASOS DUPLICADOS
       Set<Atraso> set = new HashSet<>(atrasos);
        atrasos.clear();
        atrasos.addAll(set);
       
       result.setAtrasos(atrasos);
       result.setExtras(extras);
        
        return result;
        
        
    }
    
    public static <Atraso> List<Atraso> removeDuplicates(List<Atraso> list)
    {
  
        // Create a new LinkedHashSet
        Set<Atraso> set = new LinkedHashSet<>();
  
        // Add the elements to set
        set.addAll(list);
  
        // Clear the list
        list.clear();
  
        // add the elements of set
        // with no duplicates to the list
        list.addAll(set);
  
        // return the list
        return list;
    }
    
    public List<Interval> findOverlappingIntervalApproach1(List<Interval> intervals) {
        //Collections.sort(intervals); //(n log n)
        List<Interval> overlappingInterval = new ArrayList<>();
 
        for (int i = 0; i < intervals.size()-1; i++) { //n
            if (intervals.get(i).getEndMillis() > intervals.get(i+1).getStartMillis()) {
                overlappingInterval.add(intervals.get(i));
                overlappingInterval.add(intervals.get(i+1));
            }
        }
        return overlappingInterval;
    }
    
    public static boolean findOverlappingIntervalApproach2(List<Interval> intervals){
        //find the highest end time within list of all intervals
        int highestTime = Math.toIntExact(intervals.get(0).getEndMillis());
        for (int i = 1; i<intervals.size(); i++) {
            int endTime = Math.toIntExact(intervals.get(i).getEndMillis());
            if(highestTime < endTime)
                highestTime = endTime;
        }
 
        int[] count = new int[highestTime + 1];
        //Mark the count[startTime] to +1 and count[endTime] to -1
        for (int i = 0; i<intervals.size(); i++) {
            Interval current = intervals.get(i);
            count[Math.toIntExact(current.getStartMillis())]++;
            count[Math.toIntExact(current.getEndMillis())]--;
        }
 
        //Iterate count array and sum the values
        //if at any point sum is > 1 that means interval overlaps.
        boolean isOverlappingIntervals = false;
 
        int sum = 0;
        for (int i = 0; i <count.length; i++) {
            sum += count[i];
            if(sum > 1){
                isOverlappingIntervals = true;
                break;
            }
        }
 
        return isOverlappingIntervals;
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
        if(subExistingList.size()>0){
        DateTime subEarliestStart = subExistingList.get(0).getStart();
        DateTime subLatestStop = subExistingList.get(subExistingList.size() - 1).getEnd();

        
        if (searchStart.isBefore(subEarliestStart)) {
            gaps.add(new Interval(searchStart, subEarliestStart));
        }

        
        gaps.addAll(getExistingIntervalGaps(subExistingList));

       
        if (searchEnd.isAfter(subLatestStop)) {
            gaps.add(new Interval(subLatestStop, searchEnd));
        }
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
        
        DateTime earliestStart;
         earliestStart = existingIntervals.get(0).getStart();
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