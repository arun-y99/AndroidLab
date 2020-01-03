package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button b;
    EditText n1,n2,n3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = findViewById(R.id.button);
        n1 = findViewById(R.id.n1);
        n2 = findViewById(R.id.n2);
        n3 = findViewById(R.id.n3);

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                double x = Double.parseDouble(n1.getText().toString()) + Double.parseDouble(n2.getText().toString());
                n3.setText(Double.toString(x));
            }
        });
    }


}
