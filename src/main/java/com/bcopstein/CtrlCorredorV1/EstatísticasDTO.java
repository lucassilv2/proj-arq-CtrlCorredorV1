/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bcopstein.CtrlCorredorV1;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class EstatísticasDTO {
    double media;
    double mediana;
    double desvio_padrao;
    private EventoRepository eR;
    
    public EstatísticasDTO(int distancia){
        List<Evento> eventos = this.eR.eventoDistancia(distancia);
        int h = 0;
        int m = 0;
        int s = 0;
        List<Integer> list = new ArrayList<Integer>();
        for (Evento e : eventos) {
            h =+ e.getHoras();
            m =+ e.getMinutos();
            s =+ e.getSegundos();
            addList(list, h,m,s);
        }
        Arrays.sort(list.toArray());

        double median;
        if (list.toArray().length % 2 == 0)
            median = ((double)list.toArray()[list.toArray().length/2] + (double)list.toArray()[list.toArray().length/2 - 1])/2;
        else
        this.mediana = (double) list.toArray()[list.toArray().length/2];
        s = s/60;
        h = h*60;
        m = m + h +s;
        this.media = m / eventos.size();
        this.desvio_padrao = getDesvioPadrao(list);
    }
    public void addList (List<Integer> a, int h,int m,int s){
        s = s/60;
        h = h*60;
        m = m + h +s;
        a.add(m);
    }
    
    public Double getDesvioPadrao(List<Integer> valor) {
        Double media = this.media;
        int tam = valor.size();
        Double desvPadrao = 0D;
        for (Integer vlr : valor) {
            Double aux = vlr - media;
            desvPadrao += aux * aux;
        }
        return Math.sqrt(desvPadrao / (tam - 1));
    }
}
