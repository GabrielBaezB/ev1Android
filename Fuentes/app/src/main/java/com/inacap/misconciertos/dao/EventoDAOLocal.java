package com.inacap.misconciertos.dao;

import com.inacap.misconciertos.dto.Evento;

import java.util.List;

public interface EventoDAOLocal {

    public void add (Evento e);
    public List<Evento> getAll();
}
