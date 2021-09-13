package com.alaafia.GestionBanque.web;

import com.alaafia.GestionBanque.dao.ClientRepository;
import com.alaafia.GestionBanque.dao.CompteRepository;
import com.alaafia.GestionBanque.entities.*;
import com.alaafia.GestionBanque.metier.MetierInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class BanqueController {

    @Autowired
    private MetierInterface banqueMetier;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CompteRepository compteRepository;

    @RequestMapping(value= {"/","index"},method= RequestMethod.GET)
    public String index() {

        return "comptes";
    }

    @RequestMapping(value="/consulterCompte")
    public String consulter(Model model, String codeCompte,
                            @RequestParam(name="page", defaultValue="0")int page,
                            @RequestParam(name="size",defaultValue="5")int size) {
        // On appelle la couche metier pour consulter un compte
        try {
            Compte compte = banqueMetier.consulterCompte(codeCompte);
            model.addAttribute("compte", compte);

            Page<Operation> pageOperations = banqueMetier.listOperation(codeCompte, page, size);
            model.addAttribute("listOperations", pageOperations.getContent());
            int[] pages = new int[pageOperations.getTotalPages()];
            model.addAttribute("pages",pages);
            model.addAttribute("pageCourante", page);

        }catch(Exception e) {
            model.addAttribute("exception", e);
        }

        return "comptes";
    }

    @RequestMapping(value="/saveOperation",method=RequestMethod.POST)
    public String saveOperation(Model model,String typeOperation,String codeCompte,double montant,String codeCompte2) {
        try {
            if(typeOperation.equals("versement")) {
                banqueMetier.verser(codeCompte, montant);
            }
            else if(typeOperation.equals("retrait")) {
                banqueMetier.retirer(codeCompte, montant);
            }
            else { // Autrement il s'agit d'un virement.
                banqueMetier.virement(codeCompte, codeCompte2, montant);
            }
        }catch(Exception e) {
            model.addAttribute("error", e);
            return "redirect:/consulterCompte?codeCompte="+codeCompte+"&error="+e.getMessage();
        }

        return "redirect:/consulterCompte?codeCompte="+codeCompte;
    }

    @RequestMapping(value="/nouveau")
    String creerCompte() {

        return "creerCompte";
    }

    @RequestMapping(value="/create",method=RequestMethod.POST)
    public String createNewAccount(String codeCompte, String nom,  String email, String typeCompte,
                                   String decouvert, String taux, String solde) {
        double _solde = Double.parseDouble(solde);

        Client client=clientRepository.save(new Client(nom,email));
        System.out.println("=============::"+typeCompte+"========::=======");
        if(typeCompte.contentEquals("compteCourant")) {
            double _decouvert = Double.parseDouble(decouvert);
            compteRepository.save(new CompteCourant(codeCompte,new Date(),_solde,client,_decouvert));
        }

        if(typeCompte.contentEquals("compteEpargne")) {
            double _taux = Double.parseDouble(taux);
            compteRepository.save(new CompteEpargne(codeCompte,new Date(),_solde,client,_taux));
        }

        return "redirect:/";
    }
}
