package com.alaafia.GestionBanque.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("V")
public class Versement extends Operation{

    public Versement() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Versement(Date dateOperation, double montant, Compte compte) {
        super(dateOperation, montant, compte);
        // TODO Auto-generated constructor stub
    }

}
