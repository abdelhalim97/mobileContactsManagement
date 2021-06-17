package com.example.gestioncontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ajout extends AppCompatActivity implements View.OnClickListener {
    Button btn_quiter,btn_valider;
    EditText ed_nom,ed_prenom,ed_telephone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);

        btn_valider=findViewById(R.id.btn_valider_ajout);
        btn_quiter=findViewById(R.id.btn_quitter_ajout);
        ed_nom=findViewById(R.id.ed_nom_ajout);
        ed_prenom=findViewById(R.id.ed_prenom_ajout);
        ed_telephone=findViewById(R.id.ed_tel_ajout);

        btn_quiter.setOnClickListener(this);
        btn_valider.setOnClickListener(this);

        Intent x=this.getIntent();

    }

    @Override
    public void onClick(View v) {
    if (v==btn_quiter)
    {
       this.finish();
    }
    if(v==btn_valider)
    {
        String nom =ed_nom.getText().toString();
        String prenom=ed_prenom.getText().toString();
        String ntel=ed_telephone.getText().toString();

        if (nom.length()!=0 && prenom.length()!=0 && ntel.length()==8) {
            Contact c =new Contact(nom,prenom,ntel);
            Acceuil.data.add(c);
            Toast.makeText(this, "Contact Ajouté", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Verifier les champs ", Toast.LENGTH_SHORT).show();
    }
    }
}