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

    public String getProvas() {
        return Provas;
    }
     private String Provas;
     
     private EventoRepository eR;
    
    public PerformanceDTO(int distancia, int ano ,EventoRepository eR){
       this.eR = eR;
       double s = 0;
       double h = 0;
       double m = 0;
       double s1 = 0;
       double h1 = 0;
       double m1 = 0;
       double menor = Double.MIN_VALUE; 
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
            if(m1-m > menor){
                menor = m1-m;
                this.Provas = eventos.get(i).getNome()+" e "+ eventos.get(i+1).getNome();
            }
          }
       } 
       
    }
}
