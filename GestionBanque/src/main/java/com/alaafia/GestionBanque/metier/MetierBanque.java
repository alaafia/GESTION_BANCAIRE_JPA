package com.alaafia.GestionBanque.metier;

import com.alaafia.GestionBanque.dao.CompteRepository;
import com.alaafia.GestionBanque.dao.OperationRepository;
import com.alaafia.GestionBanque.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class MetierBanque implements MetierInterface{
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;

    @Override
    public Compte consulterCompte(String codeCompte) {
        Optional<Compte> compte = compteRepository.findById(codeCompte);
        if(compte==null) {
            throw new RuntimeException("Le compte que vous avez saisi est introuvable.");
        }
        return compte.get();
    }


    @Override
    public void verser(String codeCompte, double montant) {
        Compte compte = compteRepository.getOne(codeCompte);
        Versement versement = new Versement(new Date(), montant, compte);
        operationRepository.save(versement);
        compte.setSolde(compte.getSolde() + montant);
        compteRepository.save(compte);

    }


    @Override
    public void retirer(String codeCompte, double montant) {

        Compte compte = compteRepository.getOne(codeCompte);
        double faciliteCaisse = 0;
        if(compte instanceof CompteCourant) {
            faciliteCaisse = ((CompteCourant) compte).getDecouvert();
        }
        if(faciliteCaisse + compte.getSolde()<montant) {
            throw new RuntimeException("Votre sold est insuffisant pour effectuer cette opération");
        }
        Retrait retrait = new Retrait(new Date(), montant, compte);
        operationRepository.save(retrait);
        compte.setSolde(compte.getSolde() - montant);
        compteRepository.save(compte);

    }

    @Override
    public void virement(String codeCompte1, String codeCompte2, double montant) {
        if(codeCompte1.equals(codeCompte2)) {
            throw new RuntimeException("Le virement d'un compte vers ce même compte n'est pas autorisé!");
        }
        retirer(codeCompte1,montant);
        verser(codeCompte2,montant);
    }

    @Override
    public Page<Operation> listOperation(String codeCompte, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return operationRepository.listOperation(codeCompte, paging) ;
    }

}

