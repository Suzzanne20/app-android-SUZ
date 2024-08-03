package com.example.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.entidades.Stock;
import com.example.myapplication.utilidades.Utilidades;

import java.util.ArrayList;

public class Activity_ConsultaSt extends AppCompatActivity {

    private Spinner idEditText;
    private EditText /*idEditText,*/nombreEditText, tomoEditText, cntEditText;
    private Button buscarButton, actualizarButton, eliminarButton;
    ArrayList<String> listaMangas;
    ArrayList<Stock> stockList;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_st);

        conn = new ConexionSQLiteHelper(this, "db_stock", null, 1);
        idEditText      = findViewById(R.id.idEditText);
        nombreEditText  = findViewById(R.id.nombreEditText);
        tomoEditText    = findViewById(R.id.tomoEditText);
        cntEditText     = findViewById(R.id.cntEditText);
        actualizarButton = findViewById(R.id.actualizarButton);
        eliminarButton  = findViewById(R.id.eliminarButton);

        consultarStock();
        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter(this, android.R.layout.simple_spinner_item,listaMangas);
        idEditText.setAdapter(adaptador);
        idEditText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i !=0 ){
                    Stock stock = stockList.get(i - 1);
                    nombreEditText.setText(stock.getNombre());
                    tomoEditText.setText(stock.getTomo());
                    cntEditText.setText(String.valueOf(stock.getCnt()));
                }else{
                    limpiarCampos();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        actualizarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actuaManga();
            }
        });

        eliminarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elimManga();
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
        cursor.close();
        obtenerLista();
    }

    private void obtenerLista() {
        listaMangas=new ArrayList<String>();
        listaMangas.add("Selecciona..");

        for (int i=0; i<stockList.size();i++){
            listaMangas.add(stockList.get(i).getId()+" - "+stockList.get(i).getNombre());
        }
    }


    private void elimManga() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros={String.valueOf(stockList.get(idEditText.getSelectedItemPosition()-1).getId())};

        db.delete(Utilidades.TABLA_STOCK,Utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Registro eliminado",Toast.LENGTH_SHORT).show();
        limpiarCampos();
        consultarStock();
        db.close();
    }

    private void actuaManga() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros={String.valueOf(stockList.get(idEditText.getSelectedItemPosition()-1).getId())};

        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE,nombreEditText.getText().toString());
        values.put(Utilidades.CAMPO_TOMO, tomoEditText.getText().toString());
        values.put(Utilidades.CAMPO_CNT, cntEditText.getText().toString());

        db.update(Utilidades.TABLA_STOCK,values,Utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(), "Se actualizo el registro", Toast.LENGTH_SHORT).show();
        limpiarCampos();
        consultarStock();
        db.close();
    }

    private void limpiarCampos(){
        nombreEditText.setText("");
        tomoEditText.setText("");
        cntEditText.setText("");
    }


}