package com.example.gestioncontacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
   Context con;
   ArrayList<Contact> data;

    public ContactAdapter(Context con, ArrayList<Contact> data) {
        this.con = con;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inf = LayoutInflater.from(con);
        View v =inf.inflate(R.layout.view_contact,null);


        TextView tv_nom=v.findViewById(R.id.tv_nom_contact);
        TextView tv_prenom=v.findViewById(R.id.tv_prenom_contact);
        TextView tv_num=v.findViewById(R.id.tv_num_contact);


        Contact c = data.get(position);
        tv_nom.setText(c.nom);
        tv_prenom.setText(c.prenom);
        tv_num.setText(c.ntel);

        return v;


    }
}
