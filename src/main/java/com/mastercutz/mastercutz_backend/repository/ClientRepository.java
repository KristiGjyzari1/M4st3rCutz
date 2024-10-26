package com.mastercutz.mastercutz_backend.repository;

import com.mastercutz.mastercutz_backend.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
}
