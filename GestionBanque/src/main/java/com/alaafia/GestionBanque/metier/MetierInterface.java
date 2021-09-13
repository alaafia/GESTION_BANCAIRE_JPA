package com.alaafia.GestionBanque.metier;

import com.alaafia.GestionBanque.entities.Compte;
import com.alaafia.GestionBanque.entities.Operation;
import org.springframework.data.domain.Page;

public interface MetierInterface {
    public Compte consulterCompte(String codeCompte);
    public void verser(String codeCompte, double montant);
    public void retirer(String codeCompte, double montant);
    public void virement(String codeCompte1, String codeCompte2,double montant);
    public Page<Operation> listOperation(String codeCompte, int page, int size);

}
