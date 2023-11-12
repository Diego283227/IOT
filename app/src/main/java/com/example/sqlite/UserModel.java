package com.example.sqlite;

import java.io.Serializable;

public class UserModel implements Serializable{

    private String lab, rut, name, descripcion;

    private String fecha;
    private String hora;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLab() {
        return lab;}
    public void setLab(String lab){
        this.lab = lab;}

    public String getRut(){ return rut; }
    public void setRut(String rut){this.rut = rut; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha(){ return fecha;}
    public void  setFecha(String fecha){this.fecha=fecha;}

    public String getHora(){ return hora;}

    public void setHora(String hora){this.hora=hora;}
}
