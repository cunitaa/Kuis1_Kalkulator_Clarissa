package com.example.kuis1_kalkulator_clarissa;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText etAngka1, etAngka2;
    private TextView tvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etAngka1 = findViewById(R.id.etAngka1);
        etAngka2 = findViewById(R.id.etAngka2);
        tvHasil = findViewById(R.id.tvHasil);

        Button btnTambah = findViewById(R.id.btnTambah);
        Button btnKurang = findViewById(R.id.btnKurang);
        Button btnKali = findViewById(R.id.btnKali);
        Button btnBagi = findViewById(R.id.btnBagi);

        btnTambah.setOnClickListener(v -> hitung('+'));
        btnKurang.setOnClickListener(v -> hitung('-'));
        btnKali.setOnClickListener(v -> hitung('*'));
        btnBagi.setOnClickListener(v -> hitung('/'));
    }

    private void hitung(char operator) {
        String input1 = etAngka1.getText().toString();
        String input2 = etAngka2.getText().toString();

        if (input1.isEmpty() || input2.isEmpty()) {
            Toast.makeText(this, getString(R.string.error_input), Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double angka1 = Double.parseDouble(input1);
            double angka2 = Double.parseDouble(input2);
            double hasil;

            switch (operator) {
                case '+':
                    hasil = angka1 + angka2;
                    break;
                case '-':
                    hasil = angka1 - angka2;
                    break;
                case '*':
                    hasil = angka1 * angka2;
                    break;
                case '/':
                    if (angka2 == 0) {
                        Toast.makeText(this, getString(R.string.error_nol), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    hasil = angka1 / angka2;
                    break;
                default:
                    return;
            }

            String hasilStr;
            if (hasil == (long) hasil) {
                hasilStr = String.format(Locale.getDefault(), "%d", (long) hasil);
            } else {
                hasilStr = String.format(Locale.getDefault(), "%.2f", hasil);
            }

            tvHasil.setText(getString(R.string.hasil_format, hasilStr));

        } catch (NumberFormatException e) {
            Toast.makeText(this, getString(R.string.error_invalid), Toast.LENGTH_SHORT).show();
        }
    }
}
