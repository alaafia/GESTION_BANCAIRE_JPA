package com.alaafia.GestionBanque;

import com.alaafia.GestionBanque.DAO.JpaRepositories.ClientRepository;
import com.alaafia.GestionBanque.DAO.JpaRepositories.CompteRepository;
import com.alaafia.GestionBanque.DAO.JpaRepositories.OperationRepository;
import com.alaafia.GestionBanque.METIER.BanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionBanqueApplication {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteReposiory;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private BanqueMetier banqueMetier;


	public static void main(String[] args) {

		SpringApplication.run(GestionBanqueApplication.class, args);

	}
}
