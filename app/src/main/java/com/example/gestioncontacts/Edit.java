package com.example.gestioncontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Edit extends AppCompatActivity {
    EditText ed_nom,ed_prenom,ed_telephone;
    Button btn_terminer,btn_annuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ed_nom=findViewById(R.id.ed_nom_modif);
        ed_prenom=findViewById(R.id.ed_prenom_modif);
        ed_telephone=findViewById(R.id.ed_tel_modif);
        btn_terminer=findViewById(R.id.btn_valider_modif);
        btn_annuler=findViewById(R.id.btn_quitter_modif);

        Intent x= getIntent();
        Bundle b=x.getExtras();
        int pos =b.getInt("pos");
        btn_annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_terminer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom =ed_nom.getText().toString();
                String prenom=ed_prenom.getText().toString();
                String ntel=ed_telephone.getText().toString();

                Contact c =new Contact(nom,prenom,ntel);
                Acceuil.data.set(pos,c);

                Intent i =new Intent(Edit.this,Acceuil.class);
                startActivity(i);
            }
        });
    }
}