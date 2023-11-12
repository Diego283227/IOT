package com.example.sqlite;

public class Laboratorio {
    private int id;
    private String nombre;
    private String labcustom;

    public Laboratorio(){

    }

    public Laboratorio(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString(){
        this.labcustom = this.labcustom = nombre;
        return labcustom;
    }

}

