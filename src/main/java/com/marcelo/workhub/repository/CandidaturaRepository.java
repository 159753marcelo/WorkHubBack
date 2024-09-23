package com.marcelo.workhub.repository;

import com.marcelo.workhub.model.Candidatura;
import com.marcelo.workhub.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura,Long> {

}
