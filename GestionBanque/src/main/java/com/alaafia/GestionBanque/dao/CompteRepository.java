package com.alaafia.GestionBanque.dao;

import com.alaafia.GestionBanque.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, String> {

}