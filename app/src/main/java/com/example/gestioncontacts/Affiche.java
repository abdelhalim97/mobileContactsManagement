package com.example.gestioncontacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Affiche extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    ListView lv_liste;
    EditText ed_recherche;
    Boolean call_permission=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche);

        lv_liste=findViewById(R.id.lv_liste_cont);
        ed_recherche=findViewById(R.id.ed_recherche_cont);

        if(ContextCompat.checkSelfPermission(Affiche.this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED)
            call_permission=true;
        else {
            call_permission = false;
            ActivityCompat.requestPermissions(Affiche.this,new String[] {Manifest.permission.CALL_PHONE},1);
        }



        ContactAdapter ca = new ContactAdapter(Affiche.this,Acceuil.data);
        lv_liste.setAdapter(ca);

        lv_liste.setOnItemClickListener(this);
        lv_liste.setOnItemLongClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder alert = new AlertDialog.Builder(Affiche.this);
        alert.setTitle("Attention!!");
        alert.setMessage("Choisir une action:");
        alert.show();

        alert.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Contact c= Acceuil.data.get(position);
                        int pos=Acceuil.data.indexOf(c);
                        Intent i =new Intent(Affiche.this,Edit.class);
                        i.putExtra("pos",pos);
                        startActivity(i);

                    }
                });

        alert.setNegativeButton("Supprimer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               Acceuil.data.remove(position);
               lv_liste.invalidateViews();
            }
        });

        alert.setNeutralButton("Supprimer tous", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Acceuil.data.clear();
                lv_liste.invalidateViews();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Contact c=Acceuil.data.get(position);
       String num_tel=c.getNtel();
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + num_tel));
        try {
            this.startActivity(callIntent);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),"Your call failed... " + ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call_permission = true;
                } else call_permission = false;
            }
        }
    }
}