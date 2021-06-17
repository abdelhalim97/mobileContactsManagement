package com.example.gestioncontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    Button btn_valider,btn_quitter;
    EditText ed_nom,ed_mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_valider=findViewById(R.id.btn_valider_auth);
        btn_quitter=findViewById(R.id.btn_quitter_auth);
        ed_nom=findViewById(R.id.ed_nom_auth);
        ed_mdp=findViewById(R.id.ed_mdp_auth);

        btn_quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        btn_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom=ed_nom.getText().toString();
                String mdp=ed_mdp.getText().toString();
                if (nom.equalsIgnoreCase("halim") && mdp.equals("123456"))
                {
                    Intent i = new Intent(MainActivity.this, Acceuil.class);
                    i.putExtra("USER",nom);
                    startActivity(i);
                }
                else
                    Toast.makeText(MainActivity.this, "Information erroné", Toast.LENGTH_SHORT).show();

            }
        });
    }
    }