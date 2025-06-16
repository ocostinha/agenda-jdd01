package com.alura.agenda.repository;

import com.alura.agenda.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgendaRepository extends JpaRepository<Contato, Long> {

    Optional<Contato> findByEmail(String email);

}
