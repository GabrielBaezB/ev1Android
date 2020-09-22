package com.inacap.misconciertos.dto;

public class Evento {

    private String name;
    private String fecha;
    private Boolean generoMusical;
    private int valorEntrada;
    private int califi;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Boolean getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(Boolean generoMusical) {
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
    public String toString(){
        return "Fecha: "+fecha +"  Nombre: "+ name+" Valor Entrada: $"+valorEntrada+"    "+califi;
    }
}
