package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button navigateButton0, navigateButton1, navigateButton2, navigateButton3, navigateButton4;
    private TextView Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this, "db_stock", null, 1);

        Title = findViewById(R.id.Title);
        navigateButton0 = findViewById(R.id.navigateButton0);
        navigateButton1 = findViewById(R.id.navigateButton1); // New Button to navigate to MainActivity2
        navigateButton2 = findViewById(R.id.navigateButton2);
        navigateButton3 = findViewById(R.id.navigateButton3);
        navigateButton4 = findViewById(R.id.navigateButton4);

        navigateButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PreciosActivity.class);
                startActivity(intent);
            }});
        navigateButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }});
        navigateButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_Stock.class);
                startActivity(intent);
            }});
        navigateButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_ConsultaSt.class);
                startActivity(intent);
            }});
        navigateButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_ListaStock.class);
                startActivity(intent);
            }});
    }

}



