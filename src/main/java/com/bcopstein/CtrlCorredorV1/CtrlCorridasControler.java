package com.bcopstein.CtrlCorredorV1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ctrlCorridas")
public class CtrlCorridasControler {
    private CorredorRepository cR;
    private EventoRepository eR;

    @Autowired
    public CtrlCorridasControler(CorredorRepository cR, EventoRepository eR) {
        this.cR = cR;
        this.eR = eR;
    }

    @GetMapping("/corredor")
    @CrossOrigin(origins = "*")
    public List<Corredor> consultaCorredor() {
       return this.cR.todos();
    }

    @PostMapping("/corredor")
    @CrossOrigin(origins = "*")
    public boolean cadastraCorredor(@RequestBody final Corredor corredor) {
       return this.cR.cadastra(corredor);
    }

    @GetMapping("/eventos")
    @CrossOrigin(origins = "*")
    public List<Evento> consultaEventos() {
        return this.eR.todos();
    }

    @PostMapping("/eventos") // adiciona evento no único corredor
    @CrossOrigin(origins = "*")
    public boolean informaEvento(@RequestBody final Evento evento) {
        return this.eR.cadastra(evento);
    }
    
    @GetMapping("/estatisticas") 
    @CrossOrigin(origins = "*") 
    public EstatísticasDTO estatisticas(@RequestParam final int distancia){ 
       EstatísticasDTO estatistica = new EstatísticasDTO(distancia);
       return estatistica;
    }
    
    @GetMapping("/aumentoPerformance") 
    @CrossOrigin(origins = "*") 
    public PerformanceDTO aumentoPerformance(@RequestParam final int distancia, @RequestParam final int ano){ 
        PerformanceDTO performance = new PerformanceDTO(distancia,ano);
        return performance;
    }
}
