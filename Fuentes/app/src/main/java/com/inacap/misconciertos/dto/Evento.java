package com.inacap.misconciertos.dto;


public class Evento {

    private String name;
    private String generoMusical;
    private int valorEntrada;
    private int califi;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public int getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(int valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public int getCalifi() {
        return califi;
    }

    public void setCalifi(int califi) {
        this.califi = califi;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "name='" + name + '\'' +
                ", generoMusical='" + generoMusical + '\'' +
                ", valorEntrada=" + valorEntrada +
                ", califi=" + califi +
                '}';
    }
}
