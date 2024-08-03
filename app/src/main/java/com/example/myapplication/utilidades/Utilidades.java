package com.example.myapplication.utilidades;

public class Utilidades {

    //Constantes tabla stock
    public static final String TABLA_STOCK="stock";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_TOMO="tomo";
    public static final String CAMPO_CNT="cnt";

    public static final String CREAR_TABLA_STOCK="CREATE TABLE "+TABLA_STOCK+"(" +
            CAMPO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_NOMBRE+" TEXT, "+
            CAMPO_TOMO+" TEXT, "+
            CAMPO_CNT+" INTEGER)";
}
