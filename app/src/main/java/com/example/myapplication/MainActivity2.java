package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private EditText inputEditText, restanteEditText;
    private Button calculateButton, calculateFinalButton;
    private TextView subTotalTextView, finalAmountTextView, anticipoEditText ;
    private RadioGroup paymentOptionGroup;
    private RadioButton optionCashOnDelivery, optionPrePayment;
    private double subTotal = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialize views
        inputEditText = findViewById(R.id.inputEditText);
        restanteEditText = findViewById(R.id.restanteEditText);
        calculateButton = findViewById(R.id.calculateButton);
        calculateFinalButton = findViewById(R.id.calculateFinalButton);
        subTotalTextView = findViewById(R.id.subTotalTextView);
        finalAmountTextView = findViewById(R.id.finalAmountTextView);
        anticipoEditText = findViewById(R.id.anticipoEditText);
        paymentOptionGroup = findViewById(R.id.paymentOptionGroup);
        optionCashOnDelivery = findViewById(R.id.optionCashOnDelivery);
        optionPrePayment = findViewById(R.id.optionPrePayment);

        // Set listeners
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAmount();
            }
        });

        calculateFinalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateFinalAmount();
            }
        });

    }

    private void addAmount() {
        String input = inputEditText.getText().toString();
        if (input.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese una cantidad.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double amount = Double.parseDouble(input);
            subTotal += amount;
            subTotalTextView.setText(String.format("Sub-Total: Q%.2f", subTotal));
            inputEditText.setText("");
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor, ingrese un número válido.", Toast.LENGTH_SHORT).show();
        }
    }

    private void calculateFinalAmount() {
        double restante = 0;
        double total = 0;
        double anticipo = 0;

        if (optionCashOnDelivery.isChecked()) {
            String restanteStr = restanteEditText.getText().toString();
            if (restanteStr.isEmpty()) {
                Toast.makeText(this, "Por favor, ingrese la Cant al recibir.", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                restante = Double.parseDouble(restanteStr);
                total = subTotal + 35 + (restante * 0.04);
                anticipo = total - restante;
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Por favor, ingrese valores válidos.", Toast.LENGTH_SHORT).show();
                return;
            }
        } else if (optionPrePayment.isChecked()) {
            total = subTotal + 35;
            anticipo = total; // Para depósito previo, el anticipo es igual al total.
            restante = 0; // No hay cantidad restante en este caso.
        } else {
            Toast.makeText(this, "Por favor, seleccione una opción de pago.", Toast.LENGTH_SHORT).show();
            return;
        }

        finalAmountTextView.setText(String.format("TOTAL: Q%.2f", total));
        anticipoEditText.setText(String.format("Anticipo: Q%.2f", anticipo));
        restanteEditText.setText(String.format("%.2f", restante));
    }
}