package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PreciosActivity extends AppCompatActivity {

    private EditText inputEditText;
    private Button calculateButton, navigateButton, navigateButton2;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precios);

        inputEditText = findViewById(R.id.inputEditText);
        calculateButton = findViewById(R.id.calculateButton);
        navigateButton = findViewById(R.id.navigateButton); // New Button to navigate to MainActivity2
        navigateButton2 = findViewById(R.id.navigateButton2);
        resultTextView = findViewById(R.id.resultTextView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateFinalPrice();
            }
        });

        navigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreciosActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        navigateButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreciosActivity.this, Activity_Stock.class);
                startActivity(intent);
            }
        });
    }

    private void calculateFinalPrice() {
        String input = inputEditText.getText().toString();
        if (input.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese una cantidad.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double pesos = Double.parseDouble(input);
            double result = (pesos * 0.48) + 30;

            // Round to nearest multiple of 5
            double roundedResult;
            if (result % 5 != 0) {
                if (result % 1 > 0.60) {
                    roundedResult = Math.ceil(result / 5) * 5;
                } else {
                    roundedResult = Math.floor(result / 5) * 5;
                }
            } else {
                roundedResult = result;
            }

            resultTextView.setText(String.format("Precio Final QTZ: %.2f", roundedResult));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor, ingrese un número válido.", Toast.LENGTH_SHORT).show();
        }
    }
}