package com.example.myapplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.utilidades.Utilidades;

public class Activity_Stock extends AppCompatActivity {

    private EditText nombreEditText, tomoEditText, cntEditText;
    private Button RegistrarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        nombreEditText  = findViewById(R.id.nombreEditText);
        tomoEditText    = findViewById(R.id.tomoEditText);
        cntEditText     = findViewById(R.id.cntEditText);
        RegistrarButton = findViewById(R.id.RegistrarButton);

        RegistrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  registrarStock();  }
        });
}

    public void registrarStock(){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "db_stock", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_NOMBRE, nombreEditText.getText().toString());
            values.put(Utilidades.CAMPO_TOMO, tomoEditText.getText().toString());
            values.put(Utilidades.CAMPO_CNT, Integer.parseInt(cntEditText.getText().toString()));

            long result = db.insert(Utilidades.TABLA_STOCK, null, values);

            if (result != -1) {
                Toast.makeText(this, "Registro realizado con éxito", Toast.LENGTH_SHORT).show();
                limpiarCampos();
            } else {
                Toast.makeText(this, "No se pudo registrar", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e){
            Toast.makeText(this, "Error: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("DB_ERROR", "Error al registrar: ", e);
        } finally {
            db.close();
        }
    }
    private void limpiarCampos(){
        nombreEditText.setText("");
        tomoEditText.setText("");
        cntEditText.setText("");
    }


}