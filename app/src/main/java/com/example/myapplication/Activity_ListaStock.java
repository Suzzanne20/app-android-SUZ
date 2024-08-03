package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.entidades.Stock;
import com.example.myapplication.utilidades.Utilidades;

import java.util.ArrayList;

public class Activity_ListaStock extends AppCompatActivity {

    ListView listViewStock;
    ArrayList<String> listaMangas;
    ArrayList<Stock> stockList;

    ConexionSQLiteHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_stock);

        conn = new ConexionSQLiteHelper(this, "db_stock", null, 1);

        listViewStock  = findViewById(R.id.listViewStock);

        consultarStock();
        ArrayAdapter adaptador=new ArrayAdapter(this, android.R.layout.simple_spinner_item,listaMangas);
        listViewStock.setAdapter(adaptador);
        listViewStock.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacion="id: "+stockList.get(pos).getId()+"\n";
                informacion+="Nombre: "+stockList.get(pos).getNombre()+"\n";
                informacion+="Tomos: "+stockList.get(pos).getTomo()+"\n";
                informacion+="Cantidad: "+stockList.get(pos).getCnt()+"\n";

                Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_SHORT).show();
                /*Stock manga=stockList.get(pos);
                Intent intent=new Intent(Activity_ListaStock.this,)*/
            }

        });

}

    private void consultarStock() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Stock stock=null;
        stockList=new ArrayList<Stock>();

        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_STOCK,null);
        while (cursor.moveToNext()){
            stock=new Stock();
            stock.setId(cursor.getInt(0));
            stock.setNombre(cursor.getString(1));
            stock.setTomo(cursor.getString(2));
            stock.setCnt(cursor.getInt(3));

            stockList.add(stock);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaMangas=new ArrayList<String>();

        for (int i=0; i<stockList.size();i++){
            listaMangas.add(stockList.get(i).getId()+"    "+stockList.get(i).getNombre());
        }
    }

}