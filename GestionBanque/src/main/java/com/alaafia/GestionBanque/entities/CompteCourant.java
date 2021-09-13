package com.alaafia.GestionBanque.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {

    private double decouvert;

    public CompteCourant() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CompteCourant(String codeCompte, Date dateCreation, double solde, Client cleint, double decouvert) {
        super(codeCompte, dateCreation, solde, cleint);
        this.decouvert = decouvert;
    }

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }

}
