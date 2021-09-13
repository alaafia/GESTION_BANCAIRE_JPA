package com.alaafia.GestionBanque.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation {

    public Retrait() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Retrait(Date dateOperation, double montant, Compte compte) {
        super(dateOperation, montant, compte);
        // TODO Auto-generated constructor stub
    }

}
