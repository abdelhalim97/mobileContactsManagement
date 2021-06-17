package com.example.gestioncontacts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Acceuil extends AppCompatActivity implements View.OnClickListener {
    Button btn_affichage,btn_ajout;
    TextView tv_acceuil;
    Boolean write_permission=false;
    Boolean read_permission =false;
    static ArrayList<Contact> data=new ArrayList<Contact>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);



        if(ContextCompat.checkSelfPermission(Acceuil.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
            write_permission=true;
        else {
            write_permission = false;
            ActivityCompat.requestPermissions(Acceuil.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }


        if (ContextCompat.checkSelfPermission(Acceuil.this,Manifest.permission.READ_CONTACTS)==PackageManager.PERMISSION_GRANTED)
            read_permission =true;
        else{
            read_permission =false;
            ActivityCompat.requestPermissions(Acceuil.this,new String[]{Manifest.permission.READ_CONTACTS},2);
        }


        if(write_permission)
            importer();
        
        if(read_permission)
            recupContacts();

        btn_affichage=findViewById(R.id.btn_affiche_acc);
        btn_ajout=findViewById(R.id.btn_ajout_acc);
        tv_acceuil=findViewById(R.id.tv_acceuil_acc);

        btn_ajout.setOnClickListener(this);
        btn_affichage.setOnClickListener(this);

        Intent x=this.getIntent();
        Bundle b=x.getExtras();
        String user =b.getString("USER");
        tv_acceuil.setText("Acceuil "+user);

    }

    public void onClick(View v){
        if(v==btn_ajout)
        {
            Intent i =new Intent(Acceuil.this,Ajout.class);
            startActivity(i);
        }
        else
        {
            Intent i = new Intent(Acceuil.this,Affiche.class);
            startActivity(i);
        }

    }

    public  void recupContacts(){
        ContentResolver contentResolver = this.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE,
                        ContactsContract.CommonDataKinds.Phone.NUMBER},
                null,null,null);
        if(cursor==null)
        {
            Log.e("msg","erreur recuperation");
        }
        else
        {
            while (cursor.moveToNext()){
                String prenom=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String nom =cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE));
                String n_tel=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                Contact c =new Contact(prenom,nom,n_tel);
                data.add(c);
            }

        }
        cursor.close();
    }

    public void sauvgarder (){
        String root= Environment.getExternalStorageDirectory().getPath();
        File file =new File(root,"mescontact.csv");
        try {
            FileWriter fw = new FileWriter (file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i=0;i<data.size();i++)
            {
                bw.write(data.get(i).nom+","+data.get(i).prenom+","+data.get(i).ntel+"\n");
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            Log.e("msg","erreur sauvgarde"+e.getMessage());
        }
    }

    public void importer(){
        String root=Environment.getExternalStorageDirectory().getPath();
        File file = new File(root,"mescontact.csv");
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while((line = br.readLine()) != null)
            {
                String[] t = line.split(",");
                Contact c = new Contact(t[0], t[1], t[2]);
                data.add(c);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            Log.e("msg","erreur lecture"+e.getMessage());
        }
    }

    @Override
    protected void onStart() {
        Toast.makeText(this, "Acceuil Started", Toast.LENGTH_SHORT).show();
        super.onStart();
    }

    @Override
    protected void onStop() {
        Toast.makeText(this, "Acceuil Stopped", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "Acceuil Destroyed", Toast.LENGTH_SHORT).show();
        if(write_permission)
            sauvgarder();
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1)
        {
            if(grantResults.length>0)
            {
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    write_permission=true;
                }
                else write_permission=false;
            }
        }
        if (requestCode==2)
        {
            if(grantResults.length>0)
            {
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    read_permission =true;
                }
                else read_permission =false;
            }
        }
    }
}