package com.example.ucevaapp20232;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    Button btn2, btn3, secondButton, btnOpenCalculator; // Agregar el botón para abrir la calculadora
    EditText tv1, tv2;
    Button widgetButton; // Agregar referencia al botón del widget

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        secondButton = findViewById(R.id.secondButton);
        btn2 = findViewById(R.id.btnLogIn2);
        btn3 = findViewById(R.id.btnLogIn3);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        widgetButton = findViewById(R.id.widgetButton);

        btnOpenCalculator = findViewById(R.id.btnOpenCalculator); // Enlazar el botón para abrir la calculadora

        Toast.makeText(this, "onCreate", Toast.LENGTH_LONG).show();

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Que hubo mor 1!!!!", Toast.LENGTH_LONG).show();
            }
        });

        btn3.setOnClickListener(this);

        widgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this, "Botón del widget clickeado", Toast.LENGTH_SHORT).show();
            }
        });

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(); // Mostrar la fecha en MainActivity3
            }
        });

        btnOpenCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendar(); // Abrir la calculadora
            }
        });
    }

    public void saludar(View d) {
        Intent ir = new Intent(this, MainActivity3.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        Bundle data = new Bundle();
        data.putString("username", tv1.getText().toString());
        data.putString("passwd", tv2.getText().toString());
        ir.putExtras(data);
        if (tv1.getText().toString().matches("") || tv2.getText().toString().matches("")) {
            AlertDialog.Builder notifier = new AlertDialog.Builder(this);
            notifier.setMessage("Debe diligenciar la tupla usuario contraseña")
                    .setTitle("Advertencia mor");
            notifier.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            notifier.setNegativeButton("Ver términos de referencia", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    openCalendar();
                }
            });

            notifier.show();
        } else {
            startActivity(ir);
        }
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Que hubo mor 3!!!!", Toast.LENGTH_SHORT).show();
    }

    private void showDateDialog() {
        // Obtener la fecha actual
        Calendar calendar = Calendar.getInstance();
        int año = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int día = calendar.get(Calendar.DAY_OF_MONTH);

        // Formatear la fecha en una cadena legible
        String fechaActual = String.format("%d/%02d/%02d", año, mes + 1, día);

        // Crear un Intent para enviar la fecha a MainActivity3
        Intent intent = new Intent(this, MainActivity3.class);
        intent.putExtra("fecha_actual", fechaActual);

        // Iniciar MainActivity3
        startActivity(intent);
    }

    private void openCalendar() {
        // Abrir la aplicación de calendario
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(CalendarContract.CONTENT_URI);
        startActivity(intent);
    }
}
