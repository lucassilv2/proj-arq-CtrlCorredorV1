/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bcopstein.CtrlCorredorV1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 55519
 */
class PerformanceDTO {
    String Provas;
     private EventoRepository eR;
    
    public PerformanceDTO(int distancia, int ano){
       int s = 0;
       int h = 0;
       int m = 0;
       int s1 = 0;
       int h1 = 0;
       int m1 = 0;
       int menor = Integer.MIN_VALUE; 
       List<Evento> eventos = this.eR.eventoDistancia2(distancia, ano);
       for(int i= 0; i < eventos.size(); i++){
          if(i+1 < eventos.size()){
            h =+ eventos.get(i).getHoras();
            m =+ eventos.get(i).getMinutos();
            s =+ eventos.get(i).getSegundos();
            s = s/60;
            h = h*60;
            m = m + h +s;
            
            h1 =+ eventos.get(i+1).getHoras();
            m1 =+ eventos.get(i+1).getMinutos();
            s1 =+ eventos.get(i+1).getSegundos();
            s1 = s/60;
            h1 = h*60;
            m1 = m + h +s;
            
            if(m-m1 > menor){
                menor = m-m1;
                this.Provas = eventos.get(i).getNome()+" "+ eventos.get(i+1).getNome();
            }
          }
       } 
       
    }
}
