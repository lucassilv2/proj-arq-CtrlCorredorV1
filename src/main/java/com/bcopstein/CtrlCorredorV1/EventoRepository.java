package com.bcopstein.CtrlCorredorV1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;



@Component
public class EventoRepository {
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public EventoRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        
        this.jdbcTemplate.execute("DROP TABLE eventos IF EXISTS");
        this.jdbcTemplate.execute("CREATE TABLE eventos("
                + "id int, nome VARCHAR(255), dia int, mes int, ano int, distancia int, horas int, minutos int, segundos int,PRIMARY KEY(id))");
        
        this.jdbcTemplate.update(
                "INSERT INTO eventos(id,nome,dia,mes,ano,distancia,horas,minutos,segundos) VALUES" +
                " ('1','Poa Day Run',22,5,2019,5,0,35,32)");
        this.jdbcTemplate.update(
                "INSERT INTO eventos(id,nome,dia,mes,ano,distancia,horas,minutos,segundos) VALUES" +
                " ('2','Poa Day Run2',22,5,2019,5,2,0,32)");
 
    }
    
    public List<Evento> todos(){
        List<Evento> resp = this.jdbcTemplate.query("SELECT * from eventos",
                (rs, rowNum) -> new Evento(rs.getInt("id"), rs.getString("nome"), rs.getInt("dia"), rs.getInt("mes"),
                        rs.getInt("ano"), rs.getInt("distancia"), rs.getInt("horas"), rs.getInt("minutos"),
                        rs.getInt("segundos")));
        return resp;
    }
    public boolean cadastra(Evento evento){
         this.jdbcTemplate.update("INSERT INTO eventos(id,nome,dia,mes,ano,distancia,horas,minutos,segundos) VALUES (?,?,?,?,?,?,?,?,?)",
            evento.getId(), evento.getNome(), evento.getDia(), evento.getMes(), evento.getAno(),
            evento.getDistancia(), evento.getHoras(), evento.getMinutos(), evento.getSegundos());
        return true;
    }
    
    public List<Evento> eventoDistancia(int distancia){
        String sql = ("SELECT * from eventos Where distancia = ?");
        return (List<Evento>) jdbcTemplate.query(
            sql, 
            new Object[]{distancia}, 
            (rs, rowNum) -> new Evento(rs.getInt("id"), rs.getString("nome"), rs.getInt("dia"), rs.getInt("mes"),
                        rs.getInt("ano"), rs.getInt("distancia"), rs.getInt("horas"), rs.getInt("minutos"),
                        rs.getInt("segundos")));
    }
    
    public List<Evento> eventoDistancia2(int distancia,int ano ){
        String sql = ("SELECT * from eventos Where distancia = ? AND ano = ?");
        return (List<Evento>) jdbcTemplate.query(
            sql, 
            new Object[]{distancia,ano}, 
            (rs, rowNum) -> new Evento(rs.getInt("id"), rs.getString("nome"), rs.getInt("dia"), rs.getInt("mes"),
                        rs.getInt("ano"), rs.getInt("distancia"), rs.getInt("horas"), rs.getInt("minutos"),
                        rs.getInt("segundos")));
    }
}
