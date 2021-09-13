package com.alaafia.GestionBanque.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte {

    private double taux;

    public CompteEpargne() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CompteEpargne(String codeCompte, Date dateCreation, double solde, Client cleint, double taux) {
        super(codeCompte, dateCreation, solde, cleint);
        this.taux = taux;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

}