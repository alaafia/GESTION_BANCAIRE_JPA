package com.alaafia.GestionBanque.dao;

import com.alaafia.GestionBanque.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}