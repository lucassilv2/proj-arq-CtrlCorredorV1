/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bcopstein.CtrlCorredorV1;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;

class EstatísticasDTO {

    public double getMedia() {
        return media;
    }

    public double getMediana() {
        return mediana;
    }

    public double getDesvio_padrao() {
        return desvio_padrao;
    }

    private double media;
    private double mediana;
    private double desvio_padrao;
    private EventoRepository eR;
    
    @Autowired
    public EstatísticasDTO(int distancia,  EventoRepository eR){
        this.eR = eR;
        List<Evento> eventos = this.eR.eventoDistancia(distancia);
        double h = 0;
        double m = 0;
        double s = 0;
        ArrayList<Double> list = new ArrayList<Double>();
        for (Evento e : eventos) {
            h =+ e.getHoras();
            m =+ e.getMinutos();
            s =+ e.getSegundos();
            addList(list, h,m,s);
        }
        Collections.sort(list);

        double middle = list.size()/2;
        if (list.size()%2 == 0) {
           middle = (list.get(list.size()/2) + list.get(list.size()/2 - 1))/2;
        } else {
            middle = list.get(list.size() / 2);
        }
 
        this.mediana = middle;
        
        
        s = s/60;
        h = h*60;
        m = m + h +s;
        this.media = m / eventos.size();
        this.desvio_padrao = getDesvioPadrao(list);
    }
    public void addList (List<Double> a, double h,double m,double s){
        s = s/60;
        h = h*60;
        m = m + h +s;
        a.add(m);
    }
    
    public Double getDesvioPadrao(List<Double> valor) {
        Double media = this.media;
        int tam = valor.size();
        Double desvPadrao = 0D;
        for (Double vlr : valor) {
            Double aux = vlr - media;
            desvPadrao += aux * aux;
        }
        return Math.sqrt(desvPadrao / (tam - 1));
    }
}
