package com.inacap.misconciertos.dao;

import com.inacap.misconciertos.dto.Evento;

import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    private static List<Evento> eventos =  new ArrayList<Evento>();

    public EventoDAO(){

    }

    public void add(Evento e){
        eventos.add(e);
    }

    public List<Evento> getAll(){
        return eventos;
    }
    }

