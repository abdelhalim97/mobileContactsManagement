package com.example.gestioncontacts;

import java.io.Serializable;

public class Contact implements Serializable {
    String nom;
    String prenom;
    String ntel;

    public Contact(String nom, String prenom, String ntel) {
        this.nom = nom;
        this.prenom = prenom;
        this.ntel = ntel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNtel() {
        return ntel;
    }

    public void setNtel(String ntel) {
        this.ntel = ntel;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", ntel='" + ntel + '\'' +
                '}';
    }
}
