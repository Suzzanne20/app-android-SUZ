package com.example.myapplication.entidades;

public class Stock {

    private Integer id;
    private String nombre;
    private String tomo;
    private Number cnt;

    public Stock() {
    }

    public Stock(Integer id, String nombre, String tomo, Number cnt) {
        this.id = id;
        this.nombre = nombre;
        this.tomo = tomo;
        this.cnt = cnt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTomo() {
        return tomo;
    }

    public void setTomo(String tomo) {
        this.tomo = tomo;
    }

    public Number getCnt() {
        return cnt;
    }

    public void setCnt(Number cnt) {
        this.cnt = cnt;
    }
}
